package bbva.delivery.tarjetas.tercero.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface TerceroDao {

	List<Tercero> lstTerceros(Tercero tercero);
	
	void mntTercero(Tercero tercero);
	
	public BigDecimal obtTerceroXNrodoc(String nrodocumento);
		
}
