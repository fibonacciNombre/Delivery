package bbva.delivery.tarjetas.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.dao.DeliveryDao;
import bbva.delivery.tarjetas.util.ConfigurationProperties;
import bbva.delivery.tarjetas.bean.Configuracion;


public class InicioAppServlet extends HttpServlet {

	private static final long serialVersionUID = 8815884570144835030L;	
	private static Logger logger = Logger.getLogger(InicioAppServlet.class.getName());
	
	@Autowired
	private DeliveryDao deliveryDao;
	
	public InicioAppServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request,response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
	}
	
	@SuppressWarnings("unused")
	public void init() throws ServletException {
		ServletContext servletContext = null;
//		String rutaLog4j = null;
		
//		try {
//			rutaLog4j = System.getProperty(ConfigurationProperties.RUTA_ARCH_DEFAULT)+ Constants.RUTA_FILE_CONFIGURACION_LOG4J;
//			
////			List<Configuracion> confParam = deliveryDao.getConfiguracion_Parametros();
////			rutaLog4j = getConfParam(confParam, "rutaLog4JWeb");
////			
//			PropertyConfigurator.configure(rutaLog4j);
//			
//			System.out.println("Informacion acerca de la configuracion del log4j");
//			System.out.println("RUTA -> " + rutaLog4j);
//			System.out.println("Obteniendo los parametros de verificación");
//			
//			logger.info("[---- INICIADO APLICACION DELIVERY TARJETAS ----]");
//			
//			servletContext = super.getServletContext();
//			
//		} catch (Exception e) {
//			logger.error("Error al iniciar parametros de la aplicacion", e);
//		}
		
		try {
//			rutaLog4j = System.getProperty(ConfigurationProperties.RUTA_ARCH_DEFAULT)+
//					Constants.RUTA_FILE_CONFIGURACION_LOG4J;
			
			ClassPathResource resource = new ClassPathResource("log4j.properties");
			File file = resource.getFile();
			String rutaLog4j = file.getAbsolutePath();
			
			PropertyConfigurator.configure(rutaLog4j);
			
			System.out.println("Informacion acerca de la configuracion del log4j");
			System.out.println("RUTA -> " + rutaLog4j);
			System.out.println("Obteniendo los parametros de verificacion");
			
			logger.info("[---- INICIADO APLICACION DELIVERY WEB----]");
			
			servletContext = super.getServletContext();
			
		} catch (Exception e) {
			logger.error("Error al iniciar parametros de la aplicacion", e);
		}
		
		logger.info("INICIO PARAMETROS DE CONTEXTO FINALIZADO");
	}

}
