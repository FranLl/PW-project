package pw.p3.business.factory;

import pw.p3.business.show.EspectaculoMultipleDTO;
import pw.p3.business.show.EspectaculoPuntualDTO;
import pw.p3.business.show.EspectaculoTemporadaDTO;
import pw.p3.data.dao.common.Categoria;

/**
 * Concrete factory for shows
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */
public class CreadorEspConcreto extends CreadorEspectaculo {
	
	/**
	 * Creates a new single show
	 * @param id
	 * @param titulo
	 * @param descripcion
	 * @param locVenta
	 * @param categoria
	 * @return EspectaculoPuntualDTO
	 */
	@Override
	public EspectaculoPuntualDTO crearEspPuntual(int id, String titulo, String descripcion,
		int locVenta, Categoria categoria) {
		return new EspectaculoPuntualDTO(id, titulo, descripcion, locVenta, categoria);
	};
	
	/**
	 * Creates a new multiple show
	 * @param id
	 * @param titulo
	 * @param descripcion
	 * @param locVenta
	 * @param categoria
	 * @return EspectaculoMultipleDTO
	 */

	@Override
	public EspectaculoMultipleDTO crearEspMultiple(int id, String titulo, String descripcion,
		int locVenta, Categoria categoria) {
		return new EspectaculoMultipleDTO(id, titulo, descripcion, locVenta, categoria);
	};
	
	/**
	 * Creates a new season show
	 * @param id
	 * @param titulo
	 * @param descripcion
	 * @param locVenta
	 * @param categoria
	 * @return EspectaculoTemporadaDTO
	 */

	@Override
	public EspectaculoTemporadaDTO crearEspTemporada(int id, String titulo, String descripcion,
		int locVenta, Categoria categoria) {
		return new EspectaculoTemporadaDTO(id, titulo, descripcion, locVenta, categoria);
	}
		
}
