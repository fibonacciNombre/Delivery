package bbva.delivery.tarjetas.courier.dao.imp;
 
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.dao.CourierDao;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

import com.rimac.sas.utiles.comunes.JdbcHelper;
             
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
		JdbcHelper.setInParameter(call, in, "a_rznsocial", 	OracleTypes.VARCHAR, param.getRznsocial());
		JdbcHelper.setInParameter(call, in, "a_nrodocumentocou", OracleTypes.VARCHAR, param.getNrodocumentocou()); 
		JdbcHelper.setInParameter(call, in, "a_idpestado", OracleTypes.INTEGER, param.getIdpestado()); 

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
		
		  JdbcHelper.setInOutParameter(call, in, "a_idcourier", 	Types.INTEGER, courier.getIdcourier());  
	      JdbcHelper.setInParameter(call, in, "a_codbbva" , 		Types.VARCHAR, courier.getCodbbva());  	  
	      JdbcHelper.setInParameter(call, in, "a_rznsocial" , 		Types.VARCHAR, courier.getRznsocial()); 
	      JdbcHelper.setInParameter(call, in, "a_telffijo", 		Types.VARCHAR, courier.getTelffijo()); 
	      JdbcHelper.setInParameter(call, in, "a_telfmovil", 		Types.VARCHAR, courier.getTelfmovil());  
	      JdbcHelper.setInParameter(call, in, "a_observacion", 		Types.VARCHAR, courier.getObservacion()); 
	      JdbcHelper.setInParameter(call, in, "a_nrodocumentocou", 	Types.VARCHAR, courier.getNrodocumentocou()); 
	      JdbcHelper.setInParameter(call, in, "a_idptipodocumento", Types.INTEGER, courier.getIdptipodocumento());
	      JdbcHelper.setInParameter(call, in, "a_direccion", 		Types.VARCHAR, courier.getDireccion());  
	      JdbcHelper.setInParameter(call, in, "a_idpestado", 		Types.INTEGER, courier.getIdpestado()); 
	      JdbcHelper.setInParameter(call, in, "a_correo", 			Types.VARCHAR, courier.getCorreo()); 
	      JdbcHelper.setInParameter(call, in, "a_historial", 		Types.VARCHAR, courier.getHistorial()); 
	      JdbcHelper.setInParameter(call, in, "a_usuario", 		Types.VARCHAR, courier.getUsuario()); 
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
 
		JdbcHelper.setInParameter(call, in, "a_idcursor", OracleTypes.INTEGER, courier.getIdcourier());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Courier.class);

		out = call.execute(in);
		
		lista = JdbcHelper.getListResultSet(out, "a_cursor", Courier.class);
		  
		 return lista;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tercero> lstTercerosxCourier(Tercero tercero) {
		// TODO Auto-generated method stub
		logger.info("DAO lstTercerosxCourier");
		
		List<Tercero> lista 		= null; 
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_COURIER),
				"sp_lst_colaboradores");
 
		JdbcHelper.setInParameter(call, in, "a_idptipodocumento", 	Types.INTEGER, tercero.getIdptipodocumento());
	    JdbcHelper.setInParameter(call, in, "a_nrodocumento" , 		Types.VARCHAR, tercero.getNrodocumento());  	  
	    JdbcHelper.setInParameter(call, in, "a_idcourier" , 		Types.INTEGER, tercero.getIdcourier());
	    JdbcHelper.setInParameter(call, in, "a_idpestado" , 		Types.INTEGER, tercero.getIdpestado());
	    JdbcHelper.setInParameter(call, in, "a_idtercero", 			Types.INTEGER, tercero.getIdtercero());   

		JdbcHelper.setOutParameter(call, "a_cursor", 		OracleTypes.CURSOR, Tercero.class);
		 
		out 	= call.execute(in);
		lista 	= (List<Tercero>) out.get("a_cursor");
		  
		return lista;
	}
	
	@Override
	public Integer obtCourierXCodbbva(String codbbva) { 
		
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		Integer idcourier = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_obt_curierxcodbbva");
		
		  JdbcHelper.setInParameter(call, in, "a_codbbva"      , 	Types.VARCHAR, codbbva);
	      JdbcHelper.setOutParameter(call, "a_idcourier"     , 	Types.INTEGER, Integer.class);  
	      
		
		out = call.execute(in);
		
		idcourier = JdbcHelper.getOutResult(out, "a_idcourier", Integer.class);
		return idcourier;

	}
 
	@Override
	public Integer obtTipoarchXExt(String extension) { 
		
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		Integer idtipoarchivo = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_obt_idtipoarchxext");
		
		  JdbcHelper.setInParameter(call, in, "a_tipoarchivo"   , 	Types.VARCHAR, extension);
	      JdbcHelper.setOutParameter(call, "a_idtipoarchivo"     , 	Types.INTEGER, Integer.class);  
	      
		
		out = call.execute(in);
		
		idtipoarchivo = JdbcHelper.getOutResult(out, "a_idtipoarchivo", Integer.class);
		return idtipoarchivo;

	}

}
