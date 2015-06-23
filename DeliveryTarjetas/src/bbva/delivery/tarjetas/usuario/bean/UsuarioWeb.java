package bbva.delivery.tarjetas.usuario.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

import bbva.delivery.tarjetas.tercero.bean.Tercero;

public class UsuarioWeb implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idusuario;
	private String idptipodocumento;
	private String numerodoc;
	private String password;
	private String estado;
	private String usucreacion;
	private String usumodificacion;
	private String feccreacion;
	private String fecmodifcacion;
	private String historial;
	private Integer idtercero;
	private Integer idperfil;
	
	private Tercero tercero;
	private Perfil perfil;
	
	@SuppressWarnings("rawtypes")
	public UsuarioWeb(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}
	
	public UsuarioWeb() {
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
	public String getNumerodoc() {
		return numerodoc;
	}
	public void setNumerodoc(String numerodoc) {
		this.numerodoc = numerodoc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	
}