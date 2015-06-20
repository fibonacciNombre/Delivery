package bbva.delivery.tarjetas.courier.dao;

import java.util.List;

import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

public interface CourierDao {

	void regCourier(Courier courier);
	
	void mntCourier(Courier courier);
	
	List<Courier> lstCouriers(String estado);
	
	Courier obtDetalleCourier(Courier courier);
	
	List<Tercero> lstTercerosxCourier(Courier courier);
}