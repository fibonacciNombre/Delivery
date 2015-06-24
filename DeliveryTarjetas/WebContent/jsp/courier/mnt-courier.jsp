<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-courier">

	<h3 class="container-title">
		<span class="vineta-title"></span> <span>Edición de courier</span>
	</h3>

	<div id="modalEditarCourier">
		<div class="modal fade" id="modalCourierEdit" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						 
						<h3 class="modal-title">Editar datos de Courier</h3>
					</div>
					 

					<form id="form-courier-edit">
					
					<input type="hidden" 	id="idcourier" 			name="idcourier"			value=""/>
					
						<div class="column-formulario-carga">

							<div class="form-group form-group-carga">
								<label for="codbbva"
									class="col-md-5 control-label carga-content-maxim">Código
									BBVA</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="codbbva" name="codbbva"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>

							<div class="form-group form-group-carga">
								<label for="rznsocial"
									class="col-md-5 control-label carga-content-maxim">Razón
									Social</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="rznsocial" name="rznsocial"
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
								<label for="nrodocumentocou"
									class="col-md-5 control-label carga-content-maxim">Nro
									Documento</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nrodocumentocou" name="nrodocumentocou"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>


						</div>

						<div class="column-formulario-carga">

							<div class="form-group form-group-carga">
								<label for="telffijo"
									class="col-md-5 control-label carga-content-maxim">Teléfono
									Fijo</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="telffijo" name="telffijo"
										class="form-control">
									<div class="result"></div>
								</div>
							</div> 
					
							<div class="form-group form-group-carga">
								<label for="telfmovil"
									class="col-md-5 control-label carga-content-maxim">Teléfono
									Móvil</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="telfmovil" name="telfmovil"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>


						</div>
						
							<div class="column-formulario-carga">
							
							<div class="form-group form-group-carga">
								<label for="correo"
									class="col-md-5 control-label carga-content-maxim">Correo
									</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="correo" name="correo"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							
							
						<div class="form-group form-group-carga">
							<label for="direccion"
								class="col-md-5 control-label carga-content-maxim">Dirección</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="direccion" name="direccion"
									class="form-control">
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
										onclick="javascript:salir();">
										Salir</button>

									<button id="btnRegistrar" type="button" class="btn btn-primary"
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

	<div id="container-lst-courier" style="margin-top: 20px;">
		<table class="table table-hover table-bordered"
			id="table-lst-couriers">
			<thead>
				<tr>
					<th class="text-center">Cod. Bbva</th>
					<th class="text-center">Razón social</th>
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
</div>

<script
	src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>
	$().ready(function() {

		callCargaControlParam('DELWEB_TIPODOCUMENTO', 'form-courier-edit #idptipodocumento');

		jQuery.validator.addMethod("alphanumeric", function(value, element) {
			return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		 
	});

	function bsqCourier() {

		var param = new Object();
		param = $("#form-courier-edit").serializeArray();

		loadModalCargando();

		setTimeout(
				function() {
					$
							.ajax({
								type : "POST",
								url : "/DeliveryTarjetas/courier.do"
										+ "?method=lstCourier",
								cache : false,
								dataType : "json",
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								async : false,
								data : param,
								success : function(rsp) {

									cargarDataTablesCouries(rsp.lstcouries);
									closeModalCargando();
									/*var status 	= rsp.statustx;
									var message = rsp.messagetx;

									closeModalCargando();
									
									if(status == 0){													
										if(rsp.lstcouries!= undefined && rsp.lstcouries.lenght > 0)
											cargarDataTablesCouries(rsp.lstcouries);
									}else
										loadModalMensaje("Atención",message,null);*/
								},
								error : function(rsp, xhr, ajaxOptions,
										thrownError) {
									closeModalCargando();
									loadModalMensaje("Error",
											"ERROR BUSCANDO COURIER", null);
								}
							});
				}, 1000);
	}

	function cargarDataTablesCouries(lstcouries) {

		$("#table-lst-couriers").DataTable({
			"order" : [ [ 0, "asc" ] ],
			"searching" : true,
			"paging" : true,
			"bInfo" : true,
			"bAutoWidth" : false,
			"oLanguage" : {
				"sUrl" : "/DeliveryTarjetas/recursos/idioma/es_ES.txt"
			},
			"data" : lstcouries,
			"columns" : [ {
				"data" : "codbbva",
				"class" : "text-center"
			}, {
				"orderable" : false,
				"data" : "rznsocial"
			}, {
				"orderable" : false,
				"data" : "telfmovil",
				"class" : "desktop"
			}, {
				"orderable" : false,
				"data" : "correo",
				"class" : "desktop"
			}, {
				"orderable" : false,
				"data" : "correo",
				"class" : "text-center"
			}, {
				"orderable" : false,
				"sWidth" : "5%",
				"mRender" : function(data, type, full) {
					return linkDetalleCourier(full);
				}
			} ],
			"fnDrawCallback" : function() {
				mostrarDatatable("#table-lst-couriers");
			}
		});
	}

	function rowSelected(json) {
		json = JSON.parse(json);
		$("#form-courier-edit #idcourier").val(json.idcourier); 
		$("#form-courier-edit #codbbva").val(json.codbbva);
		$("#form-courier-edit #rznsocial").val(json.rznsocial);
		$("#form-courier-edit #idptipodocumento").val(json.idptipodocumento);
		$("#form-courier-edit #nrodocumentocou").val(json.nrodocumentocou);
		$("#form-courier-edit #telffijo").val(json.telffijo);
		$("#form-courier-edit #telfmovil").val(json.telfmovil); 
		$("#form-courier-edit #direccion").val(json.direccion); 
		$("#form-courier-edit #correo").val(json.correo); 
		
	}

	function linkDetalleCourier(full) {
		
		enlace = "<a data-toggle='modal' " + "data-target='#modalCourierEdit' "
				+ "onclick='return rowSelected("+JSON.stringify(JSON.stringify(full))+");'>" 
				+ "<i class='i-cobertura'></i>" + "</a>";

		return enlace; 
	}
	
	
	function actualizarCourier() { 
		if ($("#form-courier-edit").valid()){
		 
        		loadModalCargando();
        		 
        		$.post('/DeliveryTarjetas/courier.do?method=mntCourier', $("#form-courier-edit").serializeArray(),
       				function(data, textStatus, jqXHR){
   						var obj = $.parseJSON(data); 
   						
						 
							}).fail(
							function(jqXHR, textStatus, errorThrown) {
								closeModalCargando();
								loadModalMensaje(
										"Estimado",
										'Lo sentimos. Hubo problemas en el procesamiento. Inténtelo más tarde.',
										'');
							});

			closeModalCargando();
			 
		} else {
		/** Pone las equisy checks segun corresponda **/
		 	$.each($('input[type=text], select ,textarea', '#form-courier-edit'),function(k){
			   validateItems("form-courier-edit", this);
			});
		} 
	}
</script>
