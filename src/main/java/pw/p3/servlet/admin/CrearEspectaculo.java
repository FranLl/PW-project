package pw.p3.servlet.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pw.p3.business.common.Utils;
import pw.p3.business.factory.CreadorEspConcreto;
import pw.p3.business.session.SesionDTO;
import pw.p3.business.show.EspectaculoDTO;
import pw.p3.data.dao.common.Categoria;
import pw.p3.data.dao.common.TipoEspectaculo;
import pw.p3.data.dao.session.SesionDAO;
import pw.p3.data.dao.show.EspectaculoDAO;
import pw.p3.display.javabean.CustomerBean;

public class CrearEspectaculo extends HttpServlet{
	
	private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		
		if (customerBean == null || customerBean.getEmail().equals("")) {
			dispatcher = request.getRequestDispatcher("/practica3/index.jsp");
			dispatcher.forward(request, response);
		}
		
		dispatcher = request.getRequestDispatcher("mvc/view/vistaCrearEspectaculo.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		
		if (customerBean == null || customerBean.getEmail().equals("")) {
			dispatcher = request.getRequestDispatcher("/practica3/index.jsp");
			dispatcher.forward(request, response);
		}
		
		String dbservidor = application.getInitParameter("servidor");
		String dbusuario = application.getInitParameter("usuario");
		String dbpass = application.getInitParameter("pass");
		String dbname = application.getInitParameter("dbname");
		String pathSQL = application.getInitParameter("pathSQL");
		
		java.io.InputStream myIO = application.getResourceAsStream(pathSQL);
		java.util.Properties prop = new java.util.Properties();
		prop.load(myIO);

		EspectaculoDAO espectaculoDAO = new EspectaculoDAO(dbservidor, dbusuario, dbpass, dbname, prop);
		

		CreadorEspConcreto espFactory = new CreadorEspConcreto();
		EspectaculoDTO espectaculo;
		
		TipoEspectaculo tipo = TipoEspectaculo.valueOf(request.getParameter("tipo"));
		String titulo = request.getParameter("titulo");
		String descripcion = request.getParameter("descripcion");
		int locVenta = Integer.parseInt(request.getParameter("locVenta"));
		Categoria categoria = Categoria.valueOf(request.getParameter("categoria"));
		
		if(tipo == TipoEspectaculo.puntual) {
			espectaculo = espFactory.crearEspPuntual(-1, titulo, descripcion, locVenta, categoria);
		}
		else if(tipo == TipoEspectaculo.multiple) {
			espectaculo = espFactory.crearEspMultiple(-1, titulo, descripcion, locVenta, categoria);
		}
		else {
			espectaculo = espFactory.crearEspTemporada(-1, titulo, descripcion, locVenta, categoria);
		}
		
		SesionDAO sesionDAO = new SesionDAO(dbservidor, dbusuario, dbpass, dbname, prop);
		
		if(espectaculoDAO.crearEspectaculo(espectaculo)) {
			espectaculo = espectaculoDAO.getEspectaculos(titulo);
			if(espectaculo.getTipo() == TipoEspectaculo.puntual) {
				String fecha = request.getParameter("fecha1");
				String checkedFecha = fecha.substring(0,10)+' '+fecha.substring(11);
				SesionDTO sesion = new SesionDTO(-1, checkedFecha, 0, espectaculo.getID());
				sesionDAO.crearSesion(sesion);
			}
			else if(tipo == TipoEspectaculo.multiple) {
				if (request.getParameter("fecha1") != "") {
					String fecha = request.getParameter("fecha1");
					String checkedFecha = fecha.substring(0,10)+' '+fecha.substring(11);
					SesionDTO sesionDTO1 = new SesionDTO(-1, checkedFecha, 0, espectaculo.getID());
					sesionDAO.crearSesion(sesionDTO1);
				}

				if (request.getParameter("fecha2") != "") {
					String fecha = request.getParameter("fecha2");
					String checkedFecha = fecha.substring(0,10)+' '+fecha.substring(11);
					SesionDTO sesionDTO2 = new SesionDTO(-1, checkedFecha, 0, espectaculo.getID());
					sesionDAO.crearSesion(sesionDTO2);
				}
				
				if (request.getParameter("fecha3") != "") {
					String fecha = request.getParameter("fecha3");
					String checkedFecha = fecha.substring(0,10)+' '+fecha.substring(11);
					SesionDTO sesionDTO3 = new SesionDTO(-1, checkedFecha, 0, espectaculo.getID());
					sesionDAO.crearSesion(sesionDTO3);
				}
			}
			else {
				String fecha1 = request.getParameter("fecha1");
				String checkedFecha1 = fecha1.substring(0,10)+' '+fecha1.substring(11);
				Date sesion1 = Utils.stringToDate(checkedFecha1);
				String fecha2 = request.getParameter("fecha2");
				String checkedFecha2 = fecha2.substring(0,10)+' '+fecha2.substring(11);
				Date sesion2 = Utils.stringToDate(checkedFecha2);
				
				long milisWeek = 604800000;
				long milisSesion1 = sesion1.getTime();
				long milisSesion2 = sesion2.getTime();
				
				do {					
					SesionDTO sesionDTO = new SesionDTO(-1, Utils.millisToString(milisSesion1), 0, espectaculo.getID());
					sesionDAO.crearSesion(sesionDTO);
					
					milisSesion1 = milisSesion1 + milisWeek;
					 
				} while (milisSesion1 <= milisSesion2);
				
				
				
				/*
				for (long milisSesion1 = sesion1.getTime(); milisSesion1 <= milisSesion2; milisSesion1 += milisWeek) {
					System.out.println(milisSesion1);
					SesionDTO sesionDTO = new SesionDTO(-1, Utils.millisToString(milisSesion1), 0, espectaculo.getID());
					sesionDAO.crearSesion(sesionDTO);
				}
				*/
			}
		}
		
		dispatcher = request.getRequestDispatcher("mvc/view/adminView.jsp");
		dispatcher.forward(request, response);
	}
}
