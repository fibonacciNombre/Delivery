package bbva.delivery.tarjetas.bean;

import java.util.Date;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Archivo {

	private Integer idarchivo;
	private Date feccarga;
	private Integer idtipoarchivo;
	private String fecentrega;
	private Integer idcourier;
	
	private String codcourier;
	private String tipoarchivo;
	private String filename;
	
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

	public String getFecentrega() {
		return fecentrega;
	}

	public void setFecentrega(String fecentrega) {
		this.fecentrega = fecentrega;
	}

	public Integer getIdcourier() {
		return idcourier;
	}

	public void setIdcourier(Integer idcourier) {
		this.idcourier = idcourier;
	}

	public String getCodcourier() {
		return codcourier;
	}

	public void setCodcourier(String codcourier) {
		this.codcourier = codcourier;
	}

	public String getTipoarchivo() {
		return tipoarchivo;
	}

	public void setTipoarchivo(String tipoarchivo) {
		this.tipoarchivo = tipoarchivo;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
