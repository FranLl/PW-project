<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>
<%@ page import="pw.p3.data.dao.user.UsuarioDAO,pw.p3.business.user.UsuarioDTO" %>
<jsp:useBean id="utilsBean" scope="session" class="pw.p3.display.javabean.UtilsBean"></jsp:useBean>
<jsp:useBean id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<%
String nextPage = "/";
String nextPageMessage = "Acceda o reg&iacute;strese";


if (customerBean != null && !customerBean.getEmail().equals("")) {

	if(customerBean.getAdmin()){
		String dbservidor = application.getInitParameter("servidor");
		String dbusuario = application.getInitParameter("usuario");
		String dbpass = application.getInitParameter("pass");
		String dbname = application.getInitParameter("dbname");
		String pathSQL = application.getInitParameter("pathSQL");
		nextPage = "../view/adminView.jsp";
		//Create UtilsBean with registered users
		java.io.InputStream myIO = application.getResourceAsStream(pathSQL);
			
		java.util.Properties prop = new java.util.Properties();

		prop.load(myIO);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(dbservidor, dbusuario, dbpass, dbname, prop);
		java.util.ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = usuarioDAO.getUsuarios();
%>
		<jsp:setProperty property="usuarios" value="<%=usuarios%>" name="utilsBean"/>
<%
	}
	else nextPage = "../view/spectatorView.jsp";
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=nextPageMessage%>" name="message"/>
</jsp:forward>