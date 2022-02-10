package pw.p3.data.dao.common;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

/**
 * A class that manages the connection to the data base and changes made on it
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class DBConnection {
	protected Connection connection = null;
	private Properties sqlProp;
	
	/**
	 * Constructor
	 */
	public DBConnection(Properties path) {
		sqlProp = new Properties();
		sqlProp=path;
	}
	
	/**
	 * Retrieves a new connection to the data base
	 * @return Connection to the data base
	 */
	public Connection getConnection(String dbServidor, String dbUsuario, String dbPass, String dbName){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(dbServidor + dbName, 
					dbUsuario, dbPass);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this.connection;
	}
	
	/**
	 * Creates a new user in the data base
	 * @param idUsuario
	 * @param nick
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param isAdmin
	 * @param registro, date when the user was registered
	 * @param login, last time user logged in
	 * @return 1 on success
	 */
	public int createUser(int idUsuario, String nick, String nombre, String apellidos, 
			String email, int isAdmin, String registro, String login, Connection con){
		int status=0;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("createUser"));
			ps.setString(1, nick);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, email);
			ps.setInt(5, isAdmin);
			ps.setString(6, registro);
			ps.setString(7, login);
			status = ps.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
		return status;
	}
	
	/**
	 * Creates a new password to a new user in the data base
	 * @param idPass
	 * @param pass
	 * @param idUsuario
	 * @return 1 on success
	 */
	public int createPassword(int idPass, String password, int idUsuario, Connection con){
		int status=0;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("createPassword"));
			ps.setString(1, password);
			ps.setInt(2, idUsuario);
			status = ps.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
		return status;
	}
	
	/**
	 * Creates a new show
	 * @param idEspectaculo
	 * @param nombre
	 * @param descripcion
	 * @param localidades
	 * @param tipoEspectaculo
	 * @param caregoria
	 * @param con
	 * @return 1 on success
	 */
	public int createEspectaculo(int idEspectaculo, String titulo, String descripcion, int localidades, String tipoEspectaculo, String categoria, Connection con){
		int status=0;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("createEsp"));
			ps.setString(1, titulo);
			ps.setString(2, descripcion);
			ps.setInt(3, localidades);
			ps.setString(4, tipoEspectaculo);
			ps.setString(5, categoria);
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	/**
	 * Creates a new review
	 * @param idCritica
	 * @param titulo
	 * @param descripcion
	 * @param puntuacion
	 * @param usuario
	 * @param espectaculo
	 * @param con
	 * @return 1 on success
	 */
	public int createCritica(int idCritica, String titulo, String descripcion, int puntuacion, int usuario, int espectaculo, Connection con){
		int status=0;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("createCrit"));
			ps.setString(1, titulo);
			ps.setString(2, descripcion);
			ps.setInt(3, puntuacion);
			ps.setInt(4, usuario);
			ps.setInt(5, espectaculo);
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	/**
	 * Creates a new session
	 * @param idSesion
	 * @param fecha
	 * @param locVendidas
	 * @param espectaculo
	 * @param con
	 * @return 1 on success
	 */
	public int createSesion(int idSesion, String fecha, int locVendidas, int espectaculo, Connection con){
		int status=0;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("createSes"));
			ps.setString(1, fecha);
			ps.setInt(2, locVendidas);
			ps.setInt(3, espectaculo);
			status = ps.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
		return status;
	}
	
	/**
	 * Creates a new rate
	 * @param idValoracion
	 * @param valoracion
	 * @param usuario
	 * @param critica
	 * @param con
	 * @return 1 on success
	 */
	public int createValoracion(int idValoracion, int valoracion, int usuario, int critica, Connection con){
		int status=0;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("createVal"));
			ps.setInt(1, valoracion);
			ps.setInt(2, usuario);
			ps.setInt(3, critica);
			status = ps.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
		return status;
	}
	
	/**
	 * Removes an user from the data base
	 * @param idUsuario
	 * @param con
	 * @return 1 on success
	 */
	public int deleteUser(int idUsuario, Connection con){
		int status=0;
		try {
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("deleteUser"));
			ps.setInt(1, idUsuario);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	/**
	 * Removes a show from the data base
	 * @param idEspectaculo
	 * @param con
	 * @return 1 on success
	 */
	public int deleteEspectaculo(int idEspectaculo, Connection con){
		int status=0;
		try {
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("deleteEsp"));
			ps.setInt(1, idEspectaculo);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	/**
	 * Removes a review from the data base
	 * @param idCritica
	 * @param con
	 * @return 1 on success
	 */
	public int deleteCritica(int idCritica, Connection con){
		int status=0;
		try {
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("deleteCrit"));
			ps.setInt(1, idCritica);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	/**
	 * Removes a session from the data base
	 * @param idSesion
	 * @param con
	 * @return 1 on success
	 */
	public int deleteSesion(int idSesion, Connection con){
		int status=0;
		try {
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("deleteSes"));
			ps.setInt(1, idSesion);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	/**
	 * Removes a rate from the data base
	 * @param idValoracion
	 * @param con
	 * @return 1 on success
	 */
	public int deleteValoracion(int idValoracion, Connection con){
		int status=0;
		try {
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("deleteVal"));
			ps.setInt(1, idValoracion);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	/**
	 * Removes all the rates from the data base
	 * @param idValoracion
	 * @param con
	 * @return 1 on success
	 */
	public int deleteValoraciones(int critica, Connection con){
		int status=0;
		try {
			PreparedStatement ps=
					con.prepareStatement(sqlProp.getProperty("deleteValByCritica"));
			ps.setInt(1, critica);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	/**
	 * Removes all thesession from the data base
	 * @param espectaculo
	 * @param con
	 * @return 1 on success
	 */
	public int deleteSesionByEspectaculo(int espectaculo, Connection con){
		int status=0;
		try {
			PreparedStatement ps=
					con.prepareStatement(sqlProp.getProperty("deleteSesByEsp"));
			ps.setInt(1, espectaculo);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	/**
	 * Updates user info
	 * @param idUsuario
	 * @param nick
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param registro, date when the user was registered
	 * @param login, last time user logged in 
	 * @param con
	 * @return 1 on success
	 */
	public int updateUsuario(int idUsuario, String nick, String nombre, 
			String apellidos, String email, int isAdmin, String registro, String login, Connection con){
		int status=0;
		try{
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("updateUser"));
			ps.setString(1, nick);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, email);
			ps.setInt(5, isAdmin);
			ps.setString(6, registro);
			ps.setString(7, login);
			ps.setInt(8, idUsuario);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * Updates user password
	 * @param password
	 * @param idUsuario 
	 * @param con
	 * @return 1 on success
	 */
	public int updatePassword(String password, int idUsuario, Connection con){
		int status=0;
		try{
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("updatePassword"));
			ps.setString(1, password);
			ps.setInt(2, idUsuario);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * Updates show info
	 * @param idEspectaculo
	 * @param nombre
	 * @param localidades
	 * @param tipoEspectaculo
	 * @param categoria
	 * @param con
	 * @return 1 on success
	 */
	public int updateEspectaculo(int idEspectaculo, String titulo, String descripcion, int localidades, String tipoEspectaculo, String categoria, Connection con){
		int status=0;
		try{
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("updateEsp"));
			ps.setString(1, titulo);
			ps.setString(2, descripcion);
			ps.setInt(3, localidades);
			ps.setString(4, tipoEspectaculo);
			ps.setString(5, categoria);
			ps.setInt(6, idEspectaculo);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * Updates review info
	 * @param idCritica
	 * @param titulo
	 * @param descripcion
	 * @param puntuacion
	 * @param usuario
	 * @param espectaculo
	 * @param con
	 * @return 1 on success
	 */
	public int updateCritica(int idCritica, String titulo, String descripcion, int puntuacion, int usuario, int espectaculo, Connection con){
		int status=0;
		try{
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("updateCrit"));
			ps.setString(1, titulo);
			ps.setString(2, descripcion);
			ps.setInt(3, puntuacion);
			ps.setInt(4, usuario);
			ps.setInt(5, espectaculo);
			ps.setInt(6, idCritica);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * Updates session info
	 * @param idSesion
	 * @param fecha
	 * @param locVendidas
	 * @param espectaculo
	 * @param con
	 * @return 1 on success
	 */
	public int updateSesion(int idSesion, String fecha, int locVendidas, int espectaculo, Connection con){
		int status=0;
		try{
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("updateSes"));
			ps.setString(1, fecha);
			ps.setInt(2, locVendidas);
			ps.setInt(3, espectaculo);
			ps.setInt(4, idSesion);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * Updates rate info
	 * @param idValoracion
	 * @param valoracion
	 * @param usuario
	 * @param critica
	 * @param con
	 * @return 1 on success
	 */
	public int updateValoracion(int idValoracion, int valoracion, int usuario, int critica, Connection con){
		int status=0;
		try{
			PreparedStatement ps=con.prepareStatement(sqlProp.getProperty("updateVal"));
			ps.setInt(1, valoracion);
			ps.setInt(2, usuario);
			ps.setInt(3, critica);
			ps.setInt(4, idValoracion);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * Gets the user info from the data base given its nick
	 * @param nick
	 * @param con
	 * @return Hashtable<String,String> with the info of the user
	 */
	public Hashtable<String,String> usuarioByNick (String nick, Connection con) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("usuarioByNick") + "'" + nick + "'");
			
			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");
				int isAdmin = rs.getInt("isAdmin");
				String registro = rs.getString("registro");
				String login = rs.getString("login");
				
				result = new Hashtable<String,String>();
				result.put("idUsuario", Integer.toString(idUsuario));
				result.put("nick", nick);
				result.put("nombre", nombre);
				result.put("apellidos", apellidos);
				result.put("email", email);
				result.put("isAdmin", Integer.toString(isAdmin));
				result.put("registro", registro);
				result.put("login", login);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets the user info from the data base given its email
	 * @param email
	 * @param con
	 * @return Hashtable<String,String> with the info of the user
	 */
	public Hashtable<String,String> usuarioByEmail (String email, Connection con) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("usuarioByEmail") + "'" + email + "'");
			
			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String nick = rs.getString("nick");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				int isAdmin = rs.getInt("isAdmin");
				String registro = rs.getString("registro");
				String login = rs.getString("login");
				
				result = new Hashtable<String,String>();
				result.put("idUsuario", Integer.toString(idUsuario));
				result.put("nick", nick);
				result.put("nombre", nombre);
				result.put("apellidos", apellidos);
				result.put("email", email);
				result.put("isAdmin", Integer.toString(isAdmin));
				result.put("registro", registro);
				result.put("login", login);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets the user info from the data base given its id
	 * @param idUsuario
	 * @param con
	 * @return Hashtable<String,String> with the info of the user
	 */
	public Hashtable<String,String> usuarioById (int idUsuario, Connection con) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("usuarioById") + idUsuario);
			
			while (rs.next()) {
				String nick = rs.getString("nick");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");
				int isAdmin = rs.getInt("isAdmin");
				String registro = rs.getString("registro");
				String login = rs.getString("login");
				
				result = new Hashtable<String,String>();
				result.put("idUsuario", Integer.toString(idUsuario));
				result.put("nick", nick);
				result.put("nombre", nombre);
				result.put("apellidos", apellidos);
				result.put("email", email);
				result.put("isAdmin", Integer.toString(isAdmin));
				result.put("registro", registro);
				result.put("login", login);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets the user password from the data base given its id
	 * @param idUsuario
	 * @param con
	 * @return Hashtable<String,String> with the password of the user
	 */
	public Hashtable<String,String> passwordByID (int idUsuario, Connection con) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("passwordById") + idUsuario);
			
			while (rs.next()) {
				String password = rs.getString("pass");
				
				result = new Hashtable<String,String>();
				result.put("password", password);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets the show info from the data base given its id
	 * @param idEspectaculo
	 * @param con
	 * @return Hashtable<String,String> with the info of the show
	 */
	public Hashtable<String,String> espectaculoById (int idEspectaculo, Connection con) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("espectaculoById") + idEspectaculo);
			
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				int localidades = rs.getInt("localidades");
				String tipoEspectaculo = rs.getString("tipoEspectaculo");
				String categoria = rs.getString("categoria");
				
				result = new Hashtable<String,String>();
				result.put("descripcion", descripcion);
				result.put("categoria", categoria);
				result.put("idEspectaculo", Integer.toString(idEspectaculo));
				result.put("titulo", titulo);
				result.put("localidades", Integer.toString(localidades));
				result.put("tipoEspectaculo", tipoEspectaculo);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets the show info from the data base given its title
	 * @param titulo
	 * @param con
	 * @return Hashtable<String,String> with the info of the show
	 */
	public Hashtable<String,String> espectaculoByTitulo (String titulo, Connection con) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("espectaculoByNombre") + "'" + titulo + "'" );
			
			while (rs.next()) {
				int idEspectaculo = rs.getInt("idEspectaculo");
				String descripcion = rs.getString("descripcion");
				int localidades = rs.getInt("localidades");
				String tipoEspectaculo = rs.getString("tipoEspectaculo");
				String categoria = rs.getString("categoria");
				
				titulo = titulo.toLowerCase();
				titulo = titulo.substring(0, 1).toUpperCase() + titulo.substring(1);
				
				result = new Hashtable<String,String>();
				result.put("descripcion", descripcion);
				result.put("categoria", categoria);
				result.put("idEspectaculo", Integer.toString(idEspectaculo));
				result.put("titulo", titulo);
				result.put("localidades", Integer.toString(localidades));
				result.put("tipoEspectaculo", tipoEspectaculo);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets the shows info of a given category from the data base
	 * @param categoria
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the show
	 */

	public ArrayList<Hashtable<String,String>> espectaculosByCategoria (String categoria, Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> espectaculoMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("espectaculosByCategoria") + "'"  + categoria + "'" );
			
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				int idEspectaculo = rs.getInt("idEspectaculo");
				int localidades = rs.getInt("localidades");
				String tipoEspectaculo = rs.getString("tipoEspectaculo");
				
				espectaculoMap = new Hashtable<String,String>();
				espectaculoMap.put("descripcion", descripcion);
				espectaculoMap.put("categoria", categoria);
				espectaculoMap.put("idEspectaculo", Integer.toString(idEspectaculo));
				espectaculoMap.put("titulo", titulo);
				espectaculoMap.put("localidades", Integer.toString(localidades));
				espectaculoMap.put("tipoEspectaculo", tipoEspectaculo);
				
				result.add(espectaculoMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets the info of all the users registered in the data base
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the user
	 */
	public ArrayList<Hashtable<String,String>> usuariosGetAll (Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> usuarioMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("usuariosGetAll"));
			
			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String nick = rs.getString("nick");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");
				int isAdmin = rs.getInt("isAdmin");
				String registro = rs.getString("registro");
				String login = rs.getString("login");

				usuarioMap = new Hashtable<String, String>();
				usuarioMap.put("idUsuario", Integer.toString(idUsuario));
				usuarioMap.put("nick", nick);
				usuarioMap.put("nombre", nombre);
				usuarioMap.put("apellidos", apellidos);
				usuarioMap.put("email", email);
				usuarioMap.put("isAdmin", Integer.toString(isAdmin));
				usuarioMap.put("registro", registro);
				usuarioMap.put("login", login);

				
				result.add(usuarioMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	
	/**
	 * Gets all the shows info from the data base
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the show
	 */
	public ArrayList<Hashtable<String,String>> espectaculosGetAll (Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> espectaculoMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("espectaculosAll"));
			
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				int idEspectaculo = rs.getInt("idEspectaculo");
				int localidades = rs.getInt("localidades");
				String categoria = rs.getString("categoria");
				String tipoEspectaculo = rs.getString("tipoEspectaculo");
				
				espectaculoMap = new Hashtable<String,String>();
				espectaculoMap.put("descripcion", descripcion);
				espectaculoMap.put("categoria", categoria);
				espectaculoMap.put("idEspectaculo", Integer.toString(idEspectaculo));
				espectaculoMap.put("titulo", titulo);
				espectaculoMap.put("localidades", Integer.toString(localidades));
				espectaculoMap.put("tipoEspectaculo", tipoEspectaculo);
				
				result.add(espectaculoMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}
	
	/**
	 * Gets all the reviews info from the data base given their author
	 * @param usuario
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the reviews
	 */
	public ArrayList<Hashtable<String,String>> criticasByUsuario (int usuario, Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> criticaMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("criticasByUsuario") + usuario);
			
			while (rs.next()) {
				int idCritica = rs.getInt("idCritica");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				int puntuacion = rs.getInt("puntuacion");
				int espectaculo = rs.getInt("espectaculo");
				
				criticaMap = new Hashtable<String,String>();
				criticaMap.put("idCritica", Integer.toString(idCritica));
				criticaMap.put("titulo", titulo);
				criticaMap.put("descripcion", descripcion);
				criticaMap.put("puntuacion", Integer.toString(puntuacion));
				criticaMap.put("espectaculo", Integer.toString(espectaculo));
				criticaMap.put("usuario", Integer.toString(usuario));
				
				result.add(criticaMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return result;
	}

	/**
	 * Gets all the reviews info from the data base
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of all the reviews
	 */

	public ArrayList<Hashtable<String,String>> criticasGetAll (Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> criticaMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("criticasGetAll"));
			
			while (rs.next()) {
				int idCritica = rs.getInt("idCritica");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				int puntuacion = rs.getInt("puntuacion");
				int usuario = rs.getInt("usuario");
				int espectaculo = rs.getInt("espectaculo");
				
				criticaMap = new Hashtable<String,String>();
				criticaMap.put("idCritica", Integer.toString(idCritica));
				criticaMap.put("titulo", titulo);
				criticaMap.put("descripcion", descripcion);
				criticaMap.put("puntuacion", Integer.toString(puntuacion));
				criticaMap.put("espectaculo", Integer.toString(espectaculo));
				criticaMap.put("usuario", Integer.toString(usuario));
				
				result.add(criticaMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return result;
	}
	
	/**
	 * Gets all the reviews info from the data base given their show
	 * @param espectaculo
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the reviews
	 */
	public ArrayList<Hashtable<String,String>> criticasByEspectaculo (int espectaculo, Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> criticaMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("criticasByEspectaculo") + espectaculo);
			
			while (rs.next()) {
				int idCritica = rs.getInt("idCritica");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				int puntuacion = rs.getInt("puntuacion");
				int usuario = rs.getInt("usuario");
				
				criticaMap = new Hashtable<String,String>();
				criticaMap.put("idCritica", Integer.toString(idCritica));
				criticaMap.put("titulo", titulo);
				criticaMap.put("descripcion", descripcion);
				criticaMap.put("puntuacion", Integer.toString(puntuacion));
				criticaMap.put("espectaculo", Integer.toString(espectaculo));
				criticaMap.put("usuario", Integer.toString(usuario));
				
				result.add(criticaMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}
	
	/**
	 * Gets the sessions info from the data base given their show
	 * @param espectaculo
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the sessions
	 */
	public ArrayList<Hashtable<String,String>> sesionesByEspectaculo (int espectaculo, Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> sesioneMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("sesionesByEspectaculo") + espectaculo);
			
			while (rs.next()) {
				int idSesion = rs.getInt("idSesion");
				String fecha = rs.getString("fecha");
				int locVendidas = rs.getInt("locVendidas");
				
				sesioneMap = new Hashtable<String,String>();
				sesioneMap.put("idSesion", Integer.toString(idSesion));
				sesioneMap.put("fecha", fecha);
				sesioneMap.put("espectaculo", Integer.toString(espectaculo));
				sesioneMap.put("locVendidas", Integer.toString(locVendidas));
				
				result.add(sesioneMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}
	
	/**
	 * Gets all the rates info from the data base given their review
	 * @param critica
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the rates
	 */
	public ArrayList<Hashtable<String,String>> valoracionesByCritica (int critica, Connection con) {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> valoracionMap = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("valoracionesByCritica") + critica);
			
			while (rs.next()) {
				int idValoracion = rs.getInt("idValoracion");
				int valoracion = rs.getInt("valoracion");
				int usuario = rs.getInt("usuario");
				
				valoracionMap = new Hashtable<String,String>();
				valoracionMap.put("idValoracion", Integer.toString(idValoracion));
				valoracionMap.put("valoracion", Integer.toString(valoracion));
				valoracionMap.put("critica", Integer.toString(critica));
				valoracionMap.put("usuario", Integer.toString(usuario));
				
				result.add(valoracionMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}
	
	/**
	 * Checks if there is already an user in the data base with the given nick 
	 * @param nick
	 * @param con
	 * @return 1 on success
	 */
	public int countUserNick (String nick, Connection con) {
		Statement stmt = null;
		int result=0;
		try{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("countUserNick") + "'" + nick + "'" );
			if ( rs.next() ) 
			    result = rs.getInt(1);
			
		}catch(Exception e){System.out.println(e);}
		return result;
	}
	
	/**
	 * Checks if there is already an user in the data base with the given email 
	 * @param email
	 * @param con
	 * @return 1 on success
	 */
	public int countUserEmail (String email, Connection con) {
		Statement stmt = null;
		int result=0;
		try{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("countUserEmail") + "'" + email + "'" );
			if ( rs.next() ) 
			    result = rs.getInt(1);
			
		}catch(Exception e){System.out.println(e);}
		return result;
	}
	
	/**
	 * Checks if there is already a review in the data base with the given show and user
	 * @param idUsuario
	 * @param idEspectaculo
	 * @param con
	 * @return 1 on success
	 */
	public boolean countUserReview (int idUsuario, int idEspectaculo, Connection con) {
		boolean result_int = false;
		ResultSet result = null;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("countUserReview"));
			ps.setInt(1, idUsuario);
			ps.setInt(2, idEspectaculo);
			result = ps.executeQuery();
			
			if (result.next() )
				result_int = ( Integer.parseInt(result.getString("count(*)"))  > 0 );
		} catch(Exception e) { System.out.println(e); }
		
		return result_int;
	}
	
	/**
	 * Checks if there is already a rate in the data base with the given review and user
	 * @param idUsuario
	 * @param idCritca
	 * @param con
	 * @return 1 on success
	 */
	public boolean countReviewRated(int idUsuario, int idCritica, Connection con) {
		boolean result_int = false;
		ResultSet result = null;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("countReviewRated"));
			ps.setInt(1, idUsuario);
			ps.setInt(2, idCritica);
			result = ps.executeQuery();
			
			if (result.next() )
				result_int = ( Integer.parseInt(result.getString("count(*)"))  > 0 );
		} catch(Exception e) { System.out.println(e); }
		
		return result_int;
	}
	
	/**
	 * Get average rate of given review
	 * @param idCritica
	 * @param con
	 * @return average
	 */
	public float averageReviewRate(int idCritica, Connection con) {
		float average = 0;
		ResultSet result = null;
		try{
			PreparedStatement ps = con.prepareStatement(sqlProp.getProperty("averageReviewRate"));
			ps.setInt(1, idCritica);
			result = ps.executeQuery();
			
			if (result.next()  )
				if( result.getString("round(avg(valoracion),1)") != null)
					average = Float.parseFloat(result.getString("round(avg(valoracion),1)"));
		} catch(Exception e) { System.out.println(e); }
		
		return average;
	}
	/**
	 *  Closes connection
	 */
	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
