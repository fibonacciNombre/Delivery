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
	private String telffijo;
	private String telfmovil;
	private String observacion;
	private String idpestado;
	private String nrodocumentocou;
	private String idptipodocumento;
	private String direccion;
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
	public String getTelffijo() {
		return telffijo;
	}
	public void setTlffijo(String telffijo) {
		this.telffijo = telffijo;
	}
	public String getTelfmovil() {
		return telfmovil;
	}
	public void setTelfmovil(String telfmovil) {
		this.telfmovil = telfmovil;
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
	public String getIdpestado() {
		return idpestado;
	}
	public void setIdpestado(String idpestado) {
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
	public String getIdptipodocumento() {
		return idptipodocumento;
	}
	public void setIdptipodocumento(String idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
		
}