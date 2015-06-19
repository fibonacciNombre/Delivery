package bbva.delivery.tarjetas.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationProperties {

public static final String RUTA_ARCH_DEFAULT = "RUTA_ARCH_CONF";
	
	/**
	 * Método que crea un objeto Properties con los parametros configurados
	 * en un archivo externo en el servidor de aplicaciones
	 * @param ruta Ruta donde se encuetra el archivo propeties
	 * @param fileName Nombre del archivo properties si la extensión
	 * @return Intancia con los parametros cargados desde el archivo
	 */
	public static final Properties getExternalProperties(String ruta,
			String fileName) {
		Properties properties = null;
		FileInputStream fis = null;
		File file = null;
		
		try {
			file = new File(ruta + System.getProperty("file.separator")
					+ fileName + ".properties");
			fis = new FileInputStream(file);
			
			properties = new Properties();
			properties.load(fis);
			
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return properties;
	}
}