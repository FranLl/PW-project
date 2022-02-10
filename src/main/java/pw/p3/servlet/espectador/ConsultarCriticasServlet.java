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
import pw.p3.data.dao.review.CriticaDAO;
import pw.p3.business.review.CriticaDTO;
import pw.p3.business.show.EspectaculoDTO;

/**
 * Servlet implementation class BuscarEspectaculoServlet
 */
@WebServlet(name="consultarcriticas", urlPatterns="/consultarcriticas")
public class ConsultarCriticasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if ( customerBean.getEmail().equals("") )
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
			
			String variable = request.getParameter("idEspectaculo");
			
			EspectaculoDAO espectaculoDAO = new EspectaculoDAO(dbservidor, dbusuario, dbpass, dbname, prop);
			CriticaDAO criticaDAO = new CriticaDAO(dbservidor, dbusuario, dbpass, dbname, prop);
			
			if ( variable == null  )
			{
				//Lo mandamos a la vista con todos los espectaculos
				ArrayList<EspectaculoDTO> espectaculos = espectaculoDAO.getEspectaculos();
				ArrayList<EspectaculoDTO> espectCriticas = new ArrayList<>();
				
				for(EspectaculoDTO espectaculoDTO: espectaculos) {
					ArrayList<CriticaDTO> criticasDTO = criticaDAO.consultarCritEsp( espectaculoDTO );
					try {
						if( ! criticasDTO.isEmpty() )
							espectCriticas.add(espectaculoDTO);
					} catch(Exception e) {continue;}
				}
							
				request.setAttribute("espectaculos", espectCriticas);
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarEspectaculosCriticas.jsp");
			}
			else
			{
				Integer idEspectaculo = Integer.parseInt(variable);
				
				EspectaculoDTO espectaculoDTO = espectaculoDAO.getEspectaculos(idEspectaculo);
			
				ArrayList<CriticaDTO> criticasDTO = criticaDAO.consultarCritEsp( espectaculoDTO );
				
				for( CriticaDTO critica : criticasDTO)
				{
					critica.setMedia( criticaDAO.getMedia(critica.getID()) );
				}
				
				request.setAttribute( "titulo", espectaculoDTO.getTitulo() );
				request.setAttribute( "criticas", criticasDTO );
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarCriticasEspectaculo.jsp");
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
