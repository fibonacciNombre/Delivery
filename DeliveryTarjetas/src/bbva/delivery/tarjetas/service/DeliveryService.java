package bbva.delivery.tarjetas.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONObject;

import bbva.delivery.tarjetas.bean.CargaEntregaTarjetav1;

public interface DeliveryService {

	public void test();

	public JSONObject cargaEntregaTarjeta(String fileName) throws FileNotFoundException;
	public List<CargaEntregaTarjetav1> lstCargarEntregaTarjeta(CargaEntregaTarjetav1 param);
	public BigDecimal crearGrupoCarga();
}