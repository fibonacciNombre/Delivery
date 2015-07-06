<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="consultar-delivery">
	
	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Consultar entregas a realizar</span>
	</h3>
	
    <form id="form-bsqlstdelivery">
    	
    	<input type="hidden" class="form-control" id="idcourier" name="idcourier"/>
    	
    	<div class="panel panel-default">
	    	<div class="panel-heading">
				Buscar entregas de tarjetas
			</div>
			    	
	    	<div class="panel-body">	    		
	    		<div class="row">	
	    			
	    			<div class="col-md-6">
						<div class="form-group" id="cbocourier-div">
							<label for="cbocourier" class="col-md-5 control-label">Courier</label>
		                    <div class="col-md-7">
								<select class="form-control" id="cbocourier" name="cbocourier"></select>
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
		        		
		        		<div class="form-group" id="idpestadodelivery-div">
							<label for="idpestadodelivery" class="col-md-5 control-label">Estado de entrega</label>
							<div class="col-md-7">
								<select class="form-control" id="idpestadodelivery" name="idpestadodelivery">                      	
		                   		</select>
							</div>
						</div>																										
					</div>
											
					<div class="col-md-6">						
						<div class="form-group" id="idptipodocumento-div">
							<label for="idptipodocumento" class="col-md-6 control-label">Tipo documento del responsable</label>
							<div class="col-md-6">
								<select class="form-control" id="idptipodocumento" name="idptipodocumento">                        	
		                   		</select>
							</div>
						</div>
							
						<div class="form-group" id="nrodocumento-div">
							<label for="dnitrabajador" class="col-md-6 control-label">DNI del Colaborador</label>
							<div class="col-md-6">								
								<input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">							
							</div>
						</div>
						
						<div class="form-group" id="btnbsqdelivery-div">
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
	
	<div id="container-lst-detdelivery" style="margin-top:20px; display: none;">	
	
		<div class="row">
			<div class="col-md-12">						
				<div class="form-group">
					<button id="btnBsqDelivery"
				    		type="button" 
				    		class="btn-descargar btn btn-primary " 
				    		onclick="javascript:exportarListaDelivery();">
				    			<i class="icono-excel"></i>			                    			
				    			<div style="display: inline-block; margin-top: 3px; vertical-align: top;">Exportar a Excel</div>			                    			
				    </button>																				
				</div>			
			</div>	
		</div>			
		
		<table id="table-lstdelivery" class="table table-hover table-bordered table-inter text-center">
		 	<thead>
				<tr> 
	 				<th class="text-center">Fecha Entrega</th>  
	 				<th class="text-center">DNI Cliente</th>
				 	<th class="text-center">Nombre cliente</th>
				 	<th class="text-center">Lugar de entrega</th>
				 	<th class="text-center">Observaciones</th>					
					<th class="text-center">Detalle</th>
					<th class="text-center">PDF</th>					
				</tr>
			</thead>
			<tbody class="vcenter">
			</tbody>
		</table>
				
	</div>
	
	<%@include file="/jsp/delivery/det-delivery.jsp" %>
	
</div>

<script>
	
    $().ready(function(){
    	
    	if(CTE_LOAD_INIT == 0)
    		loadModalCargando();
    	else
    		CTE_LOAD_INIT = 0;
    	
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','cbocourier', ['idcourier','rznsocial'], {form: 'form-bsqlstdelivery'});
    	
		callCargaControlParam('DELWEB_TIPODOCUMENTO','form-bsqlstdelivery #idptipodocumento', true);
		
		callCargaControlParam('DELWEB_ESTADODELIVERY','form-bsqlstdelivery #idpestadodelivery', true);
		
		initDatePicker("fecentrega","calendario");
		
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!=""){
    		$("#form-bsqlstdelivery #cbocourier").val($("#form-datos-usuario #idcourier").val());
    		$("#form-bsqlstdelivery #idcourier").val($("#form-datos-usuario #idcourier").val());    		
    		$("#form-bsqlstdelivery #cbocourier").attr("disabled",true);
    	}
		
		$("#bntDetalleDelivery-div").remove();
		
		closeModalCargando();
    });	
    
    function bsqDelivery(){
		
    	$("#container-lst-detdelivery").hide();
		
		cleanDatatable("table-lstdelivery");
		
		loadModalCargando();
		
		$("#form-bsqlstdelivery #cbocourier").attr("disabled",false);
		$("#form-bsqlstdelivery #idcourier").val($("#form-bsqlstdelivery #cbocourier").val());
		
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!="")
			$("#form-bsqlstdelivery #cbocourier").attr("disabled",true);
		
		var param 				= new Object();
		 
		param.idptipodocumento 		= $("#form-bsqlstdelivery #idptipodocumento").val();
		param.nrodocumento 			= $("#form-bsqlstdelivery #nrodocumento").val();
		param.idcourier 			= $("#form-bsqlstdelivery #idcourier").val();
		param.fechaentregaarh 		= $("#form-bsqlstdelivery #fecentrega").val();
		param.idpestadodelivery 	= $("#form-bsqlstdelivery #idpestadodelivery").val();

		var lstDelivery			= bsqDeliveryUtil(param);
		
		if(lstDelivery!= undefined && lstDelivery.length > 0){											
			cargarTablaLstDelivery(lstDelivery);
			$("#container-lst-detdelivery").slideDown(1000);
		}
    }
    
    function exportarListaDelivery() {

		loadModalCargando();

		var param 					= new Object();
		 
		param.idptipodocumento 		= $("#form-bsqlstdelivery #idptipodocumento").val();
		param.nrodocumento 			= $("#form-bsqlstdelivery #nrodocumento").val();
		param.idcourier 			= $("#form-bsqlstdelivery #idcourier").val();
		param.fechaentregaarh 		= $("#form-bsqlstdelivery #fecentrega").val();
		param.idpestadodelivery 	= $("#form-bsqlstdelivery #idpestadodelivery").val();

		var pathFile				= obtArchivoLstEntregas(param);
		
		if(pathFile!=""){
			window.open("../"+pathFile, 'NewWin');
		}
	}
    
    function cargarTablaLstDelivery(dataList) {

		$("#table-lstdelivery").DataTable({ 
			"data" 			: dataList,
			"ordering" 		: true,
			"searching" 	: true,
			"paging" 		: true,
			"bInfo" 		: true,
			"bAutoWidth" 	: true,
			"oLanguage"  	: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
			"columns" 		: [ 
								{ "data" 		: "fecentrega",
									"orderable"	: false,
									"sWidth" 	: "12%" },
								{ "data" 		: "nrodocumentocli",
									"orderable"	: false,
									"sWidth" 	: "12%" },
								{ "data" 		: "nombrescli",
									"orderable"	: false,
									"sWidth" 	: "30%" },																
								{ "data" 		: "lugarentrega",
									"sWidth" 	: "20%",
									"orderable"	: false }, 
								{ "data" 		: "lugarentrega",
									"orderable"	: false,
									"sWidth" 	: "15%" }, 															
                   				{ "data"      	: "",
									"orderable"	: false,
									"sWidth" 	: "10%",
                      				"class"		: "text-center",
                       	 			"mRender"  	: function (data, type, full) {
                        	 								return linkDetalleDelivery(full,false);  }},
								{ "data"      	: "",
									"orderable"	: false,
									"sWidth" 	: "5%",
                      				"class"		: "text-center",
                  	 				"mRender"  	: function (data, type, full) {
                   	 									return linkPDF(full.iddelivery);  }}                        	 								
							],
			"fnDrawCallback" 	: function() {mostrarDatatable("#table-lstdelivery");},
			"fnCreatedRow"		: function (r, data, i) {
									    if(data.idpestadocarga == 2)  
											$(r).addClass("row-alerta");
								   }
		});		
	}
</script>
