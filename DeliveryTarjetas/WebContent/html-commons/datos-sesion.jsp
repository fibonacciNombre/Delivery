<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="datos-cliente" class="header-section2">
	<form method="post" id="formlogout" name="formlogout" action="/DeliveryTarjetas/login.do?method=logout" >
		<div id="header-section2">
			<i class="m-icn-locked"></i>			
			<span id="avatar">
				<span id="nombreUsuarioHeader"></span>
			</span>
			<input id="logout" 		name="logout" 		type="button" 	value="" onclick="javascript:cerrarSession();"/>
		</div>
	</form>
	
	<form method="post" id="form-datos-usuario" name="form-datos-usuario">				
		
		<div id="avatar">
			<span id="nombre-usuario"></span>
		</div>

		<input type="hidden" 	id="idusuario" 			name="idusuario"			value=""/>
		<input type="hidden" 	id="idperfil" 			name="idperfil"				value=""/>
		<input type="hidden" 	id="idtercero" 			name="idtercero"			value=""/>
		<input type="hidden" 	id="idcourier" 			name="idcourier"			value=""/>
		<input type="hidden" 	id="numerodoc" 			name="numerodoc"			value=""/>
		<input type="hidden" 	id="idptipodocumento" 	name="idptipodocumento"			value=""/>
			
	</form>	
	
</div>
