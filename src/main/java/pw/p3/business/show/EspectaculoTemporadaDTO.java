package pw.p3.business.show;

import pw.p3.data.dao.common.Categoria;
import pw.p3.data.dao.common.TipoEspectaculo;

/**
 * A class that represent a season show
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 * @author Mario Berrios Carmona
 *
 */

public class EspectaculoTemporadaDTO extends EspectaculoDTO {
	public EspectaculoTemporadaDTO(int idEspectaculo, String titulo, String descripcion, int localidades, Categoria categoria) {
		super(idEspectaculo, titulo, descripcion, localidades, categoria, TipoEspectaculo.temporada);
	}
}
