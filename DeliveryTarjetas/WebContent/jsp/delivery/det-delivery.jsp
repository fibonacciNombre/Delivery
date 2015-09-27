<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalDetalleEntrega" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3 class="modal-title">Datos de entrega de tarjeta</h3>
			</div>
			
			<form id="form-detdelivery">
				
				<input type="hidden" id="iddelivery" name="iddelivery"/>			
				
				<div id="tabs-detalle-delivery" style="border: 0px solid;">
					<ul style="background: white; border:0px;">
						<li id="t-entrega" class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-entrega">Entrega</a></li>
						<li id="t-cliente" class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-cliente">Cliente</a></li>							
						<li id="t-tarjeta" class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-tarjeta">Tarjeta / Remito</a></li>						
						<li id="t-responsable" class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-responsable">Mensajero</a></li>
						<li id="t-responsablemnt" class="tabs" style="display: inline-block; width: 24.5%; float: left;"><a href="#tabs-responsablemnt">Mensajero</a></li>
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
									class="col-md-5 control-label carga-content-maxim">Número de documento</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nrodocumentocli" name="nrodocumentocli"
										class="form-control" maxlength="12">
									<div class="result"></div>
								</div>
							</div>
							<div class="form-group form-group-carga">
								<label for="telmovilcli"
									class="col-md-5 control-label carga-content-maxim">Teléfono</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="telmovilcli" name="telmovilcli"
										class="form-control" maxlength="40">
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
										class="form-control" maxlength="120">
									<div class="result"></div>
								</div>
							</div>
							<div class="form-group form-group-carga">
								<label for="correocli"
									class="col-md-5 control-label carga-content-maxim">Correo cliente</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="correocli" name="correocli"
										class="form-control" maxlength="80">
									<div class="result"></div>
								</div>
							</div>
						</div>
					</div>
					
					<div id="tabs-entrega">							
						<div class="column-formulario-carga">
							<div class="form-group form-group-carga">
								<label for="ordenentrega"
									class="col-md-5 control-label carga-content-maxim">Código de entrega</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="ordenentrega" name="ordenentrega"
										class="form-control" disabled="disabled">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-persona">
								<label for="fecentrega"
									class="col-md-5 control-label carga-content-maxim">Fecha
									de entrega (dd/mm/yyyy):</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="fecentrega" name="fecentrega" class="form-control" maxlength="10">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="horaentrega"
									class="col-md-5 control-label carga-content-maxim">Rango de horas de entrega (de: 00:00 am hasta: 00:00 pm):</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="horaentrega" name="horaentrega"
										class="form-control" maxlength="20">
									<div class="result"></div>
								</div> 
							
							</div>
							
							<div class="form-group form-group-carga">
								<label for="distritocli"
									class="col-md-5 control-label carga-content-maxim">Distrito</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="distritocli" name="distritocli"
										class="form-control" maxlength="80">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="direccioncli"
									class="col-md-5 control-label carga-content-maxim">Dirección del cliente</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="direccioncli" name="direccioncli"
										class="form-control" maxlength="200">
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
									class="col-md-5 control-label carga-content-maxim">Lugar de entrega</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="lugarentrega" name="lugarentrega"
										class="form-control">
									<div class="result"></div>
								</div>
							</div>	
							
							<div class="form-group form-group-carga">
								<label for="referencia"
									class="col-md-5 control-label carga-content-maxim">Referencia</label>
								<div class="col-md-7 carga-content-maxim">
									<textarea rows="4" id="referencia" name="referencia"
										class="form-control"></textarea>									
									<div class="result"></div>
								</div>
							</div>	
							
							<div class="form-group form-group-carga">
								<label for="latitudofi"
									class="col-md-5 control-label carga-content-maxim">Latitud</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="latitudofi" name="latitudofi"
										class="form-control" disabled="disabled">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="longitudofi"
									class="col-md-5 control-label carga-content-maxim">Longitud</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="longitudofi" name="longitudofi"
										class="form-control" disabled="disabled">
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga">
								<label for="indverificacion" class="col-md-12 control-label">Necesita verificación
								</label>
								<div class="col-md-12">
									<select class="form-control" id="indverificacion" name="indverificacion">
									</select>
									<div class="result"></div>
								</div>
							</div>							
							 <%--
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
							--%>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group form-group-carga">
									<label for="oficina" class="col-md-4 control-label">Entrega derivada a oficina</label>
									<div class="col-md-8 carga-content-maxim">
										<textarea rows="4" id="oficina" name="oficina"
										class="form-control" disabled="disabled"></textarea>
										<div class="result"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div id="tabs-tarjeta">
						<div class="column-formulario-carga">		
							<div class="form-group form-group-persona">
								<label for="nrocontrato"
									class="col-md-5 control-label carga-content-maxim">Nro Contrato</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="nrocontrato" name="nrocontrato"
										class="form-control" maxlength="40">
									<div class="result"></div>
								</div>
							</div>								
							<div class="form-group form-group-persona">
								<label for="tipotarjeta"
									class="col-md-5 control-label carga-content-maxim">Tipo de tarjeta</label>
								<div class="col-md-7 persona-content-maxim">
									<input type="text" id="tipotarjeta" name="tipotarjeta"
										class="form-control" maxlength="100">
									<div class="result"></div>
								</div>
							</div> 									
							<div class="form-group form-group-carga">
								<label for="remito"
									class="col-md-5 control-label carga-content-maxim">Remito</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="remito" name="remito"
										class="form-control" maxlength="50">
									<div class="result"></div>
								</div>
							</div>
						</div>		
							
						<div class="column-formulario-carga">
							<div class="form-group form-group-persona">
								<label for="pridigtarjeta"
									class="col-md-5 control-label carga-content-maxim">Primeros digitos</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="number" id="pridigtarjeta" name="pridigtarjeta"
										class="form-control" maxlength="4">
									<div class="result"></div>
								</div>
							</div>								
							<div class="form-group form-group-carga">
								<label for="ultdigtarjeta"
									class="col-md-5 control-label carga-content-maxim">Ultimos digitos</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="number" id="ultdigtarjeta" name="ultdigtarjeta"
										class="form-control" maxlength="4">
									<div class="result"></div>
								</div>
							</div>
							<div class="form-group form-group-carga">
								<label for="ordenservicio"
									class="col-md-5 control-label carga-content-maxim">Orden de servicio</label>
								<div class="col-md-7 carga-content-maxim">
									<input type="text" id="ordenservicio" name="ordenservicio"
										class="form-control" maxlength="20">
									<div class="result"></div>
								</div>
							</div>
						</div>	
					</div>
					
					<div id="tabs-responsablemnt">
						
						<div class="row">
							<div class="col-md-12" >
								<label for="idcourier" class="col-md-12 control-label">Courier
								</label>
								<div class="col-md-12">
									<select class="form-control" id="cbocourier" name="cbocourier">
									</select>
									<input type="hidden" id="idcourier" name="idcourier"/>
									<div class="result"></div>
								</div>
							</div>
							<div class="col-md-12" >
								<label for="idcourier" class="col-md-12 control-label">Responsable de entrega
								</label>
								<div class="col-md-12">
									<select class="form-control" id="idtercero" name="idtercero">
									</select>
									<div class="result"></div>
								</div>
							</div>
						</div>
					</div>
					
					<div id="tabs-responsable">
						
						<div class="column-formulario-carga">
							<div class="form-group form-group-carga" >
								<label for="idcourier" class="col-md-12 control-label">Courier
								</label>
								<div class="col-md-12">
									<select class="form-control" id="cbocourier" name="cbocourier">
									</select>
									<input type="hidden" id="idcourier" name="idcourier"/>
									<div class="result"></div>
								</div>
							</div>
							
							<div class="form-group form-group-carga" >
							    <label for="nombres" class="col-md-12 control-label required">Nombres </label>
							    <div class="col-md-12">
							        <input type="text" class="form-control" id="nombres" name="nombres" maxlength="100">
							        <div class="result"></div>
							    </div>
							</div>
							
							<div class="form-group form-group-carga" >
							    <label for="apepaterno" class="col-md-12 control-label required">Apellido paterno </label>
							    <div class="col-md-12">
							        <input type="text" class="form-control" id="apepaterno" name="apepaterno" maxlength="100">
							        <div class="result"></div>
							    </div>
							</div>	
					                
							<div class="form-group form-group-carga" >
							    <label for="apematerno" class="col-md-12 control-label required">Apellido materno </label>
							    <div class="col-md-12">
							        <input type="text" class="form-control" id="apematerno" name="apematerno" maxlength="100">
							        <div class="result"></div>
							    </div>
							</div>		
						</div>
						
						<div class="column-formulario-carga">
							<div class="form-group form-group-carga" >
							    <label for="idptipodocumento" class="col-md-12 control-label required">Tipo de documento </label>
							    <div class="col-md-12">
							        <select class="form-control" id="idptipodocumento" name="idptipodocumento">                      	
							   		</select>
							        <div class="result"></div>
							    </div>
							</div>
					            
							<div class="form-group form-group-carga" >
							    <label for="nrodocumento" class="col-md-12 control-label required">Nro. documento </label>
							    <div class="col-md-12">
							        <input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="8">
							        <div class="result"></div>
							    </div>
							</div>
							
							<div class="form-group form-group-carga" >
							    <label for="telfmovil" class="col-md-12 control-label required">Teléfono móvil </label>
							    <div class="col-md-12">
							        <input type="text" class="form-control" id="telfmovil" name="telfmovil" maxlength="9">
							        <div class="result"></div>
							    </div>
							</div>
							<%--
							<div class="form-group form-group-carga" >
							    <label for="correo" class="col-md-12 control-label required">Correo electrónico </label>
							    <div class="col-md-12">
							        <input type="text" class="form-control" id="correo"  name="correo" maxlength="200">
							        <div class="result"></div>
							    </div>
							</div>
							--%>
		        		</div>
						
					</div>
					
					<div class="row" style="margin-top:10px;" id="bntDetalleDelivery-div">
						<div class="col-md-12">
							<div id="divbutton">
								<button id="bntDetalleDelivery" 
										type="button" 
										class="btn btn-primary pull-right"
										onclick="guardarDatosEditados();">
									Actualizar
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
