<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-usuariows">

	<h3 class="container-title">Servicios web - Registro de usuarios</h3>
	
	<div id="container-principal"> 
	    <form id="form-registrousuariows">
	        <div class="row">
	            <div class="col-md-12">
					
					<div class="form-group" id="idperfil-div">
	                    <label for="idperfil" class="col-sm-5 col-sm-offset-1 control-label required">Perfil </label>
	                    <div class="col-sm-5">
	                    	<select class="form-control" id="cboperfil" name="cboperfil"> 
						 		<option value="{debe ir el idperfil">Nombre perfil</option>                       	
	                   		</select>
	                   		<input type="hidden" class="form-control" id="idperfil" name="idperfil" >
	                    </div>
	                </div>
	                
	                <div class="form-group" id="codusuario-div">
	                    <label for="codusuario" class="col-sm-5 col-sm-offset-1 control-label required">Código de usuario </label>
	                    <div class="col-sm-5">
	                        <input type="text" class="form-control" id="codusuario" name="codusuario" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="contrasena-div">
	                    <label for="contrasena" class="col-sm-5 col-sm-offset-1 control-label required">Contraseña </label>
	                    <div class="col-sm-5">
	                        <input type="password" class="form-control" id="contrasena" name="contrasena" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="estado-div">
	                    <label for="idpestado" class="col-sm-5 col-sm-offset-1 control-label required">Estado </label>
	                    <div class="col-sm-5">
	                        <select class="form-control" id="idpestado" name="idpestado">     
	                        	<option value="{debe ir id del estado}">ESTADO</option>                   	
	                        </select>
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="comentarios-div">
	                    <label for="comentarios" class="col-sm-5 col-sm-offset-1 control-label">Comentarios </label>
	                    <div class="col-sm-5">
	                    	<textarea class="form-control" id="comentarios" name="comentarios" maxlength="300"  wrap="hard" style="max-width: 100%; max-height: 50px;" ></textarea>
	                    </div>
	                </div>
	               
	                <div class="form-group" style="margin-bottom: 0px; font-size: 11px; font-style: italic; font-weight: bold;">
	                	<label for="*" class="col-sm-5 col-sm-offset-1 control-label">
	                		<span>(<label class="required"></label>) Campos obligatorios</span>
	                	</label>                	
	                </div>
	                
				</div>
	            
	        </div>        
	        <div class="row">
		        <div class="col-md-12">
		        
		            <div class="col-sm-10  col-sm-offset-1">
		                <div class="form-group">
		                	<label class="col-sm-5 col-sm-offset-1 control-label" style="padding: 0px;">
		                	</label>
		                	<div class="col-sm-6 pull-right" style="text-align: right; padding: 0px;">
			                    <button type="button" 
			                    		class="btn btn-default"
			                    		onclick="javascript:limpiarFormulario('form-registrousuariows');">
			                    			Limpiar
			                    </button>
			                    
			                    <button id="btnRegistrar"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		onclick="javascript:registrarUsuarioWS();">
			                    			Registrar
			                    </button>
		                    </div>
		                </div>
		            </div>
		            
		        </div>
	        </div>
	    </form>
    </div>
    
    <div id="container-final" style="margin-top: 20px; display: none;">
    	<div>
    		<p class="page-info-wc"> Felicitaciones, ha registrado satisfactoriamente un Usuario de Servicios Web.</p>    		
    		<p> A continuación le hacemos entrega del password encriptado</p>
    		<p> &nbsp; </p>
    	</div>
    	<div class="row">
            <div class="col-md-12">
            	 <div class="form-group" id="codusuario-final-div">
                    <label for="codusuario-final" class="col-sm-5 col-sm-offset-1 control-label">Código de usuario </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="codusuario-final" name="codusuario-final" maxlength="200">
                    </div>
                </div>
                
                <div class="form-group" id="contrasena-final-div">
                    <label for="contrasena-final" class="col-sm-5 col-sm-offset-1 control-label">Contraseña </label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="contrasena-final" name="contrasena-final" maxlength="200">
                    </div>
                </div>                
            </div>
		</div>
		
		<div class="row">
	        <div class="col-md-12">
	        
	            <div class="col-sm-10  col-sm-offset-1">
	                <div class="form-group">
	                	<label class="col-sm-5 col-sm-offset-1 control-label" style="padding: 0px;">
	                	</label>
	                	<div class="col-sm-6 pull-right" style="text-align: right; padding: 0px;">
		                    <button id="btnRegistrar"
		                    		type="button" 
		                    		class="btn btn-primary" 
		                    		onclick="javascript:registrarNvoUsuarioWS();">
		                    			Registrar nuevo usuario
		                    </button>
	                    </div>
	                </div>
	            </div>
	            
	        </div>
        </div>	        
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>
	
    $().ready(function(){
		
    	callCargaControlParam('PARAM_ESTADOS','form-registrousuariows #estado');
    	
		var paramLstPerfil	= new Object();
    	
    	$.ajax({
			type 		: "POST",
			url 		: "/DeliveryTarjetas/usuario.do"+"?method=lstPerfil",
			cache 		: false ,
			dataType	: "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			async 		: false,
			data 		: paramLstPerfil,
			success 	: function(rspLstPerfil){
			
								var status 	= rspLstPerfil.statustx;
								var message = rspLstPerfil.messagetx;

								if(status == 0){													
									if(rspLstPerfil.lstPerfil!= undefined && rspLstPerfil.lstPerfil.lenght > 0){
										var lstPerfil = rspLstPerfil.lstPerfil;
										
										for(var i=0; i<lstPerfil.length; i++){											
												var opcion = '<option value="'+lstPerfil[i].idperfil+'" >'+lstPerfil[i].descripcion+'</option>' ;
												$("#form-registrousuariows #cboperfil").append(opcion);										
										}
										
										$("#form-registrousuariows #cboperfil").val("");
										$("#form-registrousuariows #cboperfil").attr("disabled", true);
										$("#form-registrousuariows #idperfil"). val($("#form-registrousuariows #idperfil"));
									}
								}
			},						
			error: function (rsp, xhr, ajaxOptions, thrownError) {
				closeModalCargando();
				loadModalMensaje("Error","ERROR CARGANDO PERFILES DISPONIBLES PARA LOS USUARIOS",null);								
			}			
		});	
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-registrousuariows").validate({
			rules : {

				codusuario 			: {				required 	: true,
													minlength 	: 5 },
				
				contrasena 			: {				required 	: true,
													minlength 	: 5 }
			},
			messages : {
				codusuario 			: {				required 	: "Debes ingresar el código de usuario",
													minlength 	: "Ingresar un mínimo de 5 carácteres"},
			
				contrasena 			: {				required 	: "Debes ingresar la contraseña para el código de usuario",
													minlength 	: "Ingresar un mínimo de 5 carácteres"},																
			}
		});	
		
	});
    
    function registrarNvoUsuarioWS(){
    	$("#view-reg-usuariows").click();		
    }
    
	function registrarUsuarioWS(){

		var $inputs = $('#form-registrousuariows :input');

		$inputs.each(function() {
			if(this.id!=""){
				if($("#form-registrousuariows #"+ this.id).valid())
					$(".result", $("#"+this.id).parent()).html("<i class='success'></i>");			
				else
					$(".result", $("#"+this.id).parent()).html("<i class='error'></i>");
			}
	    });

		activarChecksValidate("form-registrousuariows");
		
		if($("#form-registrousuariows").valid()){

 			var param 	= new Object();
 			param 		= $("#form-registrousuariows").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/courier.do"+"?method=mntUsuario",
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
														$("#codusuario-final").val("");
														$("#contrasena-final").val("");
														$("#codusuario-final").val(rsp.codusuario);
														$("#contrasena-final").val(rsp.contrasena);
														$("#codusuario-final").attr("disabled", true);
														$("#contrasena-final").attr("disabled", true);
														
														$("#container-principal").slideUp(1000,
																							function(){ $("#container-final").slideDown(1000); });
														
													}else
														loadModalMensaje("Atención",message,null);
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","ERROR REGISTRANDO COURIER",null);								
								}			
							});		    					    				
	  				},1000);    			    		
    	}    	
    }
    
</script>