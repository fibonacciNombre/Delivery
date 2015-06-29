<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarColaborador" 
		class="modal fade" 
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3 class="modal-title">Courier - Editar datos del colaborador</h3>
			</div>

			<form id="form-mntcolaborador">

				<input type="hidden" id="idtercero" name="idtercero" value="" />
									
				<%@include file="/jsp/courier/form-colaboradorcourier.jsp" %>
				 
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-6 control-label" style="padding: 0px;">
								</label>
								<div class="col-sm-6 pull-right"
										style="text-align: right; padding: 0px;">										
									<button id="btnRegistrar" type="button" class="btn btn-primary" onclick="javascript:actualizarColaborador();">Guardar</button>
								</div>
							</div>
						</div>
					</div>
				</div>

			</form>
		</div>
		
	</div>
</div>

<script>

	$().ready(function(){
		
		$("#form-mntcolaborador").validate({
			rules : {
	
				cbocourier 			: {				required 	: true },
				
				idptipodocumento 	: {				required 	: true },
				
				nrodocumento 		: {				required 	: true,
													digits 	 	: true,
													minlength 	: 8},
				
				nombres		 		: {				required 	: true },
				
				apepaterno	 		: {				required 	: true },
				
				apematerno	 		: {				required 	: true },
				
				telfmovil 			: {				required 	: true,
													digits 	 	: true,
													minlength 	: 9 },
													
				correo 				: {				required 	: true,
													email		: true },
													
				idpestado 			: {				required 	: true }
			},
			messages : {
				cbocourier 			: {				required 	: "Debes seleccionar un courier" },
				
				idptipodocumento 	: {				required 	: "Debes seleccionar un tipo de documento" },
				
				nrodocumento 		: {				required 	: "Debes ingresar un nro. de documento",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 8 dígitos"},
				
				nombres 			: {				required 	: "Debes ingresar el nombre" },
				
				apepaterno 			: {				required 	: "Debes ingresar el apellido paterno" },
				
				apematerno 			: {				required 	: "Debes ingresar el apellido materno" },
				
				telfmovil 			: {				required 	: "Debes ingresar un teléfono móvil",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 9 dígitos" },
													
				correo 				: {
													required 	: "Debes ingresar un email",
													email		: "Debes ingresar formato de email válido"},
				
				idpestado 			: {				required 	: "Debes seleccionar un estado" }													
			}
		});		
	});
	
	function actualizarColaborador() {
		
		if ($("#form-mntcolaborador").valid()){
		 
	 		loadModalCargando();
	 		
	 		$.ajax({
				type 		: "POST",
				url 		: "/DeliveryTarjetas/tercero.do?method=mntTercero",
				cache 		: false,
				dataType 	: "json",
				contentType	: "application/x-www-form-urlencoded; charset=UTF-8",
				async 		: false,
				data 		: $("#form-mntcolaborador").serializeArray(),
				success 	: function(rsp) {
					
									var status 	= rsp.tx.statustx;
									var message = rsp.tx.messagetx;
	
									closeModalCargando();
									
									if(status == 0)
										loadModalMensaje("Enhorabuena",
															message,
															function(){
																$("#modalEditarColaborador").modal('hide');
																bsqColaborador();
															});										
	
									else
										loadModalMensaje("Atención",message,null);
				},
				error 		: function(rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Lo sentimos","Se presentaron problemas al registrar sus cambios. <br> Por favor intentelo en unos minutos.", null);
				}
			});
	 						 
		} 
	}
</script>