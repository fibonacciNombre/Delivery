package bbva.delivery.tarjetas.courier.dao.imp;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.rimac.sas.utiles.comunes.JdbcHelper;

import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.dao.CourierDao;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

@Repository("courierDao")
public class CourierDaoImp extends JdbcDaoBase implements CourierDao {

	private static CourierDaoImp instance;
	
	private static Logger logger 				= Logger.getLogger(CourierDaoImp.class.getName());
	
	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public CourierDaoImp() {
		super();
	}
	
	public static CourierDaoImp getInstance() {
	    if (instance == null) {
		  instance = new CourierDaoImp();
	    }
	    return instance;
	  }

 
	@SuppressWarnings("unchecked")
	@Override
	public List<Courier> lstCouriers(Courier param) {
		List<Courier> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_COURIER),
				"sp_lst_courier");
 
		JdbcHelper.setInParameter(call, in, "a_codbbva",	OracleTypes.VARCHAR, param.getCodbbva());
		JdbcHelper.setInParameter(call, in, "a_nombre", 	OracleTypes.VARCHAR, param.getNombre());
		JdbcHelper.setInParameter(call, in, "a_rznsocial", 	OracleTypes.VARCHAR, param.getRznsocial());
		JdbcHelper.setInParameter(call, in, "a_nrodocumentocou", OracleTypes.VARCHAR, param.getNrodocumentocou()); 
		JdbcHelper.setOutParameter(call, "a_cursor", 		OracleTypes.CURSOR, Courier.class);
		 
		out = call.execute(in);
		lista = (List<Courier>) out.get("a_cursor");
		  
		return lista;
	}

	@Override
	public void mntCourier(Courier courier) {
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		Integer idcourier = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_mnt_courier");
		
		  JdbcHelper.setInOutParameter(call, in, "a_idcourier", 	Types.NUMERIC, courier.getIdcourier());  
	      JdbcHelper.setInParameter(call, in, "a_codbbva" , 		Types.VARCHAR, courier.getCodbbva());  	  
	      JdbcHelper.setInParameter(call, in, "a_rznsocial" , 		Types.VARCHAR, courier.getNombre());
	      JdbcHelper.setInParameter(call, in, "a_nombre" , 			Types.VARCHAR, courier.getNombre());
	      JdbcHelper.setInParameter(call, in, "a_telffijo", 		Types.VARCHAR, courier.getTelffijo()); 
	      JdbcHelper.setInParameter(call, in, "a_telfmovil", 		Types.VARCHAR, courier.getTelfmovil());  
	      JdbcHelper.setInParameter(call, in, "a_observacion", 		Types.VARCHAR, courier.getObservacion()); 
	      JdbcHelper.setInParameter(call, in, "a_nrodocumentocou", 	Types.VARCHAR, courier.getNrodocumentocou()); 
	      JdbcHelper.setInParameter(call, in, "a_idptipodocumento", Types.INTEGER, courier.getIdptipodocumento());
	      JdbcHelper.setInParameter(call, in, "a_direccion", 		Types.INTEGER, courier.getIdptipodocumento()); 
		
		out = call.execute(in);
		
		idcourier = JdbcHelper.getOutResult(out, "a_idcourier", Integer.class);
		courier.setIdcourier(idcourier); 
		
	}


	@Override
	public List<Courier>  obtCourier(Courier courier) { 

		List<Courier> lista = null;
		  
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
	  
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_obt_courier");
 
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Courier.class);

		out = call.execute(in);
		
		lista = JdbcHelper.getListResultSet(out, "a_cursor", Courier.class);
		  
		 return lista;
		
	}

	@Override
	public List<Tercero> lstTercerosxCourier(Courier courier) {
		// TODO Auto-generated method stub
		logger.info("Dao lstTercerosxCourier");
		return null;
	}
 
	
}