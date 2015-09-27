<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarUsuario"
		class="modal fade"
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>				 
				<h3 class="modal-title">Editar subgerente</h3>
			</div>
			 
			<form id="form-mntusuario">
				
				<input type="hidden" id="indaccion" name="indaccion" value="1"/>				
				<input type="hidden" id="idsubgerente" name="idsubgerente"/>
				<div class="row">	 
				           
					<div class="col-md-6">																						             
						<div class="form-group" id="nombres-div">
						    <label for="nombre" class="col-md-12 control-label required">Nombres </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="nombre" name="nombre" maxlength="120">
						        <div class="result"></div>
						    </div>
						</div>
						
						<div class="form-group" id="apepaterno-div">
						    <label for="apellidopaterno" class="col-md-12 control-label required">Apellido paterno </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="apellidopaterno" name="apellidopaterno" maxlength="120">
						        <div class="result"></div>
						    </div>
						</div>
						
						<div class="form-group" id="apematerno-div">
						    <label for="apellidomaterno" class="col-md-12 control-label required">Apellido materno </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="apellidomaterno" name="apellidomaterno" maxlength="120">
						        <div class="result"></div>
						    </div>
						</div>
					</div>
				     
				   	<div class="col-md-6">												
						<div class="form-group" id="correo-div">
						    <label for="correo" class="col-md-12 control-label required">Correo electrónico </label>
						    <div class="col-md-12">
						        <input type="text" class="form-control" id="correo"  name="correo" maxlength="200">
						        <div class="result"></div>
						    </div>
						</div>
						
						<div class="form-group" id="estado-div">
						    <label for="estado" class="col-md-12 control-label required">Estado </label>
						    <div class="col-md-12">
						        <select class="form-control" id="estado" name="estado">              	
						        </select>
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
			                	<label class="col-md-6  control-label" style="padding: 0px;">
			                	</label>
			                	<div class="col-sm-6 pull-right" style="text-align: right; padding: 0px;">				                    				                    
				                    <button id="btnRegistrar"
				                    		type="button" 
				                    		class="btn btn-primary" 
				                    		onclick="javascript:actualizarUsuario();">
				                    			Modificar
				                    </button>
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
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-mntusuario").validate({
			rules : {				
				nombre		 		: {				required 	: true },
				
				apellidopaterno	 	: {				required 	: true },
				
				apellidomaterno	 	: {				required 	: true },							
													
				correo 				: {				required 	: true,
													email		: true}
			},
			messages : {								
				nombre 				: {				required 	: "Debes ingresar el nombre" },
				
				apellidopaterno 	: {				required 	: "Debes ingresar el apellido paterno" },
				
				apellidomaterno		: {				required 	: "Debes ingresar el apellido materno" },
																				
				correo 				: {				required 	: "Debes ingresar un correo electrónico",
													email		: "Debes ingresar formato de correo electrónico válido"},
													
				estado 				: {				required 	: "Debes seleccionar un estado para el colaborador" }
			}
		});	
		
	});
    
	function actualizarUsuario(){
		
		if($("#form-mntusuario").valid()){
			
 			var param 	= new Object();
 			param 		= $("#form-mntusuario").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntSubgerente",
								cache 		: false ,
								dataType	: "json",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								async 		: false,
								data 		: param,
								success 	: function(rsp){
								
													var status 	= rsp.tx.statustx;
													var message = rsp.tx.messagetx;
				
													closeModalCargando();
													
													if(status == 0){
														loadModalMensaje("Enhorabuena", message, function(){
															bsqCourier();
															$("#modalEditarUsuario").modal('hide');
														});																										
													}else
														loadModalMensaje("Atención",message,null);													
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","ERROR REGISTRANDO USUARIO WEB",null);								
								}			
							});		    					    				
	  				},1000);    			    		
    	}    	
    }    
</script>