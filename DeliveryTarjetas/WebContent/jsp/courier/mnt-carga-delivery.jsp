<div>
	<h3 class="vineta-carga-entrega-tarjetas">Carga de datos de entregas de tarjeta</h3>
 
   
	<!--  Botones de filtro de la grilla -->
	<form id="form-filter-carga" rol="form">
		<div class="panel panel-default">
			<div class="panel-body">

				<div class="column-formulario-carga">
					<div class="form-group form-group-carga">
						<label for=""
							class="col-md-5 control-label carga-content-maxim">DNI Cliente:</label>
						<div class="col-md-7 carga-content-maxim">
							<input type="text" id="dnicliente" name="dnicliente"
								class="form-control">
							<div class="result"></div>
						</div>
					</div>

					<div class="form-group form-group-carga">
						<label for=""
							class="col-md-5 control-label carga-content-maxim">Nombre Cliente:</label>
						<div class="col-md-7 carga-content-maxim">
							<input type="text" id="nomcliente" name="nomcliente"
								class="form-control">
							<div class="result"></div>
						</div>
					</div>

				</div>

				<div class="column-formulario-carga">
					<div class="form-group form-group-carga">
						<label for=""
							class="col-md-5 control-label carga-content-maxim">DNI Courier:</label>
						<div class="col-md-7 carga-content-maxim">
							<input type="text" id="dnicourier" name="dnicourier"
								class="form-control">
							<div class="result"></div>
						</div>
					</div>
					<div class="form-group form-group-carga">
						<label for=""
							class="col-md-5 control-label carga-content-maxim">Nombre Courier:</label>
						<div class="col-md-7 carga-content-maxim">
							<input type="text" id="courierasoc" name="courierasoc"
								class="form-control">
							<div class="result"></div>
						</div>
					</div>
				</div>

			</div>
		</div>

	</form>

	<!--  Botones buscar y editar -->
	<div class="form-group">
		<div class="col-md-7">
			<h6>Seleccione un registro y editar para modificar los datos</h6>
		</div>

		<div class="col-md-5">
			<div class="form-group">
				<div class="boton-siguiente"> 
					<a data-target="" href="" class="btn btn-primary pull-right"
						data-toggle="modal" onclick="refrescarTable();">Buscar</a>
				</div>
			</div>
		</div>
	</div>


	<%--Grilla de cargas--%>
	<div id="div-cargar-entrega-tarjeta"></div>
	
	<%--Responsive de data table de carga de entrega de tarjetas --%>
	<div class="row visible-xs-block">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="list-group" id="id-list-carga-entrega-tarjeta"></div>
			</div>
		</div>
	</div>
 
 <div id="modalCargaEntregaTarjetaXXX"></div>
 Modal editar datos 
	<div class="modal fade" id="modalCargaEntregaTarjetaEdit" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title">Editar datos de entrega de tarjeta</h3>
				</div>

				<form id="form-cargar-entrega-tarjeta-edit">


					<div class="column-formulario-carga"
						style="width: 100%; margin-top: 10px; margin-bottom: 10px; padding-left: 15px; padding-right: 15px;">
						<h4 style="margin: 0px;">Datos del Producto</h4>
						<hr style="margin: 0px; border-color: #dddddd;">
					</div>

					<div class="column-formulario-carga">

						<div class="form-group form-group-carga">
							<label for="numerotarjeta"
								class="col-md-5 control-label carga-content-maxim">Número
								Tarjeta:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="numerotarjeta" name="numerotarjeta"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-carga">
							<label for="ultimosdigitos"
								class="col-md-5 control-label carga-content-maxim">Últimos
								4 digitos:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="ultimosdigitos" name="ultimosdigitos"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-carga">
							<label for="tipoprod"
								class="col-md-5 control-label carga-content-maxim">Tipo
								de Producto:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="tipoprod" name="tipoprod"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>
					</div>

					<div class="column-formulario-carga">

						<div class="form-group form-group-carga">
							<label for="subtipoprod"
								class="col-md-5 control-label carga-content-maxim">SubTipo
								de Producto:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="subtipoprod" name="subtipoprod"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-carga">
							<label for="courierasoc"
								class="col-md-5 control-label carga-content-maxim">Courier
								Asociado:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="courierasoc" name="courierasoc"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-carga">
							<label for="dnicourier"
								class="col-md-5 control-label carga-content-maxim">DNI
								Courier :</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="dnicourier" name="dnicourier"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>
					</div>

					<div class="column-formulario-carga"
						style="width: 100%; margin-top: 10px; margin-bottom: 10px; padding-left: 15px; padding-right: 15px;">
						<h4 style="margin: 0px;">Datos del Cliente</h4>
						<hr style="margin: 0px; border-color: #dddddd;">
					</div>

					<div class="column-formulario-carga">

						<div class="form-group form-group-persona">
							<label for="dnicliente"
								class="col-md-5 control-label carga-content-maxim">DNI
								del Cliente:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="dnicliente" name="dnicliente"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-persona">
							<label for="nomcliente"
								class="col-md-5 control-label carga-content-maxim">Nombre
								del Cliente</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="nomcliente" name="nomcliente"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

					</div>

					<div class="column-formulario-carga">


						<div class="form-group form-group-carga">
							<label for="distcliente"
								class="col-md-5 control-label carga-content-maxim">Distrito
								Cliente:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="distcliente" name="distcliente"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-carga">
							<label for="distcliente"
								class="col-md-5 control-label carga-content-maxim">Dirección
								del Cliente:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="dircliente" name="distcliente"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

					</div>

					<div class="form-group form-group-carga">
						<label for="loccliente"
							class="col-md-5 control-label carga-content-maxim">Localización
							Cliente:</label>
						<div class="col-md-7 carga-content-maxim">
							<input type="text" id="loccliente" name="loccliente"
								class="form-control">
							<div class="result"></div>
						</div>
					</div>

					<div class="column-formulario-carga"
						style="width: 100%; margin-top: 10px; margin-bottom: 10px; padding-left: 15px; padding-right: 15px;">
						<h4 style="margin: 0px;">Datos de Entrega</h4>
						<hr style="margin: 0px; border-color: #dddddd;">
					</div>
 
					<div class="column-formulario-carga">

						<div class="form-group form-group-persona">
							<label for="fecentregaprog"
								class="col-md-5 control-label carga-content-maxim">Fecha
								de entrega:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="fecentregaprog" name="fecentregaprog"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-persona">
							<label for="horentregaprog"
								class="col-md-5 control-label carga-content-maxim">Hora
								de entrega:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="horentregaprog" name="horentregaprog"
									class="form-control">
								<div class="result"></div>
							</div> 

						</div>
 
						<div class="form-group form-group-persona">
							<label for="ubientrega"
								class="col-md-5 control-label carga-content-maxim">Ubicación
								del Entrega</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="ubientrega" name="ubientrega"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

					</div>

					<div class="column-formulario-carga">
						<div class="form-group form-group-carga">
							<label for="depentrega"
								class="col-md-5 control-label carga-content-maxim">Departamento
								de Entrega:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="depentrega" name="depentrega"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-carga">
							<label for="proventrega"
								class="col-md-5 control-label carga-content-maxim">Provincia
								de Entrega:</label>
							<div class="col-md-7 carga-content-maxim">
								<input type="text" id="proventrega" name="proventrega"
									class="form-control">
								<div class="result"></div>
							</div>
						</div>

						<div class="form-group form-group-persona">
							<label for="distentrega"
								class="col-md-5 control-label carga-content-maxim">Distrito
								de entrega:</label>
							<div class="col-md-7 persona-content-maxim">
								<input type="text" id="distentrega" name="distentrega"
									class="form-control">
								<div class="result"></div>
							</div>
						</div> 

					</div>

					<div class="form-group form-group-persona">
						<label for="direntrega"
							class="col-md-5 control-label carga-content-maxim">Dirección
							de entrega</label>
						<div class="col-md-7 carga-content-maxim">
							<input type="text" id="direntrega" name="direntrega"
								class="form-control">
							<div class="result"></div>
						</div>
					</div>

				</form>

				<div class="row">
					<div class="col-md-12">
						<div class="col-md-12">
							<div id="divbutton">
								<button type="button" class="btn btn-default pull-right"
									data-dismiss="modal">Salir</button>
							</div>
							<div id="divbutton">
								<button type="button" class="btn btn-primary pull-right"
									onclick="guardarDatosEditados();">Guardar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<script>
	 
	var coberturaDomi  		= null;
	var indDivDetalleDomi 	= 0;
	
	/** Se ejecuta apenas termina de renderizar **/
	$().ready(function() {
		$('#div-cargar-entrega-tarjeta tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	    } );
		 
		//getListTable();
	});

	/** Trae la lista del servidor **/
	function getListTable() {

		/** Muestra gif de cargando **/
		loadModalCargando();

		/** Datos de entrada **/
		var param = new Object();
		 
		param.dnicliente = $("#form-filter-carga #dnicliente").val(); 
		param.nomcliente = $("#form-filter-carga #nomcliente").val();
		param.dnicourier = $("#form-filter-carga #dnicourier").val();
		param.courierasoc = $("#form-filter-carga #courierasoc").val();

		/** Request POST **/
		$.ajax({
			type : "POST",
			url : "/DeliveryTarjetas/delivery.do?method=lstDelivery",
			cache : false,
			async : false,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : param,
			success : function(data) {
				var jsonPolizas = eval(data);
				createHtmlTable(jsonPolizas, "cargar-entrega-tarjeta");
				 
				closeModalCargando();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				closeModalCargando();
				loadModalMensaje(
						'Lo Sentimos',
						'<center>Hubo problemas en el procesamiento de datos.</center>',
						'');
			}
		});
	}

	/** Crea html de dataTable y carga los datos **/
	function createHtmlTable(dataList, name) {

		$("#divdestroy *").remove();

		var html = 
			 '<div id="divdestroy" class="row visible-md-block visible-sm-block visible-lg-block">' + 
				 '<div class="table-responsive col-md-12">' + 
					 '<table id="table-' + name + '" class="table table-hover table-bordered table-inter text-center">' +
					 	'<thead>' +
					 		'<tr>' + 
								 '<th class="text-center">N° Tarjeta</th>' +
								 '<th class="text-center">DNI Cliente</th>' +
								 '<th class="text-center">Nombre Cliente</th>' +
								 '<th class="text-center">Monto Crédito</th>' +
								 '<th class="text-center">Fecha Entrega</th>' +  
								 '<th class="text-center">Detalle</th>' +
					 		'</tr>' +
					 	'</thead>' +
					 	'<tbody class="vcenter">' +
					 	'</tbody>' +
					 '</table>' +
				 '</div>' +
			
				 '<div class="row">' +
				 	'<div class="col-md-12"></div>' +
				 '</div>'+
			 '</div>';

		$("#div-cargar-entrega-tarjeta").append(html);

		var idTabla = '#table-' + name;

		$(idTabla).DataTable(
			{
				"data" : dataList,
				"ordering" : false,
				"searching" : false,
				"paging" : true,
				"bInfo" : true,
				"bAutoWidth" : true,
				"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
				"columns" : [ 
						{
							"data" : "ultdigtarjeta",
							"sWidth" : "10%"
						},
						{
							"data" : "nrodocumentocli",
							"sWidth" : "10%"
						},
						{
							"data" : "nombrescli",
							"sWidth" : "20%"
						},
						{
							"data" : "mtoasoctarjeta",
							"render" : function(data, type, full) {
								var a = '<div style="text-align: right;">'
										+ formatNumber(data)
										+ '</div>';
								return a;
							},
							"sWidth" : "15%"
						}, {
							"data" : "fecentrega",
							"sWidth" : "10%"
						}, 
						{ "orderable"		: false,
			                "data"    		: "",
			                "sWidth"		: "5%",
			                "mRender"		: function (data, type, full) {
        						indDivDetalleDomi++;
                    			
        						return linkCargaEntregaTarjetaEditar(data, 
         		 						full, 
         		 						"table-cargar-entrega-tarjeta",
         		 						"coberturaDomi",
         		 						indDivDetalleDomi);}
						}
						
						],
				"fnDrawCallback" : function() {
					var table = $(idTabla).dataTable();
					if (table.fnGetData().length == 0) {
						table.parent().toggle(true);
					}
				}
			});
		
		indDivDetalleDomi = 0;
	}

	
	function linkCargaEntregaTarjetaEditar(data, full, idTabla, nomvar, indDivDetalleDomi){
		
		var enlace = null;
		//setValuesForm(full);
		
		 
			
		enlace = "<a data-toggle='modal' " + 
					"data-target='#modalCargaEntregaTarjetaEdit' "+ indDivDetalleDomi  +
					"onclick='return rowSelected(\""+idTabla+"\",\""+nomvar+"\");'>"+
						"<i class='i-cobertura'></i>" +
				 "</a>";

		return enlace;
	}
	
	function rowSelected(idTabla, nomvar){
	 
		if(!window[nomvar]){
			window[nomvar] = null;
		}
		
		var table = $(idTabla).DataTable(),
			tbody = idTabla+ " tbody";
		
		$(tbody).on( 'click', 'tr', function () {	
			var data = table.column( 0 ).data();
			
			var r = table.row( this ).data();
			var idx = table.row( this ).index();
			window[nomvar] = {fila   : r,
	  			      indice : idx};
			
		});
		
		return false;
	}
	 

	/** Refresca el dataTable **/
	function refrescarTable() {
		getListTable();
	}

 
	
	/** Editar datos del formulario**/
	function guardarDatosEditados(){
		var formValues = $("#form-cargar-entrega-tarjeta-edit").serializeArray();
		inspect(formValues);
	}
	
</script>



































