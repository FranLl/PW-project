<%@page import="pw.p3.data.dao.common.TipoEspectaculo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.show.EspectaculoDTO, pw.p3.business.session.SesionDTO, pw.p3.business.common.Utils" %>
<%@ page import = "java.util.ArrayList" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<%
if (customerBean == null || customerBean.getEmail().equals("") )
{
%>
	<jsp:forward page="/">
		<jsp:param value="Acceda o reg&iacute;strese" name="message"/>
	</jsp:forward>
<%
}

EspectaculoDTO espectaculo = (EspectaculoDTO)request.getAttribute("espectaculo");
ArrayList<SesionDTO> sesiones = (ArrayList<SesionDTO>)request.getAttribute("sesiones");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Lista de Sesiones</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/listarSesiones.css">
</head>
<body>
	<fieldset>
	<legend><%= espectaculo.getTitulo() %></legend>
		<ul>
			<%for(SesionDTO sesion: sesiones){
				%>
				<li>
					<div><%=sesion.getFecha()%> <%= espectaculo.getLocalidades() - sesion.getLocVendidas() %></div>
				</li>
				<%
			}%>
		</ul>
		<form method="post" action="/practica3/index.jsp">
			<input type="submit" value="Volver">
		</form>
	</fieldset>
</body>
</html>