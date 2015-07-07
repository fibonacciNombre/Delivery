package bbva.delivery.tarjetas.aspecto;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.TransaccionWeb;
import bbva.delivery.tarjetas.util.CommonsHelper;

import commons.framework.BaseController;
import commons.web.UtilWeb;

@Aspect
public class AuditaController {
	
	private static Logger logger = Logger.getLogger(AuditaController.class.getName());
	
	/**
	 * Permite definir la expresión para filtrar los metodos que serán 
	 * interceptados en los controladores
	 */
	@Pointcut("execution(* org.springframework.web.servlet.mvc.AbstractController.handleRequest(..)) "
			+ "&& @target(bbva.delivery.tarjetas.anotaciones.AdviceController))")
	public void metodosController() {
	}
	
	/**
	 * Método que se ejecutará al llamar a los métodos de los controladores
	 * que hagan match con la expresion definida en metodosController()
	 * @param joinPoint Permite invocar al método del controlador
	 */
	
	@Around("metodosController()")
	public Object auditarController(ProceedingJoinPoint joinPoint) {
		
		logger.debug("#########################################");
		Object result					= null;
		BaseController controller 		= null;
		HttpServletRequest request 		= null;
		HttpServletResponse response 	= null;
		Date tiempoInicio 				= null;
		String metodoEjecutado 			= null;
		String mensajeError				= null;
		
		try {
			logger.debug("Ejecutando metodo que intercepta a los controllers");
			
			tiempoInicio 	= new Date();
			
			controller 		= (BaseController) joinPoint.getTarget();
			
			request 		= (HttpServletRequest) joinPoint.getArgs()[0];
			
			response	 	= (HttpServletResponse) joinPoint.getArgs()[1];
			
			response.setContentType("text/html; charset=UTF-8");

			System.out.println("request		--> "	+ joinPoint.getArgs()[0]);
			
			System.out.println("response	--> "	+ joinPoint.getArgs()[1]);
			
			metodoEjecutado = controller.getClass().getName() + "." + request.getParameter("method");
			
			result = joinPoint.proceed();
			
			logger.info(CommonsHelper.tiempoEjecucion(metodoEjecutado,tiempoInicio));
			
		} catch(Throwable t) {
			
			logger.error("Error controlado por el Audita", t);
			
			TransaccionWeb tx = new TransaccionWeb();
			
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			
			System.out.println(CommonsHelper.getErrorMessage(t));
			
			String jsonCommonsHelper 	= CommonsHelper.getErrorMessage(t).
														replaceAll("\r", "").
														replaceAll("\n", "-").
														replaceAll("\"", "").
														replaceAll("'", "\"").
														replaceAll("\t", " ");

			logger.error("Error Audita JSON" + jsonCommonsHelper);
			
			JSONObject jsonObject;
			
			try {
				jsonObject = (JSONObject)new JSONParser().parse(jsonCommonsHelper);
				
				tx.setOrigen("DeliveryTarjetas");
				tx.setOperacion(metodoEjecutado);
				tx.setMessagetx(jsonObject.get("msg").toString());
				tx.setTrace(jsonObject.get("log").toString());
				
				mensajeError = "{"
									+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) 
								+ "}";
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			System.out.println("ERROR AuditaController");
			System.out.println(mensajeError);
			
			logger.debug(mensajeError);

			controller.escribirTextoSalida(response, mensajeError);
			
			logger.error("Error ejecutar-> " + metodoEjecutado + " : " + t);
			
			t.printStackTrace();
		}
		
		return result;
	}
	
}
