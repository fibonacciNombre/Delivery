package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONObject;

import bbva.delivery.tarjetas.bean.CargaEntregaTarjeta;

public interface DeliveryService {

	public void test();

	public JSONObject cargaEntregaTarjeta(String fileName) throws FileNotFoundException;
	public List<CargaEntregaTarjeta> lstCargarEntregaTarjeta(CargaEntregaTarjeta param);
	public BigDecimal crearGrupoCarga();
}