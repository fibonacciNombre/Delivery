<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div id="registro-oficina">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Registro de oficina</span>
	</h3>
	
	<div id="container-principal"> 
	
	    <form id="form-registrousuario">
	        	        
	        <input type="hidden" id="indaccion" name="indaccion" value="0"/>
	        <input type="hidden" id="ubigeo" name="ubigeo" value="0"/>
	        
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
					
					<div class="form-group" id="codoficina-div">
					    <label for="codoficina" class="col-md-12 control-label required">Código oficina </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="codoficina"  name="codoficina" maxlength="6">
					        <div class="result"></div>
					    </div>
					</div>
			        
			        <div class="form-group" id="oficina-div">
					    <label for="oficina" class="col-md-12 control-label required">Oficina </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="oficina" name="oficina" maxlength="200">
					        <div class="result"></div>
					    </div>
					</div>
					
					<div class="form-group" id="categoria-div">
					    <label for="categoria" class="col-md-12 control-label">Categoria </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="categoria" name="categoria" maxlength="80">
					        <div class="result"></div>
					    </div>
					</div>
					
					<div class="form-group" id="direccion-div">
					    <label for="direccion" class="col-md-12 control-label required">Dirección </label>
					    <div class="col-md-12">
					        <textarea id="direccion" name="direccion" maxlength="400" rows="4" class="form-control"></textarea>
					        <div class="result"></div>
					    </div>
					</div>										
				</div>
			     
			   	<div class="col-md-6">
					<div class="form-group" id="horariolv-div">
					    <label for="horariolv" class="col-md-12  control-label required">Horario Lunes-Viernes </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="horariolv" name="horariolv" maxlength="80">
					        <div class="result"></div>
					    </div>
					</div>
					
					<div class="form-group" id="horarios-div">
					    <label for="horarios" class="col-md-12 control-label required">Horario Sábados </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="horarios" name="horarios" maxlength="80">		        
					        <div class="result"></div>
					    </div>
					</div>
					      
					<div class="form-group" id="horariodf-div">
					    <label for="horariodf" class="col-md-12 control-label required">Horario Domingo-Feriados </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="horariodf" name="horariodf" maxlength="80">
					        <div class="result"></div>
					    </div>
					</div>
					
					<div class="form-group" id="latitud-div">
					    <label for="latitudofi" class="col-md-12 control-label">Latitud </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="latitudofi"  name="latitudofi" maxlength="14">
					        <div class="result"></div>
					    </div>
					</div>
					
					<div class="form-group" id="longitud-div">
					    <label for="longitudofi" class="col-md-12 control-label">Longitud </label>
					    <div class="col-md-12">
					        <input type="text" class="form-control" id="longitudofi"  name="longitudofi" maxlength="14">
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
		    
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#form-registrousuario").validate({
			rules : {

				departamento 		: {				required 	: true },
				
				provincia 			: {				required 	: true },
				
				distrito 			: {				required 	: true },								
				
				codoficina	 		: {				required 	: true,
													digits 	 	: true,
													minlength 	: 1},
				
				oficina		 		: {				required 	: true },
				
				direccion	 		: {				required 	: true },
				
				horariolv	 		: {				required 	: true },
				
				horarios 			: {				required 	: true },
				
				horariodf 			: {				required 	: true }																
			},
			messages : {
				departamento 		: {				required 	: "Debes seleccionar un departamento" },
				
				provincia 			: {				required 	: "Debes seleccionar un provincia" },
				
				distrito 			: {				required 	: "Debes seleccionar un distrito" },
				
				codoficina 			: {				required 	: "Debes ingresar un código de oficina",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 1 dígitos"},
				
				oficina 			: {				required 	: "Debes ingresar el nombre de la oficina" },
				
				direccion 			: {				required 	: "Debes ingresar la dirección de la oficina" },
				
				horariolv 			: {				required 	: "Debes ingresar el horario de lunes a viernes" },
				
				horarios 			: {				required 	: "Debes ingresar el horario de sábados" },													

				horariodf 			: {				required 	: "Debes ingresar la horario de domingos y feriados"}
			}
		});	
		
		cargarDepartamento();
		
		closeModalCargando();
		
		$("#form-registrousuario #provincia").change(function () 
		{																	
			var dist = $('#form-registrousuario #distrito');
			dist.empty();
			
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
			
 			var param 	= new Object();
 			param 		= $("#form-registrousuario").serializeArray();
 			
 			loadModalCargando();

   			setTimeout(
 					  	function(){   			
				   			$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntOficina",
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
															$("#view-reg-oficina").click();	
														});																										
													}else
														loadModalMensaje("Atención",message,null);													
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","ERROR REGISTRANDO OFICINA",null);								
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