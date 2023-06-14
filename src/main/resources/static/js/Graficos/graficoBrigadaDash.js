document.addEventListener("DOMContentLoaded",  (e) => {
graficoBarra();
  
});


async function graficoBarra() {
		
  let data = await getGrafico1();
  let labels = [];
  let datos =	[];
 
  data.forEach(e => {
	 labels.push(e.nombre+' '+e.apellido+':'+e.numero);
	 datos.push(e.numero);
	})	

var ctx = document.getElementById("myChart1");
  let color = [];
  let border = []
  	labels.forEach(e => {
		color.push("#0061f2");
		border.push(1);
	})
  var myChart = new Chart(ctx, {
	 type:'horizontalBar',
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
					                max: 15 ,
					                
					            }
	        		}],
	        		yAxes: [{
			            ticks: {
					                beginAtZero: true,
					                max: 15 ,
					                fontSize:13
					                
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
	
	//ACTUALIZAR GRAFICO
	setInterval(function() {
	  updateGrafico1(myChart)
	}, 2000);
}

// OBTENER DATA
async function getData(url) {
  return (await fetch(url)).json();
}

/** GRAFICO 2 */

async function getGrafico1() {
  try {
    let grafico2 = [];
    let url = "http://localhost:8083/views/visitamed/numerovisita";
    grafico2 = await getData(url);

    return grafico2;
  } catch (error) {
    console.log("Error");
    console.log(error);
  }
}




/** UPDATE AL GRAFICO */



async function updateGrafico1(chart) {
  let data = await getGrafico1();
  let labels = [];
  let datos =	[];

  data.forEach(e => {
	 labels.push(e.nombre+' '+e.apellido+':'+e.numero);
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


