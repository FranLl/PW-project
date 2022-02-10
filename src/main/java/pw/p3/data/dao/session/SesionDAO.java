package pw.p3.data.dao.session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import pw.p3.business.session.SesionDTO;
import pw.p3.business.show.EspectaculoDTO;
import pw.p3.data.dao.common.DBConnection;

/**
 * DAO to manage sessions  info
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class SesionDAO {
	
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
	
	public SesionDAO(String dbServidor, String dbUsuario, String dbPass, String dbName, Properties pathSQL) {
		this.dbName = dbName;
		this.dbPass = dbPass;
		this.dbServidor = dbServidor;
		this.dbUsuario = dbUsuario;
		this.pathSQL = pathSQL;
	}
	
	/**
	 * Creates a new session
	 * @param sesion
	 * @return true on success
	 */
	public boolean crearSesion(SesionDTO sesion){
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);;
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.createSesion(sesion.getID(), sesion.getFecha(), sesion.getLocVendidas(),
					sesion.getEspectaculo(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		return (status == 1);
	}
	
	/**
	 * Retrieves all the sessions of the given show
	 * @param espectaculo
	 * @return ArrayList<SesionDTO> with the sessions
	 */
	public ArrayList<SesionDTO> getSesiones(EspectaculoDTO espectaculo){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<SesionDTO> sesiones = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.sesionesByEspectaculo(espectaculo.getID(), con);
			
			dbConnection.closeConnection();


			if (result != null) {
				result.forEach((item) -> {
					sesiones.add(new SesionDTO(Integer.parseInt(item.get("idSesion")),item.get("fecha"), 
							Integer.parseInt(item.get("locVendidas")), Integer.parseInt(item.get("espectaculo"))));
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return sesiones;	
	}
	
	/**
	 * Deletes a session
	 * @param sesion
	 * @return true on success
	 */
	public boolean borrarSesion (SesionDTO sesion) {

		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.deleteSesion(sesion.getID(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		return (status == 1);
	}
	
	/**
	 * Updates a session info
	 * @param sesion
	 * @return true on success
	 */
	public boolean actualizarSesion (SesionDTO sesion) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.updateSesion(sesion.getID(), sesion.getFecha(), sesion.getLocVendidas(),
					sesion.getEspectaculo(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		return (status == 1);
	}
	
	/**
	 * Deletes a session of the given show
	 * @param espectaculoID
	 * @return true on success
	 */
	public boolean borrarSesionByEspectaculo (int espectaculoID) {

		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.deleteSesionByEspectaculo(espectaculoID, con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		return (status == 1);
	}
}
