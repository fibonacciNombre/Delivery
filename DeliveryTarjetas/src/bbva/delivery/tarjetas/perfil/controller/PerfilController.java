package bbva.delivery.tarjetas.perfil.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.TransaccionWeb;
import bbva.delivery.tarjetas.perfil.bean.Perfil;
import bbva.delivery.tarjetas.perfil.service.PerfilService;

import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class PerfilController extends BaseController {
	
	private static Logger logger = Logger.getLogger(PerfilController.class.getName());
	
	@Autowired
	private PerfilService perfilService;
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}
	
	public void lstPerfil(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller lstPerfil");
		
		String result				= "";
		String lstperfiles 			= "\"\"";
		List<Perfil> listaPerfiles 	= null;
		TransaccionWeb tx			= new TransaccionWeb();				
		Perfil perfil 				= new Perfil(request.getParameterMap());

		try {
			
			listaPerfiles 	= perfilService.lstPerfiles(perfil);
			
			if(listaPerfiles != null && listaPerfiles.size() > 0)
				lstperfiles 	= UtilWeb.listaToArrayJson(listaPerfiles, null, Perfil.class.getName());			
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			lstperfiles = "{" + e.getMessage() + "}";
		}
		
		result += "{"
					+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
					+ "\"lst\":" + lstperfiles 
					+ "}";
		
		this.escribirTextoSalida(response, result);

	}

	public void obtPerfil(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller obtPerfil");
		
		String result				= "";
		String lstperfil 			= "";
		List<Perfil> listaPerfil 	= null;
		TransaccionWeb tx			= new TransaccionWeb();						
		Perfil perfil 				= new Perfil(request.getParameterMap());

		try {
			
			perfil		 	= perfilService.obtPerfil(perfil);
			lstperfil 		= UtilWeb.objectToJson(perfil, null, Perfil.class.getName());
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			lstperfil = "{" + e.getMessage() + "}";
		}

		result += "{"
				+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
				+ "\"lst\":" + lstperfil 
				+ "}";
		
		this.escribirTextoSalida(response, result);

	}

	public void mntPerfil(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller mntCourier");
		
		String result			= "";
		HttpSession session 	= request.getSession();
		TransaccionWeb tx		= new TransaccionWeb();				
		Perfil perfil 			= new Perfil(request.getParameterMap());
		
		//String usuario = session.getAttribute(Constants.REQ_SESSION_USUARIO).toString();
		perfil.setUsucreacion("BBVA");
		
		try {
			
			perfilService.mntPerfil(perfil);			
			tx.setMessagetx("Su transacción fue realizada con éxito");
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
		}

		result += "{\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) +"}";
		
		this.escribirTextoSalida(response, result);
	}
	
}