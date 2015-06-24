package bbva.delivery.tarjetas.comun.dao;

import java.util.List;

import bbva.delivery.tarjetas.comun.bean.ArchivoBlob;
import bbva.delivery.tarjetas.comun.bean.Constante;
import bbva.delivery.tarjetas.comun.bean.Parametro;

public interface ComunDao{
	/**
	 * Obtiene los valores de la constante registrada en base de datos
	 * @param ideConstante Identificador de la constate
	 * @return Datos de la constante
	 */
	Constante obtenerConstante(String ideConstante);
	
	/**
	 * Obtiene el parametro seg�n los filtros de b�squeda.
	 * 
	 * @param parametro los filtros para la b�squeda del parametro
	 * @return el parametro buscado
	 */
	Parametro obtenerParametro(Parametro parametro);
	
	/**
	 * Lista los parametros seg�n los filtros de b�squeda.
	 * 
	 * @param parametro los filtros para la b�squeda del parametro
	 * @return la lista de valores del parametro buscado seg�n los filtros
	 */
	List<Parametro> lstParametro(Parametro parametro);
	
	/**
	 * 
	 * @param param
	 */
	void obtenerListaParametros(Parametro param);
	
	/**
	 * 
	 * @param param
	 */
	void mntArchivoblob(ArchivoBlob param);
	
	
}