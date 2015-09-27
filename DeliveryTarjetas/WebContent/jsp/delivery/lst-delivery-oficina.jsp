<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="consultar-delivery">
	
	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Consulta de entregas</span>
	</h3>
	
    <form id="form-bsqlstdelivery">
    	
    	<input type="hidden" class="form-control" id="idoficina" name="idoficina"/>
    	
    	<div class="panel panel-default">
	    	<div class="panel-heading">
				Buscar entregas de tarjetas
			</div>
			    	
	    	<div class="panel-body">	    		
	    		<div class="row">	
	    			
	    			<div class="col-md-6">
						<div class="form-group" id="cbocourier-div">
							<label for="oficina" class="col-md-5 control-label">Oficina</label>
		                    <div class="col-md-7">
								<select class="form-control" id="oficina" name="oficina"></select>
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
						<div class="form-group" id="nrodocumento-div">
							<label for="nrodocumento" class="col-md-6 control-label">DNI del Cliente</label>
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
		<table id="table-lstdelivery" class="table table-hover table-bordered table-inter text-center">
		 	<thead>
				<tr> 
					<th class="text-center">Código entrega</th>
	 				<th class="text-center">Fecha entrega</th>  
	 				<th class="text-center">DNI cliente</th>
				 	<th class="text-center">Nombre cliente</th>
				 	<th class="text-center">Lugar de entrega</th>
				 	<th class="text-center">Estado</th>					
					<th class="text-center">Detalle</th>									
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
		
		callCargaControlParam('DELWEB_ESTADODELIVERY','form-bsqlstdelivery #idpestadodelivery', true);
		
		var combo = $('#oficina');
		combo.empty();
		
		combo.append('<option value="0">' + 'Todas las oficinas' + '</option>');
		combo.append('<option value="' + CTE_IDOFICINA + '">' + CTE_DETALLE_OFICINA + '</option>');
		
		$("#t-responsablemnt").remove();
		
		$("#tabs-responsablemnt").remove();
		
		$("#bntDetalleDelivery-div").remove();
		
		closeModalCargando();
    });	
    
    function bsqDelivery(){
		
    	$("#container-lst-detdelivery").hide();
		
		cleanDatatable("table-lstdelivery");
		
		loadModalCargando();
				
		var param 				= new Object();
		 
		param.idoficina 			= $("#form-bsqlstdelivery #oficina").val();
		param.nrodocumentocli 			= $("#form-bsqlstdelivery #nrodocumento").val();			
		param.idpestadodelivery 	= $("#form-bsqlstdelivery #idpestadodelivery").val();

		var lstDelivery			= bsqDeliveryOficinas(param);
		
		if(lstDelivery!= undefined && lstDelivery.length > 0){											
			cargarTablaLstDelivery(lstDelivery);
			$("#container-lst-detdelivery").slideDown(1000);
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
								{ "data" 		: "iddelivery",
									"orderable"	: false,
									"sWidth" 	: "10%" },
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
								{ "data" 		: "estado",
									"orderable"	: false,
									"sWidth" 	: "10%" }, 															
                   				{ "data"      	: "",
									"orderable"	: false,
									"sWidth" 	: "6%",
                      				"class"		: "text-center",
                       	 			"mRender"  	: function (data, type, full) {
                        	 								return linkDetalleDelivery(full,false,false);  }}		
							],
			"fnDrawCallback" 	: function() {mostrarDatatable("#table-lstdelivery");},
			"fnCreatedRow"		: function (r, data, i) {
									    if(data.idpestadocarga == 2)  
											$(r).addClass("row-alerta");
								   }
		});						
	}
</script>