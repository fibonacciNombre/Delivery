<div id="footer" class="color-bg-login-transaparent">	
	<div style="font-size: 11px; margin-bottom: 10px; margin-top: 10px;">
		Haz clic <a style="cursor: pointer;" id="link-content-navegadores" data-toggle="modal" data-target="#content-navegadores">AQUÍ</a> para ver los navegadores soportados.		
	</div>
	
	<div class="footer-signature">
		<strong>Bbva Continental </strong> . Todos los derechos reservados.
	</div>
	
</div>

<%-- INI CONTENT NAVEGADORES --%>
<div id="content-navegadores" 
		class="modal fade" 
		tabindex="-1" 
		role="dialog" 
		aria-hidden="true">			
	<div class="modal-navegadores">		      
		<div class="modal-content">
			<span class="btn-primary" style="color: white; font-weight: bold; display: block; padding: 5px; width: auto; text-align: center;">
				<span id="titulo">NAVEGADORES SOPORTADOS</span>
				<button id="btnclosemodal" type="button" class="close-white" data-dismiss="modal" aria-hidden="true">&times;</button>
			</span>
			<div id="contenido" style="text-align: center; width: auto; display: block; padding: 15px;">
				<div>
					<img width="225px;" alt="" title="Navegadores soportados" src="<%=request.getContextPath()%>/img/bbva/navegadores-soportados.jpg">
				</div>
				
				<div style="font-weight: bold; text-align: left; margin-top: 10px; line-height: 2;">
				
					<div>
						<span>INTERNET EXPLORER : 8.0 en adelante</span>
					</div>
					<div>
						<span>MOZILLA FIREFOX : 5.0 en adelante</span>
					</div>
					<div>	
						<span>GOOGLE CHROME : 9.0 en adelante</span>
					</div>
					<div>
						<span>SAFARI : 4.0 en adelante</span>
					</div>
									
				</div>
							
			</div>
		</div>
	</div>		  		
</div>
<%-- FIN CONTENT NAVEGADORES --%>

<script type="text/javascript">
	
	function bloqueoCargando(){
		$.fancybox('<div>'+
						'<img src="<%=request.getContextPath()%>/img/bbva/cargando.gif">'+						
					'</div>',	
					{	height		: 60,
						padding		: 10,
						closeBtn	: false,
						closeClick	: false,
						scrolling	: 'no'
					});			
	}
	
	function callService(methodType, queryUrl, isAsync, dataToSend, callbackSuccess, callComplete, callError){
		$.ajax({
			type 		: methodType ,
			url 		: queryUrl ,
			cache 		: false ,
			dataType	: 'json',
			async 		: isAsync ,
			data		: dataToSend ,
			success 	: function (rsp){callbackSuccess;},
			complete 	: function (rsp){callComplete;},
			error		: function (rsp){callError;}
		});	
	}
 	
</script>