package bbva.delivery.tarjetas.courier.service;
 
import java.util.List;
 
 




import bbva.delivery.tarjetas.courier.bean.Courier;

public interface CourierService {

	
	void mntCourier(Courier courier);
	
	List<Courier> lstCouriers(Courier courier);
	
	List<Courier>  obtCourier(Courier courier);
 
	public Integer obtTipoarchXExt(String extension);
	 
	
	public Integer obtCourierXCodbbva(String codbbva);
}
