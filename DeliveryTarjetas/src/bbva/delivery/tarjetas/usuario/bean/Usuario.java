package bbva.delivery.tarjetas.usuario.bean;

import java.util.Map;

import commons.mapper.ResourceManager;

public class Usuario {

	private Integer idusuario;
	private String codusuario;
	private String contrasena;
	private Integer idtercero;
	private Integer idperfil;
	private Integer idpestado; 
	
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public Integer getIdpestado() {
		return idpestado;
	}

	public void setIdpestado(Integer idpestado) {
		this.idpestado = idpestado;
	}
	
}
