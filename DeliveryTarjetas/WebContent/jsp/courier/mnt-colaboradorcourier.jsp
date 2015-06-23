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
							<label for="codbbva" class="col-md-5 control-label">Código Bbva</label>
		                    <div class="col-md-7">
		                        <input type="text" class="form-control" id=codbbva name="codbbva" maxlength="200">
		                    </div>
	                	</div>	
						<div class="form-group">
							<label for="rznsocial" class="col-md-5 control-label">Razón social</label>
							<div class="col-md-7">
								<input type="text" class="form-control" id="rznsocial" name="rznsocial" maxlength="200">
							</div>
						</div>						
					</div>
						
					<div class="col-md-6">						
						<div class="form-group">
							<label for="estado" class="col-md-5 control-label">Estado</label>
							<div class="col-md-7">								
								 <select class="form-control" id="estado" name="estado"> 
								 	<option>Todos</option>                       	
                        		</select>								
							</div>
						</div>							
						<div class="form-group">
							<label for="btnRegistrar" class="col-md-7">
								<button id="btnRegistrar"
			                    		type="button" 
			                    		class="btn btn-primary" 
			                    		onclick="javascript:bsqColaboradorCourier();">
			                    			<%--
			                    				<i style="background-position: -592px -1405px;height: 25px;margin-right: 5px;transform: scale(0.5);width: 26px;"></i>			                    			
			                    			--%>
			                    			Buscar			                    			
			                    </button>
			                </label>							
						</div>																
					</div>										
				</div>						
	    	</div>
    	</div>	
	</form>
	
	<div id="container-lst-colaboradores-courier" style="margin-top:20px;">
		<table class="table table-hover table-bordered" id="table-lst-couriers">
			<thead>
				<tr>
					<th class="text-center">Cod. Bbva</th>
					<th class="text-center">Razón social</th>
					<th class="text-center desktop">Móvil</th>
					<th class="text-center desktop">Correo electrónico</th>
					<th class="text-center">Estado</th>
					<th class="text-center">Editar</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>a</td>
					<td class="desktop">s</td>
					<td class="desktop">d</td>
					<td>f</td>
					<td>g</td>
				</tr>
				<tr>
					<td>2</td>
					<td>q</td>
					<td class="desktop">w</td>
					<td class="desktop">e</td>
					<td>r</td>
					<td>t</td>
				</tr>
				<tr>
					<td>3</td>
					<td>z</td>
					<td class="desktop">x</td>
					<td class="desktop">c</td>
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
    	
    	callCargaControlParam('PWEB_USR_TIPDOC','form-bsqcourier #estado');
    	    	
		jQuery.validator.addMethod("alphanumeric", function(value, element) {
	        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		});
		
		$("#table-lst-couriers").DataTable({
			"order"				:  [[ 0, "asc" ]],
			"searching"	 		: true,
			"paging"	 		: true,
          	"bInfo"		 		: true,
          	"bAutoWidth" 		: false,
          	"oLanguage"  		: {"sUrl": "/DeliveryTarjetas/recursos/idioma/es_ES.txt"}			
		});
				
	});
    
	function bsqCourier(){
	
		var param 	= new Object();
		param 		= $("#form-bsqcolaborador").serializeArray();
		
		loadModalCargando();

		setTimeout(
			  		function(){
			   			$.ajax({
							type 		: "POST",
							url 		: "/DeliveryTarjetas/courier.do"+"?method=bsqCourier",
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
													if(rsp.lstcouries!= undefined && rsp.lstcouries.lenght > 0)
														cargarDataTablesCouries(rsp.lstcouries);
												}else
													loadModalMensaje("Atención",message,null);
							},						
							error: function (rsp, xhr, ajaxOptions, thrownError) {
								closeModalCargando();
								loadModalMensaje("Error","ERROR BUSCANDO COURIER",null);								
							}			
						});		    					    				
				},1000);    				
    }
    
	function cargarDataTablesCouries(lstcouries){
		
		$("#table-lst-couriers").DataTable({
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
				                         	"data"		: "rznsocial"},
                           				{ "orderable"	: false,
		                      				"data"      : "nummovil",
		                      				"class"		: "desktop"},
	                      				{ "orderable"	: false,
		                      				"data"      : "email",
		                      				"class"		: "desktop"},
	                      				{ "orderable"	: false,
		                      				"data"      : "dscestado",
		                      				"class"		: "text-center"},
	                      				{ "orderable"	: false,
		                      				"data"      : "",
		                      				"class"		: "text-center",
                         	 				"mRender"  	: function (data, type, full) {
	                         	 								return linkDetalleCourier();
	                         	 							}}										
								],
			"fnDrawCallback"	: function () { mostrarDatatable('#grupoSalud',nomTablaSalud);}
		});
	}
	
	function linkDetalleCourier(){
		return '<a class="method-ajax" ' +
					'href="/DeliveryTarjetas/courier.do?method=obtCourier" >'+
						'<i class="i-detalle"></i>'+
				'</a>';
	}
</script>