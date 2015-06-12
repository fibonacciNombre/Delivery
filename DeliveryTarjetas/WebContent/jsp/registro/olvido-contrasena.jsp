<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
	<body>
		<div id="olvido-contrasenia-container">
			<div class="modal-header">				
				<button type="button" 
							class="close" 
							data-dismiss="modal" 
							aria-hidden="true">					
								&times;
				</button>				
				<h3 class="modal-title">¿Olvidaste tu contraseña?</h3>				
			</div>
	
			<div class="row">		
				
				<%-- INI CONTENEDOR CAMBIO DE CONTRASEÑA BASICO --%>
				<div class="col-md-12" id="olvido-contrasena-paso1">					
					<form id="form-olvido-contrasena-paso1">
						<div class="form-group">
							<label for="idtipodocumento" class="col-md-6 control-label">Tipo de documento de identidad: </label>
							<div class="col-md-5">
								<select class="form-control" id="idtipodocumento" name="idtipodocumento"></select>
								<div class="result"></div>
							</div>
						</div>
			
						<div class="form-group">
							<label for="numerodoc" class="col-md-6 control-label">Nro de documento: </label>
							<div class="col-md-5">
								<input type="text" 
										id="numerodoc" 
										name="numerodoc" 
										class="form-control"
										maxlength="15">
								<div class="result"></div>
							</div>
						</div>
						<br>
						
						<input id="publickey" name="publickey" type="hidden"/>
						
						<div id="recaptcha"></div>
						
						
						<div id="recaptcha_widget" style="display:none" class="content-recaptcha">
							
							<div style="text-align: left;">
								<div id="recaptcha_image" class="img-recaptcha">
								</div>
								
								<div class="botonera-recaptcha">
									<a href="javascript:Recaptcha.reload()" style="text-decoration: none !important">
										<img src="https://www.google.com/recaptcha/api/img/clean/refresh.png">
									</a>
									<a class="recaptcha_only_if_image" href="javascript:Recaptcha.switch_type('audio')" onclick='document.getElementById("recaptcha_response_field").placeholder="Escribe lo que oigas"' style="text-decoration: none !important">
										<img src="https://www.google.com/recaptcha/api/img/clean/audio.png">
									</a>
									<a class="recaptcha_only_if_audio" href="javascript:Recaptcha.switch_type('image')" onclick='document.getElementById("recaptcha_response_field").placeholder="Introduzca el texto"' style="text-decoration: none !important">
										<img src="https://www.google.com/recaptcha/api/img/clean/text.png">
									</a>
									<a href="javascript:Recaptcha.showhelp()" style="text-decoration: none !important">
										<img src="https://www.google.com/recaptcha/api/img/clean/help.png">
									</a>
								</div>
								
								<div class="logo-recaptcha">
									<img src="http://www.google.com/recaptcha/api/img/clean/logo.png">							
								</div>
								
								<div class="input-recaptcha" style="display: table;margin-top: 10px;">
								<input class="form-control input-recaptcha-field" type="text" id="recaptcha_response_field" name="recaptcha_response_field" placeholder="Introduzca el texto"/>
							</div>
							</div>
						</div>

						
						
						<br>
						<div class="form-group">
							<div class="col-md-offset-6 col-md-5">
								<a href="javascript:validarUsuario();" class="btn btn-primary pull-right">
									Continuar
								</a>
							</div>
						</div>
					</form>
				</div>
			
				<div class="col-md-12" id="olvido-contrasena-paso2" style="display: none;">
			
					<div class="text-center">
						<div class="text-center">
							Enviaremos una nueva contraseña a tu correo electrónico 
						</div>
						<div id="email_reg" class="text-center"><b>{CORREO ELECTRONICO DE REGISTRO}</b></div>
					</div>
					<br>
					<div class="form-group text-center">
						<div class="col-md-6 col-md-offset-3 ">
							<button type="button" 
		                       		class="btn btn-primary"
		                       		data-dismiss="modal" 
		                       		onclick="javascript:enviarContrasenaMail();">
		                       			Continuar
		                       </button>
						</div>
					</div>
			
					<div class="form-group text-center">
		            	<div class="col-md-6 col-md-offset-3 ">
		                	<button type="button" 
		                    		class="btn btn-primary"
		                       		onclick="javascript:solicitarContrasenaNvoCorreo();">
		                       			Yo no uso este correo
		                       </button>
						</div>
					</div>
				</div>
				<%-- FIN CONTENEDOR CAMBIO DE CONTRASEÑA BASICO --%>
				
				<%-- INI CONTENEDOR CAMBIO DE CONTRASEÑA ESPECIAL, SI USUARIO RESPONDE PREG. SEGURIDAD PUEDE CAMBIAR MAIL DE NOTIFICACION --%>
				<div id="olvido-contrasena-paso2-nvocorreo" style="display: none;">
					<hr>			
					<h6>Responde tu pregunta de seguridad</h6>					
					<form id="form-olvido-contrasena-paso2-nvocorreo">
						<div class="row">
							<div class="col-md-12">
								
								<div class="form-group">
									<label class="col-md-5 control-label">
										Pregunta de seguridad:
									</label>
									<div class="col-md-7">
										<!-- {PREGUNTA DE SEGURIDAD DEL CLIENTE} -->
										<%--
										<select class="form-control" id="selpregseguridad" name="selpregseguridad" disabled></select>
										--%>
										<input type="text" 
												class="form-control" 
												id="inputpregseguridad" 
												name="inputpregseguridad"/>
										
										<div class="result"></div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-5 control-label">Respuesta: </label>
									<div class="col-md-7">
										<input type="text" 
												class="form-control" 
												id="inputrespuesta"
												name="inputrespuesta">
										<div class="result"></div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-12 pull-right">
										<a id="btn-validar-rpta" href="javascript:validarRptaPregSeguridad();" class="btn btn-primary pull-right">
												Continuar
										</a>
									</div>
								</div>
								
							</div>
						</div>
					</form>					
				</div>
				
				<div class="col-md-12" id="olvido-contrasena-paso3-nvocorreo" style="display: none;">			
					<h6>Ingresa el correo electrónico al que deseas que te enviemos tu contraseña</h6>			
					<form id="form-olvido-contrasena-paso3-nvocorreo">
						<div class="row">					
							<div class="col-md-12">
								
								<div class="form-group">
									<label for="email" class="col-md-5 control-label">Email: </label>
									<div class="col-md-7">
										<input type="text" 
												class="form-control" 
												id="email"
												name="email" maxlength="100">
										<div class="result"></div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-12 pull-right">
										<button id="btnNuevoMail"
												type="button" 
												class="btn btn-primary pull-right"
												aria-hidden="true"
												onclick="javascript:enviarContrasenaNvoMail();">
													Continuar
										</button>
									</div>
								</div>
								
							</div>					
						</div>
					</form>				
				</div>
				<%-- FIN CONTENEDOR CAMBIO DE CONTRASEÑA ESPECIAL, SI USUARIO RESPONDE PREG. SEGURIDAD PUEDE CAMBIAR MAIL DE NOTIFICACION --%>	
			</div>
		</div>	

		<script src="<%=request.getContextPath()%>/js/bbva/main-portal.js"></script>

		<script>
	
			$().ready(function(){
		
				$("#publickey").val(CTE_PUBLIC_KEY);
				
				Recaptcha.create($("#publickey").val(),   // update with your api key
					    "recaptcha_widget",
					    {
					      theme: "custom",
					      custom_theme_widget: 'recaptcha_widget',
					      callback: Recaptcha.focus_response_field
					    }
					  );
				
				callCargaControlParam('PWEB_USR_TIPDOC','olvido-contrasenia-container #idtipodocumento');
				<%--
				callCargaControlParam('PWEB_USR_PREGSEG','olvido-contrasena-paso2-nvocorreo #selpregseguridad');
				--%>
				jQuery.validator.addMethod("alphanumeric", function(value, element) {
			        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
				});
				
				$("#form-olvido-contrasena-paso1").validate({
					rules : {
						idtipodocumento : {
											required 	: true },
						numerodoc 		: {
											required 	: true,
											digits 	 	: true,
											minlength 	: function(){
																$result = 8;
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_DNI ||
																   $("#idtipodocumento").val()==CTE_TIPDOCUM_CE){
																	$result = 8;
																}
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_CIP ||
																   $("#idtipodocumento").val()==CTE_TIPDOCUM_PAS	){
																	$result = 7;
																}
																return $result;
											 				},
											maxlength 	: function(){
																$result = 8;
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_DNI){
																	$result = 8;
																}
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_CE ||
															   	   $("#idtipodocumento").val()==CTE_TIPDOCUM_PAS){
																	$result = 12;
																}
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_CIP	){
																	$result = 15;
																}
																return $result;
								  							}
											}
					},
					messages : {
						idtipodocumento : {
											required 	: "Seleccione Tipo de Documento" },
						numerodoc 		: {
											required 	: "Ingrese Número de Documento",
											digits 	 	: "Sólo números",
											minlength 	: function(){
																$result = 8;
																$res1 = "Ingresar un mínimo de ";
																$res2 = " dígitos";
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_DNI ||
																   $("#idtipodocumento").val()==CTE_TIPDOCUM_CE){
																	$result = 8;
																}
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_CIP ||
																   $("#idtipodocumento").val()==CTE_TIPDOCUM_PAS	){
																	$result = 7;
																}
																return $res1+$result+$res2;
															},
											maxlength 	: function(){
																$result = 8;
																$res1 = "Ingresar un máximo de ";
																$res2 = " dígitos";
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_DNI){
																	$result = 8;
																}
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_CE ||
															   	   $("#idtipodocumento").val()==CTE_TIPDOCUM_PAS){
																	$result = 12;
																}
																if($("#idtipodocumento").val()==CTE_TIPDOCUM_CIP	){
																	$result = 15;
																}
																return $res1+$result+$res2;
														  }
											}
					}
				});
				
				$("#form-olvido-contrasena-paso2-nvocorreo").validate({
					rules : {
						/*selpregseguridad 	: {
												required 	: true },*/
						inputrespuesta 		: {
												required 	: true}
					},
					messages : {
						/*selpregseguridad 	: {
												required 	: "Seleccionar Pregunta de Seguridad" },*/
						inputrespuesta 		: {
												required 	: "Ingresar Pregunta de Seguridad"}
					}
				});	
				
				$("#form-olvido-contrasena-paso3-nvocorreo").validate({
					rules : {
						email	: {
									required 	: true,
									email 		: true,
									maxlength 	: 100}
					},
					messages : {
						email 	: {
									required 	: "Ingresar Email",
									email 		: "Ingresar formato de email válido",
									maxlength 	: "Has excedido la longitud máxima"}
					}
				});	
				
				activarChecksValidate("form-olvido-contrasena-paso1");
				activarChecksValidate("form-olvido-contrasena-paso2-nvocorreo");
				activarChecksValidate("form-olvido-contrasena-paso3-nvocorreo");
				
			});

			function enviarContrasenaMail(){
				
				var destinomail;
				
				if($("#email").val()==null || $("#email").val()=="")
					destinomail = CTE_EMAIL;
				else
					destinomail = $("#email").val();

				loadModalCargando();
				
				setTimeout( function() {
					var param 					= new Object();
					param.email 				= destinomail;
					param.idetercero 			= CTE_IDETERCERO;
					param.numerodoc  			= CTE_NUM_DOC;
					param.idptipodocumento		= CTE_TIP_DOC;
					param.idepuntocontactomail 	= CTE_PUNTOCONTACTO_EMAIL;
					
					$.ajax({
						type 		: "POST",
						url 		: "/PORTALWEB/perfil.do"+"?method=enviarContrasenaTemp",
						cache 		: false,
						async 		: false,
						dataType 	: "json",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						data		: param,
						success 	: function(rsp) {
							
										// alert('ENVIAR MAIL CON CONTRASEÑA TEMPORAL '+destinomail);
										var status 	= rsp.statustx;
										var message = rsp.messagetx;
	
										closeModalCargando();
										
										if(status == 0){
											loadModalMensaje("Confirmación de envío", message, function(){$("#modalOlvideContrasenia").modal('hide');});
											limpiarFormOlvidoContrasena();
										}else{
											loadModalMensaje("Atención", message, null);
										}															
						},
						error : function(xhr, ajaxOptions, thrownError) {}
					});
				}, 1000);
			}

			function enviarContrasenaNvoMail(){
				//$("#btnNuevoMail").attr('data-dismiss','""');
				if($("#form-olvido-contrasena-paso3-nvocorreo").valid()){
					//$("#btnNuevoMail").attr('data-dismiss','modal');
					enviarContrasenaMail();
				}
			}
			
			function validarCaptcha(){

				var result = true;
				var paramCaptcha 		= new Object();
				paramCaptcha.challenge 	= $("#recaptcha_challenge_field").val();
				paramCaptcha.response 	= $("#recaptcha_response_field").val();
				
				$.ajax({
					type 		: "POST",
					url 		: "/PORTALWEB/login.do"+"?method=validarCaptcha",
					cache 		: false,
					async 		: false,
					dataType 	: 'json',
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					data		: paramCaptcha,
					success 	: function(rsp) {

										var status 	= rsp.statustx;
										var message = rsp.messagetx;

										if(status==0){
											result =  true;
										}else{
											closeModalCargando();
											Recaptcha.reload();
											loadModalMensaje("Atención", message, null);
											result =  false;
										}						
					},
					error 		: function(xhr, ajaxOptions, thrownError) {
										closeModalCargando();
					}
				});
				return result;
			}
	
			function validarUsuario(){
				
				if($("#form-olvido-contrasena-paso1").valid()){

					loadModalCargando();
					
					var resultCaptcha = validarCaptcha();
					
					if(resultCaptcha){
						
						var param 					= new Object();
						param.numerodoc 			= $("#numerodoc").val();
						param.idptipodocumento 		= $("#idtipodocumento").val();
						
						$.ajax({
							type 		: "POST",
							url 		: "/PORTALWEB/perfil.do"+"?method=obtDatosUsuarioNroDoc",
							cache 		: false ,
							dataType	: "json",
							contentType : "application/x-www-form-urlencoded; charset=UTF-8",
							async 		: false,
							data 		: param,		
							success 	: function(rsp){

											closeModalCargando();
											
											var status		= rsp.Tx[0].statustx;
											var message		= rsp.Tx[0].messagetx;
		
											if(status == 0){
												CTE_NUM_DOC = $("#numerodoc").val();
												CTE_TIP_DOC = $("#idtipodocumento").val();
												
												if(rsp.Usuarioweb!='undefined' || rsp.Usuarioweb!=null){
													
													CTE_IDETERCERO 			= rsp.Usuarioweb[0].idetercero;
													CTE_PREG_SEG 			= rsp.Usuarioweb[0].pregseg;
													CTE_RESP_SEG		 	= rsp.Usuarioweb[0].respseg;
													CTE_PUNTOCONTACTO_EMAIL = rsp.Usuarioweb[0].idepuntocontactomail;

													if(rsp.Correoweb.length > 0)
														CTE_EMAIL	= rsp.Correoweb[0].email;
													else
														CTE_EMAIL	= rsp.UsuarioLdap[0].mail;
																	
													$("#olvido-contrasena-paso2 #email_reg").prop('innerHTML','<b>'+CTE_EMAIL+'</b>');
													
													$("#olvido-contrasena-paso1").slideUp(1000,'swing', function(){
														$("#olvido-contrasena-paso2").slideDown(1000);
														Recaptcha.reload();
													});
													
												}
											}else{
												loadModalMensaje("Atención", message, null);
											}
							},
							complete : function (){},
							error: function (rsp, xhr, ajaxOptions, thrownError) {}			
						});
					}
				}
			}
	
			function solicitarContrasenaNvoCorreo(){					
					$("#olvido-contrasena-paso2-nvocorreo #inputpregseguridad").val(CTE_PREG_SEG);
					$("#olvido-contrasena-paso2-nvocorreo #inputpregseguridad").attr("disabled","disabled");
					$("#olvido-contrasena-paso2-nvocorreo").slideDown(1000);
			}
			
			function validarRptaPregSeguridad(){
				
				if($("#form-olvido-contrasena-paso2-nvocorreo").valid()){

					loadModalCargando();
					
					var param 			= new Object();					
					param.numerodoc 	= CTE_NUM_DOC;
					param.respseg 		= $("#inputrespuesta").val();
					
					$.ajax({
						type 		: "POST",
						url 		: "/PORTALWEB/perfil.do"+"?method=validarRptaPregSeguridad",
						cache 		: false ,
						dataType	: "json",
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						async 		: false,
						data 		: param,		
						success 	: function(rsp){

										var status 	= rsp.statustx;
										var message = rsp.messagetx;

										closeModalCargando();

										if(status == 0){
											$("#inputrespuesta").attr("readonly","readonly");
											$("#btn-validar-rpta").attr("disabled","disabled");
											
											$("#olvido-contrasena-paso2").slideUp(1000,'swing', function(){
												$("#olvido-contrasena-paso3-nvocorreo").slideDown(1000);	
											});
										}else
											loadModalMensaje("Atención", message, null);																					
						},
						complete : function (){},
						error: function (rsp, xhr, ajaxOptions, thrownError) {
							closeModalCargando();
							loadModalMensaje("&nbsp;","Hubo problemas en el procesamiento. Intentelo mas tarde.",null);
						}			
					});					
				}
			}
			
			function limpiarFormOlvidoContrasena(){
				
				$("#inputrespuesta").removeAttr("readonly");
				$("#btn-validar-rpta").removeAttr("disabled");
				
				$("#olvido-contrasena-paso1").show();
				limpiarFormulario('form-olvido-contrasena-paso1');
				
				$("#olvido-contrasena-paso2").hide();
				
				$("#olvido-contrasena-paso2-nvocorreo").hide();
				limpiarFormulario('form-olvido-contrasena-paso2-nvocorreo');
				
				$("#olvido-contrasena-paso3-nvocorreo").hide();
				limpiarFormulario('form-olvido-contrasena-paso3-nvocorreo');				
			}			
		</script>
	</body>
</html>
