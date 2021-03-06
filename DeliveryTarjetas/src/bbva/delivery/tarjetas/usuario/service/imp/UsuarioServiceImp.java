package bbva.delivery.tarjetas.usuario.service.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Transactional(propagation = Propagation.SUPPORTS)
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private CourierDao courierDao;

	@Autowired
	private TerceroDao terceroDao;

	@Autowired
	private UsuarioDao usuarioDao;

	private static Logger logger = Logger.getLogger(UsuarioServiceImp.class
			.getName());

	public Usuario obtUsuario(Usuario usuario) {
		logger.info("SERVICE UsuarioServiceImp obtUsuario");
		return usuarioDao.obtUsuario(usuario);
	}

	public Usuario autenticarUsuario(LoginWeb loginWeb) throws Exception {

		logger.info("SERVICE UsuarioServiceImp autenticarUsuario");

		Courier courier 		= new Courier();
		Tercero tercero 		= new Tercero();
		Usuario usuario 		= new Usuario();
		Usuario usuarioTmp 		= new Usuario();

		String idUsuario 	= loginWeb.getUserlogin();
		String password 	= loginWeb.getPasslogin();

		usuarioTmp.setCodusuario(idUsuario);
		
		// VERIFICAR QUE EL USUARIO EXISTA EN LA BD
		 usuarioTmp 		= obtUsuario(usuarioTmp);
		
		 if(usuarioTmp.getIdtercero() != null){

			 // VERIFICAR CREDENCIALES (PASSWORD) EN BD
			 usuarioTmp.setContrasena(AESHelper.encriptar(AESHelper.KEY, AESHelper.IV, password));
			 
			 if (validarContrasena(usuarioTmp)) {

				 usuario = obtUsuario(usuarioTmp);
			
				 if(usuario.getIdpestado().equals(Constants.DELIVERY_IDPESTADO_INACTIVO))
					 	loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_USUARIO_INACTIVO);
				 else{

					 loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_ACCESOS_CORRECTOS);
			
					 tercero.setIdtercero(usuario.getIdtercero());
					 
					 tercero = terceroDao.obtTercero(tercero);
			
					 if(tercero.getIdcourier() != null){
						 	courier.setIdcourier(tercero.getIdcourier());
			
						 	courier = courierDao.obtCourier(courier).get(0);
			
						 	if(courier.getIdpestado() == Constants.DELIVERY_IDPESTADO_INACTIVO)
						 		loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_COURIER_INACTIVA);
					 }
					 
				 }
			 }else
				 loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_ACCESOS_INCORRECTOS);
			
		}else{
		 //USUARIO NO EXISTE EN LA BD
		 loginWeb.setEscenario(Constants.ESCENARIO_LOGIN_USUARIO_NO_EXITE);
		}

		return usuario;
	}

	@Override
	public boolean validarContrasena(Usuario usuario) {
		logger.info("SERVICE UsuarioServiceImp validarContrasena");
		return usuarioDao.validarContrasena(usuario);
	}
		
	public Usuario addUsuario(Usuario usuario) throws Exception {

		logger.info("SERVICE UsuarioServiceImp addUsuario");
		
		usuario.setContrasena(AESHelper.encriptar(AESHelper.KEY, AESHelper.IV, usuario.getContrasena()));
		usuario.setIdpestado(1);
		usuario.setCodusuario("DELIVERY_BBVA");

		return usuarioDao.obtUsuario(usuarioDao.addUsuario(usuario)); 
	}

	@Override
	public void mntUsuario(Usuario usuario) {
		
		logger.info("SERVICE UsuarioServiceImp mntUsuario");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		usuario.setHistorial("Usuario: " + usuario.getUsumodificacion() + ", Fecha:" + dateFormat.format(date) + ", " +  usuario.toString());
		
		usuarioDao.mntUsuario(usuario);
	}

	@Override
	public List<Usuario> lstUsuarios(Usuario usuario) {
		logger.info("SERVICE UsuarioServiceImp lstUsuarios");
		return usuarioDao.lstUsuarios(usuario);
	}

	@Override
	public void mntContrasena(Usuario usuario) throws Exception {
		
		logger.info("SERVICE UsuarioServiceImp mntContrasena");
		
		if(usuario.getContrasena()==null || usuario.getContrasena()==""){
			usuario.setIndrnvcontrasena("S");
			usuario.setContrasena(Constants.CONTRASENA_DEFAULT);
		}

		usuario.setContrasena(AESHelper.encriptar(AESHelper.KEY, AESHelper.IV, usuario.getContrasena()));
		
		usuarioDao.mntContrasena(usuario);
	}

	@Override
	public List<Usuario> lstUsuariosWS(Usuario usuario) {
		logger.info("SERVICE UsuarioServiceImp lstUsuariosWS");
		return usuarioDao.lstUsuariosWS(usuario);
	}
}