package bbva.delivery.tarjetas.perfil.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import commons.mapper.ResourceManager;

public class PuntoContacto implements Serializable {

	/**
  *
  **/
	private static final long serialVersionUID = 1L;
	private Integer idepuntocontacto;
	private Integer idepais;
	private Integer idedepartamento;
	private Integer ideprovincia;
	private Integer idedistrito;
	private String idptipovia;
	private Integer idevia;
	private String numcasa;
	private Integer idptipointerior;
	private String interior;
	private String manzana;
	private String lote;
	private String kilometro;
	private Integer idptipoagrupacion;
	private String dscagrupgeo;
	private Integer idpsubdivision;
	private String dscsubdivision;
	private String referencia;
	private String usucreacion;
	private Integer idetercero;
	private String nomvia;
	private Integer idelugar;
	private String stspuntocontacto;
	private String indprincipal;
	private String coordenadax;
	private String coordenaday;
	private String indexrow;
	private String cadidpusopuntocontacto;
	private String direccioncompleta;
	private List<PuntoContacto> cursor;

	public PuntoContacto() {
	}

	@SuppressWarnings("rawtypes")
	public PuntoContacto(Map map) throws Exception {
		ResourceManager.populateDtoFromMap(this, map);
	}

	public Integer getIdepais() {
		return idepais;
	}

	public void setIdepais(Integer idepais) {
		this.idepais = idepais;
	}

	public Integer getIdedepartamento() {
		return idedepartamento;
	}

	public void setIdedepartamento(Integer idedepartamento) {
		this.idedepartamento = idedepartamento;
	}

	public Integer getIdeprovincia() {
		return ideprovincia;
	}

	public void setIdeprovincia(Integer ideprovincia) {
		this.ideprovincia = ideprovincia;
	}

	public Integer getIdedistrito() {
		return idedistrito;
	}

	public void setIdedistrito(Integer idedistrito) {
		this.idedistrito = idedistrito;
	}

	public String getIdptipovia() {
		return idptipovia;
	}

	public void setIdptipovia(String idptipovia) {
		this.idptipovia = idptipovia;
	}

	public Integer getIdevia() {
		return idevia;
	}

	public void setIdevia(Integer idevia) {
		this.idevia = idevia;
	}

	public String getNumcasa() {
		return numcasa;
	}

	public void setNumcasa(String numcasa) {
		this.numcasa = numcasa;
	}

	public Integer getIdptipointerior() {
		return idptipointerior;
	}

	public void setIdptipointerior(Integer idptipointerior) {
		this.idptipointerior = idptipointerior;
	}

	public String getInterior() {
		return interior;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public String getManzana() {
		return manzana;
	}

	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getKilometro() {
		return kilometro;
	}

	public void setKilometro(String kilometro) {
		this.kilometro = kilometro;
	}

	public Integer getIdptipoagrupacion() {
		return idptipoagrupacion;
	}

	public void setIdptipoagrupacion(Integer idptipoagrupacion) {
		this.idptipoagrupacion = idptipoagrupacion;
	}

	public String getDscagrupgeo() {
		return dscagrupgeo;
	}

	public void setDscagrupgeo(String dscagrupgeo) {
		this.dscagrupgeo = dscagrupgeo;
	}

	public Integer getIdpsubdivision() {
		return idpsubdivision;
	}

	public void setIdpsubdivision(Integer idpsubdivision) {
		this.idpsubdivision = idpsubdivision;
	}

	public String getDscsubdivision() {
		return dscsubdivision;
	}

	public void setDscsubdivision(String dscsubdivision) {
		this.dscsubdivision = dscsubdivision;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getUsucreacion() {
		return usucreacion;
	}

	public void setUsucreacion(String usucreacion) {
		this.usucreacion = usucreacion;
	}

	public Integer getIdetercero() {
		return idetercero;
	}

	public void setIdetercero(Integer idetercero) {
		this.idetercero = idetercero;
	}

	public String getNomvia() {
		return nomvia;
	}

	public void setNomvia(String nomvia) {
		this.nomvia = nomvia;
	}

	public String getIndexrow() {
		return indexrow;
	}

	public void setIndexrow(String indexrow) {
		this.indexrow = indexrow;
	}

	public Integer getIdelugar() {
		return idelugar;
	}

	public void setIdelugar(Integer idelugar) {
		this.idelugar = idelugar;
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

	public String getIndprincipal() {
		return indprincipal;
	}

	public void setIndprincipal(String indprincipal) {
		this.indprincipal = indprincipal;
	}

	public String getCoordenadax() {
		return coordenadax;
	}

	public void setCoordenadax(String coordenadax) {
		this.coordenadax = coordenadax;
	}

	public String getCoordenaday() {
		return coordenaday;
	}

	public void setCoordenaday(String coordenaday) {
		this.coordenaday = coordenaday;
	}

	public String getDireccioncompleta() {
		return direccioncompleta;
	}

	public void setDireccioncompleta(String direccioncompleta) {
		this.direccioncompleta = direccioncompleta;
	}

	public String getCadidpusopuntocontacto() {
		return cadidpusopuntocontacto;
	}

	public void setCadidpusopuntocontacto(String cadidpusopuntocontacto) {
		this.cadidpusopuntocontacto = cadidpusopuntocontacto;
	}

	public List<PuntoContacto> getCursor() {
		return cursor;
	}

	public void setCursor(List<PuntoContacto> cursor) {
		this.cursor = cursor;
	}
	
	
}
