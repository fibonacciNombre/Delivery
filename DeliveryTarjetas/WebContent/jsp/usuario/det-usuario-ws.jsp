<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarUsuarioWS"
		class="modal fade"
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>				 
				<h3 class="modal-title">Editar usuario de servicios web</h3>
			</div>
			 
			<form id="form-mntusuario-ws">
			
				<input type="hidden" id="idusuario" name="idusuario" value=""/>
				
				<%@include file="/jsp/usuario/form-usuario-ws.jsp" %>
				
			</form>
		</div>

	</div>
</div>