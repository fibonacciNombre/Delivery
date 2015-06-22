<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="registro-courier">

	<h1>Registro de Courier</h1>

    <form id="form-registrocourier">
        <div class="row">
            <div class="col-md-12">

                <div class="form-group" id="numerodoc-div">
                    <label for="numerodoc" class="col-sm-5 col-sm-offset-1 control-label required">Código BBVA</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id=codbbva name="codbbva" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="direccioncompleta-div">
                    <label for="direccioncompleta" class="col-sm-5 col-sm-offset-1 control-label required">Razón social</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="rznsocial" name="rznsocial" maxlength="200">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="nummovil-div">
                    <label for="nummovil" class="col-sm-5 col-sm-offset-1 control-label required">Teléfono móvil</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control phone-group" id="nummovil" name="nummovil" maxlength="10">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="numlocal-div">
                    <label for="numlocal" class="col-sm-5 col-sm-offset-1 control-label required">Teléfono casa</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control phone-group" id="numlocal"  name="numlocal" maxlength="10">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div class="form-group" id="email-div">
                    <label for="email" class="col-sm-5 col-sm-offset-1 control-label required">Correo electrónico</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="email"  name="email" maxlength="100">
                        <div class="result"></div>
                    </div>
                </div>
                <%--
                <div style="display: table; width: 100%;">
	                <p>
	                    Dirección postal referencial solo para el envío de promociones o publicacidad, en el caso desee modificar la dirección de envío de póliza a documentos relacionados acercase a las oficinas de atención presencial de RIMAC Seguros.
	                </p>
                </div>
                --%>
                <div style="display: table; width: 100%;">
	                <p class="page-info-wc">
	                    La siguiente pregunta y respuesta de seguridad te permitirán recordar tus datos de acceso en caso te olvides:
	                </p>
                </div>
                
                <div class="form-group" id="pregseg-div">
                    <label for="pregseg" class="col-sm-5 col-sm-offset-1 control-label required">Pregunta de seguridad</label>
                    <div class="col-sm-5">
                    	<select class="form-control" id="pregseg" name="pregseg"></select>
						<div class="result"></div>
                    </div>

                </div>
                
                <div class="form-group" id="respseg-div">
                    <label for="respseg" class="col-sm-5 col-sm-offset-1 control-label required" >Respuesta</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="respseg" name="respseg" maxlength="15">
                        <div class="result"></div>
                    </div>
                </div>
                
                <div style="display: table; margin-bottom: 10px; font-size: 11px; font-style: italic; font-weight: bold;">
                	<span>(<label class="required"></label>) Campos obligatorios</span>
                </div>
                
                <div style="display: table; width: 100%;" class="form-group" id="TermCond-div">
                    <div class="col-sm-10 col-sm-offset-1">
                        <div class="col-sm-10 col-sm-offset-1">
                        <div class="checkbox">
                            
                              <input id="chktermcond" name="chktermcond" type="checkbox">
                              <a href="#" onclick="$('#link-tycdatos').click();">Acepta los términos y condiciones</a>
                              <br>
                              <div class="result"></div>
                        </div>
                    </div>
                    </div>
                </div>

            </div>
            
        </div>
        
        <div class="row">
            <div class="col-sm-10  col-sm-offset-1">
                <div class="form-group pull-right">
                    <button type="button" 
                    		class="btn btn-default"
                    		onclick="javascript:limpiarFormulario('form-registro-cliente');">
                    			Limpiar
                    </button>
                    
                    <button id="btnRegistrar"
                    		type="button" 
                    		class="btn btn-primary" 
                    		onclick="javascript:registrarCliente();">
                    			Registrar
                    </button>
                </div>
            </div>
        </div>
        
    </form>
    
</div>

<script src="<%=request.getContextPath()%>/js/default/datepicker.custom.js"></script>
<script src="<%=request.getContextPath()%>/js/bbva/main-portal.js"></script>

<script>
	
    $().ready(function(){
    	
    	$('#ui-datepicker-div').appendTo($('#modalRegistro'));
    	
    	callCargaControlParam('PWEB_USR_TIPDOC','registro-cliente-container #idptipodocumento');

    	callCargaControlParam('PWEB_USR_PREGSEG','registro-cliente-container #pregseg');

    	callCargaControlParam('PWEB_USR_GENERO','registro-cliente-container #idpgenero');
    	
    	callCargaControlParam('PTO_TIPOVIA','registro-cliente-container #idptipovia');
    	
    	obtenerDpto($("#registro-cliente-container #idepais").val(),'registro-cliente-container #idedepartamento');
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		jQuery.validator.addMethod("dateFormat", function(value, element) {
			var dtRegex = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
			return dtRegex.test(value);

		});
		
		$("#form-registro-cliente").validate({
			rules : {
				
				idptipodocumento	: {				required 	: true },
				
				numerodoc 			: {				required 	: true,
													digits 	 	: true,
													minlength 	: function(){
																	$result = 8;
																	if($("#idptipodocumento").val()==CTE_TIPDOCUM_DNI ||
																	   $("#idptipodocumento").val()==CTE_TIPDOCUM_CE){
																		$result = 8;
																	}
																	if($("#idptipodocumento").val()==CTE_TIPDOCUM_CIP ||
																	   $("#idptipodocumento").val()==CTE_TIPDOCUM_PAS	){
																		$result = 7;
																	}
																	return $result;
																  },
													maxlength 	: function(){
																	$result = 8;
																	if($("#idptipodocumento").val()==CTE_TIPDOCUM_DNI){
																		$result = 8;
																	}
																	if($("#idptipodocumento").val()==CTE_TIPDOCUM_CE ||
																   	   $("#idptipodocumento").val()==CTE_TIPDOCUM_PAS){
																		$result = 12;
																	}
																	if($("#idptipodocumento").val()==CTE_TIPDOCUM_CIP	){
																		$result = 15;
																	}
																	return $result;
													  } 
													},							
				fecnacimiento 		: {
													required 	: true,
													dateFormat	: true},
				idpgenero 			: {
													required 	: true },
				idedepartamento 	: {
													required 	: true },
				ideprovincia 		: {
													required 	: true },
				idedistrito 		: {
													required 	: true },
				idptipovia 			: {
													required 	: true },
				direccioncompleta 	: {
													required 	: true },
				numlocal 			: {
													require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 6},
				nummovil 			: {
													require_from_group: [1, ".phone-group"],
													digits 	 	: true,
													minlength 	: 9},
				pregseg 			: {
													required 	: true },
				respseg 			: {
													required 	: true },
				email 				: {
													required 	: true,
													email		: true}
			},
			messages : {
				
				idptipodocumento 	: {				required 	: "Ingresar Tipo de Documento" },
				
				numerodoc 			: {				required 	: "Ingresar Número de Documento",
													digits 	 	: "Sólo números",
													minlength 	: function(){
														$result = 8;
														$res1 = "Ingresar un mínimo de ";
														$res2 = " dígitos";
														if($("#idptipodocumento").val()==CTE_TIPDOCUM_DNI ||
														   $("#idptipodocumento").val()==CTE_TIPDOCUM_CE){
															$result = 8;
														}
														if($("#idptipodocumento").val()==CTE_TIPDOCUM_CIP ||
														   $("#idptipodocumento").val()==CTE_TIPDOCUM_PAS	){
															$result = 7;
														}
														return $res1+$result+$res2;
													  },
													maxlength 	: function(){
														$result = 8;
														$res1 = "Ingresar un máximo de ";
														$res2 = " dígitos";
														if($("#idptipodocumento").val()==CTE_TIPDOCUM_DNI){
															$result = 8;
														}
														if($("#idptipodocumento").val()==CTE_TIPDOCUM_CE ||
													   	   $("#idptipodocumento").val()==CTE_TIPDOCUM_PAS){
															$result = 12;
														}
														if($("#idptipodocumento").val()==CTE_TIPDOCUM_CIP	){
															$result = 15;
														}
														return $res1+$result+$res2;
													  }
												},							
				fecnacimiento 		: {
													required 	: "Ingresar Fecha de Nacimiento",
													dateFormat  : "Fecha inválida"},
				idpgenero 			: {
													required 	: "Ingresar género" },
				idedepartamento		: {
													required 	: "Seleccionar Departamento" },
				ideprovincia 		: {
													required 	: "Seleccionar Provincia" },
				idedistrito 		: {
													required 	: "Seleccionar Distrito" },
				direccioncompleta 	: {
													required 	: "Ingresar Dirección" },
				idptipovia 			: {
													required 	: "Ingresar Vía" },
				numlocal 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Sólo números",
													minlength 	: "Ingresar un mínimo de 6 dígitos" },
				nummovil 			: {
													require_from_group : "Debe ingresar al menos un número de teléfono",
													digits 	 	: "Sólo números",
													minlength 	: "Ingresar un mínimo de 9 dígitos"},
				pregseg 			: {
													required 	: "Seleccionar Pregunta de Seguridad" },
				respseg				: {
													required 	: "Ingresar Respuesta de Seguridad" },
				email 				: {
													required 	: "Ingresar Email",
													email		: "Ingresar formato de email válido"}
			}
		});	
		
		//activarChecksValidate("form-registro-cliente");
		
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

	function registrarCliente(){

		var $inputs = $('#form-registro-cliente :input');

		$inputs.each(function() {
			if(this.id!=""){
				if($("#form-registro-cliente #"+ this.id).valid())
					$(".result", $("#"+this.id).parent()).html("<i class='success'></i>");			
				else
					$(".result", $("#"+this.id).parent()).html("<i class='error'></i>");
			}
	    });

		activarChecksValidate("form-registro-cliente");
		
		if($("#form-registro-cliente").valid()){
			
    		if($("#form-registro-cliente #chktermcond").prop('checked')){
    			
    			var param = new Object();
    			param = $("#form-registro-cliente").serializeArray();
    			
    			loadModalCargando();

    			setTimeout(
  					  function(){
    			
			    			$.ajax({
								type 		: "POST",
								url 		: "/PORTALWEB/perfil.do"+"?method=regUsuario",
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
														loadModalMensaje("Felicitaciones", message, function(){$("#modalRegistro").modal('hide');});
														limpiarFormulario('form-registro-cliente');
														
													}else
														loadModalMensaje("Atención",message,null);
								},						
								error: function (rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Error","ERROR REGISTRANDO CLIENTE",null);
									
								}			
							});		    					    				
		  				},1000);    			
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
				//console.log()
				llenarCombo(idCmbHijo, rsp, true);
				if(defaultSonValue!=undefined)
					$('#registro-cliente-container #'+idCmbHijo+' option[text="'+defaultSonValue+'"]').attr('selected','selected');
			},
			error 		: function(xhr, ajaxOptions, thrownError) {}
		});
		
		if(defaultGrandSonValue!=undefined) 
			obtenerProvincias( $('#registro-cliente-container #'+idCmbHijo), grandSonId ,defaultGrandSonValue);
		else 
			obtenerProvincias('');
	}
    
    function obtenerProvincias(padre,idCmbHijo,defaultSonValue,defaultGrandSonValue,grandSonId) {
		
    	if(padre.value!=null && padre.value!=undefined && padre.value!=""){
		
			var param = new Object();
	    	param.idedepartamento = padre.value;
	    	
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
						$('#registro-cliente-container #'+idCmbHijo+' option[text="'+defaultSonValue+'"]').attr('selected','selected');
				},
				error 		: function(xhr, ajaxOptions, thrownError) {}
			});
			
			if(defaultGrandSonValue!=undefined)
				obtenerDistritos( $('#registro-cliente-container #'+idCmbHijo), grandSonId ,defaultGrandSonValue);
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
					$('#registro-cliente-container #'+idCmbHijo+' option[text="'+defaultValue+'"]').attr('selected','selected');
				},
				complete : function (){},
				error 		: function(xhr, ajaxOptions, thrownError) {}
				
			});
    	}
	}    
</script>