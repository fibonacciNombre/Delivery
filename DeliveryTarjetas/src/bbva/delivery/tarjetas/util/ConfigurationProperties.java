package bbva.delivery.tarjetas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class ConfigurationProperties {

public static final String RUTA_ARCH_DEFAULT = "RUTA_ARCH_CONF";
	
	/**
	 * Método que crea un objeto Properties con los parametros configurados
	 * en un archivo externo en el servidor de aplicaciones
	 * @param ruta Ruta donde se encuetra el archivo propeties
	 * @param fileName Nombre del archivo properties si la extensión
	 * @return Intancia con los parametros cargados desde el archivo
	 */
//	public static final Properties getExternalProperties(String ruta,
//			String fileName) {
//		Properties properties = null;
//		FileInputStream fis = null;
//		File file = null;
//		
//		try {
//			file = new File(ruta + System.getProperty("file.separator")
//					+ fileName + ".properties");
//			fis = new FileInputStream(file);
//			
//			properties = new Properties();
//			properties.load(fis);
//			
//			fis.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return properties;
//	}

	public static final Properties getExternalProperties(String fileName) {
		// String ruta = null;
	
		FileInputStream fis = null;
		Properties properties = null;
		try {
			// ruta = System.getProperty(RUTA_ARCH_DEFAULT)
			// + Constants.RUTA_FILE_CONFIGURACION_APP;
	
			ClassPathResource resource = new ClassPathResource(
					"configuracion.properties");
			File file = resource.getFile();
	
			fis = new FileInputStream(file);
	
			properties = new Properties();
			properties.load(fis);
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return getExternalProperties(ruta, fileName);
		return properties;
	}
}