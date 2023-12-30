document.addEventListener("DOMContentLoaded",  (e) => {
	graficoBarra2();
    ComboYear5();
    ComboMonth5();
});

function ComboYear5(){

    var d = new Date();
    var n = d.getFullYear();
    var select = document.getElementById("yearGrupo");
    for(var i = n; i >= 2023; i--) {
        var opc = document.createElement("option");
        opc.text = i;
        opc.value = i;
        select.add(opc)
    }
}

function ComboMonth5(){
	var months = [
	    'Enero', 'Febrero', 'Marzo', 'Abril',
	    'Mayo', 'Junio', 'Julio', 'Agosto',
	    'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
	 ];
    var select = document.getElementById("mesGrupo");
    for(var i = 0; i < months.length; i++) {
        var opc = document.createElement("option");
        opc.text = months[i];
        opc.value = i+1;
        select.add(opc)
    }
	
	var d = new Date();
    var n = d.getMonth();
    select[n].selected = 'selected';
}

async function graficoBarra2() {
		
  let data = await getGrafico2();
  let labels = [];
  let datos =	[];
  let mes  = document.getElementById('mesGrupo')
  let year  = document.getElementById('yearGrupo')
 
	 mes.addEventListener('change', (e) => {
		let month = mes.value;
		let year1 = year.value;
	     updateGrafico2(myChart2,month,year1);
	})
	
	year.addEventListener('change', (e) => {
		let month = mes.value;
		let year1 = year.value;
	     updateGrafico2(myChart2,month,year1);
	})
	
 
  data.forEach(e => {
	let linea = [];
	let prueba = e.apellidoEnfermera
	
	linea.push(e.numero);
	linea.push(e.nombreMedico+' '+e.apellidoMedico);
	linea.push(e.nombreEnfermera+' '+e.apellidoEnfermera.substring(0,e.apellidoEnfermera.indexOf(' ')));
	linea.push(e.nombreTecnico+' '+e.apellidoTecnico.substring(0,e.apellidoTecnico.indexOf(' ')));
	labels.push(linea);
	datos.push(e.numero);
	})	

	var ctx2 = document.getElementById("myGrupoChartMes");
	  let color = [];
	  let border = []
	  	labels.forEach(e => {
			color.push("#0061f2");
			border.push(1);
		})
  var myChart2 = new Chart(ctx2, {
	 type:'bar',
	 data: {
			labels:labels,
			datasets: [{
				data:datos,
				label: 'Visitas',
				backgroundColor: color,
				borderColor:['black'],
			}]
		},
	  options: {
			scales:{
					xAxes: [{
			            ticks: {
					                beginAtZero: true,
					                
					                fontSize:10,
					                maxRotation: 0,
                					minRotation: 0,
                					fontSize:15
					            }
	        		}],
	        		yAxes: [{
			            ticks: {
					                beginAtZero: true,
					             
					                
					            }
	        		}]
				},

				legend:{
						position:'right'
					},
				title:{
						text: 'Grupo Brigada',
					}
			
				
			}
	})

	/*setInterval(function() {
	  
	}, 2000);*/
}

// OBTENER DATA
async function getData(url) {
  return (await fetch(url)).json();
}

/** GRAFICO 2 */

async function getGrafico2() {
  try {
    let grafico2 = [];
    let url = "http://localhost:8083/views/visitamed/gruponumerovisita";
    grafico2 = await getData(url);

    return grafico2;
  } catch (error) {
    console.log("Error");
    console.log(error);
  }
}

async function getGrupoGraficomes(mes,year) {
  try {
    let grafico2 = [];
    let url = "http://localhost:8083/views/visitamed/gruponumerovisitames/"+mes+"/"+year;
    console.log(url)
    grafico2 = await getData(url);

    return grafico2;
  } catch (error) {
    
    console.log(error);
  }
}



/** UPDATE AL GRAFICO */



async function updateGrafico2(chart,mes,year) {
  let data = await getGrupoGraficomes(mes,year);
  let labels = [];
  let datos =	[];

  data.forEach(e => {
	 let linea = [];
	linea.push(e.numero);
	linea.push(e.nombreMedico+' '+e.apellidoMedico);
	linea.push(e.nombreEnfermera+' '+e.apellidoEnfermera.substring(0,e.apellidoEnfermera.indexOf(' ')));
	linea.push(e.nombreTecnico+' '+e.apellidoTecnico.substring(0,e.apellidoTecnico.indexOf(' ')));
	labels.push(linea);
	datos.push(e.numero);
	})	
	let color = [];
  	let border = []
  	labels.forEach(e => {
		color.push("#0061f2");
		border.push(1);
	})


	  chart.data.datasets[0].data = datos
	  chart.data.datasets[0].backgroundColor = color
	  chart.data.labels = labels
	  chart.update();
}


