package bbva.delivery.tarjetas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import bbva.delivery.tarjetas.anotaciones.AdviceController; 
import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.commons.Constants; 
import bbva.delivery.tarjetas.bean.Parametro;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.ldap.service.LdapService;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.PuntoContacto;
import bbva.delivery.tarjetas.perfil.bean.UsuarioLDAP;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.perfil.service.PerfilService;
import bbva.delivery.tarjetas.service.DeliveryService;
import commons.framework.BaseController;
import commons.web.UtilWeb;

@AdviceController
public class DeliveryController extends BaseController{
	 
	
	public static final String COOKIE_USUARIO_SAS 			= "SAS_CODUSUARIO_COOKIE";
	
	public static final String WEBAPP_SAS 					= "/" ;
	
	
	
	
	public static final String token 						= "F2DA2A4571F9A6BF8B85BB6452CAFAFF";
	
	LdapService ldapService 								= LdapService.getInstance();
		
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private PerfilService perfilService;

	@SuppressWarnings("unused")
	private void setupCookies(HttpServletRequest request, HttpServletResponse response){
		System.out.println("request.getParameter(usuario)	: "+ request.getParameter("usuario"));
		Cookie usrCookie = new Cookie(COOKIE_USUARIO_SAS, request.getParameter("usuario"));
		usrCookie.setPath(WEBAPP_SAS);
		response.addCookie(usrCookie);		
	}
	
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

	public void obtDireccion(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		PuntoContacto puntoContacto 		= new PuntoContacto(request.getParameterMap());
		
		perfilService.obtPtocontactoUsuarioweb(puntoContacto);
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
	
	public void cargarLdapUsers(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		String result						= "";
		UsuarioWeb usuarioWeb				= null;
		MedioContacto mediocontactomail		= null;
		
		List<UsuarioLDAP> lstUsrLdap 		= ldapService.getAllUsersLDAP();
		List<UsuarioLDAP> lstUsrLdapReg		= new ArrayList<UsuarioLDAP>();
		
		String cadenaJson 					= "";
		 
		/* INI REGISTRANDO EN TER_TERCEROWEB LOS CORREOS DE USUARIOS LDAP */
		for (int i=0; i<lstUsrLdap.size(); i++){
			
			usuarioWeb = new UsuarioWeb();			
			usuarioWeb.setIdptipodocumento(lstUsrLdap.get(i).getDocumentNumber());
			usuarioWeb.setNumerodoc(lstUsrLdap.get(i).getUid());
			
			try {
				
				perfilService.obtIdeterceroUsuarioweb(usuarioWeb);			
				perfilService.obtUsuarioweb(usuarioWeb);
				
				if(lstUsrLdap.get(i).getUid().trim().length()<=8 && usuarioWeb.getCursor().size() > 0){
					
					usuarioWeb = usuarioWeb.getCursor().get(0);
					usuarioWeb.setIdptipodocumento(lstUsrLdap.get(i).getDocumentNumber());
					usuarioWeb.setNumerodoc(lstUsrLdap.get(i).getUid());
					
					if(usuarioWeb.getIdepuntocontactodir() == null &&
						usuarioWeb.getIdepuntocontactotelf() == null &&
						usuarioWeb.getIdepuntocontactomail() == null ){
						
						System.out.println("*******************************");
						System.out.println(" DOC	: " + usuarioWeb.getNumerodoc() + "		TIPO	: " + usuarioWeb.getIdptipodocumento() + "		I :" + i);
						System.out.println(" (usuarioWeb.getIdepuntocontactodir()	: " + usuarioWeb.getIdepuntocontactodir());
						System.out.println(" usuarioWeb.getIdepuntocontactotelf() 	: " + usuarioWeb.getIdepuntocontactotelf());
						System.out.println(" usuarioWeb.getIdepuntocontactomail()	: " + usuarioWeb.getIdepuntocontactomail());
						
						mediocontactomail		= new MedioContacto();
						
						mediocontactomail.setUsucreacion("CARGA_NWR");
						mediocontactomail.setEmail(lstUsrLdap.get(i).getMail());
						mediocontactomail.setCadidpusopuntocontacto(Constants.USO_DIRECCION_PWEB);
						
				        perfilService.regMediocontactoUsuarioweb(mediocontactomail,Constants.TMC_EMAIL);
				        
				        usuarioWeb.setIdepuntocontactomail(mediocontactomail.getIdepuntocontacto().toString());		        
				        usuarioWeb.setUsuario("CARGA_NWR");
				        
				        perfilService.obtIdeterceroUsuarioweb(usuarioWeb);
				        perfilService.regUsuarioweb(usuarioWeb);
				        
				        perfilService.actUsuarioweb(usuarioWeb,Constants.ACCION_WEB_RENOVAR_DATOSCONTACTO);
				        
				        lstUsrLdapReg.add(lstUsrLdap.get(i));
				       
					}
				}
			} catch (Exception e) {
				// TODO: handle exception		
				e.printStackTrace();
			}			
		}
		/* FIN REGISTRANDO EN TER_TERCEROWEB LOS CORREOS DE USUARIOS LDAP */
		
		/* INI PREPARANDO RESPUESTA JSON */
		for (int x = 0; x < lstUsrLdapReg.size(); x++) 
			cadenaJson +=  UtilWeb.objectToJson(lstUsrLdapReg.get(x), null, UsuarioLDAP.class.getName() )+",";				
		
		if (cadenaJson.length()>0)
			cadenaJson	= cadenaJson.substring(0, cadenaJson.length()-1);
		/* FIN PREPARANDO RESPUESTA JSON */
		
		result = "{\"RESULTADO\":["+cadenaJson+"]}";
		
		this.escribirTextoSalida(response, result);
	}
	
	
	public String goCargaEntregaTarjeta(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "carga/carga-entrega-tarjeta";
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
	
	public void lstParametro(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Parametro> listaParametro = null;
		String lstparametro = "";

		Parametro parametro = null;
 
		parametro = new Parametro(request.getParameterMap());

		try {
			listaParametro = deliveryService.lstParametro(parametro);
			lstparametro = commons.web.UtilWeb.listaToArrayJson(listaParametro, null,
					Parametro.class.getName());
		} catch (Error e) {
			lstparametro = "{" + e.getMessage() + "}";
		}

		this.escribirTextoSalida(response, lstparametro);
		 
	}
	
}
