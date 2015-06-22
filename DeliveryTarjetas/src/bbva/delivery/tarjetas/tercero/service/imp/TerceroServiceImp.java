package bbva.delivery.tarjetas.tercero.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.dao.TerceroDao;
import bbva.delivery.tarjetas.tercero.service.TerceroService;

@Service("terceroService")
@Transactional(propagation=Propagation.SUPPORTS)
public class TerceroServiceImp implements TerceroService {
	
	private static Logger logger = Logger.getLogger(TerceroServiceImp.class.getName());
	
	private TerceroDao terceroDao;
	/*
	@Override
	public void regTercero(Tercero tercero) {
		logger.info("Service regCourier");
		terceroDao.regTercero(tercero);
	}

	@Override
	public void mntTercero(Tercero tercero) {
		logger.info("Service regCourier");
		terceroDao.mntTercero(tercero);
	}

	@Override
	public List<Tercero> lstTerceros(String estado) {
		logger.info("Service regCourier");
		return terceroDao.lstTerceros(estado);
	}

	@Override
	public Tercero obtDetalleTercero(Tercero tercero) {
		logger.info("Service regCourier");
		return terceroDao.obtDetalleTercero(tercero);
	}*/
}