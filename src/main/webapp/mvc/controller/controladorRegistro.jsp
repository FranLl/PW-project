<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>
<%@ page import="pw.p3.data.dao.user.UsuarioDAO,pw.p3.business.user.UsuarioDTO" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) Hay parámetros en el request  -> procede de la vista 
	2) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
*/

String nextPage = "";
String mensajeNextPage = "";

String nickUser = request.getParameter("nick");
String nameUser = request.getParameter("name");
String surnameUser = request.getParameter("surname");
String emailUser = request.getParameter("email");
String stringAdminUser = request.getParameter("isAdmin");
String password = request.getParameter("password");
Boolean isAdminUser = false;
if( stringAdminUser != null && !stringAdminUser.equals("") )
{
	isAdminUser = true;
}

//Caso 1: Hay parámetros -> procede de la VISTA
if (nickUser != null) {
	String dbservidor = application.getInitParameter("servidor");
	String dbusuario = application.getInitParameter("usuario");
	String dbpass = application.getInitParameter("pass");
	String dbname = application.getInitParameter("dbname");
	String pathSQL = application.getInitParameter("pathSQL");
	
	java.io.InputStream myIO = application.getResourceAsStream(pathSQL);
	
	java.util.Properties prop = new java.util.Properties();

	prop.load(myIO);
	
	UsuarioDAO usuarioDAO = new UsuarioDAO(dbservidor, dbusuario, dbpass, dbname, prop);
		
	//Si el nick ya existe, se devuelve un error
	if( usuarioDAO.comprobarUsuarioNick(nickUser) )
	{
		nextPage = "../view/vistaRegistro.jsp";
		mensajeNextPage = "El nick que ha indicado ya existe y no se puede crear";
	}
	//Si el email ya existe, se devuelve un error
	else if( usuarioDAO.comprobarUsuarioEmail(emailUser) )
	{
		nextPage = "../view/vistaRegistro.jsp";
		mensajeNextPage = "El email que ha indicado ya existe y no se puede crear";	
	}
	else//Datos correctos, creamos el usuario
	{
		UsuarioDTO usuarioDTO = new UsuarioDTO( -1, nickUser, nameUser, 
				surnameUser, emailUser, isAdminUser, Long.toString(System.currentTimeMillis()), "0", password);
		usuarioDAO.crearUsuario( usuarioDTO );
		
		nextPage = "../../index.jsp";
		mensajeNextPage = "Usuario creado correctamente";
	}
//Caso 2 -> se debe ir a la vista por primera vez
} else {
	nextPage = "../view/vistaRegistro.jsp";		
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>