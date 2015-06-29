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
					id="panelMiCuenta"
					data-url="/DeliveryTarjetas/usuario.do?method=goMiCuenta" 
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#micuenta">
					<i class="ui-icn-mi-cuenta"></i>Mi cuenta
				</a>				
			</li>
			
			<li class="panel">
				<a href="#" 
				    id="panelGestionUsuarios"
				    class="view-admin"
				    data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#usuario-menu"  >
					<i class="ui-icn-usuarios"></i>Gestión de Usuarios<i class="arrow"></i>
				</a>
				<div id="usuario-menu" class="collapse view-admin">
					<ul>

						<li>
							<a href="#" id="view-reg-usuarioweb" data-url="/DeliveryTarjetas/usuario.do?method=goRegUsuarioWeb">Registro de usuario</a>
						</li>
						<li>
							<a href="#" id="view-mnt-usuarioweb" data-url="/DeliveryTarjetas/usuario.do?method=goMntUsuarioWeb">Edición de usuarios</a>
						</li>
						<li>
							<a href="#" id="view-reg-usuariows" data-url="/DeliveryTarjetas/usuario.do?method=goRegUsuarioWS">Servicios web <br> Registro de usuario</a>
						</li>
						<li>
							<a href="#" id="view-mnt-usuariows" data-url="/DeliveryTarjetas/usuario.do?method=goMntUsuarioWS">Servicios web <br> Edición de usuarios</a>
						</li>
						
					</ul>
				</div>
			</li>
			
			<li class="panel">
				<a href="#" 
					id="panelGestionCouriers"
					data-toggle="collapse" 
					data-parent="#accordion" 
					data-target="#courier-menu">
					<i class="ui-icn_couriers"></i>Gestión de Couriers<i class="arrow"></i>
				</a>
				<div id="courier-menu" class="collapse">   
					<ul>
						<li>
							<a href="#" class="view-admin" id="view-reg-courier" data-url="/DeliveryTarjetas/courier.do?method=goRegCourier">Registro de courier</a>
						</li>
						<li>
							<a href="#" class="view-admin" id="view-mnt-courier" data-url="/DeliveryTarjetas/courier.do?method=goMntCourier">Edición de couriers</a>
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
			    	id="panelDelivery"
			    	data-toggle="collapse" 
			    	data-parent="#accordion" 
			    	data-target="#delivery-menu">
			        <i class="ui-icn_tarjetas"></i>Delivery de Tarjetas <i class="arrow"></i>
			    </a>
			    <div id="delivery-menu" class="collapse">
			        <ul>
			            <li>
			                <a href="#" id="view-carga-entrega" data-url="/DeliveryTarjetas/delivery.do?method=goCargaDelivery">Carga entregas a realizar</a>

			            </li>
			            <li>
			                <a href="#" id="view-mnt-entrega" data-url="/DeliveryTarjetas/delivery.do?method=goMntCargaDelivery">Edición de datos de entrega</a>
			            </li>
			            <li>
			                <a href="#" id="view-lst-entrega" data-url="/DeliveryTarjetas/delivery.do?method=golstDelivery">Consultar entregas a realizar</a>
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
