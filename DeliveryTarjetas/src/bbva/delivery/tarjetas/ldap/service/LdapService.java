package bbva.delivery.tarjetas.ldap.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

import sas.seguridad.commons.Encriptacion;
import ws.synopsis.frmwrk.common.InitParameter;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.bean.CryptoWeb;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.UsuarioLDAP;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.util.ConfigurationProperties;

public class LdapService {
	
	private static LdapService instance;
	
	private static Logger logger 						= Logger.getLogger(LdapService.class.getName());

//	private static final Properties properties = ConfigurationProperties
//			.getExternalProperties(Constantes.ARCH_SAS_CONFIG_PROPERTIES);
	
	private static final Properties properties 		= ConfigurationProperties.getExternalProperties(System.getProperty(Constants.RUTA_ARCH_DEFAULT)+ 
																										Constants.RUTA_PROPERTIES_CONFIGURACION,
																										Constants.FILE_CONFIGURACION_DELIVERY_TARJETAS);
	
	private static String[] userAttributes 			= {"cn", 
			        									  "givenname", 
			        									  "uid",
			        									  "sn",
			        									  "documentNumber",
			        									  "securityQuestion",
			        									  "securityAnswer",
			        									  "mail"};
	
	/**
	 * Constructor privado para que no se creen instancias desde otras clases
	 */
	private LdapService() {
		super();
	}
	
	/**
	 * M�todo que devuelve la intancia de la clase creada
	 * @return Instancia de la clase
	 */
	public static LdapService getInstance() {
		if(instance == null) {
			instance = new LdapService();
		}
		
		return instance;
	}
	
	/**
	 * M�todo que permite obtener la conexi�n al LDAP previa
	 * validaci�n y el password y usuario con que se esta ingresando
	 * @param userName Nombre del usuario que se conecta al LDAP
	 * @param password Clave del usuario que se conecta al LDAP
	 * @return Conexi�n al servidor del LDAP
	 */
	public DirContext getConnection(String userName, String password)
			throws Exception {
		return getConnection(userName, password, null);
	}
	
	/**
	 * M�todo que permite obtener la conexi�n al LDAP previa
	 * validaci�n y el password y usuario con que se esta ingresando
	 * @param userName Nombre del usuario que se conecta al LDAP
	 * @param password Clave del usuario que se conecta al LDAP
	 * @param suffixName Dominio al que permtece el usuario
	 * @return Conexi�n al servidor del LDAP
	 */
	public DirContext getConnection(String userName, String password,
			String suffixName) throws Exception {
		if(suffixName == null || "".equals(suffixName.trim())) {
			suffixName = properties.getProperty(ConstantsProperties.TDS_LDAP_SUFFIX_NAME);
		}
		
		return getConnection(userName, password, suffixName, null);
	}
	
	/**
	 * M�todo que permite obtener la conexi�n al Active Directory previa
	 * validaci�n y el password y usuario con que se esta ingresando
	 * @param userName Nombre del usuario que se conecta al LDAP
	 * @param password Clave del usuario que se conecta al LDAP
	 * @param suffixName Dominio al que permtece el usuario
	 * @param server Servidor donde esta instalado el Directorio Activo
	 * @return Conexi�n al servidor del Active Directory
	 */
	public DirContext getConnection(String userName, String password,
			String suffixName, String ldapHost) throws Exception {
		DirContext ctx = null;
		Hashtable<String, Object> env = null;
		String ctxFactory = null;
		String principalName = null;
		
		if(ldapHost == null || "".equals(ldapHost.trim())) {
			ldapHost = properties.getProperty(ConstantsProperties.TDS_LDAP_HOST);
		}
		
		ctxFactory = properties.getProperty(ConstantsProperties.TDS_LDAP_FACTORY);
		principalName = userName;// + suffixName;
		
		env = new Hashtable<String, Object>();
		
		System.out.println("LDPA		: " + ldapHost	);
		System.out.println("USER		: " + principalName	);
		System.out.println("PWD			: " + password	);
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
		env.put(Context.PROVIDER_URL, ldapHost);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, principalName);
		env.put(Context.SECURITY_CREDENTIALS, password);
		
		ctx = new InitialDirContext(env);
		
		return ctx;
	}
	
	/**
	 * M�todo que permite obtener la conexi�n al Active Directory previa
	 * validaci�n y el password y usuario con que se esta ingresando
	 * @param userName Nombre del usuario que se conecta al LDAP
	 * @param password Clave del usuario que se conecta al LDAP
	 * @param suffixName Dominio al que permtece el usuario
	 * @param server Servidor donde esta instalado el Directorio Activo
	 * @return Conexi�n al servidor del Active Directory
	 */
	@SuppressWarnings("unused")
	public DirContext getAdminConnection(String ldapHost) throws Exception {
		
		DirContext ctx 					= null;
		Hashtable<String, Object> env 	= null;
		String ctxFactory 				= null;
		String ldapAuthentication 		= null;
		String ldapAdmin				= null;
		String ldapPassword				= null;
		String enc						= null;
		String desmEnc					= null;
		String cryptoKey				= null;
		
		if(ldapHost == null || "".equals(ldapHost.trim())){
			ldapHost = properties.getProperty(ConstantsProperties.TDS_LDAP_HOST);
		}

		cryptoKey			= properties.getProperty(ConstantsProperties.TDS_CRYPTOKEY);
		
		InitParameter.getInstance().setCryptoKey(cryptoKey);
		
		Encriptacion encripta = Encriptacion.getInstance();
		
		ldapAdmin			= properties.getProperty(ConstantsProperties.TDS_LDAP_ADMIN);
		
		ctxFactory 			= properties.getProperty(ConstantsProperties.TDS_LDAP_FACTORY);
		
		ldapAuthentication 	= properties.getProperty(ConstantsProperties.TDS_LDAP_AUTHENTICATION);

		desmEnc				= properties.getProperty(ConstantsProperties.TDS_LDAP_PASSWORD);

		ldapPassword 		= desencriptaCadena(properties.getProperty(ConstantsProperties.TDS_LDAP_PASSWORD));
		
		env 				= new Hashtable<String, Object>();
		
		System.out.println("LDAP		: " + ldapHost	);
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
		env.put(Context.PROVIDER_URL, ldapHost);
		env.put(Context.SECURITY_AUTHENTICATION, ldapAuthentication);
		env.put(Context.SECURITY_PRINCIPAL, ldapAdmin);
		env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		
		ctx = new InitialDirContext(env);
		
		return ctx;
	}
	
	/**
     * M�todo que permite obtener la conexi�n al Active Directory previa
     * validaci�n y el password y usuario con que se esta ingresando
     * @param userName Nombre del usuario que se conecta al LDAP
     * @param password Clave del usuario que se conecta al LDAP
     * @param suffixName Dominio al que permtece el usuario
     * @param server Servidor donde esta instalado el Directorio Activo
     * @return Conexi�n al servidor del Active Directory
     */
     public DirContext validarLogin(String userName, String password,
                 String preffixName, String suffixName, String ldapHost) throws Exception {
           
           DirContext ctx                  = null;
           Hashtable<String, Object> env   = null;
           String ctxFactory               = null;
           String principalName            = null;
           
           if(ldapHost == null || "".equals(ldapHost.trim())) 
                 ldapHost 	 = properties.getProperty(ConstantsProperties.TDS_LDAP_HOST);
           
           if(preffixName == null || "".equals(preffixName.trim()))
                 preffixName = properties.getProperty(ConstantsProperties.TDS_LDAP_PREFFIX_NAME);
           
           if(suffixName == null || "".equals(suffixName.trim())) 
                 suffixName  = properties.getProperty(ConstantsProperties.TDS_LDAP_SUFFIX_NAME);
           
           ctxFactory        = properties.getProperty(ConstantsProperties.TDS_LDAP_FACTORY);
           principalName     = preffixName + userName + "," + suffixName;
           
           env               = new Hashtable<String, Object>();
           
           System.out.println("LDAP           : " + ldapHost    );
           System.out.println("USER           : " + principalName     );
           
           env.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
           env.put(Context.PROVIDER_URL, ldapHost);
           env.put(Context.SECURITY_AUTHENTICATION, "simple");
           env.put(Context.SECURITY_PRINCIPAL, principalName);
           env.put(Context.SECURITY_CREDENTIALS, password);
           
           ctx = new InitialDirContext(env);
           
           return ctx;
     }
	
	/**
	 * M�todo que valida que la conexi�n al LDAP sea Correcta
	 * @return Validaci�n de la conexi�n al LDAP
	 */
	public boolean testLdapConnection() {
		
		boolean esValido 				= false;
		DirContext ctx 					= null;
		String ldapHost 				= null;
		String ctxFactory 				= null;
		Hashtable<String, Object> env 	= null;
		
		try {
			
			ldapHost = properties.getProperty(ConstantsProperties.TDS_LDAP_HOST);
			ctxFactory = properties.getProperty(ConstantsProperties.TDS_LDAP_FACTORY);
			
			System.out.println("**************************");
			System.out.println("CONEXION DE PRUEBA AL LDAP");
			System.out.println("**************************");
			
			System.out.println("LDAP HOST			: 		"+ ldapHost);
			System.out.println("CTX FACTORY			: 		"+ ctxFactory);
			
			env = new Hashtable<String, Object>();
			
			env.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
			env.put(Context.PROVIDER_URL, ldapHost);
			
			ctx = new InitialDirContext(env);
			
			if(ctx != null) 
				esValido = true;
			
		} catch(Exception e) {
			esValido = false;
			
			System.out.println("ERROR DE CONEXION");
			
			e.printStackTrace();
		}
		
		return esValido;
	}
	
	/**
	 * M�todo que devuelve el nombre del Usuario conectado al LDAP
	 * @param ctx Contexto que contiene la conexi�n al LDAP
	 * @param username Usuario que se esta validando en el LDAP|
	 * @return Nombre del Usuario conectado al LDAP
	 */
	public UsuarioLDAP getPrincipalDataUser(DirContext ctx, String userId, String preffixName, String suffixName) throws Exception {
		UsuarioLDAP	usuario			= new UsuarioLDAP();

		SearchControls controls = new SearchControls();
		
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(userAttributes);

		String searchFilter = "("+preffixName+userId+")";
		 
		NamingEnumeration<SearchResult> answer = ctx.search(suffixName,
				searchFilter, controls);
		
		
		if (answer.hasMore()) {
			Attributes attr 	= answer.next().getAttributes();
			Attribute cn 		= attr.get("cn");
			Attribute givenname = attr.get("givenname");
			Attribute uid 		= attr.get("uid");
			Attribute sn 		= attr.get("sn");
			Attribute docNumber	= attr.get("documentnumber");
			Attribute securQues = attr.get("securityquestion");
			Attribute securAnsw = attr.get("securityanswer");
			Attribute mail		= attr.get("mail");
			
			usuario.setValido(true);
			
			if (cn!=null) {
				usuario.setCn((String) attr.get("cn").get());
			}
			if (givenname!=null) {
				usuario.setGivenName((String) attr.get("givenname").get());
			}
			if (uid!=null) {
				usuario.setUid((String) attr.get("uid").get());
			}
			if (sn!=null) {
				usuario.setSn((String) attr.get("sn").get());
			}
			if (docNumber!=null) {
				usuario.setDocumentNumber((String) attr.get("documentnumber").get());
			}
			if (securQues!=null) {
				usuario.setSecurQues((String) attr.get("securityQuestion").get());
			}
			if (securAnsw!=null) {
				usuario.setSecurAnsw((String) attr.get("securityAnswer").get());
			}
			if(mail!=null) {
				usuario.setMail((String) attr.get("mail").get());
			}
		}

		return usuario;
	}
	
	/**
	 * Valida que el usuario que esta logueando en el aplicativo tiene acceso
	 * @param usuario Usuario que se esta logueando en el aplicativo
	 * @param password Clave del usuario que se esta logueando en el aplicativo
	 * @return Validaci�n de las credenciales del Usuario
	 */
	@SuppressWarnings("unused")
	public boolean validarUserLDAP(String usuario, String password) {
		
		boolean esValido 		= false;
		DirContext ctxValidate	= null;
		
		try {
			
			System.out.println("****************************************");
			System.out.println("AUTENTICACION DEL USUARIO CONTRA EL LDAP");
			System.out.println("****************************************");

			//VALIDAR EXISTENCIA DEL USUARIO
			ctxValidate = validarLogin(usuario, password, null, null, null);

			esValido = true;
			
		} catch(Exception e) {
			esValido = false;
			
			System.out.println("ERROR DE AUTENTICACION");
			
			e.printStackTrace();
		}
		
		return esValido;
	}
	
	/**
	 * Obtiene datos del usuario que esta logueando en el aplicativo
	 * @param usuario Usuario que se esta logueando en el aplicativo
	 * @param password Clave del usuario que se esta logueando en el aplicativo
	 * @return Datos  del Usuario
	 * @throws NamingException 
	 */
	public UsuarioLDAP obtUserLDAP(String userId) throws NamingException {
		
		DirContext ctxDetail	  = null;
		String preffixName 		  = null;
		String suffixName		  = null;
		UsuarioLDAP usuarioLdap   = null;
		
		try {
			
			System.out.println("****************************************");
			System.out.println("BUSCANDO AL USUARIO EN EL LDAP - INGRESO COMO ADMIN");
			System.out.println("****************************************");
			
			preffixName = properties.getProperty(ConstantsProperties.TDS_LDAP_PREFFIX_NAME);
	
			suffixName = properties.getProperty(ConstantsProperties.TDS_LDAP_SUFFIX_NAME);
			
			//OBTENER CONEXION ADMIN 
			ctxDetail = getAdminConnection(null);
			
			//OBTENER DATA DEL USUARIO
			usuarioLdap = getPrincipalDataUser(ctxDetail, userId, preffixName, suffixName);
			
			ctxDetail.close();
			
		} catch(Exception e) {
			
			System.out.println("ERROR OBTENER DATA USUARIO.");
			
			e.printStackTrace();
			
			ctxDetail.close();
		}
		
		return usuarioLdap;
	}
	
	/**
	 * 
	 * @param domainName
	 * @return
	 */
	@SuppressWarnings("unused")
	private String toDC(String domainName) {
		StringBuilder buf = new StringBuilder();
		for (String token : domainName.split("\\.")) {
			if(token.length()==0)   continue;
			if(buf.length()>0)  buf.append(",");
			buf.append("DC=").append(token);
		}
		
		return buf.toString();
	}
	
	/**
	 * M�todo que permite desencriptar una cadena de texto
	 * 
	 * @param textoEncriptado
	 *            Texto a ser desencripatdo
	 * @return Texto desencriptado
	 * @throws Exception
	 */
	private String desencriptaCadena(String textoEncriptado) throws Exception {
		Encriptacion encripta = null;

		if (textoEncriptado == null) {
			return null;
		} else {
			encripta = Encriptacion.getInstance();
		}

		return encripta.desencripta(textoEncriptado);
	}
	
	public void registrarUser(UsuarioWeb usuario, 
							  MedioContacto medioContactoTelf, 
							  MedioContacto medioContactoMail,
							  Persona persona){
		
		DirContext ctx = null;
		String preffixName 		  = null;
		String suffixName		  = null;
		String[] fecnac			  = null;
		String formtFecNac 		  = null;
		
		
		fecnac = usuario.getFecnacimiento().split("/");
		
		formtFecNac = fecnac[2]+fecnac[1]+fecnac[0];
		
		preffixName = properties.getProperty(ConstantsProperties.TDS_LDAP_PREFFIX_NAME);
		
		suffixName = properties.getProperty(ConstantsProperties.TDS_LDAP_SUFFIX_NAME);
		
		try {
			
			ctx = getAdminConnection(null);
			
			Attributes attributes=new BasicAttributes();
			Attribute objectClass=new BasicAttribute("objectClass");
			objectClass.add("inetorgperson");
			attributes.put(objectClass);

			Attribute sn=new BasicAttribute("sn");
			  sn.add(persona.getApepaterno());
			Attribute cn=new BasicAttribute("cn");
			  cn.add(persona.getNombre());
			
			attributes.put(sn);
			attributes.put(cn);
			attributes.put("documentNumber",usuario.getIdptipodocumento());
			attributes.put("mobile",medioContactoTelf.getNumlocal());
			attributes.put("securityQuestion",usuario.getPregseg());
			attributes.put("securityAnswer",usuario.getRespseg());
			attributes.put("dateOfBirth",formtFecNac);
			attributes.put("mail",medioContactoMail.getEmail());
			attributes.put("userPassword",usuario.getPassword());
			
			ctx.createSubcontext(preffixName+usuario.getNumerodoc()+","+suffixName,attributes);
			
			
		} catch (Exception e1) {
			logger.debug("****LDAPSERVICE ejecutando registrarUser --- ERROR****");
			logger.debug(e1);
			e1.printStackTrace();
		}
	}
	
	public void actualizarLdapUser(UsuarioWeb usuario, MedioContacto medioContactoTelf, MedioContacto medioContactoMail, String ind) throws NamingException{
		
		DirContext ctx 		= null;
		String preffixName	= null;
		String suffixName	= null;
		
		suffixName 		= properties.getProperty(ConstantsProperties.TDS_LDAP_SUFFIX_NAME);
		preffixName 	= properties.getProperty(ConstantsProperties.TDS_LDAP_PREFFIX_NAME);
		
		try{
			ctx = getAdminConnection(null);
			
			Attributes attributes=new BasicAttributes();
			
			
			if(ind==Constants.ACCION_LDAP_PWDTEMPORAL){
				
				attributes.put("mail",medioContactoMail.getEmail());
				attributes.put("userPassword",usuario.getPassword());
				
			}
			
			if(ind==Constants.ACCION_LDAP_MODIFDATOS){
				
				attributes.put("mail",medioContactoMail.getEmail());
				attributes.put("mobile",medioContactoTelf.getNumlocal());
			}
			
			if(ind==Constants.ACCION_LDAP_MODIFPREGSEG){
			
				attributes.put("securityQuestion",usuario.getPregseg());
				attributes.put("securityAnswer",usuario.getRespseg());
			}
			
			if(ind==Constants.ACCION_LDAP_MODIFPWD){
				
				attributes.put("userPassword",usuario.getNewpassword());
			}
			
			System.out.println(preffixName+usuario.getNumerodoc()+","+suffixName);

			ctx.modifyAttributes(preffixName+usuario.getNumerodoc()+","+suffixName, DirContext.REPLACE_ATTRIBUTE, attributes);
			
			ctx.close();
		}
		
		catch(Exception e){
			
			System.out.println("ERROR ACTUALIZAR DATA USUARIO.");
			
			e.printStackTrace();
			
			ctx.close();
		}
	}
	
	/**
	 * M�todo para desencriptar cadena.
	 * @param textoEntrada
	 * @param cryptoKey
	 * @return texto encriptado
	 * @throws Exception
	 */
	public void getTextoDesencriptado(CryptoWeb cryptoweb) throws Exception {
		
		String cadenaDesencriptada	=	null;
		String cryptoKey			=	null;
		
		cryptoKey			= properties.getProperty(ConstantsProperties.TDS_CRYPTOKEY);
		
		if(cryptoweb != null){
			
			if(cryptoweb.getTextoencriptado() != null && !(cryptoweb.getTextoencriptado().trim()).equals("") || 
					cryptoKey != null && !(cryptoKey.trim()).equals("")){
			
				InitParameter.getInstance().setCryptoKey(cryptoKey);
				
				cadenaDesencriptada	=	desencriptaCadena(cryptoweb.getTextoencriptado().trim());
		
				cryptoweb.setTexto(cadenaDesencriptada);
			}
		}
	}
	
	/**
	 * M�todo para encriptar cadena.
	 * @param textoEntrada
	 * @param cryptoKey
	 * @return texto encriptado
	 * @throws Exception
	 */
	public void getTextoEncriptado(CryptoWeb cryptoweb) throws Exception {
		
		String cadenaEncriptada	=	null;
		String cryptoKey		=	null;
		
		cryptoKey			= properties.getProperty(ConstantsProperties.TDS_CRYPTOKEY);
		
		if(cryptoweb != null){
			
			if(cryptoweb.getTexto() != null && !(cryptoweb.getTexto().trim()).equals("") || 
					cryptoKey != null && !(cryptoKey.trim()).equals("")){
			
				InitParameter.getInstance().setCryptoKey(cryptoKey);
				
				cadenaEncriptada	=	encriptaCadena(cryptoweb.getTexto().trim());
		
				cryptoweb.setTextoencriptado(cadenaEncriptada);
			}
		}
	}
	
	/**
	 * M�todo que permite encriptar una cadena de texto
	 * 
	 * @param textoEntrada
	 * @return Texto encriptado
	 * @throws Exception
	 */
	private String encriptaCadena(String textoEntrada) throws Exception {
		Encriptacion encripta = null;

		if (textoEntrada == null) {
			return null;
		} else {
			encripta = Encriptacion.getInstance();
		}

		return encripta.encripta(textoEntrada);
	}
	
	/**	
	 * @return
	 */
	public List<UsuarioLDAP> getAllUsersLDAP() {
		
		DirContext ctxDetail	  		= null;
		String preffixName 		 		= null;
		String suffixName		  		= null;
		UsuarioLDAP	usuarioLdap			= null;	
		
		SearchControls controls 		= new SearchControls();		
		List<UsuarioLDAP> lstUsrLdap	= new ArrayList<UsuarioLDAP>();
		
		try {
			
			System.out.println("*********************");
			System.out.println("** getAllUsersLDAP **");
			System.out.println("*********************");
			
			preffixName	= properties.getProperty(ConstantsProperties.TDS_LDAP_PREFFIX_NAME);	
			suffixName 	= properties.getProperty(ConstantsProperties.TDS_LDAP_SUFFIX_NAME);
			
			//OBTENER CONEXION ADMIN 
			ctxDetail	= getAdminConnection(null);
			
			//OBTENER DATA DEL USUARIO
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setReturningAttributes(userAttributes);
			
			String searchFilter 			= "("+preffixName+"*"+")";
			
			NamingEnumeration<SearchResult> answer = ctxDetail.search(suffixName, searchFilter, controls);
			
			int count = 0;
			while (answer.hasMore()) {
				
				count ++;
				
				usuarioLdap				=  new UsuarioLDAP();
				
				Attributes attr 			= answer.next().getAttributes();
				Attribute cn 				= attr.get("cn");
				Attribute uid 				= attr.get("uid");
				Attribute mail				= attr.get("mail");
				Attribute documentNumber	= attr.get("documentNumber");
				
				usuarioLdap.setValido(true);
				
				if (cn!=null)
					usuarioLdap.setCn((String) attr.get("cn").get());
			
				if (uid!=null)
					usuarioLdap.setUid((String) attr.get("uid").get());
				
				if(mail!=null)
					usuarioLdap.setMail((String) attr.get("mail").get());
				
				if(documentNumber!=null)
					usuarioLdap.setDocumentNumber((String) attr.get("documentNumber").get());
				
				System.out.println("answer.hasMore()	: " + count + "				" + usuarioLdap.getUid() + "		"  + usuarioLdap.getMail());
				
				lstUsrLdap.add(usuarioLdap);
			}
			
			System.out.println("SIZE	:" + lstUsrLdap.size());
			
		} catch(Exception e) {
	
			System.out.println("ERROR OBTENER DATA USUARIO.");
			
			e.printStackTrace();
		}
		
		return lstUsrLdap;
	}	
}
