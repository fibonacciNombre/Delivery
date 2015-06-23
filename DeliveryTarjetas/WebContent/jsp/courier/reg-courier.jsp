<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-courier">

	<h3 class="container-title">Registro de courier</h3>

    <form id="form-registrocourier">
        <div class="row">
            <div class="col-md-12">

                <div class="form-group" id="codbbva-div">
                    <label for="codbbva" class="col-sm-5 col-sm-offset-1 control-label required">Código Bbva </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id=codbbva name="codbbva" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="rznsocial-div">
                    <label for="rznsocial" class="col-sm-5 col-sm-offset-1 control-label required">Razón social </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="rznsocial" name="rznsocial" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="direccion-div">
                    <label for="direccion" class="col-sm-5 col-sm-offset-1 control-label required">Dirección completa </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="direccion" name="direccion" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="nummovil-div">
                    <label for="nummovil" class="col-sm-5 col-sm-offset-1 control-label required">Teléfono móvil </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control phone-group" id="nummovil" name="nummovil" maxlength="9">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="numlocal-div">
                    <label for="numlocal" class="col-sm-5 col-sm-offset-1 control-label required">Teléfono fijo </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control phone-group" id="numlocal"  name="numlocal" maxlength="9">
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
		                    		onclick="javascript:limpiarFormulario('form-registrocourier');">
		                    			Limpiar
		                    </button>
		                    
		                    <button id="btnRegistrar"
		                    		type="button" 
		                    		class="btn btn-primary" 
		                    		onclick="javascript:registrarCourier();">
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
    		
    	callCargaControlParam('PARAM_ESTADOS','form-registrocourier #estado');
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-registrocourier").validate({
			rules : {

				codbbva 			: {				required 	: true },
				
				rznsocial 			: {				required 	: true },
				
				direccion 			: {				required 	: true },
				
				nummovil 			: {				require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 9},
													
				numlocal 			: {				require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 6},
													
				email 				: {				required 	: true,
													email		: true}
			},
			messages : {
				codbbva 			: {				required 	: "Debes ingresar el código BBVA del Courier" },
				
				rznsocial 			: {				required 	: "Debes ingresar la razón social del Courier" },
				
				direccion 			: {				required 	: "Debes ingresar Dirección" },
				
				nummovil 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Sólo números",
													minlength 	: "Ingresar un mínimo de 9 dígitos" },
													
				numlocal 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Sólo números",
													minlength 	: "Ingresar un mínimo de 6 dígitos"},
													
				email 				: {
													required 	: "Ingresar Email",
													email		: "Ingresar formato de email válido"}
			}
		});	
		
	});
    
	

	function registrarCourier(){

		var $inputs = $('#form-registrocourier :input');

		$inputs.each(function() {
			if(this.id!=""){
				if($("#form-registrocourier #"+ this.id).valid())
					$(".result", $("#"+this.id).parent()).html("<i class='success'></i>");			
				else
					$(".result", $("#"+this.id).parent()).html("<i class='error'></i>");
			}
	    });

		activarChecksValidate("form-registrocourier");
		
		if($("#form-registrocourier").valid()){

 			var param 	= new Object();
 			param 		= $("#form-registrocourier").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/courier.do"+"?method=mntCourier",
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
															$("#view-reg-courier").click();	
														});																										
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