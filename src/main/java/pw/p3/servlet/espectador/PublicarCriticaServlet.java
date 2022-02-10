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
import pw.p3.data.dao.session.SesionDAO;
import pw.p3.business.common.Utils;
import pw.p3.business.review.CriticaDTO;
import pw.p3.business.session.SesionDTO;
import pw.p3.business.show.EspectaculoDTO;

/**
 * Servlet implementation class BuscarEspectaculoServlet
 */
@WebServlet(name="publicarcritica", urlPatterns="/publicarcritica")
public class PublicarCriticaServlet extends HttpServlet {
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
			
			String variable = request.getParameter("idEspectaculo");
			
			EspectaculoDAO espectaculoDAO = new EspectaculoDAO(dbservidor, dbusuario, dbpass, dbname, prop);
			
			if ( variable == null  )
			{
				//Lo mandamos a la vista con todos los espectaculos
				SesionDAO sesionDAO = new SesionDAO(dbservidor, dbusuario, dbpass, dbname, prop);
				ArrayList<EspectaculoDTO> espectaculos = espectaculoDAO.getEspectaculos();
				ArrayList<EspectaculoDTO> espectAnteriores = new ArrayList<>();
				
				for(EspectaculoDTO espectaculoDTO: espectaculos) {
					ArrayList<SesionDTO> sesionesDTO = sesionDAO.getSesiones( espectaculoDTO );
					for( SesionDTO sesionDTO : sesionesDTO )
					{
						try {
							if( System.currentTimeMillis() > Utils.stringToDate(sesionDTO.getFecha()).getTime() && !espectAnteriores.contains(espectaculoDTO) )
								espectAnteriores.add(espectaculoDTO);
						} catch(Exception e) {continue;}
					}
				}
							
				request.setAttribute("espectaculos", espectAnteriores);
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarCriticas.jsp");
			}
			else
			{
				Integer idEspectaculo = Integer.parseInt(variable);
				
				CriticaDAO criticaDAO = new CriticaDAO(dbservidor, dbusuario, dbpass, dbname, prop);
				
				if( criticaDAO.comprobarEspectaculoCriticado( customerBean.getID(), idEspectaculo) )
				{
					request.setAttribute( "message", "El espectáculo seleccionado ya lo has criticado" );
					dispatcher = request.getRequestDispatcher("/mvc/view/spectatorView.jsp");
				}
				else 
				{
					String tituloCrit = request.getParameter("titulo");
					
					if( tituloCrit == null )
					{		
						EspectaculoDTO espectaculoDTO = espectaculoDAO.getEspectaculos(idEspectaculo);
					
						request.setAttribute( "espectaculo", espectaculoDTO );
						dispatcher = request.getRequestDispatcher("/mvc/view/vistaCrearCritica.jsp");
					}
					else
					{
						String descripcionCrit = request.getParameter("descripcion");
						Integer puntuacionCrit = Integer.parseInt( request.getParameter("puntuacion") );
						
						CriticaDTO criticaDTO = new CriticaDTO( -1, tituloCrit, descripcionCrit, puntuacionCrit, customerBean.getID(), idEspectaculo );
						
						if( criticaDAO.crearCritica( criticaDTO ) )
						{
							request.setAttribute( "message", "Crítica creada correctamente" );
							dispatcher = request.getRequestDispatcher("/mvc/view/spectatorView.jsp");
						}
						else
						{
							request.setAttribute( "message", "Error al crear la crítica" );
							dispatcher = request.getRequestDispatcher("/mvc/view/spectatorView.jsp");
						}
					}
				}
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
