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
import pw.p3.data.dao.session.SesionDAO;
import pw.p3.business.common.Utils;
import pw.p3.business.session.SesionDTO;
import pw.p3.business.show.EspectaculoDTO;

/**
 * Servlet implementation class BuscarEspectaculoServlet
 */
@WebServlet(name="buscarentradas", urlPatterns="/buscarentradas")
public class BuscarEntradasServlet extends HttpServlet {
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
			//Para los formularios es getParameter
			String categoria = request.getParameter("categoria");

			if ( categoria == null  )
			{
				//Lo mandamos a la vista sin datos
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaBuscarEntradas.jsp");
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
				SesionDAO sesionDAO = new SesionDAO(dbservidor, dbusuario, dbpass, dbname, prop);
				
				ArrayList<EspectaculoDTO> espectaculos = new ArrayList<>();
				
				if( categoria.equals("todas") )
				{			
					espectaculos = espectaculoDAO.getEspectaculos();
				}
				else
				{
					Categoria categ = Categoria.valueOf(categoria);
					espectaculos = espectaculoDAO.getEspectaculos(categ);
				}
				
				ArrayList<EspectaculoDTO> espectaculosProximos = new ArrayList<>();
				
				for( EspectaculoDTO espectaculoDTO : espectaculos )
				{
					ArrayList<SesionDTO> sesiones = sesionDAO.getSesiones(espectaculoDTO);
					
					for( SesionDTO sesionDTO : sesiones )
					{
						if( ( !espectaculosProximos.contains(espectaculoDTO) )
								&& (System.currentTimeMillis() <= Utils.stringToDate(sesionDTO.getFecha()).getTime() )
								&& ( espectaculoDTO.getLocalidades() - sesionDTO.getLocVendidas() > 0 ) )
						{
							espectaculosProximos.add(espectaculoDTO);
						}
					}
				}
				request.setAttribute("espectaculos",espectaculosProximos);
				dispatcher = request.getRequestDispatcher("/mvc/view/vistaListarEspectaculos.jsp");
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
