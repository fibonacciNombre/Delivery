package bbva.delivery.tarjetas.courier.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Courier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idcourier;
	private String codbbva;
	private String rznsocial;
	private String nombre;
	private String correo;
	private String tlffijo;
	private String tlfmovil;
	private String observaciones;
	private String estado;
	private String usucreacion;
	private String usumodificacion;
	private String feccreacion;
	private String fecmodifcacion;
	private String historial;
	
	@SuppressWarnings("rawtypes")
	public Courier(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}	
	public Courier(){
	}
	public String getCodbbva() {
		return codbbva;
	}
	public void setCodbbva(String codbbva) {
		this.codbbva = codbbva;
	}
	public String getRznsocial() {
		return rznsocial;
	}
	public void setRznsocial(String rznsocial) {
		this.rznsocial = rznsocial;
	}
	public int getIdcourier() {
		return idcourier;
	}
	public void setIdcourier(int idcourier) {
		this.idcourier = idcourier;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTlffijo() {
		return tlffijo;
	}
	public void setTlffijo(String tlffijo) {
		this.tlffijo = tlffijo;
	}
	public String getTlfmovil() {
		return tlfmovil;
	}
	public void setTlfmovil(String tlfmovil) {
		this.tlfmovil = tlfmovil;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	public String getHistorial() {
		return historial;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
		
}