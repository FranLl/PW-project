<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<jsp:useBean id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/index.css">
	</head>
	
	<body>
		<%
		String message = (String)request.getAttribute("message");
		if (message == null)
		{
			message = (String)request.getParameter("message");
			if( message == null )
				message = "";
		}
		if (customerBean == null || customerBean.getEmail().equals("") ) {
		%>
			<p><%=message%></p>
			<fieldset>
				<legend>Práctica 3<br>Programación Web</legend>
				<form method="post" action="/practica3/mvc/controller/controladorLogin.jsp">
					<input type="submit" value="Acceder">
				</form>
				<form method="post" action="/practica3/mvc/controller/controladorRegistro.jsp">
					<input type="submit" value="Registrarse">
				</form>
			</fieldset>
			<fieldset>
				<legend>Autores</legend>
				Mario Berrios Carmona<br>
				Francisco Camargo Lucena<br>
				Francisco Llamas Nuflo
				
			</fieldset>
		<%
		} else { 
		%>
			<jsp:forward page="mvc/controller/controladorContenido.jsp"/>
		<% } %>
	</body>
</html>