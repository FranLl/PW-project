package pw.p3.business.review;

/**
 * A class that represent a review
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class CriticaDTO {
	private int idCritica, puntuacion, usuario, espectaculo;
	private float media;
	private String titulo, descripcion;
	
	public CriticaDTO(int idCritica, String titulo, String descripcion, int puntuacion, int usuario, int espectaculo) {
		this.setID(idCritica);
		this.setTitulo(titulo);
		this.setDescripcion(descripcion);
		this.setPuntuacion(puntuacion);
		this.setUsuario(usuario);
		this.setEspectaculo(espectaculo);
	}
	
	/*
	 * Overloaded constructor with media parameter
	 */
	public CriticaDTO(int idCritica, String titulo, String descripcion, int puntuacion, int usuario, int espectaculo, float media) {
		this.setID(idCritica);
		this.setTitulo(titulo);
		this.setDescripcion(descripcion);
		this.setPuntuacion(puntuacion);
		this.setUsuario(usuario);
		this.setEspectaculo(espectaculo);
		this.setMedia(media);
	}
	
	public String getDescripcion() {return descripcion;}
	
	public int getEspectaculo() {return espectaculo;}
	
	public int getID() {return idCritica;}
	
	public int getPuntuacion() {return puntuacion;}
	
	public String getTitulo() {return titulo;}

	public int getUsuario() {return usuario;}
	
	public float getMedia() {return media;}
	
	public void setTitulo(String titulo) {this.titulo = titulo;}
	
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	
	public void setID(int idCritica) {this.idCritica = idCritica;}
	
	public void setEspectaculo(int espectaculo) {this.espectaculo = espectaculo;}
	
	public void setPuntuacion(int puntuacion) {this.puntuacion = puntuacion;}
	
	public void setUsuario(int usuario) {this.usuario = usuario;}
	
	public void setMedia(float media) {this.media = media;}
}
