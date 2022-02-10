<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>
<%@ page import="pw.p3.data.dao.user.UsuarioDAO,pw.p3.business.user.UsuarioDTO" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<%
String nextPage = "";
String nextPageMessage = "";

String nickUser = request.getParameter("nick");
String nameUser = request.getParameter("name");
String surnameUser = request.getParameter("surname");
String passwordUser = request.getParameter("password");

String dbservidor = application.getInitParameter("servidor");
String dbusuario = application.getInitParameter("usuario");
String dbpass = application.getInitParameter("pass");
String dbname = application.getInitParameter("dbname");
String pathSQL = application.getInitParameter("pathSQL");

java.io.InputStream myIO = application.getResourceAsStream(pathSQL);

java.util.Properties prop = new java.util.Properties();

prop.load(myIO);

UsuarioDAO usuarioDAO = new UsuarioDAO(dbservidor, dbusuario, dbpass, dbname, prop);

UsuarioDTO usuarioTemp = null;

//Caso 1: no hay usuario logueado
if (customerBean == null || customerBean.getEmail().equals("")) {
	nextPage="../../index.jsp";
	nextPageMessage = "Acceda o reg&iacute;strese";
}
//Caso 2: no hay datos, es la primera vez que llega al controladorModificar
else if( nickUser == null )
{
	nextPage="../view/vistaModificar.jsp";
	nextPageMessage = "Los campos en blanco se mantendr&aacute;n";
}
//Caso 3: hay datos en el request, modificamos el usuario
else
{
	//Error en el nick
	if( usuarioDAO.comprobarUsuarioNick( nickUser ) )
	{
		nextPage="../view/vistaModificar.jsp";
		nextPageMessage = "Error al actualizar el usuario. Nick ya cogido. Escoja otro nick";
	}
	else
	{
		if (nickUser.equals("")){
			nickUser = customerBean.getNick();
		}
		if (nameUser.equals("")){
			nameUser = customerBean.getNombre();
		}
		if (surnameUser.equals("")){
			surnameUser = customerBean.getApellidos();
		}
		if (passwordUser.equals("")){
			//Para sacar la contraseña
			if( usuarioDAO.comprobarUsuarioNick( customerBean.getNick() ) )
				usuarioTemp = usuarioDAO.consultarUsuario( customerBean.getEmail() );
			
			passwordUser = usuarioTemp.getPassword();
			//
		}
		UsuarioDTO usuarioDTO = new UsuarioDTO( customerBean.getID(), nickUser, nameUser, surnameUser, 
				customerBean.getEmail(), customerBean.getAdmin(), customerBean.getRegistro(), customerBean.getLogin(), passwordUser);
		
		//Usuario actualizado correctamente
		if( usuarioDAO.actualizarUsuario( usuarioDTO ) )
		{
%>
			<jsp:setProperty property="nick" value="<%=usuarioDTO.getNick()%>" name="customerBean"/>
			<jsp:setProperty property="nombre" value="<%=usuarioDTO.getNombre()%>" name="customerBean"/>
			<jsp:setProperty property="apellidos" value="<%=usuarioDTO.getApellidos()%>" name="customerBean"/>
<%
			nextPage="../view/vistaModificar.jsp";
			nextPageMessage = "Usuario actualizado correctamente";
		}
		//Usuario no actualizado
		else
		{
			nextPage="../view/vistaModificar.jsp";
			nextPageMessage = "Usuario NO actualizado correctamente";
		}
	}	
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=nextPageMessage%>" name="message"/>
</jsp:forward>