<div class="form-mi-perfil">
	<h1>Actualiza tus datos</h1>
	
	<div class="row">
		<div id="bienvenida_act_datos" class="col-md-12">
			<p class="page-info-wc">Hola, @@@, estos son los datos que tienes actualmente registrados.</p>
		</div>
		<br>
		<br>
   	</div>
	
	<div class="panel panel-default">
		
		<div class="panel-heading">
			Información Personal
		</div>
		
		<form id="frm-informacionpersonal">
			<div id="informacionpersonal" class="panel-body">
				<div class="row">
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="tipodoc" class="col-md-5 control-label">Tipo de Documento</label>
		                    <div class="col-md-7">
		                        <select class="form-control" id="tipodoc" name="tipodoc" disabled></select>
		                        <div class="result"></div>
		                    </div>
                         </div>

						<div class="form-group">
							<label for="nrodoc" class="col-md-5 control-label">Nro. de documento</label>
							<div class="col-md-7">
								<input type="text" id="nrodoc" name="nrodoc" class="form-control" data-rule-required="true" data-msg-required="El campo es obligatorio" required disabled>
								<div class="result"></div>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="fechanac" class="col-md-5 control-label">Fecha de Nacimiento</label>
							<div class="col-md-7">
								<div class="input-group">
									<input type="text" id="fechanac" name="fechanac" class="form-control" data-rule-required="true" data-msg-required="El campo es obligatorio" required disabled>
									<span class="input-group-addon">
										<a href="javascript:void(0);" class="btn-date">
											<span class="glyphicon glyphicon-calendar"></span>
										</a>
									</span>
								    <div class="result"></div>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="idpgenero" class="col-md-5 control-label">Sexo</label>
							<div class="col-md-7">
								<select class="form-control" id="idpgenero" name="idpgenero" required disabled></select>
								<div class="result"></div>
							</div>
						</div>						
					</div>

				</div>
			</div>
		</form>
		
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			Datos de contacto
		</div>
		
		<form id="frm-datos-contacto">
		
			<div id="datos_contacto" class="panel-body">
				
				<div class="row">
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="departamento2" class="col-md-5 control-label">Departamento <span class="required"></span></label>
							<div class="col-md-7">
								<select	id="departamento2" 		name="departamento2" 		class="form-control" onchange="obtenerProvincias(this,'datos_contacto #provincia2');"></select>
								<input 	id="idepais"			name="idepais"				type="hidden" 	value="80"/>
								<input 	id="ptocontacto-dom"  	name="ptocontacto-dom"		type="hidden" 	value=""/>
								<div class="result"></div>
							</div>                               
						</div>
					
						<div class="form-group">
							<label for="provincia2" class="col-md-5 control-label">Provincia <span class="required"></span></label>
							<div class="col-md-7">
								<select class="form-control" id="provincia2" name="provincia2" onchange="obtenerDistritos(this,'datos_contacto #distrito2');"></select>
								<div class="result"></div>
							</div>
						</div>
					
						<div class="form-group">
							<label for="distrito2" class="col-md-5 control-label">Distrito <span class="required"></span></label>
							<div class="col-md-7">
								<select class="form-control" id="distrito2" name="distrito2"></select>
								<div class="result"></div>
							</div>
						</div>
					</div>
                            
					<div class="col-md-6">
						<div class="form-group">
							<label for="telefonocasa2" class="col-md-5 control-label">Teléfono casa <span class="required"></span></label>
							<div class="col-md-7">
						        <input type="text" id="telefonocasa2" name="telefonocasa2" class="form-control phone-group" disabled>
						        <input id="mediocontactotelf"  name="depuntocontactotelf"	maxlength="10"	type="hidden" 	value=""/>
						        <div class="result"></div>
							</div>
						</div>
						
					    <div class="form-group">
							<label for="telefonomovil2" class="col-md-5 control-label">Teléfono móvil <span class="required"></span></label>
							<div class="col-md-7">
						        <input type="text" id="telefonomovil2" name="telefonomovil2" class="form-control phone-group" disabled>
						        <input id="mediocontactomovil"  name="mediocontactomovil"	maxlength="10"	type="hidden" 	value=""/>
						        <div class="result"></div>
							</div>
						</div>
					     
						<div class="form-group">
							<label for="email2" class="col-md-5 control-label">Correo electrónico <span class="required"></span></label>
							<div class="col-md-7">
						        <input type="text" id="email2" name="email2" class="form-control"disabled>
						        <input id="mediocontactomail"  name="mediocontactomail"	maxlength="100"	type="hidden" 	value=""/>
						        <div class="result"></div>
							</div>
						</div>
					     
						<div class="form-group">
							<label class="sr-only" for=""></label>
						</div>					
					</div>
                            
					<div class="col-md-12">
						<div class="row">
						
							<div class="col-md-6">
								<div class="form-group" id="idptipovia-div">
									<label for="idptipovia" class="col-md-5 control-label">Tipo Vía <span class="required"></span></label>
									<div class="col-md-7">
										<select class="form-control" id="idptipovia" name="idptipovia"></select>
										<div class="result"></div>
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label for="direccion2" class="col-md-5 control-label">Dirección <span class="required"></span></label>
									<div class="col-md-7">
										<input type="text" id="direccion2" name="direccion2" maxlength="200" class="form-control"required>
										<div class="result"></div>
									</div>
								</div>
							</div>	
													
						</div>
					</div>
					
				</div>
				
            </div>
			
			<div class="panel-body" style="font-size: 11px; font-style: italic; font-weight: bold; padding-bottom: 0px; padding-top: 0px;">
           		<span>(<label class="required"></label>) Campos obligatorios</span>
			</div>
                
			<div class="row">
				<div class="col-md-12">
					<div class="checkbox text-center">
						<label>
							<input id="terminosCond" type="checkbox" value="">						  
						</label>
						<a href="#">
					   		<span onclick="$('#link-tycdatos').click();">Acepto términos y condiciones de uso</span>
						</a>
					</div>
				</div>
			 	
				<div class="col-md-12">
					<div class="text-right">
						<div class="col-md-12">
							<button id="editar_dirpweb" type="button" class="btn btn-default" onclick="javascript:habilitarEdicion('#datos_contacto');">Editar</button>
							<button id="grabar_dirpweb" type="button" class="btn btn-primary" onclick="javascript:actDatosContacto();" disabled>Grabar</button>
						</div>
					</div>
				</div>
			</div>
        
		</form>
	</div>        
</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-portal.js"></script>

<script>
var EV_EDITAR_DATOS   = 'dataEdit';

	$().ready(function(){
		
		var pageUrl = '';
		pageUrl = VP_GO_ACTUALIZAR_DATOS;
		dataLayer.push({'event': EV_VIRTUAL_PAGE, 'pageUrl': pageUrl, 'numDocument': CTE_NUM_DOC});
		
		loadModalCargando();
		$("#frm-datos-contacto").validate({
			rules			: {
								departamento2	: {
										required 		: true
								},
								provincia2	: {
										required 		: true
								},
								distrito2	: {
										required 		: true
								},
								direccion2	: {
										required 		: true
								},
								telefonocasa2	: {
										//require_from_group: [1, ".phone-group"],
										required 		: true,
										digits 	 		: true,
										minlength 		: 6
								},
								telefonomovil2	: {
										//require_from_group: [1, ".phone-group"],
										required 		: true,
										digits 	 		: true,
										minlength 		: 9
								},
								email2	: {
										required 		: true,
										email			: true,
								}
							},
						
						
			messages 		: {
								departamento2: {
									required 		: "Campo Obligatorio"
								},
								provincia2	: {
									required 		: "Campo Obligatorio"
								},
								distrito2	: {
									required 		: "Campo Obligatorio"
								},
								direccion2	: {
									required 		: "Campo Obligatorio"
								},
								telefonocasa2: {
									//require_from_group 	: "Debe ingresar al menos un número de teléfono",
									required 			: "Campo Obligatorio",
									digits 	 			: "Sólo números",
									minlength 			: "Ingresar un mínimo de 6 dígitos"
								},
								telefonomovil2: {
									//require_from_group 	: "Debe ingresar al menos un número de teléfono",
									required 			: "Campo Obligatorio",
									digits 	 			: "Sólo números",
									minlength 			: "Ingresar un mínimo de 9 dígitos"
								},
								email2	: {
									required 		: "Campo Obligatorio",
									email			: "Ingresar formato de email válido"
								}
							}
		});
		
		var bienvenida = replaceAll($("#bienvenida_act_datos").text(),'@@@',CTE_JSON_PERSONAPWEB.nombre);
		$("#bienvenida_act_datos").text(bienvenida);

		callCargaControlParam('PWEB_USR_TIPDOC','informacionpersonal #tipodoc');
		callCargaControlParam('TER_GENERO','informacionpersonal #idpgenero');
    	callCargaControlParam('PTO_TIPOVIA','datos_contacto #idptipovia');
		
		cargarData();
		
		closeModalCargando();
	});
	
	$("#link-tyc").click (function () {
     	 $("#tyc").fancybox({
     		 			 'width'             : '85%',
     	                 'height'            : '400px',
     	                 'autoScale'         : false,
     	                 'transitionIn'      : 'none',
     	                 'transitionOut'     : 'none',
     	                 'type'              : 'iframe'
     	             });

   });
	
	function cargarData(){
		
		/*Informacion personal*/
		$("#informacionpersonal #nrodoc").val(CTE_JSON_USUARIOPWEB.numerodoc);
		$("#informacionpersonal #fechanac").val(CTE_JSON_PERSONAPWEB.fecnacimiento);
		$("#informacionpersonal #tipodoc").val(CTE_JSON_PERSONAPWEB.idptipodocumento);
		$("#informacionpersonal #idpgenero").val(CTE_JSON_PERSONAPWEB.idpgenero);
		
		/*Datos Direccion Portal web*/
		$("#datos_contacto :input").attr("disabled", true);
		obtenerDpto($("#datos_contacto #idepais").val(),'datos_contacto #departamento2');

		cargarDireccion("#datos_contacto",CTE_JSON_DIRPWEB);
		
		$("#datos_contacto #idptipovia").val(CTE_JSON_DIRPWEB.idptipovia);
		$("#datos_contacto #direccion2").val(CTE_JSON_DIRPWEB.nomvia);
		$("#ptocontacto-dom").val(CTE_JSON_DIRPWEB.idepuntocontacto);
		
		/*Datos de telefono pweb secundario (fijo)*/
		if(CTE_JSON_TELFSECPWEB!=undefined){
			$("#telefonocasa2").val(CTE_JSON_TELFSECPWEB.numlocal);
			$("#mediocontactotelf").val(CTE_JSON_TELFSECPWEB.idepuntocontacto);
		}
		
		/*Datos de telefono pweb principal (movil) */
		$("#telefonomovil2").val(CTE_JSON_TELFPWEB.numlocal);
		$("#mediocontactomovil").val(CTE_JSON_TELFPWEB.idepuntocontacto);
		
		/*Datos de email*/
		$("#email2").val(CTE_JSON_CORREOPWEB.email);
		$("#mediocontactomail").val(CTE_JSON_CORREOPWEB.idepuntocontacto);
		
		$("#editar_dirpweb").attr("disabled", false);
		//$("#editar_corresp").attr("disabled", false);
		$("#terminosCond").attr("disabled", true);
		
	}
		
	function actDatosContacto(){
		if($("#frm-datos-contacto").valid()){
			
			if($("#terminosCond").prop('checked')){
				$("#datos_contacto :input").attr("disabled", true);
				
				var param = new Object();
				param.idetercero				=CTE_IDETERCERO;
				param.idepuntocontactodir		=$("#datos_contacto #ptocontacto-dom").val();
				param.idepais					=$("#datos_contacto #idepais").val();
				param.idedepartamento			=$("#datos_contacto #departamento2").val();
				param.ideprovincia				=$("#datos_contacto #provincia2").val();
				param.idedistrito				=$("#datos_contacto #distrito2").val();
				param.direccioncompleta			=$("#datos_contacto #direccion2").val();
				
				param.idptipovia				=$("#datos_contacto #idptipovia").val();
				
				param.idepuntocontactomail		=$("#datos_contacto #mediocontactomail").val();
				param.email						=$("#datos_contacto #email2").val();
				
				param.idepuntocontactotelf		=$("#datos_contacto #mediocontactomovil").val();
				param.numlocal					=$("#datos_contacto #telefonomovil2").val();
				
				param.idepuntocontactotelfSec	=$("#datos_contacto #mediocontactotelf").val();
				param.numlocalSec				=$("#datos_contacto #telefonocasa2").val();
				
				loadModalCargando();
				
				$.ajax({
					type 		: "POST",
					url 		: "/PORTALWEB/perfil.do"+"?method=mntDatosContacto",
					cache 		: false ,
					dataType	: "json",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					async 		: false,
					data 		: param,
					success 	: function(rsp){

									var statusTx 	= rsp.statustx;
									var messageTx	= rsp.messagetx;

									if(statusTx==0){
										
										$.ajax({
											type 		: "POST",
											url 		: "/PORTALWEB/perfil.do"+"?method=enviarMailActContacto",
											cache 		: false ,
											dataType	: "json",
											contentType : "application/x-www-form-urlencoded; charset=UTF-8",
											async 		: true,
											data 		: CTE_JSON_USUARIOPWEB,
											success 	: function(rsp2){
															closeModalCargando();
															
															loadModalMensaje("Atención",
																				"Modificación de datos de contacto exitosa.",
																				function(){
																							obtDatosUsuarioSesion();
																							cargarData();
																				});
											},
											error 		: function(xhr, ajaxOptions, thrownError) {
																closeModalCargando();
																loadModalMensaje("Atención", "Error modificando datos de contacto.",null);
											}										
										});
									}else{
										closeModalCargando();
										loadModalMensaje("Atención", messageTx,null);
									}	
									
							dataLayer.push({'event': EV_ACTUALIZA_DATOS, 'numDocument': CTE_NUM_DOC});
					},
					error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Atención","Error modificando datos de contacto.",null);
					}
				});
				
				$("#editar_dirpweb").attr("disabled", false);
				$("#terminosCond").prop('checked',false);
				$("#grabar_dirpweb").attr("disabled", true);
				$("#terminosCond").attr("disabled", true);
			
			}else{
				loadModalMensaje("Atención","Debe aceptar los Términos y Condiciones",null);
			}
		}
	}
	
	function obtenerDpto(paisorigen,idCmbHijo,defaultSonValue,defaultGrandSonValue,grandSonId) {
		
	  	var param = new Object();
	  	param.idepais = paisorigen;
	  	
	  	$.ajax({
				type 		: "POST",
				url 		: "/TERCERO/puntocontacto.do" + "?method=cboDepartamento",
				cache 		: false ,
				dataType	: "json",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				async 		: false,
				data 		: param,
				success 	: function(rsp){
				
					llenarCombo(idCmbHijo, rsp, true);
					if(defaultSonValue!=undefined)
						$(idCmbHijo+' option[text="'+defaultSonValue+'"]').attr('selected','selected');
				},
				error 		: function(xhr, ajaxOptions, thrownError) {}
			});
			
			if(defaultGrandSonValue!=undefined) 
				obtenerProvincias( $('#'+idCmbHijo), grandSonId ,defaultGrandSonValue);
			else 
				obtenerProvincias('');
	}
	  
	function obtenerProvincias(padre,idCmbHijo,defaultSonValue,defaultGrandSonValue,grandSonId) {
			
	  	if(padre.value!=null && padre.value!=undefined && padre.value!=""){
			
			var param 				= new Object();
	    	param.idedepartamento 	= padre.value;
	    	
	    	$.ajax({
				type 		: "POST",
				url 		: "/TERCERO/puntocontacto.do" + "?method=cboProvincia",
				cache 		: false ,
				dataType	: "json",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				async 		: false,
				data 		: param,
				success 	: function(rsp){
				
					llenarCombo(idCmbHijo, rsp, true);
					if(defaultSonValue!=undefined)
						$(idCmbHijo+' option[text="'+defaultSonValue+'"]').attr('selected','selected');
				},
				error 		: function(xhr, ajaxOptions, thrownError) {}
			});
			
			if(defaultGrandSonValue!=undefined)
				obtenerDistritos( $('#'+idCmbHijo), grandSonId ,defaultGrandSonValue);
			else 
				obtenerDistritos('');
	  	}
	}
	  
	function obtenerDistritos(padre,idCmbHijo,defaultValue) {
			
	  	if(padre.value!=null && padre.value!=undefined && padre.value!=""){
	    	var param = new Object();
	    	param.ideprovincia = padre.value;
	    	
	    	$.ajax({
				type 		: "POST",
				url 		: "/TERCERO/puntocontacto.do" + "?method=cboDistrito",
				cache 		: false ,
				dataType	: "json",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				async 		: false,
				data 		: param,
				success 	: function(rsp){
				
					llenarCombo(idCmbHijo, rsp, true);
					$(idCmbHijo+' option[text="'+defaultValue+'"]').attr('selected','selected');
				},
				complete 	: function (){},
				error 		: function(xhr, ajaxOptions, thrownError) {}				
			});
	  	}
	}
	
	function cargarDireccion(div, datosDir){
		$(div+" #departamento2").val(datosDir.idedepartamento);
		$(div+" #departamento2").change();
		$(div+" #provincia2").val(datosDir.ideprovincia);
		$(div+" #provincia2").change();
		$(div+" #distrito2").val(datosDir.idedistrito);
		$(div+" #distrito2").change();
	}
		
	function habilitarEdicion(div){
		$(div +" :input").attr("disabled", false);
		$("#terminosCond").attr("disabled", false);
		$("#grabar_dirpweb").attr("disabled", false);
 		/*GOOGLE ANALYTICS*/
 		dataLayer.push({'event': EV_EDITAR_DATOS, 'numDocument': CTE_NUM_DOC});
		
	}

</script>