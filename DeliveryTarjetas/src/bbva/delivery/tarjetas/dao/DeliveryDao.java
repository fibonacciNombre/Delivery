package bbva.delivery.tarjetas.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.delivery.tarjetas.bean.CargaEntregaTarjetav1;

public interface DeliveryDao{
	void test();
	
	
	
	public void cargarEntregaTarjeta(CargaEntregaTarjetav1 param);
	public List<CargaEntregaTarjetav1> lstCargarEntregaTarjeta(CargaEntregaTarjetav1 param);
	public BigDecimal crearGrupoCarga();
}
