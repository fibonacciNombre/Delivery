package bbva.delivery.tarjetas.usuario.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.perfil.bean.Perfil;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idusuario;
	private Integer idpestado;
	private String codusuario;
	private String contrasena;
	private String estado;
	private String usuario; 
	private String comentario;
	private String historial;
	private String indrnvcontrasena;
	private Integer idtercero;

	private String idptipodocumento;
	private String nrodocumento;
	private String nombres; 
	private String apepaterno;
	private String apematerno;
	private String dscperfil;
	private String dscestado;
 
 	private Integer idcourier; 
	private Integer idperfil; 
	
	private Tercero tercero;
	private Perfil perfil;
	private Courier courier;
	
	@SuppressWarnings("rawtypes")
	public Usuario(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}
	
	public Usuario() {
	}
	
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	public String getIdptipodocumento() {
		return idptipodocumento;
	}
	public void setIdptipodocumento(String idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}
	public String getCodusuario() {
		return codusuario;
	}
	public void setCodusuario(String codusuario) {
		this.codusuario = codusuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	 
	public String getHistorial() {
		return historial;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	public Integer getIdtercero() {
		return idtercero;
	}
	public void setIdtercero(Integer idtercero) {
		this.idtercero = idtercero;
	}
	public Integer getIdperfil() {
		return idperfil;
	}
	public void setIdperfil(Integer idperfil) {
		this.idperfil = idperfil;
	}
	public Tercero getTercero() {
		return tercero;
	}
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Integer getIdpestado() {
		return idpestado;
	}
	public void setIdpestado(Integer idpestado) {
		this.idpestado = idpestado;
	}
	public Integer getIdcourier() {
		return idcourier;
	}
	public void setIdcourier(Integer idcourier) {
		this.idcourier = idcourier;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public String getIndrnvcontrasena() {
		return indrnvcontrasena;
	}
	public void setIndrnvcontrasena(String indrnvcontrasena) {
		this.indrnvcontrasena = indrnvcontrasena;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", idptipodocumento="
				+ idptipodocumento + ", idpestado=" + idpestado
				+ ", codusuario=" + codusuario + ", estado=" + estado
				+ ", usuario=" + usuario + ", comentario=" + comentario
				+ ", indrnvcontrasena=" + indrnvcontrasena + ", idtercero="
				+ idtercero + ", idperfil=" + idperfil + ", idcourier="
				+ idcourier + "]";
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

	public String getDscperfil() {
		return dscperfil;
	}

	public void setDscperfil(String dscperfil) {
		this.dscperfil = dscperfil;
	}

	public String getDscestado() {
		return dscestado;
	}

	public void setDscestado(String dscestado) {
		this.dscestado = dscestado;
	}	
	
}