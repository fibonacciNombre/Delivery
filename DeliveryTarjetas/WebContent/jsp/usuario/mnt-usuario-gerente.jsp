<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div id="mantenimiento-oficina-usuario">

	<h3 class="container-title">
		<span class="vineta-title"></span> <span>Edición de gerentes</span>
	</h3>

	<form id="form-bsqgerente">
		<div class="panel panel-default">
			<div class="panel-heading">Buscar oficina</div>
			<div class="panel-body">				
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label for="departamento" class="col-md-2 control-label">Departamento</label>
							<div class="col-md-4">
								<select class="form-control" id="departamento" name="departamento">
									<option value="0">Todos</option>
								</select>
							</div>						
							<label for="provincia" class="col-md-2 control-label">Provincia</label>
							<div class="col-md-4">
								<select class="form-control" id="provincia" name="provincia">
									<option value="0">Todos</option>
								</select>
							</div>
						</div>
					</div>	
				</div>				
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label for="distrito" class="col-md-2 control-label">Distrito</label>
							<div class="col-md-4">
								<select class="form-control" id="distrito" name="distrito">
									<option value="0">Todos</option>
								</select>
							</div>						
							<label for="btnBsqCourier" class="col-md-2 control-label">
							</label>
							<div class="col-md-4">
								<button id="btnBsqCourier" type="button" class="btn btn-primary"
									style="float: right; padding-top: 3px; padding-bottom: 3px;"
									onclick="javascript:bsqGerente();">
									<i
										style="background-position: -592px -1405px; height: 25px; margin-right: 0px; width: 25px; display: inline-block; vertical-align: top; transform: scale(0.6);"></i>
									<div
										style="display: inline-block; margin-top: 3px; vertical-align: top;">Buscar</div>
								</button>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div id="container-lst-gerentes"
		style="margin-top: 20px; display: none;">
		<table class="table table-hover table-bordered"
			id="table-lstgerentes">
			<thead>
				<tr>
					<th class="text-center tablet">Perfil</th>
					<th class="text-center">Cod. Usuario</th>
					<th class="text-center desktop">Nombres</th>
					<th class="text-center">Oficina</th>					
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
	
</div>

<%@include file="/jsp/usuario/det-usuario-gerente.jsp" %>

<script>
	$().ready(
			function() {

				loadModalCargando();
				
				cargarDepartamento();
				
				closeModalCargando();
				
				$("#form-bsqgerente #provincia").change(function () 
				{																	
					var dist = $('#form-bsqgerente #distrito');
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
	            									var combo = $('#form-bsqgerente #distrito');
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
				
				$("#form-bsqgerente #departamento").change(function () {										
					var prov = $('#form-bsqgerente #provincia');
					prov.empty();
					
					var dist = $('#form-bsqgerente #distrito');
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
	            									var combo = $('#form-bsqgerente #provincia');
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
	
	function cargarDepartamento(){
		var prov = $('#form-bsqgerente #provincia');
		prov.empty();
		
		var dist = $('#form-bsqgerente #distrito');
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
									var combo = $('#form-bsqgerente #departamento');
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
	
	function bsqGerente() {

		$("#container-lst-gerentes").hide();
		
		cleanDatatable("table-lstgerentes");

		loadModalCargando();
		
		var ubigeo 			= "";
		var departamento 	= $('#form-bsqgerente #departamento').val();
		var provincia 		= $('#form-bsqgerente #provincia').val();
		var distrito 		= $('#form-bsqgerente #distrito').val();
		var codoficina 		= $('#form-bsqgerente #codoficina').val();
		
		if(codoficina=="")
			codoficina = "0";
		
		if(departamento == "0")
			ubigeo = "";
		else{
			
			ubigeo = ubigeo + departamento;
			
			/** ini / mfarfanr / ajustes **/
			//if(provincia!="0" || provincia!=undefined || provincia!=""){
			if(provincia!=undefined && provincia!=null && provincia!="" && provincia!="0"){
				ubigeo 		= provincia;
			}
			//if(distrito!="0" || distrito!=undefined || distrito!=""){
			if(distrito!=undefined && distrito!=null && distrito!="" && distrito!="0"){
				ubigeo 		= distrito;				
			}
			/** fin / mfarfanr / ajustes **/			
		}		
		
		var param 	= new Object();
		param.ubigeo = ubigeo;

		setTimeout(
				function() {
					$
							.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/usuario.do?method=lstUsuarios2",
								cache 		: false,
								dataType 	: "json",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								async 		: false,
								data 		: param,
								success 	: function(rsp) {

													var status = rsp.tx.statustx;
													var message = rsp.tx.messagetx;
				
													closeModalCargando(); 
													if (status == 0) {
														if (rsp.lst != undefined && rsp.lst.length > 0) {
															$("#container-lst-gerentes").slideDown(1000);
															cargarDataTablesGerentes(rsp.lst);
														} else
															loadModalMensaje(
																	"Atención",
																	"No se encontraron resultados por la búsqueda realizada",
																	null);
				
													} else
														loadModalMensaje("Atención", message, null);
								},
								error 		: function(rsp, xhr, ajaxOptions,thrownError) {
													closeModalCargando();
													loadModalMensaje("Error", "ERROR BUSCANDO USUARIOS", null);
								}
							});
				}, 1000);
	}

	function cargarDataTablesGerentes(lstGerentes) {

		$("#table-lstgerentes").DataTable({
			"order" 		: [ [ 0, "asc" ] ],
			"searching" 	: true,
			"paging" 		: true,
			"bInfo" 		: true,
			"bAutoWidth" 	: false,
			"oLanguage" 	: { "sUrl" : "/DeliveryTarjetas/recursos/idioma/es_ES.txt" },
			"data" 			: lstGerentes,
			"columns"    		: [
									{ "data"      	: "",
										"sWidth"	: "10%",
										"class"		: "text-center tablet",
                    	 				"mRender"  	: function (data, type, full) {
        	 												return obtDescripcionPerfil(full.idperfil);} },											
		                           	{ "orderable"	: false,
			                         	"data"		: "codusuario",
			                         	"sWidth"	: "15%"},
		                         	{ "orderable"	: false,
			                         	"data"		: "nombrecompleto",
			                         	"sWidth"	: "25%",
			                         	"class"		: "desktop"},
		                         	{ "orderable"	: false,
			                         	"data"		: "oficina",
			                         	"sWidth"	: "25%"},
                     				{ "orderable"	: false,
	                      				"data"      : "",
	                      				"sWidth"	: "15%",
	                      				"class"		: "text-center",
                    	 				"mRender"  	: function (data, type, full) {
                    	 								return obtDescripcionParametro(CTE_INIT_PARAM_ESTADO, null, full.idpestado);} },
  	 								{ "orderable"	: false,
		                      				"data"      : "",
		                      				"sWidth"	: "10%",
		                      				"class"		: "text-center",
                      	 					"mRender"  	: function (data, type, full) {
	                         	 								return linkDetalleGerente(full); } },	                         	 								
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
										"visible"	: false} 
							],
			"fnDrawCallback" : function() {mostrarDatatable("#table-lstgerentes");}
		});
	}

	function linkDetalleGerente(full) {
		enlace = "<a data-toggle='modal' "
					+ "data-target='#modalEditarGerente' "
					+ "onclick='return rowSelected("+ JSON.stringify(JSON.stringify(full)) +");'>"
					+ "<i class='i-detalle'></i>" 
				+ "</a>";

		return enlace;
	}

	function rowSelected(json) {
		
		loadModalCargando();
		
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-mntgerente #idptipodocumento',false);  
		
		callCargaControlParam('DELWEB_ESTADO','form-mntgerente #idpestado',false);
    	 
		cargarCombo('/DeliveryTarjetas/perfil.do', 'lstPerfil','cboperfil',  ['idperfil','descripcion'], {form: 'form-mntgerente'});
		
    	$("#form-mntgerente #cboperfil option[value='"+CTE_INIT_IDROL_ADMIN_WS+"']").remove();
    	
		$("#form-mntgerente #contrasena-div").hide();
		
		json = JSON.parse(json);
		
		var paramTercero 		= new Object();
		paramTercero.idtercero	= json.idtercero;
		
		var paramOficina 		= new Object();
		paramOficina.idoficina	= json.idoficina;
		
		$.ajax({
			type 		: "POST",
			url 		: "/DeliveryTarjetas/tercero.do?method=lstTerceros",
			cache 		: false,
			dataType 	: "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			async 		: false,
			data 		: paramTercero,
			success 	: function(rsp) {

								var status 	= rsp.tx.statustx;
								var message = rsp.tx.messagetx;
								
								if(status == 0){													
									if(rsp.lst!= undefined && rsp.lst.length > 0){
										$("#form-mntgerente #idptipodocumento").val(rsp.lst[0].idptipodocumento);
										$("#form-mntgerente #nrodocumento").val(rsp.lst[0].nrodocumento);
										$("#form-mntgerente #nombres").val(rsp.lst[0].nombres);
										$("#form-mntgerente #apepaterno").val(rsp.lst[0].apepaterno);
										$("#form-mntgerente #apematerno").val(rsp.lst[0].apematerno);
										$("#form-mntgerente #correo").val(rsp.lst[0].correo);
										$("#form-mntgerente #telfmovil").val(rsp.lst[0].telfmovil);
										$("#form-mntgerente #idcourier").val(rsp.lst[0].idcourier);
										$("#form-mntgerente #cbocourier").val(rsp.lst[0].idcourier);
										
										$.ajax({
											type 		: "POST",
											url 		: "/DeliveryTarjetas/usuario.do?method=obtOficina",
											cache 		: false,
											dataType 	: "json",
											contentType : "application/x-www-form-urlencoded; charset=UTF-8",
											async 		: false,
											data 		: paramOficina,
											success 	: function(rspOficina) {
															
															
															
															var status 	= rspOficina.tx.statustx;
															var message = rspOficina.tx.messagetx;
															
															if(status == 0){								
																var jsonOficina	= rspOficina.oficina;
																
																var ubigeo		= jsonOficina.ubigeo;
																
																var departamento;
																var provincia;
																var distrito;
																
																if(ubigeo.length==6){
																	departamento 	= ubigeo.substring(0,2);
																	provincia 		= ubigeo.substring(0,4);
																	distrito 		= ubigeo;
																	
																	$("#form-mntgerente #departamento").val(departamento);
																	$("#form-mntgerente #departamento").change();
																	
																	$("#form-mntgerente #provincia").val(provincia);
																	$("#form-mntgerente #provincia").change();
																	
																	$("#form-mntgerente #distrito").val(distrito);
																	$("#form-mntgerente #distrito").change();
																	
																	$("#form-mntgerente #oficina").val(jsonOficina.idoficina);
																}
															
																closeModalCargando();
															}
											},
											error 		: function(rsp, xhr, ajaxOptions, thrownError) {
																closeModalCargando();
																loadModalMensaje("Error","ERROR OBTENIENDO LA INFORMACION DEL TERCERO",null);
											}
										});
									}
								}
			},
			error 		: function(rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR OBTENIENDO LA INFORMACION DEL TERCERO",null);
			}
		});
		
		$("#form-mntgerente #idusuario").val(json.idusuario);
		$("#form-mntgerente #idtercero").val(json.idtercero);
		$("#form-mntgerente #idperfil").val(json.idperfil);
		$("#form-mntgerente #cboperfil").val(json.idperfil);				
		$("#form-mntgerente #idpestado").val(json.idpestado);
		$("#form-mntgerente #codusuario").val(json.codusuario);
		$("#form-mntgerente #contrasena").val(json.contrasena);
		$("#form-mntgerente #comentario").val(json.comentario);
		
		$("#form-mntgerente").valid();
		
		closeModalCargando();
	}
	
</script>