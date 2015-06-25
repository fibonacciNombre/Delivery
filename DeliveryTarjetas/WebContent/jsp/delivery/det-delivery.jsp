<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div  id="modalDetalleEntrega"
		class="modal fade" 
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">	
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3 class="modal-title">Editar datos de entrega de tarjeta</h3>
			</div>

			<form id="form-cargar-entrega-tarjeta-edit">
				
				<div id="container-data-delivery">
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
				</div>
				
				<div id="container-data-cliente">
					<div class="column-formulario-carga" style="width: 100%; margin-top: 10px; margin-bottom: 10px; padding-left: 15px; padding-right: 15px;">
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
				</div>
				
				<div id="container-data-producto">
					<div class="column-formulario-carga" style="width: 100%; margin-top: 10px; margin-bottom: 10px; padding-left: 15px; padding-right: 15px;">
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