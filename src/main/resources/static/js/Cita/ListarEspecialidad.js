
const ignore = ["Chofer", "Técnica en Enfermería", "Enfermera"];

document.addEventListener("DOMContentLoaded", function (event) {
    cargarEspecialidad();
});

const urlEsp = "http://localhost:8083/listar-especialidad";
const urlPer = "http://localhost:8083/views/personal/listarpersonal";
let personal = {};

async function cargarEspecialidad() {
    try {
        const data = await getData(urlEsp);
        const select = document.getElementById("especialidad");

        for (i = 0; i < data.length; i++) {
            if (!ignore.includes(data[i].nomespecialidad)) {
                option = document.createElement("OPTION");
                option.id = data[i].id;
                option.value = data[i].nomespecialidad;
                option.text = data[i].nomespecialidad;
                select.appendChild(option);
            }
        }
    } catch (err) {
        console.log(err);
    }

    personas();
}

function getData(url) {
    return fetch(url)
        .then((res) => res.json())
        .then((json) => json);
}

const personas = async function getPersonal() {
    personal = await getData(urlPer);
    filtrarDoctor(personal);
};

function filtrarDoctor(datos) {
    let especialidades = document.querySelectorAll("#especialidad option");
    for (i = 0; i < especialidades.length; i++) {
        let p = especialidades[i];
        especialidades[i].addEventListener("click", (e) => {
            const selectPersonal = document.getElementById("personal");
            selectPersonal.innerHTML = "";
            for (i = 0; i < datos.length; i++) {
                if (e.target.id == datos[i].espec.id) {
                    option = document.createElement("OPTION");
                    option.id = datos[i].id;
                    option.value = datos[i].nombre;
                    option.text = datos[i].nombre + " " + datos[i].apellidopat;
                    selectPersonal.appendChild(option);
                }
            }
        });
    }
}