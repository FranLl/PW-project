<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.review.CriticaDTO,pw.p3.business.common.Utils" %>
<%@ page import = "java.util.ArrayList" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>
<jsp:useBean id="utilsEspectadorBean" scope="request" class="pw.p3.display.javabean.UtilsEspectadorBean"></jsp:useBean>

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
		<title>Vista Criticas Espectaculo</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/criticaEspectaculo.css">
		<script type="text/javascript" src="/practica3/js/confirmationBorrarCritica.js"></script>
	</head>
	<body>
		<%
			ArrayList<CriticaDTO> criticas = (ArrayList<CriticaDTO>)request.getAttribute("criticas");//utilsEspectadorBean.getEspectaculos();
			String titulo = (String)request.getAttribute("titulo");
		%>
		<fieldset>
			<legend><%= titulo %></legend>
			<%if(criticas == null || criticas.size() == 0){%>
				<div>Este espectáculo no tiene críticas</div>
			<%}
			else{
				
			 for (CriticaDTO c: criticas) { %>
				<div>
				<p class="titulo">Título: <%= c.getTitulo() %><br>(<%= c.getPuntuacion() %>/10)</p>
				<p>Descripcion: <%= c.getDescripcion() %></p>
		    	<p>Valoración media de la crítica: <%= c.getMedia() %></p>
		    	<% if( c.getUsuario() != customerBean.getID() )
		    	{ %>
		    	<div>
					<form action="/practica3/valorarcritica" method="POST">
						<input type="hidden" id="idCritica" name="idCritica" value="<%= c.getID()%>">
						<input type="submit" name="opcion" value="Valorar">
					</form>
				</div>
				<%}
		    	else
		    	{ %>
		    	<div>
					<form id="formBorrarCritica" action="/practica3/borrarcritica" method="POST">
						<input type="hidden" id="idCritica" name="idCritica" value="<%= c.getID()%>">
						<input type="submit" name="opcion" value="Borrar">
					</form>
				</div>
				<% } %>
				 </div>
				 <% }%> 
			 <% }%>
			
			<form method="post" action="/practica3/index.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>
