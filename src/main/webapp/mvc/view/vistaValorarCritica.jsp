<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.review.CriticaDTO,pw.p3.business.common.Utils" %>
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
		<title>Vista Valorar Crítica</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/rateCritica.css">
		<script type="text/javascript" src="/practica3/js/range.js"></script>
		<script type="text/javascript" src="/practica3/js/confirmationValorarCritica.js"></script>
	</head>
	<body>
		<%
			CriticaDTO critica = (CriticaDTO)request.getAttribute("critica");
			
			String message = (String)request.getAttribute("message");
			if (message == null) message = "";
		%>
		<%=message%>
		<fieldset>
			<legend>Valorar critica</legend>
		<form id="formValorarCritica" action="/practica3/valorarcritica" method="POST">
			<p>
				<label for="puntuacion">Puntuación</label>
				<input type="range" min="0" max="10" value="5" class="slider" id="puntuacion" name="puntuacion" oninput="updateValue()">
				<span id="value">-</span>
			</p>
			<input type="hidden" id="idCritica" name="idCritica" value="<%= critica.getID() %>">
			<input type="submit" value="Valorar">
		</form>
		<form id="volver" method="post" action="/practica3/index.jsp">
			<input type="submit" value="Volver">
		</form>
		</fieldset>
	</body>
</html>
