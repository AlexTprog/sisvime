
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

