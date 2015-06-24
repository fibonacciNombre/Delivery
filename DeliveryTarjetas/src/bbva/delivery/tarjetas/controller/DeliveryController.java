package bbva.delivery.tarjetas.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.bean.Delivery; 
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.service.DeliveryService;
import bbva.delivery.tarjetas.usuario.service.UsuarioService;
import commons.framework.BaseController;

@AdviceController
public class DeliveryController extends BaseController{

		
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private UsuarioService perfilService;
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}

	public String goHomePage(HttpServletRequest request,
			HttpServletResponse response)throws Exception {		
		System.out.println("login	-->		inicio");
		return "inicio"; 
	}
	
	public void redireccionInicio(HttpServletRequest request,
			HttpServletResponse response){
		
		String a = request.getContextPath()+"/jsp/inicio.jsp";
		
		this.escribirTextoSalida(response, a);
	}
	
	public void redireccionInicio2(HttpServletRequest request,
			HttpServletResponse response){
		
		String a = request.getContextPath()+"/jsp/inicio.jsp";
		
		try {
			response.sendRedirect(a);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	 
	
	public String goCargaDelivery(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("goCargaDelivery	-->		carga-delivery.jsp");
		return "courier/carga-delivery";
	}
	
	public void cargaExcelDelivery(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JSONObject joRetorno = new JSONObject();
		String fileName = request.getParameter("fileName");
		joRetorno = deliveryService.cargarExcelDelivery(fileName);

		this.escribirTextoSalida(response, joRetorno.toString());
	}

	public void lstDelivery(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Delivery> listaCarga = null;
		String lstcarga = "";

		Delivery carga = null;
 
		carga = new Delivery(request.getParameterMap());

		try {
			listaCarga = deliveryService.lstDelivery(carga);
			lstcarga = commons.web.UtilWeb.listaToArrayJson(listaCarga, null,
					Delivery.class.getName());
		} catch (Error e) {
			lstcarga = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, lstcarga);
		 
	} 
	

	
	
}
