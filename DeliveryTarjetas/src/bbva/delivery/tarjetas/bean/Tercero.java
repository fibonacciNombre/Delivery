package bbva.delivery.tarjetas.bean;

import java.math.BigDecimal;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Tercero {
	private BigDecimal idtercero;
	private String nombres;
	private String nrodocumento; 
	private String apepaterno;
	private String apematerno;
	private String razonsocial;
	private Integer idroltercero;
	private Integer idptipodocumento;
	private BigDecimal idtercerorel;
	private Integer idarchivo;
	
	public Tercero (){
		
	}
	
	@SuppressWarnings("rawtypes")
	public Tercero(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNrodocumento() {
		return nrodocumento;
	}

	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}

	public BigDecimal getIdtercero() {
		return idtercero;
	}

	public void setIdtercero(BigDecimal idtercero) {
		this.idtercero = idtercero;
	}

	public String getApepaterno() {
		return apepaterno;
	}

	public void setApepaterno(String apepaterno) {
		this.apepaterno = apepaterno;
	}

	public String getApematerno() {
		return apematerno;
	}

	public void setApematerno(String apematerno) {
		this.apematerno = apematerno;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public Integer getIdroltercero() {
		return idroltercero;
	}

	public void setIdroltercero(Integer idroltercero) {
		this.idroltercero = idroltercero;
	}

	public Integer getIdptipodocumento() {
		return idptipodocumento;
	}

	public void setIdptipodocumento(Integer idptipodocumento) {
		this.idptipodocumento = idptipodocumento;
	}

	public BigDecimal getIdtercerorel() {
		return idtercerorel;
	}

	public void setIdtercerorel(BigDecimal idtercerorel) {
		this.idtercerorel = idtercerorel;
	}

	public Integer getIdarchivo() {
		return idarchivo;
	}

	public void setIdarchivo(Integer idarchivo) {
		this.idarchivo = idarchivo;
	} 
	
}
