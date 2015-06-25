package bbva.delivery.tarjetas.service.imp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

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
import bbva.delivery.tarjetas.dao.DeliveryDao;
import bbva.delivery.tarjetas.service.DeliveryService;

@Service("portalWebService")
@Transactional(propagation=Propagation.SUPPORTS)
public class DeliveryServiceImp implements DeliveryService {

	@Autowired
	private DeliveryDao portalWebDao;

	public void test() {
		// TODO Auto-generated method stub
		System.out.println("service ok");
		
		portalWebDao.test();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject cargarExcelDelivery(Archivo archivo) throws FileNotFoundException{
		
		JSONObject joRetorno = new JSONObject();
		Integer resultado = 0;
		String mensaje = "Datos subidos satisfactoriamente!!";
		String logCarga = "";
		Integer errorCarga = 0; 
		 
		Delivery carga = new Delivery();
		
		FileInputStream fileInput = null;
		Workbook wb = null;
		try {
			fileInput = new FileInputStream(archivo.getFilename());

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
			 
			cargarArchivoDelivery(archivo);
			
			//Integer existeCourier;
			
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
					
					//existeCourier = valCourierDelivery(getCellValue(row, 0));
					
					carga.setIddelivery(null);
					carga.setGrupocarga(idgrupoCarga);
					carga.setIdarchivo(archivo.getIdarchivo());
					
					carga.setTipodocumento(getCellValue(row, 0));
					carga.setNrodocumentocli(getCellValue(row, 1));
					carga.setNombrescli(getCellValue(row, 2));
					carga.setTipotarjeta(getCellValue(row, 3));
					carga.setPridigtarjeta(getCellValue(row, 4));
					carga.setUltdigtarjeta(getCellValue(row, 5));
					carga.setNrocontrato(getCellValue(row, 6));
					try {
						carga.setMtoasoctarjeta(new BigDecimal(getCellValue(row, 7)));
					} catch (Exception e) {
						carga.setMtoasoctarjeta(null);
					}
					
					carga.setFecentrega(getCellValue(row, 8));
					carga.setHoraentrega(getCellValue(row, 9));
				 
					 
					carga.setLugarentrega(getCellValue(row, 10));

					carga.setIndverificacion(getCellValue(row, 11));
					carga.setDireccioncli(getCellValue(row, 12));
					carga.setDistritocli(getCellValue(row, 13));
					
					try {
						carga.setLatitudofi(new BigDecimal(getCellValue(row, 14)));
					} catch (Exception e) {
						carga.setLatitudofi(null);
					}
					
					try {
						carga.setLongitudofi(new BigDecimal(getCellValue(row, 15)));
					} catch (Exception e) {
						carga.setLongitudofi(null);
					}
					
					carga.setCorreocli(getCellValue(row, 16));
					carga.setTelmovilcli(getCellValue(row, 17));
					carga.setOrdenentrega(getCellValue(row, 18));
					
					carga.setCodcourier(getCellValue(row, 19));
					carga.setDnitrabajador(getCellValue(row, 20));
					
					try {
						portalWebDao.cargaDelivery(carga);
						
					} catch (Exception e) {
						errorCarga = 2;
						logCarga += "Fila N�: " + r + "\n Error : " + e.getMessage() + "\n\n"; 
					}
					 
				}
			}
			
			if (errorCarga == 2){
				resultado = errorCarga;
				mensaje = "Hubo problemas en la carga de uno o m�s registros \n " + logCarga;
			}

		} catch (Exception e) {
			resultado = 1;
			mensaje = "El sistema no pudo ubicar el archivo indicado";
		}
 
		joRetorno.put("resultado", resultado);
		joRetorno.put("mensaje", mensaje);
		
		return joRetorno;
	}
	
	/** Retorna el valor de la celda indicada en la fila **/
	public static String getCellValue(Row row, int posicion) {

		Cell cell = row.getCell(posicion);
		String value = null;

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

		return value;
	}
	
	@Override
	public List<Delivery> lstDelivery(Delivery param){
		return portalWebDao.lstDelivery(param);
	}
	 
	public BigDecimal crearGrupoCargaDelivery(){
		return portalWebDao.crearGrupoCargaDelivery();
	}
 
	public void mntDelivery(Delivery param) throws FileNotFoundException {
		portalWebDao.mntDelivery(param);
	}
	 

	@Override
	public Integer valCourierDelivery(String dnicourier) {
		return portalWebDao.valCourierDelivery(dnicourier);
	}
 
	public void cargaDelivery(Delivery param){
		portalWebDao.cargaDelivery(param);
	}
	
	public void cargarArchivoDelivery(Archivo param){
		portalWebDao.cargarArchivoDelivery(param);
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject cargarExcelDelivery2(MultipartFile multipartFile, Archivo archivo) throws FileNotFoundException{
		
		JSONObject joRetorno = new JSONObject();
		Integer resultado = 0;
		String mensaje = "Datos subidos satisfactoriamente!!";
		String logCarga = "";
		Integer errorCarga = 0; 
		 
		Delivery carga = new Delivery();
		
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
			 
			cargarArchivoDelivery(archivo);
			
			//Integer existeCourier;
			
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
					
					//existeCourier = valCourierDelivery(getCellValue(row, 0));
					
					carga.setIddelivery(null);
					carga.setGrupocarga(idgrupoCarga);
					carga.setIdarchivo(archivo.getIdarchivo());
					
					carga.setTipodocumento(getCellValue(row, 0));
					carga.setNrodocumentocli(getCellValue(row, 1));
					carga.setNombrescli(getCellValue(row, 2));
					carga.setTipotarjeta(getCellValue(row, 3));
					carga.setPridigtarjeta(getCellValue(row, 4));
					carga.setUltdigtarjeta(getCellValue(row, 5));
					carga.setNrocontrato(getCellValue(row, 6));
					try {
						carga.setMtoasoctarjeta(new BigDecimal(getCellValue(row, 7)));
					} catch (Exception e) {
						carga.setMtoasoctarjeta(null);
					}
					
					carga.setFecentrega(getCellValue(row, 8));
					carga.setHoraentrega(getCellValue(row, 9));
				 
					 
					carga.setLugarentrega(getCellValue(row, 10));

					carga.setIndverificacion(getCellValue(row, 11));
					carga.setDireccioncli(getCellValue(row, 12));
					carga.setDistritocli(getCellValue(row, 13));
					
					try {
						carga.setLatitudofi(new BigDecimal(getCellValue(row, 14)));
					} catch (Exception e) {
						carga.setLatitudofi(null);
					}
					
					try {
						carga.setLongitudofi(new BigDecimal(getCellValue(row, 15)));
					} catch (Exception e) {
						carga.setLongitudofi(null);
					}
					
					carga.setCorreocli(getCellValue(row, 16));
					carga.setTelmovilcli(getCellValue(row, 17));
					carga.setOrdenentrega(getCellValue(row, 18));
					
					carga.setCodcourier(getCellValue(row, 19));
					carga.setDnitrabajador(getCellValue(row, 20));
					
					try {
						portalWebDao.cargaDelivery(carga);
						
					} catch (Exception e) {
						errorCarga = 2;
						logCarga += "Fila N�: " + r + "\n Error : " + e.getMessage() + "\n\n"; 
					}
					 
				}
			}
			
			if (errorCarga == 2){
				resultado = errorCarga;
				mensaje = "Hubo problemas en la carga de uno o m�s registros \n " + logCarga;
			}

		} catch (Exception e) {
			resultado = 1;
			mensaje = "El sistema no pudo ubicar el archivo indicado";
		}
 
		joRetorno.put("resultado", resultado);
		joRetorno.put("mensaje", mensaje);
		
		return joRetorno;
	}

}
