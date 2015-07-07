package bbva.delivery.tarjetas.comun.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.tarjetas.comun.bean.ArchivoBlob;
import bbva.delivery.tarjetas.comun.bean.Constante;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.dao.ComunDao;
import bbva.delivery.tarjetas.comun.service.ComunService;

@Service("comunService")
@Transactional(propagation=Propagation.SUPPORTS)
public class ComunServiceImp implements ComunService {

	@Autowired private ComunDao comunDao;
	
	private static Logger logger = Logger.getLogger(ComunServiceImp.class.getName());
	/*
	 * (non-Javadoc)
	 * @see rimac.portalweb.dao.ComunDaoImp#obtenerConstante(String)
	 */	
	public Constante obtenerConstante(String ideConstante) {
		logger.info("SERVICE ComunServiceImp obtenerConstante");
		return comunDao.obtenerConstante(ideConstante);
	}
	
	/*
	 * (non-Javadoc)
	 * @see rimac.portalweb.dao.ComunDaoImp#obtenerParametro(rimac.portalweb.bean.Parametro)
	 */	
	public Parametro obtenerParametro(Parametro parametro){
		logger.info("SERVICE ComunServiceImp obtenerParametro");
		return comunDao.obtenerParametro(parametro);
	}

	/* (non-Javadoc)
	 * @see rimac.portalweb.comun.service.ComunService#listarParametro(rimac.portalweb.comun.bean.Parametro)
	 */
	public List<Parametro> lstParametro(Parametro parametro) {
		logger.info("SERVICE ComunServiceImp lstParametro");
		return comunDao.lstParametro(parametro);
	}
	
	public List<Parametro> cmbParametro(Parametro parametro) {
		logger.info("SERVICE ComunServiceImp cmbParametro");
		return comunDao.cmbParametro(parametro);
	}

	/* (non-Javadoc)
	 * @see rimac.portalweb.comun.service.ComunService#obtenerListaParametros(rimac.portalweb.comun.bean.Parametro)
	 */
	public void obtenerListaParametros(Parametro param) {
		comunDao.obtenerListaParametros(param);		
	}

	public void mntArchivoblob(ArchivoBlob param) {
		comunDao.mntArchivoblob(param);		
	}
}