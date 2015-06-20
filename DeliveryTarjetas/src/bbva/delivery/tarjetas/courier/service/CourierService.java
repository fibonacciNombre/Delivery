package bbva.delivery.tarjetas.courier.service;

import java.util.List;

import bbva.delivery.tarjetas.courier.bean.Courier;

public interface CourierService {

	void regCourier(Courier courier);
	
	void mntCourier(Courier courier);
	
	List<Courier> lstCouriers(String estado);
	
	Courier obtDetalleCourier(Courier courier);
}
