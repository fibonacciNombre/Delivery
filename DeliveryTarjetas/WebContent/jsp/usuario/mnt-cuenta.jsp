<div id="mi-cuenta">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Mi cuenta</span>
	</h3>
	
	<div class="row">
		<div id="bienvenida_act_datos" class="col-md-12">
			<p class="page-info-wc">Hola @@@, estos son tus datos que tenemos actualmente registrados.</p>
		</div>		
   	</div>
	
	<div id="container-usuario" class="panel panel-default">
	
		<div class="panel-heading">
			Datos personales
		</div>
		<form id="form-micuenta">
		<div id="datos-personales" class="panel-body">
		
			<div class="col-md-6">
				<div class="form-group" id="dscperfil-div">
                    <label for="dscperfil" class="col-md-6 control-label">Perfil </label>
                    <div class="col-md-12">
                   		<select class="form-control" id="idperfil" name="idperfil"></select>
                    </div>
                </div>
                
                <div class="form-group" id="idptipodocumento-div">
                    <label for="idptipodocumento" class="col-md-6 control-label">Tipo de documento </label>
                    <div class="col-md-12">
                    	<select class="form-control" id="idptipodocumento" name="idptipodocumento"></select>
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
        		<div class="form-group" id="rznsocial-div" style="display: none;">
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
                    	<select class="form-control" id="idpestado" name="idpestado"></select>                        
                    </div>
                </div>
                
                <div class="form-group" id="comentarios-div">
                    <label for="comentarios" class="col-md-6 control-label">Comentarios </label>
                    <div class="col-md-12">
                    	<textarea readonly class="form-control" id="comentario" name="comentario" maxlength="300"  wrap="hard" style="min-width: 100%; max-width: 100%; min-height: 50px; max-height: 50px;" ></textarea>
                    </div>
                </div>
        	</div>	
        			
		</div>	
		</form>
		
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
				
				<form id="form-micuenta-courier">
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
				</form>		
					
			</div>
		</div>		  		
	</div>
	<%-- FIN MODAL COURIER --%>
	
</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>

	$().ready(function(){
		
		loadModalCargando();
		
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-micuenta #idptipodocumento',false);  
		
		callCargaControlParam('DELWEB_ESTADO','form-micuenta #idpestado',false);
    	 
		cargarCombo('/DeliveryTarjetas/perfil.do', 'lstPerfil','idperfil',  ['idperfil','descripcion'], {form: 'form-micuenta'});
		
		var bienvenida = replaceAll($("#bienvenida_act_datos").text(),'@@@',CTE_JSON_TERCERO.nombres);
		
		$("#bienvenida_act_datos").text(bienvenida);

		cargarData();
		
		closeModalCargando();
		
	});
	
	function cargarData(){
		
		/*Informacion personal*/
		$("#form-micuenta #idperfil").val(CTE_JSON_PERFIL.idperfil);
		$("#form-micuenta #nrodocumento").val(CTE_JSON_TERCERO.nrodocumento);
		$("#form-micuenta #idptipodocumento").val(CTE_JSON_TERCERO.idptipodocumento);
		$("#form-micuenta #idpestado").val(CTE_JSON_USUARIOWEB.idpestado);
		$("#form-micuenta #nombres").val(CTE_JSON_TERCERO.nombres);
		$("#form-micuenta #apepaterno").val(CTE_JSON_TERCERO.apepaterno);
		$("#form-micuenta #apematerno").val(CTE_JSON_TERCERO.apematerno);
		$("#form-micuenta #codusuario").val(CTE_JSON_USUARIOWEB.codusuario);
		$("#form-micuenta #telfmovil").val(CTE_JSON_TERCERO.telfmovil);
		$("#form-micuenta #correo").val(CTE_JSON_TERCERO.correo);
		$("#form-micuenta #comentario").val(CTE_JSON_USUARIOWEB.comentario);
		$
		
		if(CTE_JSON_PERFIL.idperfil ==CTE_INIT_IDROL_COLAB_COURIER){
			$("#rznsocial-div").show();
			$("#form-micuenta #rznsocial").val(CTE_JSON_COURIER.rznsocial);	
			$("#form-micuenta-courier #codbbva").val(CTE_JSON_COURIER.codbbva);
			$("#form-micuenta-courier #idptipodocumento").val(obtDescripcionParametro(CTE_INIT_PARAM_TIPDOCUMENTO, null, CTE_JSON_COURIER.idptipodocumento));
			$("#form-micuenta-courier #nrodocumentocou").val(CTE_JSON_COURIER.nrodocumentocou);
			$("#form-micuenta-courier #rznsocial").val(CTE_JSON_COURIER.rznsocial);
			$("#form-micuenta-courier #direccion").val(CTE_JSON_COURIER.direccion);
			$("#form-micuenta-courier #telfmovil").val(CTE_JSON_COURIER.telfmovil);
			$("#form-micuenta-courier #telffijo").val(CTE_JSON_COURIER.telffijo);
			$("#form-micuenta-courier #correo").val(CTE_JSON_COURIER.correo);
			$("#form-micuenta-courier #idpestado").val(obtDescripcionParametro(CTE_INIT_PARAM_ESTADO, null, CTE_JSON_COURIER.idpestado));
			
		}
		
		$("#form-micuenta *").attr("disabled","disabled");
		$("#form-micuenta #btnDetalleCourier").attr("disabled",false);
	}
		
</script>