package pw.p3.data.dao.rate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import pw.p3.business.rate.ValoracionDTO;
import pw.p3.business.review.CriticaDTO;
import pw.p3.data.dao.common.DBConnection;



/**
 * DAO to manage rates info
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class ValoracionDAO {

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
	
	public ValoracionDAO(String dbServidor, String dbUsuario, String dbPass, String dbName, Properties pathSQL) {
		this.dbName = dbName;
		this.dbPass = dbPass;
		this.dbServidor = dbServidor;
		this.dbUsuario = dbUsuario;
		this.pathSQL = pathSQL;
	}
	
	/**
	 * Creates a new rate
	 * @param valoracion
	 * @return true on success
	 */
	public boolean crearValoracion(ValoracionDTO valoracion) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.createValoracion(valoracion.getID(), valoracion.getValoracion(), valoracion.getUsuario(),
					valoracion.getCritica(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		return (status == 1);
	}
	
	/**
	 * Retrieves the rates of a given review
	 * @param critica
	 * @return ArrayList<ValoracionDTO> with the rates
	 */
	public ArrayList<ValoracionDTO> getValoraciones(CriticaDTO critica){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<ValoracionDTO> valoraciones = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.valoracionesByCritica(critica.getID(), con);
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					valoraciones.add(new ValoracionDTO(Integer.parseInt(item.get("idValoracion")), Integer.parseInt(item.get("valoracion")), 
							Integer.parseInt(item.get("usuario")), Integer.parseInt(item.get("critica"))));
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return valoraciones;	
	}
	
	/**
	 * Deletes a rate form the data base
	 * @param critica
	 * @return true on success
	 */
	public boolean borrarValoraciones (CriticaDTO critica) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);

			status = dbConnection.deleteValoraciones(critica.getID(), con);
			
			dbConnection.closeConnection();
			
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}
	
	/**
	 * Check if the user has rated the given review 
	 * @param idUsuario
	 * @param idCritica
	 * @return true on success
	 */
	public boolean comprobarCriticaValorada( int idUsuario, int idCritica )
	{
		boolean status = false;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.countReviewRated(idUsuario, idCritica, con);
			
			dbConnection.closeConnection();	
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return status;
	}
}
