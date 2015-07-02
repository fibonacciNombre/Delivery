package bbva.delivery.tarjetas.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.TransaccionWeb;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.service.DeliveryService;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.usuario.bean.Usuario;
import bbva.delivery.tarjetas.usuario.service.UsuarioService;

import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class DeliveryController extends BaseController{

	private static Logger logger = Logger.getLogger(DeliveryController.class.getName());
		
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
	
	public String goCargaDelivery(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("goCargaDelivery	-->		carga-delivery.jsp");
		return "delivery/carga-delivery";
	}
	
	public String goMntCargaDelivery(HttpServletRequest request, HttpServletResponse response) throws Exception {


		System.out.println("goCargaDelivery	-->		mnt-carga-delivery.jsp");
		return "delivery/mnt-delivery";
	}
	
	public String golstDelivery(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("goCargaDelivery	-->		carga-delivery.jsp");
		return "delivery/lst-delivery";
	}
	
	public void mntDelivery(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("CONTROLLER mntDelivery");
		
		String result			= "";
		HttpSession session 	= request.getSession();
		TransaccionWeb tx		= new TransaccionWeb();				
		Delivery delivery 		= new Delivery(request.getParameterMap());
		 Usuario usuarioSes     = (Usuario)session.getAttribute(Constants.REQ_SESSION_USUARIO);
		
		delivery.setUsuario(usuarioSes.getCodusuario());
	
		try {
			
			deliveryService.mntDelivery(delivery);			
			tx.setMessagetx("Su transacción fue realizada con éxito");
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
		}

		result += "{\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) +"}";
		
		this.escribirTextoSalida(response, result);
	}
	
	public void obtFileLstEntregas(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("CONTROLLER obtFileLstEntregas");
		
		String result		= "";
		String temporal		= "";
		TransaccionWeb tx	= new TransaccionWeb();
		Calendar cal 		= Calendar.getInstance();
		Delivery delivery 	= new Delivery(request.getParameterMap());
		Tercero tercero 	= new Tercero(request.getParameterMap());
		
		try {
			temporal = "temp/"+
						"ListadoEntregas"+
						cal.get(Calendar.DATE)+
						(cal.get(Calendar.MONTH)+1)+
						cal.get(Calendar.YEAR)+".xls";
			
			delivery.setRutaexpotacion(request.getSession().getServletContext().getRealPath("/")+ temporal);
			
			System.out.println(delivery.getRutaexpotacion());
			
			deliveryService.obtArchivoLstDelivery(delivery,tercero);
			
 		} catch (Error e) {
 			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
 		}
		
		System.out.println(temporal);
		
		result += "{"
				+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
				+ "\"archivo\":\"" + temporal.replace("\\", "\\\\") + "\"" 
				+ "}";
	
		this.escribirTextoSalida(response,result);
	}
	
	public void lstDelivery(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("CONTROLLER lstDelivery");
		
		String result				= "";
		String lstdelivery 			= "";
		List<Delivery> listaCarga 	= null;
		TransaccionWeb tx			= new TransaccionWeb();
		
		Delivery delivery 			= new Delivery(request.getParameterMap());
		Tercero tercero 			= new Tercero(request.getParameterMap());

		try {
			
			listaCarga 		= deliveryService.lstDelivery(delivery, tercero);			
			lstdelivery 	= commons.web.UtilWeb.listaToArrayJson(listaCarga, null,Delivery.class.getName());
			
		} catch (Error e) {
			tx.setStatustx(Constants.TRANSACCION_STATUS_ERROR);
			lstdelivery = "{" + e.getMessage() + "}";
		}

		result += "{"
				+ "\"tx\":"+ UtilWeb.objectToJson(tx, null, TransaccionWeb.class.getName()) + ","
				+ "\"lst\":" + lstdelivery 
				+ "}";
		
		this.escribirTextoSalida(response, result);		 
	} 
	
	@SuppressWarnings("rawtypes")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("CONTROLLER uploadFile");
		
		Archivo archivo 		= new Archivo();
		JSONObject joRetorno 	= new JSONObject();
		HttpSession session 	= request.getSession();
		boolean isMultipart 	= ServletFileUpload.isMultipartContent(request);
		
		String fecentrega		= request.getParameter("fecentrega");
		Integer idcourier 		= Integer.parseInt(request.getParameter("idcourier"));

 		Usuario usuarioSes    	= (Usuario)session.getAttribute(Constants.REQ_SESSION_USUARIO);
 		
 		archivo.setIdcourier(idcourier);
 		archivo.setUsuario(usuarioSes.getCodusuario());
		 
		Date dfechaentrega 		= null;
		DateFormat df			= new SimpleDateFormat("dd/MM/yyyy");
		 
		try {			
			dfechaentrega = df.parse(fecentrega);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		archivo.setFecentrega(dfechaentrega);
		if (isMultipart) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					MultipartFile f = new CommonsMultipartFile(item);
					 
					if(item.getFieldName().equalsIgnoreCase("filename")){
						archivo.setFilename(f.getOriginalFilename());
					
						joRetorno = deliveryService.cargarExcelDelivery(f, archivo);
						break;
					}
				}
				
				this.escribirTextoSalida(response, joRetorno.toString());
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
}