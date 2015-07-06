package bbva.delivery.tarjetas.comun.dao.imp;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.bean.ArchivoBlob;
import bbva.delivery.tarjetas.comun.bean.Constante;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.dao.ComunDao;
import bbva.delivery.tarjetas.util.JdbcHelper;

@Repository("comunDao")
public class ComunDaoImp extends JdbcDaoBase implements ComunDao {

	private static final ResourceBundle resources = ResourceBundle
			.getBundle("configuracion");
	
	public Constante obtenerConstante(String ideConstante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parametro obtenerParametro(Parametro parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parametro> lstParametro(Parametro param) {
		  
		List<Parametro> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
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
		  
		List<Parametro> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_COMUN),
				"sp_cmb_parametro");
 
		JdbcHelper.setInParameter(call, in, "a_idparametrotipo", OracleTypes.VARCHAR, param.getIdparametrotipo()); 
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Parametro.class);
		 
		out = call.execute(in);
		lista = (List<Parametro>) out.get("a_cursor");
		  
		return lista;
	}



	@Override
	public void obtenerListaParametros(Parametro param) {
		// TODO Auto-generated method stub
		
	}

	public void mntArchivoblob(ArchivoBlob param) {
		// TODO Auto-generated method stub
		
	}
	
	
}
