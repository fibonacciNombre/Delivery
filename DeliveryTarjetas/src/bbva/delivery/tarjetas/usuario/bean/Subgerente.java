package bbva.delivery.tarjetas.usuario.bean;

import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class Subgerente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7813970368205651363L;
	private Integer idsubgerente;
	private Integer idoficina;
	private String nombre;
	private String apellidopaterno;
	private String apellidomaterno;
	private String correo;
	private Integer estado;
	private String oficina;
	private String ubigeo;
	
	@SuppressWarnings("rawtypes")
	public Subgerente(Map map) throws Exception {
	    ResourceManager.populateDtoFromMap(this, map);
	}
	
	public Subgerente() {
	}
	
	public Integer getIdsubgerente(){
		return idsubgerente;
	}
	public void setIdsubgerente(Integer idsubgerente){
		this.idsubgerente = idsubgerente;
	}
	
	public Integer getIdoficina(){
		return idoficina;
	}
	public void setIdoficina(Integer idoficina){
		this.idoficina = idoficina;
	}

	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getApellidopaterno(){
		return apellidopaterno;
	}
	public void setApellidopaterno(String apellidopaterno){
		this.apellidopaterno = apellidopaterno;
	}

	public String getApellidomaterno(){
		return apellidomaterno;
	}
	public void setApellidomaterno(String apellidomaterno){
		this.apellidomaterno = apellidomaterno;
	}

	public String getCorreo(){
		return correo;
	}
	public void setCorreo(String correo){
		this.correo = correo;
	}
	
	public Integer getEstado(){
		return estado;
	}
	public void setEstado(Integer estado){
		this.estado = estado;
	}

	public String getOficina(){
		return oficina;
	}
	public void setOficina(String oficina){
		this.oficina = oficina;
	}
	
	public String getUbigeo(){
		return ubigeo;
	}
	public void setUbigeo(String ubigeo){
		this.ubigeo = ubigeo;
	}
}
