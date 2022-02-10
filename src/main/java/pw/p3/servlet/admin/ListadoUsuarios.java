package pw.p3.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pw.p3.business.user.UsuarioDTO;
import pw.p3.data.dao.user.UsuarioDAO;
import pw.p3.display.javabean.CustomerBean;
import pw.p3.display.javabean.UtilsBean;

public class ListadoUsuarios extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		UtilsBean utilsBean = (UtilsBean) session.getAttribute("utilsBean");
		
		if (customerBean == null || customerBean.getEmail().equals("")) {
			dispatcher = request.getRequestDispatcher("/practica3/index.jsp");
			dispatcher.forward(request, response);
		}
		
		ServletContext application = session.getServletContext();
		
		String dbservidor = application.getInitParameter("servidor");
		String dbusuario = application.getInitParameter("usuario");
		String dbpass = application.getInitParameter("pass");
		String dbname = application.getInitParameter("dbname");
		String pathSQL = application.getInitParameter("pathSQL");
		
		java.io.InputStream myIO = application.getResourceAsStream(pathSQL);
		
		java.util.Properties prop = new java.util.Properties();

		prop.load(myIO);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(dbservidor, dbusuario, dbpass, dbname, prop);
		ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = usuarioDAO.getUsuarios();
		
		utilsBean.setUsuarios(usuarios);

		dispatcher = request.getRequestDispatcher("mvc/view/vistaListaUsuarios.jsp");
		dispatcher.forward(request, response);
	}
}
