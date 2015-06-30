package bbva.delivery.tarjetas.courier.bean; 

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Courier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idcourier;
	private String codbbva;
	private String rznsocial;
	private String correo;
	private String telffijo;
	private String telfmovil;
	private String observacion;
	private Integer idpestado;
	private String nrodocumentocou;
	private Integer idptipodocumento;
	private String direccion;
	private String usuario;  
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
	public Integer getIdcourier() {
		return idcourier;
	}
	public void setIdcourier(Integer idcourier) {
		this.idcourier = idcourier;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelffijo() {
		return telffijo;
	}
	public void setTelffijo(String telffijo) {
		this.telffijo = telffijo;
	}
	public String getTelfmovil() {
		return telfmovil;
	}
	public void setTelfmovil(String telfmovil) {
		this.telfmovil = telfmovil;
	}
	 
	 
	public String getHistorial() {
		return historial;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	public Integer getIdpestado() {
		return idpestado;
	}
	public void setIdpestado(Integer idpestado) {
		this.idpestado = idpestado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getNrodocumentocou() {
		return nrodocumentocou;
	}
	public void setNrodocumentocou(String nrodocumentocou) {
		this.nrodocumentocou = nrodocumentocou;
	}
	public Integer getIdptipodocumento() {
		return idptipodocumento;
	}
	public void setIdptipodocumento(Integer idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Courier [idcourier=" + idcourier + ", codbbva=" + codbbva
				+ ", rznsocial=" + rznsocial + ", correo=" + correo
				+ ", telffijo=" + telffijo + ", telfmovil=" + telfmovil
				+ ", observacion=" + observacion + ", idpestado=" + idpestado
				+ ", nrodocumentocou=" + nrodocumentocou
				+ ", idptipodocumento=" + idptipodocumento + ", direccion="
				+ direccion + "]";
	}
		
}
