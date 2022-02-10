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
		<title>Vista Buscar Espectaculos</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/buscarShow.css">
		<script type="text/javascript" src="/practica3/js/formBuscarEspectaculos.js"></script>
	</head>
	<body>
		<%
			String message = (String)request.getAttribute("message");
		if (message == null)
			message = "";
		%>
		<p id="message"><%= message %></p>
		<fieldset>
			<legend>Buscar espectáculos</legend>
			<form id="formBuscarEspectaculos" action="/practica3/buscarespectaculo" method="POST">
				<p>
					<input type="radio" id="titulo" name="buscar" value="titulo" checked>
					<label for="titulo">Título</label>
					<input type="text" id="clave" name="clave">
				</p>
				<p>
					<input type="radio" id="categoria" name="buscar" value="categoria">
					<label for="categoria">Categoría</label>
					<select name="categoria">
	   					<option value="concierto">concierto</option>
	   					<option value="obra">obra</option>
	   					<option value="monologo">monologo</option>
					</select>
				</p>
				<input type="submit" value="Buscar">
			</form>
			<form id="volver" method="post" action="/practica3/index.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>
