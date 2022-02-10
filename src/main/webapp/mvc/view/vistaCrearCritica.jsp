<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.show.EspectaculoDTO,pw.p3.business.common.Utils" %>
<%@ page import = "java.util.Vector" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>

<%
if (customerBean == null || customerBean.getEmail().equals("") )
{
%>
	<jsp:forward page="/">
		<jsp:param value="Acceda o reg&iacute;strese" name="message"/>
	</jsp:forward>
<%
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vista Crear Crítica</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/crearReview.css">
		<script type="text/javascript" src="/practica3/js/range.js"></script>
		<script type="text/javascript" src="/practica3/js/confirmationCrearCritica.js"></script>
	</head>
	<body>
		<%
			EspectaculoDTO espectaculo = (EspectaculoDTO)request.getAttribute("espectaculo");//utilsEspectadorBean.getEspectaculos();
			
			String message = (String)request.getAttribute("message");
			if (message == null) message = "";
		%>
		<%=message%>
		<fieldset>
			<legend>Crear crítica</legend>
			<form id="formCrearCritica" action="/practica3/publicarcritica" method="POST">
				<p>
					<label for="titulo">Título</label>
					<input type="text" id="titulo" name="titulo" required>
				</p>
				
				<div>
					<label for="descripcion">Descripción</label>
					<textarea id="descripcion" name="descripcion" rows="5" required></textarea>
				</div>
				
				<p>
					<label for="puntuacion">Puntuación</label>
					<input type="range" min="0" max="10" value="5" class="slider" id="puntuacion" name="puntuacion" oninput="updateValue()">
					<span id="value">-</span>
				</p>
				
				<p>
					<label for="titulo">Espectáculo a criticar</label>
					<input type="text" id="espectaculo" name="espectaculo" value="<%= espectaculo.getTitulo() %>" readonly>
				</p>
				<p>
					<input type="hidden" id="idEspectaculo" name="idEspectaculo" value="<%= espectaculo.getID() %>">
				</p>
				<input type="submit" value="Criticar">
			</form>
			<form id="volver" method="post" action="/practica3/index.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>
