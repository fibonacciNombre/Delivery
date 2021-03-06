<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-colaboradorescourier">

	<h3 class="container-title">
		<span class="vineta-title"></span> <span>Edición de
			colaboradores por courier</span>
	</h3>

	<form id="form-bsqcolaborador">
		<div class="panel panel-default">
			<div class="panel-heading">Buscar colaboradores por courier</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="idptipodocumento" class="col-md-5 control-label">Tipo
								de documento</label>
							<div class="col-md-7">
								<select class="form-control" id="idptipodocumento" name="idptipodocumento">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="nrodocumento" class="col-md-5 control-label">Nro.
								documento</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="nrodocumento" name="nrodocumento" maxlength="8">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="idcourier" class="col-md-5 control-label">Courier</label>
							<div class="col-md-7">
								<select class="form-control" id="cbocourier" name="cbocourier"></select>
								<input type="hidden" id="idcourier" name="idcourier"/>
							</div>
						</div>
						<div class="form-group">
							<label for="" idpestado"" class="col-md-5 control-label">Estado
								del colaborador </label>
							<div class="col-md-7">
								<select class="form-control" id="idpestado" name="idpestado">
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6"></div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="btnBsqColaborador" class="col-md-5 control-label">
							</label>
							<div class="col-md-7">
								<button id="btnBsqColaborador" type="button"
									class="btn btn-primary"
									style="float: right; padding-top: 3px; padding-bottom: 3px;"
									onclick="javascript:bsqColaborador();">
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

	<div id="container-lst-colaboradorescourier" style="margin-top: 20px; display: none;">
		<table class="table table-hover table-bordered"
			id="table-lstcolaboradores">
			<thead>
				<tr>
					<th class="text-center tablet">Courier</th>
					<th class="text-center">Nro Documento</th>					
					<th class="text-center desktop">Nombre</th>					
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	<%@include file="/jsp/courier/det-colaboradorcourier.jsp" %>
</div>

<script>
	$().ready(function() {
		
		loadModalCargando();
		
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-bsqcolaborador #idptipodocumento',true);
		
		callCargaControlParam('DELWEB_ESTADO','form-bsqcolaborador #idpestado', false);

		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','cbocourier', ['idcourier','rznsocial'], {form : 'form-bsqcolaborador'});
		
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!=""){
    		$("#form-bsqcolaborador #cbocourier").val($("#form-datos-usuario #idcourier").val());
    		$("#form-bsqcolaborador #idcourier").val($("#form-datos-usuario #idcourier").val());    		
    		$("#form-bsqcolaborador #cbocourier").attr("disabled","disabled");
    	}
		
		closeModalCargando();

	});

	function bsqColaborador() {
		
		$("#container-lst-colaboradorescourier").hide();
		
		$('#table-lstcolaboradores').dataTable().fnClearTable();
		$('#table-lstcolaboradores').dataTable().fnDestroy();
		
		cleanDatatable("table-lstmntdelivery");
		
		loadModalCargando();

		$("#form-bsqcolaborador #cbocourier").attr("disabled",false);
		$("#form-bsqcolaborador #idcourier").val($("#form-bsqcolaborador #cbocourier").val());
		
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!="")
			$("#form-bsqcolaborador #cbocourier").attr("disabled",true);
		
		var param 	= new Object();
		param 		= $("#form-bsqcolaborador").serializeArray();

		setTimeout(
				function() {
						$.ajax({
								type 		: "POST",
								url 		: "/DeliveryTarjetas/courier.do?method=lstColaboradores",
								cache 		: false,
								dataType 	: "json",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								async 		: false,
								data 		: param,
								success 	: function(rsp) {

													var status 	= rsp.tx.statustx;
													var message = rsp.messagetx;
													
													closeModalCargando();
													
													if(status == 0){													
														if(rsp.lst!= undefined && rsp.lst.length > 0){
															$("#container-lst-colaboradorescourier").slideDown(1000);
															cargarDataTablesColaboradores(rsp.lst);
														} else
															loadModalMensaje(
																	"Atención",
																	"No se encontraron resultados por la búsqueda realizada",
																	null);
				
													} else
														loadModalMensaje("Atención", message, null);
								},
								error 		: function(rsp, xhr, ajaxOptions, thrownError) {
													closeModalCargando();
													loadModalMensaje("Error","ERROR BUSCANDO COLABORADORES",null);
								}
							});
				}, 1000);
	}

	function cargarDataTablesColaboradores(lstTerceros) {

		$("#table-lstcolaboradores").DataTable({
			"order" 		: [ [ 0, "asc" ] ],
			"searching" 	: true,
			"paging" 		: true,
			"bInfo" 		: true,
			"bAutoWidth" 	: false,
			"oLanguage" 	: {"sUrl" : "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
			"data" 			: lstTerceros,
			"columns" 		: [ {
									"orderable" : false,
									"data" 		: "",
									"sWidth" 	: "20%",
									"class"		: "tablet",
									"mRender"  	: function (data, type, full) {
                 	 												return obtDescripcionCourier(full.idcourier);}
								},{
									"data"		: "nrodocumento",
									"sWidth" 	: "15%",
									"class"		: "text-center"
								},{
									"orderable" : false,
									"data" 		: "nombres",
									"sWidth" 	: "40%",
									"class"		: "desktop",
									"mRender" 	: function(data, type, full) {
														return full.nombres + " " + full.apepaterno + " " + full.apematerno; }
								}, {
									"orderable" : false,
									"data" 		: "",
									"sWidth" 	: "15%",
									"class"		: "text-center",
									"mRender"  	: function (data, type, full) {
                    	 												return obtDescripcionParametro(CTE_INIT_PARAM_ESTADO, null, full.idpestado);}
								}, {
									"orderable" : false,
									"sWidth" 	: "10%",
									"mRender" 	: function(data, type, full) {
														return linkDetalleColaborador(full); } 
								}],
			"fnDrawCallback" : function() {mostrarDatatable("#table-lstcolaboradores");}
		});
	}

	function linkDetalleColaborador(full) {

		enlace = "<a data-toggle='modal' "
					+ "data-target='#modalEditarColaborador' "
					+ "onclick='return rowSelected("+ JSON.stringify(JSON.stringify(full)) +");'>"
						+ "<i class='i-detalle'></i>" 
				+ "</a>";

		return enlace;
	}

	function rowSelected(json) {
		
		loadModalCargando();
		
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-mntcolaborador #idptipodocumento',false);
		
		callCargaControlParam('DELWEB_ESTADO','form-mntcolaborador #idpestado',true);
		
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','cbocourier', ['idcourier','rznsocial'], {form : 'form-mntcolaborador'});
		
		json = JSON.parse(json);
		
		$("#form-mntcolaborador #idtercero").val(json.idtercero);
		$("#form-mntcolaborador #nombres").val(json.nombres);
		$("#form-mntcolaborador #apepaterno").val(json.apepaterno);
		$("#form-mntcolaborador #apematerno").val(json.apematerno);
		$("#form-mntcolaborador #idptipodocumento").val(json.idptipodocumento);
		$("#form-mntcolaborador #nrodocumento").val(json.nrodocumento);
		$("#form-mntcolaborador #telfmovil").val(json.telfmovil);
		$("#form-mntcolaborador #telffijo").val(json.telffijo);
		$("#form-mntcolaborador #correo").val(json.correo);
		$("#form-mntcolaborador #idcourier").val(json.idcourier);
		$("#form-mntcolaborador #cbocourier").val(json.idcourier);
		$("#form-mntcolaborador #idpestado").val(json.idpestado);
		
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!="")
			$("#form-mntcolaborador #cbocourier").attr("disabled",true);
		
		closeModalCargando();
	}
</script>
