<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/html-commons/head.jsp"%>
	</head>

	<body class="">
	
		<%@include file="/html-commons/login-principal.jsp"%>
	
		<script>
	
			var contextPath = '<%=request.getContextPath()%>';
	
			$().ready(function() {
				
				validateAlfanumerico();
	
				validateSinEspacios();
	
				loadValidateLogin("#formlogin");
	
			});		
		</script>
	</body>
</html>