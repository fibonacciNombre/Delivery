package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException; 
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.Delivery;  

public interface DeliveryService {

	public void test();

	public JSONObject cargarExcelDelivery(Archivo archivo) throws FileNotFoundException;
	public List<Delivery> lstDelivery(Delivery param);
	 
	 
	public Integer valCourierDelivery(String dnicourier);
	
	public void mntDelivery(Delivery delivery);
	
	
	public void cargaDelivery(Delivery param);
	
	public void cargarArchivoDelivery(Archivo param);
	
	public JSONObject cargarExcelDelivery2(MultipartFile multipartFile,Archivo archivo) throws FileNotFoundException;
}