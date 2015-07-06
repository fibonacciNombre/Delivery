
package bbva.delivery.tarjetas.service.imp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
 

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.service.ComunService;
import bbva.delivery.tarjetas.courier.service.CourierService;
import bbva.delivery.tarjetas.dao.DeliveryDao;
import bbva.delivery.tarjetas.service.DeliveryService;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.service.TerceroService;

@Service("deliveryService")
@Transactional(propagation=Propagation.SUPPORTS)
public class DeliveryServiceImp implements DeliveryService {

	@Autowired
	private DeliveryDao deliveryDao;

	@Autowired
	private TerceroService terceroService;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private CourierService courierService;
	
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("service ok");
		
		//portalWebDao.test();
	}
		
	/** Retorna el valor de la celda indicada en la fila **/
	public static String getCellValue(Row row, int posicion) {

		Cell cell = row.getCell(posicion);
		String value = null;
		if(cell != null){
			

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				value = "" + cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				value = "" + cell.getStringCellValue();
				break;
			default:
				value = "";
				break;
			}
	
		}
		
		return value;
	}
	
	@Override
	public List<Delivery> lstDelivery(Delivery delivery, Tercero tercero){
		return deliveryDao.lstDelivery(delivery, tercero);
	}
	 
	public BigDecimal crearGrupoCargaDelivery(){
		return deliveryDao.crearGrupoCargaDelivery();
	}
 
	public void mntDelivery(Delivery param) {
		

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		param.setHistorial("Usuario: " + param.getUsuario() + ", Fecha:" + dateFormat.format(date) + ", " +  param.toString()); 
		
		deliveryDao.mntDelivery(param);
	}
	 

	@Override
	public Integer valCourierDelivery(String dnicourier) {
		return deliveryDao.valCourierDelivery(dnicourier);
	}
  
	public void mntArchivo(Archivo param){
		deliveryDao.mntArchivo(param);
	}

	public Workbook instanciarExcel(MultipartFile multipartFile, Archivo archivo) throws IOException {
		InputStream fileInput = null;
		Workbook wb = null;

		fileInput = multipartFile.getInputStream();
		/** Se instancia si es xls o xlsx **/
		if (archivo.getFilename().toLowerCase().endsWith("xlsx")) {
			wb = new XSSFWorkbook(fileInput);

		} else if (archivo.getFilename().toLowerCase().endsWith("xls")) {
			wb = new HSSFWorkbook(fileInput);
		}

		return wb;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject validarParametrosExcel(Row firstRow){
		JSONObject jo = new JSONObject();
		Integer resultado = 0;
		String mensaje = "";
		
		Parametro param = new Parametro();
		param.setIdparametrotipo("DELWEB_XLSDELIVERY");
		List<Parametro> lstParametro;
		lstParametro = comunService.lstParametro(param);
		
		/** Cargar la primera hoja **/
		Integer numberOfColumns = firstRow.getPhysicalNumberOfCells();
		
		/** Cantidad de filas de la hoja **/
		Integer numberOfParams = lstParametro.size(); 
		
		if(numberOfColumns != numberOfParams){
			resultado = 1;
			mensaje = "El n�mero de columnas no coincide con el configurado.";
		} else {
			/** Validar que los campos ingresados estan en el orden correcto **/
			for (int i = 0; i < lstParametro.size(); i++) {
				if(!lstParametro.get(i).getAbreviatura().equals(getCellValue(firstRow, i))){
					resultado = 1;
					mensaje = "La columna " + i + ": " + getCellValue(firstRow, i) + ", no est� en el orden configurado.";
				} 
			}	
		}

		jo.put("resultado", resultado);
		jo.put("mensaje", mensaje);
		
		return jo;
	}

	public String getFormatMensaje(String msj){
		
		return "<tr><td>" + msj + "</td></tr>";
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject cargarExcelDelivery(MultipartFile multipartFile, Archivo archivo) throws FileNotFoundException{
		
		JSONObject joRetorno = new JSONObject();
		Integer resultado = 0;
		String mensaje = "";  

		String mensajeRegistro = "<center><table>";  
		BigDecimal idtercero = null;
 
		String nrodocumentocli, tipodocumento, nombrescli, tipotarjeta, pridigtarjeta, ultdigtarjeta, nrocontrato, distritocli,     
			   mtoasoctarjeta, fecentrega, horaentrega, lugarentrega, indverificacion, direccioncli, telfmovilcli,
			   latitudofi, longitudofi, correocli, ordenentrega, dnitrabajador;
		
		Delivery carga = new Delivery();
		/** Se obtiene la extensi�n del archivo **/
		String extArchivo = FilenameUtils.getExtension(archivo.getFilename());
		
		/** Verificamos si es una de las extensiones permitidas **/
		Integer extPermitida = 0;
		for (String extItem : Constants.EXT_EXCEL) {
		    if (extItem.equals(extArchivo)) {
		    	extPermitida = 1;
		        break;
		    }
		}

		/** Si es permitida**/
		if(extPermitida == 1){
			
			/** Instanciamos el workbook **/
			Workbook wb;
			try {
				wb = instanciarExcel(multipartFile, archivo);
				
				/** Generamos el correlativo de carga **/
				BigDecimal idgrupoCarga = crearGrupoCargaDelivery();
				
				
				/** Registramos el archivo **/
				try {
					mntArchivo(archivo);
					 
					/** Cargamos la primera hoja **/
					Sheet sheet = wb.getSheetAt(Constants.NROHOJAEXCEL);

					/** Cantidad de filas de la hoja **/
					int rows = sheet.getPhysicalNumberOfRows();
						 
					/** Validamos el excel **/
					joRetorno = validarParametrosExcel(sheet.getRow(0));
					resultado = (Integer) joRetorno.get("resultado");
					mensaje =  (String) joRetorno.get("mensaje");
					
					if(resultado == 0){
						
						/**Seteamos los datos generales **/
						carga.setGrupocarga(idgrupoCarga);
						carga.setUsuario(archivo.getUsuario());
						carga.setIdpestado(Constants.DELIVERY_IDPESTADO_ACTIVO);

						carga.setIdpestadodelivery(Constants.DELIVERY_PENDIENTE);
						carga.setIdarchivo(archivo.getIdarchivo());
						
						/** Recorremos las filas sin contar el header **/
						for (int r = 1; r < rows; r++) {
							Row row = sheet.getRow(r);
							if (row == null) {
								continue;
							}
							
							tipodocumento 	= getCellValue(row, 0);
							nrodocumentocli = getCellValue(row, 1);
							nombrescli 		= getCellValue(row, 2);
							tipotarjeta		= getCellValue(row, 3);
							pridigtarjeta 	= getCellValue(row, 4); 
							ultdigtarjeta 	= getCellValue(row, 5);
							nrocontrato 	= getCellValue(row, 6);
							mtoasoctarjeta 	= getCellValue(row, 7);
							fecentrega 		= getCellValue(row, 8); 
							horaentrega 	= getCellValue(row, 9); 
							lugarentrega	= getCellValue(row, 10); 
							indverificacion = getCellValue(row, 11);
							direccioncli 	= getCellValue(row, 12); 
							distritocli		= getCellValue(row, 13); 
							latitudofi 		= getCellValue(row, 14); 
							longitudofi 	= getCellValue(row, 15); 
							correocli 		= getCellValue(row, 16); 
							telfmovilcli    = getCellValue(row, 17); 
							ordenentrega 	= getCellValue(row, 18); 
							dnitrabajador 	= getCellValue(row, 19);
							
							if (tipodocumento == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING  ;
								mensaje += getFormatMensaje(" - Tipo de documento no enviado.");
							}
							
							if (nrodocumentocli == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING  ;
								mensaje += getFormatMensaje(" - Nro de documento no enviado.");
							}
							
							if (nombrescli == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Nombre del cliente no enviado.");
							}
							
							if (pridigtarjeta == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Primeros 4 digitos de tarjeta no enviado.");
							}
							
							if (ultdigtarjeta == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Ultimos 4 digitos de tarjeta no enviado.");
							}
							
							if (nrocontrato == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Nro de contrato no enviado.");
							}
							
							if (mtoasoctarjeta == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Monto de linea no enviado.");
							}
							
							if (fecentrega == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Fecha de entrega no enviado.");
							}
							
							if (horaentrega == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Hora de entrega no enviado.");
							}
						
							if (lugarentrega == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Lugar de entrega no enviado.");
							}
							
							if (indverificacion == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Indicador de verificaci�n no enviado.");	
							}
							
							if (direccioncli == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Direcci�n del cliente no enviado.");
							}
							
							if (latitudofi == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Latitud no enviado.");
							}
						
							if (longitudofi == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Longitud no enviado.");
							}
							
							if (correocli == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Correo del cliente no enviado.");	
							}
							
							if (ordenentrega == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
								mensaje += getFormatMensaje(" - Orden de entrega no enviado.");
							}
							
							if (dnitrabajador == null) {
								resultado = Constants.DELIVERY_CARGA_WARNING ;
							}
							
							carga.setIddelivery(null);
								
							carga.setTipodocumento(tipodocumento);
							carga.setNrodocumentocli(nrodocumentocli);
							carga.setNombrescli(nombrescli);
							carga.setTipotarjeta(tipotarjeta);
							carga.setPridigtarjeta(pridigtarjeta);
							carga.setUltdigtarjeta(ultdigtarjeta);
							carga.setNrocontrato(nrocontrato);
							
							try {
								carga.setMtoasoctarjeta(new BigDecimal(mtoasoctarjeta));
							} catch (Exception e) {
								carga.setMtoasoctarjeta(null);
							}
							
							carga.setFecentrega(fecentrega);
							carga.setHoraentrega(horaentrega);
							carga.setLugarentrega(lugarentrega);

							carga.setIndverificacion(indverificacion);
							carga.setDireccioncli(direccioncli);
							carga.setDistritocli(distritocli);
							
							try {
								carga.setLatitudofi(new BigDecimal(latitudofi));
							} catch (Exception e) {
								carga.setLatitudofi(null);
							}
							
							try {
								carga.setLongitudofi(new BigDecimal(longitudofi));
							} catch (Exception e) {
								carga.setLongitudofi(null);
							}
							
							carga.setCorreocli(correocli);
							carga.setTelmovilcli(telfmovilcli);
							carga.setOrdenentrega(ordenentrega);
							carga.setDnitrabajador(dnitrabajador);

							carga.setIdpestadocarga(resultado);
							
							/** Verificacion si dni del colaborador esta en algun tercero **/
							idtercero = terceroService.obtTerceroXNrodoc(carga.getDnitrabajador());
							
							/** Si no esta lo registramos como tercero **/
							if(idtercero == null){
								Tercero tercero =  new Tercero();
								tercero.setIdcourier(archivo.getIdcourier());
								tercero.setIdpestado(Constants.DELIVERY_IDPESTADO_ACTIVO);
								tercero.setUsuario(archivo.getUsuario());
								tercero.setIdptipodocumento(Constants.TIPODOCUMENTO_DNI);
								tercero.setNrodocumento(carga.getDnitrabajador());
								tercero.setIdcourier(archivo.getIdcourier());
								terceroService.mntTercero(tercero);
								idtercero = tercero.getIdtercero();
							
								mensaje += getFormatMensaje("DNI del trabajador no enviado, se ha creado el colaborador con c�digo: " + idtercero + "." );
							}
							
							if(resultado == Constants.DELIVERY_CARGA_WARNING ){

								carga.setIdpestadocarga(Constants.DELIVERY_CARGA_WARNING );
								mensajeRegistro = mensajeRegistro + getFormatMensaje("<b>Alerta! Fila Nro " + r +"</b>" + mensaje );
								mensaje = "";
								
								tipodocumento 	= null;
								nrodocumentocli = null;
								nombrescli 		= null;
								pridigtarjeta 	= null;
								ultdigtarjeta 	= null;
								nrocontrato 	= null;
								mtoasoctarjeta 	= null;
								telfmovilcli 	= null;
								fecentrega 		= null;
								horaentrega 	= null;
								lugarentrega	= null;
								indverificacion = null;
								direccioncli 	= null;
								latitudofi 		= null;
								longitudofi 	= null;
								correocli 		= null;
								ordenentrega 	= null;
								dnitrabajador 	= null; 
								
								resultado = Constants.DELIVERY_CARGA_OK;
							}
								
							carga.setIdtercero(idtercero);
							
							mntDelivery(carga);
 
						}	
					} 
 				} catch (Exception e) {
					resultado = 1;
					mensaje = "Error al registrar courier y fecha de entrega.";
				}
				
			} catch (IOException e1) {
				resultado = 1;
				mensaje = "Error al levantar el archivo excel";
			} 	
			
		} else {
			resultado = 1;
			mensaje = "Solo se permiten archivos xls o xlsx.";
			
		}
		 
		if(!mensajeRegistro.equals("<center><table>")) {
			mensaje = mensajeRegistro + "</table></center>";
			resultado = Constants.DELIVERY_CARGA_WARNING ;
		}
			
		joRetorno.put("resultado", resultado);
		joRetorno.put("mensaje", mensaje);
		
		return joRetorno;
	}
	
	public String exportarListaDelivery(Delivery delivery) throws IOException{
		Workbook wb = new HSSFWorkbook();
		Sheet personSheet = wb.createSheet("Datos de Delivery");
		Row headerRow = personSheet.createRow(0);
		Cell tipodocumentoHeader = headerRow.createCell(0);
		tipodocumentoHeader.setCellValue("Tipo de Documento");
		
		Cell nrodocHeader = headerRow.createCell(1);
		nrodocHeader.setCellValue("Nro Documento");
		
		Cell nombreHeader = headerRow.createCell(2);
		nombreHeader.setCellValue("Nombre Cliente");
		
		Cell primHeader = headerRow.createCell(3);
		primHeader.setCellValue("Primeros 4 digitos");
		
		Cell ultHeader = headerRow.createCell(4);
		ultHeader.setCellValue("Ultimos 4 digitos");
		
		Cell nrocontratoHeader = headerRow.createCell(5);
		nrocontratoHeader.setCellValue("Nro Contrato");
		
		Cell montoHeader = headerRow.createCell(6);
		montoHeader.setCellValue("Monto Linea");
		
		Cell fechaentregaHeader = headerRow.createCell(7);
		fechaentregaHeader.setCellValue("Fecha de entrega");
		
		Cell horaentregaHeader = headerRow.createCell(8);
		horaentregaHeader.setCellValue("Hora de entrega");
		
		Cell lugarHeader = headerRow.createCell(9);
		lugarHeader.setCellValue("Lugar de Entrega");
		
		Cell indverificacionHeader = headerRow.createCell(10);
		indverificacionHeader.setCellValue("Indica Verificaci�n");
		
		Cell direccionHeader = headerRow.createCell(11);
		direccionHeader.setCellValue("Direcci�n");
		
		Cell latitudHeader = headerRow.createCell(12);
		latitudHeader.setCellValue("Latitud");
		
		Cell longitudHeader = headerRow.createCell(13);
		longitudHeader.setCellValue("Longitud");
		
		Cell correoHeader = headerRow.createCell(14);
		correoHeader.setCellValue("Correo Cliente");
		
		Cell ordenHeader = headerRow.createCell(15);
		ordenHeader.setCellValue("Orden de Entrega");
		
		Cell dnitrabajadorHeader = headerRow.createCell(16);
		dnitrabajadorHeader.setCellValue("DNI Trabajador");
		
		List<Delivery> lstDelivery = lstDelivery(delivery, null);
		
		int row = 1;
		
		for(Delivery d: lstDelivery){
			Row dataRow = personSheet.createRow(row);
 
		    Cell tipodocumento  = dataRow.createCell(0);
		    tipodocumento.setCellValue(d.getTipodocumento());

		    Cell nrodocumentocli = dataRow.createCell(1);
		    nrodocumentocli.setCellValue(d.getNrodocumentocli());
		    
		    Cell nombrescli = dataRow.createCell(2);
		    nombrescli.setCellValue(d.getNombrescli());
		    
		    Cell pridigtarjeta = dataRow.createCell(3);
		    pridigtarjeta.setCellValue(d.getPridigtarjeta());
		    
		    Cell ultdigtarjeta = dataRow.createCell(4); 
		    ultdigtarjeta.setCellValue(d.getUltdigtarjeta());
		    
		    Cell nrocontrato = dataRow.createCell(5); 
		    nrocontrato.setCellValue(d.getNrocontrato());
		    
		    Cell mtoasoctarjeta = dataRow.createCell(6);
		    mtoasoctarjeta.setCellValue(""+d.getMtoasoctarjeta());
		    
		    Cell fecentrega = dataRow.createCell(7);
		    fecentrega.setCellValue(d.getFecentrega());
		    
		    Cell horaentrega = dataRow.createCell(8);
		    horaentrega.setCellValue(d.getHoraentrega());
		    
		    Cell lugarentrega = dataRow.createCell(9);
		    lugarentrega.setCellValue(d.getLugarentrega());
		    
		    Cell indverificacion = dataRow.createCell(10);
		    indverificacion.setCellValue(d.getIndverificacion());
		    
		    Cell direccioncli = dataRow.createCell(11); 
		    direccioncli.setCellValue(d.getDireccioncli());
		    
		    Cell latitudofi = dataRow.createCell(12);
		    latitudofi.setCellValue("" + d.getLatitudofi());
		    
		    Cell longitudofi = dataRow.createCell(13); 
		    longitudofi.setCellValue("" + d.getLongitudofi());
		    
		    Cell correocli = dataRow.createCell(14);
		    correocli.setCellValue(d.getCorreocli());
		    
		    Cell ordenentrega = dataRow.createCell(15);
		    ordenentrega.setCellValue(d.getOrdenentrega());
		    
		    Cell dnitrabajador = dataRow.createCell(16);
		    dnitrabajador.setCellValue(d.getDnitrabajador());
		
		    row = row + 1;
		}
		
		
//		String outputDirPath = "D:/CargaDelivery.xls";

		FileOutputStream fileOut = new FileOutputStream(delivery.getRutaexpotacion());
		wb.write(fileOut);
		fileOut.close();
		
		return delivery.getRutaexpotacion();
	}


	@Override
	public String obtArchivoLstDelivery(Delivery delivery, Tercero tercero) throws IOException{
		
		
		Workbook wb 		= new HSSFWorkbook();
		
		Sheet personSheet 	= wb.createSheet("Datos de Delivery");
		Row headerRow 		= personSheet.createRow(0);
		
		Cell tipodocumentoHeader = headerRow.createCell(0);
		tipodocumentoHeader.setCellValue("Tipo de Documento");
		
		Cell nrodocHeader = headerRow.createCell(1);
		nrodocHeader.setCellValue("Nro Documento");
		
		Cell nombreHeader = headerRow.createCell(2);
		nombreHeader.setCellValue("Nombre Cliente");
		
		Cell primHeader = headerRow.createCell(3);
		primHeader.setCellValue("Primeros 4 digitos");
		
		Cell ultHeader = headerRow.createCell(4);
		ultHeader.setCellValue("Ultimos 4 digitos");
		
		Cell nrocontratoHeader = headerRow.createCell(5);
		nrocontratoHeader.setCellValue("Nro Contrato");
		
		Cell montoHeader = headerRow.createCell(6);
		montoHeader.setCellValue("Monto Linea");
		
		Cell fechaentregaHeader = headerRow.createCell(7);
		fechaentregaHeader.setCellValue("Fecha de entrega");
		
		Cell horaentregaHeader = headerRow.createCell(8);
		horaentregaHeader.setCellValue("Hora de entrega");
		
		Cell lugarHeader = headerRow.createCell(9);
		lugarHeader.setCellValue("Lugar de Entrega");
		
		Cell indverificacionHeader = headerRow.createCell(10);
		indverificacionHeader.setCellValue("Indica Verificación");
		
		Cell direccionHeader = headerRow.createCell(11);
		direccionHeader.setCellValue("Dirección");
		
		Cell latitudHeader = headerRow.createCell(12);
		latitudHeader.setCellValue("Latitud");
		
		Cell longitudHeader = headerRow.createCell(13);
		longitudHeader.setCellValue("Longitud");
		
		Cell correoHeader = headerRow.createCell(14);
		correoHeader.setCellValue("Correo Cliente");
		
		Cell ordenHeader = headerRow.createCell(15);
		ordenHeader.setCellValue("Orden de Entrega");
		
		Cell dnitrabajadorHeader = headerRow.createCell(16);
		dnitrabajadorHeader.setCellValue("DNI Trabajador");
		
		List<Delivery> lstDelivery = lstDelivery(delivery, tercero);
		
		int row = 1;
		
		for(Delivery d: lstDelivery){
			Row dataRow = personSheet.createRow(row);
 
		    Cell tipodocumento  = dataRow.createCell(0);
		    tipodocumento.setCellValue(d.getTipodocumento());

		    Cell nrodocumentocli = dataRow.createCell(1);
		    nrodocumentocli.setCellValue(d.getNrodocumentocli());
		    
		    Cell nombrescli = dataRow.createCell(2);
		    nombrescli.setCellValue(d.getNombrescli());
		    
		    Cell pridigtarjeta = dataRow.createCell(3);
		    pridigtarjeta.setCellValue(d.getPridigtarjeta());
		    
		    Cell ultdigtarjeta = dataRow.createCell(4); 
		    ultdigtarjeta.setCellValue(d.getUltdigtarjeta());
		    
		    Cell nrocontrato = dataRow.createCell(5); 
		    nrocontrato.setCellValue(d.getNrocontrato());
		    
		    Cell mtoasoctarjeta = dataRow.createCell(6);
		    mtoasoctarjeta.setCellValue(""+d.getMtoasoctarjeta());
		    
		    Cell fecentrega = dataRow.createCell(7);
		    fecentrega.setCellValue(d.getFecentrega());
		    
		    Cell horaentrega = dataRow.createCell(8);
		    horaentrega.setCellValue(d.getHoraentrega());
		    
		    Cell lugarentrega = dataRow.createCell(9);
		    lugarentrega.setCellValue(d.getLugarentrega());
		    
		    Cell indverificacion = dataRow.createCell(10);
		    indverificacion.setCellValue(d.getIndverificacion());
		    
		    Cell direccioncli = dataRow.createCell(11); 
		    direccioncli.setCellValue(d.getDireccioncli());
		    
		    Cell latitudofi = dataRow.createCell(12);
		    latitudofi.setCellValue("" + d.getLatitudofi());
		    
		    Cell longitudofi = dataRow.createCell(13); 
		    longitudofi.setCellValue("" + d.getLongitudofi());
		    
		    Cell correocli = dataRow.createCell(14);
		    correocli.setCellValue(d.getCorreocli());
		    
		    Cell ordenentrega = dataRow.createCell(15);
		    ordenentrega.setCellValue(d.getOrdenentrega());
		    
		    Cell dnitrabajador = dataRow.createCell(16);
		    dnitrabajador.setCellValue(d.getDnitrabajador());
		
		    row = row + 1;
		}

		FileOutputStream fileOut = new FileOutputStream(delivery.getRutaexpotacion());
		wb.write(fileOut);
		fileOut.close();
		
		return delivery.getRutaexpotacion();
	}


}

