<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-usuariows">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Servicios web - Edición de usuarios</span>
	</h3>
	
    <form id="form-bsqusuariows">
    	<div class="panel panel-default">
	    	<div class="panel-heading">
				Buscar usuarios de Servicios web
			</div>    	
	    	<div class="panel-body">	    		
	    		<div class="row">						
					<div class="col-md-6">						
						<div class="form-group">
							<label for="codusuario" class="col-md-5 control-label">Codigo usuario</label>
		                    <div class="col-md-7">
		                        <input type="text" class="form-control" id=codusuario name="codusuario" maxlength="200">
		                        <input type="hidden" class="form-control" id="idperfil" name="idperfil" >
		                    </div>
	                	</div>												
					</div>
					
					<div class="col-md-6">						
						<div class="form-group">
							<label for="estado" class="col-md-5 control-label">Estado</label>
							<div class="col-md-7">								
								 <select class="form-control" id="estado" name="estado">                        	
                        		</select>								
							</div>
						</div>							
						<div class="form-group">
							<label for="btnBsqUsuarioWS" class="col-md-5 control-label">
							</label>
							<div class="col-md-7">
								<button id="btnBsqUsuarioWS"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		style="float:right;padding-top: 3px; padding-bottom: 3px;"
			                    		onclick="javascript:bsqUsuarioWS();">
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
	
	<div id="container-lst-usuariosws" style="margin-top:20px;">
		<table class="table table-hover table-bordered" id="table-lst-usuariosws">
			<thead>
				<tr>
					<th class="text-center">Cod. Usuario</th>
					<th class="text-center desktop">Password</th>
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>				
			</tbody>
		</table>
	</div>
</div>

<script>
	
    $().ready(function(){
    	
    	loadModalCargando();
    	
    	callCargaControlParam('DELWEB_ESTADO','form-bsqusuariows #idpestado',true);
    	
    	callCargaControlParam('DELWEB_ESTADO','form-mntusuario-ws #idpestado',false);
    	
    	cargarCombo('/DeliveryTarjetas/perfil.do', 'lstPerfil','cboperfil', ['idcourier','rznsocial'], {form: 'form-mntusuario-ws'});
    	
    	// TRAER EL ID DEL PERFIL "USUARIO SERVICIO WEB"
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		closeModalCargando();
				
	});
    
	function bsqUsuarioWS(){
		
		$("#container-lst-usuariosws").hide();
		
		$('#table-lst-usuarios').dataTable().fnClearTable();
		$('#table-lst-usuarios').dataTable().fnDestroy();
		
		var param 	= new Object();
		param 		= $("#form-bsqusuariows").serializeArray();
		
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
														$("#container-lst-usuariosws").slideDown(1000);
														cargarDataTablesUsuariosWS(rsp.lst);
													}else
														loadModalMensaje("Atención","No se encontraron resultados por la búsqueda realizada",null);
													
												}else
													loadModalMensaje("Atención",message,null);
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR BUSCANDO USUARIOS DE SERVICIOS WEB",null);								
							}			
						});		    					    				
				},1000);    				
    }
    
	function cargarDataTablesUsuariosWS(lstusuarios){
		
		$("#table-lst-usuariosws").DataTable({
			"order"				:  [[ 0, "asc" ]],
			"searching"	 		: true,
			"paging"	 		: false,
          	"bInfo"		 		: true,
          	"bAutoWidth" 		: false,
          	"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
          	"data"		 		: lstusuarios,
			"columns"    		: [
										{ "data"        : "codusuario",
											"class"		: "text-center"},
			                           	{ "orderable"	: false,
				                         	"data"		: "contrasena"},                           				
	                      				{ "orderable"	: false,
		                      				"data"      : "idpestado",
		                      				"class"		: "text-center"},
	                      				{ "orderable"	: false,
		                      				"data"      : "",
		                      				"class"		: "text-center",
                         	 				"mRender"  	: function (data, type, full) {
	                         	 								return linkDetalleUsuarioWS(full); } },
	                      				{ "orderable" 	: false,
  											"data" 		: "idusuario",
  											"visible"	: false},  											
             	 						{ "orderable" 	: false,
  											"data" 		: "idperfil",
  											"visible"	: false},		                      				
								],
			"fnDrawCallback"	: function () { mostrarDatatable("#table-lst-usuariosws");}
		});
	}
	
	function linkDetalleUsuarioWS(full) {
		enlace = "<a data-toggle='modal' "
					+ "data-target='#modalEditarUsuarioWS' "
					+ "onclick='return rowSelected("+ JSON.stringify(JSON.stringify(full)) +");'>"
					+ "<i class='i-detalle'></i>" 
				+ "</a>";

		return enlace;
	}

	function rowSelected(json) {
		json = JSON.parse(json);
		
		$("#form-mntusuario-ws #idusuario").val(json.idusuario);
		$("#form-mntusuario-ws #cboperfil").val(json.idperfil);
		$("#form-mntusuario-ws #idperfil").val(json.idperfil);
		$("#form-mntusuario-ws #idpestado").val(json.idpestado);
		$("#form-mntusuario-ws #codusuario").val(json.codusuario);
		$("#form-mntusuario-ws #contrasena").val(json.contrasena);
		
		$("#form-mntusuario-ws #cboperfil").attr("disabled","disabled");
	}
</script>