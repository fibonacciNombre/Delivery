package bbva.delivery.tarjetas.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.ArchivoPDF;
import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface DeliveryDao{
	
	void mntArchivo(Archivo param);
	
	void mntDelivery(Delivery param);
	
	List<Delivery> lstDelivery(Delivery delivery, Tercero tercero);
	
	BigDecimal crearGrupoCargaDelivery();
		
	Integer valCourierDelivery(String dnicourier);
	
	List<ArchivoPDF> getArchivoPDF( ArchivoPDF archivoPDF);
}
