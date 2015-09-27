<%@ page language="java" contentType="text/html; charset=utf-8"%>

<script src="<%=request.getContextPath()%>/js/default/jquery.form.js"></script>

<div>
	
	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Carga de datos de entrega</span>
	</h3>

	<!--  Botones de filtro de la grilla -->
	<form id="form-cargararchivo" method="post" enctype="multipart/form-data">
		
		<input type="hidden" class="form-control" id="idcourier" name="idcourier"/>
		
		<div class="row">		
			<div class="col-md-12">
		        <div class="form-group" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Ubicación archivo </label>
		            <div class="col-md-12">
		                <input type="file" class="col-md-6 form-control" id="filename" name="filename" style="height: auto;" >
		                <div class="result"></div>
		            </div>
		        </div>
			</div>		
		</div>
		
		<div class="row">		
			<div class="col-md-6">
		        <div class="form-group" id="filename-div">
		            <label for="fecnac_persona" class="col-md-6 control-label required">Fecha de entrega </label>
		            <div class="col-md-12">
		                <div id="div-fecentrega" class="input-group">
							<input type="text" readonly id="fecentrega" name="fecentrega" class="form-control calendario"> 
							<span class="input-group-addon">
								<a id="btnfecentrega" href="javascript:void(0)" class="btn-date calendario"> 
									<span class="glyphicon glyphicon-calendar"></span>
								</a>
							</span>
							<div class="result"></div>
						</div>
		            </div>
		        </div>
			</div>
			<div class="col-md-6">
		        <div class="idcourier" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Courier </label>
		            <div class="col-md-12">
		                <select id="cbocourier" name="cbocourier" class="form-control">
						</select>
		                <div class="result"></div>
		            </div>
		        </div>
			</div>		
		</div>
		
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-12">
				 <div class="col-md-12">
	                <div class="form-group">
	                	<label class="col-md-6 control-label" style="padding: 0px;">
	                	</label>
	                	<div class="col-sm-6 pull-right " style="text-align: right; padding: 0px;">
		                    <button type="submit" id="btnCargar" class="btn btn-primary pull-right start">
								Cargar
							</button>
	                    </div>
	                </div>
	            </div>	     									
			</div>
		</div>
	</form>

</div>
<script>

	$().ready(function() {
		
		loadModalCargando();
		
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','cbocourier', ['idcourier', 'rznsocial'], {form: 'form-cargararchivo'});
		
		$("#fecentrega").datepicker({ 	beforeShow 		: function() {
															setTimeout(function() {
																$('.ui-datepicker').css({'z-index' : 9999, 'border' : '1px solid #ccc'});
															}, 0); },
										onSelect 		: function(dateText, inst) {
															$("#fecentrega").removeClass("error"); }
									});
		
		$(".calendario").click(function() {
									$("#fecentrega").datepicker("show");
		})
		
		$("#form-cargararchivo").validate({
			rules : {

				cbocourier 			: {				required 	: true },
				
				fecentrega 			: {				required 	: true },
				
				filename 			: {				required 	: true }
				
			},
			messages : {
				cbocourier 			: {				required 	: "Debes seleccionar un Courier" },
			
				fecentrega 			: {				required 	: "Debes ingresar la fecha de entrega" },
				
				filename 			: {				required 	: "Debes seleccionar el archivo a cargar" }					
			}
		});	
		
		if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!=""){
    		$("#form-cargararchivo #cbocourier").val($("#form-datos-usuario #idcourier").val());
    		$("#form-cargararchivo #idcourier").val($("#form-datos-usuario #idcourier").val());    		
    		$("#form-cargararchivo #cbocourier").attr("disabled",true);
    	}
		
		$('#btnCargar').click(function(){
				cargarArchivo();			
			});
		
		closeModalCargando(); 
		
	});
	
	function cargarArchivo(){
		
		if($('#form-cargararchivo').valid()){
			
			loadModalCargando();
			
			$("#form-cargararchivo #cbocourier").attr("disabled",false);
			$("#form-cargararchivo #idcourier").val($("#form-cargararchivo #cbocourier").val());
			
			if($("#form-datos-usuario #idcourier").val()!=null  && $("#form-datos-usuario #idcourier").val()!="")
				$("#form-cargararchivo #cbocourier").attr("disabled",true);
			
			var idcourier_ 		= $("#form-cargararchivo #idcourier").val();
			var fechaentrega_ 	= $("#form-cargararchivo #fecentrega").val();
			
			$('#form-cargararchivo').ajaxForm({	
												url		:"/DeliveryTarjetas/delivery.do?method=uploadFile&"+
																						"idcourier="+ idcourier_ + "&"+
																						"fecentrega="+ fechaentrega_ +"&"+
																						"idpestadodelivery=0" ,
												type	:"post", 
												success	:function(data){ 

													var jsonRpta = JSON.parse(data);
													if(jsonRpta.resultado == 0){
														loadModalMensaje("Enhorabuena",
																			"Se ha completado la carga de entregas.",
																			function(){$("#view-carga-entrega").click();});		
													} else if(jsonRpta.resultado == 2){
														loadModalMensaje("Alerta!", "Se ha completado la carga de entregas." + jsonRpta.mensaje, null)
													} else{
														loadModalMensaje("Error!", jsonRpta.mensaje, null);
													}
													
													closeModalCargando();
												},
												error	:function(){
																
												}
											});			
		}		
	}
</script>
