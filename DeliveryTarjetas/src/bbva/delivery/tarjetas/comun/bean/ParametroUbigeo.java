package bbva.delivery.tarjetas.comun.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class ParametroUbigeo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1602611753874074213L;
	
	private Integer idubigeo;
	private String ubigeo;
	private String departamento;
	private String provincia;
	private String distrito;
	
	public ParametroUbigeo() {}

	@SuppressWarnings("rawtypes")
	public ParametroUbigeo(Map map) throws Exception {
		ResourceManager.populateDtoFromMap(this, map);
	}
	
	public Integer getIdubigeo(){
		return idubigeo;
	}
	public void setIdubigeo(Integer idubigeo){
		this.idubigeo = idubigeo;
	}
	
	public String getUbigeo(){
		return ubigeo;
	}
	public void setUbigeo(String ubigeo){
		this.ubigeo = ubigeo;
	}
	
	public String getDepartamento(){
		return departamento;
	}
	public void setDepartamento(String departamento){
		this.departamento = departamento;
	}
	
	public String getProvincia(){
		return provincia;
	}
	public void setProvincia(String provincia){
		this.provincia = provincia;
	}
	
	public String getDistrito(){
		return distrito;
	}
	public void setDistrito(String distrito){
		this.distrito = distrito;
	}

}
