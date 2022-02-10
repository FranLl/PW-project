package pw.p3.data.dao.show;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import pw.p3.business.factory.CreadorEspConcreto;
import pw.p3.business.show.EspectaculoDTO;
import pw.p3.data.dao.common.Categoria;
import pw.p3.data.dao.common.DBConnection;

/**
 * DAO to manage shows info
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class EspectaculoDAO {

	private String dbServidor, dbUsuario, dbPass, dbName;
	private Properties pathSQL;
	
	/**
	 * Constructor
	 * @param dbServidor
	 * @param dbUsuario
	 * @param dbPass
	 * @param dbName
	 * @param pathSQL, sql.properties path
	 */
	public EspectaculoDAO(String dbServidor, String dbUsuario, String dbPass, String dbName, Properties pathSQL) {
		this.dbName = dbName;
		this.dbPass = dbPass;
		this.dbServidor = dbServidor;
		this.dbUsuario = dbUsuario;
		this.pathSQL = pathSQL;
	}
	
	/**
	 * Creates a new show
	 * @param espectaculo
	 * @return true on success
	 */
	public boolean crearEspectaculo(EspectaculoDTO espectaculo) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.createEspectaculo(espectaculo.getID(), espectaculo.getTitulo(), 
					espectaculo.getDescripcion(), espectaculo.getLocalidades(), espectaculo.getTipo().toString(),
					espectaculo.getCategoria().toString(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}

	/**
	 * Removes a show from the data base
	 * @param espectaculo
	 * @return true on success
	 */
	public boolean borrarEspectaculo (EspectaculoDTO espectaculo) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.deleteEspectaculo(espectaculo.getID(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}
	
	/**
	 * Updates the show info
	 * @param espectaculo
	 * @return true on success
	 */
	public boolean actualizarEspectaculo (EspectaculoDTO espectaculo) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.updateEspectaculo(espectaculo.getID(), espectaculo.getTitulo(), 
					espectaculo.getDescripcion(), espectaculo.getLocalidades(), espectaculo.getTipo().toString(),
					espectaculo.getCategoria().toString(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		return (status == 1);
	}
	
	/**
	 * Retrieves all the shows existing
	 * @return ArrayList<EspectaculoDTO> with the shows
	 */
	public ArrayList<EspectaculoDTO> getEspectaculos(){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<EspectaculoDTO> espectaculos = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.espectaculosGetAll(con);
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					CreadorEspConcreto cec = new CreadorEspConcreto();
					EspectaculoDTO espectaculo = null;
	
					if(item.get("tipoEspectaculo").equals("puntual")) {
						
						espectaculo = cec.crearEspPuntual(Integer.parseInt(item.get("idEspectaculo")), item.get("titulo"),
								item.get("descripcion"), Integer.parseInt(item.get("localidades")), Categoria.valueOf(item.get("categoria")));
						
					} else if (item.get("tipoEspectaculo").equals("multiple")) {
						
						espectaculo = cec.crearEspMultiple(Integer.parseInt(item.get("idEspectaculo")), item.get("titulo"),
								item.get("descripcion"), Integer.parseInt(item.get("localidades")), Categoria.valueOf(item.get("categoria")));
						
					} else if (item.get("tipoEspectaculo").equals("temporada")){
						
						espectaculo = cec.crearEspTemporada(Integer.parseInt(item.get("idEspectaculo")), item.get("titulo"),
								item.get("descripcion"), Integer.parseInt(item.get("localidades")), Categoria.valueOf(item.get("categoria")));
						
					}
					
					espectaculos.add(espectaculo);
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return espectaculos;	
	}
	
	/**
	 * Retrieves a show given its title
	 * @param titulo
	 * @return EspectaculoDTO of the show, null if not found
	 */
	public EspectaculoDTO getEspectaculos(String titulo){
		Hashtable<String, String> result = new Hashtable<>();
		EspectaculoDTO espectaculo = null;
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.espectaculoByTitulo(titulo, con);
			
			dbConnection.closeConnection();
			CreadorEspConcreto cec = new CreadorEspConcreto();

			if (result != null) {
				if(result.get("tipoEspectaculo").equals("puntual")) {
					
					espectaculo = cec.crearEspPuntual(Integer.parseInt(result.get("idEspectaculo")), result.get("titulo"),
							result.get("descripcion"), Integer.parseInt(result.get("localidades")),
							Categoria.valueOf(result.get("categoria")));
					
				} else if (result.get("tipoEspectaculo").equals("multiple")) {
					
					espectaculo = cec.crearEspMultiple(Integer.parseInt(result.get("idEspectaculo")), result.get("titulo"),
							result.get("descripcion"), Integer.parseInt(result.get("localidades")),
							Categoria.valueOf(result.get("categoria")));
					
				} else if (result.get("tipoEspectaculo").equals("temporada")){
					
					espectaculo = cec.crearEspTemporada(Integer.parseInt(result.get("idEspectaculo")), result.get("titulo"),
							result.get("descripcion"), Integer.parseInt(result.get("localidades")),
							Categoria.valueOf(result.get("categoria")));
					
				}
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return espectaculo;	
	}
	
	/**
	 * Retrieves a show given its id
	 * @param idEspectaculo
	 * @return EspectaculoDTO of the show, null if not found
	 */
	public EspectaculoDTO getEspectaculos(int idEspectaculo){
		Hashtable<String, String> result = new Hashtable<>();
		EspectaculoDTO espectaculo = null;
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.espectaculoById(idEspectaculo, con);
			
			dbConnection.closeConnection();
			CreadorEspConcreto cec = new CreadorEspConcreto();

			if (result != null) {
				if(result.get("tipoEspectaculo").equals("puntual")) {
					
					espectaculo = cec.crearEspPuntual(Integer.parseInt(result.get("idEspectaculo")), result.get("titulo"),
							result.get("descripcion"), Integer.parseInt(result.get("localidades")), Categoria.valueOf(result.get("categoria")));
					
				} else if (result.get("tipoEspectaculo").equals("multiple")) {
					
					espectaculo = cec.crearEspMultiple(Integer.parseInt(result.get("idEspectaculo")), result.get("titulo"),
							result.get("descripcion"), Integer.parseInt(result.get("localidades")), Categoria.valueOf(result.get("categoria")));
					
				} else if (result.get("tipoEspectaculo").equals("temporada")){
					
					espectaculo = cec.crearEspTemporada(Integer.parseInt(result.get("idEspectaculo")), result.get("titulo"),
							result.get("descripcion"), Integer.parseInt(result.get("localidades")), Categoria.valueOf(result.get("categoria")));
					
				}
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return espectaculo;	
	}
	
	/**
	 * Retrieves all the shows given a category
	 * @param tipoEspectaculo
	 * @return ArrayList<EspectaculoDTO> with the shows
	 */
	public ArrayList<EspectaculoDTO> getEspectaculos(Categoria categoria){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<EspectaculoDTO> espectaculos = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.espectaculosByCategoria(categoria.toString(), con);
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					CreadorEspConcreto cec = new CreadorEspConcreto();
					EspectaculoDTO espectaculo = null;
	
					if(item.get("tipoEspectaculo").equals("puntual")) {
						
						espectaculo = cec.crearEspPuntual(Integer.parseInt(item.get("idEspectaculo")), item.get("titulo"),
								item.get("descripcion"), Integer.parseInt(item.get("localidades")), Categoria.valueOf(item.get("categoria")));
						
					} else if (item.get("tipoEspectaculo").equals("multiple")) {
						
						espectaculo = cec.crearEspMultiple(Integer.parseInt(item.get("idEspectaculo")), item.get("titulo"),
								item.get("descripcion"), Integer.parseInt(item.get("localidades")), Categoria.valueOf(item.get("categoria")));
						
					} else if (item.get("tipoEspectaculo").equals("temporada")){
						
						espectaculo = cec.crearEspTemporada(Integer.parseInt(item.get("idEspectaculo")), item.get("titulo"),
								item.get("descripcion"), Integer.parseInt(item.get("localidades")), Categoria.valueOf(item.get("categoria")));
						
					}
					
					espectaculos.add(espectaculo);
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return espectaculos;	
	}
}
