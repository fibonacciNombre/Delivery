package bbva.delivery.tarjetas.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.delivery.tarjetas.bean.CargaEntregaTarjeta;

public interface DeliveryDao{
	void test();
	
	
	
	public void cargarEntregaTarjeta(CargaEntregaTarjeta param);
	public List<CargaEntregaTarjeta> lstCargarEntregaTarjeta(CargaEntregaTarjeta param);
	public BigDecimal crearGrupoCarga();
}
