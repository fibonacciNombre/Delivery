package bbva.delivery.tarjetas.usuario.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.TransaccionWeb;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.service.CourierService;
import bbva.delivery.tarjetas.perfil.bean.Perfil;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.service.TerceroService;
import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.Usuario;
import bbva.delivery.tarjetas.usuario.service.UsuarioService;
import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class UsuarioController extends BaseController {
	
	private static Logger logger = Logger.getLogger(UsuarioController.class.getName());
	
	@Autowired
	private ComunService comunService;

	@Autowired
	private CourierService courierService;
	
	@Autowired
	private TerceroService terceroService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}
	
	public String goRegUsuarioWeb(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goRegUsuarioWeb	-->		reg-usuario.jsp");
		return "usuario/reg-usuario"; 
	}
	
	public String goMntUsuarioWeb(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goMntUsuarioWeb	-->		mnt-usuario.jsp");
		return "usuario/mnt-usuario"; 
	}
	
	public String goRegUsuarioWS(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goRegUsuarioWS	-->		reg-usuario-ws.jsp");
		return "usuario/reg-usuario-ws"; 
	}
	
	public String goMntUsuarioWS(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goMntUsuarioWS	-->		mnt-usuario-ws.jsp");
		return "usuario/mnt-usuario-ws"; 
	}
	
	public String goMiCuenta(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goMiCuenta	-->		mnt-cuenta.jsp");
		return "usuario/mnt-cuenta"; 
	}
	
	public String goActContrasena(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goActContrasena	-->		act-contrasena.jsp");
		return "usuario/act-contrasena"; 
	}
	
	public void lstUsuario(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller lstCourier");
		
		String result				= "";
		String lstusuario 			= "";
		List<Usuario> listaUsuario 	= null;
		TransaccionWeb tx			= new TransaccionWeb();				
		Usuario usuario 			= new Usuario(request.getParameterMap());
		Tercero tercero 			= new Tercero(request.getParameterMap());

		try {
			
			listaUsuario 	= usuarioService.lstUsuarios(usuario, tercero);
			
			lstusuario 		= UtilWeb.listaToArrayJson(listaUsuario, null, Courier.class.getName());			
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			lstusuario = "{" + e.getMessage() + "}";
		}
		
		result += "{"
					+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
					+ "\"lst\":" + lstusuario 
					+ "}";
		
		this.escribirTextoSalida(response, result);

	}
	
	public void mntUsuario(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller mntUsario");
		
		String result			= "";
		HttpSession session 	= request.getSession();
		TransaccionWeb tx		= new TransaccionWeb();
		Tercero tercero 		= new Tercero(request.getParameterMap());
		Usuario usuario 		= new Usuario(request.getParameterMap());
		
		//String usuario = session.getAttribute(Constants.REQ_SESSION_USUARIO).toString();
		usuario.setUsucreacion("BBVA");
		
		try {
			
			if(tercero.getNrodocumento()!=null && tercero.getNrodocumento()!=""){
				terceroService.mntTercero(tercero);
				usuario.setIdtercero(tercero.getIdtercero());
			}
			
			usuarioService.mntUsuario(usuario);
			
			tx.setMessagetx("Su transacción fue realizada con éxito");
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
		}

		result += "{\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + "," +
					"\"usuario\":"+UtilWeb.objectToJson(usuario, null, Usuario.class.getName()) +
					"}";
		
		this.escribirTextoSalida(response, result);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String escenarioLogin 			= "";
		String result					= "";
		LoginWeb loginWeb 				= null;
		HttpSession session				= request.getSession(true);
		String userlogin				= request.getParameter("userlogin");
		
		try {
			
			logger.info("*** ini login *** ");
			logger.debug("Logueando al usuario	: " + userlogin);
			
			loginWeb 				= new LoginWeb(request.getParameterMap());
			
			Usuario usuarioWeb 	= usuarioService.autenticarUsuario(loginWeb);
			
			escenarioLogin 			= loginWeb.getEscenario();
			
			logger.debug("Resultado del logueo	: " + escenarioLogin);
			
			System.out.println("Resultado del logueo	: " + escenarioLogin);
			
			session.setAttribute(Constants.REQ_SESSION_LOGINWEB, loginWeb);
			session.setAttribute(Constants.REQ_SESSION_USUARIO, usuarioWeb);
			
			if( escenarioLogin.equals(Constants.ESCENARIO_LOGIN_ACCESOS_CORRECTOS) ){
				loginWeb.setUrldestino("jsp/inicio.jsp");
				
			}else if ( escenarioLogin.equals(Constants.ESCENARIO_LOGIN_USUARIO_INACTIVO) ){
				loginWeb.setPasslogin(null);
				loginWeb.setMensaje("USUARIO SE ENCUENTRA INACTIVO");
			
			}else if ( escenarioLogin.equals(Constants.ESCENARIO_LOGIN_COURIER_INACTIVA) ){
				loginWeb.setPasslogin(null);
				loginWeb.setMensaje("COURIER SE ENCUENTRA INACTIVO");
				
			}else if ( escenarioLogin.equals(Constants.ESCENARIO_LOGIN_USUARIO_NO_EXITE) ){
				loginWeb.setPasslogin(null);
				loginWeb.setMensaje("USUARIO NO EXISTE");
				
			}else if ( escenarioLogin.equals(Constants.ESCENARIO_LOGIN_ACCESOS_INCORRECTOS) ){				
				loginWeb.setPasslogin(null);
				loginWeb.setMensaje("USUARIO Y/O CONTRASEÑA NO VALIDOS");				
			}
			
			logger.debug("*** fin login ***");
						
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("*** login -ERROR ***");
			logger.error(e);
		}
		
		result = commons.web.UtilWeb.objectToJson(loginWeb, null, LoginWeb.class.getName());
		
		logger.info("*** fin login *** ");
		
		this.escribirTextoSalida(response, result);
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("*** ini logout *** ");
		
		HttpSession session	= request.getSession();
		
		session.invalidate();			
		response.sendRedirect(request.getContextPath());			
		
		logger.info("*** fin logout *** ");
	}
	
	public void obtDatosUsuarioSesion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("*** ini obtDatosUsuarioSesion *** ");
		
		HttpSession session			= request.getSession();
		
		String result				= "";
		Usuario usuarioWeb		= null;
		Perfil perfil				= new Perfil();
		Courier courier				= new Courier();
		Tercero tercero				= new Tercero();
		List<Courier> lstcourier;
				
		String jsonUsuario			= "\"Usuarioweb\":[";
		String jsonPerfil			= "\"Perfil\":[";
		String jsonTercero			= "\"Tercero\":[";
		String jsonCourier			= "\"Courier\":[";
		
		usuarioWeb	= (Usuario) session.getAttribute(Constants.REQ_SESSION_USUARIO);
		
//		usuarioWeb = usuarioService.obtDetalleUsuarioWeb(usuarioWeb);
		
		usuarioWeb.setEstado(Constants.USR_STS_ACTIVO.toString());
		
		jsonUsuario += commons.web.UtilWeb.objectToJson(usuarioWeb, null, Usuario.class.getName());
		
		jsonUsuario += "]";
		
		if(usuarioWeb.getIdperfil()!=null){
			perfil.setIdperfil(usuarioWeb.getIdperfil());
//			perfil = perfilService.obtDetallePerfil(perfil);	
			jsonPerfil += commons.web.UtilWeb.objectToJson(perfil, null, Perfil.class.getName());
		}
		
		jsonPerfil += "]";
		
		if(usuarioWeb.getIdtercero()!=null){
			tercero.setIdtercero(usuarioWeb.getIdtercero());
			//tercero = terceroService.obtDetalleTercero(tercero);	
			jsonTercero += commons.web.UtilWeb.objectToJson(tercero, null, Tercero.class.getName());
		}
		
		jsonTercero += "]";
		
		if(tercero.getIdcourier()!=null){
			courier.setIdcourier(tercero.getIdcourier());
			lstcourier = courierService.obtCourier(courier);	
			jsonCourier += commons.web.UtilWeb.objectToJson(lstcourier.get(0), null, Courier.class.getName());
		}
		
		jsonCourier += "]";
		
		result = "{" +
					jsonCourier + "," +
					jsonTercero + "," +
					jsonUsuario + "," +
					jsonPerfil +
				"}";
		
		this.escribirTextoSalida(response, result);
		
		logger.info("*** fin obtDatosUsuarioSesion *** ");
	}
	
	public void actContrasena(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("*** ini actContrasena *** ");
		
		HttpSession session			= request.getSession();
		
		String result				= "";
		TransaccionWeb tx			= new TransaccionWeb();
		Usuario usuarioWeb 		= new Usuario(request.getParameterMap());
		
//		usuarioService.actContrasena(usuarioWeb);
		
		usuarioWeb.setEstado(Constants.USR_STS_ACTIVO);
		
//		usuarioService.mntUsuarioWeb(usuarioWeb);
		
		session.setAttribute(Constants.REQ_SESSION_USUARIO, usuarioWeb);
		
		result = commons.web.UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName());
		
		this.escribirTextoSalida(response, result);
		
		logger.info("*** fin actContrasena *** ");
	}
}