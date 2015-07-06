package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException; 
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.ArchivoPDF;
import bbva.delivery.tarjetas.bean.Delivery;   

public interface DeliveryService {

	List<Delivery> lstDelivery(Delivery param) throws IOException;
	 
	Integer valCourierDelivery(String dnicourier);
	
	void mntDelivery(Delivery delivery);
	
	void mntArchivo(Archivo param);
	 
	
	JSONObject cargarExcelDelivery(MultipartFile multipartFile,Archivo archivo) throws FileNotFoundException;

	String obtArchivoLstDelivery(Delivery delivery) throws IOException;
	
	public ArchivoPDF getArchivoPDF(ArchivoPDF archivoPDF, String ruta) throws Exception;

		
}