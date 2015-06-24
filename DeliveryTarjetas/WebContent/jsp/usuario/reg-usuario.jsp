<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-usuario">

	<h3 class="container-title">Registro de usuario</h3>
	
	<div id="container-principal"> 
	
	    <form id="form-registrousuario">
	        
	        <div class="row">	 
	               
	        	<div class="col-md-6">
					<div class="form-group" id="idperfil-div">
	                    <label for="idperfil" class="col-md-6 control-label required">Perfil </label>
	                    <div class="col-md-12">
	                    	<select class="form-control" id="idperfil" name="idperfil"> 
						 		<option value="{debe ir el idperfil">Nombre perfil</option>                       	
	                   		</select>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="codbbva-div">
	                    <label for=codbbva" class="col-md-6 control-label">Courier </label>
	                    <div class="col-md-12">
	                        <select class="form-control" id="codbbva" name="codbbva"> 
							 	<option value="{debe ir el codbbva}">Nombre courier</option>                       	
	                   		</select>
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="idptipodocumento-div">
	                    <label for="idptipodocumento" class="col-md-6 control-label required">Tipo de documento </label>
	                    <div class="col-md-12">
	                        <select class="form-control" id="idptipodocumento" name="idptipodocumento"> 
							 	<option value="{debe ir id de tipo de documento}">DNI</option>                       	
	                   		</select>
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="nrodocumento-div">
	                    <label for="nrodocumento" class="col-md-6  control-label required">Nro. documento </label>
	                    <div class="col-md-12">
	                        <input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="nombres-div">
	                    <label for="nombres" class="col-md-6 control-label required">Nombres </label>
	                    <div class="col-md-12">
	                        <input type="text" class="form-control" id="nombres" name="nombres" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="apepaterno-div">
	                    <label for="apepaterno" class="col-md-6 control-label required">Apellido paterno </label>
	                    <div class="col-md-12">
	                        <input type="text" class="form-control" id="apepaterno" name="apepaterno" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="apematerno-div">
	                    <label for="apematerno" class="col-md-6 control-label required">Apellido materno </label>
	                    <div class="col-md-12">
	                        <input type="text" class="form-control" id="apematerno" name="apematerno" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
				</div>
	        
	        	<div class="col-md-6">
	        		<div class="form-group" id="codusuario-div">
	                    <label for="codusuario" class="col-md-6  control-label required">Código de usuario </label>
	                    <div class="col-md-12">
	                        <input type="text" class="form-control" id="codusuario" name="codusuario" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="contrasena-div">
	                    <label for="contrasena" class="col-md-6 control-label required">Contraseña </label>
	                    <div class="col-md-12">
	                        <input type="password" class="form-control" id="contrasena" name="contrasena" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	        		<div class="form-group" id="telfmovil-div">
	                    <label for="telfmovil" class="col-md-6 control-label required">Teléfono móvil </label>
	                    <div class="col-md-12">
	                        <input type="text" class="form-control" id="telfmovil" name="telfmovil" maxlength="9">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="correo-div">
	                    <label for="correo" class="col-md-6 control-label required">Correo electrónico </label>
	                    <div class="col-md-12">
	                        <input type="text" class="form-control" id="correo"  name="correo" maxlength="200">
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="estado-div">
	                    <label for="idpestado" class="col-md-6 control-label required">Estado </label>
	                    <div class="col-md-12">
	                        <select class="form-control" id="idpestado" name="idpestado">     
	                        	<option value="{debe ir id del estado}">ESTADO</option>                   	
	                        </select>
	                        <div class="result"></div>
	                    </div>
	                </div>
	                
	                <div class="form-group" id="comentarios-div">
	                    <label for="comentarios" class="col-md-6 control-label">Comentarios </label>
	                    <div class="col-md-12">
	                    	<textarea class="form-control" id="comentarios" name="comentarios" maxlength="300"  wrap="hard" style="min-width: 100%; max-width: 100%; min-height: 50px; max-height: 50px;" ></textarea>
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
			                    <button type="button" 
			                    		class="btn btn-default"
			                    		onclick="javascript:limpiarFormulario('form-registrousuario');">
			                    			Limpiar
			                    </button>
			                    
			                    <button id="btnRegistrar"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		onclick="javascript:registrarUsuario();">
			                    			Registrar
			                    </button>
		                    </div>
		                </div>
		            </div>		            
		        </div>
		        
	        </div>
	        
	    </form>
    </div>

</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>
	
    $().ready(function(){
    	
    	callCargaControlParam('PARAM_ESTADOS','form-registrousuario #estado');
    	
    	loadPerfiles("#form-registrousuario","#cboperfil");
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-registrousuario").validate({
			rules : {

				idperfil 			: {				required 	: true },
				
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
				idperfil 			: {				required 	: "Debes seleccionar un perfil" },
				
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
    
	function registrarUsuario(){

		var $inputs = $('#form-registrousuario :input');

		$inputs.each(function() {
			if(this.id!=""){
				if($("#form-registrousuario #"+ this.id).valid())
					$(".result", $("#"+this.id).parent()).html("<i class='success'></i>");			
				else
					$(".result", $("#"+this.id).parent()).html("<i class='error'></i>");
			}
	    });

		activarChecksValidate("form-registrousuario");
		
		if($("#form-registrousuario").valid()){

 			var param 	= new Object();
 			param 		= $("#form-registrousuario").serializeArray();
 			
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
								
													var status 	= rsp.statustx;
													var message = rsp.messagetx;
				
													closeModalCargando();
													
													if(status == 0){														
														
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