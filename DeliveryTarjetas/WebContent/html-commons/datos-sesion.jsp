<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="datos-cliente" class="header-section2">
	<form method="post" id="formlogout" name="formlogout" action="/DeliveryTarjetas/login.do?method=logout" >
		<div id="header-section2">
			<i class="m-icn-locked"></i>
			
			<span id="avatar">
				<span id="nameTitularHeader"></span>
			</span>
			<input id="logout" 		name="logout" 		type="button" 	value="" onclick="javascript:cerrarSession();"/>
			<input id="mobile" 		name="mobile" 		type="hidden" 	value="0"/>
		</div>
	</form>
		
	
	
	<form method="post" id="form-datos-cliente" name="form-datos-cliente">				
		
		<div id="avatar">
			<span id="nombre-cliente"></span>
		</div>

		<input type="hidden" 	id="idptiptercero" 		name="idptiptercero"		value=""/>
		<input type="hidden" 	id="validacionlogin" 	name="validacionlogin"		value=""/>
		<input type="hidden" 	id="idetercero" 		name="idetercero"			value=""/>
		<input type="hidden" 	id="codexterno" 		name="codexterno"			value=""/>
		<input type="hidden" 	id="numerodoc" 			name="numerodoc"			value=""/>
		<input type="hidden" 	id="indmobile" 			name="indmobile"			value=""/>
			
	</form>	
	
	<form id="form-ind-seguros" name="form-ind-seguros">
		<input type="hidden" 	id="ind-seg-salud" 			name="ind-seg-salud"		value="0"/>
		<input type="hidden" 	id="ind-seg-vehicular" 		name="ind-seg-vehicular"	value="0"/>
		<input type="hidden" 	id="ind-seg-vida" 			name="ind-seg-vida"			value="0"/>
		<input type="hidden" 	id="ind-seg-domi" 			name="ind-seg-domi"			value="0"/>
		<input type="hidden" 	id="ind-seg-otros" 			name="ind-seg-otros"		value="0"/>
	</form>
</div>
