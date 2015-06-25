package bbva.delivery.tarjetas.tercero.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.courier.bean.Courier; 
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.service.TerceroService;
import commons.framework.BaseController;

@AdviceController
public class TerceroController extends BaseController {
	
//	private static Logger logger = Logger.getLogger(TerceroController.class.getName());
	
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

		List<Tercero> listaTercero = null;
		String lsttercero = "";

		Tercero tercero = null;

		tercero = new Tercero(request.getParameterMap());

		try {
			listaTercero = terceroService.lstTerceros(tercero);
			lsttercero = commons.web.UtilWeb.listaToArrayJson(listaTercero, null, Tercero.class.getName());
		} catch (Error e) {
			lsttercero = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, "{\"lst\":" + lsttercero + "}");

	}

	public void obtTercero(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Tercero> listaTercero = null;
		String lsttercero= "";

		Tercero tercero = null;
		
	 
		tercero = new Tercero(request.getParameterMap());

		try {
			listaTercero = terceroService.obtTercero(tercero);
			lsttercero = commons.web.UtilWeb.listaToJson(listaTercero, null,
					Courier.class.getName());
		} catch (Error e) {
			lsttercero = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, lsttercero);

	}

	public void mntTercero(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Tercero tercero = null;

		tercero = new Tercero(request.getParameterMap());

		
		HttpSession session = request.getSession(true);
		
		//String usuario = session.getAttribute(Constants.REQ_SESSION_USUARIO).toString();
		tercero.setUsuario("BBVA");
		
		try {
			terceroService.mntTercero(tercero);
		} catch (Error e) {
		}

		this.escribirTextoSalida(response, "{resultado: 0}");

	}
	
}