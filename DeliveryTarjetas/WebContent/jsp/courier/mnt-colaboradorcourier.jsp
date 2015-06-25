<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-colaboradorescourier">

	<h3 class="container-title">
		<span class="vineta-title"></span> <span>Edición de
			colaboradores por courier</span>
	</h3>

	<div id="modalEditarColaborador">
		<div class="modal fade" id="modalColaboradorEdit" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>

						<h3 class="modal-title">Editar datos del colaborador Courier</h3>
					</div>


					<form id="form-colaborador-edit">

						<input type="hidden" id="idtercero" name="idtercero" value="" />

						<div class="column-formulario-carga">

							<div class="form-group form-group-carga">
								<label for="nombres"
									class="col-md-5 control-label carga-content-maxim">Nombres</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nombres" name="nombres"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>

							<div class="form-group form-group-carga">
								<label for="apepaterno"
									class="col-md-5 control-label carga-content-maxim">Apellido
									Paterno</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="apepaterno" name="apepaterno"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>


<div class="form-group form-group-carga">
								<label for="apematerno"
									class="col-md-5 control-label carga-content-maxim">Apellido
									Materno</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="apematerno" name="apematerno"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
						</div>


						<div class="column-formulario-carga">

							<div class="form-group form-group-carga">
								<label for=""
									class="col-md-5 control-label carga-content-maxim required">Tipo
									de documento de identidad: </label>
								<div class="col-md-7 carga-content-maxim">
									<select class="form-control" id="idptipodocumento"
										name="idptipodocumento"></select>
									<div class="result"></div>
								</div>
							</div>

							<div class="form-group form-group-carga">
								<label for="nrodocumento"
									class="col-md-5 control-label carga-content-maxim">Nro
									Documento</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nrodocumento" name="nrodocumento"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>

<div class="form-group form-group-carga">
								<label for=""
									class="col-md-5 control-label carga-content-maxim required">Courier </label>
								<div class="col-md-7 carga-content-maxim">
									<select class="form-control" id="idcourier" name="idcourier">
										<option value="{debe ir el codbbva}">Nombre courier</option>
									</select>
									<div class="result"></div>
								</div>
							</div>
						</div>

						 

						<div class="row">

							<div class="col-md-12">
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-6 control-label" style="padding: 0px;">
										</label>
										<div class="col-sm-6 pull-right"
											style="text-align: right; padding: 0px;">
											<button type="button" class="btn btn-default"
												onclick="javascript:salir();">Salir</button>

											<button id="btnRegistrar" type="button"
												class="btn btn-primary"
												onclick="javascript:actualizarCourier();">Modificar</button>
										</div>
									</div>
								</div>
							</div>

						</div>


					</form>
				</div>


			</div>
		</div>

	</div>

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
								<select class="form-control" id="idptipodocumento"
									name="idptipodocumento">
									<option value="{debe ir id de tipo de documento}">DNI</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="nrodocumento" class="col-md-5 control-label">Nro.
								documento</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="nrodocumento"
									name="nrodocumento" maxlength="12">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="idcourier" class="col-md-5 control-label">Courier</label>
							<div class="col-md-7">
								<select class="form-control" id="idcourier" name="idcourier">
									<option value="{debe ir el codbbva}">Nombre courier</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" idpestado"" class="col-md-5 control-label">Estado
								del colaborador </label>
							<div class="col-md-7">
								<select class="form-control" id="idpestado" name="idpestado">
									<option>Todos</option>
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

	<div id="container-lst-colaboradores-courier" style="margin-top: 20px;">
		<table class="table table-hover table-bordered"
			id="table-lst-colaboradores">
			<thead>
				<tr>
					<th class="text-center">Nro Documento</th>
					<th class="text-center">Nombre</th>
					<th class="text-center">Courier</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>

<script
	src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>
	$().ready(
			function() {

				var paramBsqCourier = new Object();

				callCargaControlParam('DELWEB_TIPODOCUMENTO',
						'form-colaborador-edit #idptipodocumento');
				callCargaControlParam('DELWEB_TIPODOCUMENTO',
						'form-bsqcolaborador #idptipodocumento');
				callCargaControlParam('DELWEB_ESTADO',
						'form-bsqcolaborador #idpestado');

				cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier',
						'idcourier', {
							form : 'form-bsqcolaborador'
						});
				cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier',
						'idcourier', {
							form : 'form-colaborador-edit'
						});

				jQuery.validator.addMethod("alphanumeric", function(value,
						element) {
					return this.optional(element)
							|| /^[a-zA-Z0-9]+$/.test(value);
				});

			});

	function bsqColaborador() {

		var param = new Object();
		param = $("#form-bsqcolaborador").serializeArray();

		loadModalCargando();

		setTimeout(
				function() {
					$
							.ajax({
								type : "POST",
								url : "/DeliveryTarjetas/tercero.do"
										+ "?method=lstTerceros",
								cache : false,
								dataType : "json",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								async : false,
								data : param,
								success : function(rsp) {

									var status = rsp.statustx;
									var message = rsp.messagetx;

									cargarDataTablesColaboradores(rsp.lst);
									closeModalCargando();

									/*if(status == 0){													
										if(rsp.lstcolaboradores!= undefined && rsp.lstcolaboradores.lenght > 0)
											cargarDataTablesColaboradores(rsp.lstcolaboradores);
									}else
										loadModalMensaje("Atención",message,null);*/
								},
								error : function(rsp, xhr, ajaxOptions,
										thrownError) {
									closeModalCargando();
									loadModalMensaje("Error",
											"ERROR BUSCANDO COLABORADORES",
											null);
								}
							});
				}, 1000);
	}

	function cargarDataTablesColaboradores(lstTerceros) {

		$("#table-lst-colaboradores").DataTable(
				{
					"order" : [ [ 0, "asc" ] ],
					"searching" : true,
					"paging" : true,
					"bInfo" : true,
					"bAutoWidth" : false,
					"oLanguage" : {
						"sUrl" : "/DeliveryTarjetas/recursos/idioma/es_ES.txt"
					},
					"data" : lstTerceros,
					"columns" : [
							{
								"data" : "nrodocumento",
								"class" : "text-center"
							},
							{
								"orderable" : false,
								"data" : "nombres",
								"mRender" : function(data, type, full) {
									return full.apepaterno + " "
											+ full.apematerno + ", "
											+ full.nombres;
								}
							}, {
								"orderable" : false,
								"data" : "idcourier",
								"class" : "desktop"
							}, {
								"orderable" : false,
								"sWidth" : "5%",
								"mRender" : function(data, type, full) {
									return linkDetalleColaborador(full);
								}
							} ],
					"fnDrawCallback" : function() {
						mostrarDatatable("#table-lst-colaboradores");
					}
				});
	}

	function linkDetalleColaborador(full) {

		enlace = "<a data-toggle='modal' "
				+ "data-target='#modalColaboradorEdit' "
				+ "onclick='return rowSelected("
				+ JSON.stringify(JSON.stringify(full)) + ");'>"
				+ "<i class='i-cobertura'></i>" + "</a>";

		return enlace;
	}

	function rowSelected(json) {
		json = JSON.parse(json);
		$("#form-colaborador-edit #idtercero").val(json.idtercero);
		$("#form-colaborador-edit #nombres").val(json.nombres);
		$("#form-colaborador-edit #apepaterno").val(json.apepaterno);
		$("#form-colaborador-edit #idptipodocumento")
				.val(json.idptipodocumento);
		$("#form-colaborador-edit #nrodocumento").val(json.nrodocumento);
		$("#form-colaborador-edit #apematerno").val(json.apematerno);
		$("#form-colaborador-edit #idcourier").val(json.idcourier);

	}
</script>