package pw.p3.data.dao.user;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import pw.p3.business.user.UsuarioDTO;
import pw.p3.data.dao.common.DBConnection;

/**
 * DAO to manage users info
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class UsuarioDAO {
	
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
	
	public UsuarioDAO(String dbServidor, String dbUsuario, String dbPass, String dbName, Properties pathSQL) {
		this.dbName = dbName;
		this.dbPass = dbPass;
		this.dbServidor = dbServidor;
		this.dbUsuario = dbUsuario;
		this.pathSQL = pathSQL;
	}

	/**
	 * Creates a new user
	 * @param usuario
	 * @return true on success
	 */
	public boolean crearUsuario(UsuarioDTO usuario) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);

			int admin = usuario.getAdmin() ? 1 : 0;
			status = dbConnection.createUser(usuario.getID(), usuario.getNick(), usuario.getNombre(), usuario.getApellidos(), 
					usuario.getEmail(), admin, usuario.getRegistro(), usuario.getLogin(), con);
			if( status == 1)
			{
				status = 0;
				Hashtable<String, String> result;
				result = dbConnection.usuarioByEmail(usuario.getEmail(), con);

				if (result != null) {
					status = dbConnection.createPassword(-1,usuario.getPassword(),Integer.parseInt(result.get("idUsuario")), con);
				}
			}
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status == 1);
	}
	
	/**
	 * Removes an user from the data base
	 * @param usuario
	 * @return true on success
	 */
	public boolean borrarUsuario (UsuarioDTO usuario) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			status = dbConnection.deleteUser(usuario.getID(), con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}
	
	/**
	 * Updates an user info
	 * @param usuario
	 * @return true on success
	 */
	public boolean actualizarUsuario (UsuarioDTO usuario) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);

			int admin = usuario.getAdmin() ? 1 : 0;
			status = dbConnection.updateUsuario(usuario.getID(), usuario.getNick(), usuario.getNombre(), usuario.getApellidos(), 
					usuario.getEmail(), admin, usuario.getRegistro(), usuario.getLogin(), con);
			
			if( status == 1)
			{
				status = 0;
				status = dbConnection.updatePassword(usuario.getPassword(), usuario.getID(), con);
			}
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}
	
	/**
	 * Checks if there is an user with the given email in the data base
	 * @param email
	 * @return UsuarioDTO with the user info, null if not found
	 */
	public UsuarioDTO consultarUsuario (String email) {
		UsuarioDTO usuario = null;

		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			Hashtable<String, String> result;
			result = dbConnection.usuarioByEmail(email, con);
			
			if (result != null) {
				Hashtable<String, String> password = dbConnection.passwordByID(Integer.parseInt(result.get("idUsuario")), con);
				
				boolean admin = Integer.parseInt(result.get("isAdmin")) != 0;
				usuario = new UsuarioDTO(Integer.parseInt(result.get("idUsuario")), result.get("nick"), result.get("nombre"),
						result.get("apellidos"), result.get("email"), admin, result.get("registro"), result.get("login"), password.get("password"));
			}
			
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	/**
	 * Checks if there is an user with the given id in the data base
	 * @param idUsuario
	 * @return UsuarioDTO with the user info, null if not found
	 */
	public UsuarioDTO consultarUsuario (int idUsuario) {
		UsuarioDTO usuario = null;
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			Hashtable<String, String> result;
			result = dbConnection.usuarioById(idUsuario, con);
			
			dbConnection.closeConnection();

			if (result != null) {
				boolean admin = Integer.parseInt(result.get("isAdmin")) != 0;
				usuario = new UsuarioDTO(Integer.parseInt(result.get("idUsuario")), result.get("nick"), result.get("nombre"),
						result.get("apellidos"), result.get("email"), admin, result.get("registro"), result.get("login"), result.get("password"));
			}
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	/**
	 * Checks if there is an user with the given nick in the data base
	 * @param nick
	 * @return true if found, false if not
	 */
	public boolean comprobarUsuarioNick (String nick) {
		int count = 0;
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			count = dbConnection.countUserNick(nick, con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (count > 0);
	}
	
	/**
	 * Checks if there is an user with the given email in the data base
	 * @param email
	 * @return true if found, false if not
	 */
	public boolean comprobarUsuarioEmail (String email) {
		int count = 0;
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			count = dbConnection.countUserEmail(email, con);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (count > 0);
	}
	
	/**
	 * Retrieves all the users in the data base, no matter which type of user they are
	 * @return ArrayList<UsuarioDTO> with all the registered users in the data base
	 */
	public ArrayList<UsuarioDTO> getUsuarios(){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection(pathSQL);
			Connection con = dbConnection.getConnection(dbServidor, dbUsuario, dbPass, dbName);
			
			result = dbConnection.usuariosGetAll(con);
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					boolean admin = Integer.parseInt(item.get("isAdmin")) != 0;
					UsuarioDTO usuario = new UsuarioDTO(Integer.parseInt(item.get("idUsuario")), item.get("nick"), item.get("nombre"),
							item.get("apellidos"), item.get("email"), admin, item.get("registro"), item.get("login"), item.get("password"));

					usuarios.add(usuario);
				});
			}
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return usuarios;	
	}
}
