<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-usuario">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Edición de usuarios</span>
	</h3>
	
    <form id="form-bsqusuario">
    	<div class="panel panel-default">
	    	<div class="panel-heading">
				Buscar usuarios
			</div>    	
	    	<div class="panel-body">	    		
	    		<div class="row">						
					<div class="col-md-6">						
						<div class="form-group">
							<label for="codusuario" class="col-md-5 control-label">Código de usuario</label>
		                    <div class="col-md-7">
								<input type="text" class="form-control" id="codusuario" name="codusuario" maxlength="200">
		                    </div>
	                	</div>	
						<div class="form-group">
							<label for="idperfil" class="col-md-5 control-label">Perfil</label>
							<div class="col-md-7">								
		                    	<select class="form-control" id="idperfil" name="idperfil"> 
							 		<option value="{debe ir el idperfil">Nombre perfil</option>                       	
		                   		</select>
							</div>
						</div>	
						<div class="form-group">
							<label for="idpestado" class="col-md-5 control-label">Estado</label>
							<div class="col-md-7">								
		                    	<select class="form-control" id="idpestado" name="idpestado">     
		                        	<option value="{debe ir id del estado}">ESTADO</option>                   	
		                        </select>
							</div>
						</div>										
					</div>
						
					<div class="col-md-6">
						<div class="form-group">
							<label for="idptipodocumento" class="col-md-5 control-label">Tipo de documento</label>
							<div class="col-md-7">
								<select class="form-control" id="idptipodocumento" name="idptipodocumento"> 
								 	<option value="{debe ir id de tipo de documento}">DNI</option>                       	
		                   		</select>
							</div>
						</div>
						<div class="form-group">
							<label for="nrodocumento" class="col-md-5 control-label">Nro. documento</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">							
							</div>
						</div>	
						<div class="form-group">
							<label for="btnBsqUsuario" class="col-md-5 control-label">
							</label>
							<div class="col-md-7">
								<button id="btnBsqUsuario"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		style="float:right;padding-top: 3px; padding-bottom: 3px;"
			                    		onclick="javascript:bsqUsuario();">
			                    			<i style="background-position: -592px -1405px; height: 25px; margin-right: 0px; width: 25px; display: inline-block; vertical-align: top; transform: scale(0.6);"></i>			                    			
			                    			<div style="display: inline-block; margin-top: 3px; vertical-align: top;">Buscar</div>			                    			
			                    </button>
			                </div>							
						</div>																											
					</div>										
				</div>	
							
	    	</div>
    	</div>	
	</form>
	
	<div id="container-lst-usuarios" style="margin-top:20px;">
		<table class="table table-hover table-bordered" id="table-lst-usuarios">
			<thead>
				<tr>
					<th class="text-center desktop">Perfil</th>
					<th class="text-center">Cod. Usuario</th>
					<th class="text-center">Nombres</th>					
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>a</td>
					<td class="desktop">s</td>
					<td>f</td>
					<td>g</td>
				</tr>
				<tr>
					<td>2</td>
					<td>q</td>
					<td class="desktop">e</td>
					<td>r</td>
					<td>t</td>
				</tr>
				<tr>
					<td>3</td>
					<td>z</td>
					<td class="desktop">c</td>
					<td>v</td>
					<td>b</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>
	
    $().ready(function(){
    	
    	callCargaControlParam('PARAM_TIPODOCUMENTO','form-bsqcolaborador #tipdocumento');
    	
    	callCargaControlParam('PARAM_ESTADOS','form-bsqcolaborador #estado');
    	
    	loadPerfiles("#form-bsqusuario","#idperfil");
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#table-lst-usuarios").DataTable({
			"order"				:  [[ 0, "asc" ]],
			"searching"	 		: true,
			"paging"	 		: false,
          	"bInfo"		 		: true,
          	"bAutoWidth" 		: false,
          	"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"}			
		});
				
	});
    
	function bsqUsuario(){
	
		var param 	= new Object();
		param 		= $("#form-bsqusuario").serializeArray();
		
		loadModalCargando();

		setTimeout(
			  		function(){
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/usuario.do"+"?method=bsqUsuarios",
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
													if(rsp.lstusuarios!= undefined && rsp.lstusuarios.lenght > 0)
														cargarDataTablesUsuarios(rsp.lstusuarios);
												}else
													loadModalMensaje("Atención",message,null);
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR BUSCANDO USUARIOS",null);								
							}			
						});		    					    				
				},1000);    				
    }
    
	function cargarDataTablesUsuarios(lstusuarios){
		
		$("#table-lst-usuarios").DataTable({
			"order"				:  [[ 0, "asc" ]],
			"searching"	 		: true,
			"paging"	 		: true,
          	"bInfo"		 		: true,
          	"bAutoWidth" 		: false,
          	"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
          	"data"		 		: lstusuarios,
			"columns"    		: [
										{ "data"        : "dscperfil",
											"class"		: "text-center"},
			                           	{ "orderable"	: false,
				                         	"data"		: "codusuario"},
                           				{ "orderable"	: false,
				                         	"class"		: "desktop",
		                         			"mRender"  	: function (data, type, full) {
                     	 									return  data.apepaterno + " " + data.apematerno + ", " + data.nombres;}},
	                      				{ "orderable"	: false,
		                      				"data"      : "dscestado",
		                      				"class"		: "text-center"},
	                      				{ "orderable"	: false,
		                      				"data"      : "",
		                      				"class"		: "text-center",
                         	 				"mRender"  	: function (data, type, full) {
	                         	 								return linkDetalleUsuario();
	                         	 							}}										
								],
			"fnDrawCallback"	: function () { mostrarDatatable("#table-lst-usuarios");}
		});
	}
	
	function linkDetalleUsuario(){
		return '<a class="method-ajax" ' +
					'href="/DeliveryTarjetas/usuario.do?method=obtUsuario" >'+
						'<i class="i-detalle"></i>'+
				'</a>';
	}
</script>