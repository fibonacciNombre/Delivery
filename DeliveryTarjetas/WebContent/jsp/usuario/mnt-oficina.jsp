<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div id="mantenimiento-oficina">

	<h3 class="container-title">
		<span class="vineta-title"></span> <span>Edición de oficinas</span>
	</h3>

	<form id="form-bsqcourier">
		<div class="panel panel-default">
			<div class="panel-heading">Buscar oficina</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label for="codoficina" class="col-md-2 control-label">Código
								BBVA</label>
							<div class="col-md-4">
								<%-- ini / mfarfanr / ajustes --%>
								<%--
								<input type="text" class="form-control" id=codoficina
									name="codoficina" maxlength="5">
								--%>
								<input type="text" class="form-control" id=codoficina
									name="codoficina" maxlength="6">
								<%-- fin / mfarfanr / ajustes --%>
							</div>						
							<label for="oficina" class="col-md-2 control-label">Oficina</label>
							<div class="col-md-4">
								<input type="text" class="form-control" id="oficina"
									name="oficina" maxlength="200">
							</div>
						</div>
					</div>									
				</div>
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
									onclick="javascript:bsqCourier();">
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

	<div id="container-lst-courier"
		style="margin-top: 20px; display: none;">
		<table class="table table-hover table-bordered"
			id="table-lstcouriers">
			<thead>
				<tr>
					<th class="text-center">Cod. Bbva</th>
					<th class="text-center tablet">Oficina</th>
					<th class="text-center desktop">Dirección</th>
					<th class="text-center desktop">Ubigeo</th>					
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
	
</div>

<%@include file="/jsp/usuario/det-oficina.jsp" %>

<script>
	$().ready(
			function() {

				loadModalCargando();
				
				cargarDepartamento();
				
				closeModalCargando();
				
				$("#form-bsqcourier #provincia").change(function () 
				{																	
					var dist = $('#form-bsqcourier #distrito');
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
	            									var combo = $('#form-bsqcourier #distrito');
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
				
				$("#form-bsqcourier #departamento").change(function () {										
					var prov = $('#form-bsqcourier #provincia');
					prov.empty();
					
					var dist = $('#form-bsqcourier #distrito');
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
	            									var combo = $('#form-bsqcourier #provincia');
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
		var prov = $('#form-bsqcourier #provincia');
		prov.empty();
		
		var dist = $('#form-bsqcourier #distrito');
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
									var combo = $('#form-bsqcourier #departamento');
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
	
	function bsqCourier() {

		$("#container-lst-courier").hide();
		
		cleanDatatable("table-lstcouriers");

		loadModalCargando();
		
		var ubigeo 			= "";
		var departamento 	= $('#form-bsqcourier #departamento').val();
		var provincia 		= $('#form-bsqcourier #provincia').val();
		var distrito 		= $('#form-bsqcourier #distrito').val();
		var codoficina 		= $('#form-bsqcourier #codoficina').val();
		
		if(codoficina=="")
			codoficina 		= "0";
		
		if(departamento == "0")
			ubigeo 			= "";
		
		else{
			
			ubigeo 			= ubigeo + departamento;
			
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
		
		var param 			= new Object();
		param.codoficina 	= codoficina;
		param.oficina 		= $('#form-bsqcourier #oficina').val();
		param.ubigeo 		= ubigeo;

		setTimeout(
				function() {
					$
							.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/usuario.do?method=lstOficinas",
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
															$("#container-lst-courier").slideDown(1000);
															cargarDataTablesCouries(rsp.lst);
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
													loadModalMensaje("Error", "ERROR BUSCANDO OFICINA", null);
								}
							});
				}, 1000);
	}

	function cargarDataTablesCouries(lstCouriers) {

		$("#table-lstcouriers").DataTable({
			"order" 		: [ [ 0, "asc" ] ],
			"searching" 	: true,
			"paging" 		: true,
			"bInfo" 		: true,
			"bAutoWidth" 	: false,
			"oLanguage" 	: { "sUrl" : "/DeliveryTarjetas/recursos/idioma/es_ES.txt" },
			"data" 			: lstCouriers,
			"columns" 		: [ { "data" 		: "codoficina",
									"sWidth" 	: "10%",
									"class" 	: "text-center"
								}, {
									"orderable"	: false,
									"sWidth" 	: "20%",
									"class"		: "tablet",
									"data" 		: "oficina"
								}, {
									"orderable"	: false,
									"data" 		: "direccion",
									"sWidth"	: "20%",
									"class"		: "desktop"
								}, {
									"orderable"	: false,
									"data"		: "ubigeodescripcion",
									"sWidth"	: "40%",
									"class"		: "desktop"
								},  {
									"orderable"	: false,
									"data" 		: "",
									"sWidth"	: "10%",
									"mRender" 	: function(data, type, full) {
														return linkDetalleCourier(data, full);
									}
								} ],
			"fnDrawCallback" : function() {mostrarDatatable("#table-lstcouriers");}
		});
	}

	function linkDetalleCourier(data, full) {
		enlace = "<a data-toggle='modal' "
					+ "data-target='#modalEditarCourier' "
					+ "onclick='return rowSelected("+ JSON.stringify(JSON.stringify(full)) + ");'>"
						+ "<i class='i-detalle'></i>" 
					+ "</a>";

		return enlace;
	}

	function rowSelected(json) {
		
		loadModalCargando();
				
		json = JSON.parse(json);
		
		$("#form-mntcourier #ubigeo").val(json.ubigeo);
		$("#form-mntcourier #codoficina").val(json.codoficina);
		$("#form-mntcourier #oficina").val(json.oficina);
		$("#form-mntcourier #categoria").val(json.categoria);
		$("#form-mntcourier #direccion").val(json.direccion);
		$("#form-mntcourier #horariolv").val(json.horariolv);
		$("#form-mntcourier #horarios").val(json.horarios);
		$("#form-mntcourier #horariodf").val(json.horariodf);
		$("#form-mntcourier #latitudofi").val(json.latitudofi);
		$("#form-mntcourier #longitudofi").val(json.longitudofi);
		$("#form-mntcourier #idoficina").val(json.idoficina);
		
		closeModalCargando();
	}
</script>