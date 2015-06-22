package bbva.delivery.tarjetas.service.imp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import bbva.delivery.tarjetas.bean.CargaEntregaTarjeta;
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
	public JSONObject cargaEntregaTarjeta(String fileName) throws FileNotFoundException{
		
		JSONObject joRetorno = new JSONObject();
		Integer resultado = 0;
		String mensaje = "Datos subidos satisfactoriamente!!";
		String logCarga = "";
		Integer errorCarga = 0; 
		
		
		CargaEntregaTarjeta carga = new CargaEntregaTarjeta();
		
		FileInputStream fileInput = null;
		Workbook wb = null;
		try {
			fileInput = new FileInputStream(fileName);

			/** Se instancia si es xls o xlsx **/
			if (fileName.toLowerCase().endsWith("xlsx")) {
				try {
					wb = new XSSFWorkbook(fileInput);
				} catch (Exception e) {
					resultado = 1;
					mensaje = "Error al levantar el archivo excel";
				}

			} else if (fileName.toLowerCase().endsWith("xls")) {
				try {
					wb = new HSSFWorkbook(fileInput);
				} catch (Exception e) { 
					resultado = 1;
					mensaje = "Error al levantar el archivo excel";
				} 
			}
			
			BigDecimal grupoCarga = crearGrupoCarga();
			
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
					 
					carga.setIdecarga(null);
					carga.setGrupocarga(grupoCarga);
					carga.setNumerotarjeta(getCellValue(row, 0));
					carga.setUltimosdigitos(getCellValue(row, 1));
					carga.setDnicliente(getCellValue(row, 2));
					carga.setNomcliente(getCellValue(row, 3));
					carga.setLoccliente(getCellValue(row, 4));
					carga.setDircliente(getCellValue(row, 5));
					carga.setDistcliente(getCellValue(row, 6));
					carga.setTipoprod(getCellValue(row, 7));
					carga.setSubtipoprod(getCellValue(row, 8));
					
					try {
						carga.setMontocred(new BigDecimal(getCellValue(row, 9)));
					} catch (Exception e) {
						carga.setMontocred(null);
					}
					
					carga.setUbientrega(getCellValue(row, 10));
					
					try {
						carga.setLatoficina(new BigDecimal(getCellValue(row, 11)));
					} catch (Exception e) {
						carga.setLatoficina(null);
					}
					
					try {
						carga.setLngoficina(new BigDecimal(getCellValue(row, 12)));
					} catch (Exception e) {
						carga.setLngoficina(null);
					}
					
					try {
						carga.setLatdomicilio(new BigDecimal(getCellValue(row, 13)));
					} catch (Exception e) {
						carga.setLatdomicilio(null);
					}
					
					try {
						carga.setLngdomicilio(new BigDecimal(getCellValue(row, 14)));
					} catch (Exception e) {
						carga.setLngdomicilio(null);
					}
					
					carga.setFecentregaprog(getCellValue(row, 15));
					carga.setHorentregaprog(getCellValue(row, 16));
					carga.setDirentrega(getCellValue(row, 17));
					carga.setDistentrega(getCellValue(row, 18));
					carga.setProventrega(getCellValue(row, 19));
					carga.setDepentrega(getCellValue(row, 20));
					carga.setCourierasoc(getCellValue(row, 21));
					carga.setDnicourier(getCellValue(row, 22));
					
					try {
						portalWebDao.cargarEntregaTarjeta(carga); 
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
	public List<CargaEntregaTarjeta> lstCargarEntregaTarjeta(CargaEntregaTarjeta param){
		return portalWebDao.lstCargarEntregaTarjeta(param);
	}
	
	@Override
	public BigDecimal crearGrupoCarga(){
		return portalWebDao.crearGrupoCarga();
	}

}