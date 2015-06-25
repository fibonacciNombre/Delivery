package bbva.delivery.tarjetas.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController;
import bbva.delivery.tarjetas.bean.Archivo;
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
	
	public void cargaExcelDelivery(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JSONObject joRetorno = new JSONObject();
		
		Archivo archivo = null;
		 
		archivo = new Archivo(request.getParameterMap());
		 
		joRetorno = deliveryService.cargarExcelDelivery(archivo);

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
	
	@SuppressWarnings("rawtypes")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Into uploadFile method");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		JSONObject joRetorno = new JSONObject();
		Archivo archivo = new Archivo();
		Integer idcourier = Integer.parseInt(request.getParameter("idcourier"));
		String fecentrega=request.getParameter("fecentrega");
		String tipoarchivo=request.getParameter("tipoarchivo");
		archivo.setIdcourier(idcourier);
		archivo.setTipoarchivo(tipoarchivo);
		archivo.setFecentrega(fecentrega);
		if (isMultipart) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					MultipartFile f = new CommonsMultipartFile(item);
					
					System.out.println("nombre --> "+ item.getName());
					System.out.println("getOriginalFilename --> "+ f.getOriginalFilename());
					System.out.println("getOriginalFilename --> "+ f.getInputStream());
 
						archivo.setFilename(f.getOriginalFilename());
						
						joRetorno = deliveryService.cargarExcelDelivery2(f, archivo);
						
				//	}
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
