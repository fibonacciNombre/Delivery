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
							<label for="nrodocumentocli" class="col-md-5 control-label">DNI del cliente</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="nrodocumentocli"  name="nrodocumentocli" maxlength="8">							
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
    	
    	cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', ['idcourier','rznsocial'], {form: 'form-bsqdelivery'});
		callCargaControlParam('DELWEB_ESTADO','form-cargar-entrega-tarjeta-edit #idpestado',false);

		
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
															"sWidth" 	: "15%" },
														{ "data" 		: "nrodocumentocli",
															"orderable"	: false,
															"sWidth" 	: "15%" },
														{ "data" 		: "nombrescli",
															"orderable"	: false,
															"sWidth" 	: "35%" },

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
															"sWidth" 	: "20%" }, 															
					                      				{ "data"      	: "",
															"orderable"	: false,
															"sWidth" 	: "10%",
						                      				"class"		: "text-center",
				                         	 				"mRender"  	: function (data, type, full) {
					                         	 								return linkDetalleDelivery(full);  }}
														
													],
									"fnDrawCallback" : function() {
														var table = $(idDataTable).dataTable();
														
														if (table.fnGetData().length == 0)
															table.parent().toggle(true);
														}
								});		
	}

	function linkDetalleDelivery(full) {
		enlace = "<a data-toggle='modal' "
					+ "data-target='#modalDetalleEntrega' "
					+ "onclick='return rowSelected("+ JSON.stringify(JSON.stringify(full)) +");'>"
					+ "<i class='i-detalle'></i>" 
				+ "</a>";

		return enlace;
	}

	function rowSelected(json) {
		json = JSON.parse(json);

		$("#form-cargar-entrega-tarjeta-edit #iddelivery").val(json.iddelivery);
        $("#form-cargar-entrega-tarjeta-edit #tipodocumento").val(json.tipodocumento);
        $("#form-cargar-entrega-tarjeta-edit #nrodocumentocli").val(json.nrodocumentocli);
        $("#form-cargar-entrega-tarjeta-edit #nombrescli").val(json.nombrescli);
        $("#form-cargar-entrega-tarjeta-edit #tipotarjeta").val(json.tipotarjeta);
        $("#form-cargar-entrega-tarjeta-edit #pridigtarjeta").val(json.pridigtarjeta);
        $("#form-cargar-entrega-tarjeta-edit #ultdigtarjeta").val(json.ultdigtarjeta);
        $("#form-cargar-entrega-tarjeta-edit #nrocontrato").val(json.nrocontrato);
        $("#form-cargar-entrega-tarjeta-edit #mtoasoctarjeta").val(json.mtoasoctarjeta);
        $("#form-cargar-entrega-tarjeta-edit #fecentrega").val(json.fecentrega);
        $("#form-cargar-entrega-tarjeta-edit #horaentrega").val(json.horaentrega);
        $("#form-cargar-entrega-tarjeta-edit #lugarentrega").val(json.lugarentrega);
        $("#form-cargar-entrega-tarjeta-edit #indverificacion").val(json.indverificacion);
        $("#form-cargar-entrega-tarjeta-edit #direccioncli").val(json.direccioncli);
        $("#form-cargar-entrega-tarjeta-edit #distritocli").val(json.distritocli);
        $("#form-cargar-entrega-tarjeta-edit #latitudofi").val(json.latitudofi);
        $("#form-cargar-entrega-tarjeta-edit #longitudofi").val(json.longitudofi);
        $("#form-cargar-entrega-tarjeta-edit #correocli").val(json.correocli);
        $("#form-cargar-entrega-tarjeta-edit #telmovilcli").val(json.telmovilcli);
        $("#form-cargar-entrega-tarjeta-edit #ordenentrega").val(json.ordenentrega);
        $("#form-cargar-entrega-tarjeta-edit #idcourier").val(json.idcourier);
        $("#form-cargar-entrega-tarjeta-edit #idtercero").val(json.idtercero);
        $("#form-cargar-entrega-tarjeta-edit #idpestado").val(json.idpestado);
        $("#form-cargar-entrega-tarjeta-edit #idarchivo").val(json.idarchivo);
        $("#form-cargar-entrega-tarjeta-edit #idpestadocarga").val(json.idpestadocarga);
        $("#form-cargar-entrega-tarjeta-edit #historial").val(json.historial);
        $("#form-cargar-entrega-tarjeta-edit #grupocarga").val(json.grupocarga);
	}
	
	function guardarDatosEditados() { 
		if ($("#form-cargar-entrega-tarjeta-edit").valid()){
		 
     		loadModalCargando();
     		
     		$.ajax({
				type 		: "POST",
				url 		: "/DeliveryTarjetas/delivery.do?method=mntDelivery",
				cache 		: false,
				dataType 	: "json",
				contentType	: "application/x-www-form-urlencoded; charset=UTF-8",
				async 		: false,
				data 		: $("#form-cargar-entrega-tarjeta-edit").serializeArray(),
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
     						 
		} else {
			$.each($('input[type=text], select ,textarea', '#form-cargar-entrega-tarjeta-edit'),function(k){
			   validateItems("form-cargar-entrega-tarjeta-edit", this);
			});
		} 
	}
		
</script>
