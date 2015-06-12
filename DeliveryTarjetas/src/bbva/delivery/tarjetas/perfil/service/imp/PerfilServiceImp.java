package bbva.delivery.tarjetas.perfil.service.imp;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rimac.sas.correo.service.CorreoService;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.ListaParametroCursor;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.ldap.service.LdapService;
import bbva.delivery.tarjetas.perfil.bean.LoginWeb;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.PuntoContacto;
import bbva.delivery.tarjetas.perfil.bean.UsuarioLDAP;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.perfil.dao.PerfilDao;
import bbva.delivery.tarjetas.perfil.service.PerfilService;

@Service("perfilService")
@Transactional(propagation=Propagation.SUPPORTS)
public class PerfilServiceImp implements PerfilService {
	
	@Autowired
	private PerfilDao perfilDao;
	
	LdapService ldapService 							= LdapService.getInstance();
	
	private static Logger logger 						= Logger.getLogger(PerfilServiceImp.class.getName());
	
	public void validarEstadoUsuarioweb(UsuarioWeb mntRegUser) {
		perfilDao.obtEstadoUsuarioWeb(mntRegUser);
	}
	
	public void actUsuarioweb(UsuarioWeb terLogIn, String ind) {
		perfilDao.mtnEstadoUsuarioWeb(terLogIn, ind);
	}
	public void regUsuarioweb(UsuarioWeb mntRegUser) throws Exception {
		perfilDao.registrarUsuarioweb(mntRegUser);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void regDireccionUsuarioweb(PuntoContacto puntoContacto) throws Exception {
		perfilDao.regPuntocontactoUsuarioweb(puntoContacto) ;
	}
	
	public void regMediocontactoUsuarioweb(MedioContacto medioContacto, String idpTipoMedioContacto) {
		
		medioContacto.setIdptipmediocontac(idpTipoMedioContacto);
		
		perfilDao.regMediocontactoUsuarioweb(medioContacto);
		
	}
	
	public void obtUsuarioweb(UsuarioWeb usuarioWeb) {
		perfilDao.obtUsuarioweb(usuarioWeb);
	}
	
	public void obtPtocontactoUsuarioweb(PuntoContacto puntoContacto) {
		perfilDao.obtPtocontactoUsuarioweb(puntoContacto);
	}
	
	public void obtMediocontactoUsuarioweb(MedioContacto medioContacto) {
		perfilDao.obtMediocontactoUsuarioweb(medioContacto);
	}
	
	public void obtMediocontactoSecUsuarioweb(MedioContacto medioContacto) {
		perfilDao.obtMediocontactoSecUsuarioweb(medioContacto);
	}
	
	public void obtDirCorrespUsuarioweb(PuntoContacto puntoContacto) {
		perfilDao.obtDirCorrespUsuarioweb(puntoContacto);
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
	
	public String regToken(Integer idetercero, String token, String fecinivig, String userapp){
		return perfilDao.regToken(idetercero, token, fecinivig, userapp);
	}
	
	public Integer obtIdeterceroxToken(String token) {
		return perfilDao.obtIdeterceroxToken(token);
	}
	
	public UsuarioWeb autenticarUsuario(LoginWeb loginWeb)throws Exception {
		
		logger.debug("****ini [] autenticarUsuario****");
		
		String result			= "";
		UsuarioLDAP	userLdap	= null;
		boolean valUsuario		= false;
		Parametro parametro 	= new Parametro();
		UsuarioWeb usuarioWeb	= new UsuarioWeb();

		String idUsuario 		= loginWeb.getUserlogin();
		String password 		= loginWeb.getPasslogin();
				
		usuarioWeb.setNumerodoc(idUsuario);
		
		//VERIFICAR QUE EL USUARIO EXISTA EN LDAP Y OBTENER INFORMACION BASICA
		System.out.println("Validando que el usuario exista en el LDAP");
		userLdap = ldapService.obtUserLDAP(idUsuario);
			
		//EXISTE USUARIO EN LDAP
		if(userLdap != null && userLdap.isValido()){
				
			//VERIFICAR CREDENCIALES (PASSWORD) EN LDAP
			logger.debug("El usuario existe en el LDAP, procedemos a validar sus credenciales");
			valUsuario = ldapService.validarUserLDAP(idUsuario, password);
			logger.debug("Resultado del logueo	" + valUsuario);
			
			usuarioWeb.setIdptipodocumento(userLdap.getDocumentNumber());
			
			obtIdeterceroUsuarioweb(usuarioWeb);
			obtCodexternoUsuarioweb(usuarioWeb);
		
			logger.debug("**** INI ANTES USUARIOWEB ****");
			logger.info(ToStringBuilder.reflectionToString(usuarioWeb,ToStringStyle.MULTI_LINE_STYLE));
			logger.debug("**** FIN ANTES USUARIOWEB ****");
			
			//VALIDAR EL ESTADO DE LA CUENTA DE ACCESO A LA WEB
			validarEstadoUsuarioweb(usuarioWeb);
			
			obtUsuarioweb(usuarioWeb);
			
			String idepuntocontactodir = usuarioWeb.getCursor().get(0).getIdepuntocontactodir();
			String idepuntocontactotelf = usuarioWeb.getCursor().get(0).getIdepuntocontactotelf();
			
			usuarioWeb.setIdepuntocontactodir(idepuntocontactodir);
			usuarioWeb.setIdepuntocontactotelf(idepuntocontactotelf);
			
			logger.debug("**** INI POST USUARIOWEB ****");
			logger.info(ToStringBuilder.reflectionToString(usuarioWeb,ToStringStyle.MULTI_LINE_STYLE));
			logger.debug("**** FIN POST USUARIOWEB ****");
			
			//PASSWORD CORRECTO
			if (valUsuario){
				
				logger.debug("Las credenciales son correctas, usuario autenticado");
					
				//VALIDAR SI EL USUARIO SE ENCUENTRA BLOQUEADO POR BASE DE DATOS 
				if(usuarioWeb.getIndbloqueo()==null || usuarioWeb.getIdepuntocontactodir()==null || usuarioWeb.getIdepuntocontactotelf()==null){
					
					loginWeb.setEscenario(Constants.RSP_LOGIN_USER_REG_DIRECCION);
					result = Constants.RSP_LOGIN_USER_REG_DIRECCION;
					
					logger.debug("El usuario debe registrar su direccion de contacto web " + result);
					
				}else{
					//SI USUARIO SE ENCUENTRA BLOQUEADO SE ENCUENTRA BLOQUEADO SE LE NIEGA EL ACCESO A LA WEB
					if(usuarioWeb.getIndbloqueo().equals("S")){
						
						loginWeb.setEscenario(Constants.RSP_LOGIN_USER_BLOQUEADO);
						loginWeb.setNrointento(usuarioWeb.getNumintentos());
						result = Constants.RSP_LOGIN_USER_BLOQUEADO;
						
						logger.debug("El usuario se encuentra bloqueado por haber superado el limite de reintentos de autenticacion " + result);
						
					}
					else{
						
						//SI ES EL PRIMER LOGUEO DEL USUARIO O AUN NO HA COMPLETADO PASO 2 DEL REGISTRO SE ENVIA A PANTALLA DE PRIMER LOGUEO
						if(usuarioWeb.getNumvisitas()== 0 || 
								usuarioWeb.getIdpstsusuario().equals(Constants.USR_STS_REGTEMPORAL.toString()) ||
								usuarioWeb.getIdpstsusuario().equals(Constants.USR_STS_RNVPASSWORD.toString())){
							
							loginWeb.setEscenario(Constants.RPS_LOGIN_USER_PRIMER_LOGIN);
							result = Constants.RPS_LOGIN_USER_PRIMER_LOGIN;
							
							logger.debug("El usuario debe completar los datos, dado que es su primer logueo " + result);
							
						}else{
							
							loginWeb.setEscenario(Constants.RSP_LOGIN_USER_CRED_VALIDA);
							result = Constants.RSP_LOGIN_USER_CRED_VALIDA;
							
							logger.debug("El usuario puede acceder a la web privada " + result);
							
						}
						
						//SI EL USUARIO NO SE ENCUENTRA BLOQUEADO SE INCREMENTA EL NUMERO DE INGRESOS DEL USUARIO A LA PAGINA -> IND = 1 
						usuarioWeb.setUsuario(Constants.USUARIO_PWEB);
						
						logger.debug("Procediendo a actualizar el numero de visitas del usuario en 1 ");
						actUsuarioweb(usuarioWeb,Constants.ACCION_WEB_ACCESOS_CORRECTOS);						
					}
				}
			}
			
			//PASSWORD INCORRECTO
			else{
				
				System.out.println("Lo sentimos, las credenciales del usuarios no son v�lidas. IDETERCERO : " + usuarioWeb.getIdetercero());
				
				logger.debug("Se inicia el flujo para un usuario con credenciales no v�lidas");
				
				loginWeb.setEscenario(Constants.RSP_LOGIN_USER_CRED_INVALIDA);
				
				//INCREMENTAR EL NUMERO DE ACCESOS INCORRECTOS A LA PAGINA. -> IND = 2 
				if(usuarioWeb.getIndbloqueo()!=null){
					if( usuarioWeb.getIndbloqueo().equals("S") || usuarioWeb.getIdpstsusuario().equals(Constants.RSP_LOGIN_USER_BLOQUEADO)){
										
						if(usuarioWeb.getNumintentos().equals(Constants.MAX_INTENTO_BLOQ)){//manda mail al sexto intento
							
							/* OBTENCION DE PARAMETRO IDMODULO, PARA EL ENVIO DE CORREOS */
							String idmodulo = null;
							
							parametro.setIdeTipPar(Constants.PARAM_IDMODULOS);
							
//							comunService.obtenerListaParametros(parametro);
							
							ListaParametroCursor listaParametroCursor;
							
							for(int i=0;i<parametro.getCursor().size();i++){
								
								listaParametroCursor = (ListaParametroCursor) parametro.getCursor().get(i);
								if (listaParametroCursor.getCodigo().equals(Constants.MODULO_LOGIN)){
									idmodulo = listaParametroCursor.getDescripcion();
								}
							}
							
							logger.debug("El usuario ha sido bloqueado por superar el n�mero m�ximo de reintentos, se le enviar� un mail informando su estado");
//							enviarMail(usuarioWeb, Integer.parseInt(idmodulo), Constants.USR_STS_RVNDATOSCONTACTO);
						}
							
	
						logger.debug("El usuario se encuentra bloqueado, tendra que solicitar un contrase�a temporal");
						loginWeb.setEscenario(Constants.RSP_LOGIN_USER_BLOQUEADO);						
					}
				}
				if(usuarioWeb.getIndbloqueo()!=null){
					Integer intentos = usuarioWeb.getNumintentos()+1;
					loginWeb.setNrointento(intentos);
					usuarioWeb.setUsuario(Constants.USUARIO_PWEB);
					
					logger.debug("Se procede a actualizar la informaci�n del usuario aumentando en uno el numero de intentos de autenticaci�n");
					actUsuarioweb(usuarioWeb, Constants.ACCION_WEB_ACCESOS_INCORRECTOS);
					
					if(intentos.equals(Constants.MAX_INTENTO_BLOQ))
						loginWeb.setEscenario(Constants.RSP_LOGIN_USER_BLOQUEADO);
				}
				
				logger.debug("Se termina con el flujo para un usuario con credenciales no validas");			
			}				
		}		
		//ERROR CONEXCION LDAP
		else{
			if(userLdap!=null){
				//USUARIO NO REGISTRADO
				logger.debug("El usuario no se encuentra registro en nuestros sistemas");
				loginWeb.setEscenario(Constants.RSP_LOGIN_USER_NO_EXISTE);
			}			
		}		
		return usuarioWeb;
	}
}
