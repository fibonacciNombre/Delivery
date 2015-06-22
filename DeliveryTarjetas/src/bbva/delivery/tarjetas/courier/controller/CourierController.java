package bbva.delivery.tarjetas.courier.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import commons.framework.BaseController;

@AdviceController
public class CourierController extends BaseController {
	
//	private static Logger logger = Logger.getLogger(CourierController.class.getName());
	
	@Override 
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView open(HttpServletRequest request,HttpServletResponse response) {return null;}

	@Override
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) {return null;}
	
	public String goRegCourier(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goRegCourier	-->		reg-courier.jsp");
		return "courier/reg-courier"; 
	}
	
	public String goMntCourier(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goMntCourier	-->		mnt-courier.jsp");
		return "courier/mnt-courier"; 
	}
	
	public String goRegColaborador(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goRegColaborador	-->		reg-colaboradorcourier.jsp");
		return "courier/reg-colaboradorcourier"; 
	}
	
	public String goMntColaborador(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		System.out.println("goMntColaborador	-->		mnt-colaboradorcourier.jsp");
		return "courier/mnt-colaboradorcourier"; 
	}
	
}