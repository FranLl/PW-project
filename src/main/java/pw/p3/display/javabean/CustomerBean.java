package pw.p3.display.javabean;

import java.io.Serializable;

/**
 * Bean used for the logged user
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class CustomerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ID = -1;
	private String nick = "", nombre = "", apellidos = "", email = "", registro = "",
			login = "";
	private boolean admin = false;
	
	public boolean getAdmin() {return admin;}
	
	public String getApellidos() {return apellidos;}
	
	public String getRegistro() {return registro;}
	
	public String getLogin() {return login;}
	
	public String getEmail() {return email;}
	
	public int getID() {return ID;}
	
	public String getNick() {return nick;}
	
	public String getNombre() {return nombre;}
	
	public void setApellidos(String apellidos) {this.apellidos = apellidos;}
	
	public void setEmail(String email) {this.email = email;}
	
	public void setRegistro(String registro) {this.registro = registro;}
	
	public void setLogin(String login) {this.login = login;}
	
	public void setID(int idUsuario) {this.ID = idUsuario;}
	
	public void setNick(String nick) {this.nick = nick;}
	
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public void setAdmin(boolean isAdmin) {this.admin = isAdmin;}
}
