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
import bbva.delivery.tarjetas.courier.service.CourierService;
import bbva.delivery.tarjetas.dao.DeliveryDao;
import bbva.delivery.tarjetas.service.DeliveryService;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.service.TerceroService;

@Service("portalWebService")
@Transactional(propagation=Propagation.SUPPORTS)
public class DeliveryServiceImp implements DeliveryService {

	@Autowired
	private DeliveryDao portalWebDao;

	@Autowired
	private TerceroService terceroService;
	
	@Autowired
	private CourierService courierService;
	
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("service ok");
		
		portalWebDao.test();
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
	public List<Delivery> lstDelivery(Delivery param){
		return portalWebDao.lstDelivery(param);
	}
	 
	public BigDecimal crearGrupoCargaDelivery(){
		return portalWebDao.crearGrupoCargaDelivery();
	}
 
	public void mntDelivery(Delivery param) {
		

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		param.setHistorial("Usuario: " + param.getUsuario() + ", Fecha:" + dateFormat.format(date) + ", " +  param.toString()); 
		
		portalWebDao.mntDelivery(param);
	}
	 

	@Override
	public Integer valCourierDelivery(String dnicourier) {
		return portalWebDao.valCourierDelivery(dnicourier);
	}
  
	public void mntArchivo(Archivo param){
		portalWebDao.mntArchivo(param);
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject cargarExcelDelivery(MultipartFile multipartFile, Archivo archivo) throws FileNotFoundException{
		
		JSONObject joRetorno = new JSONObject();
		Integer resultado = 0;
		String mensaje = "";  

		String mensajeRegistro = "";  
		BigDecimal idtercero = null;
		
		Delivery carga = new Delivery();
		String ext = FilenameUtils.getExtension(archivo.getFilename());
		
		Integer tipoarchivo = courierService.obtTipoarchXExt(ext);
		
		
		if(tipoarchivo != null){
			
			//Integer idcourier = courierService.obtCourierXCodbbva(archivo.getCodcourier());
			
			//if(idcourier != null) {
				
				//FileInputStream fileInput = null;
				InputStream fileInput = null;
				Workbook wb = null;
				try {
					//fileInput = new FileInputStream(archivo.getFilename());
					fileInput = multipartFile.getInputStream();
					/** Se instancia si es xls o xlsx **/
					if (archivo.getFilename().toLowerCase().endsWith("xlsx")) {
						try {
							wb = new XSSFWorkbook(fileInput);
						} catch (Exception e) {
							resultado = 1;
							mensaje = "Error al levantar el archivo excel";
						}

					} else if (archivo.getFilename().toLowerCase().endsWith("xls")) {
						try {
							wb = new HSSFWorkbook(fileInput);
						} catch (Exception e) { 
							resultado = 1;
							mensaje = "Error al levantar el archivo excel";
						} 
					}
					
					BigDecimal idgrupoCarga = crearGrupoCargaDelivery();
					
					archivo.setIdtipoarchivo(tipoarchivo);
					mntArchivo(archivo);
					
					// Integer existeCourier;
					String  nrodocumentocli, 
							tipodocumento, 
							nombrescli, 
							pridigtarjeta, 
							ultdigtarjeta, 
							nrocontrato, 
							mtoasoctarjeta, 
							fecentrega, 
							horaentrega, 
							lugarentrega, 
							indverificacion, 
							direccioncli, 
							latitudofi, 
							longitudofi, 
							correocli, 
							ordenentrega, 
							dnitrabajador;
					
					/** Recorrer las hojas del excel **/
					for (int k = 0; k < wb.getNumberOfSheets(); k++) {
						Sheet sheet = wb.getSheetAt(k);

						/** Cantidad de filas de la hoja **/
						int rows = sheet.getPhysicalNumberOfRows();

						/** Recorremos las filas **/
						for (int r = 1; r < rows; r++) {
							Row row = sheet.getRow(r);
							if (row == null) {
								continue;
							}
							 
							carga.setIddelivery(null);
							carga.setGrupocarga(idgrupoCarga);
							carga.setUsuario(archivo.getUsuario());
							carga.setIdpestado(1);

							carga.setIdpestadodelivery(0);
							carga.setIdpestadocarga(1);
							carga.setIdarchivo(archivo.getIdarchivo());
							
							 
							tipodocumento = getCellValue(row, 0);
							nrodocumentocli = getCellValue(row, 1);
							nombrescli = getCellValue(row, 2); 
							pridigtarjeta = getCellValue(row, 4); 
							ultdigtarjeta = getCellValue(row, 5);
							nrocontrato = getCellValue(row, 6);
							mtoasoctarjeta =  getCellValue(row, 7 );
							fecentrega = getCellValue(row, 8); 
							horaentrega = getCellValue(row, 9); 
							lugarentrega= getCellValue(row, 10); 
							indverificacion = getCellValue(row, 11);
							direccioncli = getCellValue(row, 12); 
							latitudofi = getCellValue(row, 14); 
							longitudofi = getCellValue(row, 15); 
							correocli = getCellValue(row, 16); 
							ordenentrega = getCellValue(row, 18); 
							dnitrabajador = getCellValue(row, 20);
							
						if (tipodocumento == null) {
							resultado = 2;
							mensaje += "Tipo de documento no enviado, ";

						}
						if (nrodocumentocli == null) {
							resultado = 2;
							mensaje += "Nro de documento no enviado, ";

						}
						if (nombrescli == null) {
							resultado = 2;
							mensaje += "Nombre del cliente no enviado, ";
						}
						if (pridigtarjeta == null) {
							resultado = 2;
							mensaje += "Primeros 4 digitos de tarjeta no enviado, ";

						}
						if (ultdigtarjeta == null) {
							resultado = 2;
							mensaje += "Ultimos 4 digitos de tarjeta no enviado, ";

						}
						if (nrocontrato == null) {
							resultado = 2;
							mensaje += "Nro de contrato no enviado, ";

						}
						if (mtoasoctarjeta == null) {
							resultado = 2;
							mensaje += "Monto de linea no enviado, ";

						}
						if (fecentrega == null) {
							resultado = 2;
							mensaje += "Fecha de entrega no enviado, ";

						}
						if (horaentrega == null) {
							resultado = 2;
							mensaje += "Hora de entrega no enviado, ";
						}

						if (lugarentrega == null) {
							resultado = 2;
							mensaje += "Lugar de entrega no enviado, ";

						}
						if (indverificacion == null) {
							resultado = 2;
							mensaje += "Indicador de verificación no enviado, ";

						}
						if (direccioncli == null) {
							resultado = 2;
							mensaje += "Dirección del cliente no enviado, ";

						}
						if (latitudofi == null) {
							resultado = 2;
							mensaje += "Latitud no enviado, ";
						}

						if (longitudofi == null) {
							resultado = 2;
							mensaje += "Longitud no enviado, ";

						}
						if (correocli == null) {
							resultado = 2;
							mensaje += "Correo del cliente no enviado, ";

						}
						if (ordenentrega == null) {
							resultado = 2;
							mensaje += "Orden de entrega no enviado, ";

						}
						if (dnitrabajador == null) {
							resultado = 2;
							mensaje += "DNI del trabajador no enviado, ";

						}
								
							
							carga.setTipodocumento(tipodocumento);
							carga.setNrodocumentocli(nrodocumentocli);
							carga.setNombrescli(nombrescli);
							carga.setTipotarjeta(getCellValue(row, 3));
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
							carga.setDistritocli(getCellValue(row, 13));
							
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
							carga.setTelmovilcli(getCellValue(row, 17));
							carga.setOrdenentrega(ordenentrega);
							
							carga.setCodcourier(getCellValue(row, 19));
							carga.setDnitrabajador(dnitrabajador);
							
							 
								 
								idtercero = terceroService.obtTerceroXNrodoc(carga.getDnitrabajador());
								
								if(idtercero == null){
									Tercero tercero =  new Tercero();
									tercero.setNrodocumento(carga.getDnitrabajador());
									 
									terceroService.mntTercero(tercero);
									idtercero = tercero.getIdtercero();
								}
								

								carga.setHistorial(mensaje);
								if(resultado == 2){

									carga.setIdpestadocarga(2);
									mensajeRegistro = mensajeRegistro + "Alerta! Fila Nro: " + r + ", " + mensaje + "\n";
									mensaje = "";
									tipodocumento = null;
									nrodocumentocli = null;
									nombrescli = null;
									pridigtarjeta = null;
									ultdigtarjeta = null;
									nrocontrato = null;
									mtoasoctarjeta =  null;
									fecentrega = null;
									horaentrega = null;
									lugarentrega= null;
									indverificacion = null;
									direccioncli = null;
									latitudofi = null;
									longitudofi = null;
									correocli = null;
									ordenentrega = null;
									dnitrabajador = null;
									resultado = 0;
								}
									
								carga.setIdtercero(idtercero);
								
								mntDelivery(carga);
								
							
							 
						}
					}
					 

				} catch (Exception e) {
					resultado = 1;
					mensaje = "El sistema no pudo ubicar el archivo indicado";
				}
			
			/*} else {
				resultado = 1;
				mensaje = "No existe el curier seleccionado";
			}*/
			
		} else {
			resultado = 1;
			mensaje = "Archivo no permitido";
		}
		
		
		if(!mensajeRegistro.equals("")) {
			mensaje = mensajeRegistro;
			resultado = 2;
		}
			
		joRetorno.put("resultado", resultado);
		joRetorno.put("mensaje", mensaje);
		
		return joRetorno;
	}
	
	public void exportarListaDelivery(Delivery delivery) throws IOException{
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
		
		List<Delivery> lstDelivery = lstDelivery(delivery);
		
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
		 
		String outputDirPath = "D:/CargaDelivery.xls";
		FileOutputStream fileOut = new FileOutputStream(outputDirPath);
		wb.write(fileOut);
		fileOut.close();
	}

}
