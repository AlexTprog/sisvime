document.addEventListener("DOMContentLoaded",  (e) => {
graficoBarraGrupo();
  
});


async function graficoBarraGrupo() {
		
  let data2 = await getGrafico2();
  let labels2 = [];
  let datos2 =	[];
  
  data2.forEach(e => { 
		let linea = [];
		linea.push(e.numero);
		linea.push(e.nombreMedico+' '+e.apellidoMedico);
		linea.push(e.nombreEnfermera+' '+e.apellidoEnfermera.substring(0,e.apellidoEnfermera.indexOf(' ')));
	linea.push(e.nombreTecnico+' '+e.apellidoTecnico.substring(0,e.apellidoTecnico.indexOf(' ')));
		labels2.push(linea);
		datos2.push(e.numero);
	})	

var ctx2 = document.getElementById("myChart2");
  let color2 = [];
  let border2 = []
  	labels2.forEach(e => {
		color2.push("#0061f2");
		border2.push(1);
	})
  var myChart2 = new Chart(ctx2, {
	 type:'bar',
	 data: {
			labels:labels2,
			datasets: [{
				data:datos2,
				label: 'Visitas',
				backgroundColor: color2,
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
                					fontSize:12
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
						text: 'Personal por Mes',
					}
			
				
			}
	})
	
	console.log(myChart2)
	
	setInterval(function() {
	  updateGrafico2(myChart2)
	}, 2000);
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




/** UPDATE AL GRAFICO */



async function updateGrafico2(chart) {
  let data2 = await getGrafico2();
  let labels2 = [];
  let datos2 =	[];
 
 data2.forEach(e => { 
	let linea = [];
	linea.push(e.numero);
	linea.push(e.nombreMedico+' '+e.apellidoMedico);
	linea.push(e.nombreEnfermera+' '+e.apellidoEnfermera.substring(0,e.apellidoEnfermera.indexOf(' ')));
	linea.push(e.nombreTecnico+' '+e.apellidoTecnico.substring(0,e.apellidoTecnico.indexOf(' ')));
	labels2.push(linea);
	datos2.push(e.numero);
	})		
	let color2 = [];
  	let border2 = []
  	labels2.forEach(e => {
		color2.push("#0061f2");
		border2.push(1);
	})

	  chart.data.datasets[0].data = datos2
	  chart.data.datasets[0].backgroundColor = color2
	   chart.data.labels = labels2
	  chart.update();
}


