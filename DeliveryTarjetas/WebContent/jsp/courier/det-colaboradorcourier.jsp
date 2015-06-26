<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="modalEditarColaborador" 
		class="modal fade" 
		tabindex="-1"
		role="dialog" 
		aria-labelledby="myModalLabel" 
		aria-hidden="true">
		
	<div class="modal-dialog">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3 class="modal-title">Courier - Editar datos del colaborador</h3>
			</div>

			<form id="form-mntcolaborador">

				<input type="hidden" id="idtercero" name="idtercero" value="" />
									
				<%@include file="/jsp/courier/form-colaboradorcourier.jsp" %>
				 
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-md-6 control-label" style="padding: 0px;">
								</label>
								<div class="col-sm-6 pull-right"
										style="text-align: right; padding: 0px;">										
									<button id="btnRegistrar" type="button" class="btn btn-primary" onclick="javascript:actualizarColaborador();">Guardar</button>
								</div>
							</div>
						</div>
					</div>
				</div>

			</form>
		</div>
		
	</div>
</div>