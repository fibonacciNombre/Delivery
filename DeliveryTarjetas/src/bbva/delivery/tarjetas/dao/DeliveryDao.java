package bbva.delivery.tarjetas.dao;

import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONObject;

import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.bean.Parametro;

public interface DeliveryDao{
	void test();
	
	 
	public void mntDelivery(Delivery param);
	public List<Delivery> lstDelivery(Delivery param);
	public BigDecimal crearGrupoCargaDelivery();
	
	public List<Parametro> lstParametro(Parametro param);
}
