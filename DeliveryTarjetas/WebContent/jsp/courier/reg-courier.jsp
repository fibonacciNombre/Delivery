<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-courier">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Registro de courier</span>
	</h3>

    <form id="form-registrocourier">
		
		<%@include file="/jsp/courier/form-courier.jsp" %>
		        
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

<script>
	
    $().ready(function(){
    	
    	loadModalCargando();
    	
    	callCargaControlParam('DELWEB_TIPODOCUMENTO','form-registrocourier #idptipodocumento',false);
    	
    	callCargaControlParam('DELWEB_ESTADO','form-registrocourier #idpestado',false);
    	
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
													email		: true },
													
				idpestado			: {				required 	: true }
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
													email		: "Debes ingresar formato de correo electrónico válido"},
													
				idpestado 			: {				required 	: "Debes seleccionar un estado" }
			}
		});	
		
		closeModalCargando();
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
								
													var status 	= rsp.tx.statustx;
													var message = rsp.tx.messagetx;
				
													closeModalCargando();
													
													if(status == 0){
														loadModalMensaje("Enhorabuena", message, function(){
															$("#view-reg-courier").click();	
														});																										
													}else
														loadModalMensaje("Atención",message,null);
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","Se presentaron problemas al registrar el courier. <br> Por favor intentelo en unos minutos.",null);								
								}			
							});		    					    				
	  				},1000);    			    		
    	}    	
    }
    
</script>