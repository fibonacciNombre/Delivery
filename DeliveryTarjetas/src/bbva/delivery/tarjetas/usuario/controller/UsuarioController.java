package bbva.delivery.tarjetas.usuario.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.UsuarioWeb;
import bbva.delivery.tarjetas.usuario.service.UsuarioService;
import commons.framework.BaseController;

@AdviceController
public class UsuarioController extends BaseController {
	
	private static Logger logger = Logger.getLogger(
			UsuarioController.class.getName());
	
	@Autowired
	private ComunService comunService;
	@Autowired
	private UsuarioService usuarioService;
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}
	
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
			
			UsuarioWeb usuarioWeb 	= usuarioService.autenticarUsuario(loginWeb);
			
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
}