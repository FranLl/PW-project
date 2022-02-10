package pw.p3.display.javabean;

import java.io.Serializable;
import java.util.ArrayList;

import pw.p3.business.show.EspectaculoDTO;

/**
 * Bean used for saving all the users from the data base
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class UtilsEspectadorBean implements Serializable{
	
	private static final long serialVersionUID = 2L;

	private ArrayList<EspectaculoDTO> espectaculos;
	
	public void setEspectaculos(ArrayList<EspectaculoDTO> espectaculos) {
		this.espectaculos = espectaculos;
	}
	
	public ArrayList<EspectaculoDTO> getEspectaculos() {
		return espectaculos;
	}

}
