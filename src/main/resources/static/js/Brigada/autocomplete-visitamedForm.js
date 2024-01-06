const Input = "FirstName"
const getAllPersonal = "http://localhost:8083/views/personal/listarpersonal"
const especialidad = {
    Doctor: ['Geriatra',
        'Medico General',
        'Medico Especialista',
        'Odontologo',
        'Psicologo',
        'Nutricionista',
        'Cirujano'
    ],
    Enfermera: ['Enfermera'],
    TecnicaEnf: ['Tenica Enfermeria'],
    Chofer: ['Chofer'],
    Ambulancia: ['Vehiculo'],
}


document.addEventListener("DOMContentLoaded", function (event) {
    autocompletePersonal(especialidad.Doctor);
});


function autocompletePersonal(especialidad) {

    if (especialidad.length > 0) {
        var inp = document.getElementById(Input + "Doctor")
    } else {
        var inp = document.getElementById(Input + especialidad)
    }


    var currentFocus;

    inp.addEventListener("input", function (e) {

        var a, b, i, val = this.value;

        if (val.length == 0) {
            closeAllLists();
            return;
        } else {

            fetch(getAllPersonal)
                .then((response) => response.json())
                .then((data) => {
                    let arr = data;


                    closeAllLists();
                    if (!val) { return false; }
                    currentFocus = -1;

                    a = document.createElement("DIV");
                    a.setAttribute("id", this.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");

                    this.parentNode.appendChild(a);


                    for (i = 0; i < arr.length; i++) {
                        let re3 = new RegExp(val)


                        if (especialidad.includes(arr[i].espec.nomespecialidad) && (re3.test(arr[i].nombre) || arr[i].nombre.toUpperCase().includes(val.toUpperCase()))) {

                            b = document.createElement("DIV");

                            b.innerHTML = "<strong>" + arr[i].nombre + ' ' + arr[i].apellidopat + ' ' + "</strong>";

                            b.innerHTML += "<input type='hidden' data-nombre='" + arr[i].nombre + "' data-apellidos='" + arr[i].apellidopat + ' ' + arr[i].apellidomat + "' data-img='" + arr[i].foto + " '  data-dni='" + arr[i].dni + "' data-especialidad='" + arr[i].espec.nomespecialidad + "' value='" + arr[i].id + "'>";

                            b.addEventListener("click", function (e) {

                                inp.value = this.getElementsByTagName("input")[0].value;
                                // const firstname = document.getElementById("FirstName" + especialidad);
                                const nsa = document.getElementById("NSA" + especialidad);
                                const lastname = document.getElementById("LastName" + especialidad);
                                const dni = document.getElementById("Dni" + especialidad);
                                const espec = document.getElementById("Espc" + especialidad);
                                const imgDoctor = document.getElementById("Img" + especialidad);

                                // firstname.value = this.getElementsByTagName("input")[0].dataset.nombre;
                                nsa.value = this.getElementsByTagName("input")[0].dataset.nombre;
                                lastname.value = this.getElementsByTagName("input")[0].dataset.apellidos;
                                dni.value = this.getElementsByTagName("input")[0].dataset.dni;
                                espec.value = this.getElementsByTagName("input")[0].dataset.especialidad;

                                imgDoctor.src = "/imagen/personal/" + this.getElementsByTagName("input")[0].dataset.img;

                                closeAllLists();
                            });
                            a.appendChild(b);
                        }
                    }
                })

        }

    });


    inp.addEventListener("keydown", function (e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) { //down

            currentFocus++;

            addActive(x);
        } else if (e.keyCode == 38) { //up

            currentFocus--;

            addActive(x);
        } else if (e.keyCode == 13) {

            e.preventDefault();
            if (currentFocus > -1) {

                if (x) x[currentFocus].click();
            }
        }
    });

    function addActive(x) {

        if (!x) return false;

        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);

        x[currentFocus].classList.add("autocomplete-active");
    }

    function removeActive(x) {
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }

    function closeAllLists(elmnt) {

        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }

    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}

