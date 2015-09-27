package bbva.delivery.tarjetas.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import bbva.delivery.tarjetas.bean.Configuracion;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.dao.DeliveryDao;

public class DeliveryProperties extends PropertyPlaceholderConfigurer {

	public DeliveryProperties() {
		super();
		
		try {
			ClassPathResource resource = new ClassPathResource("configuracion.properties");
			File file = resource.getFile();
			
			String configPropertyPath = file.getAbsolutePath();
			System.out.println("RUTA DeliveryProperties -> " + configPropertyPath);
			Resource configResource = new FileSystemResource(configPropertyPath);
	
			setLocation(configResource);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
//
//		String configPropertyPath = System.getProperty(Constants.RUTA_ARCH_DEFAULT) + Constants.RUTA_FILE_CONFIGURACION_APP;
//
////		List<Configuracion> confParam = deliveryDao.getConfiguracion_Parametros();
////		String configPropertyPath = getConfParam(confParam, "rutaLog4JWeb");
//
//		Resource configResource = new FileSystemResource(configPropertyPath);
//
//		setLocation(configResource);
//	}
//
	}
	
}
