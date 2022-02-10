package pw.p3.business.session;

/**
 * A class that represent a session
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */
public class SesionDTO {
	int idSesion, locVendidas, espectaculo;
	String fecha;
	
	public SesionDTO(int idSesion, String fecha, int locVendidas, int espectaculo) {
		this.setID(idSesion);
		this.setFecha(fecha);
		this.setLocVendidas(locVendidas);
		this.setEspectaculo(espectaculo);
	}
	
	public int getID() {return idSesion;}
	
	public int getLocVendidas() {return locVendidas;}
	
	public int getEspectaculo() {return espectaculo;}
	
	public String getFecha() {return fecha;}
	
	public void setID(int idSesion) {this.idSesion = idSesion;}
	
	public void setFecha(String fecha) {this.fecha = fecha;}
	
	public void setEspectaculo(int espectaculo) {this.espectaculo = espectaculo;}
	
	public void setLocVendidas(int locVendidas) {this.locVendidas = locVendidas;}
}
