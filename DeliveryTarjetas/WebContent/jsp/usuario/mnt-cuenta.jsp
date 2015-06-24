<div id="mi-cuenta">

	<h3 class="container-title">Mi cuenta</h3>
	
	<div class="row">
		<div id="bienvenida_act_datos" class="col-md-12">
			<p class="page-info-wc">Hola @@@, estos son tus datos que tenemos actualmente registrados.</p>
		</div>		
   	</div>
	
	<div id="container-usuario" class="panel panel-default">
	
		<div class="panel-heading">
			Datos personales
		</div>
		
		<div id="datos-personales" class="panel-body">
		
			<div class="col-md-6">
				<div class="form-group" id="dscperfil-div">
                    <label for="dscperfil" class="col-md-6 control-label">Perfil </label>
                    <div class="col-md-12">
                   		<input type="text" readonly class="form-control" id="dscperfil" name="dscperfil" maxlength="200">
                    </div>
                </div>
                
                <div class="form-group" id="idptipodocumento-div">
                    <label for="idptipodocumento" class="col-md-6 control-label">Tipo de documento </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="idptipodocumento" name="idptipodocumento" maxlength="200">
                    </div>
                </div>
                
                <div class="form-group" id="nrodocumento-div">
                    <label for="nrodocumento" class="col-md-6  control-label">Nro. documento </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">                        
                    </div>
                </div>
                
                <div class="form-group" id="nombres-div">
                    <label for="nombres" class="col-md-6 control-label">Nombres </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="nombres" name="nombres" maxlength="200">                        
                    </div>
                </div>
                
                <div class="form-group" id="apepaterno-div">
                    <label for="apepaterno" class="col-md-6 control-label">Apellido paterno </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="apepaterno" name="apepaterno" maxlength="200">                        
                    </div>
                </div>
                
                <div class="form-group" id="apematerno-div">
                    <label for="apematerno" class="col-md-6 control-label">Apellido materno </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="apematerno" name="apematerno" maxlength="200">                        
                    </div>
                </div>
			</div>
        
        	<div class="col-md-6">
        		<div class="form-group" id="rznsocial-div">
                    <label for=rznsocial" class="col-md-6 control-label">Courier </label>
                    <div class="col-md-12">
                        <div style="display: inline-block; vertical-align: top; padding: 0px;">
                        	<input type="text" readonly class="form-control" id="rznsocial" name="rznsocial" maxlength="200">
                        </div>
                        <div style="display: inline-block;">	
                        	<button id="btnDetalleCourier"
		                    		type="button" 
		                    		class="btn btn-primary" 
		                    		data-toggle="modal" 
									data-target="#content-courier"
		                    		style="padding-top: 3px; padding-bottom: 3px;">
		                    			<i style="background-position: -592px -1405px; height: 25px; margin-right: 0px; width: 25px; display: inline-block; vertical-align: top; transform: scale(0.6);"></i>			                    						                    			
		                    </button>
                        	
                        </div>                        
                    </div>
                </div>
                
        		<div class="form-group" id="codusuario-div">
                    <label for="codusuario" class="col-md-6  control-label">Código de usuario </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="codusuario" name="codusuario" maxlength="200">                        
                    </div>
                </div>
                
        		<div class="form-group" id="telfmovil-div">
                    <label for="telfmovil" class="col-md-6 control-label">Teléfono móvil </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="telfmovil" name="telfmovil" maxlength="9">                        
                    </div>
                </div>
                
                <div class="form-group" id="correo-div">
                    <label for="correo" class="col-md-6 control-label">Correo electrónico </label>
                    <div class="col-md-12">
                        <input type="text" readonly class="form-control" id="correo"  name="correo" maxlength="200">                        
                    </div>
                </div>
                
                <div class="form-group" id="estado-div">
                    <label for="idpestado" class="col-md-6 control-label">Estado </label>
                    <div class="col-md-12">
                    	<input type="text" readonly class="form-control" id="idpestado"  name="idpestado" maxlength="200">                        
                    </div>
                </div>
                
                <div class="form-group" id="comentarios-div">
                    <label for="comentarios" class="col-md-6 control-label">Comentarios </label>
                    <div class="col-md-12">
                    	<textarea readonly class="form-control" id="comentarios" name="comentarios" maxlength="300"  wrap="hard" style="min-width: 100%; max-width: 100%; min-height: 50px; max-height: 50px;" ></textarea>
                    </div>
                </div>
        	</div>	
        			
		</div>	
		
	</div>        
	
	<%-- INI MODAL COURIER --%>
	<div id="content-courier" 
			class="modal fade" 
			tabindex="-1" 
			role="dialog" 
			aria-hidden="true"			
			data-backdrop="static" 
	  		data-keyboard="false">  		
		<div class="modal-mensaje">
			<div class="modal-content">	
				
				<span  style="color: white; font-weight: bold; display: block; padding: 5px; width: auto; text-align: center; background-color: #00559d;">
					<span id="titulo-mensaje">Datos de courier</span>
					<button id="btnclosemodal" type="button" class="close-white" data-dismiss="modal" aria-hidden="true">&times;</button>
				</span>
				
				<div class="row">
					<div class="col-md-12" style="margin-top: 15px;">
						<p class="page-info-wc">Estos son los datos actualmente registrados para tu Courier.</p>
					</div>		
			   	</div>
			   	
				<div id="container-courier" class="panel panel-default">	
					
					<div id="datos-courier" class="panel-body">
					
						<div class="col-md-6" style="padding: 0px;">
			                <div class="form-group" id="codbbva-div">
			                    <label for="codbbva" class="col-md-12 control-label">Código Bbva </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control" id=codbbva name="codbbva" maxlength="200">
			                    </div>
			                </div>
			                
			                <div class="form-group" id="tipdocumento-div">
			                    <label for="idptipodocumento" class="col-md-12 control-label">Tipo de documento </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control" id=idptipodocumento name="idptipodocumento" maxlength="200">
			                    </div>
			                </div>
			                
			                <div class="form-group" id="nrodocumento-div">
			                    <label for="nrodocumentocou" class="col-md-12 control-label">Nro. documento </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control" id="nrodocumentocou"  name="nrodocumentocou" maxlength="11">                        
			                    </div>
			                </div>
			                
			                <div class="form-group" id="rznsocial-div">
			                    <label for="rznsocial" class="col-md-12 control-label">Razón social </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control" id="rznsocial" name="rznsocial" maxlength="200">                        
			                    </div>
			                </div>
			                
			                <div class="form-group" id="direccion-div">
			                    <label for="direccion" class="col-md-12 control-label">Dirección completa </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control" id="direccion" name="direccion" maxlength="200">                        
			                    </div>
			                </div>
						</div>
						
					  	<div class="col-md-6" style="padding: 0px;">            
			                <div class="form-group" id="telfmovil-div">
			                    <label for="telfmovil" class="col-md-12 control-label">Teléfono móvil </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control phone-group" id="telfmovil" name="telfmovil" maxlength="9">                        
			                    </div>
			                </div>
			                
			                <div class="form-group" id="telffijo-div">
			                    <label for="telffijo" class="col-md-12 control-label">Teléfono fijo </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control phone-group" id="telffijo"  name="telffijo" maxlength="9">                        
			                    </div>
			                </div>
			                
			                <div class="form-group" id="correo-div">
			                    <label for="correo" class="col-md-12 control-label">Correo electrónico </label>
			                    <div class="col-md-12">
			                        <input type="text" readonly class="form-control" id="correo"  name="correo" maxlength="200">                        
			                    </div>
			                </div>
			                
			                <div class="form-group" id="idpestado-div">
			                    <label for="idpestado" class="col-md-12 control-label">Estado </label>
			                    <div class="col-md-12">
			                    	<input type="text" readonly class="form-control" id=idpestado name="idpestado" maxlength="200">                        
			                    </div>
			                </div>
			            </div>
			                      	         
			        </div>			        
				</div>
								
			</div>
		</div>		  		
	</div>
	<%-- FIN MODAL COURIER --%>
	
</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>

	$().ready(function(){
		/*
		loadModalCargando();
		
		var bienvenida = replaceAll($("#bienvenida_act_datos").text(),'@@@',CTE_JSON_PERSONAPWEB.nombre);
		$("#bienvenida_act_datos").text(bienvenida);

		cargarData();
		
		closeModalCargando();
		*/
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
		
</script>