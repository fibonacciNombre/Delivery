package bbva.delivery.tarjetas.perfil.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.ListaParametroCursor;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.comun.bean.TransaccionWeb;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.ldap.service.LdapService;
import bbva.delivery.tarjetas.perfil.bean.LoginWeb;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.PuntoContacto;
import bbva.delivery.tarjetas.perfil.bean.UsuarioLDAP;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.perfil.service.PerfilService;

import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class PerfilController extends BaseController {
	
	public static final String WEBAPP_SAS 			= "/" ;
	public static final String COOKIE_USUARIO_SAS 	= "SAS_CODUSUARIO_COOKIE" ;
	public static final String token 				= "F2DA2A4571F9A6BF8B85BB6452CAFAFF";
	
	LdapService ldapService 						= LdapService.getInstance();
/*
	static ComunService comunService 			= ComunService.getInstance();
	static PerfilService perfilService 			= PerfilService.getInstance();
	static SegurosService segurosService 		= SegurosService.getInstance();
*/
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
	
	public String goCambioContrasenia(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goCambioContrasenia	-->		perfil/cambio-contrasenia");
		return "perfil/cambio-contrasenia"; 
	}
	
	public String goCambioPreguntaSecreta(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		System.out.println("goCambioPreguntaSecreta	-->		perfil/cambio-pregunta");
		return "perfil/cambio-pregunta"; 
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
		UsuarioLDAP	userLdap				= null;
		Parametro parametro 				= new Parametro();
		TransaccionWeb transaccion			= new TransaccionWeb();
		UsuarioWeb usuarioWeb				= new UsuarioWeb(request.getParameterMap());
		PuntoContacto puntocontacto			= new PuntoContacto(request.getParameterMap());
		MedioContacto mediocontactotelf		= new MedioContacto(request.getParameterMap());
		MedioContacto mediocontactomovil	= new MedioContacto(request.getParameterMap());
		MedioContacto mediocontactomail		= new MedioContacto(request.getParameterMap());
		String num_movil					= request.getParameter("nummovil");
		
		
		try{
			
			/* SE VALIDA SI EL USUARIO HA SIDO REGISTRADO EN EL LDAP ANTERIORMENTE */
			userLdap = ldapService.obtUserLDAP(usuarioWeb.getNumerodoc());
			
			/* SI EL USUARIO YA SIDO REGISTRADO SE DEVUELVE UN MENSAJE DE ERROR A LA WEB */
			if(userLdap != null && userLdap.isValido()){
				transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
				transaccion.setMessagetx("EL USUARIO HA SIDO REGISTRADO ANTES");
//				result = "{"+ "\"mensaje\"" + ":\"EL USUARIO HA SIDO REGISTRADO ANTES.\"" + "}";
			}
			
			/* SI EL USUARIO NO EXISTE EN EL LDAP SE REALIZAN LAS VALIDACIONES PREVIAS AL REGISTRO */
			else{
				
				perfilService.obtIdeterceroUsuarioweb(usuarioWeb);
				
				if(usuarioWeb.getIdetercero()!=null){
					
					/* OBTENIENDO LA INFORMACION PERSONAL DEL TERCERO - IAA */
					Persona persona = new Persona();
					
					persona.setIdetercero(usuarioWeb.getIdetercero());
					
					perfilService.obtPersona(persona);
					persona = persona.getCursor().get(0);
					
					/* VALIDANDO QUE EL CLIENTE TENGA AL MENOS UNA POLIZA ACTIVA EN ALGUNO DE LOS CORE */
							//if(lista.equals(""))
					/*lista = segurosService.obtPolizasCore(cert, token);
					
					if(lista.equals("1")){*/
						
						/* VALIDANDO QUE LA FECHA DE NACIMIENTO INGRESADA POR EL CLIENTE COINCIDA CON LA FECHA DE NACIMIENTO DEL IAA */
						if(usuarioWeb.getFecnacimiento().equals(persona.getFecnacimiento())){
							
							/* REGISTRANDO EN EL IAA LA DIRECCION DE CONTACTO DE USO PWEB */
							puntocontacto.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
							puntocontacto.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
							puntocontacto.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
							perfilService.regDireccionUsuarioweb(puntocontacto);
							usuarioWeb.setIdepuntocontactodir(puntocontacto.getIdepuntocontacto().toString());
							
							/* REGISTRANDO EN EL IAA EL CORREO ELECTRONICO DE CONTACTO DE USO PWEB */
							mediocontactomail.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
							mediocontactomail.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
							mediocontactomail.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
							perfilService.regMediocontactoUsuarioweb(mediocontactomail,Constants.TMC_EMAIL);
							usuarioWeb.setIdepuntocontactomail(mediocontactomail.getIdepuntocontacto().toString());
							
							/* REGISTRANDO EN EL IAA EL TELEFONO DE CONTACTO DE USO PWEB */
							if(!mediocontactotelf.getNumlocal().equals("")){
								mediocontactotelf.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
								mediocontactotelf.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
								mediocontactotelf.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
								perfilService.regMediocontactoUsuarioweb(mediocontactotelf,Constants.TMC_TELEF);
								usuarioWeb.setIdepuntocontactotelf(mediocontactotelf.getIdepuntocontacto().toString());
							}
							
							/* REGISTRANDO EN EL IAA EL TELEFONO MOVIL DE CONTACTO DE USO PWEB */
							if(!num_movil.equals("")){
								mediocontactomovil.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
								mediocontactomovil.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
								mediocontactomovil.setNumlocal(num_movil);
								mediocontactomovil.setIndmovil("S");
								mediocontactomovil.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
								perfilService.regMediocontactoUsuarioweb(mediocontactomovil,Constants.TMC_TELEF);
								usuarioWeb.setIdepuntocontactotelf(mediocontactomovil.getIdepuntocontacto().toString());
							}
							/* REGISTRANDO EN EL IAA TABLA TERWEB AL CLIENTE REGISTRADO EN LA WEB*/
							usuarioWeb.setUsuario(Constants.USUARIO_PWEB);
							perfilService.regUsuarioweb(usuarioWeb);
							
							/* REGISTRANDO EN LDAP AL CLIENTE REGISTRADO EN LA WEB*/
							ldapService.registrarUser(usuarioWeb, mediocontactomail, mediocontactotelf,persona);
							
							userLdap = ldapService.obtUserLDAP(usuarioWeb.getNumerodoc());
							if(userLdap != null && userLdap.isValido()){
								
								/* ENVIANDO EL MAIL AL CLIENTE CON SU PWD AUTOGENERADO */
								parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
								comunService.obtenerListaParametros(parametro);
								String idmodulo = null;
								ListaParametroCursor listaParametroCursor;
								
								for(int i=0;i<parametro.getCursor().size();i++){
									
									listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
									if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
										idmodulo = listaParametroCursor.getDescripcion();
									}
								}
								
//								perfilService.enviarMail(usuarioWeb, Integer.parseInt(idmodulo), Constants.USR_STS_REGTEMPORAL);
								
								transaccion.setMessagetx("Su registro ha sido exitoso.<br>Por favor revisa tu correo electr�nico para obtener tu contrase�a temporal.");
//								result = "{"+ "\"mensaje\"" + ":\"" + "SU REGISTRO HA SIDO EXITOSO" + "\"" + "}";
						    }else{
						    	transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
								transaccion.setMessagetx("LO SENTIMOS, TUVIMOS PROBLEMAS AL REGISTRARLO. POR FAVOR INTENTELO EN UNOS MINUTOS");
//						    	result = "{"+ "\"mensaje\"" + ":\"" + " LO SENTIMOS, TUVIMOS PROBLEMAS AL REGISTRARLO. POR FAVOR INTENTELO EN UNOS MINUTOS " + "\"" + "}";
						    }
						}else{
							transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
							transaccion.setMessagetx("SUS DATOS PERSONALES DE REGISTRO NO COINCIDEN CON LOS REGISTRADOS EN NUESTRAS BASES DE DATOS");
//							result = "{"+ "\"mensaje\"" + ":\"" + "SUS DATOS PERSONALES DE REGISTRO NO COINCIDEN CON LOS REGISTRADOS EN NUESTRAS BASES DE DATOS" + "\"" + "}";
						}
					/*}else{
						transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
						transaccion.setMessagetx("PARA REGISTRARSE DEBE TENER AL MENOS UNA POLIZA EN RIMAC SEGUROS");
//						result = "{"+ "\"mensaje\"" + ":\"" + "PARA REGISTRARSE DEBE TENER AL MENOS UNA POLIZA EN RIMAC SEGUROS" + "\"" + "}";
					}*/
				}				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug("****PERFILCONTROLLER ejecutando registrarUsuario --- ERROR****");
			logger.debug(e);
			
			transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			transaccion.setMessagetx("LO SENTIMOS, TUVIMOS PROBLEMAS AL REGISTRARLO. POR FAVOR INTENTELO EN UNOS MINUTOS");
			
//			result = "{"+ "\"mensaje\"" + ":\" LO SENTIMOS, TUVIMOS PROBLEMAS AL REGISTRARLO. POR FAVOR INTENTELO EN UNOS MINUTOS " +  "," +
//					 "\"error\"" + ":\"" + e + "\"" +
//					 "}";
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		
		logger.info("*** fin regUsuario *** ");
		this.escribirTextoSalida(response, result);
	}
	
	public void regDireccion(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini regDireccion *** ");
		
		String result 						= "";
		Parametro parametro 				= new Parametro();
		TransaccionWeb transaccion			= new TransaccionWeb();
		HttpSession session					= request.getSession();
		UsuarioWeb usuario					= new UsuarioWeb(request.getParameterMap());
		PuntoContacto puntocontacto			= new PuntoContacto(request.getParameterMap());
		MedioContacto mediocontactotelf		= new MedioContacto(request.getParameterMap());
		MedioContacto mediocontactomovil	= new MedioContacto(request.getParameterMap());
		MedioContacto mediocontactomail		= new MedioContacto(request.getParameterMap());
		LoginWeb loginWeb 					= (LoginWeb) session.getAttribute(Constants.REQ_ATTR_LOGINWEB);
		String num_movil					= request.getParameter("nummovil");
		String jsonTransaccion				= "\"Tx\":[";
		try{
			
			if(usuario.getIdetercero()!=null){
				
				/* REGISTRANDO LA INFORMACION DE CONTACTO DEL USUARIOWEB */
				Integer idepuntocontactomail = usuario.getIdepuntocontactomail().equals("")?null:Integer.parseInt(usuario.getIdepuntocontactomail());
				mediocontactomail.setIdepuntocontacto(idepuntocontactomail);
				mediocontactomail.setUsucreacion(Constants.USUARIO_PWEB);
				mediocontactomail.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
				
				perfilService.regMediocontactoUsuarioweb(mediocontactomail,Constants.TMC_EMAIL);
				usuario.setIdepuntocontactomail(mediocontactomail.getIdepuntocontacto().toString());
				
				puntocontacto.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
				puntocontacto.setIdepuntocontacto(null);
				puntocontacto.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
				perfilService.regDireccionUsuarioweb(puntocontacto);
				usuario.setIdepuntocontactodir(puntocontacto.getIdepuntocontacto().toString());
				
				if(!mediocontactotelf.getNumlocal().equals("")){
					mediocontactotelf.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
					mediocontactotelf.setIdepuntocontacto(null);
					mediocontactotelf.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
					perfilService.regMediocontactoUsuarioweb(mediocontactotelf,Constants.TMC_TELEF);
					usuario.setIdepuntocontactotelf(mediocontactotelf.getIdepuntocontacto().toString());
				}
				
				/* REGISTRANDO EN EL IAA EL TELEFONO MOVIL DE CONTACTO DE USO PWEB */
				if(!num_movil.equals("")){
					mediocontactomovil.setIdetercero(Integer.parseInt(usuario.getIdetercero().toString()));
					mediocontactomovil.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
					mediocontactomovil.setNumlocal(num_movil);
					mediocontactomovil.setIndmovil("S");
					mediocontactomovil.setIdepuntocontacto(null);
					mediocontactomovil.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
					perfilService.regMediocontactoUsuarioweb(mediocontactomovil,Constants.TMC_TELEF);
					usuario.setIdepuntocontactotelf(mediocontactomovil.getIdepuntocontacto().toString());
				}
				
				usuario.setUsuario(Constants.USUARIO_PWEB);
				
				/* REGISTRANDO USUARIOWEB COMO USUARIO EN EL IAA*/
				perfilService.regUsuarioweb(usuario);
				perfilService.actUsuarioweb(usuario, Constants.ACCION_WEB_ACCESOS_CORRECTOS);
				
				/* ACTUALIZACION EL ESTADO DEL USUARIOWEB*/
				usuario.setIdpstsusuario(null);
				perfilService.actUsuarioweb(usuario, Constants.ACCION_WEB_RENOVAR_DATOSCONTACTO);
				
				/* ENVIANDO EL MAIL AL CLIENTE */
				parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
				comunService.obtenerListaParametros(parametro);
				String idmodulo = null;
				ListaParametroCursor listaParametroCursor;
				
				for(int i=0;i<parametro.getCursor().size();i++){
					
					listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
					if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
						idmodulo = listaParametroCursor.getDescripcion();
					}
				}
				
//				perfilService.enviarMail(usuario, Integer.parseInt(idmodulo), Constants.USR_STS_RVNDATOSCONTACTO);
				
				/* REACTUALIZANDO EL ESTADO DEL USUARIOWEB*/
				if(idepuntocontactomail==null){
					usuario.setIdpstsusuario(Constants.USR_STS_REGDEFINITIVO.toString());
				}else{
					usuario.setIdpstsusuario(Constants.USR_STS_REGTEMPORAL.toString());
				}
				
				perfilService.actUsuarioweb(usuario, Constants.ACCION_WEB_RENOVAR_DATOSCONTACTO);
				
				/* ACTUALIZANDO EL ESCENARIO DEL LOGINWEB */
				
				if(idepuntocontactomail==null){
					loginWeb.setEscenario(Constants.RSP_LOGIN_USER_CRED_VALIDA);
				}else{
					loginWeb.setEscenario(Constants.RPS_LOGIN_USER_PRIMER_LOGIN);
				}
				session.setAttribute(Constants.REQ_ATTR_LOGINWEB, loginWeb);
				
				transaccion.setMessagetx("Tus datos de contacto han sido registrados satisfactoriamente.");
			}			
		}
		catch(Exception e){
			transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			e.printStackTrace();
			logger.debug(e);
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		jsonTransaccion += UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		jsonTransaccion += "]";
		
		result = "{"
				+ "\"escenario\""  + ":\"" + loginWeb.getEscenario()		    + "\"" + ","
				+ jsonTransaccion +  
			"}";
				
		logger.info("*** fin regDireccion *** ");
		
		this.escribirTextoSalida(response, result);
	}
	
	public void enviarContrasenaTemp(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini enviarContrasenaTemp *** ");
		
		String result 					= "";
		String idmodulo 				= null;
		TransaccionWeb transaccion		= new TransaccionWeb();
		UsuarioWeb usuario 				= new UsuarioWeb(request.getParameterMap());
		MedioContacto mediocontactomail	= new MedioContacto(request.getParameterMap());
		Parametro parametro 			= new Parametro();
		
		try{
			
			usuario.setUsuario(Constants.USUARIO_PWEB);
			
			if(!usuario.getIdepuntocontactomail().equals("")){
				mediocontactomail.setIdepuntocontacto(Integer.parseInt(usuario.getIdepuntocontactomail()));
				mediocontactomail.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
				perfilService.regMediocontactoUsuarioweb(mediocontactomail,Constants.TMC_EMAIL);
			}else{
				
				mediocontactomail.setUsucreacion(Constants.USUARIO_PWEB);
				mediocontactomail.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
				
		        perfilService.regMediocontactoUsuarioweb(mediocontactomail,Constants.TMC_EMAIL);
		        usuario.setIdepuntocontactomail(mediocontactomail.getIdepuntocontacto().toString());
		        
		        usuario.setUsuario(Constants.USUARIO_PWEB);
		        
		        perfilService.obtIdeterceroUsuarioweb(usuario);
		        perfilService.regUsuarioweb(usuario);
				
			}
			
			perfilService.actUsuarioweb(usuario,Constants.ACCION_WEB_RENOVAR_PASSWORD);
			
			ldapService.actualizarLdapUser(usuario, null, mediocontactomail, Constants.ACCION_LDAP_PWDTEMPORAL);
			
			parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
			comunService.obtenerListaParametros(parametro);
			
			ListaParametroCursor listaParametroCursor;
			
			for(int i=0;i<parametro.getCursor().size();i++){
				
				listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
				if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
					idmodulo = listaParametroCursor.getDescripcion();
				}
			}
//			perfilService.enviarMail(usuario, Integer.parseInt(idmodulo),Constants.USR_STS_RNVPASSWORD);
				
//			result = "{"+ "\"result\"" + ":\"" + "OK" + "\"" + "}";
			transaccion.setMessagetx("Se ha enviado una contrase�a temporal al correo " + mediocontactomail.getEmail());
		
		}catch(Exception e){
			transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			e.printStackTrace();
			logger.debug(e);
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		
		logger.info("*** fin enviarContrasenaTemp *** ");
		
		this.escribirTextoSalida(response, result);
	}
	
	public void enviarMailActContacto(HttpServletRequest request,
			HttpServletResponse response)throws Exception {

		logger.info("*** ini enviarMailActContacto *** ");
		
		String estadoprev;
		String idmodulo 		= null;
		String result 			= "";
		Parametro parametro 	= new Parametro();
		UsuarioWeb usuario 		= new UsuarioWeb(request.getParameterMap());
		
		try{
			
			perfilService.validarEstadoUsuarioweb(usuario);
			estadoprev = usuario.getIdpstsusuario();
			
			usuario.setIdpstsusuario(null);
			usuario.setUsuario(Constants.USUARIO_PWEB);
			perfilService.actUsuarioweb(usuario,Constants.ACCION_WEB_RENOVAR_DATOSCONTACTO);
			
			parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
			comunService.obtenerListaParametros(parametro);
			
			ListaParametroCursor listaParametroCursor;
			
			for(int i=0;i<parametro.getCursor().size();i++){
				
				listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
				if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
					idmodulo = listaParametroCursor.getDescripcion();
				}
			}
//			perfilService.enviarMail(usuario, Integer.parseInt(idmodulo),Constants.USR_STS_RVNDATOSCONTACTO);
			
			usuario.setIdpstsusuario(estadoprev);
			usuario.setUsuario(Constants.USUARIO_PWEB);
			perfilService.actUsuarioweb(usuario,Constants.ACCION_WEB_RENOVAR_DATOSCONTACTO);
				
			result = "{"+ "\"result\"" + ":\"" + "OK" + "\"" + "}";
				
		}
		catch(Exception e){
			e.printStackTrace();
			logger.debug(e);
		}
		
		logger.info("*** fin enviarMailActContacto *** ");
		
		this.escribirTextoSalida(response, result);
	}
	
	public void actContrasena(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		logger.info("*** ini actContrasena *** ");
		
		String result				= "";
		Parametro parametro 		= new Parametro();
		HttpSession session			= request.getSession();
		TransaccionWeb transaccion	= new TransaccionWeb();
		UsuarioWeb usuarioWeb		= new UsuarioWeb(request.getParameterMap());
		
		try{
			
			perfilService.obtUsuarioweb(usuarioWeb);
			
			if(!usuarioWeb.getCursor().isEmpty()){
				
				//SE ACTUALIZA EL PASSWORD EN EL LDAP
				ldapService.actualizarLdapUser(usuarioWeb, null, null, Constants.ACCION_LDAP_MODIFPWD);
				
				//SE ACTUALIZA EL ESTADO EL USUARIO EN LA BD DEL IAA
				usuarioWeb.setUsuario(Constants.USUARIO_PWEB);
				perfilService.actUsuarioweb(usuarioWeb,Constants.ACCION_WEB_PRIMER_LOGIN);
				
				//SE ENVIA UN CORREO ELECTRONICO AL USUARIO NOTIFICANDO EL ESTADO DE SU TRANSACCION
				parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
				comunService.obtenerListaParametros(parametro);
				
				String idmodulo = null;
				
				ListaParametroCursor listaParametroCursor;
				
				for(int i=0;i<parametro.getCursor().size();i++){
					
					listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
					if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
						idmodulo = listaParametroCursor.getDescripcion();
					}
				}
				
//				perfilService.enviarMail(usuarioWeb, Integer.parseInt(idmodulo),Constants.USR_STS_REGDEFINITIVO);
				
				String indmobile 		= (String) session.getAttribute(Constants.REQ_SESSION_INDMOBILE);
				String token_usuario 	= (String) session.getAttribute(Constants.REQ_SESSION_TOKENID);
				
				LoginWeb loginWeb = (LoginWeb) session.getAttribute(Constants.REQ_ATTR_LOGINWEB);
				loginWeb.setEscenario(Constants.RSP_LOGIN_USER_CRED_VALIDA);
				session.setAttribute(Constants.REQ_ATTR_LOGINWEB, loginWeb);
//				session.setAttribute(Constants.REQ_ATTR_VALIDACION_LOGIN, Constants.RSP_LOGIN_USER_CRED_VALIDA);
				
				if(indmobile!=null && indmobile.equals("1"))
					response.setHeader("Location", "rimac:/?token="+token_usuario);
			}
			
		}catch(Exception e){
			transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			transaccion.setMessagetx("Se produjo un error durante la actualizaci�n de la contrase�a, por favor intentelo en unos minutos.");
			
			logger.debug(e);
			e.printStackTrace();
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		
		logger.info("*** fin actContrasena *** ");
		
		this.escribirTextoSalida(response, result);
	}
		
	public void obtDatosUsuarioNroDoc(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini obtDatosUsuarioNroDoc *** ");
		
		UsuarioWeb usuario;		
		String result				= "";
		UsuarioLDAP	userLdap		= null;	
		TransaccionWeb transacccion	= new TransaccionWeb();
		String jsonUsuario			= "\"Usuarioweb\":[";
		String jsonUsuarioLdap		= "\"UsuarioLdap\":[";
		String jsonTransaccion		= "\"Tx\":[";
		
		usuario = new UsuarioWeb(request.getParameterMap());
		
		userLdap = ldapService.obtUserLDAP(usuario.getNumerodoc());
		
		if(userLdap != null && userLdap.isValido()){
			
			logger.debug("**** INI USERLDAP ****");
			logger.info(ToStringBuilder.reflectionToString(userLdap,ToStringStyle.MULTI_LINE_STYLE));
			logger.debug("**** FIN USERLDAP ****");
			
			userLdap.setSecurAnsw(null);
			jsonUsuarioLdap += UtilWeb.objectToJson(userLdap, null, UsuarioLDAP.class.getName());
			jsonUsuarioLdap += "]";
			
			usuario.setIdptipodocumento(userLdap.getDocumentNumber());
			
			perfilService.obtIdeterceroUsuarioweb(usuario);
			
			perfilService.obtUsuarioweb(usuario);
			
			if(!usuario.getCursor().isEmpty()){
				
				usuario = usuario.getCursor().get(0);
				
				usuario.setPregseg(userLdap.getSecurQues());
				usuario.setRespseg(userLdap.getSecurAnsw());
				
				jsonUsuario += UtilWeb.objectToJson(usuario, null, UsuarioWeb.class.getName());
				jsonUsuario += "]";
				
				String jsonCorreo			= "\"Correoweb\":[";
				MedioContacto correoWeb 	= new MedioContacto();
				
				if(usuario.getIdepuntocontactomail()!=null){
					correoWeb.setIdepuntocontacto(Integer.parseInt(usuario.getIdepuntocontactomail()));
					perfilService.obtMediocontactoUsuarioweb(correoWeb);
				}
				
				if(correoWeb.getCursor()!=null){
					correoWeb = correoWeb.getCursor().get(0);
					jsonCorreo += UtilWeb.objectToJson(correoWeb,null,MedioContacto.class.getName());
				}
				
				jsonCorreo += "]";
				
				jsonTransaccion += UtilWeb.objectToJson(transacccion, null, TransaccionWeb.class.getName());
				jsonTransaccion += "]";
				
				result = "{"
							+ jsonTransaccion + ","
							+ jsonUsuario + ","
							+ jsonUsuarioLdap + ","
							+ jsonCorreo + 
						"}";			
			}
		}else{
			
			transacccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			transacccion.setMessagetx("EL USUARIO NO SE ENCUENTRA REGISTRADO EN LA WEB");
			
			jsonTransaccion += UtilWeb.objectToJson(transacccion, null, TransaccionWeb.class.getName());
			jsonTransaccion += "]";
			
			result = "{"+ jsonTransaccion +"}";
		}
		
		logger.info("*** fin obtDatosUsuarioNroDoc *** ");
		
		this.escribirTextoSalida(response, result);
	}
		
	public void validarRptaPregSeguridad(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini validarRptaPregSeguridad *** ");
		
		UsuarioWeb usuario;
		String result 				= "";
		UsuarioLDAP	userLdap		= null;
		TransaccionWeb transaccion	= new TransaccionWeb();
		
		usuario = new UsuarioWeb(request.getParameterMap());
		
		userLdap = ldapService.obtUserLDAP(usuario.getNumerodoc());
		
		if(userLdap != null && userLdap.isValido()){
			
			boolean validacionRpta = usuario.getRespseg().equals(userLdap.getSecurAnsw());
			
			if(!validacionRpta){
				transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
				transaccion.setMessagetx("Su respuesta no es correcta");
			}
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		
		logger.info("*** fin validarRptaPregSeguridad *** ");
		
		this.escribirTextoSalida(response, result);
	}
		
	public void mntContrasenaPerfil(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("*** ini mntContrasenaPerfil *** ");
		
		UsuarioWeb userNewData;
		String result 					= "";
		boolean valUsuario				= false;
		TransaccionWeb transaccion 		= new TransaccionWeb();
		
		String password					= (String) request.getParameter("password");
		String newpassword				= (String) request.getParameter("newpassword");
		
		userNewData = new UsuarioWeb(request.getParameterMap());
		Parametro parametro 				= new Parametro();
		UsuarioWeb usuarioWeb	= new UsuarioWeb();
		
		userNewData.setPassword(password);
		userNewData.setNewpassword(newpassword);
		
		//VERIFICAR CREDENCIALES (PASSWORD) EN LDAP
		valUsuario = ldapService.validarUserLDAP(userNewData.getNumerodoc(), userNewData.getPassword());
		
		//PASSWORD CORRECTO
		if (valUsuario){			
			ldapService.actualizarLdapUser(userNewData, null, null, Constants.ACCION_LDAP_MODIFPWD);			
			transaccion.setMessagetx("Tu contrase�a ha sido actualizada satisfactoriamente.");
			
			usuarioWeb.setIdetercero(userNewData.getIdetercero());
			
			//perfilService.obtUsuarioweb(usuarioWeb);
			
			/* ACTUALIZACION EL ESTADO DEL USUARIOWEB*/
			usuarioWeb.setIdpstsusuario(null);
			usuarioWeb.setUsuario(Constants.USUARIO_PWEB);//2904jllempen
			perfilService.actUsuarioweb(usuarioWeb, Constants.ACCION_WEB_MODIFPASSWORD);
			
			/* ENVIANDO EL MAIL AL CLIENTE */
			parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
			comunService.obtenerListaParametros(parametro);
			String idmodulo = null;
			ListaParametroCursor listaParametroCursor;
			
			for(int i=0;i<parametro.getCursor().size();i++){
				
				listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
				if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
					idmodulo = listaParametroCursor.getDescripcion();
				}
			}
			
//			perfilService.enviarMail(usuarioWeb, Integer.parseInt(idmodulo), Constants.USR_STS_MODIFPASSWORD);
			
			/* REACTUALIZANDO EL ESTADO DEL USUARIOWEB*/
			usuarioWeb.setIdpstsusuario(userNewData.getIdpstsusuario());
			
			perfilService.actUsuarioweb(usuarioWeb, Constants.ACCION_WEB_MODIFPASSWORD);
			
		}else{
			transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			transaccion.setMessagetx("Su contrase�a actual no coincide con la registrada en nuestros sistemas.");
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		logger.info("*** ini mntContrasenaPerfil *** ");
		
		this.escribirTextoSalida(response, result);
	}
	
	public void mntPregRptaSeguridad(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini mntPregRptaSeguridad *** ");
		
		String result 				= "";
		TransaccionWeb transaccion 	= new TransaccionWeb();
		UsuarioWeb usuario 			= new UsuarioWeb(request.getParameterMap());
		Parametro parametro 		= new Parametro();
		UsuarioWeb usuarioWeb		= new UsuarioWeb();
		
		try{			
			ldapService.actualizarLdapUser(usuario, null, null, Constants.ACCION_LDAP_MODIFPREGSEG);
			
			
			/* ACTUALIZACION EL ESTADO DEL USUARIOWEB*/
			usuarioWeb.setIdetercero(usuario.getIdetercero());
			usuarioWeb.setIdpstsusuario(null);
			usuarioWeb.setUsuario(Constants.USUARIO_PWEB);//2904jllempen
			perfilService.actUsuarioweb(usuarioWeb, Constants.ACCION_WEB_MODIFPREGSEC);
			
			/* ENVIANDO EL MAIL AL CLIENTE */
			parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
			comunService.obtenerListaParametros(parametro);
			String idmodulo = null;
			ListaParametroCursor listaParametroCursor;
			
			for(int i=0;i<parametro.getCursor().size();i++){
				
				listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
				if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
					idmodulo = listaParametroCursor.getDescripcion();
				}
			}
			
//			perfilService.enviarMail(usuarioWeb, Integer.parseInt(idmodulo), Constants.USR_STS_MODIFPREGSEC);
			
			/* REACTUALIZANDO EL ESTADO DEL USUARIOWEB*/
			usuarioWeb.setIdpstsusuario(usuario.getIdpstsusuario());
			
			perfilService.actUsuarioweb(usuarioWeb, Constants.ACCION_WEB_MODIFPREGSEC);
		}
		catch(Exception e){
			transaccion.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			transaccion.setMessagetx("Se produjo un error durante el cambio de Pregunta y Respuesta de Seguridad. Intentelo nuevamente.");
			logger.error("ERROR - ",e.getCause());
			e.printStackTrace();
		}
		
		logger.debug("**** INI TRANSACCIONWEB****");
		logger.info(ToStringBuilder.reflectionToString(transaccion,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN TRANSACCIONWEB ****");
		
		result = commons.web.UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		logger.info("*** ini mntPregRptaSeguridad *** ");
		
		this.escribirTextoSalida(response, result);
	}
		
	public void mntDatosContacto(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		logger.info("*** ini mntDatosContacto *** ");
		
		String result 						= "";
		TransaccionWeb transaccion			= new TransaccionWeb();
		UsuarioWeb usuarioWeb				= new UsuarioWeb(request.getParameterMap());
		PuntoContacto puntocontacto			= new PuntoContacto(request.getParameterMap());
		MedioContacto mediocontactotelf		= new MedioContacto(request.getParameterMap());
		MedioContacto mediocontactotelfSec	= new MedioContacto();
		MedioContacto mediocontactomail		= new MedioContacto(request.getParameterMap());
		String idepuntocontactotelfSec		= request.getParameter("idepuntocontactotelfSec");
		String numlocalSec 					= request.getParameter("numlocalSec");
		
		puntocontacto.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
		puntocontacto.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
		puntocontacto.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactodir()));
		puntocontacto.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
		perfilService.regDireccionUsuarioweb(puntocontacto);
		
		/* REGISTRANDO EN EL IAA EL CORREO ELECTRONICO DE CONTACTO DE USO PWEB */
		mediocontactomail.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
		mediocontactomail.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
		mediocontactomail.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactomail()));
		mediocontactomail.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
		perfilService.regMediocontactoUsuarioweb(mediocontactomail,Constants.TMC_EMAIL);
		
		/* REGISTRANDO EN EL IAA EL TELEFONO DE CONTACTO DE USO PWEB */
		
		mediocontactotelf.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
		mediocontactotelf.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
		mediocontactotelf.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactotelf()));
		mediocontactotelf.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
		perfilService.regMediocontactoUsuarioweb(mediocontactotelf,Constants.TMC_TELEF);
		
		mediocontactotelfSec.setIdetercero(Integer.parseInt(usuarioWeb.getIdetercero().toString()));
		mediocontactotelfSec.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
		mediocontactotelfSec.setIdepuntocontacto(idepuntocontactotelfSec.equals("")?null:Integer.parseInt(idepuntocontactotelfSec));
		mediocontactotelfSec.setNumlocal(numlocalSec);
		mediocontactotelfSec.setUsucreacion(Constants.USUARIO_PWEB);//2904jllempen
		perfilService.regMediocontactoUsuarioweb(mediocontactotelfSec,Constants.TMC_TELEF);

		usuarioWeb.setIdepuntocontactotelf(mediocontactotelf.getIdepuntocontacto().toString());
		
		result = UtilWeb.objectToJson(transaccion, null, TransaccionWeb.class.getName());
		
		logger.info("*** fin mntDatosContacto *** ");
		this.escribirTextoSalida(response, result);
	}
	
	public void obtDatosUsuarioSesion(HttpServletRequest request,
			HttpServletResponse response){
		
		String result 			= "";
		HttpSession session		= request.getSession();
		LoginWeb loginWeb	 	= (LoginWeb)session.getAttribute(Constants.REQ_ATTR_LOGINWEB);
		UsuarioWeb usrWebTemp	= (UsuarioWeb)session.getAttribute(Constants.REQ_ATTR_USUARIOWEB);
		
		logger.debug("**** INI LOGINWEB SESSION ****");
		logger.info(ToStringBuilder.reflectionToString(loginWeb,ToStringStyle.MULTI_LINE_STYLE));
		logger.debug("**** FIN LOGINWEB SESSION ****");
		
		System.out.println("*** DATOS USUARIO LOGUEADO ***");
		
		String jsonUsuario		= "\"Usuarioweb\":[";
		
		UsuarioWeb usuarioWeb 	= (UsuarioWeb)session.getAttribute(Constants.REQ_ATTR_USUARIOWEB);
		
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
			perfilService.obtPtocontactoUsuarioweb(puntoContacto);
			
			if(puntoContacto.getCursor()!=null){
				puntoContacto = puntoContacto.getCursor().get(0);
				puntoContacto.setDireccioncompleta(puntoContacto.getDireccioncompleta().trim());
				
				session.setAttribute(Constants.REQ_SESSION_DIRECCION, puntoContacto);
				
				jsonDireccion += UtilWeb.objectToJson(puntoContacto,null,PuntoContacto.class.getName());
			}			
		}
		
		jsonDireccion += "]";
		
		System.out.println("*** CORREO WEB ***");
		
		String jsonCorreo			= "\"Correoweb\":[";
		
		if(usuarioWeb.getIdepuntocontactomail()!=null){
			
			MedioContacto correoWeb 	= new MedioContacto();
			
			correoWeb.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactomail()));
			perfilService.obtMediocontactoUsuarioweb(correoWeb);
			
			if(correoWeb.getCursor()!=null){
				correoWeb = correoWeb.getCursor().get(0);
				
				session.setAttribute(Constants.REQ_SESSION_CORREO, correoWeb);
				
				jsonCorreo += UtilWeb.objectToJson(correoWeb,null,MedioContacto.class.getName());
			}
		}
		
		jsonCorreo += "]";
		
		System.out.println("*** TELEFONO WEB ***");
		
		String jsonTelefono			= "\"Telefonoweb\":[";
		
		if(usuarioWeb.getIdepuntocontactotelf()!=null){
			
			MedioContacto telefonoWeb 	= new MedioContacto();
			
			telefonoWeb.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactotelf()));
			perfilService.obtMediocontactoUsuarioweb(telefonoWeb);
			
			if(telefonoWeb.getCursor()!=null){
				telefonoWeb = telefonoWeb.getCursor().get(0);
				
				session.setAttribute(Constants.REQ_SESSION_TELEFONO, telefonoWeb);
				
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
			String[] usomediocontacto = Constants.USO_DIRECCION_PWEB.replace("|", "<>").split("<>");
			telefonoSecWeb.setCadidpusopuntocontacto(usomediocontacto[0]);
			telefonoSecWeb.setIdptipmediocontac(Constants.TMC_TELEF);
			perfilService.obtMediocontactoSecUsuarioweb(telefonoSecWeb);
			telefonoWebSesion = (MedioContacto) session.getAttribute(Constants.REQ_SESSION_TELEFONO);
			
			
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
					+ "\"indmobile\""  + ":\"" + session.getAttribute(Constants.REQ_SESSION_INDMOBILE)		 	+ "\"" + ","
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
	
	@SuppressWarnings("unchecked")
	public void obtDatosUsuarioSesionxToken(HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
		
		String result 			= "";
//		HttpSession session		= request.getSession();
		String parametros		= request.getParameter("datos");//session.getAttribute("TOKEN").toString();
		String token			= "";

		HashMap<String, Object> mapFiltros = new ObjectMapper().readValue(
				parametros, HashMap.class);
		
		token = (String) mapFiltros.get("token");
		
		Integer idetercero = perfilService.obtIdeterceroxToken(token);
		
		System.out.println("*** DATOS PERSONA ***");
		
		String jsonPersona		= "\"Personaweb\":[";
		Persona personaTemp 	= new Persona();
		Persona personaWeb 		= new Persona();
		
		personaTemp.setIdetercero(idetercero);
		perfilService.obtPersona(personaTemp);
		
		if(personaTemp.getCursor()!=null){
			personaTemp = personaTemp.getCursor().get(0);
			
			personaWeb.setApepaterno(personaTemp.getApepaterno());
			personaWeb.setApematerno(personaTemp.getApematerno());
			personaWeb.setNombre(personaTemp.getNombre());
			personaWeb.setFecnacimiento(personaTemp.getFecnacimiento());
			personaWeb.setIdptipodocumento(personaTemp.getIdptipodocumento());
			personaWeb.setNumerodoc(personaTemp.getNumerodoc());
			personaWeb.setIdpgenero(personaTemp.getIdpgenero());
			
//			session.setAttribute(Constants.REQ_SESSION_PERSONA, personaWeb);
			
			jsonPersona += UtilWeb.objectToJson(personaWeb,null,Persona.class.getName());
		}
		
		jsonPersona += "]";
		
		System.out.println("*** DATOS USUARIO LOGUEADO ***");
		
		String jsonUsuario		= "\"Usuarioweb\":[";
		UsuarioWeb usuarioWeb 	= new UsuarioWeb();
		
		usuarioWeb.setIdetercero(idetercero);
		perfilService.obtUsuarioweb(usuarioWeb);
		
		if(usuarioWeb.getCursor()!=null){
			usuarioWeb = usuarioWeb.getCursor().get(0);
			usuarioWeb.setNumerodoc(personaWeb.getNumerodoc());
			usuarioWeb.setIdptipodocumento(personaWeb.getIdptipodocumento().toString());
			
//			session.setAttribute(Constants.REQ_SESSION_USUARIO, usuarioWeb);
			
			jsonUsuario += UtilWeb.objectToJson(usuarioWeb,null,UsuarioWeb.class.getName());
		}
		
		jsonUsuario += "]";
		
		System.out.println("*** DIRECCION WEB ***");
		
		String jsonDireccion		= "\"Direccionweb\":[";
		
		if(usuarioWeb.getIdepuntocontactodir()!=null){
			
			PuntoContacto puntoContacto = new PuntoContacto();
			
			puntoContacto.setIdepuntocontacto(Integer.parseInt(usuarioWeb.getIdepuntocontactodir()));
			perfilService.obtPtocontactoUsuarioweb(puntoContacto);
			
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
			perfilService.obtMediocontactoUsuarioweb(correoWeb);
			
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
			perfilService.obtMediocontactoUsuarioweb(telefonoWeb);
			
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
			
			telefonoSecWeb.setIdetercero(idetercero);
			String[] usomediocontacto = Constants.USO_DIRECCION_PWEB.replace("|", "<>").split("<>");
			telefonoSecWeb.setCadidpusopuntocontacto(usomediocontacto[0]);
			telefonoSecWeb.setIdptipmediocontac(Constants.TMC_TELEF);
			perfilService.obtMediocontactoSecUsuarioweb(telefonoSecWeb);
//			telefonoWebSesion = (MedioContacto) session.getAttribute(Constants.REQ_SESSION_TELEFONO);
			
			
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
//					+ "\"validacion\"" + ":\"" + ((LoginWeb)session.getAttribute(Constants.REQ_ATTR_LOGINWEB)).getEscenario() 	+ "\"" + ","
					+ "\"idetercero\"" + ":\"" + idetercero 	 	+ "\"" + ","
					+ "\"codexterno\"" + ":\"" + usuarioWeb.getCodexterno()+ "\"" + ","
					+ "\"numerodoc\""  + ":\"" + usuarioWeb.getNumerodoc()+ "\"" + ","
//					+ "\"mobile\""  + ":\"" + session.getAttribute("MOBILE")+ "\"" + ","
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
