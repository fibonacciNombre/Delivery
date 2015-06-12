<%@page import="bbva.delivery.tarjetas.commons.Constants"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>

<%
//	if(session.getAttribute(Constants.REQ_ATTR_NRODOCUMENTO)== null)
//		response.sendRedirect(request.getContextPath());	
%>

<div id="aside">
	<div class="row">
		<ul class="menu panel-group" id="accordion">
			
			<li class="panel" >
				<a 	href="#" 
					id="panelSeguros"
					data-url="/PORTALWEB/misseguros.do?method=goMisSeguros" 
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#seguros">
					<i class="ui-icn-mi-cuenta"></i>Mi cuenta
				</a>				
			</li>
			
			<li class="panel">
				<a href="#" 
				    id="panelSalud"
				    <%--data-url="pages/servicios-salud/clinicas"--%> 
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#salud"  >
					<i class="ui-icn-usuarios"></i>Mantenimiento de Usuarios<i class="arrow"></i>
				</a>
				<div id="salud" class="collapse">
					<ul>

						<li>
							<a href="#" id="lstClinicas" data-url="/PORTALWEB/clinicas.do?method=goMisClinicas">Registrar usuario</a>
						</li>
						<li>
							<a href="#" data-url="/PORTALWEB/cartasGarantia.do?method=goCartasGarantia">Edición de usuario</a>
						</li>
						
					</ul>
				</div>
			</li>
			
			<li class="panel">
				<a href="#" 
					id="panelVehicular"
					<%--data-url="pages/servicios-vehiculos/emergencias"--%> 
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#segurovehicular">
					<i class="ui-icn_couriers"></i>Mantenimiento de Couriers<i class="arrow"></i>
				</a>
				<div id="segurovehicular" class="collapse">   
					<ul>

						<li>
							<a href="#" id="lstTalleres" data-url="/PORTALWEB/talleres.do?method=goMisTalleres">Registro de courier</a>
						</li>
						<li>
							<a href="#" id="obtConductor" data-url="/PORTALWEB/asistenciaveh.do?method=goConductor">Edición de courier</a>
						</li>
						<li>
							<a href="#" id="obtSinistro" data-url="/PORTALWEB/asistenciaveh.do?method=goSiniestro">Colaboradores por courier</a>
						</li>						
						<li>
							<a href="#" id="obtAuxilio" data-url="/PORTALWEB/asistenciaveh.do?method=goAuxilio">Carga de archivo<br>colaboradores por courier</a>
						</li>						
					</ul>
				</div>
			</li>
			
			<li class="panel">
			    <a href="#" 
			    	id="panelPerfil"
			    	<%--data-url="pages/mi-perfil/mi-perfil"--%> 
			    	data-toggle="collapse" 
			    	data-parent="#accordion" 
			    	data-target="#miperfil">
			        <i class="ui-icn_tarjetas"></i>Delivery de Tarjetas <i class="arrow"></i>
			    </a>
			    <div id="miperfil" class="collapse">
			        <ul>
			            <li>
			                <a href="#" data-url="/PORTALWEB/perfil.do?method=goActualizaDatos">Editar datos <br>de una entrega</a>
			            </li>
			            <li>
			                <a href="#" data-url="/PORTALWEB/perfil.do?method=goCambioContrasenia">Carga de archivo<br>entregas a realizar</a>
			            </li>			            
			        </ul>
			    </div>
			</li>
			
			<li class="panel">
				<a href="#" 
					id="panelReclamos"
					<%--data-url="/PORTALWEB/reclamos.do?method=goMisReclamos"--%>  
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#reclamos">
					<i class="ui-icn_maestros"></i>Maestros<i class="arrow"></i>
				</a>				
				<div id="reclamos" class="collapse">
					<ul>												
						<li>														 
							<a href="#" id="obtreclamos" data-url="../jsp/reclamos/reclamos-registrar.jsp">Agencias</a>
						</li>	
						<li>														 
							<a href="#" data-url="../jsp/reclamos/reclamos-mis-reclamos.jsp">Productos</a>
						</li>	
						<li>														 
							<a href="#" data-url="../jsp/reclamos/reclamos-mis-reclamos.jsp">Sub productos</a>
						</li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	
	<script>
		bindBreadcrumb();
	</script>
</div>