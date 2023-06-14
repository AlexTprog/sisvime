document.addEventListener("DOMContentLoaded",  (e) => {
	graficoBarra2();
    ComboYear5();
    ComboMonth5();
    console.log(1212)
});

function ComboYear5(){

    var d = new Date();
    var n = d.getFullYear();
    var select = document.getElementById("yearParentesco");
    for(var i = n; i >= 2015; i--) {
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
    var select = document.getElementById("mesParentesco");
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
  let mes  = document.getElementById('mesParentesco')
  let year  = document.getElementById('yearParentesco')
 
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
			let elemento = e.distrito;
		    if (!labels.includes(elemento)) {
		      labels.push(elemento);
		    }
	})	
	
	/**Titular  */
	titular = []
     labels.forEach(e => {
		 
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].distrito == e && data[i].parentesco == 'Titular' ) {
				
			
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
			if(data[i].distrito == e && data[i].parentesco == 'Padre' ) {
				
			
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
			if(data[i].distrito == e && data[i].parentesco == 'Madre' ) {
				
				
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
			if(data[i].distrito == e && data[i].parentesco == 'Conyuge' ) {
				
			
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
			if(data[i].distrito == e && data[i].parentesco == 'HijoDiscapacitado' ) {
				
			
				validar = data[i].numero
			}  

		}
		hijo.push(validar)
	
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
	
	var ctx2 = document.getElementById("myChartParentesco");
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
				      label: 'Titular',
				      data: titular,
				      backgroundColor: '#3498DB',
				    },
				    {
				      label: 'Padre',
				      data: padre,
				      backgroundColor: "#DC7633",
				    },
				    {
				      label: 'Madre',
				      data: madre,
				      backgroundColor:'#FE2E64',
				    },
				    {
				      label: 'Conyuge',
				      data: conyuge,
				      backgroundColor: '#BB8FCE',
				    },
				    {
				      label: 'Hijo Discapacitado',
				      data: hijo,
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
                					fontSize:15
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
						text: 'Distrito por Parentesco',
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

async function getGrafico2() {
  try {
    let grafico2 = [];
    let url = "http://localhost:8083/views/brigada/distritoparentescoactual";
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
    let url = "http://localhost:8083/views/brigada/distritoparentesco/"+mes+"/"+year;
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
			let elemento = e.distrito;
		    if (!labels.includes(elemento)) {
		      labels.push(elemento);
		    }
	})	
	
	/**Titular  */
	titular = []
     labels.forEach(e => {
		 
		 let validar = null
		for(let i = 0 ; i < data.length; i++) {
			if(data[i].distrito == e && data[i].parentesco == 'Titular' ) {
				
			
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
			if(data[i].distrito == e && data[i].parentesco == 'Padre' ) {
				
			
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
			if(data[i].distrito == e && data[i].parentesco == 'Madre' ) {
				
				
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
			if(data[i].distrito == e && data[i].parentesco == 'Conyuge' ) {
				
			
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
			if(data[i].distrito == e && data[i].parentesco == 'HijoDiscapacitado' ) {
				
			
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


