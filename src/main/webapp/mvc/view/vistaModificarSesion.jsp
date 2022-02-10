<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.session.SesionDTO, pw.p3.business.common.Utils" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>

<%
SesionDTO sesion = utilsBean.getSesionModificar();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modificar Sesion</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/modificarUnaSesion.css">
		<script src="/practica3/js/modificarSesion.js"></script>
		
	</head>
	<body>
	<fieldset>
			<legend>Modificar sesión</legend>
		<%
		String message = request.getParameter("message");

		if (message == null) message = "";
		
		if (customerBean == null || customerBean.getEmail().equals("")) {
			%>
			<jsp:forward page="../../index.jsp"/>
			<%
		} else {
			%>
			<%= message %>
			<div>
			<form id="formModificarSesion" method="get" action="/practica3/modificarSesion">
				<label for="fechaSesion">Fecha</label>
				<input id="fecha" type="datetime-local" name="fechaSesion" value="<%=sesion.getFecha()%>" required>
				<input type="submit" value="Modificar"/>
			</form>
			</div>
			<%
		}
		%>
		<form id="volver" method="get" action="/practica3/mvc/view/adminView.jsp">
			<input type="submit" value="Volver">
		</form>
		</fieldset>
	</body>
</html>