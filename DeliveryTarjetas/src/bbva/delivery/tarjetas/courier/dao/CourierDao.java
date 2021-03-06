package bbva.delivery.tarjetas.courier.dao;

import java.util.List;

import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface CourierDao {
	
	void mntCourier(Courier courier);
	
	List<Courier> lstCouriers(Courier param);
	
	List<Courier>  obtCourier(Courier courier);
	
  

	public Integer obtTipoarchXExt(String extension);
	 
	public Integer obtCourierXCodbbva(String codbbva);

	List<Tercero> lstTercerosxCourier(Tercero tercero);
	  
}
