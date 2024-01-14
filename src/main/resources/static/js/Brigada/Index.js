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

    // for (var entry of formData.entries()) {
    //     console.log(entry[0], entry[1]);
    // }

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
                console.log('La visita fue eliminada exitosamente.');
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

                        mostrarMensaje(data);
                        if (istEdit) {
                            window.location.href = ShowBrigadas
                        }
                    })
                    .catch(error => {
                        mostrarMensaje("Ocurrió un error");
                        console.error('Error:', error);
                    });
            } else {
                mostrarMensaje(msg);
            }
        })
        .catch(error => {
            mostrarMensaje("Ocurrió un error al validar horario");
            console.error('Error de red:', error);
        });
}

function mostrarMensaje(data) {
    $('#mensajeExito').text(data);
    $('#modalM').show();
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

        const jsonData = await response.json();

        const horariosBase = ["08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"];

        const idper = parseInt(data.get('idper'), 10);
        const idenf = data.get('idenf');
        const idtec = data.get('idtec');
        const idchf = data.get('idchf');
        const idveh = parseInt(data.get('idveh'), 10);
        const fecha = data.get('fecha');
        const obs = data.get('obs');
        const hora = data.get('hora');

        const findIdPer = jsonData.find(item => item.idper.id === idper && item.fecha === fecha);
        const findIdEnf = jsonData.find(item => item.idenf === idenf && item.fecha === fecha);
        const findIdTec = jsonData.find(item => item.idper.id === idtec && item.fecha === fecha);
        const findIdChf = jsonData.find(item => item.idper.id === idchf && item.fecha === fecha);
        const findIdVeh = jsonData.find(item => item.idper.id === idveh && item.fecha === fecha);

        const horarioIdPer = horariosBase.filter(e => !findIdPer);
        const horarioIdEnf = horariosBase.filter(e => !findIdEnf);
        const horarioIdTec = horariosBase.filter(e => !findIdTec);
        const horarioIdChf = horariosBase.filter(e => !findIdChf);
        const horarioIdVeh = horariosBase.filter(e => !findIdVeh);

        const brigadaDia = jsonData.find((e) => obs === e.obs && fecha === e.fecha);

        if (brigadaDia !== null && brigadaDia !== undefined) {
            if (
                brigadaDia.idper.id === idper &&
                brigadaDia.idenf === idenf &&
                brigadaDia.idtec === idtec &&
                brigadaDia.idchf === idchf &&
                brigadaDia.idveh.id === idveh &&
                brigadaDia.fecha === fecha &&
                brigadaDia.hora === hora &&
                brigadaDia.obs === obs
            ) {
                const horaEstaEnTodas = (
                    horarioIdPer.some(e => e.hora === hora) &&
                    horarioIdEnf.some(e => e.hora === hora) &&
                    horarioIdTec.some(e => e.hora === hora) &&
                    horarioIdChf.some(e => e.hora === hora) &&
                    horarioIdVeh.some(e => e.hora === hora)
                );

                if (horaEstaEnTodas) {
                    return [true, ""];
                } else {
                    return [true, "El horario no está disponible"];
                }
            }
            return [false, `No se pudo crear la ${obs}, ya fue creada con otros integrantes.`];
        }

        return [true, ""];

    } catch (error) {
        console.error("Error de red:", error);
        return [false, "Ocurrió un error inesperado"];
    }
}


