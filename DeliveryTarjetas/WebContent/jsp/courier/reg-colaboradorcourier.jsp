<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-colaboradorescourier">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Registro de colaboradores por courier</span>
	</h3>
	
    <form id="form-registrocolaboradores">
    	<input type="hidden" id="indaccion" name="indaccion" value="0"/>
    	
        <%@include file="/jsp/courier/form-colaboradorcourier.jsp" %>
        
        <div class="row">
        
	        <div class="col-md-12">	        
	            <div class="col-md-12">
	                <div class="form-group">
	                	<label class="col-md-6 control-label" style="padding: 0px;">
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

<script>
	
    $().ready(function(){
    	
    	loadModalCargando();
    	
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-registrocolaboradores #idptipodocumento',false); 
    	
		callCargaControlParam('DELWEB_ESTADO','form-registrocolaboradores #idpestado',false);
    	 
    	cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','cbocourier', ['idcourier','rznsocial'], {form: 'form-registrocolaboradores'});
		
    	if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!=""){
    		$("#form-registrocolaboradores #cbocourier").val($("#form-datos-usuario #idcourier").val());
    		$("#form-registrocolaboradores #idcourier").val($("#form-datos-usuario #idcourier").val());    		
    		$("#form-registrocolaboradores #cbocourier").attr("disabled","disabled");
    	}
    	
		$("#form-registrocolaboradores").validate({
			rules : {

				cbocourier 			: {				required 	: true },
				
				idptipodocumento 	: {				required 	: true },
				
				nrodocumento 		: {				required 	: true,
													digits 	 	: true,
													minlength 	: 8},
				
				nombres		 		: {				required 	: true },
				
				apepaterno	 		: {				required 	: true },
				
				apematerno	 		: {				required 	: true },
				
				telfmovil 			: {				required 	: true,
													digits 	 	: true,
													minlength 	: 9 },
													
				correo 				: {				required 	: true,
													email		: true },
													
				idpestado 			: {				required 	: true }
			},
			messages : {
				cbocourier 			: {				required 	: "Debes seleccionar un courier" },
				
				idptipodocumento 	: {				required 	: "Debes seleccionar un tipo de documento" },
				
				nrodocumento 		: {				required 	: "Debes ingresar un nro. de documento",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 8 dígitos"},
				
				nombres 			: {				required 	: "Debes ingresar el nombre" },
				
				apepaterno 			: {				required 	: "Debes ingresar el apellido paterno" },
				
				apematerno 			: {				required 	: "Debes ingresar el apellido materno" },
				
				telfmovil 			: {				required 	: "Debes ingresar un teléfono móvil",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 9 dígitos" },
													
				correo 				: {
													required 	: "Debes ingresar un email",
													email		: "Debes ingresar formato de email válido"},
				
				idpestado 			: {				required 	: "Debes seleccionar un estado" }													
			}
		});	
		
		closeModalCargando();
	});
    
	function registrarColaboradorxCourier(){

		if($("#form-registrocolaboradores").valid()){
			
			$("#form-registrocolaboradores #cbocourier").attr("disabled",false);
			$("#form-registrocolaboradores #idcourier").val($("#form-registrocolaboradores #cbocourier").val());
			$("#form-registrocolaboradores #cbocourier").attr("disabled",true);
			
 			var param 	= new Object();
 			param 		= $("#form-registrocolaboradores").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/tercero.do"+"?method=mntTercero",
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
															$("#view-reg-colaborador").click();	
														});																										
													}else
														loadModalMensaje("Atención",message,null);
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","Se presentaron problemas al registrar al colaborador. <br> Por favor intentelo en unos minutos.",null);								
								}			
							});		    					    				
	  				},1000);    			    		
    	}    	
    }
    
</script>