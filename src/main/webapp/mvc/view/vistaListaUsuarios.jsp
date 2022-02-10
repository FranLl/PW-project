<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import = "pw.p3.business.user.UsuarioDTO,pw.p3.business.common.Utils" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>

<%
java.util.ArrayList<UsuarioDTO> usuarios = utilsBean.getUsuarios();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista de usuarios</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/listarUser.css">
	</head>
	<body>
		<fieldset>
			<legend>Lista de usuarios</legend>
			<p>ADMINS</p>
			<ul>
				<%for(UsuarioDTO usuario: usuarios){
					if (usuario.getAdmin()){
						String fecha = "";
						if(!usuario.getLogin().equals("0"))
							fecha = Utils.millisToString(Long.parseLong(usuario.getLogin()));
						else
							fecha = "Nunca se ha logeado";
					%>
						<li><%=usuario.getNick()%> // Último login: <%=fecha%></li>
				<%}}%>
			</ul>
			<p>ESPECTADORES</p>
			<ul>
				<%for(UsuarioDTO usuario: usuarios){
					if (!usuario.getAdmin()){
						String fecha = "";
						if(!usuario.getLogin().equals("0"))
							fecha = Utils.millisToString(Long.parseLong(usuario.getLogin()));
						else
							fecha = "Nunca se ha logeado";
					%>
						<li><%=usuario.getNick()%> // Último login: <%=fecha%></li>
				<%}}%>
			</ul>
		
			<form method="get" action="/practica3/mvc/view/adminView.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>