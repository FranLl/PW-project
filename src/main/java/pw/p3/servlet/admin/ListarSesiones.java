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
import pw.p3.business.show.EspectaculoDTO;
import pw.p3.data.dao.common.TipoEspectaculo;
import pw.p3.data.dao.session.SesionDAO;
import pw.p3.display.javabean.CustomerBean;
import pw.p3.display.javabean.UtilsBean;

public class ListarSesiones  extends HttpServlet{
	
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

		String idEspectaculoMod = request.getParameter("idEspectaculo");
			
		ArrayList<EspectaculoDTO> espectaculos = utilsBean.getEspectaculos();
		EspectaculoDTO espectaculoMod = null;
		for(EspectaculoDTO espectaculo: espectaculos) {
			if (Integer.toString(espectaculo.getID()).equals(idEspectaculoMod)) {
				espectaculoMod = espectaculo;
				break;
			}
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
		
		SesionDAO sesionDAO = new SesionDAO(dbservidor, dbusuario, dbpass, dbname, prop);
		ArrayList<SesionDTO> sesionesEspectaculoModificar = sesionDAO.getSesiones(espectaculoMod);
		utilsBean.setSesiones(sesionesEspectaculoModificar);
		
		if (espectaculoMod.getTipo() == TipoEspectaculo.puntual) {
			utilsBean.setSesionModificar(sesionesEspectaculoModificar.get(0));
			dispatcher = request.getRequestDispatcher("/mvc/view/vistaModificarSesion.jsp");
			dispatcher.forward(request, response);
		}
		else {
			utilsBean.setSesiones(sesionesEspectaculoModificar);

			dispatcher = request.getRequestDispatcher("/mvc/view/vistaListaSesiones.jsp");
			dispatcher.forward(request, response);
			
		}
	}
}
