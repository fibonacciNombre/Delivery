package bbva.delivery.tarjetas.perfil.bean; 

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idperfil;
	private String descripcion;

	private Integer idpestado;
	private String usucreacion;
	private String feccreacion;
	private String usumodificacion;
	private String fecmodificacion;
	private String historial;
	
	@SuppressWarnings("rawtypes")
	public Perfil(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}	
	public Perfil(){
	}
	public Integer getIdperfil() {
		return idperfil;
	}
	public void setIdperfil(Integer idperfil) {
		this.idperfil = idperfil;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getUsumodificacion() {
		return usumodificacion;
	}
	public void setUsumodificacion(String usumodificacion) {
		this.usumodificacion = usumodificacion;
	}
	public String getFecmodificacion() {
		return fecmodificacion;
	}
	public void setFecmodificacion(String fecmodificacion) {
		this.fecmodificacion = fecmodificacion;
	}
	public String getHistorial() {
		return historial;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	@Override
	public String toString() {
		return "Perfil [idperfil=" + idperfil + 
						", descripcion=" + descripcion + "]";
	}
	public Integer getIdpestado() {
		return idpestado;
	}
	public void setIdpestado(Integer idpestado) {
		this.idpestado = idpestado;
	}
	
	
		
}