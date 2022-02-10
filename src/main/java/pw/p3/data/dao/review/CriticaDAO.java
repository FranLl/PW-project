package pw.p3.data.dao.review;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import pw.p3.business.review.CriticaDTO;
import pw.p3.business.show.EspectaculoDTO;
import pw.p3.business.user.UsuarioDTO;
import pw.p3.data.dao.common.DBConnection;



/**
 * DAO to manage reviews  info
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class CriticaDAO {
	
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
	
	public CriticaDAO(String dbServidor, String dbUsuario, String dbPass, String dbName, Properties pathSQL) {
		this.dbName = dbName;
		this.dbPass = dbPass;
		this.dbServidor = dbServidor;
		this.dbUsuario = dbUsuario;
		this.pathSQL = pathSQL;
	}
	
	/**
	 * Creates a new review
	 * @param critica
	 * @return true on success
	 */
	public boolean crearCritica(CriticaDTO critica) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.createCritica(critica.getID(), critica.getTitulo(), critica.getDescripcion(),
					critica.getPuntuacion(), critica.getUsuario(), critica.getEspectaculo(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1) ? true : false;
	}
	
	/**
	 * Retrieves the review of the given id
	 * @param ID critica
	 * @return  CriticaDTO
	 */
	public CriticaDTO consultarCritID ( int ID ){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		CriticaDTO criticaDTO = new CriticaDTO(-1, "null", "null", -1, -1, -1 );
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.criticasGetAll(con);
			
			dbConnection.closeConnection();
			
			if (result != null) {
				result.forEach((item) -> {
					if( Integer.parseInt(item.get("idCritica")) == ID )
					{
						criticaDTO.setID( Integer.parseInt(item.get("idCritica")) );
						criticaDTO.setTitulo( item.get("titulo") );
						criticaDTO.setDescripcion( item.get("descripcion") );
						criticaDTO.setPuntuacion( Integer.parseInt(item.get("puntuacion")) );
						criticaDTO.setUsuario( Integer.parseInt(item.get("usuario")) ); 
						criticaDTO.setEspectaculo( Integer.parseInt(item.get("espectaculo")) );
					}
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		if( criticaDTO.getID() == -1)
			return null;
		
		return criticaDTO;
	}
	
	/**
	 * Retrieves the reviews of the given show
	 * @param espectaculo
	 * @return  ArrayList<CriticaDTO> with the reviews
	 */
	public ArrayList<CriticaDTO> consultarCritEsp (EspectaculoDTO espectaculo){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<CriticaDTO> criticas = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.criticasByEspectaculo(espectaculo.getID(), con);
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					criticas.add(new CriticaDTO(Integer.parseInt(item.get("idCritica")), item.get("titulo"), item.get("descripcion"),
							Integer.parseInt(item.get("puntuacion")), Integer.parseInt(item.get("usuario")), 
							Integer.parseInt(item.get("espectaculo"))));
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return criticas;
	}
	
	/**
	 * Retrieves the reviews of the given user
	 * @param usuario
	 * @return  ArrayList<CriticaDTO> with the reviews
	 */
	public ArrayList<CriticaDTO> consultarCritUsu (UsuarioDTO usuario){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<CriticaDTO> criticas = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.criticasByUsuario(usuario.getID(), con);
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					criticas.add(new CriticaDTO(Integer.parseInt(item.get("idCritica")), item.get("titulo"), item.get("descripcion"),
							Integer.parseInt(item.get("puntuacion")), Integer.parseInt(item.get("usuario")), 
							Integer.parseInt(item.get("espectaculo"))));
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return criticas;
	}
	
	/**
	 * Retrieves all the reviews made
	 * @return  ArrayList<CriticaDTO> with the reviews
	 */
	
	public ArrayList<CriticaDTO> consultarCriticas (){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<CriticaDTO> criticas = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.criticasGetAll(con);
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					criticas.add(new CriticaDTO(Integer.parseInt(item.get("idCritica")), item.get("titulo"), item.get("descripcion"),
							Integer.parseInt(item.get("puntuacion")), Integer.parseInt(item.get("usuario")), 
							Integer.parseInt(item.get("espectaculo"))));
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return criticas;
	}
	
	/**
	 * Removes a review from the data base
	 * @param critica
	 * @return true on success
	 */
	public boolean borrarCritica (CriticaDTO critica) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.deleteCritica(critica.getID(), con);
			
			dbConnection.closeConnection();
			
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}
	
	/**
	 * Updates a review info
	 * @param critica
	 * @return true on success
	 */
	public boolean actualizarCritica (CriticaDTO critica) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.updateCritica(critica.getID(), critica.getTitulo(), critica.getDescripcion(),
					critica.getPuntuacion(), critica.getUsuario(), critica.getEspectaculo(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}
	
	/**
	 * Check if the user has created a review to given show 
	 * @param idUsuario
	 * @param idEspectaculo
	 * @return true on success
	 */
	public boolean comprobarEspectaculoCriticado( int idUsuario, int idEspectaculo )
	{
		boolean status = false;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.countUserReview (idUsuario, idEspectaculo, con);
			
			dbConnection.closeConnection();	
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return status;
	}
	
	/**
	 * Get average rate of given review
	 * @param idCritica
	 * @return media
	 */
	public float getMedia( int idCritica )
	{
		float media = 0;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			media = dbConnection.averageReviewRate( idCritica, con);
			
			dbConnection.closeConnection();	
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return media;
	}
}
