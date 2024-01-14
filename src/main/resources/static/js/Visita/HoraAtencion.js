function compararPorHora(a, b) {
    // Convierte las horas de formato "HH:mm" a minutos para facilitar la comparación
    var horaA = parseInt(a.hora.split(":")[0]) * 60 + parseInt(a.hora.split(":")[1]);
    var horaB = parseInt(b.hora.split(":")[0]) * 60 + parseInt(b.hora.split(":")[1]);

    return horaA - horaB;
}

document.addEventListener("DOMContentLoaded", function (event) {
    autocomplete_hora_atencion(document.getElementById("tipobrigada"));

    async function autocomplete_hora_atencion(input) {
        var input_hora = document.getElementById("horaini");
        var input_fecha = document.getElementById("fecha");
        var distrito = $("#obs").val()

        async function handleChange() {
            var val = this.value;

            try {
                const response = await fetch("http://localhost:8083/views/visitamed/getallvisitas");
                const data = await response.json();

                let brigadas = data;
                input_hora.innerHTML = '';

                brigadas.sort(compararPorHora);

                for (let i = 0; i < brigadas.length; i++) {
                    if (brigadas[i].obs == val &&
                        brigadas[i].fecha == input_fecha.value &&
                        brigadas[i].dist == distrito &&
                        brigadas[i].isFree) {
                        let opcion = brigadas[i].hora;
                        let optionElement = document.createElement('option');
                        optionElement.textContent = opcion;
                        optionElement.value = opcion;
                        input_hora.appendChild(optionElement);
                        input_hora.disabled = false;
                    }
                }

                await editVisita(input_hora, input_fecha);

                if (input_hora.length == 0) {
                    let optionDefault = document.createElement('option');
                    optionDefault.textContent = "No Hay horarios disponibles";
                    optionDefault.value = "";
                    input_hora.appendChild(optionDefault);
                    input_hora.disabled = true;
                }
            } catch (error) {
                console.error("Error en la llamada a la API:", error);
            }
        }

        input.addEventListener("change", handleChange);
        handleChange.call(input);  // Llama a la función onchange una vez
    }
});

async function editVisita(input_hora, input_fecha) {
    var currentRoute = window.location.href;
    var pathArray = currentRoute.split('/');
    const isEditVisita = !isNaN(pathArray[pathArray.length - 1]);

    if (isEditVisita) {
        const idVisita = pathArray[pathArray.length - 1];

        try {
            const response = await fetch("http://localhost:8083/views/brigada/getVisita/" + idVisita);
            const data = await response.json();

            if (data.horaini != undefined && data.fecha == input_fecha.value) {
                let opcion = data.horaini;
                let optionElement = document.createElement('option');
                optionElement.textContent = opcion;
                optionElement.value = opcion;
                input_hora.appendChild(optionElement);
                input_hora.disabled = false;
            }
        } catch (error) {
            console.error("Error en la llamada a la API para obtener la visita:", error);
        }
    }
}
