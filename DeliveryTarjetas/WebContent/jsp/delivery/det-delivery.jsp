<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalDetalleEntrega" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3 class="modal-title">Editar datos de entrega de tarjeta</h3>
			</div>
			
			<form id="form-detdelivery">
				
				<input type="hidden" id="iddelivery" name="iddelivery"/>			
				
				<div id="tabs-detalle-delivery" style="border: 0px solid;">
					<ul style="background: white; border:0px;">
						<li class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-entrega">Entrega</a></li>
						<li class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-cliente">Cliente</a></li>							
						<li class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-tarjeta">Tarjeta</a></li>
						<li class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-courier">Courier</a></li>
					</ul>
					
					<div id="tabs-cliente">
						<div class="column-formulario-carga">
							<div class="form-group form-group-persona">
								<label for="tipodocumento"
									class="col-md-5 control-label carga-content-maxim">Tipo de documento</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="tipodocumento" name="tipodocumento"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>								
							<div class="form-group form-group-carga">
								<label for="nrodocumentocli"
									class="col-md-5 control-label carga-content-maxim">Número de Documento</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nrodocumentocli" name="nrodocumentocli"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
						</div>
						
						<div class="column-formulario-carga">
							<div class="form-group form-group-carga">
								<label for="nombrescli"
									class="col-md-5 control-label carga-content-maxim">Nombres</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nombrescli" name="nombrescli"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							<div class="form-group form-group-carga">
								<label for="correocli"
									class="col-md-5 control-label carga-content-maxim">Correo Cliente</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="correocli" name="correocli"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
						</div>
					</div>
					
					<div id="tabs-entrega">							
						<div class="column-formulario-carga">
							<div class="form-group form-group-carga">
								<label for="telmovilcli"
									class="col-md-5 control-label carga-content-maxim">Orden de Entrega</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="ordenentrega" name="ordenentrega"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-persona">
								<label for="fecentrega"
									class="col-md-5 control-label carga-content-maxim">Fecha
									de entrega:</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="fecentrega" name="fecentrega"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="horaentrega"
									class="col-md-5 control-label carga-content-maxim">Hora
									de entrega:</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="horaentrega" name="horaentrega"
										class="form-control">
									<div class="result"></div>
								</div> 
							
							</div>
							
							<div class="form-group form-group-carga">
								<label for="distritocli"
									class="col-md-5 control-label carga-content-maxim">Distrito</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="distritocli" name="distritocli"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="direccioncli"
									class="col-md-5 control-label carga-content-maxim">Dirección del cliente</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="direccioncli" name="direccioncli"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							
							
							<div class="form-group form-group-carga">
								<label for="idpestadodelivery" class="col-md-12 control-label required">Estado de la entrega
								</label>
								<div class="col-md-12">
									<select class="form-control" id="idpestadodelivery" name="idpestadodelivery">
									</select>
									<div class="result"></div>
								</div>
							</div> 
								
						</div>
						<div class="column-formulario-carga">	
							<div class="form-group form-group-carga">
								<label for="lugarentrega"
									class="col-md-5 control-label carga-content-maxim">Lugar de Entrega</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="lugarentrega" name="lugarentrega"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>	
							
							<div class="form-group form-group-carga">
								<label for="latitudofi"
									class="col-md-5 control-label carga-content-maxim">Latitud</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="latitudofi" name="latitudofi"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="longitudofi"
									class="col-md-5 control-label carga-content-maxim">Longitud</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="longitudofi" name="longitudofi"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="indverificacion" class="col-md-12 control-label">Necesita Verificación
								</label>
								<div class="col-md-12">
									<select class="form-control" id="indverificacion" name="indverificacion">
									</select>
									<div class="result"></div>
								</div>
							</div>
							 
							<div class="form-group form-group-carga">
								<label for="idpestado" class="col-md-12 control-label">Estado del registro
								</label>
								<div class="col-md-12">
									<select class="form-control" id="idpestado" name="idpestado">
									</select>
									<div class="result"></div>
								</div>
							</div>
							
							
							<div class="form-group form-group-carga">
								<label for="idpestadocarga" class="col-md-12 control-label required">Estado de la carga
								</label>
								<div class="col-md-12">
									<select class="form-control" id="idpestadocarga" name="idpestadocarga">
									</select>
									<div class="result"></div>
								</div>
							</div> 
						</div>
					</div>
					
					<div id="tabs-tarjeta">
						<div class="column-formulario-carga">		
							<div class="form-group form-group-persona">
								<label for="nrocontrato,"
									class="col-md-5 control-label carga-content-maxim">Nro Contrato</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nrocontrato," name="nrocontrato,"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>								
							<div class="form-group form-group-persona">
								<label for="tipotarjeta"
									class="col-md-5 control-label carga-content-maxim">Tipo de Tarjeta</label>
								<div class="col-md-7 persona-content-maxim">
									<input type="text" id="tipotarjeta" name="tipotarjeta"
										class="form-control">
									<div class="result"></div>
								</div>
							</div> 									
							<div class="form-group form-group-carga">
								<label for="mtoasoctarjeta"
									class="col-md-5 control-label carga-content-maxim">Monto:</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="mtoasoctarjeta" name="mtoasoctarjeta"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
						</div>		
							
						<div class="column-formulario-carga">
							<div class="form-group form-group-persona">
								<label for="pridigtarjeta"
									class="col-md-5 control-label carga-content-maxim">Primeros Digitos
									de entrega</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="pridigtarjeta" name="pridigtarjeta"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>								
							<div class="form-group form-group-carga">
								<label for="ultdigtarjeta"
									class="col-md-5 control-label carga-content-maxim">Ultimos Digitos</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="ultdigtarjeta" name="ultdigtarjeta"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>
						</div>	
					</div>
					
					<div id="tabs-courier">
						
						<div class="column-formulario-carga">
							<div class="form-group form-group-carga" >
								<label for="idcourier" class="col-md-12 control-label">Courier
								</label>
								<div class="col-md-12">
									<select class="form-control" id="idcourier" name="idcourier">
									</select>
									<div class="result"></div>
								</div>
							</div>
						</div>
						
						<div class="column-formulario-carga">
							<div class="form-group form-group-carga">
					            <label for="fecentregaarr" class="col-md-5 control-label carga-content-maxim">Fecha de entrega </label>
					            <div class="col-md-7  carga-content-maxim">
					                <div id="div-fecentregaarch" class="input-group">
										<input type="text" readonly id="fecentregaarch" name="fecentregaarch" class="form-control calendario2"> 
										<span class="input-group-addon">
											<a id="btnfecentregaarch" href="javascript:void(0)" class="btn-date calendario2"> 
												<span class="glyphicon glyphicon-calendar"></span>
											</a>
										</span>
									</div>
					            </div>
			        		</div>
		        		</div>
						
					</div>
					
					<div class="row" style="margin-top:10px;" id="bntDetalleDelivery-div">
						<div class="col-md-12">
							<div id="divbutton">
								<button id="bntDetalleDelivery" 
										type="button" 
										class="btn btn-primary pull-right"
										onclick="guardarDatosEditados();">
									Guardar
								</button>
							</div>							
						</div>
					</div>
					
					
				</div>
			</form>
			
		</div>
	</div>
</div>

<script>
	function guardarDatosEditados() { 
		if ($("#form-detdelivery").valid()){
		 
	 		loadModalCargando();
	 		
	 		$("#form-detdelivery *").attr("disabled",false);
	 		
	 		$.ajax({
				type 		: "POST",
				url 		: "/DeliveryTarjetas/delivery.do?method=mntDelivery",
				cache 		: false,
				dataType 	: "json",
				contentType	: "application/x-www-form-urlencoded; charset=UTF-8",
				async 		: false,
				data 		: $("#form-detdelivery").serializeArray(),
				success 	: function(rsp) {
					
									var status 	= rsp.tx.statustx;
									var message = rsp.tx.messagetx;
	
									closeModalCargando();
									
									if(status == 0)
										loadModalMensaje("Enhorabuena",
															message,
															function(){
																$("#modalDetalleEntrega").modal('hide');
																bsqDelivery();
															});										
	
									else
										loadModalMensaje("Atención",message,null);
				},
				error 		: function(rsp, xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje("Lo sentimos","Se presentaron problemas al registrar sus cambios. <br> Por favor intentelo en unos minutos.", null);
				}
			});
	 						 
		} 
	}
</script>
