<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarCourier"
		class="modal fade"
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>				 
				<h3 class="modal-title">Editar datos de Courier</h3>
			</div>
			 
			<form id="form-mntcourier">
				
				<%@include file="/jsp/courier/form-courier.jsp" %>
							
				<div class="row">	
					<div class="col-md-12">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-6 control-label" style="padding: 0px;">
								</label>
							<div class="col-sm-6 pull-right"
									style="text-align: right; padding: 0px;">							
								<button id="btnGuardar" type="button" class="btn btn-primary" onclick="javascript:actualizarCourier();">Guardar</button>
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
		
		$("#form-mntcourier").validate({
			rules : {
	
				codbbva 			: {				required 	: true },
				
				rznsocial 			: {				required 	: true },
				
				idptipodocumento 	: {				required 	: true },
				
				nrodocumentocou		: {				required	: true,
													digits 	 	: true,
													minlength 	: 11 },
													
				direccion 			: {				required 	: true },
				
				telfmovil 			: {				require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 9 },
													
				telffijo 			: {				require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 6 },
													
				correo 				: {				required 	: true,
													email		: true },
													
				idpestado			: {				required 	: true }
			},
			messages : {
				codbbva 			: {				required 	: "Debes ingresar el código BBVA" },
				
				rznsocial 			: {				required 	: "Debes ingresar la razón social" },
				
				idptipodocumento 	: {				required 	: "Debes seleccionar el tipo de documento" },
				
				nrodocumentocou		: {				required	: "Debes ingresar el nro. de documento",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Dedes ingresar un mínimo de 11 dígitos"},
													
				direccion 			: {				required 	: "Debes ingresar la dirección completa" },
				
				telfmovil 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 9 dígitos" },
													
				telffijo 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 6 dígitos"},
												
				correo 				: {
													required 	: "Debes ingresar correo electrónico",
													email		: "Debes ingresar formato de correo electrónico válido"},
													
				idpestado 			: {				required 	: "Debes seleccionar un estado" }
			}
		});	
	});
	
	function actualizarCourier() {
		
		if ($("#form-mntcourier").valid()) {
	
			loadModalCargando();
	
			$.ajax({
					type 		: "POST",
					url 		: "/DeliveryTarjetas/courier.do?method=mntCourier",
					cache 		: false,
					dataType 	: "json",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					async 		: false,
					data 		: $("#form-mntcourier").serializeArray(),
					success 	: function(rsp) {
	
										var status = rsp.tx.statustx;
										var message = rsp.tx.messagetx;
				
										closeModalCargando();
				
										if (status == 0)
											loadModalMensaje("Enhorabuena", 
																message,
																function() {
																	$("#modalEditarCourier").modal('hide');
																	bsqCourier();
																});
				
										else
											loadModalMensaje("Atención", message, null);
					},
					error 		: function(rsp, xhr, ajaxOptions, thrownError) {
										closeModalCargando();
										loadModalMensaje(
												"Lo sentimos",
												"Se presentaron problemas al registrar sus cambios. <br> Por favor intentelo en unos minutos.",
												null);
					}
			});
	
		}
	}
</script>