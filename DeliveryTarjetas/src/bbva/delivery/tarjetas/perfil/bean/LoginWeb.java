package bbva.delivery.tarjetas.perfil.bean;

import java.io.Serializable;
import java.util.Map;

import bbva.delivery.tarjetas.commons.Constants;
import commons.mapper.ResourceManager;

public class LoginWeb implements Serializable {

	/**
  *
  **/
	private static final long serialVersionUID = 1L;

	private String userlogin;
	private String passlogin;
	private Integer mobile;
	private String escenario;
	private String mensaje;
	private String token;
	private Integer nrointento;
	private String urldestino;
	private String useragent;
	private String indlogin = Constants.ACCION_WEB_ACCESOS_INCORRECTOS;

	@SuppressWarnings("rawtypes")
	public LoginWeb(Map map) throws Exception {
		ResourceManager.populateDtoFromMap(this, map);
	}

	public String getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public String getPasslogin() {
		return passlogin;
	}

	public void setPasslogin(String passlogin) {
		this.passlogin = passlogin;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getEscenario() {
		return escenario;
	}

	public void setEscenario(String escenario) {
		this.escenario = escenario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getNrointento() {
		return nrointento;
	}

	public void setNrointento(Integer nrointento) {
		this.nrointento = nrointento;
	}

	public String getUrldestino() {
		return urldestino;
	}

	public void setUrldestino(String urldestino) {
		this.urldestino = urldestino;
	}

	public String getIndlogin() {
		return indlogin;
	}

	public void setIndlogin(String indlogin) {
		this.indlogin = indlogin;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String usragent) {
		this.useragent = usragent;
	}
}
