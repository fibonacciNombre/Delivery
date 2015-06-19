package bbva.delivery.tarjetas.perfil.service.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.tarjetas.bean.LoginWeb;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.perfil.dao.PerfilDao;
import bbva.delivery.tarjetas.perfil.service.PerfilService;

@Service("perfilService")
@Transactional(propagation=Propagation.SUPPORTS)
public class PerfilServiceImp implements PerfilService {
	
	@Autowired
	private PerfilDao perfilDao;
	
	private static Logger logger 						= Logger.getLogger(PerfilServiceImp.class.getName());
	
	public void regUsuarioweb(UsuarioWeb mntRegUser) throws Exception {
		perfilDao.registrarUsuarioweb(mntRegUser);
	}
	
	public void actUsuarioweb(UsuarioWeb terLogIn, String ind) {
		perfilDao.mtnEstadoUsuarioWeb(terLogIn, ind);
	}
	
	public void obtUsuarioweb(UsuarioWeb usuarioWeb) {
		perfilDao.obtUsuarioweb(usuarioWeb);
	}
		
	public void obtIdeterceroUsuarioweb(UsuarioWeb usuarioWeb) {
		perfilDao.obtIdeterceroUsuarioweb(usuarioWeb);
	}
	
	public void obtCodexternoUsuarioweb(UsuarioWeb usuarioWeb) {
		perfilDao.obtCodexternoUsuarioweb(usuarioWeb);
	}

	public void obtPersona(Persona persona){
		perfilDao.obtPersona(persona);
	}
	
	public void validarEstadoUsuarioweb(UsuarioWeb mntRegUser) {
		perfilDao.obtEstadoUsuarioWeb(mntRegUser);
	}
	
	public UsuarioWeb autenticarUsuario(LoginWeb loginWeb)throws Exception {
		
		logger.debug("****ini autenticarUsuario****");
		
		UsuarioWeb usuarioWeb		= new UsuarioWeb();
		UsuarioWeb usuarioWebTmp	= new UsuarioWeb();

		String idUsuario 		= loginWeb.getUserlogin();
		String password 		= loginWeb.getPasslogin();
				
		usuarioWebTmp.setNumerodoc(idUsuario);
		usuarioWebTmp.setPassword(password);
		
		//VERIFICAR QUE EL USUARIO EXISTA EN LA BD
//		obtUsuarioweb(usuarioWebTmp);	
		
//		if(usuarioWebTmp.getCursor().size() > 0){
			
			//VERIFICAR CREDENCIALES (PASSWORD) EN BD
			
			if(validarContrasena(usuarioWeb)){
//				obtUsuarioweb(usuarioWebTmp);
//				usuarioWeb = usuarioWebTmp.getCursor().get(0);
//				
//				if(usuarioWeb.getIdpstsusuario().equals(Constants.USR_STS_INACTIVO))
//					loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_USUARIO_INACTIVO);
//				else
					loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_ACCESOS_CORRECTOS);
				
			}else
				loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_ACCESOS_INCORRECTOS);
			
//		}else{
//			//USUARIO NO EXISTE EN LA BD
//			loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_USUARIO_NO_EXITE);
//		}
					
		return usuarioWeb;
	}

	@Override
	public boolean validarContrasena(UsuarioWeb usuarioWeb) {
//		return perfilDao.validarContrsena(usuarioWeb);
		return true;
	}
}