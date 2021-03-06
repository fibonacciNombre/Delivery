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
				<h3 class="modal-title">Editar usuario</h3>
			</div>
			 
			<form id="form-mntusuario">
				
				<input type="hidden" id="indaccion" name="indaccion" value="1"/>
				
				<%@include file="/jsp/usuario/form-usuario.jsp" %>
				
				<div class="row">
	        
			        <div class="col-md-12">		        
			            <div class="col-md-12">
			                <div class="form-group">
			                	<label class="col-md-6  control-label" style="padding: 0px;">
			                	</label>
			                	<div class="col-sm-6 pull-right" style="text-align: right; padding: 0px;">
				                    <button type="button" 
				                    		class="btn btn-default"
				                    		onclick="javascript:renovarContrasena();">
				                    			Renovar contraseña
				                    </button>
				                    
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

				cboperfil 			: {				required 	: true },
				
				idptipodocumento 	: {				required 	: true },
				
				nrodocumento 		: {				required 	: true,
													digits 	 	: true,
													minlength 	: 8},
				
				nombres		 		: {				required 	: true },
				
				apepaterno	 		: {				required 	: true },
				
				apematerno	 		: {				required 	: true },
				
				codusuario 			: {				required 	: true,
													minlength 	: 5 },
				
				contrasena 			: {				required 	: true,
													minlength 	: 5 },
													
				telfmovil 			: {				required 	: true,
													digits 	 	: true,
													minlength 	: 9 },
													
				correo 				: {				required 	: true,
													email		: true}
			},
			messages : {
				cboperfil 			: {				required 	: "Debes seleccionar un perfil" },
				
				idptipodocumento 	: {				required 	: "Debes seleccionar un tipo de documento" },
				
				nrodocumento 		: {				required 	: "Debes ingresar un nro. de documento",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 8 dígitos"},
				
				nombres 			: {				required 	: "Debes ingresar el nombre" },
				
				apepaterno 			: {				required 	: "Debes ingresar el apellido paterno" },
				
				apematerno 			: {				required 	: "Debes ingresar el apellido materno" },
				
				codusuario 			: {				required 	: "Debes ingresar el código de usuario",
													minlength 	: "Debes ingresar un mínimo de 5 carácteres"},

				contrasena 			: {				required 	: "Debes ingresar la contraseña para el código de usuario",
													minlength 	: "Ingresar un mínimo de 5 carácteres"},
														
				telfmovil 			: {				required 	: "Debes ingresar un teléfono móvil",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 9 dígitos" },
													
				correo 				: {
													required 	: "Debes ingresar un correo electrónico",
													email		: "Debes ingresar formato de correo electrónico válido"},
													
				estado 				: {				required 	: "Debes seleccionar un estado para el colaborador" }
			}
		});	
		
	});
    
	function actualizarUsuario(){
		
		if($("#form-mntusuario").valid()){
			
			$("#form-mntusuario #idperfil").val($("#form-mntusuario #cboperfil").val());
			
			$("#form-mntusuario #idcourier").val($("#form-mntusuario #cbocourier").val());
			
 			var param 	= new Object();
 			param 		= $("#form-mntusuario").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntUsuario",
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
															bsqUsuario();
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
	
	function renovarContrasena(){
		
		loadModalCargando();
			
			var param				= new Object();
			param.codusuario		= $("#form-mntusuario #codusuario").val();
			
			setTimeout(
					  	function(){   			
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntContrasena",
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
													loadModalMensaje("Enhorabuena", "Se ha generado una contraseña temporal, el usuario tendrá que renovarla en su próxima autenticación al  sistema", function(){
														bsqUsuario();
														$("#modalEditarUsuario").modal('hide');
													});																										
												}else
													loadModalMensaje("Atención",message,null);													
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR RENOVANDO LA CONTRASEÑA",null);								
							}			
						});		    					    				
  				},1000);    
		
	}
    
</script>
 