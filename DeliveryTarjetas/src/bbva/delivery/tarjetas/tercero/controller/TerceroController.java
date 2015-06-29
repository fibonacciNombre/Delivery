package bbva.delivery.tarjetas.tercero.controller;

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
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.service.TerceroService;
import bbva.delivery.tarjetas.usuario.bean.Usuario;

import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class TerceroController extends BaseController {
	
	private static Logger logger = Logger.getLogger(TerceroController.class.getName());
	
	@Autowired
	private TerceroService terceroService;
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}
	
	
	public void lstTerceros(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("CONTROLLER lstTerceros");
		
		String result				= "";
		String lsttercero			= "";
		List<Tercero> listaTercero 	= null;
		TransaccionWeb tx			= new TransaccionWeb();				
		Tercero tercero 			=  new Tercero(request.getParameterMap());

		try {
		
			listaTercero = terceroService.lstTerceros(tercero);
			lsttercero = commons.web.UtilWeb.listaToArrayJson(listaTercero, null, Tercero.class.getName());
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			lsttercero = "{" + e.getMessage() + "}";
		}

		result += "{"
					+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
					+ "\"lst\":" + lsttercero 
					+ "}";
		
		this.escribirTextoSalida(response, result);

	}

		public void mntTercero(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("CONTROLLER mntTercero");
			
		String result			= "";
		HttpSession session 	= request.getSession();
		TransaccionWeb tx		= new TransaccionWeb();			
		
		Tercero tercero 		=  new Tercero(request.getParameterMap());
		
		Usuario usuario = (Usuario) session.getAttribute(Constants.REQ_SESSION_USUARIO);
		tercero.setUsuario(usuario.getCodusuario());
		
		try {
			terceroService.mntTercero(tercero);
			tx.setMessagetx("Su transacción fue realizada con éxito");
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
		}

		result += "{\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) +"}";
		
		this.escribirTextoSalida(response, result);
	}
	
}