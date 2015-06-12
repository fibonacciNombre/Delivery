package bbva.delivery.tarjetas.perfil.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class UsuarioLDAP implements Serializable {

	  /**
	  *
	  **/
	private static final long serialVersionUID = 1L;
	  
	private String cn;
	private String givenName;
	private String documentNumber;
	private String uid;
	private String sn;
	private String securQues;
	private String securAnsw;
	private String mail;
	private boolean valido;
	
	public UsuarioLDAP() {
	}
	
  @SuppressWarnings("rawtypes")
public UsuarioLDAP(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	  }
	
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public boolean isValido() {
		return valido;
	}
	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	public String getSecurQues() {
		return securQues;
	}

	public void setSecurQues(String securQues) {
		this.securQues = securQues;
	}
	
	public String getSecurAnsw() {
		return securAnsw;
	}

	public void setSecurAnsw(String securAnsw) {
		this.securAnsw = securAnsw;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}		
}
