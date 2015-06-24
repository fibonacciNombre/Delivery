package bbva.delivery.tarjetas.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.delivery.tarjetas.bean.*;

public interface DeliveryDao{
	void test();
	
	 
	public void mntDelivery(Delivery param);
	public List<Delivery> lstDelivery(Delivery param);
	public BigDecimal crearGrupoCargaDelivery();
	
 
	
	public Integer valCourierDelivery(String dnicourier);
}
