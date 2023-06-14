document.addEventListener("DOMContentLoaded",  (e) => {
  muestraFoto();
  
});

function muestraFoto() {
	const inputVehiculo = document.getElementById("imgVehiculo");
	
	inputVehiculo.addEventListener('change', e => {
		
		const imagenVehiculo = document.getElementById("imagenVehiculo");
		
		let archivos = inputVehiculo.files
		
		let primer = archivos[0]
		
		let objectUrl = URL.createObjectURL(primer)
		
	
		imagenVehiculo.src = objectUrl;
		console.log(reader.result)
	})
	
}