package bbva.delivery.tarjetas.comun.bean;

import java.util.Map;

import commons.mapper.ResourceManager;

public class ParametroTipo {
	private String idparametrotipo;
	private String descripcion;
	private String indactivo;
	private String usucreacion;
	private String feccreacion;
	private String usumodif;
	private String fecmodif;
	private String tipoparametro;
	
	public ParametroTipo(){ 
	}
	 
	@SuppressWarnings("rawtypes")
	public ParametroTipo(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}


	public String getIdparametrotipo() {
		return idparametrotipo;
	}


	public void setIdparametrotipo(String idparametrotipo) {
		this.idparametrotipo = idparametrotipo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getIndactivo() {
		return indactivo;
	}


	public void setIndactivo(String indactivo) {
		this.indactivo = indactivo;
	}


	public String getUsucreacion() {
		return usucreacion;
	}


	public void setUsucreacion(String usucreacion) {
		this.usucreacion = usucreacion;
	}


	public String getFeccreacion() {
		return feccreacion;
	}


	public void setFeccreacion(String feccreacion) {
		this.feccreacion = feccreacion;
	}


	public String getUsumodif() {
		return usumodif;
	}


	public void setUsumodif(String usumodif) {
		this.usumodif = usumodif;
	}


	public String getFecmodif() {
		return fecmodif;
	}


	public void setFecmodif(String fecmodif) {
		this.fecmodif = fecmodif;
	}


	public String getTipoparametro() {
		return tipoparametro;
	}


	public void setTipoparametro(String tipoparametro) {
		this.tipoparametro = tipoparametro;
	}
	
	
}
