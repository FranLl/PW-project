<%@page import="pw.p3.data.dao.common.TipoEspectaculo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.session.SesionDTO, pw.p3.business.common.Utils" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>

<%
java.util.ArrayList<SesionDTO> sesiones = utilsBean.getSesiones();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Sesiones</title>
<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/modificarSesion.css">
</head>
<body>
	<fieldset>
	<legend>Sesiones</legend>
	<ul>
		<%for(SesionDTO sesion: sesiones){
			%>
			<li>
			<div><%=sesion.getFecha()%></div>
				<form method="get" action="/practica3/modificarSesion">
					<input type="hidden" name="idSesion" value="<%=sesion.getID()%>">
					<input type="submit" value="Modificar">
				</form>
			</li>
			<%
		}%>
	</ul>

	<form method="get" action="/practica3/mvc/view/adminView.jsp">
		<input type="submit" value="Volver">
	</form>
	</fieldset>
</body>
</html>