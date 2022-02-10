<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import = "pw.p3.business.common.Utils" %>

<%
if (customerBean == null || customerBean.getEmail().equals("") )
{
	request.setAttribute("message", "Acceda o regístrese");
%>
	<jsp:forward page="/"/>
<%
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vista espectador</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/vistaUser.css">
	</head>
	<body>
		<%
			String message = (String)request.getAttribute("message");
			if (message == null) message = "";
		%>
		<%=message%>
		<fieldset>
			<legend>Vista usuario</legend>

			<p>¡Bienvenido <%=customerBean.getNick()%>!<br>
				Son las <%= Utils.dateToString( new java.util.Date() ) %><br>
				Se registro: <%=Utils.millisToString(Long.parseLong(customerBean.getRegistro()))%>
			</p>
			<ul>
					<li>
						<form method="post" action="/practica3/buscarespectaculo">
							<input type="submit" name="option" class="option" value="Buscar espectáculo">
						</form>
					</li>
					
					<li>
						<form method="post" action="/practica3/publicarcritica">
							<input type="submit" name="option" class="option" value="Publicar crítica">
						</form>
					</li>
					
					<li>
						<form method="post" action="/practica3/consultarcriticas">
							<input type="submit" class="option" value="Consultar críticas">
						</form>
					</li>
					
					<li>
						<form method="post" action="/practica3/consultarlocalidades">
							<input type="submit" class="option" name="option" value="Consultar localidades">
						</form>
					</li>
					
					<li>
						<form method="post" action="/practica3/buscarentradas">
							<input type="submit" class="option" name="option" value="Próximos espectáculos">
						</form>
					</li>
				</ul>
			<form method="post" action="/practica3/mvc/controller/controladorModificar.jsp">
				<input type="submit" value="Modificar datos">
			</form>
			<form method="post" action="/practica3/mvc/controller/logOutController.jsp">
				<input type="submit" value="Desconectar">
			</form>
		</fieldset>
	</body>
</html>