<%@ page language="java" contentType="text/html; charset=utf-8" %>

<div id="header">
	<div class="color-bar">
        <span class="first">&nbsp;</span> 
        <span class="second">&nbsp;</span> 
        <span class="third">&nbsp;</span> 
        <span class="fourth">&nbsp;</span> 
        <span class="fifth">&nbsp;</span> 
        <span class="sixth">&nbsp;</span>
    </div>
    				
	<div id="logo"  onclick="javascript:irInicio();" style="cursor: pointer;">
		<img class="img-logo-principal" src="<%=request.getContextPath() %>/img/bbva/logobbva-full.png">
		<img class="img-logo-mobile" src="<%=request.getContextPath() %>/img/bbva/logobbva-mobile.jpg">
	</div>
	
	<div id="header-section1">
		<h1>Delivery de Tarjetas</h1> 

		<a href="#" id="header-menu">
			<i class="m-icn-mainmenu"></i>
		</a>
	</div>
	
	<%@include file="/html-commons/datos-sesion.jsp"%>
</div>