package bbva.delivery.tarjetas.comun.dao.imp;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.bean.ArchivoBlob;
import bbva.delivery.tarjetas.comun.bean.Constante;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.bean.ParametroUbigeo;
import bbva.delivery.tarjetas.comun.dao.ComunDao;
import bbva.delivery.tarjetas.util.JdbcHelper;

@Repository("comunDao")
public class ComunDaoImp extends JdbcDaoBase implements ComunDao {

	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	private static Logger logger = Logger.getLogger(ComunDaoImp.class.getName());
	
	public Constante obtenerConstante(String ideConstante) {
		logger.info("DAO ComunDaoImp obtenerConstante");
		return null;
	}

	@Override
	public Parametro obtenerParametro(Parametro parametro) {
		logger.info("DAO ComunDaoImp obtenerParametro");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> lstParametro(Parametro param) {
		
		logger.info("DAO ComunDaoImp lstParametro");
		
		List<Parametro> lista 		= null;
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
							getJdbcTemplate(),
							resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
							resources.getString(ConstantsProperties.PQ_DEL_COMUN),
							"sp_lst_parametro");
 
		JdbcHelper.setInParameter(call, in, "a_idparametrotipo", OracleTypes.VARCHAR, param.getIdparametrotipo()); 
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Parametro.class);
		 
		out = call.execute(in);
		lista = (List<Parametro>) out.get("a_cursor");
		  
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> cmbParametro(Parametro param) {
		  
		logger.info("DAO ComunDaoImp cmbParametro");
		
		List<Parametro> lista 		= null; 		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_COMUN),
								"sp_cmb_parametro");
 
		JdbcHelper.setInParameter(call, in, "a_idparametrotipo", OracleTypes.VARCHAR, param.getIdparametrotipo()); 
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Parametro.class);
		 
		out = call.execute(in);
		lista = (List<Parametro>) out.get("a_cursor");
		  
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroUbigeo> lstDepartamentos(ParametroUbigeo parametro)	
	{
		logger.info("DAO ComunDaoImp lstDepartamentos");
		
		List<ParametroUbigeo> lista 		= null; 		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();
				
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_SERVICIO),
				"sp_lst_departamentos_servicio");
		
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, ParametroUbigeo.class);
 		
		out = call.execute(in);
		lista = (List<ParametroUbigeo>) out.get("a_cursor");
		  
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroUbigeo> lstProvincias(ParametroUbigeo parametro)	
	{
		logger.info("DAO ComunDaoImp lstDepartamentos");
		
		List<ParametroUbigeo> lista 		= null; 		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();
				
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_SERVICIO),
				"sp_lst_provincias_servicio");
		JdbcHelper.setInParameter(call, in, "a_ubigeodpto", OracleTypes.VARCHAR, parametro.getUbigeo());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, ParametroUbigeo.class);
 		
		out = call.execute(in);
		lista = (List<ParametroUbigeo>) out.get("a_cursor");
		  
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroUbigeo> lstDistritos(ParametroUbigeo parametro)	
	{
		logger.info("DAO ComunDaoImp lstDepartamentos");
		
		List<ParametroUbigeo> lista 		= null; 		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();
				
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_SERVICIO),
				"sp_lst_distritos_servicio");
		JdbcHelper.setInParameter(call, in, "a_ubigeoprov", OracleTypes.VARCHAR, parametro.getUbigeo());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, ParametroUbigeo.class);
 		
		out = call.execute(in);
		lista = (List<ParametroUbigeo>) out.get("a_cursor");
		  
		return lista;
	}

	@Override
	public void obtenerListaParametros(Parametro param) {
		logger.info("DAO ComunDaoImp obtenerListaParametros");		
	}

	public void mntArchivoblob(ArchivoBlob param) {
		logger.info("DAO ComunDaoImp mntArchivoblob");		
	}
}
