package bbva.delivery.tarjetas.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.CryptoWeb;
import bbva.delivery.tarjetas.comun.bean.ListaParametroCursor;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.bean.TransaccionWeb;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.ldap.service.LdapService;
import bbva.delivery.tarjetas.perfil.bean.LoginWeb;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.perfil.service.PerfilService;

import commons.framework.BaseController;

@AdviceController
public class LoginController extends BaseController{

	public static final String WEBAPP_SAS 				= "/" ;
	
	public static final String PARAM_KEY_RECAPTCHA 		= "PWEB_KEY_RECAPTCHA";
	
	public static final String PARAM_RECAPTCHA_PRIVATE	= "PRIVATE_KEY";
	
	public static final String PARAM_RECAPTCHA_PUBLIC 	= "PUBLIC_KEY";

	LdapService ldapService 							= LdapService.getInstance();

	private static Logger logger 						= Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private PerfilService perfilService;
	

	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String escenarioLogin 			= "";
		String result					= "";
		LoginWeb loginWeb 				= null;
		HttpSession session				= request.getSession(true);
		String token 					= UUID.randomUUID().toString();//session.getId();
		String mobile					= request.getParameter("mobile");
		String userlogin				= request.getParameter("userlogin");
		SimpleDateFormat formatDate 	= new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			logger.info("*** ini login *** ");
			logger.debug("Logueando al usuario	: " + userlogin);
			
			session.removeAttribute(Constants.REQ_SESSION_TOKENID);
	    	session.removeAttribute(Constants.REQ_SESSION_INDMOBILE);
	    	
			loginWeb 		= new LoginWeb(request.getParameterMap());
			
//			UsuarioWeb usuarioWeb 	= perfilService.autenticarUsuario(loginWeb);
//			escenarioLogin 			= loginWeb.getEscenario();
//			
//			logger.debug("Resultado del logueo	: " + escenarioLogin);
//			
//			System.out.println("Resultado del logueo	: " + escenarioLogin);
//			
//			session.setAttribute(Constants.REQ_ATTR_NRODOCUMENTO, userlogin);
//			session.setAttribute(Constants.REQ_ATTR_LOGINWEB, loginWeb);
//			session.setAttribute(Constants.REQ_ATTR_USUARIOWEB, usuarioWeb);
//			
//			if( escenarioLogin.equals(Constants.RSP_LOGIN_USER_CRED_VALIDA) ||
//					escenarioLogin.equals(Constants.RPS_LOGIN_USER_PRIMER_LOGIN) ||	
//					escenarioLogin.equals(Constants.RSP_LOGIN_USER_REG_DIRECCION)){
//				
//				session.setAttribute(Constants.REQ_SESSION_INDMOBILE, mobile);
//				
//				if(mobile.equals("1")){
//					
//					logger.debug("Usuario logueado desde el APP MOBILE");
//					
//					Calendar cal 			= Calendar.getInstance();
//					
//				    cal.setTime(new Date());
//				    
//					Integer idetercero 		= ((UsuarioWeb) session.getAttribute(Constants.REQ_ATTR_USUARIOWEB)).getIdetercero();
//					
//				    String fecinivig 		= formatDate.format(cal.getTime());
//				    
//				    logger.debug("Registrando el TOKEN para el usuario del APP MOBILE");
//				    logger.debug("idetercero	: " + idetercero);
//				    logger.debug("token			: " + token);
//				    logger.debug("Useragent		: " + loginWeb.getUseragent());
//				    
//				    perfilService.regToken(idetercero, token, fecinivig, loginWeb.getUseragent());
//									    
//			    	session.setAttribute(Constants.REQ_SESSION_TOKENID, token);
//			    	
//			    	loginWeb.setUrldestino("jsp/mobile/inicio.jsp");
//				}else{
					loginWeb.setUrldestino("jsp/inicio.jsp");
//				}
//				loginWeb.setPasslogin(null);
//				loginWeb.setIndlogin(Constants.ACCION_WEB_ACCESOS_CORRECTOS);
//				
//			}else if ( escenarioLogin.equals(Constants.RSP_LOGIN_USER_CRED_INVALIDA)){
//				loginWeb.setPasslogin(null);
//				loginWeb.setMensaje("USUARIO Y/O CONTRASE�A NO V�LIDOS");
//				
//			}else if ( escenarioLogin.equals(Constants.RSP_LOGIN_USER_BLOQUEADO)){
//				if(loginWeb.getNrointento().equals(Constants.MAX_INTENTO_BLOQ.toString())){
//					loginWeb.setPasslogin(null);
//					loginWeb.setMensaje("USUARIO Y/O CONTRASE�A NO V�LIDOS");
//				}else{
//					loginWeb.setPasslogin(null);
//					loginWeb.setMensaje("USUARIO BLOQUEADO POR N�MERO MAXIMO DE INTENTOS ERR�NEOS");
//				}
//				
//			}else if ( escenarioLogin.equals(Constants.RSP_LOGIN_USER_NO_EXISTE)){
//				loginWeb.setPasslogin(null);
//				loginWeb.setMensaje("USUARIO NO REGISTRADO");
//			}else if(escenarioLogin.equals("")){
//				loginWeb.setPasslogin(null);
//				loginWeb.setMensaje("ERROR DE CONEXION");
//			}
			
			logger.debug("*** fin login ***");
						
		} catch (Exception e) {

			e.printStackTrace();
			logger.error("*** login -ERROR ***");
			logger.error(e);
		}
		
		logger.debug("**** INI LOGINWEB****");
		//logger.debug(ToStringBuilder.reflectionToString(loginWeb,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN LOGINWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(loginWeb, null, LoginWeb.class.getName());
		
		logger.info("*** fin login *** ");
		
		this.escribirTextoSalida(response, result);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("*** ini logout *** ");
		
		String token		= "";
		String indmobile	= "";
		HttpSession session	= request.getSession();
		
		indmobile			= request.getParameter("mobile");
		
		//LOGOUT WEB
		if(indmobile != null){ 
			
			if(indmobile.equals("0")){
				session.invalidate();			
				response.sendRedirect(request.getContextPath());
			}
			
			//LOGOUT JSP MOBILE
			if(indmobile.equals("1")){
				
				token = (String) session.getAttribute(Constants.REQ_SESSION_TOKENID);
				
				perfilService.regToken(null, token, null, null);
				
				session.invalidate();				
				response.sendRedirect("jsp/mobile/login.jsp");	
			}
			
		}	
		
		logger.info("*** fin logout *** ");
	}
	
	@SuppressWarnings("unused")
	public void validarCaptcha(HttpServletRequest request,
			HttpServletResponse response){
		
		logger.info("*** ini validarCaptcha *** ");
		
		String result 					="";
		String privatekey 				="";
		String publickey 				= "";
		
		TransaccionWeb transaccion		= new TransaccionWeb();
		String remoteAddr 				= request.getRemoteAddr();
        ReCaptchaImpl reCaptcha 		= new ReCaptchaImpl();
        Parametro parametro 			= new Parametro();
        ListaParametroCursor listaparam = new ListaParametroCursor();
		
        parametro.setIdeTipPar(PARAM_KEY_RECAPTCHA);
        comunService.obtenerListaParametros(parametro);
		
		for(int i=0;i<parametro.getCursor().size();i++){
			listaparam = (ListaParametroCursor) parametro.getCursor().get(i);
			listaparam.getCodigo();
			
			if(listaparam.getCodigo().equals(PARAM_RECAPTCHA_PRIVATE)){
				privatekey = listaparam.getDescripcion();
			}
			
			if(listaparam.getCodigo().equals(PARAM_RECAPTCHA_PUBLIC)){
				publickey = listaparam.getDescripcion();
			}
		}
		
        reCaptcha.setPrivateKey(privatekey);

        String challenge = request.getParameter("challenge");
        String uresponse = request.getParameter("response");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
        
        if (reCaptchaResponse.isValid()) {
        	result = "{"+ "\"result\":\"" + "OK" + "\"}";
        } else {
        	transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
        	transaccion.setMessagetx("El texto ingresado no coincide que el texto que se muestra en la imagen");        	
//        	result = "{"+ "\"result\":\"" + reCaptchaResponse.getErrorMessage()+" - private key: "+ privatekey + "\"}";
        }
       
        logger.debug("**** INI TransaccionWeb****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TransaccionWeb ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		
		logger.info("*** fin validarCaptcha *** ");
		
        this.escribirTextoSalida(response, result);
	}
	
	public void obtCaptchaKeys(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini obtCaptchaKeys *** ");
		
		String privatekey 				= "";
		String publickey 				= "";
		String result					= "";
		HttpSession session				= request.getSession(true);
		
		Parametro parametro 			= new Parametro();
        ListaParametroCursor listaparam = new ListaParametroCursor();
		
        parametro.setIdeTipPar(PARAM_KEY_RECAPTCHA);
        comunService.obtenerListaParametros(parametro);
		
		for(int i=0;i<parametro.getCursor().size();i++){
			listaparam = (ListaParametroCursor) parametro.getCursor().get(i);
			listaparam.getCodigo();
			
			if(listaparam.getCodigo().equals(PARAM_RECAPTCHA_PRIVATE)){
				privatekey = listaparam.getDescripcion();
				session.setAttribute("PRIVATEKEY", privatekey);
			}
			
			if(listaparam.getCodigo().equals(PARAM_RECAPTCHA_PUBLIC)){
				publickey = listaparam.getDescripcion();
				session.setAttribute("PUBLICKEY", publickey);
			}
		}
		
		result = "{"
				+ "\"privatekey\"" + ":\"" + privatekey 	+ "\"" + ","
				+ "\"publickey\"" + ":\"" + publickey 	 	+ "\"" + "}";
		
		logger.info("*** fin obtCaptchaKeys *** ");
		
		this.escribirTextoSalida(response, result);
		
	}
	
	@Override
	public ModelAndView buscar(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView open(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}		
}