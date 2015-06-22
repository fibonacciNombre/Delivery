<script type="text/javascript">

	function cambiarContrasena(){
		
		if ($("#formCambioContrasenia").valid()) {
		
			var actual 	= $("#formCambioContrasenia #contrasenaactual").val();
			var nueva 	= $("#formCambioContrasenia #contrasenanueva").val();

			if(actual==nueva){
				loadModalMensaje("Atenci�n", "Por favor, su nueva contrase�a debe ser diferente a su contrase�a actual", null);
			}else{	
				CTE_JSON_USUARIOPWEB.password 		= actual;
				CTE_JSON_USUARIOPWEB.newpassword 	= nueva;
				
				loadModalCargando();
				
				setTimeout(
						  function(){
								$.ajax({
									type 		: "POST",
									url 		: "/PORTALWEB/perfil.do?method=mntContrasenaPerfil",
									cache 		: false ,
									dataType	: "json",
									contentType : "application/x-www-form-urlencoded; charset=UTF-8",
									async 		: false,
									data 		: CTE_JSON_USUARIOPWEB,
									success 	: function(rsp){
	
													var statusTx 	= rsp.statustx;
													var messageTx	= rsp.messagetx;
	
													closeModalCargando();
													
													if(statusTx == 0){													
														$("#formCambioContrasenia #contrasenaactual").attr("disabled","disabled");
														$("#formCambioContrasenia #contrasenanueva").attr("disabled","disabled");
														$("#formCambioContrasenia #confirmarcontrasena").attr("disabled","disabled");
														$("#formCambioContrasenia #btn-continuar-cambiopwd").attr("disabled","disabled");
														
														dataLayer.push({'event': EV_ACTUALIZA_CONTRASENA, 'numDocument': CTE_NUM_DOC});
														
														$("#divMensajeExitoso").slideDown(1000);
														
													}else
														loadModalMensaje("Atenci�n", messageTx, null);
													
									},
									error: function (rsp, xhr, ajaxOptions, thrownError) {	
												closeModalCargando();				
												loadModalMensaje("Atenci�n", messageTx, null);
									}			
								});
						},1000);
			}
		}else						
			loadModalMensaje("Atenci�n", "Por favor verifique los datos ingresados e intente nuevamente.", null);			
		
		return false;
	}
						
	$(document).ready(function() {
		
		var pageUrl = '';
		pageUrl = VP_GO_CAMBIO_CONTRASENA;
		dataLayer.push({'event': EV_VIRTUAL_PAGE, 'pageUrl': pageUrl, 'numDocument': CTE_NUM_DOC});

		jQuery.validator.addMethod("sinespacios", function(value, element) {
	        return this.optional(element) || /^[^\s]+$/.test(value);
		});
	
		jQuery.validator.addMethod("passwordCheck", function (value, element) {
	        return value == $("#contrasenanueva").val();
		});

	    $("#contrasenaactual").val("");
	    $("#contrasenanueva").val("");
	    $("#confirmarcontrasena").val("");
	    
		$("#formCambioContrasenia").validate({
			rules			: {
								contrasenaactual	: {
										required 		: true,
										minlength 		: 5,
										maxlength 		: 12,
										sinespacios		: true
								},
								contrasenanueva	: {
										required 		: true,
										minlength 		: 8,
										maxlength 		: 12,
										sinespacios		: true
								},
								confirmarcontrasena	: {
										required 		: true,
										minlength 		: 8,
										maxlength 		: 12,
										sinespacios		: true,
										passwordCheck	: true
								}
						},
			messages 		: {
								contrasenaactual 	: {
										required		: "Campo obligatorio",
										minlength 		: "Ingrese minimo 5 caracteres",
										maxlength 		: "Ingrese m�ximo 12 caracteres",
										sinespacios		: "No se permiten espacios en blanco"
								},
								contrasenanueva 	: {
										required		: "Campo obligatorio",
										minlength 		: "Ingrese minimo 8 caracteres",
										maxlength 		: "Ingrese m&aacute;ximo 12 caracteres",
										sinespacios		: "No se permiten espacios en blanco"
								},
								confirmarcontrasena : {
										required		: "Campo obligatorio",
										minlength 		: "Ingrese minimo 8 caracteres",
										maxlength 		: "Ingrese m&aacute;ximo 12 caracteres",
										sinespacios		: "No se permiten espacios en blanco",
										passwordCheck 	: "No coincide con el campo anterior"
								}
							}
		});
		
		$("#divMensajeExitoso").hide();
	});
</script>		
<div class="form-mi-perfil">

	<h1>Cambio de contrase�a</h1>
	<p class="page-info-wc" id="infoCambioContrasenia">
		Coloca a continuaci�n tu contrase�a actual y luego la nueva contrase�a.
	</p>
	<form class="form-horizontal" role="form"  id="formCambioContrasenia">
	<div class="panel panel-default"> 
		<div class="panel-body">
			<div class="row">
				
			   <div class="col-md-10 col-md-offset-1">
			   		<div class="form-group">
			   			<label for="contrasenaactual" class="col-sm-5">Contrase�a Actual</label>
			   			<div class="col-sm-7">
			   				<input type="password" name="contrasenaactual" id="contrasenaactual" class="form-control" value="" data-msg-required="El campo contase�a actual es obligatorio." title="">
			   				<div class="result"></div>
			   			</div>
			   		</div>
			   		<div class="form-group">
			   			<label for="contrasenanueva" class="col-sm-5">Contrase�a Nueva <span class="required"></span></label>
			   			<div class="col-sm-7">
			   				<input type="password" name="contrasenanueva" id="contrasenanueva" class="form-control" data-msg-required="El campo contrase�a nueva es obligatorio" value=""  title="">
			   				<div class="result"></div>
			   			</div>
			   		</div>
					<div class="form-group">
			   			<label for="confirmarcontrasena" class="col-sm-5">Confirmar contrase�a nueva <span class="required"></span></label>
			   			<div class="col-sm-7">
			   				<input type="password" name="confirmarcontrasena" id="confirmarcontrasena" class="form-control" data-msg-required="El campo confirmar contrase�a es obligatorio" value="" title="">
			   				<div class="result"></div>
			   			</div>
			   		</div>		
			   		
			   		<div style="font-size: 11px; font-style: italic; font-weight: bold; padding-bottom: 0px; padding-top: 0px;margin-bottom: 10px;">
		           		<span>(<label class="required"></label>) Campos obligatorios</span>
					</div>   
							
				   	<div class="form-group text-right">
				   		<div class="col-md-12">
				   			<button id="btn-continuar-cambiopwd" type="button" class="btn btn-primary"  onclick="cambiarContrasena();" >Continuar</button>
				   		</div>
				   	</div>
			   </div>  
			</div>	   
		</div>
	</div>
	</form>
	<div class="row" id="divMensajeExitoso">
		<div class="col-md-12">
	   		<div class="alert alert-success">
	   			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	   			Se ha cambiado tu contrase�a.
	   		</div>
	   </div>			   
   </div>
</div>