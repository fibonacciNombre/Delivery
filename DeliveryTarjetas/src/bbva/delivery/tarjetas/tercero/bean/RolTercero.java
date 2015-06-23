package bbva.delivery.tarjetas.tercero.bean;

import java.io.Serializable;

public class RolTercero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idroltercero;
	private String descripcion;
	private String estado;
	private String usucreacion;
	private String usumodificacion;
	private String feccreacion;
	private String fecmodifcacion;
	
	public int getIdroltercero() {
		return idroltercero;
	}
	public void setIdroltercero(int idroltercero) {
		this.idroltercero = idroltercero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsucreacion() {
		return usucreacion;
	}
	public void setUsucreacion(String usucreacion) {
		this.usucreacion = usucreacion;
	}
	public String getUsumodificacion() {
		return usumodificacion;
	}
	public void setUsumodificacion(String usumodificacion) {
		this.usumodificacion = usumodificacion;
	}
	public String getFeccreacion() {
		return feccreacion;
	}
	public void setFeccreacion(String feccreacion) {
		this.feccreacion = feccreacion;
	}
	public String getFecmodifcacion() {
		return fecmodifcacion;
	}
	public void setFecmodifcacion(String fecmodifcacion) {
		this.fecmodifcacion = fecmodifcacion;
	}
	
}