package bbva.delivery.tarjetas.bean;
import java.io.Serializable;
import java.util.Map;

import commons.mapper.ResourceManager;

public class ArchivoPDF  implements Serializable{

	private static final long serialVersionUID = 1378242042122173286L;
	
	private Integer codentrega;
	
	private String archivo;
	
	private String codigo;
	
	private String mensaje;
	
	public ArchivoPDF(){
		
	}

	@SuppressWarnings("rawtypes")
	public ArchivoPDF(Map params) throws Exception {
		ResourceManager.populateDtoFromMap(this, params);
	}
	
	public Integer getCodentrega() {
		return codentrega;
	}
	public void setCodentrega(Integer codentrega) {
		this.codentrega = codentrega;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
