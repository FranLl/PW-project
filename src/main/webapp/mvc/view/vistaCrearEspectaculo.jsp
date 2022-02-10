<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Crear Espectaculo</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/crearShow.css">
		<script src="/practica3/js/crearEspectaculo.js"></script>
	</head>
	<body>
		<%
		String message = request.getParameter("message");

		if (message == null) message = "";
		
		if (customerBean == null || customerBean.getEmail().equals("")) {
			%>
			<jsp:forward page="../../index.jsp"/>
			<%
		} else {
			%>
			<%= message %>
			<fieldset>
			<legend>Crear espectáculo</legend>
				<form id="formCrearEspectaculo" method="post" action="/practica3/crearEspectaculo">
					<p>
						<label for="titulo">Titulo</label>
						<input type="text" name="titulo" value="" placeholder="Titulo" required/>
					</p>
					<p>
						<label for="descripcion">Descripcion</label>
						<textarea name="descripcion" rows="5" placeholder="Descripcion" required></textarea>
					</p>
					<div>
						<fieldset id="categoria">
			    			<legend class="paralelo">Categoria:</legend>
							<p class="radio">
								<input type="radio" id="concierto" name="categoria" value="concierto" checked>
								<label for="concierto">Concierto</label>
							</p>
							<p class="radio">
								<input type="radio" id="obra" name="categoria" value="obra">
								<label for="obra">Obra</label>
							</p>
							<p class="radio">
								<input type="radio" id="monologo" name="categoria" value="monologo">
								<label for="monologo">Monólogo</label>
							</p>
						</fieldset>
						
						<fieldset id="tipo">
			    			<legend class="paralelo">Tipo:</legend>
							<p class="radio">
								<input type="radio" id="puntual" name="tipo" value="puntual" checked>
								<label for="puntual">Puntual</label>
							</p>
							<p class="radio">
								<input type="radio" id="multiple" name="tipo" value="multiple">
								<label for="multiple">Multiple</label>
							</p>
							<p class="radio">
								<input type="radio" id="temporada" name="tipo" value="temporada">
								<label for="temporada">Temporada</label>
							</p>
						</fieldset>
					</div>
					
					<div id="locVentas">
						<label for="locVenta">Localidades</label>
						<input type="number" name="locVenta" value="0" placeholder="30" required />
					</div>
					
					
					<div class="date" id="divFecha1">
						<label class="date_name" for="fecha1" id="labelFecha1">Fecha</label>
						<input type="datetime-local" name="fecha1" id="fecha1" required>
					</div>
					<div class="date" id="divFecha2">
						<label class="date_name" for="fecha2" id="labelFecha2">Fecha 2</label>
						<input type="datetime-local" name="fecha2" id="fecha2" required>
					</div>
					<div class="date" id="divFecha3">
						<label class="date_name" for="fecha1" id="labelFecha3">Fecha 3</label>
						<input type="datetime-local" name="fecha3" id="fecha3" required>
					</div>
					
					<input id="submitBtn" type="submit" value="Crear"/>
				</form>
			
				<form id="volver" method="get" action="/practica3/mvc/view/adminView.jsp">
					<input type="submit" value="Volver">
				</form>
			</fieldset>
			<%
		}
		%>
	</body>
</html>