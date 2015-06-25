<%@ page language="java" contentType="text/html; charset=utf-8" %>
<% 	
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader( "Pragma", "no-cache" );
   	response.setHeader( "Cache-Control", "no-cache" );
   	response.setDateHeader( "Expires", 0 );
%>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="es"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="es"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="es"> <![endif]-->
<!--[if gt IE 8]><!-->

<html  lang="es">
<!--<![endif]-->
	<head>
		<%@include file="/html-commons/head.jsp"%>		
	</head>
	
	<body>	
		
		<%@include file="/html-commons/containers-modal.jsp"%>
		
		<div class="container-body">
		
			<div id="idletimeout" style="display: none; text-align: center; background-color: #e6281e; color: white;">
				Tu sesión expirará en <span><!-- countdown place holder --></span>&nbsp;segundos por inactividad en la web. 
				<a id="idletimeout-resume" href="#" style="color: white;text-decoration: underline;">Haz clic aqui si deseas continuar usando la web</a>.
			</div>
			
			<%@include file="/html-commons/headpage-default.jsp" %>
			
			<div id="container" class="container-fluid">	
			
				<%@include file="/html-commons/menu-principal.jsp"%>
				
				<div class="row">
					<div class="col-xs-3"></div>
					<div class="col-xs-12 col-md-9 espaciado-main">
						
						<div id="content-breadcrumb" display: none;">
							<ul id="breadcrumb_list" class="breadcrumb">
							</ul>
						</div>
												
						<div class="content-rws"></div>
					</div>
				</div>
				
			</div>
	        
			<%@include file="/html-commons/footerpage-default.jsp"%>

		</div>
		
		<script>
	
			$().ready(function(){ 
				
				$("#header").hide();

				loadModalCargando();
				
				obtDatosUsuarioSesion();

				loadSesionInicial();
							
			});
			
		</script>
		
   		<script type="text/javascript">
   			/*
			$.idleTimeout('#idletimeout', '.container-body', {
				idleAfter				: 1200,
				pollingInterval			: 300,
				keepAliveURL			: '/DeliveryTarjetas/usuario.do?method=obtDatosUsuarioSesion',
				serverResponseEquals	: 'OK',
				onTimeout				: function(){
											$(this).slideUp();
											window.location = "/DeliveryTarjetas";
				},
				onIdle					: function(){
											$(this).slideDown(); // show the warning bar
				},
				onCountdown				: function( counter ){
											$(this).find("span").html( counter ); // update the counter
				},
				onResume				: function(){
											$(this).slideUp(); // hide the warning bar
				}
			});
			*/
		</script>
				
	</body>	
</html>