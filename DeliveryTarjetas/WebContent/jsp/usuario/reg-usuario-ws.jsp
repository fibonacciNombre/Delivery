<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-usuariows">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Servicios web - Registro de usuarios</span>
	</h3>
	
	<div id="container-principal"> 
	    <form id="form-registrousuariows">
	        
	        <%@include file="/jsp/usuario/form-usuario-ws.jsp" %>
	               
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
                        <input type="text" class="form-control" id="contrasena-final" name="contrasena-final" maxlength="200">
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

<script>
	
    $().ready(function(){
		
		loadModalCargando();
    	
		callCargaControlParam('DELWEB_ESTADO','form-registrousuariows #idpestado',false);
    	 
    	cargarCombo('/DeliveryTarjetas/perfil.do', 'lstPerfil','cboperfil', ['idperfil','descripcion'], {form: 'form-registrousuariows'});
		
    	$("#form-registrousuariows #cboperfil").val(CTE_INIT_IDROL_ADMIN_WS);
    	
    	$("#form-registrousuariows #ideperfil").val(CTE_INIT_IDROL_ADMIN_WS);
    	
    	$("#form-registrousuariows #cboperfil").attr("disabled","disabled");
    	
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
													minlength 	: "Debes ingresar un mínimo de 5 carácteres"},
			
				contrasena 			: {				required 	: "Debes ingresar la contraseña para el código de usuario",
													minlength 	: "Debes ingresar un mínimo de 5 carácteres"}																
			}
		});	
		
		closeModalCargando();
		
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
														$("#codusuario-final").val("");
														$("#contrasena-final").val("");
														$("#codusuario-final").val(rsp.usuario.codusuario);
														$("#contrasena-final").val(rsp.usuario.contrasena);
														$("#codusuario-final").attr("disabled", true);
														$("#contrasena-final").attr("disabled", true);
														
														$("#container-principal").slideUp(1000,
																							function(){ $("#container-final").slideDown(1000); });
														
													}else
														loadModalMensaje("Atención",message,null);
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","ERROR REGISTRANDO USUARIO DE SERVICIOS WEB",null);								
								}			
							});		    					    				
	  				},1000);    			    		
    	}    	
    }
    
</script>