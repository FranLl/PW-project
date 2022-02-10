package pw.p3.business.rate;



/**
 * A class that represent a rate
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */
public class ValoracionDTO {
	private int idValoracion, valoracion, usuario, critica;
	
	public ValoracionDTO(int idValoracion, int valoracion, int usuario, int critica) {
		this.setID(idValoracion);
		this.setValoracion(valoracion);
		this.setUsuario(usuario);
		this.setCritica(critica);
	}
	
	public int getID() {return idValoracion;}
	
	public int getValoracion() {return valoracion;}
	
	public int getUsuario() {return usuario;}
	
	public int getCritica() {return critica;}
	
	public void setID(int idValoracion) {this.idValoracion = idValoracion;}
	
	public void setValoracion(int valoracion) {this.valoracion = valoracion;}
	
	public void setUsuario(int usuario) {this.usuario = usuario;}
	
	public void setCritica(int critica) {this.critica = critica;}
}
