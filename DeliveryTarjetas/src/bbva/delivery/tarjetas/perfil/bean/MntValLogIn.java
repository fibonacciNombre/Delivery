package bbva.delivery.tarjetas.perfil.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class MntValLogIn implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idptipodocumento;
  private String numerodoc;
  private Integer idetercero;
  private Integer numvisitas;
  private Integer numintentos;
  private String stsbloq;
  private String idepuntocontacto;
  public String getIdepuntocontacto() {
	return idepuntocontacto;
}

public void setIdepuntocontacto(String idepuntocontacto) {
	this.idepuntocontacto = idepuntocontacto;
}

public String getIdemediocontactotelf() {
	return idemediocontactotelf;
}

public void setIdemediocontactotelf(String idemediocontactotelf) {
	this.idemediocontactotelf = idemediocontactotelf;
}

public String getIdemediocontactomail() {
	return idemediocontactomail;
}

public void setIdemediocontactomail(String idemediocontactomail) {
	this.idemediocontactomail = idemediocontactomail;
}

private String idemediocontactotelf;
  private String idemediocontactomail;
  private List<MntValLogIn> cursorValLogIn;

  public List<MntValLogIn> getCursorValLogIn() {
	return cursorValLogIn;
}

public void setCursorValLogIn(List<MntValLogIn> cursorValLogIn) {
	this.cursorValLogIn = cursorValLogIn;
}

public Integer getIdetercero() {
	return idetercero;
}

public void setIdetercero(Integer idetercero) {
	this.idetercero = idetercero;
}

public Integer getNumvisitas() {
	return numvisitas;
}

public void setNumvisitas(Integer numvisitas) {
	this.numvisitas = numvisitas;
}

public Integer getNumintentos() {
	return numintentos;
}

public void setNumintentos(Integer numintentos) {
	this.numintentos = numintentos;
}

public String getStsbloq() {
	return stsbloq;
}

public void setStsbloq(String stsbloq) {
	this.stsbloq = stsbloq;
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

  public MntValLogIn() {}
 
  @SuppressWarnings("rawtypes")
  public MntValLogIn(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

}