package bbva.delivery.tarjetas.dao;

import java.math.BigDecimal;
import java.util.List;
 
import bbva.delivery.tarjetas.bean.*; 

public interface DeliveryDao{
	 
	void mntDelivery(Delivery param);
	
	List<Delivery> lstDelivery(Delivery delivery);
	
	BigDecimal crearGrupoCargaDelivery();
		
	Integer valCourierDelivery(String dnicourier);
	
	void mntArchivo(Archivo param);

	public List<ArchivoPDF> getArchivoPDF( ArchivoPDF archivoPDF);
}
