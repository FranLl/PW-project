package pw.p3.business.show;

import pw.p3.data.dao.common.Categoria;
import pw.p3.data.dao.common.TipoEspectaculo;

/**
 * A class that represent a show
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */
public class EspectaculoDTO {
	int idEspectaculo, localidades;
	String titulo, descripcion;
	Categoria categoria;
	TipoEspectaculo tipo;
	
	public EspectaculoDTO(int idEspectaculo, String titulo, String descripcion, 
			int localidades, Categoria categoria, TipoEspectaculo tipo) {
		this.setCategoria(categoria);
		this.setDescripcion(descripcion);
		this.setID(idEspectaculo);
		this.setLocalidades(localidades);
		this.setTitulo(titulo);
		this.setTipo(tipo);
	}
	
	public Categoria getCategoria() {return categoria;}
	
	public TipoEspectaculo getTipo() {return tipo;}
	
	public String getDescripcion() {return descripcion;}
	
	public int getID() {return idEspectaculo;}
	
	public int getLocalidades() {return localidades;}
	
	public String getTitulo() {return titulo;}
	
	public void setCategoria(Categoria categoria) {this.categoria = categoria;}
	
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	
	public void setID(int idEspectaculo) {this.idEspectaculo = idEspectaculo;}
	
	public void setLocalidades(int localidades) {this.localidades = localidades;}
	
	public void setTitulo(String titulo) {this.titulo = titulo;}
	
	public void setTipo(TipoEspectaculo tipo) {this.tipo = tipo;}
}
