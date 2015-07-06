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
								 <select class="form-control" id="idpestado" name="idpestado">                        	
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
	
	<div id="container-lst-usuariosws" style="margin-top:20px; display: none;">
		<table class="table table-hover table-bordered" id="table-lstusuariosws">
			<thead>
				<tr>
					<th class="text-center">Cod. Usuario</th>
					<th class="text-center tablet">Comentarios</th>
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>				
			</tbody>
		</table>
	</div>
	
	<%@include file="/jsp/usuario/det-usuario-ws.jsp" %>
</div>

<script>
	
    $().ready(function(){
    	
    	loadModalCargando();
    	
    	$("#form-bsqusuariows #idperfil").val(CTE_INIT_IDROL_ADMIN_WS);
    	
    	callCargaControlParam('DELWEB_ESTADO','form-bsqusuariows #idpestado',true);
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		closeModalCargando();
				
	});
    
	function bsqUsuarioWS(){
		
		$("#container-lst-usuariosws").hide();
		
		cleanDatatable("table-lstusuariosws");
		
		var param 	= new Object();
		param 		= $("#form-bsqusuariows").serializeArray();
		
		loadModalCargando();

		setTimeout(
			  		function(){
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/usuario.do"+"?method=lstUsuariosWS",
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
		
		$("#table-lstusuariosws").DataTable({
			"order"				:  [[ 0, "asc" ]],
			"searching"	 		: true,
			"paging"	 		: false,
          	"bInfo"		 		: true,
          	"bAutoWidth" 		: false,
          	"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
          	"data"		 		: lstusuarios,
			"columns"    		: [
										{ "data"        : "codusuario",
											"sWidth"	: "25%",
											"class"		: "text-center"},
			                           	{ "orderable"	: false,
				                         	"data"		: "comentario",
				                         	"class"		: "tablet"},                           				
			                         	{ "orderable"	: false,
		                      				"data"      : "",
		                      				"sWidth"	: "15%",
		                      				"class"		: "text-center",
                         	 				"mRender"  	: function (data, type, full) {
                         	 								return obtDescripcionParametro(CTE_INIT_PARAM_ESTADO, null, full.idpestado);} },
	                      				{ "orderable"	: false,
		                      				"data"      : "",
		                      				"sWidth"	: "15%",
		                      				"class"		: "text-center",
                         	 				"mRender"  	: function (data, type, full) {
	                         	 								return linkDetalleUsuarioWS(full); } },
	                      				{ "orderable" 	: false,
  											"data" 		: "contrasena",
  											"visible"	: false},  											
             	 						{ "orderable" 	: false,
  											"data" 		: "idperfil",
  											"visible"	: false},		                      				
								],
			"fnDrawCallback"	: function () { mostrarDatatable("#table-lstusuariosws");}
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
		
		callCargaControlParam('DELWEB_ESTADO','form-mntusuario-ws #idpestado',false);
    	
    	cargarCombo('/DeliveryTarjetas/perfil.do', 'lstPerfil','cboperfil', ['idperfil','descripcion'], {form: 'form-mntusuario-ws'});
    	
    	$("#form-mntusuario-ws #contrasena-div").remove();		
		$("#form-mntusuario-ws #contrasena-visible-div").show();
		$("#form-mntusuario-ws #contrasena-visible").removeClass("resaltar-background");
		
		$("#form-mntcontrasena-ws").validate({
			rules : {
				contrasena 			: {				required 	: true,
													minlength 	: 5 },
			},
			messages : {
				contrasena 			: {				required 	: "Debes ingresar la nueva contraseña",
													minlength 	: "Debes ingresar un mínimo de 5 carácteres"},
			}
		});
		
		json 				= JSON.parse(json);
		
		var param 			= new Object();
		param.codusuario	= json.codusuario;
		
		$.ajax({
			type 		: "POST",
			url 		: "/DeliveryTarjetas/usuario.do"+"?method=obtUsuario",
			cache 		: false ,
			dataType	: "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			async 		: false,
			data 		: param,		
			success 	: function(rsp){

							var statustx	= rsp.tx.statustx;
							var messagetx	= rsp.tx.messagetx;
							
							closeModalCargando();
							
							if(statustx == 0){								
								$("#form-mntusuario-ws #contrasena").val(rsp.usuario.contrasena);
								$("#form-mntusuario-ws #idusuario").val(rsp.usuario.idusuario);
								$("#form-mntusuario-ws #cboperfil").val(rsp.usuario.idperfil);
								$("#form-mntusuario-ws #idperfil").val(rsp.usuario.idperfil);
								$("#form-mntusuario-ws #idpestado").val(rsp.usuario.idpestado);
								$("#form-mntusuario-ws #codusuario").val(rsp.usuario.codusuario);
								$("#form-mntusuario-ws #contrasena-visible").val(rsp.usuario.contrasena);
								$("#form-mntusuario-ws #comentario").val(rsp.usuario.comentario);
								
								$("#form-mntusuario-ws #contrasena-visible").attr("readonly",true);
								$("#form-mntusuario-ws #cboperfil").attr("disabled","disabled");
								
							}
			}
		});
	
	}
</script>