package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException; 
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.Delivery;  
import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface DeliveryService {

	List<Delivery> lstDelivery(Delivery param, Tercero tercero) throws IOException;
	 
	Integer valCourierDelivery(String dnicourier);
	
	void mntDelivery(Delivery delivery);
	
	void mntArchivo(Archivo param);
	 
	
	JSONObject cargarExcelDelivery(MultipartFile multipartFile,Archivo archivo) throws FileNotFoundException;

	String obtArchivoLstDelivery(Delivery delivery, Tercero tercero) throws IOException;
}