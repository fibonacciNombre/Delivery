package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException; 
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.Delivery;  

public interface DeliveryService {

	public void test();
 
	public List<Delivery> lstDelivery(Delivery param);
	 
	 
	public Integer valCourierDelivery(String dnicourier);
	
	public void mntDelivery(Delivery delivery);
	
	 
	
	public void mntArchivo(Archivo param);
	public void exportarListaDelivery(Delivery delivery) throws IOException;
	public JSONObject cargarExcelDelivery(MultipartFile multipartFile,Archivo archivo) throws FileNotFoundException;
}