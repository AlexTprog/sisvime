const urlHorarios = "http://localhost:8083/views/personal/AvalibleTimes/";

document.getElementById("data").disabled = true;
document.getElementById("idhora").disabled = true;

document.getElementById("personal").addEventListener("change", () => {
    if (document.getElementById("personal").value != "") {
        document.getElementById("data").disabled = false;
    } else {
        document.getElementById("data").disabled = true;
        document.getElementById("data").value = "";
        cargarHorarios();
    }
});

document.getElementById("data").addEventListener("change", () => {
    if (document.getElementById("data").value != "") {
        document.getElementById("idhora").disabled = false;
        cargarHorarios();
    } else {
        document.getElementById("idhora").disabled = true;
        document.getElementById("idhora").value = "";
    }
});

async function cargarHorarios() {
    try {
        const dateAtencion = document.getElementById("data");
        const medicoAtencion = document.getElementById("personal");
        const hora = document.getElementById("idhora");

        const listHorario = await getData(
            urlHorarios + medicoAtencion.value + "/" + dateAtencion.value
        );

        $("#idhora option").remove();

        for (i = 0; i < listHorario.length; i++) {
            opt = document.createElement("OPTION");
            opt.value = listHorario[i].id;
            opt.text = listHorario[i].hora.substring(0, 5);
            hora.appendChild(opt);
        }

        hora.disabled = false;
    } catch (err) {
        console.log(err);
    }
}

function getData(url) {
    return fetch(url)
        .then((res) => res.json())
        .then((json) => json);
}
