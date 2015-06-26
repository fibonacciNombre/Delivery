<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="consultar-delivery">
	
	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Consultar entregas a realizar</span>
	</h3>
	
	    <form id="form-lstdelivery">
    	<div class="panel panel-default">
	    	<div class="panel-heading">
				Buscar entregas de tarjetas
			</div>
			    	
	    	<div class="panel-body">	    		
	    		<div class="row">	
	    			
	    			<div class="col-md-6">
						<div class="form-group">
							<label for="idcourier" class="col-md-5 control-label">Courier</label>
		                    <div class="col-md-7">
								<select class="form-control" id="idcourier" name="idcourier">                        	
		                   		</select>
		                    </div>
	                	</div>	
						
						<div class="form-group" id="fecentrega-div">
				            <label for="fecentrega" class="col-md-5 control-label">Fecha de entrega </label>
				            <div class="col-md-7">
				                <div id="div-fecentrega" class="input-group">
									<input type="text" readonly id="fecentrega" name="fecentrega" class="form-control calendario"> 
									<span class="input-group-addon">
										<a id="btnfecentrega" href="javascript:void(0)" class="btn-date calendario"> 
											<span class="glyphicon glyphicon-calendar"></span>
										</a>
									</span>
								</div>
				            </div>
		        		</div>																											
					</div>
											
					<div class="col-md-6">						
						<div class="form-group">
							<label for="idptipodocumento" class="col-md-6 control-label">Tipo documento del responsable</label>
							<div class="col-md-6">
								<select class="form-control" id="idptipodocumento" name="idptipodocumento"> 
								 	<option value="{debe ir id de tipo de documento}">DNI</option>                       	
		                   		</select>
							</div>
						</div>	
						<div class="form-group">
							<label for="nrodocumento" class="col-md-6 control-label">Nro. documento del responsable</label>
							<div class="col-md-6">								
								<input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">							
							</div>
						</div>		
					</div>														
				</div>
				<div class="row">
					<div class="col-md-6"></div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="btnBsqDelivery" class="col-md-5 control-label">
							</label>
							<div class="col-md-7">
								<button id="btnBsqDelivery"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		style="float:right;padding-top: 3px; padding-bottom: 3px;"
			                    		onclick="javascript:bsqDelivery();">
			                    			<i style="background-position: -592px -1405px; height: 25px; margin-right: 0px; width: 25px; display: inline-block; vertical-align: top; transform: scale(0.6);"></i>			                    			
			                    			<div style="display: inline-block; margin-top: 3px; vertical-align: top;">Buscar</div>			                    			
			                    </button>
			                </div>							
						</div>	
					</div>
				</div>										
	    	</div>
    	</div>	
	</form>
	
</div>

<script>
	
    $().ready(function(){
    	
    	if(CTE_LOAD_INIT == 0)
    		loadModalCargando();
    	else
    		CTE_LOAD_INIT = 0;
    	
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', ['idcourier','rznsocial'], {form: 'form-lstdelivery'});
    	
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-lstdelivery #idptipodocumento', true);
		
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
		});
		
		closeModalCargando();
    });	
    
</script>