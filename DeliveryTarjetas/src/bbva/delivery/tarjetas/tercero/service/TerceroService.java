package bbva.delivery.tarjetas.tercero.service;

import java.math.BigDecimal;
import java.util.List;

import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface TerceroService {
	
	void mntTercero(Tercero tercero);
	
	List<Tercero> lstTerceros(Tercero tercero);
	
	public BigDecimal obtTerceroXNrodoc(String nrodocumento);
	

	public List<Tercero> obtTercero (Tercero tercero);
}
