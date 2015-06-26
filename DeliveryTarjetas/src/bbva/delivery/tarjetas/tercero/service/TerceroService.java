package bbva.delivery.tarjetas.tercero.service;

import java.util.List;

import bbva.delivery.tarjetas.tercero.bean.Tercero;


public interface TerceroService {
	
	void mntTercero(Tercero tercero);
	
	List<Tercero> lstTerceros(Tercero tercero);
	
}
