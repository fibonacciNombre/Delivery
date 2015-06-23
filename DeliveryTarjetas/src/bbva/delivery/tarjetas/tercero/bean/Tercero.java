package bbva.delivery.tarjetas.tercero.bean;

import java.io.Serializable;

import bbva.delivery.tarjetas.courier.bean.Courier;

public class Tercero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idtercero;
	private String nrodocumento;
	private String nombres;
	private String apepaterno;
	private String apematerno;
	private String rznsocial;
	private String estado;
	private String usucreacion;
	private String usumodificacion;
	private String feccreacion;
	private String fecmodifcacion;
	private String historial;
	private Integer idcourier;
	private Integer idroltercero;
	private Integer idtipodocumento;
	
	private Courier courier;
	private TipoDocumento tipdocumento;
	private RolTercero roltercero;
	
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
	public String getRznsocial() {
		return rznsocial;
	}
	public void setRznsocial(String rznsocial) {
		this.rznsocial = rznsocial;
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
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public TipoDocumento getTipdocumento() {
		return tipdocumento;
	}
	public void setTipdocumento(TipoDocumento tipdocumento) {
		this.tipdocumento = tipdocumento;
	}
	public RolTercero getRoltercero() {
		return roltercero;
	}
	public void setRoltercero(RolTercero roltercero) {
		this.roltercero = roltercero;
	}
	public Integer getIdcourier() {
		return idcourier;
	}
	public void setIdcourier(Integer idcourier) {
		this.idcourier = idcourier;
	}
	public Integer getIdroltercero() {
		return idroltercero;
	}
	public void setIdroltercero(Integer idroltercero) {
		this.idroltercero = idroltercero;
	}
	public Integer getIdtipodocumento() {
		return idtipodocumento;
	}
	public void setIdtipodocumento(Integer idtipodocumento) {
		this.idtipodocumento = idtipodocumento;
	}
	
}