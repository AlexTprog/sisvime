function eliminarveh(id) {
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
					url:"/views/vehiculo/eliminarveh/"+id,
					success: function(res){Console.log(res);}
			});
			
			swal("¡Eliminado!,Tu archivo ha sido eliminado.",
			    {
					icon: "success",}).then((ok)=>{
					if(ok){location.href="/views/vehiculo/listarveh";
				}
			});

			} else {
				swal("¡Tu archivo no a sido Eliminado!");
			}
		});
}