package pw.p3.servlet.espectador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pw.p3.display.javabean.CustomerBean;
import pw.p3.data.dao.rate.ValoracionDAO;
import pw.p3.data.dao.review.CriticaDAO;
import pw.p3.business.review.CriticaDTO;

/**
 * Servlet implementation class BuscarEspectaculoServlet
 */
@WebServlet(name="borrarcritica", urlPatterns="/borrarcritica")
public class BorrarCriticaServlet extends HttpServlet {
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
			String variable = request.getParameter("idCritica");
			
			if ( !variable.equals("")  )
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
				
				CriticaDAO criticaDAO = new CriticaDAO(dbservidor, dbusuario, dbpass, dbname, prop);
				
				CriticaDTO criticaDTO = criticaDAO.consultarCritID( Integer.parseInt(variable) );
				
				if( criticaDTO != null )
				{
					ValoracionDAO valoracionDAO = new ValoracionDAO(dbservidor, dbusuario, dbpass, dbname, prop);
					
					valoracionDAO.borrarValoraciones(criticaDTO);
					
					if( criticaDAO.borrarCritica(criticaDTO) )
						request.setAttribute("message", "Crítica borrada correctamente");
					else
						request.setAttribute("message", "No se ha podido borrar la crítica pero sí sus valoraciones");
				}
			}
			else
			{
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarEspectaculosCriticas.jsp");
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
