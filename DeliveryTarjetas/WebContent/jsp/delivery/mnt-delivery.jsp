<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-delivery">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Edición de datos de entrega</span>
	</h3>
	
    <form id="form-bsqdelivery">
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
							<label for="nrodocumento" class="col-md-5 control-label">DNI del cliente</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="dnicliente"  name="dnicliente" maxlength="8">							
							</div>
						</div>
						
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
	
	<div id="container-lst-delivery" style="margin-top:20px;">		
		<div id="div-container-lst-delivery"></div>		
	</div>
	
	<%@include file="/jsp/delivery/det-delivery.jsp" %>
	
</div>

<script>
	
    $().ready(function(){    
    	
    	loadModalCargando();
    	
    	cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', {form: 'form-bsqdelivery'});
    	
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
      
	function bsqDelivery() {

		loadModalCargando();

		var param = new Object();
		 
		param.dnicliente 		= $("#form-bsqdelivery #dnicliente").val(); 
		param.idcourier 		= $("#form-bsqdelivery #idcourier").val();
		param.fecentrega 		= $("#form-bsqdelivery #fecentrega").val();

		$.ajax({
			type 			: "POST",
			url 			: "/DeliveryTarjetas/delivery.do?method=lstDelivery",
			cache 			: false,
			async 			: false,
			dataType 		: 'json',
			contentType 	: "application/x-www-form-urlencoded; charset=UTF-8",
			data 			: param,
			success 		: function(data) {
									var jsonDelivery = eval(data);
									
									createHtmlTable(jsonDelivery, "table-lst-delivery");	
									
									closeModalCargando(); 
			},
			error 			: function(xhr, ajaxOptions, thrownError) {
									closeModalCargando();
									loadModalMensaje('Lo Sentimos','<center>Hubo problemas en el procesamiento de datos.</center>',function(){}); 
			}
		});
	}
	
	function createHtmlTable(dataList, table) {

		$("#divdestroy *").remove();

		var html = 
			 '<div id="divdestroy" class="row visible-md-block visible-sm-block visible-lg-block">' + 
				 '<div class="table-responsive col-md-12">' + 
					 '<table id="' + table + '" class="table table-hover table-bordered table-inter text-center">' +
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

		$("#div-container-lst-delivery").append(html);

		var idDataTable = '#' + table;

		$(idDataTable).DataTable({ "data" 			: dataList,
									"ordering" 		: true,
									"searching" 	: true,
									"paging" 		: true,
									"bInfo" 		: true,
									"bAutoWidth" 	: true,
									"oLanguage"  	: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
									"columns" 		: [ 
														{ "data" 		: "ultdigtarjeta",
															"orderable"	: false,
															"sWidth" 	: "10%" },
														{ "data" 		: "nrodocumentocli",
															"orderable"	: false,
															"sWidth" 	: "10%" },
														{ "data" 		: "nombrescli",
															"orderable"	: false,
															"sWidth" 	: "20%" },
														{ "data" 		: "mtoasoctarjeta",
															"sWidth" 	: "15%",
															"orderable"	: false,
															"mRender" 	: function(data, type, full) {
																				var a = '<div style="text-align: right;">'
																						+ formatNumber(data)
																						+ '</div>';
																				return a; } }, 
														{ "data" 		: "fecentrega",
															"orderable"	: false,
															"sWidth" 	: "10%" }, 
														{ "data"    	: "",
															"sWidth"	: "5%",
															"orderable"	: false,
											                "mRender"	: function (data, type, full) {
								        										return linkDetalleEntrega(data, 
																	         		 						full, 
																	         		 						idDataTable,
																	         		 						"idDetalleEntrega"); }
														}
													],
									"fnDrawCallback" : function() {
														var table = $(idDataTable).dataTable();
														
														if (table.fnGetData().length == 0)
															table.parent().toggle(true);
														}
								});		
	}

	function linkDetalleEntrega(data, full, idDataTable, nomVariable){
		
		var enlace = null;
			
		enlace = "<a data-toggle='modal' " + 
					" data-target='#modalDetalleEntrega' "  +
					" onclick='return rowSelected(\""+idDataTable+"\",\""+nomVariable+"\");'>"+
						"<i class='i-detalle'></i>" +
				 "</a>";

		return enlace;
	}
		
</script>