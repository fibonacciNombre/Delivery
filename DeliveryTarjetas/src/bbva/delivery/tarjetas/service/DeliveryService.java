package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.ArchivoPDF;
import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface DeliveryService {

	void mntDelivery(Delivery delivery);
	
	void mntArchivo(Archivo param);
	
	List<Delivery> lstDelivery(Delivery param, Tercero tercero) throws IOException;
	
	List<Delivery> lstDelivery2(Delivery param, Tercero tercero) throws IOException;
	
	List<Delivery> lstDeliveryOficinas(Delivery param, Tercero tercero) throws IOException;
	
	List<Delivery> lstDeliveryOficinasByDni(Delivery param) throws IOException;
	 
	Integer valCourierDelivery(String dnicourier);
	
	JSONObject cargarExcelDelivery(MultipartFile multipartFile,Archivo archivo, Delivery deliveryTemp) throws FileNotFoundException;

	String obtArchivoLstDelivery(Delivery delivery, Tercero tercero) throws IOException;
	
	ArchivoPDF getArchivoPDF(ArchivoPDF archivoPDF, String ruta) throws Exception;
}
