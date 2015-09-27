package bbva.delivery.tarjetas.usuario.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Oficina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2663957027825393173L;

	private Integer idoficina;
	private Integer codoficina;
	private String oficina;
	private String categoria;
	private String ubigeo;
	private String direccion;
	private String horariolv;
	private String horarios;
	private String horariodf;
	private BigDecimal latitudofi;
	private BigDecimal longitudofi;
	private String usucreacion;
	private String usumodificacion;
	private String ubigeodescripcion;
	
	@SuppressWarnings("rawtypes")
	public Oficina(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}	
	public Oficina() {
	}
	
	public Integer getIdoficina(){
		return idoficina;
	}
	public void setIdoficina(Integer idoficina){
		this.idoficina = idoficina;
	}
	
	public Integer getCodoficina(){
		return codoficina;
	}
	public void setCodoficina(Integer codoficina){
		this.codoficina = codoficina;
	}

	public String getOficina(){
		return oficina;
	}
	public void setOficina(String oficina){
		this.oficina = oficina;
	}
	
	public String getCategoria(){
		return categoria;
	}
	public void setCategoria(String categoria){
		this.categoria = categoria;
	}
	
	public String getUbigeo(){
		return ubigeo;
	}
	public void setUbigeo (String ubigeo){
		this.ubigeo = ubigeo;
	}
	
	public String getDireccion(){
		return direccion;
	}
	public void setDireccion (String direccion){
		this.direccion = direccion;
	}
	
	public String getHorariolv(){
		return horariolv;
	}
	public void setHorariolv (String horariolv){
		this.horariolv = horariolv;
	}
	
	public String getHorarios(){
		return horarios;
	}
	public void setHorarios (String horarios){
		this.horarios = horarios;
	}
	
	public String getHorariodf(){
		return horariodf;
	}
	public void setHorariodf (String horariodf){
		this.horariodf = horariodf;
	}
	
	public String getUsucreacion(){
		return usucreacion;
	}
	public void setUsucreacion(String usucreacion){
		this.usucreacion = usucreacion;
	}
	
	public String getUsumodificacion(){
		return usumodificacion;
	}
	public void setUsumodificacion(String usumodificacion){
		this.usumodificacion = usumodificacion;
	}
	
	public String getUbigeodescripcion(){
		return ubigeodescripcion;
	}
	public void setUbigeodescripcion (String ubigeodescripcion){
		this.ubigeodescripcion = ubigeodescripcion;
	}
	
	public BigDecimal getLatitudofi(){
		return latitudofi;
	}
	public void setLatitudofi(BigDecimal latitudofi){
		this.latitudofi = latitudofi;
	}
	
	public BigDecimal getLongitudofi(){
		return longitudofi;
	}
	public void setLongitudofi(BigDecimal longitudofi){
		this.longitudofi = longitudofi;
	}
}
