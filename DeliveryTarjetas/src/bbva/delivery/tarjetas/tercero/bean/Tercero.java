package bbva.delivery.tarjetas.tercero.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Tercero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal idtercero;
	private Integer idpestado;
	private Integer idptipodocumento;
	private String nrodocumento;
	private String nombres;
	private String apepaterno;
	private String apematerno;
	private String nomcompleto;
	private String telfmovil;
	private String correo;
	private String historial;
 	private Integer idcourier; 
	
 	private String dsccourier;
 	private String dscestado;
 	
 	private String usuario; 
	public Tercero() {
	
	}	
	
	@SuppressWarnings("rawtypes")
	public Tercero(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}	
	
	public BigDecimal getIdtercero() {
		return idtercero;
	}
	public void setIdtercero(BigDecimal idtercero) {
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
 
	public Integer getIdpestado() {
		return idpestado;
	}
	public void setIdpestado(Integer idpestado) {
		this.idpestado = idpestado;
	}
	public Integer getIdptipodocumento() {
		return idptipodocumento;
	}
	public void setIdptipodocumento(Integer idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}
	public String getNomcompleto() {
		return nomcompleto;
	}
	public void setNomcompleto(String nomcompleto) {
		this.nomcompleto = nomcompleto;
	}
	public String getTelfmovil() {
		return telfmovil;
	}
	public void setTelfmovil(String telfmovil) {
		this.telfmovil = telfmovil;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}

 

	@Override
	public String toString() {
		return "Tercero [idtercero=" + idtercero + ", idpestado=" + idpestado
				+ ", idptipodocumento=" + idptipodocumento + ", nrodocumento="
				+ nrodocumento + ", nombres=" + nombres + ", apepaterno="
				+ apepaterno + ", apematerno=" + apematerno + ", nomcompleto="
				+ nomcompleto + ", telfmovil=" + telfmovil + ", correo="
				+ correo + ", idcourier="
				+ idcourier  + "]";
	}

	public String getDsccourier() {
		return dsccourier;
	}

	public void setDsccourier(String dsccourier) {
		this.dsccourier = dsccourier;
	}

	public String getDscestado() {
		return dscestado;
	}

	public void setDscestado(String dscestado) {
		this.dscestado = dscestado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}