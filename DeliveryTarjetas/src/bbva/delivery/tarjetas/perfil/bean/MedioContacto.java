package bbva.delivery.tarjetas.perfil.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import bbva.delivery.tarjetas.commons.Constants;
import commons.mapper.ResourceManager;

public class MedioContacto implements Serializable {

	/**
  *
  **/
	private static final long serialVersionUID = 1L;
	private Integer idepuntocontacto;
	private String codigotelefonopais;
	private String codigoarea;
	private Integer idptipotelefono;
	private String numlocal;
	private String anexo;
	private String indmovil;
	private String usucreacion = Constants.USUARIO_PWEB;
	private String usumodif;
	private String direccionweb;
	private String nodo;
	private String dominio;
	private String userid;
	private String idptipdireelec;
	private String email;
	private String idptipmediocontac;
	private Integer idetercero;
	private String stspuntocontacto;
	private Integer idelugar;
	private String indexrow;
	private String cadidpusopuntocontacto;
	private List<MedioContacto> cursor;

	public MedioContacto() {
	}

	@SuppressWarnings("rawtypes")
	public MedioContacto(Map map) throws Exception {
		ResourceManager.populateDtoFromMap(this, map);
	}

	public String getCodigotelefonopais() {
		return codigotelefonopais;
	}

	public void setCodigotelefonopais(String codigotelefonopais) {
		this.codigotelefonopais = codigotelefonopais;
	}

	public String getCodigoarea() {
		return codigoarea;
	}

	public void setCodigoarea(String codigoarea) {
		this.codigoarea = codigoarea;
	}

	public Integer getIdptipotelefono() {
		return idptipotelefono;
	}

	public void setIdptipotelefono(Integer idptipotelefono) {
		this.idptipotelefono = idptipotelefono;
	}

	public String getNumlocal() {
		return numlocal;
	}

	public void setNumlocal(String numlocal) {
		this.numlocal = numlocal;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getIndmovil() {
		return indmovil;
	}

	public void setIndmovil(String indmovil) {
		this.indmovil = indmovil;
	}

	public String getUsucreacion() {
		return usucreacion;
	}

	public void setUsucreacion(String usucreacion) {
		this.usucreacion = usucreacion;
	}

	public String getUsumodif() {
		return usumodif;
	}

	public void setUsumodif(String usumodif) {
		this.usumodif = usumodif;
	}

	public String getDireccionweb() {
		return direccionweb;
	}

	public void setDireccionweb(String direccionweb) {
		this.direccionweb = direccionweb;
	}

	public String getNodo() {
		return nodo;
	}

	public void setNodo(String nodo) {
		this.nodo = nodo;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIdptipdireelec() {
		return idptipdireelec;
	}

	public void setIdptipdireelec(String idptipdireelec) {
		this.idptipdireelec = idptipdireelec;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdptipmediocontac() {
		return idptipmediocontac;
	}

	public void setIdptipmediocontac(String idptipmediocontac) {
		this.idptipmediocontac = idptipmediocontac;
	}

	public Integer getIdetercero() {
		return idetercero;
	}

	public void setIdetercero(Integer idetercero) {
		this.idetercero = idetercero;
	}

	public String getIndexrow() {
		return indexrow;
	}

	public void setIndexrow(String indexrow) {
		this.indexrow = indexrow;
	}

	public Integer getIdepuntocontacto() {
		return idepuntocontacto;
	}

	public void setIdepuntocontacto(Integer idepuntocontacto) {
		this.idepuntocontacto = idepuntocontacto;
	}

	public String getStspuntocontacto() {
		return stspuntocontacto;
	}

	public void setStspuntocontacto(String stspuntocontacto) {
		this.stspuntocontacto = stspuntocontacto;
	}

	public Integer getIdelugar() {
		return idelugar;
	}

	public void setIdelugar(Integer idelugar) {
		this.idelugar = idelugar;
	}

	public String getCadidpusopuntocontacto() {
		return cadidpusopuntocontacto;
	}

	public void setCadidpusopuntocontacto(String cadidpusopuntocontacto) {
		this.cadidpusopuntocontacto = cadidpusopuntocontacto;
	}

	public List<MedioContacto> getCursor() {
		return cursor;
	}

	public void setCursor(List<MedioContacto> cursor) {
		this.cursor = cursor;
	}

}
