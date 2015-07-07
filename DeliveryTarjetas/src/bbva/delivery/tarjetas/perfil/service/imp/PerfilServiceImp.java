package bbva.delivery.tarjetas.perfil.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.tarjetas.perfil.bean.Perfil;
import bbva.delivery.tarjetas.perfil.dao.PerfilDao;
import bbva.delivery.tarjetas.perfil.service.PerfilService;

@Service("perfilService")
@Transactional(propagation=Propagation.SUPPORTS)
public class PerfilServiceImp implements PerfilService {
	
	private static Logger logger 						= Logger.getLogger(PerfilServiceImp.class.getName());
	
	@Autowired 
	private PerfilDao perfilDao;

	@Override
	public void mntPerfil(Perfil perfil) {
		logger.info("SERVICE PerfilServiceImp mntPerfil");
		perfilDao.mntPerfil(perfil);		
	}

	@Override
	public List<Perfil> lstPerfiles(Perfil perfil) {
		logger.info("SERVICE PerfilServiceImp lstPerfiles");
		return perfilDao.lstPerfiles(perfil);
	}

	@Override
	public Perfil obtPerfil(Perfil perfil) {
		logger.info("SERVICE PerfilServiceImp obtPerfil");
		return perfilDao.obtPerfil(perfil);
	}

}