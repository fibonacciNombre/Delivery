<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-delivery">

	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Edición de datos de entrega</span>
	</h3>
	
    <form id="form-bsqmntdelivery">
    
    	<input type="hidden" class="form-control" id="idcourier" name="idcourier"/>
    	<input type="hidden" class="form-control" id="idpestadodelivery" name="idpestadodelivery" value="10"/>
    	
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

		        		<%--
						<div class="form-group" id="idpestado-div">
							<label for="idpestado" class="col-md-5 control-label required">Estado
							</label>
							<div class="col-md-7">
								<select class="form-control" id="idpestado" name="idpestado">
								</select>
								<div class="result"></div>
						<div class="form-group">
							<label for="dnitrabajador" class="col-md-5 control-label">DNI del colaborador</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="dnitrabajador"  name="dnitrabajador" maxlength="8">							
							</div>
						</div>
						--%>																											

					</div>
											
					<div class="col-md-6">						
						<%--
						<div class="form-group">
							<label for="tipodocumento" class="col-md-5 control-label">Tipo de documento</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="tipodocumento"  name="tipodocumento" maxlength="8">							
						<div class="form-group" id="tipdocumento-div">
							<label for="idptipodocumento"
								class="col-md-5 control-label required">Tipo de documento </label>
							<div class="col-md-7">
								<select class="form-control" id="idptipodocumento"
									name="idptipodocumento">
								</select>
								<div class="result"></div>
							</div>
						</div>
						--%>

						<div class="form-group">
							<label for="nrodocumentocli" class="col-md-5 control-label">DNI del cliente</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="nrodocumentocli"  name="nrodocumentocli" maxlength="8">							
							</div>
						</div>
						<%--
						<div class="form-group">
							<label for="dnitrabajador" class="col-md-5 control-label">DNI del colaborador</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="dnitrabajador"  name="dnitrabajador" maxlength="8">							
							</div>
						</div>
						--%>
						<div class="form-group">
							<label for="btnBsqDelivery" class="col-md-5 control-label">
							</label>
							<div class="col-md-7">
								<button id="btnExpDelivery"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		style="float:right;padding-top: 3px; padding-bottom: 3px;"
			                    		onclick="javascript:bsqDelivery();">
			                    			<i class="icono-buscar"></i>			                    			
			                    			<div style="display: inline-block; margin-top: 3px; vertical-align: top;">Buscar</div>			                    			
			                    </button>								
			                </div>							
						</div>			
					</div>														
				</div>									
	    	</div>
    	</div>	
	</form>
	
	<div id="container-lst-mntdelivery" style="margin-top:20px; display: none;">	
	
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
		
		<table id="table-lstmntdelivery" class="table table-hover table-bordered table-inter text-center">
		 	<thead>
				<tr> 
	 				<th class="text-center">N° Tarjeta</th>
					<th class="text-center">DNI Cliente</th>
				 	<th class="text-center">Nombre Cliente</th>
				 	<th class="text-center">Responsable</th>
					<th class="text-center">Fecha Entrega</th>  
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
    	loadModalCargando();

     	cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','cbocourier', ['idcourier','rznsocial'], {form: 'form-bsqmntdelivery'});
    	
    	callCargaControlParam('DELWEB_ESTADO', 'form-bsqmntdelivery #idpestado', true);
    	//cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', ['idcourier','rznsocial'], {form: 'form-cargar-entrega-tarjeta-edit'});
 

    	initDatePicker("fecentrega","calendario");
    	
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!=""){
    		$("#form-bsqmntdelivery #cbocourier").val($("#form-datos-usuario #idcourier").val());
    		$("#form-bsqmntdelivery #idcourier").val($("#form-datos-usuario #idcourier").val());    		
    		$("#form-bsqmntdelivery #cbocourier").attr("disabled",true);
    	}
		
		closeModalCargando();
	});
      
	function bsqDelivery() {

		$("#container-lst-mntdelivery").hide();
		
		cleanDatatable("table-lstmntdelivery");
		
		loadModalCargando();
		
		$("#form-bsqmntdelivery #cbocourier").attr("disabled",false);
		$("#form-bsqmntdelivery #idcourier").val($("#form-bsqmntdelivery #cbocourier").val());
		
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!="")
			$("#form-bsqmntdelivery #cbocourier").attr("disabled",true);

		var param 				= new Object();
		 
		param.nrodocumentocli 	= $("#form-bsqmntdelivery #nrodocumentocli").val(); 
		param.idcourier 		= $("#form-bsqmntdelivery #idcourier").val();
		param.fechaentregaarh 	= $("#form-bsqmntdelivery #fecentrega").val();
		param.idpestadodelivery = $("#form-bsqmntdelivery #idpestadodelivery").val();
		
		<%--
		param.tipodocumento 	= $("#form-bsqmntdelivery #tipodocumento").val();
		param.idpestado 		= $("#form-bsqmntdelivery #idpestado").val();
		param.dnitrabajador 	= $("#form-bsqmntdelivery #dnitrabajador").val();
 		--%> 		

		var lstDelivery			= bsqDeliveryUtil(param);
		
		if(lstDelivery!= undefined && lstDelivery.length > 0){											
			cargarTablaLstDelivery(lstDelivery);
			$("#container-lst-mntdelivery").slideDown(1000);
		} 
	}
	
	function exportarListaDelivery() {

		loadModalCargando();

		var param 					= new Object();
		 
		param.nrodocumentocli 		= $("#form-bsqmntdelivery #nrodocumentocli").val(); 
		param.idcourier 			= $("#form-bsqmntdelivery #idcourier").val();
		param.fechaentregaarh 		= $("#form-bsqmntdelivery #fecentrega").val();
		param.idpestadodelivery		= $("#form-bsqmntdelivery #idpestadodelivery").val();

		var pathFile				= obtArchivoLstEntregas(param);
		
		if(pathFile!=""){
			window.open("../"+pathFile, 'NewWin');
		}
	}

	function cargarTablaLstDelivery(dataList) {

		$("#table-lstmntdelivery").DataTable({ 
			"data" 			: dataList,
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
									"sWidth" 	: "10%" },
								{ "data" 		: "nombrescli",
									"orderable"	: false,
									"sWidth" 	: "25%" },
									{ "data" 		: "responsable",
									"sWidth" 	: "30%",
									"orderable"	: false }, 
								{ "data" 		: "fecentrega",
									"orderable"	: false,
									"sWidth" 	: "10%" }, 															
                   				{ "data"      	: "",
									"orderable"	: false,
									"sWidth" 	: "5%",
                      				"class"		: "text-center",
                       	 			"mRender"  	: function (data, type, full) {
                        	 							return linkDetalleDelivery(full,true);  }}		
							],
			"fnDrawCallback"	: function() {mostrarDatatable("#table-lstmntdelivery");},
			"fnCreatedRow" 		: function (r, data, i) {
								 	if(data.idpestadocarga == 2)  
										$(r).addClass("row-alerta");
						   		}
		});		
	}
</script>
