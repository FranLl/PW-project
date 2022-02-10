package pw.p3.display.javabean;

import java.io.Serializable;
import java.util.ArrayList;

import pw.p3.business.session.SesionDTO;
import pw.p3.business.show.EspectaculoDTO;
import pw.p3.business.user.UsuarioDTO;

/**
 * Bean used for saving all the users from the data base
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class UtilsBean implements Serializable{
	
	private static final long serialVersionUID = 1221323L;

	private ArrayList<UsuarioDTO> usuarios;
	private ArrayList<EspectaculoDTO> espectaculos;
	private ArrayList<SesionDTO> sesiones;
	
	private int idEspectaculoAdd = -1;
	private SesionDTO sesionModificar = null;
	
	public void setUsuarios(ArrayList<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}
	
	public ArrayList<UsuarioDTO> getUsuarios() {
		return usuarios;
	}
	
	public void setEspectaculos(ArrayList<EspectaculoDTO> espectaculos) {
		this.espectaculos = espectaculos;
	}
	
	public ArrayList<EspectaculoDTO> getEspectaculos() {
		return espectaculos;
	}
	
	public void setSesiones(ArrayList<SesionDTO> sesiones) {
		this.sesiones = sesiones;
	}
	
	public ArrayList<SesionDTO> getSesiones() {
		return sesiones;
	}
	
	public void setIdEspectaculoAdd(int idEspectaculoAdd) {
		this.idEspectaculoAdd = idEspectaculoAdd;
	}
	
	public int getIdEspectaculoAdd() {
		return idEspectaculoAdd;
	}
	
	public void setSesionModificar(SesionDTO sesionModificar) {
		this.sesionModificar = sesionModificar;
	}
	
	public SesionDTO getSesionModificar() {
		return sesionModificar;
	}

}
