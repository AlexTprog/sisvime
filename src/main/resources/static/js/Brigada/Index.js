const GetProgramacionBrigadas = "http://localhost:8083/views/visitamed/visitalista"
const DeleteBrigada = "http://localhost:8083/views/visitamed/deletevisita/"
const ShowBrigadas = "http://localhost:8083/views/visitamed/listvisitamedica"

$(document).ready(function () {
    $('#inputDate').datepicker({
        language: "es",
        multidate: true,
        format: 'yyyy-mm-dd',
        startDate: new Date()
    });

    cargarBrigadaYaExistente($("#brigada").val());
});

function submitForm(event) {
    event.preventDefault(); // Evitar el envío normal del formulario

    const form = event.target; // Obtener el formulario que se envió
    const formData = new FormData(form); // Obtener los datos del formulario


    formData.delete("idper.dni")
    formData.delete("idper.nombre")
    formData.delete("idper.apellidopat")
    formData.delete("idper.apellidomat")
    formData.delete("idper.espec.nomespecialidad")
    formData.delete("idveh.id")
    formData.delete("idveh.marca")
    formData.delete("idveh.modelo")
    formData.delete("idveh.color")
    formData.delete("idveh.combustible")

    // EDITAR
    var currentRoute = window.location.href
    var pathArray = currentRoute.split('/')
    const istEdit = !isNaN(pathArray[pathArray.length - 1])
    if (istEdit) {
        var idVisita = pathArray[pathArray.length - 1]
        var urlEndpoint = DeleteBrigada + idVisita;

        var opciones = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        };



        fetch(urlEndpoint, opciones)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    //EDITAR

    validarHorario(formData)
        .then(([esValido, msg]) => {
            if (esValido) {

                fetch(form.action, {
                    method: 'POST',
                    body: formData
                })
                    .then(response => response.text())
                    .then(data => {

                        swal({
                            title: msg,
                            text: "¿Desea crear más brigadas médicas?",
                            icon: "info",
                            buttons: {
                                aceptar: {
                                    text: "Aceptar",
                                    value: true,
                                    visible: true,
                                    className: "btn-success",
                                },
                                cancelar: {
                                    text: "Cancelar",
                                    value: false,
                                    visible: true,
                                    className: "btn-danger",
                                },
                            },
                        })
                            .then((value) => {
                                if (value) {
                                    swal.close();                                    
                                } else {
                                    window.location.href = ShowBrigadas
                                }
                            });

                        if (istEdit) {
                            window.location.href = ShowBrigadas
                        }
                    })
                    .catch(error => {
                        swal({
                            title: error,
                            text: error,
                            icon: "error",
                            buttons: false,
                        });
                        console.error('Error:', error);
                    });
            } else {
                swal({
                    title: msg,
                    icon: "info",
                    buttons: false,
                });
            }
        })
        .catch(error => {
            swal({
                title: "Ocurrió un error al validar horario",
                text: error,
                icon: "error",
                buttons: false,
            });
        });
}




window.setTimeout(function () {
    $(".alert")
        .fadeTo(1500, 0)
        .slideDown(1000, function () {
            $(this).remove();
        });
}, 2000);


async function validarHorario(data) {
    try {
        const response = await fetch(GetProgramacionBrigadas);

        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.statusText}`);
        }

        const AllBrigadasList = await response.json();

        const horariosBase = ["08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"];

        const idper = parseInt(data.get('idper'), 10);
        const idenf = data.get('idenf');
        const idtec = data.get('idtec');
        const idchf = data.get('idchf');
        const idveh = parseInt(data.get('idveh'), 10);
        const fecha = data.get('fecha');
        const obs = data.get('obs');
        const hora = data.get('hora');

        const AllIdPer = AllBrigadasList.filter(item => item.idper.id == idper);
        const AllIdEnf = AllBrigadasList.filter(item => item.idenf == idenf);
        const AllIdTec = AllBrigadasList.filter(item => item.idtec == idtec);
        const AllIdChf = AllBrigadasList.filter(item => item.idchf == idchf);
        const AllIdVeh = AllBrigadasList.filter(item => item.idveh.id == idveh);

        const findIdPer = AllIdPer.filter(item => item.fecha == fecha);
        const findIdEnf = AllIdEnf.filter(item => item.fecha == fecha);
        const findIdTec = AllIdTec.filter(item => item.fecha == fecha);
        const findIdChf = AllIdChf.filter(item => item.fecha == fecha);
        const findIdVeh = AllIdVeh.filter(item => item.fecha == fecha);

        const removeHours = (horariosBase, findResult) => {
            return horariosBase.filter(e => !findResult.some(item => item.hora === e));
        };

        const horarioIdPer = removeHours(horariosBase, findIdPer);
        const horarioIdEnf = removeHours(horariosBase, findIdEnf);
        const horarioIdTec = removeHours(horariosBase, findIdTec);
        const horarioIdChf = removeHours(horariosBase, findIdChf);
        const horarioIdVeh = removeHours(horariosBase, findIdVeh);

        //validar que no pertenezca a otra brigada
        function checkBrigadeMembers(arr, obs) {
            return arr.every(e => e.obs == obs);
        }

        let allInSameBrigade =
            checkBrigadeMembers(AllIdPer, obs) &&
            checkBrigadeMembers(AllIdEnf, obs) &&
            checkBrigadeMembers(AllIdTec, obs) &&
            checkBrigadeMembers(AllIdChf, obs) &&
            checkBrigadeMembers(AllIdVeh, obs);

        if (!allInSameBrigade) {
            return [false, "Algunos integrantes pertenecen a otra brigada"];
        }

        const brigadaDia = AllBrigadasList.find((e) => obs === e.obs && fecha === e.fecha);

        if (brigadaDia !== null && brigadaDia !== undefined) {
            if (
                brigadaDia.idper.id == idper &&
                brigadaDia.idenf == idenf &&
                brigadaDia.idtec == idtec &&
                brigadaDia.idchf == idchf &&
                brigadaDia.idveh.id == idveh &&
                brigadaDia.fecha == fecha &&
                brigadaDia.obs == obs
            ) {
                const horaEstaEnTodas = (
                    horarioIdPer.some(e => e === hora) &&
                    horarioIdEnf.some(e => e === hora) &&
                    horarioIdTec.some(e => e === hora) &&
                    horarioIdChf.some(e => e === hora) &&
                    horarioIdVeh.some(e => e === hora)
                );

                if (horaEstaEnTodas) {
                    return [true, ""];
                } else {
                    return [false, "El horario no está disponible"];
                }
            } else {
                return [false, `No se pudo crear la ${obs}, ya fue creada con otros integrantes.`];
            }
        }

        return [true, ""];

    } catch (error) {
        return [false, "Ocurrió un error inesperado"];
    }
}


