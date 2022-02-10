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
		<title>Vista Buscar Entradas</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/buscarEntradas.css">
	</head>
	<body>
		<%
			String message = (String)request.getAttribute("message");
			if (message != null){
				%>Mensaje: <%= message %><%;
		}%>
		<fieldset>
			<legend>Buscar entradas</legend>
			
			<form action="/practica3/buscarentradas" method="POST">
				
				<label for="categoria">Categoría (opcional)</label>
				<select name="categoria">
					<option value="todas" selected>todas</option>
   					<option value="concierto">concierto</option>
   					<option value="obra">obra</option>
   					<option value="monologo">monologo</option>
				</select>
				
				<input type="submit" value="Buscar">
			</form>
			
			<form id="volver" method="post" action="/practica3/index.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>