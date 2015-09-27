<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarGerente"
		class="modal fade"
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>				 
				<h3 class="modal-title">Editar Gerente</h3>
			</div>
			 
			<form id="form-mntgerente">
				
				<input type="hidden" id="indaccion" name="indaccion" value="1"/>
				
				<%@include file="/jsp/usuario/form-usuario-gerente.jsp" %>
				
				<div class="row">
	        
			        <div class="col-md-12">		        
			            <div class="col-md-12">
			                <div class="form-group">
			                	<label class="col-md-6  control-label" style="padding: 0px;">
			                	</label>
			                	<div class="col-sm-6 pull-right" style="text-align: right; padding: 0px;">
				                    
				                    <button type="button" 
				                    		class="btn btn-default"
				                    		onclick="javascript:renovarContrasena();">
				                    			Renovar contraseña
				                    </button>
				                    
				                    <button id="btnRegistrar"
				                    		type="button" 
				                    		class="btn btn-primary" 
				                    		onclick="javascript:actualizarGerente();">
				                    			Modificar
				                    </button>
			                    </div>
			                </div>
			            </div>		            
			        </div>
			        
		        </div>
	        
			</form>
		</div>

	</div>
</div>

<script>
	
    $().ready(function(){
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		jQuery.validator.addMethod("notEqual", function(value, element, param) {
			  return this.optional(element) || value !== param;
		}, "Please choose a value!");
		
		$("#form-mntgerente").validate({
			rules : {
				
				departamento		: {				required 	: true,
													notEqual	: "0" },
				
				provincia			: {				required 	: true,
													notEqual	: "0" },
				
				distrito			: {				required 	: true,
													notEqual	: "0" },
				
				oficina				: {				required 	: true,
													notEqual	: "0" },
				
				idptipodocumento 	: {				required 	: true },
				
				nrodocumento 		: {				required 	: true,
													digits 	 	: true,
													minlength 	: 8},
				
				nombres		 		: {				required 	: true },
				
				apepaterno	 		: {				required 	: true },
				
				apematerno	 		: {				required 	: true },
				
				codusuario 			: {				required 	: true,
													minlength 	: 5 },
				
				telfmovil 			: {				required 	: true,
													digits 	 	: true,
													minlength 	: 9 },
													
				correo 				: {				required 	: true,
													email		: true}
			},
			messages : {
				
				departamento	 	: {				required 	: "Debes seleccionar un departamento",
													notEqual	: "Debes seleccionar un departamento"},
				
				provincia		 	: {				required 	: "Debes seleccionar una provincia",
													notEqual 	: "Debes seleccionar una provincia" },
				
				distrito		 	: {				required 	: "Debes seleccionar un distrito",
													notEqual 	: "Debes seleccionar un distrito" },
				
				oficina 			: {				required 	: "Debes seleccionar una oficina",
													notEqual 	: "Debes seleccionar una oficina" },
				
				idptipodocumento 	: {				required 	: "Debes seleccionar un tipo de documento" },
				
				nrodocumento 		: {				required 	: "Debes ingresar un nro. de documento",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 8 dígitos"},
				
				nombres 			: {				required 	: "Debes ingresar el nombre" },
				
				apepaterno 			: {				required 	: "Debes ingresar el apellido paterno" },
				
				apematerno 			: {				required 	: "Debes ingresar el apellido materno" },
				
				codusuario 			: {				required 	: "Debes ingresar el código de usuario",
													minlength 	: "Debes ingresar un mínimo de 5 carácteres"},

				telfmovil 			: {				required 	: "Debes ingresar un teléfono móvil",
													digits 	 	: "Debes ingresar sólo números",
													minlength 	: "Debes ingresar un mínimo de 9 dígitos" },
													
				correo 				: {
													required 	: "Debes ingresar un correo electrónico",
													email		: "Debes ingresar formato de correo electrónico válido"},
													
				estado 				: {				required 	: "Debes seleccionar un estado para el colaborador" }
			}
		});	
		
		cargarDepartamentoMnt();
		
		initUbigueoMnt();
	});
    
	function actualizarGerente(){
		
		if($("#form-mntgerente").valid()){
			
			$("#form-mntgerente #ubigeo").val($("#form-mntgerente #distrito").val());
			$("#form-mntgerente #idoficina").val($("#form-mntgerente #oficina").val());
			
 			var param 	= new Object();
 			param 		= $("#form-mntgerente").serializeArray();
 			
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
															bsqGerente();
															$("#modalEditarGerente").modal('hide');
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
	
	function renovarContrasena(){
		
		loadModalCargando();
			
			var param				= new Object();
			param.codusuario		= $("#form-mntgerente #codusuario").val();
			
			setTimeout(
					  	function(){   			
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntContrasena",
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
													loadModalMensaje("Enhorabuena", "Se ha generado una contraseña temporal, el usuario tendrá que renovarla en su próxima autenticación al  sistema", function(){
														bsqUsuario();
														$("#modalEditarGerente").modal('hide');
													});																										
												}else
													loadModalMensaje("Atención",message,null);													
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR RENOVANDO LA CONTRASEÑA",null);								
							}			
						});		    					    				
  				},1000);    
		
	}
	
	function renovarContrasena(){
		
		loadModalCargando();
			
			var param				= new Object();
			param.codusuario		= $("#form-mntgerente #codusuario").val();
			
			setTimeout(
					  	function(){   			
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/usuario.do"+"?method=mntContrasena",
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
													loadModalMensaje("Enhorabuena", "Se ha generado una contraseña temporal, el usuario tendrá que renovarla en su próxima autenticación al  sistema", function(){
														bsqGerente();
														$("#modalEditarGerente").modal('hide');
													});																										
												}else
													loadModalMensaje("Atención",message,null);													
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR RENOVANDO LA CONTRASEÑA",null);								
							}			
						});		    					    				
  				},1000);    
		
	}
	
	function cargarDepartamentoMnt(){
		
		$('#form-mntgerente #provincia').empty();
		
		$('#form-mntgerente #distrito').empty();	
		
		$('#form-mntgerente #oficina').empty();
		
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
									var combo = $('#form-mntgerente #departamento');
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
	
	function initUbigueoMnt(){
		
		$("#form-mntgerente #distrito").change(function (){
		
			$('#form-mntgerente #oficina').empty();
			
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
        									var combo = $('#form-mntgerente #oficina');
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
				
		$("#form-mntgerente #provincia").change(function (){
			
			$('#form-mntgerente #distrito').empty();
			
			$('#form-mntgerente #oficina').empty();
			
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
           									var combo = $('#form-mntgerente #distrito');
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
				
		$("#form-mntgerente #departamento").change(function () {
			
			$('#form-mntgerente #provincia').empty();
			
			$('#form-mntgerente #distrito').empty();
			
			$('#form-mntgerente #oficina').empty();
			
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
        									var combo = $('#form-mntgerente #provincia');
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
	}
    
</script>
 