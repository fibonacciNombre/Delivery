package bbva.delivery.tarjetas.courier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.service.CourierService;

import commons.framework.BaseController;

@AdviceController
public class CourierController extends BaseController {

	// private static Logger logger =
	// Logger.getLogger(CourierController.class.getName());

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

		List<Courier> listaCourier = null;
		String lstcourier = "";

		Courier courier = null;

		courier = new Courier(request.getParameterMap());

		try {
			listaCourier = courierService.lstCouriers(courier);
			lstcourier = commons.web.UtilWeb.listaToArrayJson(listaCourier, null, Courier.class.getName());
		} catch (Error e) {
			lstcourier = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, "{\"lstcouries\":" + lstcourier + "}");

	}

	public void obtCourier(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Courier> listaCourier = null;
		String lstcourier = "";

		Courier courier = null;
		
	 
		courier = new Courier(request.getParameterMap());

		try {
			listaCourier = courierService.obtCourier(courier);
			lstcourier = commons.web.UtilWeb.listaToJson(listaCourier, null,
					Courier.class.getName());
		} catch (Error e) {
			lstcourier = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, lstcourier);

	}

	public void mntCourier(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Courier courier = null;

		courier = new Courier(request.getParameterMap());

		String id = request.getParameter("idcourier");
		
		HttpSession session = request.getSession(true);
		
		String usuario = session.getAttribute(Constants.REQ_SESSION_USUARIO).toString();
		courier.setUsuario("BBVA");
		
		try {
			courierService.mntCourier(courier);
		} catch (Error e) {
		}

		this.escribirTextoSalida(response, "0");

	}

}