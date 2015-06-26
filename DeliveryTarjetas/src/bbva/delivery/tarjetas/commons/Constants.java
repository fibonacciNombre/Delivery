package bbva.delivery.tarjetas.commons;

public class Constants {

	public static final String RUTA_ARCH_DEFAULT		 				= "RUTA_ARCH_CONF" ; //La constante se encuentra originalmente en RIMAC_SEG_Lib.jar
	
	public static final String FILE_CONFIGURACION_DELIVERY_TARJETAS		= "DELIVERYTARJETAS";

	public static final String RUTA_PROPERTIES_CONFIGURACION 			= "BBVA" +
																			System.getProperty("file.separator")+"DELIVERYTARJETAS" +
																			System.getProperty("file.separator")+"properties";
	
	public static final String RUTA_FILE_CONFIGURACION_LOG4J 			= "BBVA" +
																			System.getProperty("file.separator")+"DELIVERYTARJETAS" +
																			System.getProperty("file.separator")+"properties" +
																			System.getProperty("file.separator")+"log4j.properties";
	
	public static final String RUTA_FILE_CONFIGURACION_APP 				= "BBVA" +
																			System.getProperty("file.separator")+"DELIVERYTARJETAS" +
																			System.getProperty("file.separator")+"properties" +
																			System.getProperty("file.separator")+"DELIVERYTARJETAS.properties";
	
	//Constantes para la lectura de Archivos
	public static final String ARCHIVO_RUTA_TEMPORAL 	 				= "temp";
	public static final String SEPARADOR 				 				= System.getProperty("file.separator");
	
	//Constante cron. 
	public static final String CRON_EXPRESSION							= "0 0/30 * * * ?";
	
	//Usuario para campos de auditoria
	public static final String USUARIO_WEB_BBVA							= "BBVA_WEB";
	public static final String CONTRASENA_DEFAULT						= "123456";

	//Constantes INDICADORES DE ESTADOS
	public static final Integer DELIVERY_IDPESTADO_ACTIVO				=  1;
	public static final Integer DELIVERY_IDPESTADO_INACTIVO				=  0;

	
	//Estados de un transaccion en el DeliveryTarjetas	
	public static final Integer TRANSACCION_STATUS_OK					= 0;
	public static final Integer TRANSACCION_STATUS_ERROR				= 1;
		
	//Constantes INDICADORES DE LOGIN
	public static final String ESCENARIO_LOGIN_ACCESOS_CORRECTOS		= "1";
	public static final String ESCENARIO_LOGIN_ACCESOS_INCORRECTOS		= "2";
	public static final String ESCENARIO_LOGIN_USUARIO_NO_EXITE			= "3";
	public static final String ESCENARIO_LOGIN_USUARIO_INACTIVO			= "4";
	public static final String ESCENARIO_LOGIN_COURIER_INACTIVA			= "5";

	//Constantes REQ ATTRIBUTE SESSION
	public static final String REQ_SESSION_LOGINWEB						= "LOGINWEB";
	public static final String REQ_SESSION_USUARIO						= "USUARIOWEB";
	public static final String REQ_SESSION_PERFIL						= "PERFIL";
	public static final String REQ_SESSION_COURIER						= "COURIER";
	public static final String REQ_SESSION_PERSONA						= "PERSONAWEB";
	
}
