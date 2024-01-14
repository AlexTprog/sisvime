function deletevisita(id) {
	swal({
		title: "¿Está seguro que desea cancelar la brigada médica?",
		text: "¡Esta operación no se pude revertir!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
							
			$.ajax({
					url:"/views/visitamed/deletevisita/"+id,
					success: function(res){
					 Console.log(res);
					}			
			
			});
			
				swal("¡Cancelado!,ha sido cancelado con éxito.", {
					icon: "success",
				}).then((ok)=>{
						if(ok){
						 location.href="/views/visitamed/listvisitamedica";
						}
				
				});
			} else {
				swal("¡Tu brigada no ha sido Eliminada!");
			}
		});
}