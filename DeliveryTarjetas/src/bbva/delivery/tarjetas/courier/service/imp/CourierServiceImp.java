package bbva.delivery.tarjetas.courier.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.dao.CourierDao;
import bbva.delivery.tarjetas.courier.service.CourierService;

@Service("courierService")
@Transactional(propagation=Propagation.SUPPORTS)
public class CourierServiceImp implements CourierService {
	
	private static Logger logger 						= Logger.getLogger(CourierServiceImp.class.getName());

	private CourierDao courierDao;
	
	@Override
	public void regCourier(Courier courier) {
		logger.info("Service regCourier");
		courierDao.regCourier(courier);
	}

	@Override
	public void mntCourier(Courier courier) {
		logger.info("Service mntCourier");
		courierDao.mntCourier(courier);
	}

	@Override
	public List<Courier> lstCouriers(String estado) {
		logger.info("Service lstCourier");
		return courierDao.lstCouriers(estado);
	}

	@Override
	public Courier obtDetalleCourier(Courier courier) {
		logger.info("Service obtDetalleCourier");
		return courierDao.obtDetalleCourier(courier);
	}
}