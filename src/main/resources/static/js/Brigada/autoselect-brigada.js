const getAllPersonal = "http://localhost:8083/views/personal/listarpersonal"
const selectDistrito = "distrito"
const selectBrigada = "brigada"
const selectZona = "zona"

//doctor
const NsaDoctor = $("#NSADoctor")
const DniDoctor = $("#DniDoctor")
const FirstNameDoctor = $("#FirstNameDoctor")
const LastNameDoctor = $("#LastNameDoctor")
const EspcDoctor = $("#EspcDoctor")
const FotoDoctor = $("#ImgDoctor")

//enfermera
const NsaEnfermera = $("#myInputEnfermera")
const DniEnfermera = $("#inputdniEnfermera")
const FirstNameEnfermera = $("#inputFirstNameEnfermera")
const LastNameEnfermera = $("#inputLastNameEnfermera")
const EspcEnfermera = $("#inputEspcEnfermera")
const FotoEnfermera = $("#imgEnfermera")

// Tecnica Enfermeria
const NsaTecnia = $("#myInputTecnica")
const DniTecnica = $("#inputdniTecnica")
const FirstTecnica = $("#inputFirstNameTecnica")
const LastNameTecnica = $("#inputLastNameTecnica")
const EspcTecnica = $("#inputEspcTecnica")
const FotoTecnica = $("#imgTecnica")

// Chofer
const NsaChofer = $("#inputChofer")
const DniChofer = $("#inputdniTecnica")
const FisrtnameChofer = $("#choferNombre")
const LastNameChofer = $("#choferApellido")
const EspcChofer = $("#choferEspecialidad")
const FotoChofer = $("#imgChofer")

//Vehiculo
const PlacaVehiculo = $("#inputPlaca")
const CodigoVehiculo = $("#inputCodigo")
const MarcaVehiculo = $("#marca")
const ModeloVehiculo = $("#modelo")
const ColorVehiculo = $("#color")
const combustible = $("#combustible")
const imgVehiculo = $("#imgVehiculo")


const BrigadaOptions = {
    "Brigada1": ["SAN ISIDRO", "MIRAFLORES", "SURQUILLO", "LOS OLIVOS", "S.J LURIGANCHO", "S.M.P", "SAN MIGUEL", "CALLAO", "MAGDALENA", "BREÑA", "JESUS MARIA", "LIMA", "LINCE", "PUEBLO LIBRE", "LA VICTORIA", "SAN BORJA"],
    "Brigada2": ["BARRANCO", "CHORRILLOS", "LA MOLINA", "S.J MIRAFLORES", "V.M.T", "SALAMANCA", "CHACARILLA", "MONTERRICO", "SURCO", "SAN BORJA"],
    "Brigada3": ["SAN ISIDRO", "MIRAFLORES", "SURQUILLO", "LOS OLIVOS", "S.J LURIGANCHO", "S.M.P", "SAN MIGUEL", "CALLAO", "MAGDALENA", "BREÑA", "JESUS MARIA", "LIMA", "LINCE", "PUEBLO LIBRE", "LA VICTORIA"],
    "Brigada4": ["SAN ISIDRO", "MIRAFLORES", "SURQUILLO", "LOS OLIVOS", "S.J LURIGANCHO", "S.M.P", "SAN MIGUEL", "CALLAO", "MAGDALENA", "BREÑA", "JESUS MARIA", "LIMA", "LINCE", "PUEBLO LIBRE", "LA VICTORIA", "SAN BORJA", "BARRANCO", "CHORRILLOS", "LA MOLINA", "S.J MIRAFLORES", "V.M.T", "SALAMANCA", "CHACARILLA", "MONTERRICO", "SURCO", "SAN BORJA"],
}

document.addEventListener("DOMContentLoaded", function (event) {
    const inp = document.getElementById(selectBrigada);
    autcompleteZonaBrigadaDistrito(inp);
});


function autcompleteZonaBrigadaDistrito(inp) {
    var brigada = document.getElementById(selectBrigada);
    var zona = document.getElementById(selectZona);
    var distrito = document.getElementById(selectDistrito);
    inp.addEventListener("change", function (e) {
        var val = this.value.toUpperCase();
        for (let clave in BrigadaOptions) {
            if (BrigadaOptions[clave].includes(val)) {
                distrito.innerHTML = '';
                var opc = BrigadaOptions[clave];
                for (let i = 0; i < opc.length; i++) {
                    let opcion = opc[i];
                    let optionElement = document.createElement('option');
                    optionElement.textContent = opcion;
                    optionElement.value = opcion;
                    distrito.appendChild(optionElement);
                    if (opcion == inp.value) {
                        inp.selectedIndex = i;
                    }
                }
                distrito.value = val;
                brigada.value = clave;
                break;
            }
        }

        switch (brigada.value) {
            case "Brigada1":
                zona.selectedIndex = 0;
                break;
            case "Brigada2":
                zona.selectedIndex = 1;
                break;
        }
        cargarBrigadaYaExistente(brigada.value);
    });
}

function cargarBrigadaYaExistente(brigada) {
    let AllPersonal;

    const cargarDatos = () => {
        return new Promise((resolve, reject) => {
            fetch(getAllPersonal)
                .then((personal) => personal.json())
                .then((personal) => {
                    AllPersonal = personal;
                    resolve();
                })
                .catch(reject);
        });
    };
    cargarDatos().then(() => {
        fetch('http://localhost:8083/views/visitamed/visitalista')
        .then(data => data.json())
        .then((data) => {
            let currentDate = new Date()
            //todas las brigadas del mismo tipo y de este año
            let allBrigadasSelect = data.filter((e) => { return e.obs === brigada })

            if (allBrigadasSelect.length > 0) {

                for (const brig of allBrigadasSelect) {
                    let fecha = new Date(brig.fecha)
                    let limitFecha = new Date(fecha);
                    limitFecha.setFullYear(limitFecha.getFullYear() + 1);

                    if (currentDate >= fecha && currentDate <= limitFecha) {
                        //doctor
                        NsaDoctor.val(brig.idper.id)
                        DniDoctor.val(brig.idper.dni)
                        FirstNameDoctor.val(brig.idper.nombre)
                        LastNameDoctor.val(`${brig.idper.apellidopat} ${brig.idper.apellidomat}`)
                        EspcDoctor.val(brig.idper.espec.nomespecialidad)
                        FotoDoctor.attr("src", `/imagen/personal/${brig.idper.foto}`)

                        //enfermera
                        const enfermera = AllPersonal.find((e) => e.id == brig.idenf)
                        if (enfermera != undefined) {
                            NsaEnfermera.val(enfermera.id)
                            DniEnfermera.val(enfermera.dni)
                            FirstNameEnfermera.val(enfermera.nombre)
                            LastNameEnfermera.val(`${enfermera.apellidopat} ${enfermera.apellidomat}`)
                            EspcEnfermera.val(enfermera.espec.nomespecialidad)
                            FotoEnfermera.attr("src", `/imagen/personal/${enfermera.foto}`)

                        }

                        //tecnica enfermera
                        const tecnica = AllPersonal.find((e) => e.id == brig.idtec)
                        if (tecnica != undefined) {
                            NsaTecnia.val(tecnica.id)
                            DniTecnica.val(tecnica.dni)
                            FirstTecnica.val(tecnica.nombre)
                            LastNameTecnica.val(`${tecnica.apellidopat} ${tecnica.apellidomat}`)
                            EspcTecnica.val(tecnica.espec.nomespecialidad)
                            FotoTecnica.attr("src", `/imagen/personal/${tecnica.foto}`)
                        }


                        //chofer
                        const chofer = AllPersonal.find((e) => e.id == brig.idchf)
                        if (chofer != undefined) {
                            NsaChofer.val(chofer.id)
                            DniChofer.val(chofer.dni)
                            FisrtnameChofer.val(chofer.nombre)
                            LastNameChofer.val(`${chofer.apellidopat} ${chofer.apellidomat}`)
                            EspcChofer.val(chofer.espec.nomespecialidad)
                            FotoChofer.attr("src", `/imagen/personal/${chofer.foto}`)
                        }


                        //Vehiculo
                        PlacaVehiculo.val(brig.idveh.placa)
                        CodigoVehiculo.val(brig.idveh.codigo)
                        MarcaVehiculo.val(brig.idveh.marca)
                        ModeloVehiculo.val(brig.idveh.modelo)
                        ColorVehiculo.val(brig.idveh.color)
                        combustible.val(brig.idveh.combustible)
                        imgVehiculo.attr("src", `/imagen/vehiculo/${brig.idveh.foto}`)

                        break;
                    }
                }
            }
        }
        )
    });
    //cargar brigada si existe

}

document.addEventListener("DOMContentLoaded", function (event) {
    autocompleteDistrito(document.getElementById(selectBrigada));
});





function autocompleteDistrito(inp) {
    var district = document.getElementById(selectDistrito);
    var zona = document.getElementById(selectBrigada);

    inp.addEventListener("change", function (e) {

        var val = this.value;
        district.innerHTML = '';

        if (val) {

            var opc = BrigadaOptions[val];

            for (let i = 0; i < opc.length; i++) {
                let opcion = opc[i];
                let optionElement = document.createElement('option');
                optionElement.textContent = opcion;
                optionElement.value = opcion;
                district.appendChild(optionElement);
            }
        }

        switch (val) {
            case "Brigada1":
                //Sur
                zona.selectedIndex = 0;
                break;
            case "Brigada2":
                //Norte
                zona.selectedIndex = 1;
                break;
        }

    });
}
