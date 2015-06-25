package bbva.delivery.tarjetas.comun.bean;

import java.util.Map;

import commons.mapper.ResourceManager;

public class TipoArchivo {
	private Integer idtipoarchivo;
	private String abreviatura;
	private String descripcion;

	public TipoArchivo() {

	}

	@SuppressWarnings("rawtypes")
	public TipoArchivo(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}

	public Integer getIdtipoarchivo() {
		return idtipoarchivo;
	}

	public void setIdtipoarchivo(Integer idtipoarchivo) {
		this.idtipoarchivo = idtipoarchivo;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
