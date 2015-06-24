package bbva.delivery.tarjetas.bean;

import java.util.Date;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Archivo {

	private Integer idarchivo;
	private Date feccarga;
	private Integer idtipoarchivo;
	private Date fecentrega;
	private Integer idcourier;
	
	public Archivo(){ 
	}
	 
	@SuppressWarnings("rawtypes")
	public Archivo(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}

	public Integer getIdarchivo() {
		return idarchivo;
	}

	public void setIdarchivo(Integer idarchivo) {
		this.idarchivo = idarchivo;
	}

	public Date getFeccarga() {
		return feccarga;
	}

	public void setFeccarga(Date feccarga) {
		this.feccarga = feccarga;
	}

	public Integer getIdtipoarchivo() {
		return idtipoarchivo;
	}

	public void setIdtipoarchivo(Integer idtipoarchivo) {
		this.idtipoarchivo = idtipoarchivo;
	}

	public Date getFecentrega() {
		return fecentrega;
	}

	public void setFecentrega(Date fecentrega) {
		this.fecentrega = fecentrega;
	}

	public Integer getIdcourier() {
		return idcourier;
	}

	public void setIdcourier(Integer idcourier) {
		this.idcourier = idcourier;
	}
	
	
}
