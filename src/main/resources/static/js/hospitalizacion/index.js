
$(document).ready(function () {
    //cargar select e inputs

    getCamas;
    getPacientes;
    getDoctores;

    $('#hospitalizarBtn').click(function () {
        $('#modalM').show();
    });

    $('#btnCancelarHospitalizacion').click(function () {
        $('#modalM').hide();
    });

    $("#closeModal").click(function () {
        $('#modalM').hide();
    });

    $('#btnCancelarAlta').click(function () {
        $('#modalAlta').hide();
    });

    $("#closeModalAlta").click(function () {
        $('#modalAlta').hide();
    });

    $('#btnGuardarHospitalizacion').click(function () {
        var paciente = $('#pacienteHospitalizacion').val();
        var personal = $('#doctorHospitazacion').val();
        var fechaAdmision = $('#fechaAdmisionHospitalizacion').val();
        var cama = $('#camaHospitalizacion').val();

        var nuevaHosp = {
            "Id": null,
            "Paciente": parseInt(paciente),
            "Personal": parseInt(personal),
            "TipoPaciente": "Emergencia",
            "TipoAtencion": "Hospitalizacion",
            "Cama": parseInt(cama),
            "FechaAdmision": new Date(fechaAdmision),
            "FechaAlta": null,
            "Orden": null
        }

        $.ajax({
            method: "POST",
            url: "/views/hospitalizacion/HospitalizarPaciente",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(nuevaHosp),
            async: false,
            cache: false,
            success: function (data) {
                $("#msjHospitalizacion").text('Paciente Hospitalizado con exito');
                $('#btnCancelarHospitalizacion').click(function () {
                    $('#modalM').hide();
                    $("#msjHospitalizacion").text('');
                    location.reload();
                });

                $("#closeModal").click(function () {
                    $('#modalM').hide();
                    $("#msjHospitalizacion").text('');
                    location.reload();
                });
            },
            error: function (data, error) {
                $("#msjHospitalizacion").text('Ocurrio un error inesperado!');
                console.log(error)
                console.log(data)
            }
        });
    });

});

function DarAlta(input) {
    var fila = input.closest('tr');

    var id = fila.querySelector('td:nth-child(1)').textContent;

    $("#modalAlta").show()
    $("#hdnId").text(id)


}

function ejecutarAlta() {
    var id = $("#hdnId").text()
    const opc = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    };

    fetch("http://localhost:8083/views/hospitalizacion/DarAlta/" + id, opc);

    window.location.href = "http://localhost:8083/views/hospitalizacion/IndicacionesMedicas"

}

const getCamas = $.ajax({
    method: "GET",
    url: "/views/hospitalizacion/CamasLibres",
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    async: false,
    cache: false,
    success: function (data) {
        for (let index = 0; index < data.length; index++) {
            $('#camaHospitalizacion').append($("<option>", {
                value: data[index].Id,
                text: data[index].Cama + " / " + data[index].Habitacion
            }));
        }

        $('#camaHospitalizacion').prop('disabled', false);

        if (data.length == 0) {
            $('#camaHospitalizacion').append($("<option>", {
                value: '',
                text: 'No hay camas disponibles.'
            }));
            $('#camaHospitalizacion').prop('disabled', true);
        }

    },
    error: function (data, error) {
        console.log(error);
        $('#camaHospitalizacion').append($("<option>", {
            value: '',
            text: 'No hay camas disponibles.'
        }));
        $('#camaHospitalizacion').prop('disabled', true);
    },
});

const getPacientes = $.ajax({
    method: "GET",
    url: "/views/hospitalizacion/PacienteNoHospitalizados",
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    async: false,
    cache: false,
    success: function (data) {
        for (let index = 0; index < data.length; index++) {
            $('#pacienteHospitalizacion').append($("<option>", {
                value: data[index].id,
                text: [data[index].apellido_pa, data[index].apellidoma, data[index].nombre].join(' ').toUpperCase(),
            }));
        }

        $('#pacienteHospitalizacion').prop('disabled', false);

        if (data.length == 0) {
            $('#pacienteHospitalizacion').append($("<option>", {
                value: '',
                text: 'No hay pacientes hospitalizables.'
            }));
            $('#pacienteHospitalizacion').prop('disabled', true);
        }

    },
    error: function (data, error) {
        console.log(error);
        $('#pacienteHospitalizacion').append($("<option>", {
            value: '',
            text: 'No hay pacientes hospitalizables.'
        }));
        $('#pacienteHospitalizacion').prop('disabled', true);
    },
});

const getDoctores = $.ajax({
    method: "GET",
    url: "/views/hospitalizacion/DoctoresAutorizados",
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    async: false,
    cache: false,
    success: function (data) {
        for (let index = 0; index < data.length; index++) {
            $('#doctorHospitazacion').append($("<option>", {
                value: data[index].id,
                text: [data[index].apellidopat, data[index].apellidomat, data[index].nombre].join(' ').toUpperCase(),
            }));
        }

        $('#doctorHospitazacion').prop('disabled', false);

        if (data.length == 0) {
            $('#doctorHospitazacion').append($("<option>", {
                value: '',
                text: 'No hay Doctores autorizados disponibles.'
            }));
            $('#doctorHospitazacion').prop('disabled', true);
        }

    },
    error: function (data, error) {
        console.log(error);
        $('#doctorHospitazacion').append($("<option>", {
            value: '',
            text: 'No hay Doctores autorizados disponibles.'
        }));
        $('#doctorHospitazacion').prop('disabled', true);
    },
});

document.addEventListener("DOMContentLoaded", function (event) {
    autocompleteVisita(document.getElementById("buscarMedicamentos"));
});


function autocompleteVisita(inp) {
    /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    inp.addEventListener("input", function (e) {

        var a, b, i, val = this.value;

        if (val.length == 0) {
            closeAllLists();
            return;
        } else {

            fetch("/views/ordenes/medicamentos")
                .then((response) => response.json())
                .then((data) => {
                    let arr = data;


                    closeAllLists();
                    if (!val) { return false; }
                    currentFocus = -1;
                    /*create a DIV element that will contain the items (values):*/
                    a = document.createElement("DIV");
                    a.setAttribute("id", this.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");
                    /*append the DIV element as a child of the autocomplete container:*/
                    this.parentNode.appendChild(a);
                    /*for each item in the array...*/

                    for (i = 0; i < arr.length; i++) {
                        let re3 = new RegExp(val)

                        /*check if the item starts with the same letters as the text field value:*/
                        if ((arr[i].espec.nomespecialidad !== 'Chofer' && arr[i].espec.nomespecialidad !== 'Enfermera' && arr[i].espec.nomespecialidad !== 'Técnica en Enfermería') && (re3.test(arr[i].id) || arr[i].nombre.toUpperCase().includes(val.toUpperCase()))) {
                            /*create a DIV element for each matching element:*/
                            b = document.createElement("DIV");
                            /*make the matching letters bold:*/
                            b.innerHTML = "<strong>" + arr[i].nombre + ' ' + "</strong>";
                            /*insert a input field that will hold the current array item's value:*/
                            b.innerHTML += "<input type='hidden' data-nombre='" + arr[i].id + "' data-apellidos='" + arr[i].apellidopat + ' ' + arr[i].apellidomat + "' data-img='" + arr[i].foto + " '  data-dni='" + arr[i].dni + "' data-especialidad='" + arr[i].espec.nomespecialidad + "' value='" + arr[i].id + "'>";
                            /*execute a function when someone clicks on the item value (DIV element):*/
                            b.addEventListener("click", function (e) {
                                /*insert the value for the autocomplete text field:*/
                                inp.value = this.getElementsByTagName("input")[0].value;
                                closeAllLists();
                            });
                            a.appendChild(b);
                        }
                    }
                })


            /*close any already open lists of autocompleted values*/


        }

    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function (e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
            increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
            decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x) x[currentFocus].click();
            }
        }
    });
    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x) return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
        except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}