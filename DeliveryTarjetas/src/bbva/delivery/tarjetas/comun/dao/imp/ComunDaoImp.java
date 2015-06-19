package bbva.delivery.tarjetas.comun.dao.imp;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.bean.ArchivoBlob;
import bbva.delivery.tarjetas.comun.bean.Constante;
import bbva.delivery.tarjetas.comun.bean.ListaParametroCursor;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.dao.ComunDao;

import com.rimac.sas.utiles.comunes.JdbcHelper;

@Repository("comunDao")
public class ComunDaoImp extends JdbcDaoBase implements ComunDao {
	
	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	private String OWNER_ESQUEMA_COMUNES 	= resources.getString(ConstantsProperties.OWNER_ESQUEMA_COMUNES);
	private String PQ_COMUNES_COMUN 		= resources.getString(ConstantsProperties.PQ_COMUNES_COMUN);
	
	/*
	 * (non-Javadoc)
	 * @see rimac.portalweb.dao.ComunDaoImp#
	 * 		obtenerConstante(String)
	 */
	public Constante obtenerConstante(String ideConstante) {
		logger.info("Ejecutando m�todo: obtenerConstante");
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null;
		List<Constante> lstConstantes = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				OWNER_ESQUEMA_COMUNES, PQ_COMUNES_COMUN,"sp_obt_constante");
		
		in = new MapSqlParameterSource();
		JdbcHelper.setInParameter(call,in,"a_ideconstante",Types.VARCHAR, ideConstante);
		JdbcHelper.setOutParameter(call, "a_cursor",OracleTypes.CURSOR, Constante.class);

		out = call.execute(in);
		lstConstantes = JdbcHelper.getListResultSet(out, "a_cursor", Constante.class);
		
		return (lstConstantes!=null && lstConstantes.size()>0)?lstConstantes.get(0):null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see rimac.portalweb.dao.ComunDaoImp#
	 * 		obtenerParametro(rimac.portalweb.bean.Parametro)
	 */	
	public Parametro obtenerParametro(Parametro parametro) {
		System.out.println("***************************parametro**********************");
//		System.out.println(ToStringBuilder.reflectionToString(parametro,ToStringStyle.MULTI_LINE_STYLE));
		logger.info("Ejecutando m�todo: obtenerParametro");
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null;
		List<Parametro> lista = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				OWNER_ESQUEMA_COMUNES, PQ_COMUNES_COMUN,"sp_lst_parametro2");
		
		in = new MapSqlParameterSource();
		JdbcHelper.setInParameter(call,in,"a_idetippar",Types.VARCHAR, parametro.getIdeTipPar());
		JdbcHelper.setInParameter(call,in,"a_idetipparpadre",Types.VARCHAR, parametro.getIdeTipParPadre());
		JdbcHelper.setInParameter(call,in,"a_codigo",Types.VARCHAR, parametro.getCodigo());
		JdbcHelper.setInParameter(call,in,"a_abreviatura",Types.VARCHAR, parametro.getAbreviatura());
		JdbcHelper.setInParameter(call,in,"a_descripcion",Types.VARCHAR, parametro.getDescripcion());
		JdbcHelper.setInParameter(call,in,"a_descripcion2",Types.VARCHAR, parametro.getDescripcion2());
		JdbcHelper.setInParameter(call,in,"a_indactivo",Types.VARCHAR, parametro.getIndActivo());
		JdbcHelper.setInParameter(call,in,"a_masterdetail",Types.VARCHAR, parametro.getMasterDetail());
		JdbcHelper.setOutParameter(call, "v_cursor",OracleTypes.CURSOR, Parametro.class);

		out = call.execute(in);
		lista = JdbcHelper.getListResultSet(out, "v_cursor", Parametro.class);
		System.out.println("***************************parametro resultados..**********************");
//		for(Parametro t: lista){
//			System.out.println(ToStringBuilder.reflectionToString(t,ToStringStyle.MULTI_LINE_STYLE));	
//		}
		
		
		return (lista != null && lista.size() > 0)? lista.get(0) : new Parametro();
	}

	/* (non-Javadoc)
	 * @see rimac.portalweb.comun.dao.ComunDao#listarParametro(rimac.portalweb.comun.bean.Parametro)
	 */
	public List<Parametro> listarParametro(Parametro parametro) {
		logger.info("Ejecutando m�todo: obtenerParametro");
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				OWNER_ESQUEMA_COMUNES, PQ_COMUNES_COMUN,"sp_lst_parametro2");
		
		in = new MapSqlParameterSource();
		JdbcHelper.setInParameter(call,in,"a_idetippar",Types.VARCHAR, parametro.getIdeTipPar());
		JdbcHelper.setInParameter(call,in,"a_idetipparpadre",Types.VARCHAR, parametro.getIdeTipParPadre());
		JdbcHelper.setInParameter(call,in,"a_codigo",Types.VARCHAR, parametro.getCodigo());
		JdbcHelper.setInParameter(call,in,"a_abreviatura",Types.VARCHAR, parametro.getAbreviatura());
		JdbcHelper.setInParameter(call,in,"a_descripcion",Types.VARCHAR, parametro.getDescripcion());
		JdbcHelper.setInParameter(call,in,"a_descripcion2",Types.VARCHAR, parametro.getDescripcion2());
		JdbcHelper.setInParameter(call,in,"a_indactivo",Types.VARCHAR, parametro.getIndActivo());
		JdbcHelper.setInParameter(call,in,"a_masterdetail",Types.VARCHAR, parametro.getMasterDetail());
		JdbcHelper.setOutParameter(call, "v_cursor",OracleTypes.CURSOR, Parametro.class);

		out = call.execute(in);
		
		return JdbcHelper.getListResultSet(out, "v_cursor", Parametro.class);
	}
	
	/* (non-Javadoc)
	 * @see rimac.portalweb.comun.dao.ComunDao#obtenerListaParametros(rimac.portalweb.comun.bean.Parametro)
	 */
	@SuppressWarnings("unchecked")
	public void obtenerListaParametros(Parametro param) {
		
		SimpleJdbcCall call = null;
		SqlParameterSource in = null;
		Map<String, Object> out = null;
  
		call = new SimpleJdbcCall(getJdbcTemplate()).
							withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_COMUNES)).
							withCatalogName(resources.getString(ConstantsProperties.PQ_COMUNES_COMUN)).
							withProcedureName("SP_LISTA_PARAMETRO").
							withoutProcedureColumnMetaDataAccess().
							declareParameters(
									new SqlParameter("a_idetippar", Types.VARCHAR),
									new SqlOutParameter("a_cursor", OracleTypes.CURSOR, 
								    new BeanPropertyRowMapper<ListaParametroCursor>(ListaParametroCursor.class))
									);
		
		in = new MapSqlParameterSource().addValue("a_idetippar", param.getIdeTipPar());
  
		out = call.execute(in);
  
		param.setCursor((List<ListaParametroCursor>) out.get("a_cursor"));
	}
	
	public void mntArchivoblob(ArchivoBlob param) {      
		logger.info("Ejecutando m�todo mntArchivoblob");
		SimpleJdbcCall call = null;
		SqlParameterSource in = null;
		Map<String, Object> out = null;

		call = new SimpleJdbcCall(getJdbcTemplate()).
				withSchemaName(OWNER_ESQUEMA_COMUNES).
				withCatalogName(PQ_COMUNES_COMUN).
				withProcedureName("SP_MNT_ARCHIVOBLOB").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(
						new SqlInOutParameter("a_idearchivoblob", Types.INTEGER),
						new SqlParameter("a_archivoblob", Types.BINARY),
						new SqlParameter("a_nomarchivo", Types.VARCHAR),
						new SqlParameter("a_nomarchivo2", Types.VARCHAR),
						new SqlParameter("a_extension", Types.VARCHAR),
						new SqlParameter("a_bytes", Types.VARCHAR),
						new SqlParameter("a_descripcion", Types.VARCHAR));
					
		in = new MapSqlParameterSource().
				addValue("a_idearchivoblob", param.getIdearchivoblob()).
				addValue("a_archivoblob", param.getArchivoblob()).
				addValue("a_nomarchivo", param.getNomarchivo()).
				addValue("a_nomarchivo2", param.getNomarchivo2()).
				addValue("a_extension", param.getExtension()).
				addValue("a_bytes", param.getBytes()).
				addValue("a_descripcion", param.getDescripcion());
		
		out = call.execute(in);		
		param.setIdearchivoblob((Integer)out.get("a_idearchivoblob"));      
  }
}
