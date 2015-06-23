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

	});

	/** Cargar excel **/
	function cargarEntregasTarjeta() {
		var fileName_ = $("#form-cargar-entrega-tarjeta #filename").val();
		alert(fileName_);
		loadModalCargando();

		$
				.post(
						'/DeliveryTarjetas/delivery.do?method=cargaExcelDelivery',
						{
							fileName : fileName_
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



































