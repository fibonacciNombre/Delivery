package bbva.delivery.tarjetas.bean;

import java.math.BigDecimal;
import java.util.Map;

import commons.mapper.ResourceManager;

@SuppressWarnings("rawtypes")
public class Courier {
	private BigDecimal idcourier;
	private String codigo;
	private String nombre;
	private String telffijo;
	private String telfmovil;
	private String observacion;
	private String nrodocumentocou;
	private Integer idptipodocumento;

	public Courier() {

	} 
	
	public Courier(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}

	public BigDecimal getIdcourier() {
		return idcourier;
	}

	public void setIdcourier(BigDecimal idcourier) {
		this.idcourier = idcourier;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelffijo() {
		return telffijo;
	}

	public void setTelffijo(String telffijo) {
		this.telffijo = telffijo;
	}

	public String getTelfmovil() {
		return telfmovil;
	}

	public void setTelfmovil(String telfmovil) {
		this.telfmovil = telfmovil;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getNrodocumentocou() {
		return nrodocumentocou;
	}

	public void setNrodocumentocou(String nrodocumentocou) {
		this.nrodocumentocou = nrodocumentocou;
	}

	public Integer getIdptipodocumento() {
		return idptipodocumento;
	}

	public void setIdptipodocumento(Integer idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}

}
