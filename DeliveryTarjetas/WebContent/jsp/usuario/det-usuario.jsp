<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarUsuario"
		class="modal fade"
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>				 
				<h3 class="modal-title">Editar usuario</h3>
			</div>
			 
			<form id="form-mntusuario">
			
				<input type="hidden" id="idusuario" name="idusuario" value=""/>
				
				<input type="hidden" id="idtercero" name="idtercero" value=""/>
				
				<input type="hidden" id="idperfil" name="idperfil" value=""/>
				
				<input type="hidden" id="idcourier" name="idcourier" value=""/>
				
				<%@include file="/jsp/usuario/form-usuario.jsp" %>
				
			</form>
		</div>

	</div>
</div>