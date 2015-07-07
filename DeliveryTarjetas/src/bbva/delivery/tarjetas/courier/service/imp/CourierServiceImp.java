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
import bbva.delivery.tarjetas.tercero.bean.Tercero;

@Service("courierService")
@Transactional(propagation=Propagation.SUPPORTS)
public class CourierServiceImp implements CourierService {
	
	private static Logger logger 						= Logger.getLogger(CourierServiceImp.class.getName());
	
	@Autowired 
	private CourierDao courierDao;

	@Override
	public void mntCourier(Courier courier) {
		 
		logger.info("SERVICE CourierServiceImp mntCourier");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		courier.setHistorial("Usuario: " + courier.getUsuario() + ", Fecha:" + dateFormat.format(date) + ", " +  courier.toString()); 
		courierDao.mntCourier(courier);
	}

	@Override
	public List<Courier> lstCouriers(Courier courier) {
		logger.info("SERVICE CourierServiceImp lstCouriers");
		return courierDao.lstCouriers(courier);
	}

	@Override
	public List<Courier>  obtCourier(Courier courier) {
		logger.info("SERVICE CourierServiceImp obtCourier");
		return courierDao.obtCourier(courier);
	}

	@Override 
	public Integer obtTipoarchXExt(String extension) {
		logger.info("SERVICE CourierServiceImp obtTipoarchXExt");
		return courierDao.obtTipoarchXExt(extension);
	}

	@Override
	public Integer obtCourierXCodbbva(String codbbva) {
		logger.info("SERVICE CourierServiceImp obtCourierXCodbbva");
		return courierDao.obtCourierXCodbbva(codbbva);
	}

	public List<Tercero> lstTercerosxCourier(Tercero tercero) {
		logger.info("SERVICE CourierServiceImp lstTercerosxCourier");
		return courierDao.lstTercerosxCourier(tercero); 
	}
}
