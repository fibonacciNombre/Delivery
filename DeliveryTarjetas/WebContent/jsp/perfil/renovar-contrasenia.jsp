<%@ page language="java" contentType="text/html; charset=utf-8"%>
  <div class="modal-header">
	<button id="closebtnconfig" type="button" class="close" data-dismiss="modal" aria-hidden="true" hidden="true">&times;</button>
	<h3 class="modal-title">Registra tu contraseña</h3>
  </div>
  <div class="modal-body">
	
	<div class="row">
		<div class="col-md-12" id="configPrimerLogin">
			<form id="form-configPrimerLogin" autocomplete="off">
				<div class="col-md-12">
					<h5>Cambiar Contraseña</h5>
				</div>
				<div class="col-md-7">
			  		<div class="form-group">
				   			
				   			<label for="inputNuevacontrasena" class="col-sm-5 control-label">Nueva contraseña:</label>
				   			<div class="col-sm-7">
				   				<input type="password" name="nuevacontrasena" id="nuevacontrasena" class="form-control">
				   				<div class="resutl"></div>
				   			</div>
				   	</div>
				   	<div class="form-group">
				   			<label for="inputConfirmarcontrasena" class="col-sm-5 control-label">Confirmar contraseña:</label>
				   			<div class="col-sm-7">
				   				<input type="password" name="confirmarcontrasena" id="confirmarcontrasena" class="form-control">
				   				<div class="resutl"></div>
				   			</div>
				   	</div>
				</div>
			    <div class="col-md-5">
			   		<p>
			   			Una contraseña segura está compuesta de 8 a 12 caracteres.
			   		</p>
			   		<p>
			   			Diferencia entre mayúsculas y minúsculas.
			   		</p>
			   		<p>
			   			Permite números, letras y símbolos del teclado.
			   		</p>
			    </div>
			    
				<br>
				<div class="col-md-12 text-right ">
						<button type="button" 
		                   		class="btn btn-primary"
		                   		onclick="javascript:actualizarContrasena();">
		                		Finalizar
		                </button>
				</div>
			</form>
		</div>
	</div>
  </div>
  
  <script src="<%=request.getContextPath()%>/js/rimac/main-portal.js"></script>
  
  <script>
  $().ready(function(){
	  
		validateAlfanumerico();

		validateSinEspacios();
		
	  	$("#form-configPrimerLogin").validate({
			rules			: {
								nuevacontrasena	: {
										required 		: true,
										minlength 		: 8,
										maxlength 		: 12,
										sinespacios		: true
								},
								confirmarcontrasena	: {
										equalTo			: "#nuevacontrasena"
								}
						},
			messages 		: {
								nuevacontrasena 	: {
										required		: "Campo obligatorio",
										minlength 		: "Ingrese minimo 8 caracteres",
										maxlength 		: "Ingrese máximo 12 caracteres",
										sinespacios		: "No se permiten espacios en blanco"
								},
								confirmarcontrasena 	: {
										equalTo			: "Las contraseñas no coinciden"
								}
						}
		});
		
		closeModalCargando(); 	  
  }); 
  
  
  function actualizarContrasena(){
		
		if($("#form-configPrimerLogin").valid()){

			loadModalCargando();
			
			setTimeout( function() {
							CTE_JSON_USUARIOPWEB.newpassword = $("#nuevacontrasena").val();
							
							$.ajax({
								type 		: "POST",
								url 		: "/PORTALWEB/perfil.do"+"?method=actContrasena",
								cache 		: false ,
								dataType	: "json",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								async 		: false,
								data 		: CTE_JSON_USUARIOPWEB,		
								success 	: function(rsp){
			
												var statustx	= rsp.statustx;
												var messagetx	= rsp.messagetx;

												closeModalCargando();
												
												if(statustx == 0){
													$("#modalPrimerLogueo").modal('hide');
													
													loadModalMensaje("Felicitaciones","Haz culminado satisfactoriamente tu registro",
																		function(){
																			loadModalCargando();

																			obtDatosUsuarioSesion();
																			
																			if(CTE_MOBILE_IND!="1"){
																				$(".content-rws").load("/PORTALWEB/perfil.do?method=goDashboard",function(){});
																			}else{
																				closeModalCargando();
																				document.forminit.submit();
																			}		
																	});														
												}else
													loadModalMensaje("Mensaje",messagetx,null); 							 
								},
								complete : function (){},
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									$("#closebtnconfig").click(); 
									loadModalMensaje("Error","ERROR EN CONFIGURACION INICIAL",null); 					
								}				
							});
					  }, 1000);			
		}
  }

</script>
