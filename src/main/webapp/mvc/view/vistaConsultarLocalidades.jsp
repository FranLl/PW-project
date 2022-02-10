<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.show.EspectaculoDTO,pw.p3.business.common.Utils, pw.p3.data.dao.common.Categoria" %>
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
		<title>Vista Consultar Localidades</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/checkTickets.css">
	</head>
	<body>
		<%
			ArrayList<EspectaculoDTO> espectaculos = (ArrayList<EspectaculoDTO>)request.getAttribute("espectaculos");//utilsEspectadorBean.getEspectaculos();
		%>
		<fieldset>
			<legend>Consultar localidades</legend>
			<form action="/practica3/consultarlocalidades" method="POST">
				<p>Conciertos</p>
				<dl>
					<% int con = 0, obra = 0, mon = 0;
					for (EspectaculoDTO e: espectaculos) { 
						if( e.getCategoria() == Categoria.valueOf("concierto") )
						{
							con++;
						%>
							<dt><input type="radio" id="idEspectaculo" name="idEspectaculo" value="<%= e.getID()%>"><%= e.getTitulo() %></dt>
					    	<dd><%= e.getDescripcion() %></dd>
					    <%}
						else if( e.getCategoria() == Categoria.valueOf("obra") ) obra++;
						else mon++;
					}
					if(con == 0){%>
						<dt class="no_show">No hay espectáculos de este tipo</dt>
					<%}%>
				</dl>
				<p>Obras</p>
				<dl>
					<%
					if(obra != 0){
						for (EspectaculoDTO e: espectaculos) { 
							if( e.getCategoria() == Categoria.valueOf("obra") )
							{%>
								<dt><input type="radio" id="idEspectaculo" name="idEspectaculo" value="<%= e.getID()%>"><%= e.getTitulo() %></dt>
						    	<dd><%= e.getDescripcion() %></dd>
						    <%}			
						}
					}
					else{%>
						<dt class="no_show">No hay espectáculos de este tipo</dt>
					<%}%>
				</dl>
				<p>Monólogos</p>
				<dl>
					<%if(mon != 0){
					for (EspectaculoDTO e: espectaculos) { 
						if( e.getCategoria() == Categoria.valueOf("monologo") )
						{%>
							<dt><input type="radio" id="idEspectaculo" name="idEspectaculo" value="<%= e.getID()%>"><%= e.getTitulo() %></dt>
					    	<dd><%= e.getDescripcion() %></dd>
					    <%}			
					} }
					else{%>
						<dt class="no_show">No hay espectáculos de este tipo</dt>
					<%}%>
				</dl>
				<!-- <label for="sesion">Sesión</label><br>
				<input type="datetime-local" id="fecha" name="fecha" placeholder="YYYY-MM-DD HH:MM" required>	-->
				<input type="submit" value="Consultar">
			</form>
			<form id="volver" method="post" action="/practica3/index.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>