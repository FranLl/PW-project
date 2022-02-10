<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="pw.p3.data.dao.user.UsuarioDAO,pw.p3.business.user.UsuarioDTO" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<% 
	String nextPage = "controladorContenido.jsp";
	String nextPageMessage = "";

	if (customerBean == null || customerBean.getEmail().equals("")) {
		String emailUser = request.getParameter("email");
		String passUser = request.getParameter("password");
		
		if (emailUser != null){
			String dbservidor = application.getInitParameter("servidor");
			String dbusuario = application.getInitParameter("usuario");
			String dbpass = application.getInitParameter("pass");
			String dbname = application.getInitParameter("dbname");
			String pathSQL = application.getInitParameter("pathSQL");
			java.io.InputStream myIO = application.getResourceAsStream(pathSQL);
			java.util.Properties prop = new java.util.Properties();

			prop.load(myIO);
			
			UsuarioDAO usuarioDAO = new UsuarioDAO(dbservidor, dbusuario, dbpass, dbname, prop);
			UsuarioDTO usuario = usuarioDAO.consultarUsuario(emailUser);
		
			if ( usuario == null || !usuario.getEmail().equals(emailUser) || !usuario.getPassword().equals(passUser)  ) {
				nextPage = "../view/vistaLogin.jsp";
				nextPageMessage = "Usuario o contrase&ntilde;a incorrecta";
			}
			else
			{
				%>
				<jsp:setProperty property="ID" value="<%=usuario.getID()%>" name="customerBean"/>
				<jsp:setProperty property="nick" value="<%=usuario.getNick()%>" name="customerBean"/>
				<jsp:setProperty property="nombre" value="<%=usuario.getNombre()%>" name="customerBean"/>
				<jsp:setProperty property="apellidos" value="<%=usuario.getApellidos()%>" name="customerBean"/>
				<jsp:setProperty property="email" value="<%=usuario.getEmail()%>" name="customerBean"/>
				<jsp:setProperty property="admin" value="<%=usuario.getAdmin()%>" name="customerBean"/>
				<jsp:setProperty property="registro" value="<%=usuario.getRegistro()%>" name="customerBean"/>
				<jsp:setProperty property="login" value="<%=usuario.getLogin()%>" name="customerBean"/>
				<%
				
				usuario.setLogin(Long.toString(System.currentTimeMillis()));
				usuarioDAO.actualizarUsuario(usuario);
				
				//Según el tipo de usuario, lo mandamos a un servlets (o vista) o a otro
				if( usuario.getAdmin() )
				{
					nextPage = "../view/vistaLogin.jsp";
				}
				else
				{
					nextPage = "../view/spectatorView.jsp";
				}
			}
		}
		else {
			nextPage = "../view/vistaLogin.jsp";
		}
	}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=nextPageMessage%>" name="message"/>
</jsp:forward>