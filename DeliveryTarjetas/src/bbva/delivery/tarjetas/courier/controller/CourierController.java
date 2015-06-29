package bbva.delivery.tarjetas.courier.controller;

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
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.service.CourierService;
import bbva.delivery.tarjetas.usuario.bean.Usuario;
import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class CourierController extends BaseController {

	private static Logger logger = Logger.getLogger(CourierController.class.getName());

	@Autowired
	private CourierService courierService;

	@Override
	public ModelAndView buscar(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@Override
	public ModelAndView open(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@Override
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String goRegCourier(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("goRegCourier	-->		reg-courier.jsp");
		return "courier/reg-courier";
	}

	public String goMntCourier(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("goMntCourier	-->		mnt-courier.jsp");
		return "courier/mnt-courier";
	}

	public String goRegColaborador(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("goRegColaborador	-->		reg-colaboradorcourier.jsp");
		return "courier/reg-colaboradorcourier";
	}

	public String goMntColaborador(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("goMntColaborador	-->		mnt-colaboradorcourier.jsp");
		return "courier/mnt-colaboradorcourier";
	}

	public void lstCourier(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller lstCourier");
		
		String result				= "";
		String lstcourier 			= "";
		List<Courier> listaCourier 	= null;
		TransaccionWeb tx			= new TransaccionWeb();				
		Courier courier 			= new Courier(request.getParameterMap());

		try {
			
			listaCourier 	= courierService.lstCouriers(courier);
			lstcourier 		= UtilWeb.listaToArrayJson(listaCourier, null, Courier.class.getName());			
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			lstcourier = "{" + e.getMessage() + "}";
		}
		
		result += "{"
					+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
					+ "\"lst\":" + lstcourier 
					+ "}";
		
		this.escribirTextoSalida(response, result);

	}

	public void obtCourier(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller obtCourier");
		
		String result				= "";
		String lstcourier 			= "";
		List<Courier> listaCourier 	= null;
		TransaccionWeb tx			= new TransaccionWeb();						
		Courier courier 			= new Courier(request.getParameterMap());

		try {
			
			listaCourier 	= courierService.obtCourier(courier);
			lstcourier 		= UtilWeb.listaToJson(listaCourier, null, Courier.class.getName());
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			lstcourier = "{" + e.getMessage() + "}";
		}

		result += "{"
				+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
				+ "\"lst\":" + lstcourier 
				+ "}";
		
		this.escribirTextoSalida(response, result);

	}

	public void mntCourier(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Controller mntCourier");
		
		String result			= "";
		HttpSession session 	= request.getSession();
		TransaccionWeb tx		= new TransaccionWeb();				
		Courier courier 		= new Courier(request.getParameterMap());
		Usuario usuarioSes     = (Usuario)session.getAttribute(Constants.REQ_SESSION_USUARIO);
		
		courier.setUsuario(usuarioSes.getCodusuario());
		
		try {
			
			courierService.mntCourier(courier);			
			tx.setMessagetx("Su transacción fue realizada con éxito");
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
		}

		result += "{\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) +"}";
		
		this.escribirTextoSalida(response, result);
	}

}
