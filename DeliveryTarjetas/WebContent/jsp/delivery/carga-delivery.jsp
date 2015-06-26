<%@ page language="java" contentType="text/html; charset=utf-8"%>

<script src="<%=request.getContextPath()%>/js/default/jquery.form.js"></script>

<div>
	
	<h3 class="container-title">
		<span class="vineta-title"></span>
		<span>Carga de datos de entrega</span>
	</h3>

	<!--  Botones de filtro de la grilla -->
	<form id="form-cargararchivo" method="post" enctype="multipart/form-data">
	
		<div class="row">		
			<div class="col-md-12">
		        <div class="form-group" id="filename-div">
		            <label for="filename" class="col-md-6 control-label required">Ubicación archivo </label>
		            <div class="col-md-12">
		                <input type="file" class="col-md-6 form-control" id="filename" name="filename" >
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
		                <select id="idcourier" name="idcourier" class="form-control">
						</select>
		                <div class="result"></div>
		            </div>
		        </div>
			</div>		
		</div>
		
		<div class="row">
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
		
		cargarCombo('/DeliveryTarjetas/courier.do', 'lstCourier','idcourier', ['idcourier', 'rznsocial'], {form: 'form-cargararchivo'});
		
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

				idcourier 			: {				required 	: true },
				
				fecentrega 			: {				required 	: true },
				
				filename 			: {				required 	: true }
				
			},
			messages : {
				idcourier 			: {				required 	: "Debes seleccionar un Courier" },
			
				fecentrega 			: {				required 	: "Debes ingresar la fecha de entrega" },
				
				filename 			: {				required 	: "Debes seleccionar el archivo a cargar" }					
			}
		});	
		
		$('#btnCargar').click(function(){
			
			var idcourier_ 		= $("#form-cargararchivo #idcourier").val(),
			fechaentrega_ 		= $("#form-cargararchivo #fecentrega").val();
			
			$('#form-cargararchivo').ajaxForm({	
												url		:"/DeliveryTarjetas/delivery.do?method=uploadFile&"+
																						"idcourier="+ idcourier_ + "&"+
																						"fecentrega="+ fechaentrega_ +"&"+
																						"tipoarchivo=xlsx",
												type	:"post", 
												success	:function(data){}
											});
			});
		
		closeModalCargando(); 
		
	});
</script>