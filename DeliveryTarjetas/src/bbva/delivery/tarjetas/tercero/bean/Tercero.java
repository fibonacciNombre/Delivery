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
	private String idpestado;
	private String usuario; 
 	private String historial;
 	private Integer idcourier;
 	private Integer idarchivo;
	private Integer idroltercero;
	private Integer idptipodocumento;

 	private Integer idtercerorel;
	
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
	public Integer getIdtercerorel() {
		return idtercerorel;
	}
	public void setIdtercerorel(Integer idtercerorel) {
		this.idtercerorel = idtercerorel;
	}
	public Integer getIdarchivo() {
		return idarchivo;
	}
	public void setIdarchivo(Integer idarchivo) {
		this.idarchivo = idarchivo;
	}
	 
}