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
				<h3 class="modal-title">Editar datos de la oficina</h3>
			</div>
			 
			<form id="form-mntcourier">			
				<input type="hidden" id="ubigeo" name="ubigeo" value="0"/>	
				<input type="hidden" id="idoficina" name="idoficina" />
				<div class="row">	            
					<div class="col-md-6">												
						<div class="form-group" id="codoficina-div">
						    <label for="codoficina" class="col-md-12 control-label required">Código oficina </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="codoficina"  name="codoficina" maxlength="6">
						        <div class="result"></div>
						    </div>
						</div>
				        
				        <div class="form-group" id="oficina-div">
						    <label for="oficina" class="col-md-12 control-label required">Oficina </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="oficina" name="oficina" maxlength="200">
						        <div class="result"></div>
						    </div>
						</div>
						
						<div class="form-group" id="categoria-div">
						    <label for="categoria" class="col-md-12 control-label">Categoria </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="categoria" name="categoria" maxlength="80">
						        <div class="result"></div>
						    </div>
						</div>
						
						<div class="form-group" id="direccion-div">
						    <label for="direccion" class="col-md-12 control-label required">Dirección </label>
						    <div class="col-md-12">
						        <textarea id="direccion" name="direccion" maxlength="400" rows="4" class="form-control"></textarea>
						        <div class="result"></div>
						    </div>
						</div>	
						
						<div class="form-group" id="horariolv-div">
						    <label for="horariolv" class="col-md-12  control-label required">Horario Lunes-Viernes </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="horariolv" name="horariolv" maxlength="80">
						        <div class="result"></div>
						    </div>
						</div>									
					</div>
				     
				   	<div class="col-md-6">
												
						<div class="form-group" id="horarios-div">
						    <label for="horarios" class="col-md-12 control-label required">Horario Sábados </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="horarios" name="horarios" maxlength="80">		        
						        <div class="result"></div>
						    </div>
						</div>
						      
						<div class="form-group" id="horariodf-div">
						    <label for="horariodf" class="col-md-12 control-label required">Horario Domingo-Feriados </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="horariodf" name="horariodf" maxlength="80">
						        <div class="result"></div>
						    </div>
						</div>
						
						<div class="form-group" id="latitud-div">
						    <label for="latitudofi" class="col-md-12 control-label">Latitud </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="latitudofi"  name="latitudofi" maxlength="14">
						        <div class="result"></div>
						    </div>
						</div>
						
						<div class="form-group" id="longitud-div">
						    <label for="longitudofi" class="col-md-12 control-label">Longitud </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="longitudofi"  name="longitudofi" maxlength="14">
						        <div class="result"></div>
						    </div>
						</div>
				   	</div>
				    	
					<div class="col-md-12">
						<div class="form-group" style="margin-bottom: 0px; font-size: 11px; font-style: italic; font-weight: bold;">
							<label for="*" class="col-md-6  control-label">
								<span>(<label class="required"></label>) Campos obligatorios</span>
							</label>                	
						</div>	                
					</div>
					            
				</div>  
							
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

				departamento 		: {				required 	: true },
				
				provincia 			: {				required 	: true },
				
				distrito 			: {				required 	: true },								
				
				codoficina	 		: {				required 	: true,
													digits 	 	: true,
													minlength 	: 1},
				
				oficina		 		: {				required 	: true },
				
				direccion	 		: {				required 	: true },
				
				horariolv	 		: {				required 	: true },
				
				horarios 			: {				required 	: true },
				
				horariodf 			: {				required 	: true }																
			},
			messages : {
				departamento 		: {				required 	: "Debes seleccionar un departamento" },
				
				provincia 			: {				required 	: "Debes seleccionar un provincia" },
				
				distrito 			: {				required 	: "Debes seleccionar un distrito" },
				
				codoficina 			: {				required 	: "Debes ingresar un código de oficina",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 1 dígitos"},
				
				oficina 			: {				required 	: "Debes ingresar el nombre de la oficina" },
				
				direccion 			: {				required 	: "Debes ingresar la dirección de la oficina" },
				
				horariolv 			: {				required 	: "Debes ingresar el horario de lunes a viernes" },
				
				horarios 			: {				required 	: "Debes ingresar el horario de sábados" },													

				horariodf 			: {				required 	: "Debes ingresar la horario de domingos y feriados"}
			}
		});	
	});
	
	function actualizarCourier() {
		
		if ($("#form-mntcourier").valid()) {
	
			loadModalCargando();
	
			$.ajax({
					type 		: "POST",
					url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntOficina",
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