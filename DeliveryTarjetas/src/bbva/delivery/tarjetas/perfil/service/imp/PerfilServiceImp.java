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
		// TODO Auto-generated method stub
		logger.info("Service mntPerfil");
		perfilDao.mntPerfil(perfil);		
	}

	@Override
	public List<Perfil> lstPerfiles(Perfil perfil) {
		// TODO Auto-generated method stub
		logger.info("Service lstPerfiles");
		return perfilDao.lstPerfiles(perfil);
	}

	@Override
	public List<Perfil> obtPerfil(Perfil perfil) {
		// TODO Auto-generated method stub
		logger.info("Service obtPerfil");
		return perfilDao.obtPerfil(perfil);
	}

}