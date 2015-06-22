package bbva.delivery.tarjetas.tercero.dao;

import java.util.List;
 
import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface TerceroDao {

void mntTercero(Tercero tercero);
	
	List<Tercero> lstTerceros(Tercero tercero);
	
	List<Tercero>  obtTercero(Tercero tercero);
	
	
}
