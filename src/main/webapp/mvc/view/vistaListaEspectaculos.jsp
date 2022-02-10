<%@page import="pw.p3.data.dao.common.TipoEspectaculo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.show.EspectaculoDTO, pw.p3.business.common.Utils, pw.p3.data.dao.*" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>

<%
java.util.ArrayList<EspectaculoDTO> espectaculos = utilsBean.getEspectaculos();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista de espectaculos</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/listarShows.css">
	</head>
	<body>
		<fieldset>
			<legend>Lista de espectáculos</legend>
			<p>
				Espectaculos puntuales
			</p>
			<ul>
				<%for(EspectaculoDTO espectaculo: espectaculos){
					if (espectaculo.getTipo() == TipoEspectaculo.puntual){
						%>
						<li>
							<div>
								<%=espectaculo.getTitulo()%>
							</div> 
							<form method="get" action="/practica3/listadoSesiones">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Modificar">
							</form>
							<form method="get" action="/practica3/borrarSesion">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Cancelar">
							</form>
						</li>
						<%
					}
				}%>
			</ul>
			<p>
				Espectaculos multiples
			</p>
	
			<ul>
				<%for(EspectaculoDTO espectaculo: espectaculos){
				 	if (espectaculo.getTipo() == TipoEspectaculo.multiple){
						%>
						<li>
							<div>
								<%=espectaculo.getTitulo()%>
							</div> 
							<form method="get" action="/practica3/crearSesion">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Añadir">
							</form>
							
							<form method="get" action="/practica3/listadoSesiones">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Modificar">
							</form>
							<form method="get" action="/practica3/borrarSesion">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Borrar">
							</form>
							<form method="get" action="/practica3/cancelarEspectaculo">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Cancelar">
							</form>
						</li>
						<%
					}
				}%>
			</ul>
			<p>
				Espectaculos de temporada
			</p>
			<ul>
				<%for(EspectaculoDTO espectaculo: espectaculos){
					if (espectaculo.getTipo() == TipoEspectaculo.temporada){
						%>
						<li>
							<div>
								<%=espectaculo.getTitulo()%>
							</div> 
							<form method="get" action="/practica3/crearSesion">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Añadir">
							</form>
							<form method="get" action="/practica3/listadoSesiones">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Modificar">
							</form>
							<form method="get" action="/practica3/borrarSesion">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Borrar">
							</form>
							<form method="get" action="/practica3/cancelarEspectaculo">
								<input type="hidden" name="idEspectaculo" value="<%=espectaculo.getID()%>">
								<input type="submit" value="Cancelar">
							</form>
						</li>
						<%
					}
				}%>
			</ul>
		
			<form method="get" action="/practica3/mvc/view/adminView.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>