<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<script>

	var flagCargaDashboard = false;
	/*********************************
	*	FUNCIONES GENERICAS	  		 *
	**********************************/	

	function activateLoading(flag){
		if(flag){
			if(!flagCargaDashboard){
				loadModalCargando();
				flagCargaDashboard = true;
			}
		}
		else{
			closeModalCargando();
			flagCargaDashboard = false;
		}
	}
	
	function errorDialog(){
		$("#frmAtenciones").hide();
		$("#imgBrother").hide();
		$("#frmServiciosDisponibles").hide();
	   	activateLoading(false);
	   	
	   	var frmCuotasPendientes =
			'<div class="info-pendientes visible-sm-block visible-md-block visible-lg-block"> Felicitaciones! </div>';
		
		$("#imgBrother").show();
		$("#nroPagosPendientes").text(0);
		$("#frmCuotasPendientes").append(frmCuotasPendientes);
	}
	
	function cargarSeguros(tipoProducto){
		var pagina = "";
		if(tipoProducto == "SALUD"){
			pagina = "goMisSegurosSalud";
		}
		else if(tipoProducto == "VIDA"){
			pagina = "goMisSegurosVida";
		}
		else if(tipoProducto == "VEHICULAR"){
			pagina = "goMisSegurosVehicular";
		}
		else if(tipoProducto == "SOAT"){
			pagina = "goMisSegurosVehicular";
		}
		else if(tipoProducto == "DOMICILIARIO"){
			pagina = "goMisSegurosDomiciliario";
		}
		else if(tipoProducto == "OTROS"){
			pagina = "goMisSegurosOtros";
		}
		
		return pagina;
	}
	/************************************************************
	*  CARGA DE TODOS LOS ELEMENTOS DE LA PANTALLA 				*
	*************************************************************/
	$(document).ready(function() {
		
		var CONST_ACTIVO = 1;
		//$("#frmAtenciones").hide();
		//$("#frmServiciosDisponibles").hide();
		$("#imgBrother").hide();
		
		/********************
		* NOMBRE DE TITULAR	*
		*********************/
		if($.trim(CTE_JSON_PERSONAPWEB.nombre))
			$("#nameTitularDashboard").text(toTitleCase(CTE_JSON_PERSONAPWEB.nombre));						
		
		/***************************
		* CARGAR POLIZAS PENDIENTE *
		***************************/
		var param         	= new Object();
		param.idetercero	= String($("#form-datos-cliente #idetercero").val());
		param.codexterno	= String($("#form-datos-cliente #codexterno").val());

		$.post("/PORTALWEB/misseguros.do?method=lstPolPagoPendiente",{datos:JSON.stringify(param)},function(result){
			
			var objetoPolizas	= new Object();
			var indSalud		= null;
			var indEPS			= null;
			var indVehiculos	= null;
			var indSOAT			= null;
			var polizas			= null;

			try{
				objetoPolizas	= eval(result);
				
				if(objetoPolizas[0].origen){
					errorDialog();
				}
				else{
					var datosDashBoard = objetoPolizas[0].listAcuerdoWeb;
					
					indSalud		= datosDashBoard.indsalud;
					indVehiculos	= datosDashBoard.indvehicular;
					indSOAT			= datosDashBoard.indsoat;
					polizas			= datosDashBoard.acuerdoWeb;

					if(indSalud == CONST_ACTIVO)
						$("#form-ind-seguros #ind-seg-salud").val("1");		

					if(indVehiculos == CONST_ACTIVO)
						$("#form-ind-seguros #ind-seg-vehicular").val("1");
					
					
					if(indSalud == CONST_ACTIVO || indVehiculos == CONST_ACTIVO)
					{
						$("#frmAtenciones").show();
						$("#frmServiciosDisponibles").show();
						
						if(indSalud == CONST_ACTIVO){
							$("#lnkClinicas").show();
							$("#lnkMedicoOnline").show();

							$("#form-ind-seguros #ind-seg-salud").val("1");							
						}
						else{
							//$("#lnkClinicas").hide();
							$("#lnkClinicas").addClass("div_disabled");
							//$("#lnkMedicoOnline").hide();
							$("#lnkMedicoOnline").addClass("div_disabled");
						}
						
						if(indVehiculos == CONST_ACTIVO){
							$("#lnkTalleres").show();
							$("#lnkRobo").show();
							$("#lnkAuxilioMecanico").show();
							$("#lnkChoferReemplazo").show();

							$("#form-ind-seguros #ind-seg-vehicular").val("1");
						}else{
							//$("#lnkTalleres").hide();
							$("#lnkTalleres").addClass("div_disabled");
							//$("#lnkRobo").hide();
							$("#lnkRobo").addClass("div_disabled");
							//$("#lnkAuxilioMecanico").hide();
							$("#lnkAuxilioMecanico").addClass("div_disabled");
							//$("#lnkChoferReemplazo").hide();
							$("#lnkChoferReemplazo").addClass("div_disabled");
						}
					}
					else{
						$("#frmAtenciones").hide();
						$("#frmServiciosDisponibles").hide();
					}
										
					if(!polizas || jQuery.isEmptyObject(polizas)){
						
						var frmCuotasPendientes =
							'<div class="info-pendientes visible-sm-block visible-md-block visible-lg-block"> '+
	        					'Felicitaciones! </div>';
						
						$("#imgBrother").show();
						$("#nroPagosPendientes").text(0);
						$("#frmCuotasPendientes").append(frmCuotasPendientes);
						
					}
					else{
						
						var lstPagosPendientes = "lstPagosPendientes";
						var contenedorCuotasPen = 
							'<ul id ="'+lstPagosPendientes+'" class="list-group list-group-pago"> </ul>';
						
						$("#imgBrother").hide();
						$("#nroPagosPendientes").text(polizas.length);
						$("#frmCuotasPendientes").append(contenedorCuotasPen);
						$("#frmCuotasPendientes").css("overflow-y","scroll");
						
						$.each(polizas, function(i, value) {
	
							var jsonPoliza 		  = JSON.stringify(value);                                
		                    var jsonPolizaReplace = jsonPoliza.replace(/"/g, '&');
		                    
		                    var pagina = cargarSeguros(value.tipoproducto);
	
			      			var enlace = '<a class="btn btn-default btn-small pull-right method-ajax"  '+
										 'href="/PORTALWEB/misseguros.do?method='+ pagina +'" '+
										 'onclick="return blockSelected(\'' + jsonPolizaReplace +'\',\'polizaMisSeguros\','+i+')"> ' +
										 'Ver detalle '+
										 '</a>';
							
							var elemento =
								'<li class="list-group-item">' + enlace + 
									value.dscproducto +
								'</li>';
							
							$("#"+lstPagosPendientes).append(elemento);
			     		});
					}
					
					closeModalCargando();
					load_event_ajax();
					
					
					$(".js-btn-serv-dash").click(function(){
						
						var $this = $(this);
						var lnk = $this.attr("id");
						$("div.modal-backdrop.fade.in").attr("style","display:none");
	 					$(".panel").removeClass("active");
						$("#"+lnk).attr("class","active");
						var nomPadre = $("#"+lnk).closest("div").attr("id");
						var panelPadre = $("#"+nomPadre).closest(".panel");
						var nomacordeon = panelPadre.children(":first").attr("id");
	 					$("#"+nomacordeon).attr("class","active");
	 					$("#"+nomPadre).attr("class","collapse in");
	
					});
					
					
					$(".js-buscador-clinica").click(function(){
						
						var lnk = "lstClinicas";
						$("div.modal-backdrop.fade.in").attr("style","display:none");
	 					$(".panel").removeClass("active");
						$("#"+lnk).attr("class","active");
						var nomPadre = $("#"+lnk).closest("div").attr("id");
						var panelPadre = $("#"+nomPadre).closest(".panel");
						var nomacordeon = panelPadre.children(":first").attr("id");
	 					$("#"+nomacordeon).attr("class","active");
	 					$("#"+nomPadre).attr("class","collapse in");
					});
					
					$(".js-buscador-taller").click(function(){
						
						var lnk = "lstTalleres";
						$("div.modal-backdrop.fade.in").attr("style","display:none");
	 					$(".panel").removeClass("active");
						$("#"+lnk).attr("class","active");
						var nomPadre = $("#"+lnk).closest("div").attr("id");
						var panelPadre = $("#"+nomPadre).closest(".panel");
						var nomacordeon = panelPadre.children(":first").attr("id");
	 					$("#"+nomacordeon).attr("class","active");
	 					$("#"+nomPadre).attr("class","collapse in");
						
					});
					
	
					$(".js-mis-seguros").click(function(){
						
						$("div.modal-backdrop.fade.in").attr("style","display:none");
	 					$(".panel").removeClass("active");
						var panelPadre = "#panelSeguros";
	 					$(panelPadre).attr("class","active");
						
					});
				}					
				
			}
			catch (e) {
				errorDialog();
			}	
		});
	});
</script>
	
<div class="container-dashboard">
	<h1 style="margin-top: 0px;" >¡Hola, <span id="nameTitularDashboard"></span>!</h1>

	<p>Hemos preparado este espacio para que puedas auto atenderte con nuestros servicios en línea.</p>
	
	<p class="container-etiqueda-label">
		Te sugerimos empezar revisando esta sección:&nbsp; 		
		<a class='label label-danger label-default-dash js-mis-seguros method-ajax' href='../misseguros.do?method=goMisSeguros'>Tus seguros</a>							
	</p>
	
	<div class="row">
	
		<div class="col-md-8">
		
			<h2 class="title-dashboard" style="margin-top: 10px;">Pagos pendientes</h2>
			<div id="frmAdvertencia" class="panel panel-danger-rimac">
				<img src="/PORTALWEB/img/default/brother-dashboard.png" class="img-brother" alt="brother" id="imgBrother">
                <div class="panel-heading">
                  	<p class="panel-title">
                  		<i class="i-advertencia"></i>
						Tienes <b id="nroPagosPendientes">0</b> póliza(s) con cuotas pendientes de pago
					</p>
                </div>
                <div id="frmCuotasPendientes" class="panel-body">
               	</div>
			</div>
		</div>
		
		<div id ="frmAtenciones" class="col-md-4">

			<h2 class="title-dashboard" style="margin-top: 10px;">Dónde atenderte</h2>
			<div class="panel panel-default panel-body-buscador box-deg-gris">
				<a id="lnkClinicas" class="js-buscador-clinica method-ajax" href="/PORTALWEB/clinicas.do?method=goMisClinicas">				
					<div class="panel-body" id="lnkClinicas-bdy">
						<i class="i-mas-clinica"></i> 
						<span class="title-buscador-dashboard"> Buscador de Clínicas </span>
					</div>				
				</a>
			</div> 
			<div id="lnkTalleres" class="panel panel-default panel-body-buscador box-deg-gris">
				<a class="js-buscador-taller method-ajax" href="/PORTALWEB/talleres.do?method=goMisTalleres">					
					<div class="panel-body">
						<i class="i-mas-config"></i> 
						<span class="title-buscador-dashboard"> Buscador de Talleres </span>
					</div>					
				</a>
			</div>
		</div>
		
	</div>
	
	<div id="frmServiciosDisponibles" class="row">		
		<div class="col-sm-12">			
			<h2 class="title-dashboard" style="margin-top: 10px;">Tus servicios disponibles</h2>	
			<div class="row">			
				<div id="lnkMedicoOnline" class="col-sm-3">
                	<div class="row">
	                  	<div class="container-cuadro-servicio" <%--id="container-chatrimac"--%>>
		                    <a class="btn-serv-dash method-ajax js-btn-serv-dash" onclick="javascript:abrirChatMedico();" style="cursor: pointer;">
								<div class="cuadro-servicio">
								  <i class="i-amarillo-left"></i>
								  <span >
								 		 Médico online
								  </span>
								  <i class="i-medico-online"></i>
								</div>
		                    </a>
		                    <div class="desc-notificacion">
		                      Utiliza nuestro chat para consultas médicas
		                    </div>
	                  	</div>
                	</div>
              	</div>
				
				<div id="lnkRobo" class="col-sm-3">
	            	<div class="row">
		            	<div  class="container-cuadro-servicio"> 
			            	<a id="obtSinistro" class="btn-serv-dash method-ajax js-btn-serv-dash" href="/PORTALWEB/asistenciaveh.do?method=goSiniestro">
				            	<div class="cuadro-servicio">
				                	<i class="i-celeste-left"></i>
				                    	<span>
				                        	Choque o Robo
										</span>
									<i class="i-robo-choque"></i>
			                    </div>
		                     </a>
		                     <div class="desc-notificacion">
		                       Solicítalo en caso de accidente vehicular
		                     </div>
	                	</div>
					</div>
				</div>
				
				<div id="lnkAuxilioMecanico" class="col-sm-3">
					<div class="row">
						<div  class="container-cuadro-servicio"> 
		                    <a id="obtAuxilio" class="btn-serv-dash method-ajax js-btn-serv-dash" href="/PORTALWEB/asistenciaveh.do?method=goAuxilio">
								<div class="cuadro-servicio">
								  <i class="i-verde-1-left"></i>
								  <span>
								   Auxilio Mecánico
								  </span>
								  <i class="i-auxilio-mecanico"></i>
								</div>
		                    </a>
		                    <div class="desc-notificacion">
		                      Solicítalo en caso auxilio mecánico disponible(s).
		                    </div>
	                 	</div>
					</div>
	           	</div>
				
				<div id="lnkChoferReemplazo" class="col-sm-3">
					<div class="row">
						<div class="container-cuadro-servicio">
							<a id="obtConductor" class="btn-serv-dash method-ajax js-btn-serv-dash" href="/PORTALWEB/asistenciaveh.do?method=goConductor">
								<div class="cuadro-servicio">
						       		<i class="i-verde-2-left"></i>
						       		<span>
						        		Chofer de reemplazo
						       		</span>
						       		<i class="i-chofer-reemplazo"></i>
								</div>
							</a>
							<div class="desc-notificacion">
								Solicítalo en caso  chofer de reemplazo disponible(s).
							</div>
						</div>
					</div>
				</div>				
			</div>			
		</div>		
	</div>
	
	<div class="row">	
		<div class="col-sm-12">	
			<h2 class="text-center" style="margin-top: 10px;">Nuestros Sites</h2>
			<div class="panel-site-container" style="text-align: center;">				
				<%-- INI MFARFANR 16/12/2014 --%>
				<%--
				<a href="#" class="btn btn-default panel-site-item box-deg-gris">
					<i class="i-factura"></i> Factura Electrónica
				</a>
				--%>
				<%-- FIN MFARFANR 16/12/2014 --%> 			
				<a target="_blank" href="http://rimachablaclaro.com/pagcli/index.aspx" class="btn btn-default panel-site-item box-deg-gris" style="padding-top: 25px; padding-bottom: 25px;">
					<i class="i-telef-serv"></i> RIMAC Habla Claro
				</a> 				
				<%--
				<a target="_blank" href="http://www.rimac.com.pe/wps/portal/rimac/inicio/noticias/promociones" class="btn btn-default panel-site-item box-deg-gris" style="padding-top: 25px; padding-bottom: 25px;">
				--%>
				<a target="_blank" href="http://www.rimac.com.pe/uploads/Programa-Descuentos-Beneficios-Lima-y-Provincia.pdf" class="btn btn-default panel-site-item box-deg-gris" style="padding-top: 25px; padding-bottom: 25px;">
				
					<i class="i-estrella"></i> Programa de beneficios
				</a>	
			</div>	
		</div>		
	</div>
	<%--
	<div class="row">	
		<div class="container-serv-mobile">		
			<div class="col-sm-6 visible-sm-block visible-md-block visible-lg-block">
				<img src="<%=request.getContextPath()%>/img/default/mobil.png" class="img-responsive" alt="Image">
			</div>			
			<div class="col-sm-6">			
				<div class="container-descrip-mobile">			
					<p class="title-container-descript-mobile">Descarga aquí el aplicativo móvil de RIMAC para iPhone y para Android</p>		
					<div class="mobile-descarga">			
						<div class="mobile-image">			
							<i class="i-icon-mobile"></i>			
						</div>			
						<div class="link-descarga">			
							<a href="#" class="g-play"></a> <a href="#" class="g-istore"></a>			
						</div>			
					</div>			
					<div class="clearfix"></div>			
				</div>		
			</div>		
		</div>	
	</div>
	--%>
</div>

