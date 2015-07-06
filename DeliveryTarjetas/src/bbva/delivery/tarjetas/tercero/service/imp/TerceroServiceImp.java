package bbva.delivery.tarjetas.tercero.service.imp;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private TerceroDao terceroDao;

	@Override
	public void mntTercero(Tercero tercero) {
		
		logger.info("SERVICE mntTercero");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		tercero.setHistorial("Usuario: " + tercero.getUsuario() + ", Fecha:" + dateFormat.format(date) + ", " +  tercero.toString()); 
		
		terceroDao.mntTercero(tercero);	 
	}

	@Override
	public List<Tercero> lstTerceros(Tercero tercero) { 
		logger.info("SERVICE lstTerceros");
		return terceroDao.lstTerceros(tercero);
	}
	
	@Override
	public BigDecimal obtTerceroXNrodoc(String nrodocumento){
		return terceroDao.obtTerceroXNrodoc(nrodocumento);
	}
	
	@Override
	public List<Tercero> obtTercero (Tercero tercero){
		return terceroDao.obtTercero(tercero);
	}
}