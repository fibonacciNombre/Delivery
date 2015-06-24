<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="mantenimiento-colaboradorescourier">

	<h3 class="container-title">Edición de colaboradores por courier</h3>
	
    <form id="form-bsqcolaborador">
    	<div class="panel panel-default">
	    	<div class="panel-heading">
				Buscar colaboradores por courier
			</div>    	
	    	<div class="panel-body">	    		
	    		<div class="row">	
	    			<div class="col-md-6">
						<div class="form-group">
							<label for="tipdocumento" class="col-md-5 control-label">Tipo de documento</label>
							<div class="col-md-7">
								<select class="form-control" id="tipdocumento" name="tipdocumento"> 
								 	<option value="{debe ir id de tipo de documento}">DNI</option>                       	
		                   		</select>
							</div>
						</div>	
						<div class="form-group">
							<label for="nrodocumento" class="col-md-5 control-label">Nro. documento</label>
							<div class="col-md-7">								
								<input type="text" class="form-control" id="nrodocumento"  name="nrodocumento" maxlength="12">							
							</div>
						</div>																											
					</div>						
					<div class="col-md-6">						
						<div class="form-group">
							<label for="codbbva" class="col-md-5 control-label">Courier</label>
		                    <div class="col-md-7">
								<select class="form-control" id="codbbva" name="codbbva"> 
							 		<option value="{debe ir el codbbva}">Nombre courier</option>                       	
		                   		</select>
		                    </div>
	                	</div>	
						<div class="form-group">
							<label for="estado" class="col-md-5 control-label">Estado del colaborador </label>
							<div class="col-md-7">								
								 <select class="form-control" id="estado" name="estado"> 
								 	<option>Todos</option>                       	
                        		</select>								
							</div>
						</div>				
					</div>														
				</div>	
				<div class="row">
					<div class="col-md-6"></div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="btnBsqColaborador" class="col-md-5 control-label">
							</label>
							<div class="col-md-7">
								<button id="btnBsqColaborador"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		style="float:right;padding-top: 3px; padding-bottom: 3px;"
			                    		onclick="javascript:bsqColaborador();">
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
	
	<div id="container-lst-colaboradores-courier" style="margin-top:20px;">
		<table class="table table-hover table-bordered" id="table-lst-colaboradores">
			<thead>
				<tr>
					<th class="text-center">Cod. Bbva</th>
					<th class="text-center">Razón social</th>					
					<th class="text-center desktop">Colaborador</th>
					<th class="text-center desktop">Nro. Documento</th>
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td class="desktop">a</td>					
					<td>d</td>
					<td class="desktop">s</td>
					<td>f</td>
					<td>g</td>
				</tr>
				<tr>
					<td>2</td>
					<td class="desktop">q</td>					
					<td>e</td>
					<td class="desktop">w</td>
					<td>r</td>
					<td>t</td>
				</tr>
				<tr>
					<td>3</td>
					<td class="desktop">z</td>					
					<td>c</td>
					<td class="desktop">x</td>
					<td>v</td>
					<td>b</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<script src="<%=request.getContextPath()%>/js/bbva/main-deliverytarjetas.js"></script>

<script>
	
    $().ready(function(){
    	
    	var paramBsqCourier 	= new Object();
    	
    	$.ajax({
			type 		: "POST",
			url 		: "/DeliveryTarjetas/courier.do"+"?method=bsqCourier",
			cache 		: false ,
			dataType	: "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			async 		: false,
			data 		: paramBsqCourier,
			success 	: function(rspBsqCourier){
			
								var status 	= rspBsqCourier.statustx;
								var message = rspBsqCourier.messagetx;

								if(status == 0){													
									if(rspBsqCourier.lstcouries!= undefined && rsp.lstcouries.lenght > 0){
										var lstCouriers = rspBsqCourier.lstcouries;
										for(var i=0; i<lstCouriers.length; i++){											
												var opcion = '<option value="'+lstCouriers[i].codbbva+'" >'+lstCouriers[i].rznsocial+'</option>' ;
												$("#form-bsqcolaborador #codbbva").append(opcion);										
										}
									}
								}else
									loadModalMensaje("Atención",message,null);
			},						
			error: function (rsp, xhr, ajaxOptions, thrownError) {
				closeModalCargando();
				loadModalMensaje("Error","ERROR CARGANDO COURIERS",null);								
			}			
		});	
    	
    	callCargaControlParam('PARAM_TIPODOCUMENTO','form-bsqcolaborador #tipdocumento');
    	
    	callCargaControlParam('PARAM_ESTADOS','form-bsqcolaborador #estado');
    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#table-lst-colaboradores").DataTable({
			"order"				:  [[ 0, "asc" ]],
			"searching"	 		: true,
			"paging"	 		: true,
          	"bInfo"		 		: true,
          	"bAutoWidth" 		: false,
          	"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"}			
		});
				
	});
    
	function bsqColaborador(){
	
		var param 	= new Object();
		param 		= $("#form-bsqcolaborador").serializeArray();
		
		loadModalCargando();

		setTimeout(
			  		function(){
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/courier.do"+"?method=bsqColaboradorxCourier",
							cache 		: false ,
							dataType	: "json",
							contentType : "application/x-www-form-urlencoded; charset=UTF-8",
							async 		: false,
							data 		: param,
							success 	: function(rsp){
							
												var status 	= rsp.statustx;
												var message = rsp.messagetx;
			
												closeModalCargando();
												
												if(status == 0){													
													if(rsp.lstcolaboradores!= undefined && rsp.lstcolaboradores.lenght > 0)
														cargarDataTablesColaboradores(rsp.lstcolaboradores);
												}else
													loadModalMensaje("Atención",message,null);
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR BUSCANDO COLABORADORES",null);								
							}			
						});		    					    				
				},1000);    				
    }
    
	function cargarDataTablesColaboradores(lstcouries){
		
		$("#table-lst-colaboradores").DataTable({
			"order"				:  [[ 0, "asc" ]],
			"searching"	 		: true,
			"paging"	 		: true,
          	"bInfo"		 		: true,
          	"bAutoWidth" 		: false,
          	"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"},
          	"data"		 		: lstcouries,
			"columns"    		: [
										{ "data"        : "codbbva",
											"class"		: "text-center"},
			                           	{ "orderable"	: false,
				                         	"data"		: "rznsocial",
				                         	"class"		: "desktop"},
                           				{ "orderable"	: false,
		                         			"mRender"  	: function (data, type, full) {
                     	 									return  data.apepaterno + " " + data.apematerno + ", " + data.nombres;}},
	                      				{ "orderable"	: false,
		                      				"data"      : "nrodocumento",
		                      				"class"		: "desktop"},
	                      				{ "orderable"	: false,
		                      				"data"      : "dscestado",
		                      				"class"		: "text-center"},
	                      				{ "orderable"	: false,
		                      				"data"      : "",
		                      				"class"		: "text-center",
                         	 				"mRender"  	: function (data, type, full) {
	                         	 								return linkDetalleColaborador();
	                         	 							}}										
								],
			"fnDrawCallback"	: function () { mostrarDatatable("#table-lst-colaboradores");}
		});
	}
	
	function linkDetalleColaborador(){
		return '<a class="method-ajax" ' +
					'href="/DeliveryTarjetas/courier.do?method=obtColaborador" >'+
						'<i class="i-detalle"></i>'+
				'</a>';
	}
</script>