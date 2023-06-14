document.addEventListener("DOMContentLoaded",  (e) => {
graficoBarra();
ComboYear();
 ComboMonth();
});

function ComboYear(){

    var d = new Date();
    var n = d.getFullYear();
    var select = document.getElementById("year");
    for(var i = n; i >= 2015; i--) {
        var opc = document.createElement("option");
        opc.text = i;
        opc.value = i;
        select.add(opc)
    }
}

function ComboMonth(){
	var months = [
	    'Enero', 'Febrero', 'Marzo', 'Abril',
	    'Mayo', 'Junio', 'Julio', 'Agosto',
	    'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
	 ];
    var select = document.getElementById("mes");
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

async function graficoBarra() {
		
  let data = await getGrafico1();
  let labels = [];
  let datos =	[];
  let mes  = document.getElementById('mes')
  let year  = document.getElementById('year')
 
	 mes.addEventListener('change', (e) => {
		let month = mes.value;
		let year1 = year.value;
	     updateGrafico1(myChart,month,year1);
	})
	
	year.addEventListener('change', (e) => {
		let month = mes.value;
		let year1 = year.value;
	     updateGrafico1(myChart,month,year1);
	})
 
  data.forEach(e => {
	 labels.push(e.nombre+' '+e.apellido+':'+e.numero);
	 datos.push(e.numero);
	})	

	var ctx = document.getElementById("myChartMes");
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
					                fontSize:12
					                
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

	/*setInterval(function() {
	  
	}, 2000);*/
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
    console.log(grafico2)

    return grafico2;
  } catch (error) {
    console.log("Error");
    console.log(error);
  }
}

async function getGraficomes(mes,year) {
  try {
    let grafico2 = [];
    let url = "http://localhost:8083/views/visitamed/numerovisitames/"+mes+'/'+year;
    console.log(url)
    grafico2 = await getData(url);

    return grafico2;
  } catch (error) {
    console.log("Error");
    console.log(error);
  }
}



/** UPDATE AL GRAFICO */



async function updateGrafico1(chart,mes,year) {
  let data = await getGraficomes(mes,year);
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


