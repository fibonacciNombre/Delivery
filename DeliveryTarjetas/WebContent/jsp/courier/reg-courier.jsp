<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-courier">

	<h3 class="container-title">Registro de courier</h3>

    <form id="form-registrocourier">
        
        <div class="row">
        
            <div class="col-md-6">
                <div class="form-group" id="codbbva-div">
                    <label for="codbbva" class="col-md-6 control-label required">Código Bbva </label>
                    <div class="col-md-12">
                        <input type="text" class="form-control" id=codbbva name="codbbva" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="tipdocumento-div">
                    <label for="idptipodocumento" class="col-md-6 control-label required">Tipo de documento </label>
                    <div class="col-md-12">
                        <select class="form-control" id="idptipodocumento" name="idptipodocumento"> 
						 	<option value="{debe ir id de tipo de documento}">DNI</option>                       	
                   		</select>
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="nrodocumento-div">
                    <label for="nrodocumentocou" class="col-md-6 control-label required">Nro. documento </label>
                    <div class="col-md-12">
                        <input type="text" class="form-control" id="nrodocumentocou"  name="nrodocumentocou" maxlength="11">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="rznsocial-div">
                    <label for="rznsocial" class="col-md-6 control-label required">Razón social </label>
                    <div class="col-md-12">
                        <input type="text" class="form-control" id="rznsocial" name="rznsocial" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="direccion-div">
                    <label for="direccion" class="col-md-6 control-label required">Dirección completa </label>
                    <div class="col-md-12">
                        <input type="text" class="form-control" id="direccion" name="direccion" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
			</div>
			
		  	<div class="col-md-6">            
                <div class="form-group" id="telfmovil-div">
                    <label for="telfmovil" class="col-md-6 control-label required">Teléfono móvil </label>
                    <div class="col-md-12">
                        <input type="text" class="form-control phone-group" id="telfmovil" name="telfmovil" maxlength="9">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="telffijo-div">
                    <label for="telffijo" class="col-md-6 control-label required">Teléfono fijo </label>
                    <div class="col-md-12">
                        <input type="text" class="form-control phone-group" id="telffijo"  name="telffijo" maxlength="9">
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
                
                
                <div class="form-group" id="idpestado-div">
                    <label for="idpestado" class="col-md-6 control-label required">Estado </label>
                    <div class="col-md-12">
                        <select class="form-control" id="idpestado" name="idpestado">     
                        	<option value="{debe ir id del estado}">ESTADO</option>                   	
                        </select>
                        <div class="result"></div>
                    </div>
                </div>
            </div>
            
          	<div class="col-md-12">
                <div class="form-group" style="margin-bottom: 0px; font-size: 11px; font-style: italic; font-weight: bold;">
                	<label for="*" class="col-md-6 control-label">
                		<span>(<label class="required"></label>) Campos obligatorios</span>
                	</label>                	
                </div>                
			</div>
			            
        </div>    
            
        <div class="row">
        
	        <div class="col-md-12">	        
	            <div class="col-md-12">
	                <div class="form-group">
	                	<label class="col-md-6 control-label" style="padding: 0px;">
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
    		
    	callCargaControlParam('DELWEB_TIPODOCUMENTO','form-registrocourier #idptipodocumento');
    	callCargaControlParam('DELWEB_ESTADO','form-registrocourier #idpestado');
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-registrocourier").validate({
			rules : {

				codbbva 			: {				required 	: true },
				
				rznsocial 			: {				required 	: true },
				
				idptipodocumento 	: {				required 	: true },
				
				nrodocumentocou		: {				required	: true,
													digits 	 	: true,
													minlength 	: 11 },
													
				direccion 			: {				required 	: true },
				
				telfmovil 			: {				require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 9 },
													
				telffijo 			: {				require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 6 },
													
				correo 				: {				required 	: true,
													email		: true}
			},
			messages : {
				codbbva 			: {				required 	: "Debes ingresar el código BBVA" },
				
				rznsocial 			: {				required 	: "Debes ingresar la razón social" },
				
				idptipodocumento 	: {				required 	: "Debes seleccionar el tipo de documento" },
				
				nrodocumentocou		: {				required	: "Debes ingresar el nro. de documento",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Dedes ingresar un mínimo de 11 dígitos"},
													
				direccion 			: {				required 	: "Debes ingresar la dirección completa" },
				
				telfmovil 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 9 dígitos" },
													
				telffijo 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 6 dígitos"},
													
				correo 				: {
													required 	: "Debes ingresar correo electrónico",
													email		: "Debes ingresar formato de correo electrónico válido"}
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