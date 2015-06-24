<div>
	<h3 class="vineta-carga-entrega-tarjetas">Carga de datos de
		entregas de tarjeta</h3>

	<!--  Botones de filtro de la grilla -->
	<form id="form-cargar-entrega-tarjeta" rol="form">
		<div class="panel panel-default">
			<div class="panel-body">

				<div class="form-group form-group-carga">
					<label for=""
						class="col-md-5 control-label carga-content-maxim required">Ubicación
						Archivo</label>
					<div class="col-md-7 carga-content-maxim">
						<input type="text" id="filename" name="filename"
							value="D:/20150615_CargaDiariaEntregas.xlsx" class="form-control">
						<div class="result"></div>
					</div>
				</div>
				<div class="column-formulario-carga">

					<div class="form-group form-group-carga">
						<label for=""
							class="col-md-5 control-label  carga-content-maxim required">Fecha
							de entrega:</label>
						<div class="col-md-7 carga-content-maxim">
							<div id="divfechnacim" class="input-group">
								<input type="text" id="fecnac_persona" name="fecnac_persona"
									class="form-control" readonly="readonly"
									data-rule-required="true"
									data-msg-required="La fecha es obligatoria" required> <span
									class="input-group-addon"> <a id="btnFecnac_persona"
									href="javascript:void(0)" class="btn-date"> <span
										class="glyphicon glyphicon-calendar"></span>
								</a>
								</span>
								<div class="result"></div>
							</div>
						</div>

					</div>

					<div class="form-group form-group-carga">
						<label for="idcourier"
							class="col-md-5 control-label carga-content-maxim required">Courier</label>
						<div class="col-md-7 carga-content-maxim" id="divdpto">
							<select class="form-control" id="idcourier" name="idcourier"
								data-rule-required="true"
								data-msg-required="Seleccione un courier responsable" required></select>
							<div class="result"></div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-12">

							<div id="divbutton">
								<button type="button" class="btn btn-primary pull-right"
									onclick="cargarEntregasTarjeta();">Cargar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>


</div>
<script>
	/** Se ejecuta apenas termina de renderizar **/
	$().ready(function() {
		
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', {form: 'form-cargar-entrega-tarjeta'});
		
		$("#fecnac_persona").datepicker(
				{
					maxDate : 'today',
					beforeShow : function() {
						setTimeout(function() {
							$('.ui-datepicker').css({
								'z-index' : 9999,
								'border' : '1px solid #ccc'
							});
						}, 0);
					},
					onSelect : function(dateText, inst) {
						$(".result", $("#fec_nacim").parent()).html(
								"<i class='success'></i>");
						$("label", $("#divfechnacim").parent())
								.removeClass("error");
						$("label", $("#divfechnacim").parent()).html("");
						$("#fecnac_persona").removeClass("error");
					}
				});
		
		$("#btnFecnac_persona").click(function() {
			$("#fecnac_persona").datepicker("show");
		});
	});

	function cargarCombo(url, method, combo, config, comboPadre) {

		var param = new Object();

		for ( var key in config) {
			param[key] = config[key];
		}

		if (config.argPadre && comboPadre)
			param[config.argPadre] = comboPadre.value;

		combo = config.form ? config.form + ' #' + combo : '#' + combo;

		$.ajax({
			type : "POST",
			url : url + "?method=" + method,
			cache : false,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			async : false,
			data : param,
			success : function(rsp) {
				llenarCombo(combo, rsp.lstcouries, true);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				loadModalMensaje('Lo sentimos',
						'Hubo un error en el procesamiento de datos.',
						function() {
						});
			}
		});
	}
	
	function llenarCombo(idCombo, listaOpciones, emptyElement) {

		var combo = $('#' + idCombo);
		combo.empty();

		if (emptyElement)
			combo.append('<option value="">' + 'Seleccionar' + '</option>');

		for ( var i = 0; i < listaOpciones.length; i++) { 
			var opcion = '<option value="'+listaOpciones[i].idcourier+'" >'
					+ listaOpciones[i].rznsocial + '</option>';
			combo.append(opcion); 
		}

		$('#' + idCombo).change();
	}
	
	/** Cargar excel **/
	function cargarEntregasTarjeta() {
		var fileName_ = $("#form-cargar-entrega-tarjeta #filename").val(),
			idcourier_ = $("#form-cargar-entrega-tarjeta #idcourier").val(),
			fechaentrega_ = $("#form-cargar-entrega-tarjeta #fecnac_persona").val();
		
	 
		loadModalCargando();

		$
				.post(
						'/DeliveryTarjetas/delivery.do?method=cargaExcelDelivery',
						{
							filename : fileName_,
							fecentrega : fechaentrega_,
							idcourier : idcourier_,
							tipoarchivo: 'xls'
						},
						function(data, textStatus, jqXHR) {
							var obj = $.parseJSON(data);
							if (obj.resultado != 0) {
								loadModalMensaje("Mensaje",
										"Hubo un problema en la carga de datos, intentelo de nuevo en unos momentos.");
							}
						})
				.fail(
						function(jqXHR, textStatus, errorThrown) {
							closeModalCargando();
							loadModalMensaje(
									"Estimado",
									'Lo sentimos. Hubo problemas en el procesamiento. Inténtelo más tarde.',
									'');
						});

		closeModalCargando();
		$("#modalCargaEntregaTarjeta").modal('hide');
	}
	
</script>



































