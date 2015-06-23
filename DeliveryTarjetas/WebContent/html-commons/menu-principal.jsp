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
					data-url="/DeliveryTarjetas/usuario.do?method=goMiCuenta" 
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#micuenta">
					<i class="ui-icn-mi-cuenta"></i>Mi cuenta
				</a>				
			</li>
			
			<li class="panel">
				<a href="#" 
				    id="panelSalud"
				    data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#usuario-menu"  >
					<i class="ui-icn-usuarios"></i>Mantenimiento de Usuarios<i class="arrow"></i>
				</a>
				<div id="usuario-menu" class="collapse">
					<ul>

						<li>
							<a href="#" id="view-reg-usuarioweb" data-url="/DeliveryTarjetas/usuario.do?method=goRegUsuarioWeb">Registro de usuario web</a>
						</li>
						<li>
							<a href="#" id="view-mnt-usuarioweb" data-url="/DeliveryTarjetas/usuario.do?method=goMntUsuarioWeb">Edición de usuarios web</a>
						</li>
						<li>
							<a href="#" id="view-reg-usuariows" data-url="/DeliveryTarjetas/usuario.do?method=goRegUsuarioWS">Registro de <br>usuario servicio web</a>
						</li>
						<li>
							<a href="#" id="view-mnt-usuariows" data-url="/DeliveryTarjetas/usuario.do?method=goMntUsuarioWS">Edición de <br>usuarios servicio web</a>
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
					data-target="#courier-menu">
					<i class="ui-icn_couriers"></i>Mantenimiento de Couriers<i class="arrow"></i>
				</a>
				<div id="courier-menu" class="collapse">   
					<ul>
						<li>
							<a href="#" id="view-reg-courier" data-url="/DeliveryTarjetas/courier.do?method=goRegCourier">Registro de courier</a>
						</li>
						<li>
							<a href="#" id="view-mnt-courier" data-url="/DeliveryTarjetas/courier.do?method=goMntCourier">Edición de couriers</a>
						</li>
						<li>
							<a href="#" id="view-reg-colaborador" data-url="/DeliveryTarjetas/courier.do?method=goRegColaborador">Registro de <br>Colaboradores por courier</a>
						</li>						
						<li>
							<a href="#" id="view-mtn-colaborador" data-url="/DeliveryTarjetas/courier.do?method=goMntColaborador">Edición de <br>Colaboradores por courier</a>
						</li>
					</ul>
				</div>
			</li>
			
			<li class="panel">
			    <a href="#" 
			    	id="panelPerfil"
			    	data-toggle="collapse" 
			    	data-parent="#accordion" 
			    	data-target="#delivery-menu">
			        <i class="ui-icn_tarjetas"></i>Delivery de Tarjetas <i class="arrow"></i>
			    </a>
			    <div id="delivery-menu" class="collapse">
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
			
			<%--			
			<li class="panel">
				<a href="#" 
					id="panelReclamos"
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#maestros-menu">
					<i class="ui-icn_maestros"></i>Maestros<i class="arrow"></i>
				</a>				
				<div id="maestros-menu" class="collapse">
					<ul>												
						<li>														 
							<a href="#" id="view-reg-agencia" data-url="/DeliveryTarjetas/maestro.do?method=goRegAgencia">Agencias</a>
						</li>	
						<li>														 
							<a href="#" id="view-reg-producto" data-url="/DeliveryTarjetas/maestro.do?method=goRegProducto">Productos</a>
						</li>	
						<li>														 
							<a href="#" id="view-reg-subproducto" data-url="/DeliveryTarjetas/maestro.do?method=goSubproducto">Sub productos</a>
						</li>
					</ul>
				</div>
			</li>
			--%>
		</ul>
	</div>
	
	<script>
		bindBreadcrumb();
	</script>
</div>