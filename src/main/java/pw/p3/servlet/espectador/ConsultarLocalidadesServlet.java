package pw.p3.servlet.espectador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pw.p3.data.dao.show.EspectaculoDAO;
import pw.p3.display.javabean.CustomerBean;
import pw.p3.data.dao.session.SesionDAO;
import pw.p3.business.session.SesionDTO;
import pw.p3.business.show.EspectaculoDTO;

/**
 * Servlet implementation class BuscarEspectaculoServlet
 */
@WebServlet(name="consultarlocalidades", urlPatterns="/consultarlocalidades")
public class ConsultarLocalidadesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if ( customerBean == null || customerBean.getEmail().equals("") )
		{
			//Inserted an error message on request
			request.setAttribute("message", "Acceda o regístrese");
			//Forwarding to index.jsp;
		}
		else
		{
			ServletContext application = session.getServletContext();
			String dbservidor = application.getInitParameter("servidor");
			String dbusuario = application.getInitParameter("usuario");
			String dbpass = application.getInitParameter("pass");
			String dbname = application.getInitParameter("dbname");
			String pathSQL = application.getInitParameter("pathSQL");
			java.io.InputStream myIO = application.getResourceAsStream(pathSQL);
			java.util.Properties prop = new java.util.Properties();

			prop.load(myIO);
			
			EspectaculoDAO espectaculoDAO = new EspectaculoDAO(dbservidor, dbusuario, dbpass, dbname, prop);
			
			//Para los formularios es getParameter
			String idEspectaculo = request.getParameter("idEspectaculo");
			
			if ( idEspectaculo == null  )
			{
				ArrayList<EspectaculoDTO> espectaculos = espectaculoDAO.getEspectaculos();
				
				request.setAttribute("espectaculos",espectaculos);
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaConsultarLocalidades.jsp");
			}
			else
			{				
				SesionDAO sesionDAO = new SesionDAO(dbservidor, dbusuario, dbpass, dbname, prop);
				
				EspectaculoDTO espectaculo = espectaculoDAO.getEspectaculos( Integer.parseInt(idEspectaculo));
				
				ArrayList<SesionDTO> sesiones = sesionDAO.getSesiones(espectaculo);

				request.setAttribute("espectaculo",espectaculo);
				request.setAttribute("sesiones",sesiones);
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarSesiones.jsp");
			}
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
