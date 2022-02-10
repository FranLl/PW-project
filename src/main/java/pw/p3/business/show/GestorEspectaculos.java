package pw.p3.business.show;

import java.util.ArrayList;
import java.util.Properties;

import pw.p3.business.common.Utils;
import pw.p3.business.factory.CreadorEspConcreto;
import pw.p3.business.session.SesionDTO;
import pw.p3.data.dao.common.Categoria;
import pw.p3.data.dao.common.TipoEspectaculo;
import pw.p3.data.dao.session.SesionDAO;
import pw.p3.data.dao.show.EspectaculoDAO;

/**
 * Class that manages all the shows 
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class GestorEspectaculos {
	private String dbServidor, dbUsuario, dbPass, dbName;
	private Properties pathSQL;
	private EspectaculoDAO espectaculoDAO = new EspectaculoDAO(dbServidor, dbUsuario, dbPass, dbName, pathSQL);
	private SesionDAO sesionDAO = new SesionDAO(dbServidor, dbUsuario, dbPass, dbName, pathSQL);
	
	/**
	 * Constructor
	 * @param dbServidor
	 * @param dbUsuario
	 * @param dbPass
	 * @param dbName
	 * @param pathSQL, sql.properties path
	 */
	public GestorEspectaculos(String dbServidor, String dbUsuario, String dbPass, String dbName, Properties pathSQL) {
		this.dbName = dbName;
		this.dbPass = dbPass;
		this.dbServidor = dbServidor;
		this.dbUsuario = dbUsuario;
		this.pathSQL = pathSQL;
	}
	
	/**
	 * Creates a new show
	 * 
	 * @param tipo
	 * @param titulo
	 * @param categoria
	 * @param descripcion
	 * @param locVenta
	 * @param fechas, an ArrayList<String>
	 * @return 1 if show was properly created, -1 error parsing date, -2 if the show was not created
	 */
	public int crearEspectaculo( TipoEspectaculo tipo, String titulo, Categoria categoria,
			String descripcion, int locVenta, ArrayList<String> fechas ) {
		CreadorEspConcreto espFactory = new CreadorEspConcreto();
		EspectaculoDTO espectaculo;
		try {
			for(String f : fechas)
				Utils.stringToDate(f);
		} catch(Exception e) {
			//Error when parsing date
			return -1;
		}
		
		if(tipo == TipoEspectaculo.puntual) {
			espectaculo = espFactory.crearEspPuntual(-1, titulo, descripcion, locVenta, categoria);
			
		}
		
		else if(tipo == TipoEspectaculo.multiple) {
			espectaculo = espFactory.crearEspMultiple(-1, titulo, descripcion, locVenta, categoria);
		}
			
		else {
			espectaculo = espFactory.crearEspTemporada(-1, titulo, descripcion, locVenta, categoria);
		}
		
		EspectaculoDAO espDAO = new EspectaculoDAO(dbServidor, dbUsuario, dbPass, dbName, pathSQL);
		boolean ret = espDAO.crearEspectaculo(espectaculo);
		
		if(ret) {
			int id = espDAO.getEspectaculos(titulo).getID();
			SesionDAO sesionDAO = new SesionDAO(dbServidor, dbUsuario, dbPass, dbName, pathSQL);
			fechas.forEach((f) -> {
				sesionDAO.crearSesion(new SesionDTO(-1, f, 0, id));
			});
		}
		else return -2;

		

		return 1;
	}
	
	/**
	 * Cancels a session of a show or the whole show
	 * 
	 * @param espectaculo
	 * @param fecha
	 * @return true if cancelation was done
	 */
	public boolean cancelarEspectaculo( EspectaculoDTO espectaculo, SesionDTO sesion ) {
		boolean correcto = false;
		ArrayList<SesionDTO> sesiones;
		if ( sesion == null) {
			sesiones = sesionDAO.getSesiones( espectaculo );
			for(SesionDTO sesionDTO : sesiones)
			{
				if( !sesionDAO.borrarSesion( sesionDTO ) )
				{
					return false;
				}
			}
			
			if( espectaculoDAO.borrarEspectaculo( espectaculo ) )
			{
				correcto = true;
			}
		}
		else
		{
			if( sesionDAO.borrarSesion( sesion ) )
			{
				correcto = true;
				sesiones = sesionDAO.getSesiones( espectaculo );
				//If there is no more sessions remaining, then delete the show
				if(sesiones.isEmpty()) cancelarEspectaculo(espectaculo, null);
			}
		}
		
		return correcto;
	}
	
	/**
	 * Updates a show
	 * @param espectaculo
	 * @param titulo
	 * @param categoria
	 * @param descripcion
	 * @param locVenta
	 * @param fecha
	 * @return true if the show was properly updated
	 */
	public boolean actualizarEspectaculo( EspectaculoDTO espectaculo, String titulo, Categoria categoria,
			String descripcion, int locVenta ) {
		boolean correcto = false;
		
		EspectaculoDTO aux = espectaculo;
		
		aux.setTitulo(titulo);
		aux.setCategoria(categoria);
		aux.setDescripcion(descripcion);
		aux.setLocalidades(locVenta);
		
		if( espectaculoDAO.actualizarEspectaculo( aux ) )
		{
			correcto = true;
			espectaculo = aux;
		}
		
		return correcto;
	}
	
	/**
	 * Gets the remaining tickets given a show and a date
	 * @param espectaculo
	 * @param sesion
	 * @return remaining tickets for a given session of a given show
	 */
	public int contarLocalidadesRestantes(EspectaculoDTO espectaculo, SesionDTO sesion) {
		return espectaculo.getLocalidades() - sesion.getLocVendidas();
	}
	
	/**
	 * Gets the sold tickets given a show and a date
	 * @param sesion
	 * @return sold tickets of the given session
	 */
	public int contarLocalidadesVendidas( SesionDTO sesion ) {
		return sesion.getLocVendidas();
	}
	
	/**
	 * Searches a show given its title
	 * @param titulo
	 * @return EspectaculoDTO
	 */
	public EspectaculoDTO buscarEspectaculoTitulo( String titulo ){		
		return espectaculoDAO.getEspectaculos( titulo );
	}

	/**
	 * 
	 * Returns all the shows of a specific category
	 * If categoria == null, returns every show
	 * @param categoria
	 * @return ArrayList<EspectaculoDTO> with the shows found
	 */
	public ArrayList<EspectaculoDTO> buscarEspectaculoCat( Categoria categoria ){
		return espectaculoDAO.getEspectaculos( categoria );
	}
	
	/**
	 * Retreives the next sessions of a given the title of a show
	 * @param categoria
	 * @return ArrayList<SesionDTO> with the next sessions of the given show
	 */
	public ArrayList<SesionDTO> sesionesProximas(EspectaculoDTO espectaculo){
		ArrayList<SesionDTO> sesiones = sesionDAO.getSesiones( espectaculo );
		ArrayList<SesionDTO> sesionesProx = new ArrayList<>();
		
		for( SesionDTO sesionDTO : sesiones )
		{
			try {
				if(System.currentTimeMillis() <= Utils.stringToDate(sesionDTO.getFecha()).getTime() )
					sesionesProx.add( sesionDTO );
			} catch(Exception e) {}
		}
		
		return sesionesProx;
	}
	
	/**
	 * Retreives the next shows
	 * @return ArrayList<EspectaculoDTO> with the shows that have next sessions
	 */
	public ArrayList<EspectaculoDTO> espectaculosProximos(){
		ArrayList<EspectaculoDTO> espProx = new ArrayList<>();
		espProx = espectaculoDAO.getEspectaculos();
		
		for(EspectaculoDTO espectaculoDTO: espProx) {
			ArrayList<SesionDTO> sesionesDTO = sesionDAO.getSesiones( espectaculoDTO );
			for( SesionDTO sesionDTO : sesionesDTO ) {
				try {
					if(System.currentTimeMillis() <= Utils.stringToDate(sesionDTO.getFecha()).getTime() && !espProx.contains(espectaculoDTO) )
						espProx.add(espectaculoDTO);
				} catch(Exception e) {continue;}
				
			}
		}
		
		return espProx;
	}
	
	/**
	 * Retrieve the next sessions of a given category of a show
	 * @param categoria
	 * @return ArrayList<EspectaculoDTO> with the next shows given their category 
	 */
	public ArrayList<EspectaculoDTO> espectaculosProximos( Categoria categoria ){
		ArrayList<EspectaculoDTO> espProx = new ArrayList<>();
		espProx = espectaculoDAO.getEspectaculos( categoria );
		
		for(EspectaculoDTO espectaculoDTO: espProx) {
			ArrayList<SesionDTO> sesionesDTO = sesionDAO.getSesiones( espectaculoDTO );
			for( SesionDTO sesionDTO : sesionesDTO ) {
				try {
					if(System.currentTimeMillis() < Utils.stringToDate(sesionDTO.getFecha()).getTime() && !espProx.contains(espectaculoDTO) )
						espProx.add(espectaculoDTO);
				} catch(Exception e) {continue;}
			}
		}
		
		return espProx;
	}
	
	/**
	 * Retrieves the shows that have already been celebrated
	 * @return ArrayList<EspectaculoDTO> with past shows
	 */
	public ArrayList<EspectaculoDTO> espectaculosAnteriores(){
		ArrayList<EspectaculoDTO> espAnt = new ArrayList<>();
		espAnt = espectaculoDAO.getEspectaculos();
	
		for(EspectaculoDTO espectaculoDTO: espAnt) {
			ArrayList<SesionDTO> sesionesDTO = sesionDAO.getSesiones( espectaculoDTO );
			for( SesionDTO sesionDTO : sesionesDTO )
			{
				try {
					if( System.currentTimeMillis() > Utils.stringToDate(sesionDTO.getFecha()).getTime() && !espAnt.contains(espectaculoDTO) )
						espAnt.add(espectaculoDTO);
				} catch(Exception e) {continue;}
			}
		}
		
		return espAnt;
	}
	
	/**
	 * Buys tickets given a show, a date and the amount of tickets to buy
	 * @param espectaculo
	 * @param fecha
	 * @param entradas
	 * @return true if there was enough tickets to sell
	 */
	public boolean compraEntradas(EspectaculoDTO espectaculo, SesionDTO sesion, int entradas) {
		SesionDTO aux = sesion;
		
		if( aux.getLocVendidas() + entradas <= espectaculo.getLocalidades() ) 
		{
			aux.setLocVendidas( aux.getLocVendidas() + entradas);
			if (sesionDAO.actualizarSesion( aux )) {
				sesion = aux;
				return true;
			}
		}
	
		return false;
	}
}
