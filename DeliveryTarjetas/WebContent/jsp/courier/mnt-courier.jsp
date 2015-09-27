<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-courier">

	<h3 class="container-title">
		<span class="vineta-title"></span> <span>Edición de courier</span>
	</h3>

	<form id="form-bsqcourier">
		<div class="panel panel-default">
			<div class="panel-heading">Buscar courier</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="codbbva" class="col-md-5 control-label">Código
								Bbva</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id=codbbva
									name="codbbva" maxlength="200">
							</div>
						</div>
						<div class="form-group">
							<label for="rznsocial" class="col-md-5 control-label">Razón
								social</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="rznsocial"
									name="rznsocial" maxlength="200">
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label for="estado" class="col-md-5 control-label">Estado</label>
							<div class="col-md-7">
								<select class="form-control" id="idpestado" name="idpestado">
									<option value="">Todos</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="btnBsqCourier" class="col-md-5 control-label">
							</label>
							<div class="col-md-7">
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
					<th class="text-center tablet">Razón social</th>
					<th class="text-center desktop">Móvil</th>
					<th class="text-center desktop">Correo electrónico</th>
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>

	<%@include file="/jsp/courier/det-courier.jsp"%>
</div>

<script>
	$().ready(
			function() {

				loadModalCargando();

				callCargaControlParam('DELWEB_ESTADO', 'form-bsqcourier #idpestado', true);

				closeModalCargando();
			});

	function bsqCourier() {

		$("#container-lst-courier").hide();
		
		cleanDatatable("table-lstcouriers");

		loadModalCargando();
		
		var param 	= new Object();
		param 		= $("#form-bsqcourier").serializeArray();

		setTimeout(
				function() {
					$
							.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/courier.do?method=lstCourier",
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
													loadModalMensaje("Error", "ERROR BUSCANDO COURIER", null);
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
			"columns" 		: [ { "data" 		: "codbbva",
									"sWidth" 	: "15%",
									"class" 	: "text-center"
								}, {
									"orderable"	: false,
									"sWidth" 	: "35%",
									"class"		: "tablet",
									"data" 		: "rznsocial"
								}, {
									"orderable"	: false,
									"data" 		: "telfmovil",
									"sWidth"	: "15%",
									"class"		: "desktop"
								}, {
									"orderable"	: false,
									"data"		: "correo",
									"sWidth"	: "15%",
									"class"		: "desktop"
								}, {
									"orderable"	: false,
									"data"		: "",
									"sWidth"	: "15%",
									"class"		: "text-center",
									"mRender"  	: function (data, type, full) {
                     	 											return obtDescripcionParametro(CTE_INIT_PARAM_ESTADO, null, full.idpestado);} 
								}, {
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
		
		callCargaControlParam('DELWEB_ESTADO', 'form-mntcourier #idpestado', true);

		callCargaControlParam('DELWEB_TIPODOCUMENTO', 'form-mntcourier #idptipodocumento', false);

		json = JSON.parse(json);
		
		$("#form-mntcourier #idcourier").val(json.idcourier);
		$("#form-mntcourier #codbbva").val(json.codbbva);
		$("#form-mntcourier #rznsocial").val(json.rznsocial);
		$("#form-mntcourier #idptipodocumento").val(json.idptipodocumento);
		$("#form-mntcourier #nrodocumentocou").val(json.nrodocumentocou);
		$("#form-mntcourier #telffijo").val(json.telffijo);
		$("#form-mntcourier #telfmovil").val(json.telfmovil);
		$("#form-mntcourier #direccion").val(json.direccion);
		$("#form-mntcourier #correo").val(json.correo);
		$("#form-mntcourier #idpestado").val(json.idpestado);
		$("#form-mntcourier #observacion").val(json.observacion);
		
		<%-- ini / mfarfanr / ajustes --%>
		validacionNroDocumento();
		<%-- fin / mfarfanr / ajustes --%>
		closeModalCargando();
	}
</script>
