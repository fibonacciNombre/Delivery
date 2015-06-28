package bbva.delivery.tarjetas.tercero.dao.imp;

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
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.dao.TerceroDao;

import com.rimac.sas.utiles.comunes.JdbcHelper;

@Repository("terceroDao")
public class TerceroDaoImp extends JdbcDaoBase implements TerceroDao {

	private static TerceroDaoImp instance;
	
	private static Logger logger 				= Logger.getLogger(TerceroDaoImp.class.getName());
	
	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public TerceroDaoImp() {
		super();
	}
	
	public static TerceroDaoImp getInstance() {
	    if (instance == null) {
		  instance = new TerceroDaoImp();
	    }
	    return instance;
	  }

	@Override
	public void mntTercero(Tercero tercero) {
		
		logger.info("DAO TerceroDaoImp mntTercero");
		
		Integer idtercero 				= null;
		SimpleJdbcCall call 			= null;
		Map<String, Object> out 		= null; 	
		MapSqlParameterSource in 		= new MapSqlParameterSource(); 
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_TERCERO), 
				"sp_mnt_tercero");
		
		  JdbcHelper.setInOutParameter(call, in, "a_idtercero", 	Types.INTEGER, tercero.getIdtercero());  
	      JdbcHelper.setInParameter(call, in, "a_nombres" , 		Types.VARCHAR, tercero.getNombres());  	  
	      JdbcHelper.setInParameter(call, in, "a_nrodocumento" , 	Types.VARCHAR, tercero.getNrodocumento());
	      JdbcHelper.setInParameter(call, in, "a_apepaterno" , 		Types.VARCHAR, tercero.getApepaterno());
	      JdbcHelper.setInParameter(call, in, "a_apematerno", 		Types.VARCHAR, tercero.getApematerno());   
	      JdbcHelper.setInParameter(call, in, "a_idptipodocumento", Types.INTEGER, tercero.getIdptipodocumento());  
	      JdbcHelper.setInParameter(call, in, "a_telfmovil", 		Types.VARCHAR, tercero.getTelfmovil());  
	      JdbcHelper.setInParameter(call, in, "a_correo", 			Types.VARCHAR, tercero.getCorreo());  
	      JdbcHelper.setInParameter(call, in, "a_idcourier", 		Types.INTEGER, tercero.getIdcourier()); 
	      JdbcHelper.setInParameter(call, in, "a_idpestado", 		Types.INTEGER, tercero.getIdpestado());
	      JdbcHelper.setInParameter(call, in, "a_usuario", 			Types.VARCHAR, tercero.getUsucreacion());
	      JdbcHelper.setInParameter(call, in, "a_historial", 		Types.VARCHAR, tercero.getHistorial());
		
		out = call.execute(in);
		
		idtercero = JdbcHelper.getOutResult(out, "a_idtercero", Integer.class);
		tercero.setIdtercero(idtercero);  
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tercero> lstTerceros(Tercero tercero) {
		
		logger.info("DAO TerceroDaoImp lstTerceros");
		
		List<Tercero> lista 		= null; 
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_TERCERO),
				"sp_lst_tercero");
 
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
}

