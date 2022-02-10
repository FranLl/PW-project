<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.user.UsuarioDTO,pw.p3.business.common.Utils" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>

<%
if (customerBean == null || customerBean.getEmail().equals("") ) {%>
	<jsp:forward page="../../index.jsp"/>
<%
}

java.util.ArrayList<UsuarioDTO> usuarios = utilsBean.getUsuarios();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Vista Admin</title>
	<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
	<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
	<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
	<link rel="stylesheet" type="text/css" href="/practica3/css/vistaUser.css">
	<link rel="stylesheet" type="text/css" href="/practica3/css/adminView.css">
</head>
<body>
	<fieldset>
		<legend>Vista Admin</legend>
		<ul>
			<li>
				<form method="get" action="/practica3/listadoUsuarios">
					<input type="submit" value="Lista de usuarios">
				</form>
			</li>
			
			<li>
				<form method="get" action="/practica3/crearEspectaculo">
					<input type="submit" value="Nuevo espectaculo">
				</form>
			</li>
			
			<li>
				<form method="get" action="/practica3/listadoEspectaculos">
					<input type="submit" value="Espectaculos">
				</form>
			</li>
		</ul>
		<button onclick="location.href='../controller/logOutController.jsp';">Desconectar</button><br>
	</fieldset>
</body>
</html>