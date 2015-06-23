package bbva.delivery.tarjetas.courier.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.json.simple.JSONObject;

import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.courier.bean.Courier;

public interface CourierService {

	
	void mntCourier(Courier courier);
	
	List<Courier> lstCouriers(Courier courier);
	
	List<Courier>  obtCourier(Courier courier);
 
}
