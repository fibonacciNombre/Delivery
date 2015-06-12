package bbva.delivery.tarjetas.perfil.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class MntReposPswd implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idetercero;
  private String idptipodocumento;
  private String numerodoc;
  private String email;
  private String pregsec;
  private String respsec;
  private List<MntReposPswd> cursorrepos;

  public List<MntReposPswd> getCursorrepos() {
	return cursorrepos;
}

public void setCursorrepospswd(List<MntReposPswd> cursorrepos) {
	this.cursorrepos = cursorrepos;
}

public String getIdetercero() {
	return idetercero;
  }

  public void setIdetercero(String idetercero) {
	this.idetercero = idetercero;
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

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public String getPregsec() {
	return pregsec;
  }

  public void setPregsec(String pregsec) {
	this.pregsec = pregsec;
  }

  public String getRespsec() {
	return respsec;
  }

  public void setRespsec(String respsec) {
	this.respsec = respsec;
  }

  public MntReposPswd() {}
 
  @SuppressWarnings("rawtypes")
  public MntReposPswd(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

}