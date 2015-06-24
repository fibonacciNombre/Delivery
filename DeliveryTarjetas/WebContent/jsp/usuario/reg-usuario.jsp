<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-colaboradorescourier">

	<h3 class="container-title">Registro de usuarios web</h3>

    <form id="form-registrocolaboradores">
        <div class="row">
            <div class="col-md-12">

                <div class="form-group" id="codbbva-div">
                    <label for=codbbva" class="col-sm-5 col-sm-offset-1 control-label required">Courier </label>
                    <div class="col-sm-5">
                        <select class="form-control" id="codbbva" name="codbbva"> 
						 	<option value="{debe ir el codbbva}">Nombre courier</option>                       	
                   		</select>
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="tipdocumento-div">
                    <label for="tipdocumento" class="col-sm-5 col-sm-offset-1 control-label required">Tipo de documento </label>
                    <div class="col-sm-5">
                        <select class="form-control" id="tipdocumento" name="tipdocumento"> 
						 	<option value="{debe ir id de tipo de documento}">DNI</option>                       	
                   		</select>
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="nrodocumento-div">
                    <label for="nrodocumento" class="col-sm-5 col-sm-offset-1 control-label required">Nro. documento </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="nombres-div">
                    <label for="nombres" class="col-sm-5 col-sm-offset-1 control-label required">Nombres </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="nombres" name="nombres" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="apepaterno-div">
                    <label for="apepaterno" class="col-sm-5 col-sm-offset-1 control-label required">Apellido paterno </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="apepaterno" name="apepaterno" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="apematerno-div">
                    <label for="apematerno" class="col-sm-5 col-sm-offset-1 control-label required">Apellido materno </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="apematerno" name="apematerno" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                                
                <div class="form-group" id="nummovil-div">
                    <label for="nummovil" class="col-sm-5 col-sm-offset-1 control-label required">Teléfono móvil </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="nummovil" name="nummovil" maxlength="9">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="email-div">
                    <label for="email" class="col-sm-5 col-sm-offset-1 control-label required">Correo electrónico </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="email"  name="email" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="estado-div">
                    <label for="estado" class="col-sm-5 col-sm-offset-1 control-label required">Estado </label>
                    <div class="col-sm-5">
                        <select class="form-control" id="estado" name="estado">  
                        	<option value="{debe ir id del estado}">ESTADO</option>                      	
                        </select>
                        <div class="result"></div>
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
		                    		onclick="javascript:limpiarFormulario('form-registrocolaboradores');">
		                    			Limpiar
		                    </button>
		                    
		                    <button id="btnRegistrar"
		                    		type="button" 
		                    		class="btn btn-primary" 
		                    		onclick="javascript:registrarColaboradorxCourier();">
		                    			Registrar
		                    </button>
	                    </div>
	                </div>
	            </div>
	            
	        </div>
        </div>
        
    </form>
    
</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>
	
    $().ready(function(){
    	
		callCargaControlParam('PARAM_TIPODOCUMENTO','form-registrocolaboradores #tipdocumento');
    	
    	callCargaControlParam('PARAM_ESTADOS','form-registrocolaboradores #estado');
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-registrocolaboradores").validate({
			rules : {

				codbbva 			: {				required 	: true },
				
				tipdocumento 		: {				required 	: true },
				
				nrodocumento 		: {				required 	: true,
													digits 	 	: true,
													minlength 	: 8},
				
				nombres		 		: {				required 	: true },
				
				apepaterno	 		: {				required 	: true },
				
				apematerno	 		: {				required 	: true },
				
				nummovil 			: {				required 	: true,
													digits 	 	: true,
													minlength 	: 9 },
													
				email 				: {				required 	: true,
													email		: true},
				
				estado 				: {				required 	: true }
			},
			messages : {
				codbbva 			: {				required 	: "Debes seleccionar un código BBVA del Courier" },
				
				tipdocumento 		: {				required 	: "Debes seleccionar un tipo de documento" },
				
				nrodocumento 		: {				required 	: "Debes ingresar un nro. de documento",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 8 dígitos"},
				
				nombres 			: {				required 	: "Debes ingresar el nombre" },
				
				apepaterno 			: {				required 	: "Debes ingresar el apellido paterno" },
				
				apematerno 			: {				required 	: "Debes ingresar el apellido materno" },
				
				nummovil 			: {				required 	: "Debes ingresar un teléfono móvil",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 9 dígitos" },
													
				email 				: {
													required 	: "Debes ingresar un email",
													email		: "Debes ingresar formato de email válido"},
													
				estado 				: {				required 	: "Debes seleccionar un estado para el colaborador" }
			}
		});	
		
	});
    
	

	function registrarColaboradorxCourier(){

		var $inputs = $('#form-registrocolaboradores :input');

		$inputs.each(function() {
			if(this.id!=""){
				if($("#form-registrocolaboradores #"+ this.id).valid())
					$(".result", $("#"+this.id).parent()).html("<i class='success'></i>");			
				else
					$(".result", $("#"+this.id).parent()).html("<i class='error'></i>");
			}
	    });

		activarChecksValidate("form-registrocolaboradores");
		
		if($("#form-registrocolaboradores").valid()){

 			var param 	= new Object();
 			param 		= $("#form-registrocolaboradores").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/courier.do"+"?method=mntColaborador",
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
														loadModalMensaje("Felicitaciones", message, function(){
															$("#view-reg-colaborador").click();	
														});																										
													}else
														loadModalMensaje("Atención",message,null);
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","ERROR REGISTRANDO AL COLABORADOR DEL COURIER",null);								
								}			
							});		    					    				
	  				},1000);    			    		
    	}    	
    }
    
</script>