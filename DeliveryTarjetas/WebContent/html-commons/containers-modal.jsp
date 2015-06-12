<%-- INI MODAL REGISTRAR RECLAMO --%>
<div class="modal fade" 
		id="modalRegistrarReclamo" 
		tabindex="-1" 
		role="dialog" 
		aria-labelledby="modallabel" 
		aria-hidden="true">
	<div class="modal-dialog modal-md">
    	<div class="modal-content">
    		<div class="modal-body"></div>
    	</div>
	</div>
</div>
<%-- FIN MODAL REGISTRAR RECLAMO --%>

<%-- INI MODAL REGISTRAR DIRECCION --%>
<a id="link-registrardireccion"
	data-toggle="modal"
	href="<%=request.getContextPath()%>/jsp/perfil/registro-direccion.jsp"
	data-target="#modalRegDireccion"
	class="btn btn-link"
	style="display: none;">
</a>

<div class="modal fade"
		id="modalRegDireccion"
		tabindex="-1" 
   		data-backdrop="static" 
		data-keyboard="false"
		role="dialog"
		aria-labelledby="Direccion"
		aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body"></div>
		</div>
	</div>
</div>
<%-- FIN MODAL REGISTRAR DIRECCION --%>
		
<%-- INI MODAL PRIMER LOGIN --%>
<a id="link-primerlogueo"
	data-toggle="modal" 
	href="<%=request.getContextPath()%>/jsp/perfil/configPrimerLogin.jsp" 
	data-target="#modalPrimerLogueo"
	class="btn btn-link"
	style="display: none;">
</a>
    
<div class="modal fade" 
		id="modalPrimerLogueo" 
		tabindex="-1" 
		data-backdrop="static" 
		data-keyboard="false"
		role="dialog" 
		aria-labelledby="Direccion" 
		aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body"></div>
		</div>
	</div>		  	
</div>
<%-- FIN MODAL PRIMER LOGIN --%>

<%-- INI CONTENT CARGANDO --%>
<a id="link-content-cargando"
	data-toggle="modal" 
	data-target="#content-cargando"
	style="display: none;">
</a>

<div id="content-cargando" 
		class="modal fade" 
		tabindex="-1" 
		role="dialog" 
		aria-hidden="true"			
		data-backdrop="static" 
  		data-keyboard="false">
	<div class="modal-cargando visible-md-block visible-sm-block visible-lg-block">
		<div class="modal-content">	
			<div class="gif_animation" style="text-align: center;">					
				<img src="<%=request.getContextPath()%>/img/bbva/loading.gif">
			</div>
		</div>
	</div>		  	
			
	<div class="modal-cargando visible-xs-block" style="margin-top: 180px; width: 240px;">
		<div class="modal-content">	
			<div class="gif_animation" style="text-align: center;">					
				<img src="<%=request.getContextPath()%>/img/bbva/loading.gif">
			</div>
		</div>
	</div>
</div>
<%-- FIN CONTENT CARGANDO --%>

<%-- INI CONTENT MENSAJES --%>
<a id="link-content-mensaje"
	data-toggle="modal" 
	data-target="#content-mensaje"
	style="display: none;">
</a>

<div id="content-mensaje" 
		class="modal fade" 
		tabindex="-1" 
		role="dialog" 
		aria-hidden="true">			
	<div class="modal-mensaje">		      
		<div class="modal-content">
			<span  style="color: white; font-weight: bold; display: block; padding: 5px; width: auto; text-align: center; background-color: rgb(231, 40, 24);">
				<span id="titulo-mensaje"></span>
				<button id="btnclosemodal" type="button" class="close-white" data-dismiss="modal" aria-hidden="true">&times;</button>
			</span>
			<span id="contenido-mensaje" style="text-align: center; width: auto; display: block; padding: 15px;"></span>
		</div>
	</div>		  	
	
</div>
<%-- FIN CONTENT MENSAJES --%>