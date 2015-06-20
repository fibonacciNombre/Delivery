package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException; 
import java.util.List;

import org.json.simple.JSONObject;

import bbva.delivery.tarjetas.bean.Delivery; 
import bbva.delivery.tarjetas.bean.Parametro;

public interface DeliveryService {

	public void test();

	public JSONObject cargarExcelDelivery(String fileName) throws FileNotFoundException;
	public List<Delivery> lstDelivery(Delivery param);
	
	public List<Parametro> lstParametro(Parametro param);
}