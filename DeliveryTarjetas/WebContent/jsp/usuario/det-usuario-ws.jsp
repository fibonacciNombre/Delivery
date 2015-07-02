<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarUsuarioWS"
		class="modal fade"
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>				 
				<h3 class="modal-title">Editar usuario de servicios web</h3>
			</div>
			 
			<form id="form-mntusuario-ws">
			
				<input type="hidden" id="idusuario" name="idusuario"/>
				
				<input type="hidden" id="indaccion" name="indaccion" value="1"/>
				
				<%@include file="/jsp/usuario/form-usuario-ws.jsp" %>
				
				<script>
					$("#contrasena").attr("readonly","readonly");
				</script>
				
				<div class="row">
	        
			        <div class="col-md-12">		        
			            <div class="col-md-12">
			                <div class="form-group">
			                	<label class="col-md-6  control-label" style="padding: 0px;">
			                	</label>
			                	<div class="col-sm-6 pull-right" style="text-align: right; padding: 0px;">
				                    <button type="button" 
				                    		class="btn btn-default"
				                    		data-toggle="modal"
											data-target="#modalEditarContrasenaWS">
				                    			Renovar contraseña
				                    </button>
				                    
				                    <button id="btnRegistrar"
				                    		type="button" 
				                    		class="btn btn-primary" 
				                    		onclick="javascript:actualizarUsuarioWS();">
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

				
<div id="modalEditarContrasenaWS"
		class="modal fade"
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">

			<form id="form-mntcontrasena-ws">
			
				<input type="hidden" id="idusuario" name="idusuario"/>
									
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>				 
					<h3 class="modal-title">Editar usuario de servicios web</h3>
			 	</div>

				<div class="row">
					<div class="col-md-12">
				
						<div class="form-group" id="nvacontrasena-div">
						    <label for="nvacontrasena" class="col-sm-5 col-sm-offset-1 control-label required">Nueva contrasena </label>
						    <div class="col-sm-5">
						    	<input type="password" class="form-control" id="contrasena" name="contrasena" maxlength="12">
						    </div>
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
				                    		onclick="javascript:actContrasenaWS();">
				                    			Renovar contraseña
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
	function actualizarUsuarioWS(){
	
		if($("#form-mntusuario-ws").valid()){
			
			var param 	= new Object();
			param 		= $("#form-mntusuario-ws").serializeArray();
			
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
														bsqUsuarioWS();
														$("#modalEditarUsuarioWS").modal('hide');
													});																										
												}else
													loadModalMensaje("Atención",message,null);													
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR ACTUALIZANDO INFORMACION DEL USUARIO WEB",null);								
							}			
						});		    					    				
	 				},1000);    			    		
		}    	
	}

	function actContrasenaWS(){
		
		if($("#form-mntcontrasena-ws").valid()){
	
			loadModalCargando();
			
			var param 				= new Object();
			param.codusuario 		= $("#form-mntusuario-ws #codusuario").val();
			param.indrnvcontrasena 	= "N";
			param.contrasena 		= $("#form-mntcontrasena-ws #contrasena").val();
			
			setTimeout( function() {
							
							$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntContrasena",
								cache 		: false ,
								dataType	: "json",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								async 		: false,
								data 		: param,		
								success 	: function(rsp){
			
												var statustx	= rsp.tx.statustx;
												var messagetx	= rsp.tx.messagetx;
												
												closeModalCargando();
												
												if(statustx == 0){
													
													var paramUsr 		= new Object();
													paramUsr.codusuario	= $("#form-mntusuario-ws #codusuario").val();
													
													$.ajax({
														type 		: "POST",
														url 		: "/DeliveryTarjetas/usuario.do"+"?method=obtUsuario",
														cache 		: false ,
														dataType	: "json",
														contentType : "application/x-www-form-urlencoded; charset=UTF-8",
														async 		: false,
														data 		: paramUsr,		
														success 	: function(rspUsr){

																		var status		= rspUsr.tx.statustx;
																		var message		= rspUsr.tx.messagetx;
																		
																		if(status == 0){
																			$("#form-mntusuario-ws #contrasena-visible").val(rspUsr.usuario.contrasena);
																			$("#form-mntusuario-ws #contrasena-visible").addClass("resaltar-background");
																		}else
																			loadModalMensaje("Atención",message,null);	
														}
													});
													
													loadModalMensaje("Enhorabuena","Haz se ha registrado una nueva contraseña",
																		function(){
																			$("#modalEditarContrasenaWS").modal('hide');																					
																		});														
												}else
													loadModalMensaje("Mensaje",messagetx,null); 							 
								},
								complete 	: function (){},
								error		: function (rsp, xhr, ajaxOptions, thrownError) {									
												closeModalCargando();
												$("#closebtnconfig").click(); 
												loadModalMensaje("Error","ERROR EN EL CAMBIO DE CONTRASENA DEL USUARIO WEB",null); 					
								}				
							});
					  }, 1000);			
		}
	}
</script>