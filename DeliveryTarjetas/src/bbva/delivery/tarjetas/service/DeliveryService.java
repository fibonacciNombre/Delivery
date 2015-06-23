package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException; 
import java.util.List;

import org.json.simple.JSONObject;
 
import bbva.delivery.tarjetas.bean.Delivery;  

public interface DeliveryService {

	public void test();

	public JSONObject cargarExcelDelivery(String fileName) throws FileNotFoundException;
	public List<Delivery> lstDelivery(Delivery param);
	 
	 
	public Integer valCourierDelivery(String dnicourier);
}