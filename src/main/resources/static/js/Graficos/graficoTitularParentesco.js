document.addEventListener("DOMContentLoaded",  (e) => {
	graficoBarra();
    ComboYear();
    ComboMonth();
});

function ComboYear(){

    var d = new Date();
    var n = d.getFullYear();
    var select = document.getElementById("yearTitular");
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
    var select = document.getElementById("mesTitular");
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
		
  let data = await getGrafico3();
  let labels = [];
  let datos =	[];
  let mesTitular  = document.getElementById('mesTitular')
  let yearTitular  = document.getElementById('yearTitular')
 
	 mesTitular.addEventListener('change', (e) => {
		let month = mesTitular.value;
		let year1 = yearTitular.value;
	     updateGrafico3(myChart3,month,year1);
	})
	
	yearTitular.addEventListener('change', (e) => {
		let month = mesTitular.value;
		let year1 = yearTitular.value;
	     updateGrafico3(myChart3,month,year1);
	})
	
 
  data.forEach(e => {
			let elemento = e.titular;
		    if (!labels.includes(elemento)) {
		      labels.push(elemento);
		    }
	})	
	
	/**Titular  */
	titular1 = []
     labels.forEach(e => {
		 
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Titular' ) {
				
			
				validar = data[i].numero
			}  

		}
		titular1.push(validar)
	
	})
	/** Padre */
	padre1 = []
     labels.forEach(e => {
	
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Padre' ) {
				
			
				validar = data[i].numero
			}  

		}
		padre1.push(validar)
	
	})
	/**Madre */
	madre1 = []
     labels.forEach(e => {
		
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Madre' ) {
				
				
				validar = data[i].numero
			}  

		}
		madre1.push(validar)
	
	})
	/** Conyuge */
	conyuge1 = []
     labels.forEach(e => {
	
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Conyuge' ) {
				
			
				validar = data[i].numero
			}  

		}
		conyuge1.push(validar)
	
	})
	/** Hijo Discapacitado */
	hijo1 = []
     labels.forEach(e => {
		 
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'HijoDiscapacitado' ) {
				
			
				validar = data[i].numero
			}  

		}
		hijo1.push(validar)
	
	})
	
	/** PRUEBAS */
	
	
  /*  let dataParentesco = []
    let parentescos   = []
   
	data.forEach(e => {
		    let objeto = {};
			let parentesco = e.parentesco;
		    if (!parentescos.includes(parentesco)) {
		      parentescos.push(parentesco);
		      objeto.label = parentesco;
		      dataParentesco.push(objeto)
		      
		    }
	})	*/
	function random_rgba() {
	    var o = Math.round, r = Math.random, s = 255;
	    return 'rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + 0.3+ ')';
	}
	
	/*let finalArray = []
	dataParentesco.forEach((element,index) => {
		
		let array1 = []
		for(let i = 0; i < labels.length ; i++){
			array1.push(i)
			
		}
		dataParentesco[index].data = array1;
		dataParentesco[index].backgroundColor = random_rgba();
	})
	

	console.log(labels)
	console.log(dataParentesco)
*/
	
	var ctx3 = document.getElementById("myChartTitular");
	  let color = [];
	  let border = []
	  	labels.forEach(e => {
			color.push("#0061f2");
			border.push(1);
		})
  var myChart3 = new Chart(ctx3, {
	 type:'bar',
	 data: {
			labels:labels,
			datasets: [{
				      label: 'Titular',
				      data: titular1,
				      backgroundColor: '#3498DB',
				    },
				    {
				      label: 'Padre',
				      data: padre1,
				      backgroundColor: "#DC7633",
				    },
				    {
				      label: 'Madre',
				      data: madre1,
				      backgroundColor:'#FE2E64',
				    },
				    {
				      label: 'Conyuge',
				      data: conyuge1,
				      backgroundColor: '#BB8FCE',
				    },
				    {
				      label: 'Hijo Discapacitado',
				      data: hijo1,
				      backgroundColor: '#28B463',
				    }
				    ]
		},
	  options: {
			scales:{
					xAxes: [{
			            ticks: {
					                beginAtZero: true,
					                
					                fontSize:10,
					                maxRotation: 0,
                					minRotation: 0,
                					fontSize:20
					            }
	        		}],
	        		yAxes: [{
			            ticks: {
					                beginAtZero: true,
					             	fontSize:15
					                
					            }
	        		}]
				},

				legend:{
						position:'top',
						labels:{
							fontSize:20
						}
					},
				title:{
						text: 'Titular por Parentesco',
						display:true
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

async function getGrafico3() {
  try {
    let grafico2 = [];
    let url = "http://localhost:8083/views/brigada/titularactual";
    grafico2 = await getData(url);
    return grafico2;
  } catch (error) {
    console.log("Error");
    console.log(error);
  }
}

async function getGrupoGraficomes2(mes,year) {
  try {
    let grafico2 = [];
    let url = "http://localhost:8083/views/brigada/titularparentesco/"+mes+"/"+year;
  
    grafico2 = await getData(url);

    return grafico2;
  } catch (error) {
    
    console.log(error);
  }
}



/** UPDATE AL GRAFICO */



async function updateGrafico3(chart,mes,year) {
  let data = await getGrupoGraficomes2(mes,year);
  let labels = [];
  let datos =	[];

    data.forEach(e => {
			let elemento = e.titular;
		    if (!labels.includes(elemento)) {
		      labels.push(elemento);
		    }
	})	
	
		/**Titular  */
	titular = []
     labels.forEach(e => {
		 
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Titular' ) {
				
			
				validar = data[i].numero
			}  

		}
		titular.push(validar)
	
	})
	/** Padre */
	padre = []
     labels.forEach(e => {
	
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Padre' ) {
				
			
				validar = data[i].numero
			}  

		}
		padre.push(validar)
	
	})
	/**Madre */
	madre = []
     labels.forEach(e => {
		
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Madre' ) {
				
				
				validar = data[i].numero
			}  

		}
		madre.push(validar)
	
	})
	/** Conyuge */
	conyuge = []
     labels.forEach(e => {
	
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'Conyuge' ) {
				
			
				validar = data[i].numero
			}  

		}
		conyuge.push(validar)
	
	})
	/** Hijo Discapacitado */
	hijo = []
     labels.forEach(e => {
		 
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].titular == e && data[i].parentesco == 'HijoDiscapacitado' ) {
				
			
				validar = data[i].numero
			}  

		}
		hijo.push(validar)
	
	})
	
	let color = [];
  	let border = []
  	labels.forEach(e => {
		color.push("#0061f2");
		border.push(1);
	})


	  chart.data.datasets[0].data = titular
	  chart.data.datasets[1].data = padre
	  chart.data.datasets[2].data = madre
	  chart.data.datasets[3].data = conyuge
	  chart.data.datasets[4].data = hijo
	  /*chart.data.datasets[0].backgroundColor = color*/
	  chart.data.labels = labels
	  chart.update();
}


