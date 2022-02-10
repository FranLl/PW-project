package pw.p3.business.user;

/**
 * A class that represent an user
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class UsuarioDTO {
	
	private int idUsuario;
	private String nick, nombre, apellidos, email, registro, login, password;
	boolean isAdmin;
	
	public UsuarioDTO(int idUsuario, String nick, String nombre, String apellidos, 
			String email, boolean isAdmin, String registro, String login, String password) {
		this.setID(idUsuario);
		this.setNick(nick);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setEmail(email);
		this.setAdmin(isAdmin);
		this.setRegistro(registro);
		this.setLogin(login);
		this.setPassword(password);
	}
	
	public boolean getAdmin() {return isAdmin;}
	
	public String getApellidos() {return apellidos;}
	
	public String getRegistro() {return registro;}
	
	public String getLogin() {return login;}
	
	public String getEmail() {return email;}
	
	public int getID() {return idUsuario;}
	
	public String getNick() {return nick;}
	
	public String getNombre() {return nombre;}
	
	public String getPassword() {return password;}
	
	public void setApellidos(String apellidos) {this.apellidos = apellidos;}
	
	public void setEmail(String email) {this.email = email;}
	
	public void setRegistro(String registro) {this.registro = registro;}
	
	public void setLogin(String login) {this.login = login;}
	
	public void setID(int idUsuario) {this.idUsuario = idUsuario;}
	
	public void setNick(String nick) {this.nick = nick;}
	
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public void setAdmin(boolean isAdmin) {this.isAdmin = isAdmin;}
	
	public void setPassword(String password) {this.password = password;}
}
