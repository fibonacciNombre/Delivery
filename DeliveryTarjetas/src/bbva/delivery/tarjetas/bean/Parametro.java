package bbva.delivery.tarjetas.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Parametro {
	private BigDecimal idparametro;
	private String idparametrotipo;
	private Integer codigon;
	private String codigoc;
	private String abreviatura;
	private String descripcion;
	private String indactivo;
	private String usucreacion;
	private Date feccreacion;
	private String usumodif;
	private Date fecmodif;
	
	public Parametro(){
		
	}
	
	@SuppressWarnings("rawtypes")
	public Parametro(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}

	public BigDecimal getIdparametro() {
		return idparametro;
	}

	public void setIdparametro(BigDecimal idparametro) {
		this.idparametro = idparametro;
	}

	public String getIdparametrotipo() {
		return idparametrotipo;
	}

	public void setIdparametrotipo(String idparametrotipo) {
		this.idparametrotipo = idparametrotipo;
	}

	public Integer getCodigon() {
		return codigon;
	}

	public void setCodigon(Integer codigon) {
		this.codigon = codigon;
	}

	public String getCodigoc() {
		return codigoc;
	}

	public void setCodigoc(String codigoc) {
		this.codigoc = codigoc;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIndactivo() {
		return indactivo;
	}

	public void setIndactivo(String indactivo) {
		this.indactivo = indactivo;
	}

	public String getUsucreacion() {
		return usucreacion;
	}

	public void setUsucreacion(String usucreacion) {
		this.usucreacion = usucreacion;
	}

	public Date getFeccreacion() {
		return feccreacion;
	}

	public void setFeccreacion(Date feccreacion) {
		this.feccreacion = feccreacion;
	}

	public String getUsumodif() {
		return usumodif;
	}

	public void setUsumodif(String usumodif) {
		this.usumodif = usumodif;
	}

	public Date getFecmodif() {
		return fecmodif;
	}

	public void setFecmodif(Date fecmodif) {
		this.fecmodif = fecmodif;
	}

}
