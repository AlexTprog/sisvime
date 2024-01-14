var currentRoute = window.location.href
var pathArray = currentRoute.split('/')
const isEditVisita = !isNaN(pathArray[pathArray.length - 1])
const idVisita = pathArray[pathArray.length - 1]

if (!isEditVisita) {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();

    if (dd < 10) {
        dd = '0' + dd;
    }

    if (mm < 10) {
        mm = '0' + mm;
    }

    var fechaMinima = yyyy + '-' + mm + '-' + dd;

    document.getElementById("fecha").min = fechaMinima;
    document.getElementById("fecha").value = fechaMinima;

}

window.setTimeout(function () {
    $(".alert").fadeTo(1500, 0).slideDown(1000, function () {
        $(this).remove();
    });
}, 2000);

document.addEventListener("submit", (e) => {
    let nsa = document.getElementById("myInput");
    let placa = document.getElementById("inputPlaca");
    let horaini = document.getElementById("horaini");
    let zonabrigada = document.getElementById("zonabrigada");
    let fecha = document.getElementById("fecha");
    let obs = document.getElementById("obs");


    // EDITAR

    if (isEditVisita) {
        var idVisita = pathArray[pathArray.length - 1]
        var urlEndpoint = "http://localhost:8083/views/brigada/deletebrigada/" + idVisita;

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
                // Manejar errores aquí
            });
    }

    //EDITAR

    validarVisitaUnica(nsa, fecha).then((valid) => {
        if (valid) {
            //evitar que recarge y dirigir hacia la misma pantalla
            e.preventDefault();
            alert("El paciente solo puede tener una visita por dia");
        }
    })

})

async function validarVisitaUnica(nsa, fecha) {
    var flag = false

    var visitas = await fetch("http://localhost:8083/views/citas/visitalista")

    if (isEditVisita) {
        visitas.filter((e) => { return e.id != idVisita })
    }

    for (let i = 0; i < visitas.length; i++) {
        const e = visitas[i];

        if (e.pac.id === parseInt(nsa, 10) && e.fecha === fecha) {
            return flag = true
        }
    }

    return flag
}


document.addEventListener("DOMContentLoaded", function (event) {
    auto_fecha(document.getElementById("fecha"));
});
function auto_fecha(input) {
    input.addEventListener("change", function (e) {
        var eventoChange = new Event("change");
        document.getElementById("tipobrigada").dispatchEvent(eventoChange);
    })
}

