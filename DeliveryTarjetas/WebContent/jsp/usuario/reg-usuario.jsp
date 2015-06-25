<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-usuario">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Registro de usuario</span>
	</h3>
	
	<div id="container-principal"> 
	
	    <form id="form-registrousuario">
	        
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
    	
		loadModalCargando();
    	
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-registrousuario #idptipodocumento',false); 
    	
		callCargaControlParam('DELWEB_ESTADO','form-registrousuario #idpestado',false);
    	 
		cargarCombo('/DeliveryTarjetas/perfil.do', 'lstPerfil','idperfil',  ['idperfil','descripcion'], {form: 'form-registrousuario'});
		
    	cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', ['idcourier','rznsocial'], {form: 'form-registrousuario'});
    	
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
		
		closeModalCargando();
		
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
								
													var status 	= rsp.tx.statustx;
													var message = rsp.tx.messagetx;
				
													closeModalCargando();
													
													if(status == 0){
														loadModalMensaje("Enhorabuena", message, function(){
															$("#view-reg-usuarioweb").click();	
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