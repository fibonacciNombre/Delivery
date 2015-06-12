package bbva.delivery.tarjetas.perfil.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class UsuarioWeb implements Serializable {

	/**
  *
  **/
	private static final long serialVersionUID = 1L;

	private String idptipodocumento;
	private String numerodoc;
	private Integer idetercero;
	private String fecnacimiento;
	private String idpgenero;
	
	private String idepuntocontactodir;
	private String idepuntocontactotelf;
	private String idepuntocontactomail;
	
	private Integer validaregusr;
	private String pregseg;
	private String respseg;
	private Integer numvisitas;
	private Integer numintentos;
	private String indbloqueo;
	private String idpstsusuario;
	private String password;
	private String newpassword;
	private String codexterno;
	private String fecnacimientosas;

	private String usuario;

	private List<UsuarioWeb> cursor;
	
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

	public String getIndbloqueo() {
		return indbloqueo;
	}

	public void setIndbloqueo(String indbloqueo) {
		this.indbloqueo = indbloqueo;
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

	public Integer getIdetercero() {
		return idetercero;
	}

	public void setIdetercero(Integer idetercero) {
		this.idetercero = idetercero;
	}

	public String getFecnacimiento() {
		return fecnacimiento;
	}

	public void setFecnacimiento(String fecnacimiento) {
		this.fecnacimiento = fecnacimiento;
	}

	public String getIdpgenero() {
		return idpgenero;
	}

	public void setIdpgenero(String idpgenero) {
		this.idpgenero = idpgenero;
	}

	public String getIdepuntocontactodir() {
		return idepuntocontactodir;
	}

	public void setIdepuntocontactodir(String idepuntocontactodir) {
		this.idepuntocontactodir = idepuntocontactodir;
	}

	public String getIdepuntocontactotelf() {
		return idepuntocontactotelf;
	}

	public void setIdepuntocontactotelf(String idepuntocontactotelf) {
		this.idepuntocontactotelf = idepuntocontactotelf;
	}

	public String getIdepuntocontactomail() {
		return idepuntocontactomail;
	}

	public void setIdepuntocontactomail(String idepuntocontactomail) {
		this.idepuntocontactomail = idepuntocontactomail;
	}

	public Integer getValidaregusr() {
		return validaregusr;
	}

	public void setValidaregusr(Integer validaregusr) {
		this.validaregusr = validaregusr;
	}

	public String getPregseg() {
		return pregseg;
	}

	public void setPregseg(String pregseg) {
		this.pregseg = pregseg;
	}

	public String getRespseg() {
		return respseg;
	}

	public void setRespseg(String respseg) {
		this.respseg = respseg;
	}

	public String getIdpstsusuario() {
		return idpstsusuario;
	}

	public void setIdpstsusuario(String string) {
		this.idpstsusuario = string;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodexterno() {
		return codexterno;
	}

	public void setCodexterno(String codexterno) {
		this.codexterno = codexterno;
	}

	public String getFecnacimientosas() {
		return fecnacimientosas;
	}

	public void setFecnacimientosas(String fecnacimientosas) {
		this.fecnacimientosas = fecnacimientosas;
	}

	public UsuarioWeb() {
	}

	@SuppressWarnings("rawtypes")
	public UsuarioWeb(Map map) throws Exception {
		ResourceManager.populateDtoFromMap(this, map);
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public List<UsuarioWeb> getCursor() {
		return cursor;
	}

	public void setCursor(List<UsuarioWeb> cursor) {
		this.cursor = cursor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
