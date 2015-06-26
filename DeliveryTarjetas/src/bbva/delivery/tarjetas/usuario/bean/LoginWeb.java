package bbva.delivery.tarjetas.usuario.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class LoginWeb implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userlogin;
	private String passlogin;
	private String mensaje;
	private String escenario;
	private String urldestino;
	private String useragent;

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

	public String getUrldestino() {
		return urldestino;
	}

	public void setUrldestino(String urldestino) {
		this.urldestino = urldestino;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String usragent) {
		this.useragent = usragent;
	}

}