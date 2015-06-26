package bbva.delivery.tarjetas.courier.service.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired 
	private CourierDao courierDao;

	@Override
	public void mntCourier(Courier courier) {
		 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		courier.setHistorial("Usuario: " + courier.getUsuario() + ", Fecha:" + dateFormat.format(date) + ", " +  courier.toString()); 
		courierDao.mntCourier(courier);
	}

	@Override
	public List<Courier> lstCouriers(Courier courier) {
		logger.info("Service lstCourier");
		return courierDao.lstCouriers(courier);
	}

	@Override
	public List<Courier>  obtCourier(Courier courier) {
		logger.info("Service obtDetalleCourier");
		return courierDao.obtCourier(courier);
	}
}