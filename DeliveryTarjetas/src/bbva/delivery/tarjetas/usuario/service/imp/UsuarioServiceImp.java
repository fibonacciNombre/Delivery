package bbva.delivery.tarjetas.usuario.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.dao.CourierDao;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.dao.TerceroDao;
import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.UsuarioWeb;
import bbva.delivery.tarjetas.usuario.dao.UsuarioDao;
import bbva.delivery.tarjetas.usuario.service.UsuarioService;

@Service("usuarioService")
@Transactional(propagation=Propagation.SUPPORTS)
public class UsuarioServiceImp implements UsuarioService {
	
	@Autowired
	private CourierDao courierDao;
	
	@Autowired
	private TerceroDao terceroDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	private static Logger logger 			= Logger.getLogger(UsuarioServiceImp.class.getName());
	
	public UsuarioWeb obtDetalleUsuarioWeb(UsuarioWeb usuarioWeb) {
		logger.info("Service obtDetalleUsuarioWeb");
		
		return usuarioDao.obtDetalleUsuarioWeb(usuarioWeb);
	}
		
	public UsuarioWeb autenticarUsuario(LoginWeb loginWeb)throws Exception {
		
		logger.info("Service autenticarUsuario");
		
		logger.debug("****ini autenticarUsuario****");
		
		Courier courier				= new Courier();
		Tercero tercero				= new Tercero();
		UsuarioWeb usuarioWeb		= null;
		UsuarioWeb usuarioWebTmp	= new UsuarioWeb();

		String idUsuario 		= loginWeb.getUserlogin();
		String password 		= loginWeb.getPasslogin();
				
		usuarioWebTmp.setNumerodoc(idUsuario);
		usuarioWebTmp.setPassword(password);
		
		//VERIFICAR QUE EL USUARIO EXISTA EN LA BD
//		usuarioWebTmp = obtDetalleUsuarioWeb(usuarioWebTmp);	
//		
//		if(usuarioWebTmp.getIdtercero() != null){
			
			//VERIFICAR CREDENCIALES (PASSWORD) EN BD
			
			if(validarContrasena(usuarioWebTmp)){
				
//				usuarioWeb = obtDetalleUsuarioWeb(usuarioWebTmp);
//				
//				if(usuarioWeb.getEstado().equals(Constants.USR_STS_INACTIVO))
//					loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_USUARIO_INACTIVO);
//				else{
					
					loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_ACCESOS_CORRECTOS);
					
//					tercero.setIdtercero(usuarioWeb.getIdtercero());
//					
//					tercero = terceroDao.obtDetalleTercero(tercero);
//					
//					if(tercero.getIdcourier() != null){
//						courier.setIdcourier(tercero.getIdcourier());
//						
//						courier = courierDao.obtDetalleCourier(courier);
//						
//						if(courier.getEstado().equals(Constants.USR_STS_INACTIVO))
//							loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_COURIER_INACTIVA);						
//					}
//				}
//			}else
//				loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_ACCESOS_INCORRECTOS);
//			
		}
//		else{
//			//USUARIO NO EXISTE EN LA BD
//			loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_USUARIO_NO_EXITE);
//		}
					
		return usuarioWeb;
	}

	@Override
	public boolean validarContrasena(UsuarioWeb usuarioWeb) {
		logger.info("Service validarContrasena");
//		return perfilDao.validarContrsena(usuarioWeb);
		return true;
	}

	@Override
	public void regUsuarioWeb(UsuarioWeb usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Service regUsuarioWeb");
	}

	@Override
	public void mntUsuarioWeb(UsuarioWeb usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Service mntUsuarioWeb");
		
	}

	@Override
	public List<Courier> lstUsuariosWeb(String estado) {
		// TODO Auto-generated method stub
		logger.info("Service lstUsuariosWeb");
		return null;
	}
}