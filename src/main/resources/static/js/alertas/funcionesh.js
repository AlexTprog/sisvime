function deleteHorario(id) {
	swal({
		title: "¿Está seguro de Eliminar un Horario Médico?",
		text: "¡Esta operación no se pude revertir!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
							
			$.ajax({
					url:"/views/horatrabajo/deleteHorario/"+id,
					success: function(res){
					 Console.log(res);
					}			
			
			});
			
				swal("¡Eliminado!,Tu Registro ha sido eliminado.", {
					icon: "success",
				}).then((ok)=>{
						if(ok){
						 location.href="/views/horatrabajo/listwork";
						}
				
				});
			} else {
				swal("¡Tu Registro no a sido Eliminado!");
			}
		});
}