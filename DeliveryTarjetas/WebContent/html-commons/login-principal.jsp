<%@ page language="java" contentType="text/html; charset=utf-8"%>
    
<div id="todo" class="container-body">
	
	<div class="logo-login"></div>
		
	<div id="login" class="container-fluid login-text">
	
		<div class="row">
			
			<div class="marco-login">
						
				<div class="row">
				
					<div class="panel panel-default panel-container">
											
						<div class="panel-body">
													
							<div class="title">
								BBVA Continental - Delivery Tarjetas
							</div>
							
							<div class="row">
							
								<div class="col-sm-offset-0 col-sm-12 col-md-offset-1 col-md-10">
								
									<div class="panel panel-default panel-login">
																			
										<div class="panel-body panel-body-min">
										
											<div class="col-sm-offset-0 col-sm-12 col-md-10 col-md-offset-1 panel-body-min">		
												
												<form method="post" 
														id="formlogin" 
														name="formlogin" 
														class="col-md-12 i-group" 
														autocomplete="off"
														style="margin: 0px;">
																										
													<div class="input-group-custom">			
															<div class="col-md-12 i-group" style="padding: 0px;">
																<input id="userlogin"
																		name="userlogin"
																		type="text"
																		class="col-md-12 i-group form-control"
																		maxlength="15"
																		style="margin-bottom: 5px;"
																		placeholder="Nro. de documento">																
													  			<div class="result"></div>
													  		</div>													    	
													    	<div class="col-md-12 i-group" style="padding: 0px;">
													    		<input id="passlogin"
																		name="passlogin"
																	 	type="password"
																		class="col-md-12 i-group form-control"
																		maxlength="12"
																		placeholder="Contraseña"
																		style="margin-bottom: 5px;"
																		onkeypress="javascript:enterLOGIN(event);">
													      		<div class="result"></div>
													    	</div>													    	
													    	<input id="mobile" name="mobile" type="hidden" value="0"/>
													</div>
													
													<div class="col-md-13">
														<input class="btn btn-primary pull-right btn-block" type="button" value="INGRESAR" onclick="javascript:loginForm();">
													</div>
																										
													<div id="mensajes-login" class="row" style="display: none;">
														<div class="col-md-12">
													   		<div class="alert alert-danger">
													   			<button aria-hidden="true" 
													   					data-dismiss="alert" 
													   					class="close"
													   					type="button">×</button>
													   			<span id="msj-result-login"></span>
															</div>
														</div>			   								   	
												   	</div>
												   		                                            														
												</form>
											
											</div>
										
										</div>
									
									</div>
								</div>
															
							</div>
																					
						</div>	
											
					</div>
									
				</div>
								
			</div>		
					
		</div>	
			
	</div>
	
	<div id="footer" class="color-bg-login-transaparent">
		<div class="footer-signature">
			<%@include file="/html-commons/footerpage-default.jsp"%>
		</div>
	</div>
	
	<div id="inicioLog"></div>
	
	<%-- INI MODAL LOADING --%>
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
		<div class="modal-cargando">
			<div class="modal-content">	
				<div class="gif_animation">					
					<img src="<%=request.getContextPath()%>/img/bbva/cargando.gif">
				</div>
			</div>
		</div>		  	
	</div>
	<%-- FIN MODAL LOADING --%>
	
	<%-- INI MODAL MENSAJES--%>
	<a id="link-content-mensaje"
		data-toggle="modal" 
		data-target="#content-mensaje"
		style="display: none;">
	</a>
	
	<div id="content-mensaje" 
			class="modal fade"
			role="dialog" 
			aria-hidden="true">			
		<div class="modal-mensaje">
			<div class="modal-content">	
				<span style="color: white; font-weight: bold; display: block; padding: 5px; width: auto; text-align: center; background-color: rgb(231, 40, 24);">
					<span id="titulo-mensaje"></span>
					<button id="btnclosemodal" type="button" class="close-white" data-dismiss="modal" aria-hidden="true">&times;</button>
				</span>									
				<span id="contenido-mensaje" style="text-align: center; width: auto; display: block; padding: 15px;"></span>
			</div>
		</div>		  	
	</div>
	<%-- FIN MODAL MENSAJES--%>			
</div>