package bbva.delivery.tarjetas.perfil.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.bean.LoginWeb;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.comun.bean.TransaccionWeb;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.PuntoContacto;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.perfil.service.PerfilService;

import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class PerfilController extends BaseController {
	
	private static Logger logger = Logger.getLogger(
			PerfilController.class.getName());
	
	@Autowired
	private ComunService comunService;
	@Autowired
	private PerfilService perfilService;
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}
	
	public String goActualizaDatos(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goCambioContrasenia	-->		actualiza-datos");
		return "perfil/actualiza-datos"; 
	}
	
	
	public String goDashboard(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		System.out.println("goDashboard	-->		dashboard");
		return "dashboard"; 
	}
	
	public void regUsuario(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini regUsuario *** ");
		
		String result 						= "";
		TransaccionWeb transaccion			= new TransaccionWeb();
		
		try{
		}
		catch(Exception e){
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		
		logger.info("*** fin regUsuario *** ");
		this.escribirTextoSalida(response, result);
	}
		
	public void obtDatosUsuarioSesion(HttpServletRequest request,
			HttpServletResponse response){
		
		String result 			= "";
		HttpSession session		= request.getSession();
		LoginWeb loginWeb	 	= (LoginWeb)session.getAttribute(Constants.REQ_SESSION_LOGINWEB);
		UsuarioWeb usrWebTemp	= (UsuarioWeb)session.getAttribute(Constants.REQ_SESSION_USUARIO);
				
		System.out.println("*** DATOS USUARIO LOGUEADO ***");
		
		String jsonUsuario		= "\"Usuarioweb\":[";
		
		UsuarioWeb usuarioWeb 	= (UsuarioWeb)session.getAttribute(Constants.REQ_SESSION_USUARIO);
		
		logger.debug("**** INI USUARIOWEB SESSION ****");
		logger.info(ToStringBuilder.reflectionToString(usuarioWeb,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN USUARIOWEB SESSION ****");
		
		if(usuarioWeb.getCodexterno()==null){
			logger.debug("****PERFILCONTROLLER ejecutando obtDatosUsuarioSesion****");
			logger.debug("****No se obtuvieron valores para CODEXTERNO... CODEXTERNO NULL****");
		}
		
		perfilService.obtUsuarioweb(usuarioWeb);
		
		if(usuarioWeb.getCursor()!=null){
			usuarioWeb = usuarioWeb.getCursor().get(0);
			usuarioWeb.setNumerodoc(usrWebTemp.getNumerodoc());
			usuarioWeb.setIdptipodocumento(usrWebTemp.getIdptipodocumento());
			usuarioWeb.setIdetercero(usrWebTemp.getIdetercero());
			usuarioWeb.setCodexterno(usrWebTemp.getCodexterno());

			session.setAttribute(Constants.REQ_SESSION_USUARIO, usuarioWeb);
			
			jsonUsuario += UtilWeb.objectToJson(usuarioWeb,null,UsuarioWeb.class.getName());
		}
		
		jsonUsuario += "]";
		
		System.out.println("*** DATOS PERSONA ***");
		
		String jsonPersona		= "\"Personaweb\":[";
		Persona personaTemp 	= new Persona();
		Persona personaWeb 		= new Persona();
		
		personaTemp.setIdetercero(usuarioWeb.getIdetercero());
		perfilService.obtPersona(personaTemp);
		
		if(personaTemp.getCursor()!=null){
			personaTemp = personaTemp.getCursor().get(0);
			
			personaWeb.setApepaterno(personaTemp.getApepaterno());
			personaWeb.setApematerno(personaTemp.getApematerno());
			personaWeb.setNombre(personaTemp.getNombre());
			personaWeb.setFecnacimiento(personaTemp.getFecnacimiento());
			personaWeb.setIdptipodocumento(personaTemp.getIdptipodocumento());
			personaWeb.setNumerodoc(usuarioWeb.getNumerodoc());
			personaWeb.setIdpgenero(personaTemp.getIdpgenero());
			
			session.setAttribute(Constants.REQ_SESSION_PERSONA, personaWeb);
			
			jsonPersona += UtilWeb.objectToJson(personaWeb,null,Persona.class.getName());
		}
		
		jsonPersona += "]";
		
		System.out.println("*** DIRECCION WEB ***");
		
		String jsonDireccion		= "\"Direccionweb\":[";
		
		if(usuarioWeb.getIdepuntocontactodir()!=null){
			
			PuntoContacto puntoContacto = new PuntoContacto();
			
			puntoContacto.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactodir()));
			
			if(puntoContacto.getCursor()!=null){
				puntoContacto = puntoContacto.getCursor().get(0);
				puntoContacto.setDireccioncompleta(puntoContacto.getDireccioncompleta().trim());
				
//				session.setAttribute(Constants.REQ_SESSION_DIRECCION, puntoContacto);
				
				jsonDireccion += UtilWeb.objectToJson(puntoContacto,null,PuntoContacto.class.getName());
			}			
		}
		
		jsonDireccion += "]";
		
		System.out.println("*** CORREO WEB ***");
		
		String jsonCorreo			= "\"Correoweb\":[";
		
		if(usuarioWeb.getIdepuntocontactomail()!=null){
			
			MedioContacto correoWeb 	= new MedioContacto();
			
			correoWeb.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactomail()));
			
			if(correoWeb.getCursor()!=null){
				correoWeb = correoWeb.getCursor().get(0);
				
//				session.setAttribute(Constants.REQ_SESSION_CORREO, correoWeb);
				
				jsonCorreo += UtilWeb.objectToJson(correoWeb,null,MedioContacto.class.getName());
			}
		}
		
		jsonCorreo += "]";
		
		System.out.println("*** TELEFONO WEB ***");
		
		String jsonTelefono			= "\"Telefonoweb\":[";
		
		if(usuarioWeb.getIdepuntocontactotelf()!=null){
			
			MedioContacto telefonoWeb 	= new MedioContacto();
			
			telefonoWeb.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactotelf()));
			
			if(telefonoWeb.getCursor()!=null){
				telefonoWeb = telefonoWeb.getCursor().get(0);
				
//				session.setAttribute(Constants.REQ_SESSION_TELEFONO, telefonoWeb);
				
				jsonTelefono += UtilWeb.objectToJson(telefonoWeb,null,MedioContacto.class.getName());
			}
		}
		
		jsonTelefono += "]";
		
		
		System.out.println("*** TELEFONO WEB SECUNDARIO ***");
		
		String jsonTelefonoSec			= "\"TelefonoSecweb\":[";
		
		if(usuarioWeb.getIdepuntocontactotelf()!=null){
			
			MedioContacto telefonoSecWeb 		= new MedioContacto();
			MedioContacto telefonoSecWebTemp 	= new MedioContacto();
			MedioContacto telefonoWebSesion 	= new MedioContacto();
			
			telefonoSecWeb.setIdetercero(usuarioWeb.getIdetercero());
			
			for(Integer i=0;i<telefonoSecWeb.getCursor().size();i++){
				telefonoSecWebTemp = telefonoSecWeb.getCursor().get(i);
				if(!telefonoSecWebTemp.getIdepuntocontacto().equals(telefonoWebSesion.getIdepuntocontacto())){
					jsonTelefonoSec += UtilWeb.objectToJson(telefonoSecWebTemp,null,MedioContacto.class.getName());
					break;
				}
			}
		}
		
		jsonTelefonoSec += "]";
		
		result = "{"
					+ "\"validacion\"" + ":\"" + loginWeb.getEscenario()		 	+ "\"" + ","
//					+ "\"indmobile\""  + ":\"" + session.getAttribute(Constants.REQ_SESSION_INDMOBILE)		 	+ "\"" + ","
					+ "\"idetercero\"" + ":\"" + usuarioWeb.getIdetercero() 	 	+ "\"" + ","
					+ "\"codexterno\"" + ":\"" + usuarioWeb.getCodexterno() 		+ "\"" + ","
					+ "\"numerodoc\""  + ":\"" + usuarioWeb.getNumerodoc()		    + "\"" + ","
					+ jsonUsuario + ","
					+ jsonPersona + ","
					+ jsonDireccion + ","
					+ jsonCorreo + ","
					+ jsonTelefonoSec + ","
					+ jsonTelefono + 
				"}";
	
		this.escribirTextoSalida(response, result);		
	}
	
}