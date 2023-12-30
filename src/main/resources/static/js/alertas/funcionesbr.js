function deletebrigada(id) {
	swal({
		title: "¿Está seguro que desea cancelar la visita médica creada?",
		text: "¡Esta operación no se pude revertir!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
							
			$.ajax({
					url:"/views/brigada/deletebrigada/"+id,
					success: function(res){
					 Console.log(res);
					}			
			
			});
			
				swal("¡Cancelado,ha sido cancelado con éxito.", {
					icon: "success",
				}).then((ok)=>{
						if(ok){
						 location.href="/views/brigada/listbrigadamedica";
						}
				
				});
			} else {
				swal("¡Tu archivo no a sido Eliminado!");
			}
		});
}