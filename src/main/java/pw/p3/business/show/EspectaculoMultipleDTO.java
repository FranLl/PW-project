package pw.p3.business.show;

import pw.p3.data.dao.common.Categoria;
import pw.p3.data.dao.common.TipoEspectaculo;
/**
 * A class that represent a multiple show
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */
public class EspectaculoMultipleDTO extends EspectaculoDTO{
	public EspectaculoMultipleDTO(int idEspectaculo, String titulo, String descripcion, int localidades, Categoria categoria) {
		super(idEspectaculo, titulo, descripcion, localidades, categoria, TipoEspectaculo.multiple);
	}
}

