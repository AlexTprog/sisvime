function eliminaresp(id) {
	swal({
		title: "¿Está seguro de Eliminar?",
		text: "¡Esta operación no se pude revertir!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
							
			$.ajax({
					url:"/eliminaresp/"+id,
					success: function(res){
					 Console.log(res);
					}			
			
			});
			
				swal("¡Eliminado!,Tu archivo ha sido eliminado.", {
					icon: "success",
				}).then((ok)=>{
						if(ok){
						 location.href="/listaresp";
						}
				
				});
			} else {
				swal("¡Tu archivo no a sido Eliminado!");
			}
		});
}