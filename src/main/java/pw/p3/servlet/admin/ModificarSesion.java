package pw.p3.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pw.p3.business.session.SesionDTO;
import pw.p3.data.dao.session.SesionDAO;
import pw.p3.display.javabean.CustomerBean;
import pw.p3.display.javabean.UtilsBean;

public class ModificarSesion extends HttpServlet{
	
	private static final long serialVersionUID = 35523L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		UtilsBean utilsBean = (UtilsBean) session.getAttribute("utilsBean");
		
		if (customerBean == null || customerBean.getEmail().equals("")) {
			dispatcher = request.getRequestDispatcher("/practica3/index.jsp");
			dispatcher.forward(request, response);
		}
		
		String idSesionMod = request.getParameter("idSesion");
		
		if (idSesionMod != null) {
			ArrayList<SesionDTO> sesiones = utilsBean.getSesiones();
			for( SesionDTO sesion: sesiones) {
				if (Integer.toString(sesion.getID()).equals(idSesionMod)) {
					utilsBean.setSesionModificar(sesion);
					break;
				}
			}
			
			dispatcher = request.getRequestDispatcher("mvc/view/vistaModificarSesion.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			SesionDTO sesionMod = utilsBean.getSesionModificar();
			ServletContext application = session.getServletContext();
			
			String dbservidor = application.getInitParameter("servidor");
			String dbusuario = application.getInitParameter("usuario");
			String dbpass = application.getInitParameter("pass");
			String dbname = application.getInitParameter("dbname");
			String pathSQL = application.getInitParameter("pathSQL");
			
			java.io.InputStream myIO = application.getResourceAsStream(pathSQL);
			java.util.Properties prop = new java.util.Properties();
			prop.load(myIO);
	
			String fecha = request.getParameter("fechaSesion");
			String checkedFecha = fecha.substring(0,10)+' '+fecha.substring(11);
			
			sesionMod.setFecha(checkedFecha);
	
			SesionDAO sesionDAO = new SesionDAO(dbservidor, dbusuario, dbpass, dbname, prop);
			sesionDAO.actualizarSesion(sesionMod);
			
			utilsBean.setSesionModificar(null);
			
			dispatcher = request.getRequestDispatcher("mvc/view/vistaListaEspectaculos.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}