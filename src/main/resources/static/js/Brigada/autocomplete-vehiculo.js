document.addEventListener("DOMContentLoaded", function (event) {
    autocompleteVehiculo(document.getElementById("inputPlaca"));
});


function autocompleteVehiculo(inp) {
    var currentFocus;

    inp.addEventListener("input", function (e) {

        var a, b, i, val = this.value;

        if (val.length == 0) {
            return;
        } else {

            fetch("http://localhost:8083/views/vehiculo/listarvehiculos")
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
                        let re3 = new RegExp(val.toUpperCase())

                        if (re3.test(arr[i].placa.toUpperCase())) {
                            b = document.createElement("DIV");
                            b.innerHTML = "<strong>" + arr[i].placa + "</strong>";

                            b.innerHTML += "<input type='hidden' data-marca='" + arr[i].marca + "' data-modelo='" + arr[i].modelo + "' data-foto='" + arr[i].foto + "'data-color='" + arr[i].color + " ' data-combustible='" + arr[i].combustible + "  ' data-placa='" + arr[i].placa + " '  data-status='" + arr[i].estatus + "' value='" + arr[i].id + "'>";
                            b.addEventListener("click", function (e) {

                                inp.value = this.getElementsByTagName("input")[0].dataset.placa;
                                const codigo = document.getElementById("inputCodigo");
                                const marca = document.getElementById("marca");
                                const modelo = document.getElementById("modelo");
                                const disponible = document.getElementById("disponible");
                                // const placa = document.getElementById("placa");
                                const color = document.getElementById("color");
                                const combustible = document.getElementById("combustible");
                                const imgVehiculo = document.getElementById("imgVehiculo");

                                marca.value = this.getElementsByTagName("input")[0].dataset.marca;
                                modelo.value = this.getElementsByTagName("input")[0].dataset.modelo;
                                // placa.value = this.getElementsByTagName("input")[0].dataset.placa;
                                codigo.value = this.getElementsByTagName("input")[0].value;
                                combustible.value = this.getElementsByTagName("input")[0].dataset.combustible;
                                color.value = this.getElementsByTagName("input")[0].dataset.color;

                                imgVehiculo.src = '/imagen/vehiculo/' + this.getElementsByTagName("input")[0].dataset.foto;

                                if (this.getElementsByTagName("input")[0].dataset.status == 0) {
                                    disponible.value = 'No Disponible';
                                } else {
                                    disponible.value = 'Disponible';
                                }

                                /*close the list of autocompleted values,
                                (or any other open lists of autocompleted values:*/
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




