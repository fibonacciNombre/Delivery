package bbva.delivery.tarjetas.tercero.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

import bbva.delivery.tarjetas.courier.bean.Courier;

public class Tercero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idtercero;
	private String nrodocumento;
	private String nombres;
	private String apepaterno;
	private String apematerno;
	 
	private String idpestado;
	private String usuario; 
 	private String historial;
 	private Integer idcourier; 
	private Integer idptipodocumento;
 
	
	
	public Tercero() {
	
	}	
	
	@SuppressWarnings("rawtypes")
	public Tercero(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}	
	
	public Integer getIdtercero() {
		return idtercero;
	}
	public void setIdtercero(Integer idtercero) {
		this.idtercero = idtercero;
	}
	public String getNrodocumento() {
		return nrodocumento;
	}
	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApepaterno() {
		return apepaterno;
	}
	public void setApepaterno(String apepaterno) {
		this.apepaterno = apepaterno;
	}
	public String getApematerno() {
		return apematerno;
	}
	public void setApematerno(String apematerno) {
		this.apematerno = apematerno;
	}
	 
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getHistorial() {
		return historial;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	 
	public Integer getIdcourier() {
		return idcourier;
	}
	public void setIdcourier(Integer idcourier) {
		this.idcourier = idcourier;
	}
 
	public String getIdpestado() {
		return idpestado;
	}
	public void setIdpestado(String idpestado) {
		this.idpestado = idpestado;
	}
	public Integer getIdptipodocumento() {
		return idptipodocumento;
	}
	public void setIdptipodocumento(Integer idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}

	@Override
	public String toString() {
		return "Tercero [idtercero=" + idtercero + ", nrodocumento="
				+ nrodocumento + ", nombres=" + nombres + ", apepaterno="
				+ apepaterno + ", apematerno=" + apematerno + ", idpestado="
				+ idpestado + ", usuario=" + usuario + ", historial="
				+ historial + ", idcourier=" + idcourier
				+ ", idptipodocumento=" + idptipodocumento + "]";
	}
	 
	
	 
}