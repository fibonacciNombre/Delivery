<%-->
<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div>
	
	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Carga de datos de entrega</span>
	</h3>

	<!--  Botones de filtro de la grilla -->
	<form id="form-cargar-entrega-tarjeta" rol="form">
	
		<div class="row">		
			<div class="col-md-12">
		        <div class="form-group" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Ubicación archivo </label>
		            <div class="col-md-12">
		                <input type="text" readonly class="col-md-6 form-control" id=filename name="filename" value="D:/20150615_CargaDiariaEntregas.xlsx" >
		                <div class="result"></div>
		            </div>
		        </div>
			</div>		
		</div>
		
		<div class="row">		
			<div class="col-md-6">
		        <div class="form-group" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Fecha de entrega </label>
		            <div class="col-md-12">
		            	<div id="div-fecentrega" class="input-group">
							<input type="text" readonly id="fecentrega" name="fecentrega" class="form-control calendario"> 
							<span class="input-group-addon">
								<a id="btnfecentrega" href="javascript:void(0)" class="btn-date calendario"> 
									<span class="glyphicon glyphicon-calendar"></span>
								</a>
							</span>
							<div class="result"></div>
						</div>		          
		            </div>
		        </div>
			</div>
			<div class="col-md-6">
		        <div class="form-group" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Courier </label>
		            <div class="col-md-12">
		                <select id="idcourier" 
		                		name="idcourier"
		                		class="form-control"
								data-rule-required="true"
								data-msg-required="Seleccione un courier responsable" required>
						</select>
		                <div class="result"></div>
		            </div>
		        </div>
			</div>		
		</div>
		
		<div class="row">
			<div class="col-md-12">
				 <div class="col-md-12">
	                <div class="form-group">
	                	<label class="col-md-6 control-label" style="padding: 0px;">
	                	</label>
	                	<div class="col-sm-6 pull-right" style="text-align: right; padding: 0px;">
		                    <button type="button" 
									class="btn btn-primary pull-right"
									onclick="cargarEntregasTarjeta();">
								Cargar
							</button>
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
		
		loadModalCargando();
		
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', {form: 'form-cargar-entrega-tarjeta'});
		
		$("#fecentrega").datepicker({ 
									beforeShow 	: function() {
														setTimeout(function() {
															$('.ui-datepicker').css({
																'z-index' : 9999,
																'border' : '1px solid #ccc'
															});
														}, 0);
									},
									onSelect 		: function(dateText, inst) {
														$("#fecentrega").removeClass("error");
									}
						});
		
		$(".calendario").click(function() {
									$("#fecentrega").datepicker("show");
		})
		
		$("#form-cargar-entrega-tarjeta").validate({
			rules : {

				idcourier 			: {				required 	: true },
				
				fecentrega 			: {				required 	: true },
				
				filename 			: {				required 	: true }
				
			},
			messages : {
				idcourier 			: {				required 	: "Debes seleccionar un Courier" },
			
				fecentrega 			: {				required 	: "Debes ingresar la fecha de entrega" },
				
				filename 			: {				required 	: "Debes seleccionar el archivo a cargar" }					
			}
		});	
		
		closeModalCargando(); 
	});
	
	/** Cargar excel **/
	function cargarEntregasTarjeta() {
		
		if($("#form-cargar-entrega-tarjeta").valid()){
			
			var fileName_ 	= $("#form-cargar-entrega-tarjeta #filename").val(),
			idcourier_ 		= $("#form-cargar-entrega-tarjeta #idcourier").val(),
			fechaentrega_ 	= $("#form-cargar-entrega-tarjeta #fecnac_persona").val();
		
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
		}
				
		//$("#modalCargaEntregaTarjeta").modal('hide');
	}
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>

<script src="<%=request.getContextPath()%>/js/default/jquery.form.js"></script>

<div>
	
	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Carga de datos de entrega</span>
	</h3>

	<!--  Botones de filtro de la grilla -->
	<form id="subirAfiche" method="post" enctype="multipart/form-data">
	
		<div class="row">		
			<div class="col-md-12">
		        <div class="form-group" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Ubicación archivo </label>
		            <div class="col-md-12">
		                <input type="file" class="col-md-6 form-control" id="filename" name="filename" >
		                <div class="result"></div>
		            </div>
		        </div>
			</div>		
		</div>
		
		<div class="row">		
			<div class="col-md-6">
		        <div class="form-group" id="filename-div">
		            <label for="fecnac_persona" class="col-md-6 control-label required">Fecha de entrega </label>
		            <div class="col-md-12">
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
			</div>
			<div class="col-md-6">
		        <div class="idcourier" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Courier </label>
		            <div class="col-md-12">
		                <select id="idcourier" 
		                		name="idcourier"
		                		class="form-control"
								data-rule-required="true"
								data-msg-required="Seleccione un courier responsable" required>
						</select>
		                <div class="result"></div>
		            </div>
		        </div>
			</div>		
		</div>
		
		<div class="row">
			<div class="col-md-12">
				 <div class="col-md-12">
	                <div class="form-group">
	                	<label class="col-md-6 control-label" style="padding: 0px;">
	                	</label>
	                	<div class="col-sm-6 pull-right " style="text-align: right; padding: 0px;">
		                    <button type="submit" id="cargar"
									class="btn btn-primary pull-right start">
								Cargar
							</button>
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
		
		$('#cargar').click(function(){
			
			var idcourier_ = $("#subirAfiche #idcourier").val(),
			fechaentrega_ = $("#subirAfiche #fecnac_persona").val();
			$('#subirAfiche').ajaxForm({
			 
				url:"../delivery.do?method=uploadFile&idcourier="+ idcourier_ + "&fecentrega="+ fechaentrega_ +"&tipoarchivo=xlsx",
				type:"post", 
				success:function(data){
				
				//alert("Debe seleccionar la opción \"Mostrar Miniatura\"");
				//$("#errorMin").attr("style","display:block");
			
			}
	
	});
			
			
		});

		
		
		
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', ['idcourier', 'rznsocial'], {form: 'subirAfiche'});
		
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
	
	function cargadita(){
		$('#subirAfiche').ajaxForm({url:"../delivery.do?method=uploadFile",type:"post", success:function(data){
			
					alert("Debe seleccionar la opción \"Mostrar Miniatura\"");
					//$("#errorMin").attr("style","display:block");
				
			}
		
		});
	}

	 
	
	/** Cargar excel **/
	function cargarEntregasTarjeta() {
		var fileName_ = $("#subirAfiche").val(),
			idcourier_ = $("#fsubirAfiche").val(),
			fechaentrega_ = $("#subirAfiche #fecnac_persona").val();
		
	 
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