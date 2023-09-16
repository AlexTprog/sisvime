//Medicamentos
const inputMedicamento = $("#buscarMedicamentos");
const inputCantidad = $("#inptCantidad");
const inputTipoCantidad = $("#tipoCantidad");
const inputFrecuencia = $("#inputFrecuencia");
const inputViaAdmision = $("#inputViaAdmision");

const tablaMed = $("#tableMedicamentos tbody");
const btnAddMedicamento = $("#btnAddMed");

//Servicios
const inputServicio = $("#inputServicios");
const inputPrioridad = $("#inputPrioridad");
const inputSolicitadoA = $("#inputSolicitadoA");

const tableServicios = $("#tableServicios tbody");
const btnAddServicio = $("#btnAddServicio");

//Dietas
const inputDieta = $("#inputDieta");
const inputComentarioDieta = $("#inputComentarioDieta");
const inputFechaInicio = $("#inputFechaInicio");

const tableDieta = $("#tableDieta tbody");
const btnAddDieta = $("#btnAddDieta");

//INDICACIONES
const inputIndicaciones = $("#inputIndicaciones");

//ORDEN
const bntGuardarOrden = $("#bntGuardarOrden");
const btnResumen = $("#btnResumen");
const idHospitalizacion = $("#hdnIdHosp");

//MODAL
const modal = $('#modalM');
const modalMsj = $("#mensajeExito");

$(document).ready(function () {

    cargarOrden();

    inputCantidad.prop('disabled', true);
    inputTipoCantidad.prop('disabled', true);
    inputFrecuencia.prop('disabled', true);
    inputViaAdmision.prop('disabled', true);

    //MEDICAMENTOS
    $("#tableMedicamentos").on("click", ".eliminar", eliminarFila);
    $("#tableMedicamentos").on("click", ".editar", editarMedicamentos);
    $("#tableMedicamentos").on("click", ".guardar", guardarEditarMed);

    btnAddMedicamento.click(addAgregarMedicamentos);

    //SERVICIOS
    $("#tableServicios").on("click", ".eliminar", eliminarFila);
    $("#tableServicios").on("click", ".editar", editarServicio);
    $("#tableServicios").on("click", ".guardar", guardarEditarServicio);

    btnAddServicio.click(agregarServicio);

    //DIETA
    $("#tableDieta").on("click", ".eliminar", eliminarFila);
    $("#tableDieta").on("click", ".editar", editarDieta);
    $("#tableDieta").on("click", ".guardar", guardarEditarDieta);

    btnAddDieta.click(agregarDieta);
    //INDICACIONES
    bntGuardarOrden.click(guardarOrden);
    btnResumen.click(resumenOrden);
});

function cargarOrden() {
    var orden;
    $.ajax({
        method: "GET",
        url: "/views/ordenes/lastOrden/" + parseInt(idHospitalizacion.text()),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        cache: false,
        success: function (data) {
            orden = data;


            if (orden.medicamentos.length != undefined) {
                orden.medicamentos.forEach(element => {
                    var fecha = new Date(element.FechaInicio) || new Date();
                    var fechaFormateada = fecha ? fecha.toISOString().slice(0, 16).replace('T', ' ') : '';

                    var newRow = $("<tr>")
                        .append($("<td>").text(element.Medicamento))
                        .append($("<td>").text(element.Cantidad))
                        .append($("<td>").text(element.TipoCantidad))
                        .append($("<td>").text(element.Frecuencia))
                        .append($("<td>").text(element.ViaAdmision))
                        .append($("<td>").text(element.Administrado))
                        .append($("<td>").text(fechaFormateada))
                        .append($("<td>")
                            .append($("<button>").addClass("btn btn-primary editar").html('<i class="fas fa-edit"></i>'))
                            .append($("<button>").addClass("btn btn-danger eliminar").html('<i class="fas fa-trash-alt"></i>'))
                        );

                    // Agregar la fila al tbody
                    tablaMed.append(newRow);
                });
            }

            if (orden.medicamentos.length != undefined) {
                orden.dietas.forEach(element => {
                    var fecha = new Date(element.FechaInicio) || new Date();
                    var fechaFormateada = fecha ? fecha.toISOString().slice(0, 16).replace('T', ' ') : '';

                    var newRow = $("<tr>")
                        .append($("<td>").text(element.Dieta))
                        .append($("<td>").text(element.Comentario))
                        .append($("<td>").text(fechaFormateada))
                        .append($("<td>")
                            .append($("<button>").addClass("btn btn-primary editar").html('<i class="fas fa-edit"></i>'))
                            .append($("<button>").addClass("btn btn-danger eliminar").html('<i class="fas fa-trash-alt"></i>'))
                        );

                    // Agregar la fila al tbody
                    tableDieta.append(newRow);
                });
            }

            if (orden.medicamentos.length != undefined) {
                orden.servicios.forEach(element => {
                    var fecha = new Date(element.FechaInicio) || new Date();
                    var fechaFormateada = fecha ? fecha.toISOString().slice(0, 16).replace('T', ' ') : '';

                    var newRow = $("<tr>")
                        .append($("<td>").text(element.Servicio))
                        .append($("<td>").text(element.Prioridad))
                        .append($("<td>").text(element.SolicitadoA))
                        .append($("<td>").text(fechaFormateada))
                        .append($("<td>")
                            .append($("<button>").addClass("btn btn-primary editar").html('<i class="fas fa-edit"></i>'))
                            .append($("<button>").addClass("btn btn-danger eliminar").html('<i class="fas fa-trash-alt"></i>'))
                        );

                    // Agregar la fila al tbody
                    tableServicios.append(newRow);
                });

            }

            inputIndicaciones.val(orden.indicaciones ?? "")

        },
        error: function (data, error) {
            orden = {};
            console.log(data);
        }
    });



}

function guardarOrden() {
    var listaMedicamentos = [];
    var listaServicios = [];
    var listaDietas = [];
    var indicaciones = "";
    var orden;

    $("#tableMedicamentos tbody tr").each(function () {
        var fila = $(this);
        var objeto = {
            Medicamento: fila.find("td:eq(0)").text(),
            Cantidad: fila.find("td:eq(1)").text(),
            TipoCantidad: fila.find("td:eq(2)").text(),
            Frecuencia: fila.find("td:eq(3)").text(),
            ViaAdmision: fila.find("td:eq(4)").text(),
            Administrado: fila.find("td:eq(5)").text(),
            FechaInicio: new Date(fila.find("td:eq(6)").text())
        };

        listaMedicamentos.push(objeto);
    });

    $("#tableServicios tbody tr").each(function () {
        var fila = $(this);
        var objeto = {
            Servicio: fila.find("td:eq(0)").text(),
            Prioridad: fila.find("td:eq(1)").text(),
            SolicitadoA: fila.find("td:eq(2)").text(),
            FechaInicio: new Date(fila.find("td:eq(3)").text())
        };

        listaServicios.push(objeto);
    });

    $("#tableDieta tbody tr").each(function () {
        var fila = $(this);
        var objeto = {
            Dieta: fila.find("td:eq(0)").text(),
            Comentario: fila.find("td:eq(1)").text(),
            FechaInicio: new Date(fila.find("td:eq(2)").text()),
        };

        listaDietas.push(objeto);
    });

    indicaciones = inputIndicaciones.val();
    orden = {
        indicaciones: indicaciones,
        fechaInicio: new Date(),
        dietas: listaDietas,
        medicamentos: listaMedicamentos,
        servicios: listaServicios,
        idHospitalizacion: parseInt(idHospitalizacion.text()),
    };

    //GUARDAR ORDEN
    $.ajax({
        method: "POST",
        url: "/views/ordenes/saveOrden",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(orden),
        async: false,
        cache: false,
        success: function (data) {
            if (data != null)
                modalMsj.text("Orden Guardada Con Exito");
            modal.show();
        },
        error: function (data, error) {
            modalMsj.text("Ocurrio un error al guardar la orden");
            modal.show();
        }
    });

}

function resumenOrden() {
    var modal = $("#modalResumen");

    function extractDataFromTable(tableSelector, keys) {
        var dataList = [];
        $(tableSelector + " tbody tr").each(function () {
            var row = $(this);
            var dataObj = {};
            keys.forEach(function (key, index) {
                dataObj[key] = row.find("td:eq(" + index + ")").text();
            });
            dataList.push(dataObj);
        });
        return dataList;
    }

    var medicamentoKeys = ["Medicamento", "Cantidad", "TipoCantidad", "Frecuencia", "ViaAdmision", "Administrado", "Fecha Inicio"];
    var servicioKeys = ["Servicio", "Prioridad", "SolicitadoA", "Fecha Inicio"];
    var dietaKeys = ["Dieta", "Comentario", "FechaInicio"];

    var listaMedicamentos = extractDataFromTable("#tableMedicamentos", medicamentoKeys);
    var listaServicios = extractDataFromTable("#tableServicios", servicioKeys);
    var listaDietas = extractDataFromTable("#tableDieta", dietaKeys);

    function populateTable(tableSelector, dataList) {
        var tbody = $(tableSelector + " tbody");
        tbody.empty();
        dataList.forEach(function (element) {
            var newRow = $("<tr>");
            Object.values(element).forEach(function (value) {
                newRow.append($("<td>").text(value));
            });
            tbody.append(newRow);
        });
    }

    populateTable("#resumenMed", listaMedicamentos);
    populateTable("#resumenServicio", listaServicios);
    populateTable("#resumenDieta", listaDietas);

    $("#resumenIndi").text(inputIndicaciones.val());

    modal.show();
}

//MEDICAMENTOS
function addAgregarMedicamentos() {
    var fechaFormateada = new Date().toISOString().slice(0, 16).replace('T', ' ');
    var newRow = $("<tr>")
        .append($("<td>").text(inputMedicamento.val()))
        .append($("<td>").text(inputCantidad.val()))
        .append($("<td>").text(inputTipoCantidad.val()))
        .append($("<td>").text(inputFrecuencia.val()))
        .append($("<td>").text(inputViaAdmision.val()))
        .append($("<td>").text("NO"))
        .append($("<td>").text(fechaFormateada))
        .append($("<td>")
            .append($("<button>").addClass("btn btn-primary editar").html('<i class="fas fa-edit"></i>'))
            .append($("<button>").addClass("btn btn-danger eliminar").html('<i class="fas fa-trash-alt"></i>'))
        );

    // Agregar la fila al tbody
    tablaMed.append(newRow);
}

function editarMedicamentos() {
    var row = $(this).closest("tr");
    var cells = row.find("td");

    // Habilitar la edición de las celdas (excepto la primera celda de "Nombre")
    cells.each(function (index) {
        var text = $(this).text();
        var randomId = "id_" + Math.floor(Math.random() * 1000);
        switch (index) {
            case 0:
                break;
            case 1:
                $(this).html("<input type='number' class='form-control' value='" + text + "'>");
                break;
            case 2:
                var selectHtml = "<select  id='" + randomId + "' class='form-control'>" +
                    "<option value='TAB'>TAB</option>" +
                    "<option value='MG'>MG</option>" +
                    "<option value='ML'>ML</option>" +
                    "</select>";
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 3:
                var selectHtml = "<select  id='" + randomId + "' class='form-control'>" +
                    "<option value='Cada 24 horas'>Cada 24 horas</option>" +
                    "<option value='Cada 12 horas'>Cada 12 horas</option>" +
                    "<option value='Cada 8 horas'>Cada 8 horas</option>" +
                    "<option value='Cada 6 horas'>Cada 6 horas</option>" +
                    "<option value='Cada 4 horas'>Cada 4 horas</option>" +
                    "<option value='Cada 2 horas'>Cada 2 horas</option>" +
                    "<option value='Segun necesidad'>Segun necesidad</option>" +
                    "</select>";
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 4:
                var selectHtml = "<select  id='" + randomId + "' class='form-control'>" +
                    "<option value='Via Oral'>Via Oral</option>" +
                    "<option value='Via Topica'>Via Topica</option>" +
                    "<option value='Via intramuscular'>Via intramuscular</option>" +
                    "<option value='Via Nasal'>Via Nasal</option>" +
                    "</select>";
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 5:
                var selectHtml = "<select  id='" + randomId + "' class='form-control'>" +
                    "<option value='NO'>NO</option>" +
                    "<option value='SI'>SI</option>" +
                    "</select>";
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 6:
                var selectHtml = "<input type='datetime-local' id='" + randomId + "' class='form-control' value='" + text + "'>";
                $(this).html(selectHtml);
                break;
            default:
                break;
        }
    });

    // Cambiar el texto del botón "Editar" a "Guardar"
    $(this).html('<i class="fas fa-save"></i>').removeClass("btn-primary editar").addClass("btn-success guardar");
}

function guardarEditarMed() {
    var row = $(this).closest("tr");
    var cells = row.find("td");

    // Deshabilitar la edición de las celdas y obtener los nuevos valores
    cells.each(function (index) {
        if (index >= 1 && index <= 6) {
            var input = $(this).find("input, select");
            var text = input.val();
            $(this).html(text);
        }
    });

    // Cambiar el texto del botón de nuevo a "Editar"
    $(this).html('<i class="fas fa-edit"></i>').removeClass("btn-success guardar").addClass("btn-primary editar");
}

//SERVICIOS

function agregarServicio() {
    var fechaFormateada = new Date().toISOString().slice(0, 16).replace('T', ' ');
    var newRow = $("<tr>")
        .append($("<td>").text(inputServicio.val()))
        .append($("<td>").text(inputPrioridad.val()))
        .append($("<td>").text(inputSolicitadoA.val()))
        .append($("<td>").text(fechaFormateada))
        .append($("<td>")
            .append($("<button>").addClass("btn btn-primary editar").html('<i class="fas fa-edit"></i>'))
            .append($("<button>").addClass("btn btn-danger eliminar").html('<i class="fas fa-trash-alt"></i>'))
        );
    tableServicios.append(newRow);
}

function editarServicio() {
    var row = $(this).closest("tr");
    var cells = row.find("td");

    // Habilitar la edición de las celdas (excepto la primera celda de "Nombre")
    cells.each(function (index) {
        var text = $(this).text();
        var randomId = "id_" + Math.floor(Math.random() * 1000);
        switch (index) {
            case 0:
                var selectHtml = "<select id='" + randomId + "' class='form-control'>" +
                    "<option value='Analisis 1'>Analisis 1</option>" +
                    "<option value='Analisis 2'>Analisis 2</option>" +
                    "<option value='Analisis 3'>Analisis 3</option>" +
                    "</select > "
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 1:
                var selectHtml = "<select id='" + randomId + "' class='form-control'>" +
                    "<option value='Programado'>Programado</option>" +
                    "<option value='Critico'>Critico</option>" +
                    "<option value='Prioritario'>Prioritario</option> " +
                    "</select>";
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 2:
                var selectHtml = "<select  id='" + randomId + "' class='form-control'>" +
                    "<option value='Laboratorio Neumologia'>Laboratorio Neumologia</option>" +
                    "<option value='Laboratorio Cardiologia'>Laboratorio Cardiologia</option>" +
                    "<option value='Laboratorio Reumatologia'>Laboratorio Reumatologia</option>" +
                    "</select>";
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 3:
                var selectHtml = "<input type='datetime-local' id='" + randomId + "' class='form-control' value='" + text + "'>";
                $(this).html(selectHtml);
                break;
            default:
                break;
        }
    });

    // Cambiar el texto del botón "Editar" a "Guardar"
    $(this).html('<i class="fas fa-save"></i>').removeClass("btn-primary editar").addClass("btn-success guardar");
}

function guardarEditarServicio() {
    var row = $(this).closest("tr");
    var cells = row.find("td");
    // Deshabilitar la edición de las celdas y obtener los nuevos valores
    cells.each(function (index) {
        if (index >= 0 && index <= 3) {
            var input = $(this).find("input, select");
            var text = input.val();
            $(this).html(text);
        }
    });
    // Cambiar el texto del botón de nuevo a "Editar"
    $(this).html('<i class="fas fa-edit"></i>').removeClass("btn-success guardar").addClass("btn-primary editar");
}

//DIETAS

function agregarDieta() {
    var fechaFormateada = new Date(inputFechaInicio.val()).toISOString().slice(0, 16).replace('T', ' ');
    var newRow = $("<tr>")
        .append($("<td>").text(inputDieta.val()))
        .append($("<td>").text(inputComentarioDieta.val()))
        .append($("<td>").text(fechaFormateada))
        .append($("<td>")
            .append($("<button>").addClass("btn btn-primary editar").html('<i class="fas fa-edit"></i>'))
            .append($("<button>").addClass("btn btn-danger eliminar").html('<i class="fas fa-trash-alt"></i>'))
        );

    // Agregar la fila al tbody
    tableDieta.append(newRow);
}

function editarDieta() {
    var row = $(this).closest("tr");
    var cells = row.find("td");

    // Habilitar la edición de las celdas (excepto la primera celda de "Nombre")
    cells.each(function (index) {
        var text = $(this).text();
        var randomId = "id_" + Math.floor(Math.random() * 1000);
        switch (index) {
            case 0:
                var selectHtml = "<select  id='" + randomId + "' class='form-control'>" +
                    "<option value='Ayuno'>Ayuno</option>" +
                    "<option value='Liquidos'>Liquidos</option>" +
                    "<option value='Normal'>Normal</option>" +
                    "<option value='Blanda'>Blanda</option>" +
                    "</select>"
                $(this).html(selectHtml);
                $("#" + randomId).val(text);
                break;
            case 1:
                var selectHtml = "<input type='text' id='" + randomId + "' class='form-control'" +
                    "placeholder='Comentario' aria-label='Comentario' value='" + text + "'>";
                $(this).html(selectHtml);
                break;
            case 2:
                var selectHtml = "<input type='datetime-local' id='" + randomId + "' class='form-control' value='" + text + "'>";
                $(this).html(selectHtml);
                break;
            default:
                break;
        }
    });

    // Cambiar el texto del botón "Editar" a "Guardar"
    $(this).html('<i class="fas fa-save"></i>').removeClass("btn-primary editar").addClass("btn-success guardar");
}

function guardarEditarDieta() {
    var row = $(this).closest("tr");
    var cells = row.find("td");
    // Deshabilitar la edición de las celdas y obtener los nuevos valores
    cells.each(function (index) {
        if (index >= 0 && index <= 2) {
            var input = $(this).find("input, select");
            var text = input.val();
            $(this).html(text);
        }
    });
    // Cambiar el texto del botón de nuevo a "Editar"
    $(this).html('<i class="fas fa-edit"></i>').removeClass("btn-success guardar").addClass("btn-primary editar");
}

//UTILIDADES
function eliminarFila() {
    $(this).closest("tr").remove();
}

function cleanInputs() {
    inputCantidad.prop('disabled', false);
    inputCantidad.val("");

    inputTipoCantidad.prop('disabled', false);
    inputTipoCantidad.val("");

    inputFrecuencia.prop('disabled', false);
    inputFrecuencia.val("");

    inputViaAdmision.prop('disabled', false);
    inputViaAdmision.val("");
}

function enabledAll(data) {
    for (let id of data) {
        id.prop('disabled', false);
    }
}

//BUSCADOR MEDICAMENTO

document.addEventListener("DOMContentLoaded", function (event) {
    autocompleteMed(document.getElementById("buscarMedicamentos"));
});

function autocompleteMed(inp) {
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
                        if ((re3.test(arr[i].id) || arr[i].nombre.toUpperCase().includes(val.toUpperCase()))) {
                            /*create a DIV element for each matching element:*/
                            b = document.createElement("DIV");
                            /*make the matching letters bold:*/
                            b.innerHTML = "<strong>" + arr[i].nombre + ' ' + "</strong>";
                            b.innerHTML += "<input type='hidden' data-id='" + arr[i].id + "' value='" + arr[i].nombre + "'>";
                            /*execute a function when someone clicks on the item value (DIV element):*/
                            b.addEventListener("click", function (e) {
                                /*insert the value for the autocomplete text field:*/
                                inp.value = this.getElementsByTagName("input")[0].value;
                                closeAllLists();
                                enabledAll([inputCantidad, inputFrecuencia, inputTipoCantidad, inputViaAdmision]);
                            });
                            a.appendChild(b);
                        }
                    }
                })

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
