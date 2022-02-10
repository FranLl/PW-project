package pw.p3.business.factory;

import pw.p3.business.show.EspectaculoMultipleDTO;
import pw.p3.business.show.EspectaculoPuntualDTO;
import pw.p3.business.show.EspectaculoTemporadaDTO;
import pw.p3.data.dao.common.Categoria;

/**
 * Abstract factory
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */
public abstract class CreadorEspectaculo {
	/**
	 * Header for single show creation
	 * @param id
	 * @param titulo
	 * @param categoria
	 * @param descripcion
	 * @param locVenta
	 * @return EspectaculoPuntualDTO
	 */
	public abstract EspectaculoPuntualDTO crearEspPuntual(int id, String titulo,
		String descripcion, int locVenta, Categoria categoria);
	
	/**
	 * Header for multiple show creation
	 * @param id
	 * @param titulo
	 * @param categoria
	 * @param descripcion
	 * @param locVenta
	 * @return EspectaculoMultipleDTO
	 */
	public abstract EspectaculoMultipleDTO crearEspMultiple(int id, String titulo,
		String descripcion, int locVenta, Categoria categoria);
	
	/**
	 * Header for season show creation
	 * @param id
	 * @param titulo
	 * @param categoria
	 * @param descripcion
	 * @param locVenta
	 * @return EspectaculoTemporadaDTO
	 */
	public abstract EspectaculoTemporadaDTO crearEspTemporada(int id, String titulo,
		String descripcion, int locVenta, Categoria categoria);
		
}
