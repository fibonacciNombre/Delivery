package bbva.delivery.tarjetas.tercero.dao;

import java.util.List;

import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface TerceroDao {

	void regTercero(Tercero tercero);
	
	void mntTercero(Tercero tercero);
	
	List<Tercero> lstTerceros(String estado);
	
	Tercero obtDetalleTercero(Tercero tercero);
	
}
