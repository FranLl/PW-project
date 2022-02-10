<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/styles_login.css">
		<script type="text/javascript" src="/practica3/js/formLoginRegistrationVerification.js"></script>
	</head>
	<body>
		<%
		String message = request.getParameter("message");

		if (message == null) message = "";
		
		if (customerBean != null && !customerBean.getLogin().equals("")) {
			%>
			<jsp:forward page="../controller/controladorContenido.jsp"/>
			<%
		} else {
			%>
			<p id="message"><%= message %></p>
			<fieldset>
				<legend>Login</legend>
				<form id="form" method="post" action="../controller/controladorLogin.jsp">
					<label for="email">Email</label>
					<input type="email" name="email" id="email" value="" placeholder="ejemplo@email.com" required/>
					<label for="password">Password</label>
					<input type="password" name="password" id="password" value="" placeholder="Password" required/>
					<input type="submit" value="Acceder"/>
				</form>
				<form method="post" action="/practica3">
					<input type="submit" value="Volver">
				</form>
			</fieldset>
			
			<%
		}
		%>
	</body>
</html>
