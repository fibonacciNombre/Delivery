<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div id="registro-oficina-gerente">
<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Registro de subgerente</span>
	</h3>
	
	<div id="container-principal"> 
	
	    <form id="form-registrousuario">
	        <input type="hidden" id="idoficina" name="idoficina" value="0"/>
	        <input type="hidden" id="ubigeo" name="ubigeo" value=""/>
	        
			<div class="row">	            
				<div class="col-md-6">
					<div class="form-group" id="departamento-div">
				        <label for="departamento" class="col-md-12 control-label required">Departamento </label>
				        <div class="col-md-12">
				        	<select class="form-control" id="departamento" name="departamento">                       	
				       		</select>
				       		<div class="result"></div>
				        </div>
				    </div>
			            
					<div class="form-group" id="provincia-div">
					    <label for="provincia" class="col-md-12 control-label">Provincia </label>
					    <div class="col-md-12">
					        <select class="form-control" id="provincia" name="provincia">                        	
					   		</select>
					        <div class="result"></div>
					    </div>
					</div>
			       
					<div class="form-group" id="distrito-div">
					    <label for="distrito" class="col-md-12 control-label required">Distrito </label>
					    <div class="col-md-12">
					        <select class="form-control" id="distrito" name="distrito">                      	
					   		</select>
					        <div class="result"></div>
					    </div>
					</div>									
			        
			        <div class="form-group" id="oficina-div">
					    <label for="oficina" class="col-md-12 control-label required">Oficina </label>
					    <div class="col-md-12">
					        <select class="form-control" id="oficina" name="oficina">                      	
					   		</select>
					        <div class="result"></div>
					    </div>
					</div>										
			             
					<div class="form-group" id="nombres-div">
					    <label for="nombre" class="col-md-12 control-label required">Nombres </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="nombre" name="nombre" maxlength="200">
					        <div class="result"></div>
					    </div>
					</div>									
				</div>
			     
			   	<div class="col-md-6">
			   		<div class="form-group" id="apepaterno-div">
					    <label for="apellidopaterno" class="col-md-12 control-label required">Apellido paterno </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="apellidopaterno" name="apellidopaterno" maxlength="200">
					        <div class="result"></div>
					    </div>
					</div>
					
					<div class="form-group" id="apematerno-div">
					    <label for="apellidomaterno" class="col-md-12 control-label required">Apellido materno </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="apellidomaterno" name="apellidomaterno" maxlength="200">
					        <div class="result"></div>
					    </div>
					</div>
										
					<div class="form-group" id="correo-div">
					    <label for="correo" class="col-md-12 control-label required">Correo electrónico </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="correo"  name="correo" maxlength="200">
					        <div class="result"></div>
					    </div>
					</div>
					
					<div class="form-group" id="estado-div">
					    <label for="estado" class="col-md-12 control-label required">Estado </label>
					    <div class="col-md-12">
					        <select class="form-control" id="estado" name="estado">              	
					        </select>
					        <div class="result"></div>
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
    	
		loadModalCargando();				 
		
		callCargaControlParam('DELWEB_ESTADO','form-registrousuario #estado',false);
		    
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-registrousuario").validate({
			rules : {

				departamento 		: {				required 	: true },
				
				provincia 			: {				required 	: true },
				
				distrito 			: {				required 	: true },								
				
				oficina	 			: {				required 	: true },
								
				nombre		 		: {				required 	: true },
				
				apellidopaterno	 	: {				required 	: true },
				
				apellidomaterno	 	: {				required 	: true },
				
				correo 				: {				required 	: true,
													email		: true}																
			},
			messages : {
				departamento 		: {				required 	: "Debes seleccionar un departamento" },
				
				provincia 			: {				required 	: "Debes seleccionar un provincia" },
				
				distrito 			: {				required 	: "Debes seleccionar un distrito" },				
				
				oficina 			: {				required 	: "Debes seleccionar la oficina" },
								
				nombre 				: {				required 	: "Debes ingresar el nombre" },
				
				apellidopaterno 	: {				required 	: "Debes ingresar el apellido paterno" },
				
				apellidomaterno 	: {				required 	: "Debes ingresar el apellido materno" },
				
				correo 				: {
													required 	: "Debes ingresar un correo electrónico",
													email		: "Debes ingresar formato de correo electrónico válido"},
													
				estado 				: {				required 	: "Debes seleccionar un estado para el colaborador" }
			}
		});	
		
		cargarDepartamento();
		
		closeModalCargando();
		
		$("#form-registrousuario #distrito").change(function () 
		{																	
			var ofic = $('#form-registrousuario #oficina');
			ofic.empty();
			
            var opcion = $(this).find('option:selected').val();
            if (opcion != 0) {
            	var param = new Object();
            	param.ubigeo = opcion;
            	param.codoficina = 0;
            	
            	$.ajax({
        			type 		: "POST",
        			url 		: "/DeliveryTarjetas/usuario.do"+"?method=lstOficinas",
        			cache 		: false,
        			async 		: false,
        			dataType 	: 'json',
        			contentType : "application/x-www-form-urlencoded;charset=utf-8",
        			data		: param,
        			success 	: function(rsp) {
        								var status 	= rsp.tx.statustx;
        								var message = rsp.tx.messagetx;
        								
        								if(status==0){
        									var combo = $('#form-registrousuario #oficina');
        									combo.empty();

        									combo.append('<option value="0">' + 'Seleccionar' + '</option>');
        									var listaOpciones = rsp.lst;
        									for ( var i = 0; i < listaOpciones.length; i++) { 
        										var opcion = '<option value="'+listaOpciones[i]['idoficina']+'" >'
        												+ listaOpciones[i]['oficina'] + '</option>';
        										combo.append(opcion); 
        									}									
        								}
        			
        			},
        			error : function(xhr, ajaxOptions, thrownError) {}
        		});
            }	
        });
		
		$("#form-registrousuario #provincia").change(function () 
		{																	
			var dist = $('#form-registrousuario #distrito');
			dist.empty();
			
			var ofic = $('#form-registrousuario #oficina');
			ofic.empty();
			
               var opcion = $(this).find('option:selected').val();
               if (opcion != 0) {
               	var param = new Object();
               	param.ubigeo = opcion;
               	
               	$.ajax({
           			type 		: "POST",
           			url 		: "/DeliveryTarjetas/comun.do"+"?method=lstDistritos",
           			cache 		: false,
           			async 		: false,
           			dataType 	: 'json',
           			contentType : "application/x-www-form-urlencoded;charset=utf-8",
           			data		: param,
           			success 	: function(rsp) {
           								var status 	= rsp.tx.statustx;
           								var message = rsp.tx.messagetx;
           								
           								if(status==0){
           									var combo = $('#form-registrousuario #distrito');
           									combo.empty();

           									combo.append('<option value="0">' + 'Seleccionar' + '</option>');
           									var listaOpciones = rsp.lst;
           									for ( var i = 0; i < listaOpciones.length; i++) { 
           										var opcion = '<option value="'+listaOpciones[i]['ubigeo']+'" >'
           												+ listaOpciones[i]['distrito'] + '</option>';
           										combo.append(opcion); 
           									}									
           								}
           			
           			},
           			error : function(xhr, ajaxOptions, thrownError) {}
           		});
               }	
           });
		
		$("#form-registrousuario #departamento").change(function () {										
			var prov = $('#form-registrousuario #provincia');
			prov.empty();
			
			var dist = $('#form-registrousuario #distrito');
			dist.empty();
			
			var ofic = $('#form-registrousuario #oficina');
			ofic.empty();
			
               var opcion = $(this).find('option:selected').val();
               if (opcion != 0) {
               	var param = new Object();
               	param.ubigeo = opcion;
               	
               	$.ajax({
           			type 		: "POST",
           			url 		: "/DeliveryTarjetas/comun.do"+"?method=lstProvincias",
           			cache 		: false,
           			async 		: false,
           			dataType 	: 'json',
           			contentType : "application/x-www-form-urlencoded;charset=utf-8",
           			data		: param,
           			success 	: function(rsp) {
           								var status 	= rsp.tx.statustx;
           								var message = rsp.tx.messagetx;
           								
           								if(status==0){
           									var combo = $('#form-registrousuario #provincia');
           									combo.empty();

           									combo.append('<option value="0">' + 'Seleccionar' + '</option>');
           									var listaOpciones = rsp.lst;
           									for ( var i = 0; i < listaOpciones.length; i++) { 
           										var opcion = '<option value="'+listaOpciones[i]['ubigeo']+'" >'
           												+ listaOpciones[i]['provincia'] + '</option>';
           										combo.append(opcion); 
           									}									
           								}
           			
           			},
           			error : function(xhr, ajaxOptions, thrownError) {}
           		});
               }	                	                
           });		
	});
    
	function registrarUsuario(){
		
		if($("#form-registrousuario").valid()){
			
			$("#form-registrousuario #ubigeo").val($("#form-registrousuario #distrito").val());
			$("#form-registrousuario #idoficina").val($("#form-registrousuario #oficina").val());
			
 			var param 	= new Object();
 			param 		= $("#form-registrousuario").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntSubgerente",
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
															$("#view-reg-subgerente").click();	
														});																										
													}else
														loadModalMensaje("Atención",message,null);													
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","ERROR REGISTRANDO USUARIO",null);								
								}			
							});		    					    				
	  				},1000);    			    		
    	}    	
    }
	
	function cargarDepartamento(){
		var prov = $('#form-registrousuario #provincia');
		prov.empty();
		
		var dist = $('#form-registrousuario #distrito');
		dist.empty();	
		
		var ofic = $('#form-registrousuario #oficina');
		ofic.empty();
		
		$.ajax({
			type 		: "POST",
			url 		: "/DeliveryTarjetas/comun.do"+"?method=lstDepartamentos",
			cache 		: false,
			async 		: false,
			dataType 	: 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",			
			success 	: function(rsp) {
								var status 	= rsp.tx.statustx;
								var message = rsp.tx.messagetx;
								
								if(status==0){
									var combo = $('#form-registrousuario #departamento');
									combo.empty();

									combo.append('<option value="0">' + 'Seleccionar' + '</option>');
									var listaOpciones = rsp.lst;
									for ( var i = 0; i < listaOpciones.length; i++) { 
										var opcion = '<option value="'+listaOpciones[i]['ubigeo']+'" >'
												+ listaOpciones[i]['departamento'] + '</option>';
										combo.append(opcion); 
									}									
								}	
			},
			error : function(xhr, ajaxOptions, thrownError) {}
		});
	}
    
</script>