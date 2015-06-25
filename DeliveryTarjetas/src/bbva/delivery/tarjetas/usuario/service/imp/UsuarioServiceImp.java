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
import bbva.delivery.tarjetas.usuario.bean.Usuario;
import bbva.delivery.tarjetas.usuario.dao.UsuarioDao;
import bbva.delivery.tarjetas.usuario.service.UsuarioService;
import bbva.delivery.tarjetas.util.AESHelper;

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
	
	public Usuario obtDetalleUsuario(Usuario usuarioWeb) {
		logger.info("Service obtDetalleUsuarioWeb");		
		return usuarioDao.obtDetalleUsuario(usuarioWeb);
	}
		
	public Usuario autenticarUsuario(LoginWeb loginWeb){
		
		logger.info("Service autenticarUsuario");
		
		logger.debug("****ini autenticarUsuario****");
		
		Courier courier				= new Courier();
		Tercero tercero				= new Tercero();
		Usuario usuarioWeb		= new Usuario();
		Usuario usuarioWebTmp	= new Usuario();

		String idUsuario 		= loginWeb.getUserlogin();
		String password 		= loginWeb.getPasslogin();
				
		usuarioWebTmp.setCodusuario(idUsuario);
		usuarioWebTmp.setContrasena(password);
		
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
//					
//					tercero.setIdtercero(usuarioWeb.getIdtercero());
//					
//					tercero = terceroDao.obtDetalleTercero(tercero);
//					
//					if(tercero.getIdcourier() != null){
//						courier.setIdcourier(tercero.getIdcourier());
//						
//						courier = courierDao.obtDetalleCourier(courier);
//						
//						if(courier.getEstado().equals(Constants.BBVA_DELIVERY_STS_INACTIVO))
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
	public boolean validarContrasena(Usuario usuarioWeb) {
		logger.info("Service validarContrasena");
//		return perfilDao.validarContrsena(usuarioWeb);
		return true;
	}

	@Override
	public void regUsuario(Usuario usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Service regUsuario");
	}

	@Override
	public void mntUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Service mntUsuario");		
		usuario.setContrasena(AESHelper.encriptar(AESHelper.KEY, AESHelper.IV, usuario.getContrasena()));		
		usuarioDao.mntUsuario(usuario);
	}

	@Override
	public List<Usuario> lstUsuarios(Usuario usuario) {
		// TODO Auto-generated method stub
		logger.info("Service lstUsuarios");
		return usuarioDao.lstUsuarios(usuario);
	}

	@Override
	public void actContrasena(Usuario usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Service actContrasena");
		usuarioDao.actContrasena(usuarioWeb);
	}

	@Override
	public List<Usuario> lstUsuarios(Usuario usuario, Tercero tercero) {
		// TODO Auto-generated method stub
		return null;
	}
}