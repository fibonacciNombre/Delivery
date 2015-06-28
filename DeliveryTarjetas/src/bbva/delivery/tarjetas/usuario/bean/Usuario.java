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
	private String comentario;
	private String historial;
	private String indrnvcontrasena;
	private String idptipodocumento;
	private String nrodocumento;	
	private String nombrecompleto;
	private Integer indaccion;
	private String usucreacion;
	private String usumodificacion;
	/*
	private String apepaterno;
	private String apematerno;
	private String telfmovil;
	private String correo;
	private String dscperfil;
	private String dscestado;
 	*/
	private Integer idtercero;
	private Integer idcourier; 
	private Integer idperfil; 	
	private Integer noidperfil;
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
	public String getCodusuario() {
		return codusuario;
	}
	public void setCodusuario(String codusuario) {
		this.codusuario = codusuario;
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
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Integer getNoidperfil() {
		return noidperfil;
	}
	public void setNoidperfil(Integer noidperfil) {
		this.noidperfil = noidperfil;
	}

	public String getIdptipodocumento() {
		return idptipodocumento;
	}

	public void setIdptipodocumento(String idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}

	public String getNrodocumento() {
		return nrodocumento;
	}

	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}

	public String getNombrecompleto() {
		return nombrecompleto;
	}

	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
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
	public Integer getIndaccion() {
		return indaccion;
	}
	public void setIndaccion(Integer indaccion) {
		this.indaccion = indaccion;
	}

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", idpestado=" + idpestado
				+ ", codusuario=" + codusuario + ", contrasena=" + contrasena
				+ ", comentario=" + comentario + ", historial=" + historial
				+ ", indrnvcontrasena=" + indrnvcontrasena
				+ ", idptipodocumento=" + idptipodocumento + ", nrodocumento="
				+ nrodocumento + ", nombrecompleto=" + nombrecompleto
				+ ", usucreacion=" + usucreacion + ", usumodificacion="
				+ usumodificacion + ", idtercero=" + idtercero + ", idcourier="
				+ idcourier + ", idperfil=" + idperfil + ", noidperfil="
				+ noidperfil + ", tercero=" + tercero + ", perfil=" + perfil
				+ ", courier=" + courier + "]";
	}	
	
}
