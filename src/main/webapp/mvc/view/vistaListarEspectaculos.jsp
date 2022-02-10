<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.show.EspectaculoDTO,pw.p3.business.common.Utils, pw.p3.data.dao.common.Categoria" %>
<%@ page import = "java.util.ArrayList" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>
<%--<jsp:useBean id="utilsEspectadorBean" scope="session" class="pw.p3.display.javabean.UtilsEspectadorBean"></jsp:useBean> --%>

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
		<title>Vista Listar Espectaculos</title>
		<link rel="stylesheet" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" href="/practica3/css/box_style.css">
		<link rel="stylesheet" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" href="/practica3/css/shows_found.css">
	</head>
	<body>
		<%  ArrayList<EspectaculoDTO> espectaculos = (ArrayList<EspectaculoDTO>)request.getAttribute("espectaculos"); %>
		<fieldset>
			<legend>Espectáculos encontrados</legend>
				<p>Conciertos</p>
				<dl>
					<% for (EspectaculoDTO e: espectaculos) { 
						if( e.getCategoria() == Categoria.valueOf("concierto") )
						{%>
							<dt><%= e.getTitulo() %></dt>
					    	<dd><div><%= e.getDescripcion() %></div></dd>
					    <%}			
					}%>
				</dl>
				<p>Obras</p>
				<dl>
					<% for (EspectaculoDTO e: espectaculos) { 
						if( e.getCategoria() == Categoria.valueOf("obra") )
						{%>
							<dt><%= e.getTitulo() %></dt>
					    	<dd><div><%= e.getDescripcion() %></div></dd>

					    <%}			
					} %>
				</dl>
				<p>Monólogos</p>
				<dl>
					<% for (EspectaculoDTO e: espectaculos) { 
						if( e.getCategoria() == Categoria.valueOf("monologo") )
						{%>

							<dt><%= e.getTitulo() %></dt>
					    	<dd><div><%= e.getDescripcion() %></div></dd>

					    <%}			
					} %>
				</dl>
			<form method="post" action="/practica3/index.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>