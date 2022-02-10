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
import pw.p3.data.dao.common.Categoria;
import pw.p3.business.show.EspectaculoDTO;

/**
 * Servlet implementation class BuscarEspectaculoServlet
 */
@WebServlet(name="buscarespectaculo", urlPatterns="/buscarespectaculo")
public class BuscarEspectaculoServlet extends HttpServlet {
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
			//Para los formularios es getParameter
			String buscar = request.getParameter("buscar");

			if ( buscar == null  )
			{
				//Lo mandamos a la vista sin datos
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaBuscarEspectaculos.jsp");
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
				
				if( buscar.equals("titulo") )
				{
					String titulo = (String)request.getParameter("clave");
				
					//Busco por título y se lo envío a la vista				
					EspectaculoDTO espectaculoTemp = espectaculoDAO.getEspectaculos( titulo );
					
					if( espectaculoTemp == null )
					{
						request.setAttribute("message", "El título a buscar no ha sido encontrado");
						dispatcher = request.getRequestDispatcher("/mvc/view/vistaBuscarEspectaculos.jsp");
					}
					else
					{
						ArrayList<EspectaculoDTO> espectaculos = new ArrayList<>();
						espectaculos.add( espectaculoTemp );
						request.setAttribute("espectaculos",espectaculos);
						dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarEspectaculos.jsp");
					}
				}
				else if( buscar.equals("categoria") )
				{
					Categoria categoria = Categoria.valueOf( (String)request.getParameter("categoria") );
						
					//Busco por categoría y se lo envío a la vista
					ArrayList<EspectaculoDTO> espectaculos = espectaculoDAO.getEspectaculos(categoria);
						
					if( espectaculos.isEmpty() )
					{
						request.setAttribute("message", "No hay ningún espectáculo con la categoría introducida");
						dispatcher = request.getRequestDispatcher("/mvc/view/vistaBuscarEspectaculos.jsp");
					}
					else
					{
						/* Con javaBean y session
						UtilsEspectadorBean utilsEspectadorBean = new UtilsEspectadorBean();
						utilsEspectadorBean.setEspectaculos( espectaculos );
						session.setAttribute("utilsEspectadorBean", utilsEspectadorBean);
						*/
						request.setAttribute("espectaculos",espectaculos);
						dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarEspectaculos.jsp");
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
