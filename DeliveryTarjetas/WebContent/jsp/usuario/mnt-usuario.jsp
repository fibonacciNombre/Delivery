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
		                    	<select class="form-control" id="idperfil" name="idperfil" onchange="javascript:validarExtra();">                        	
		                   		</select>
		                   		<input type="hidden" class="form-control" id="noidperfil" name="noidperfil" maxlength="200" >
		                   		
		                   		
							</div>
						</div>	
						<div class="form-group">
							<label for="idpestado" class="col-md-5 control-label">Estado</label>
							<div class="col-md-7">								
		                    	<select class="form-control" id="idpestado" name="idpestado">                        	
		                        </select>
							</div>
						</div>										
					</div>
						
					<div class="col-md-6">
						<div class="form-group">
							<label for="idptipodocumento" class="col-md-5 control-label">Tipo de documento</label>
							<div class="col-md-7">
								<select class="form-control" id="idptipodocumento" name="idptipodocumento">                    	
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
	
	<div id="container-lst-usuarios" style="margin-top:20px;display: none;">
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
			</tbody>
		</table>
	</div>
</div>

<%@include file="/jsp/usuario/det-usuario.jsp" %>

<script>
	
    $().ready(function(){
    	
		loadModalCargando();
    	
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-bsqusuario #idptipodocumento',false); 
    	
		callCargaControlParam('DELWEB_ESTADO','form-bsqusuario #idpestado',false);
    	 
		cargarCombo('/DeliveryTarjetas/perfil.do', 'lstPerfil','idperfil',  ['idperfil','descripcion'], {form: 'form-bsqusuario'});
		
		$("#form-bsqusuario #idperfil option[value='"+CTE_INIT_IDROL_ADMIN_WS+"']").remove();
		
		$("#form-bsqusuario #noidperfil").val(CTE_INIT_IDROL_ADMIN_WS);
		
		$("#contrasena-div").remove();
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		closeModalCargando();
	});
    
	function bsqUsuario(){
		
		$("#container-lst-usuarios").hide();
		
		$('#table-lst-usuarios').dataTable().fnClearTable();
		$('#table-lst-usuarios').dataTable().fnDestroy();
		
		var param 	= new Object();
		param 		= $("#form-bsqusuario").serializeArray();
		
		loadModalCargando();

		setTimeout(
			  		function(){
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/usuario.do"+"?method=lstUsuarios",
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
													if(rsp.lst!= undefined && rsp.lst.length > 0){
														$("#container-lst-usuarios").slideDown(1000);
														cargarDataTablesUsuarios(rsp.lst);
													}else
														loadModalMensaje("Atención","No se encontraron resultados por la búsqueda realizada",null);
													
												}else
													loadModalMensaje("Atención",message,null);
							},						
							error		: function (rsp, xhr, ajaxOptions, thrownError) {
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
										{ "data"        : "idperfil",
											"class"		: "text-center"},
			                           	{ "orderable"	: false,
				                         	"data"		: "codusuario"},
				                         	
                           				{ "orderable"	: false,
				                         	"class"		: "desktop",
		                         			"mRender"  	: function (data, type, full) {
                     	 									return  full.nombres + " " + full.apepaterno + " " + full.apematerno;} },
	                      				{ "orderable"	: false,
		                      				"data"      : "idpestado",
		                      				"class"		: "text-center"},
	                      				{ "orderable"	: false,
		                      				"data"      : "",
		                      				"class"		: "text-center",
                         	 				"mRender"  	: function (data, type, full) {
	                         	 								return linkDetalleUsuario(full); } },
       	 								{ "orderable" 	: false,
  											"data" 		: "idusuario",
  											"visible"	: false},
       	 								{ "orderable" 	: false,
  											"data" 		: "idtercero",
  											"visible"	: false},  											
             	 						{ "orderable" 	: false,
  											"data" 		: "idperfil",
  											"visible"	: false},
										{ "orderable" 	: false,
											"data" 		: "idcourier",
											"visible"	: false},
                    	 				{ "orderable" 	: false,
											"data" 		: "idptipodocumento",
											"visible"	: false},
										{  "orderable"  : false,
											"data" 		: "nombres",
											"visible"	: false}, 
										{ "orderable" 	: false,
											"data" 		: "apepaterno",
											"visible"	: false}, 
										{ "orderable" 	: false,
											"data" 		: "apematerno",
											"visible"	: false} 
								],
			"fnDrawCallback"	: function () { mostrarDatatable("#table-lst-usuarios");}
		});
	}
	
	function linkDetalleUsuario(full) {
		enlace = "<a data-toggle='modal' "
					+ "data-target='#modalEditarUsuario' "
					+ "onclick='return rowSelected("+ JSON.stringify(JSON.stringify(full)) +");'>"
					+ "<i class='i-detalle'></i>" 
				+ "</a>";

		return enlace;
	}

	function rowSelected(json) {
		json = JSON.parse(json);
		
		$("#form-mntusuario #idusuario").val(json.idusuario);
		$("#form-mntusuario #idtercero").val(json.idtercero);
		$("#form-mntusuario #idperfil").val(json.idperfil);
		$("#form-mntusuario #idcourier").val(json.idcourier);
		$("#form-mntusuario #nombres").val(json.nombres);
		$("#form-mntusuario #apepaterno").val(json.apepaterno);
		$("#form-mntusuario #apematerno").val(json.apematerno);
		$("#form-mntusuario #idptipodocumento").val(json.idptipodocumento);
		$("#form-mntusuario #nrodocumento").val(json.nrodocumento);
		$("#form-mntusuario #correo").val(json.correo);
		$("#form-mntusuario #telfmovil").val(json.telfmovil);
		$("#form-mntusuario #idpestado").val(json.idpestado);
		$("#form-mntusuario #codusuario").val(json.codusuario);
		$("#form-mntusuario #contrasena").val(json.contrasena);
	}
</script>