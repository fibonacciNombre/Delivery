package bbva.delivery.tarjetas.comun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController; 
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.service.ComunService;
import commons.framework.BaseController;

@AdviceController
public class ComunController extends BaseController {
	
	@Autowired
	private ComunService comunService;
	
//	private static Logger logger = Logger.getLogger(ComunController.class.getName());
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}
	
	public void lstParametro(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Parametro> listaParametro = null;
		String lstparametro = "";

		Parametro parametro = null;
  
		parametro = new Parametro(request.getParameterMap());
		
		try {
			listaParametro = comunService.lstParametro(parametro);
			lstparametro = commons.web.UtilWeb.listaToArrayJson(listaParametro, null,
					Parametro.class.getName());
		} catch (Error e) {
			lstparametro = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, lstparametro);
		 
	}
	
	public void cmbParametro(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Parametro> listaParametro = null;
		String lstparametro = "";

		Parametro parametro = null;
  
		parametro = new Parametro(request.getParameterMap());
		
		try {
			listaParametro = comunService.cmbParametro(parametro);
			lstparametro = commons.web.UtilWeb.listaToArrayJson(listaParametro, null,
					Parametro.class.getName());
		} catch (Error e) {
			lstparametro = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, lstparametro);
		 
	}
}