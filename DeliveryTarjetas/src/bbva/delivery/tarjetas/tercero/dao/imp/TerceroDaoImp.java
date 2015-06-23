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

import com.rimac.sas.utiles.comunes.JdbcHelper;

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.dao.TerceroDao;

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
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		Integer idtercero = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_mnt_tercero");
		
		  JdbcHelper.setInOutParameter(call, in, "a_idcourier", 	Types.NUMERIC, tercero.getIdtercero());  
	      JdbcHelper.setInParameter(call, in, "a_codbbva" , 		Types.VARCHAR, tercero.getNombres());  	  
	      JdbcHelper.setInParameter(call, in, "a_rznsocial" , 		Types.VARCHAR, tercero.getNrodocumento());
	      JdbcHelper.setInParameter(call, in, "a_nombre" , 			Types.VARCHAR, tercero.getApepaterno());
	      JdbcHelper.setInParameter(call, in, "a_telffijo", 		Types.VARCHAR, tercero.getApematerno()); 
	      JdbcHelper.setInParameter(call, in, "a_telfmovil", 		Types.VARCHAR, tercero.getRznsocial());  
	      JdbcHelper.setInParameter(call, in, "a_observacion", 		Types.VARCHAR, tercero.getIdroltercero()); 
	      JdbcHelper.setInParameter(call, in, "a_nrodocumentocou", 	Types.VARCHAR, tercero.getIdptipodocumento()); 
	      JdbcHelper.setInParameter(call, in, "a_idptipodocumento", Types.INTEGER, tercero.getIdtercerorel());
	      JdbcHelper.setInParameter(call, in, "a_direccion", 		Types.INTEGER, tercero.getIdarchivo());
	      JdbcHelper.setInParameter(call, in, "a_direccion", 		Types.INTEGER, tercero.getIdcourier()); 
	      JdbcHelper.setInParameter(call, in, "a_direccion", 		Types.INTEGER, tercero.getIdpestado());
	      JdbcHelper.setInParameter(call, in, "a_direccion", 		Types.INTEGER, tercero.getUsuario());
	      JdbcHelper.setInParameter(call, in, "a_direccion", 		Types.INTEGER, tercero.getHistorial());
		
		out = call.execute(in);
		
		idtercero = JdbcHelper.getOutResult(out, "a_idtercero", Integer.class);
		tercero.setIdcourier(idtercero); 
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tercero> lstTerceros(Tercero tercero) {
		List<Tercero> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_COURIER),
				"sp_lst_tercero");
 
		JdbcHelper.setOutParameter(call, "a_cursor", 		OracleTypes.CURSOR, Tercero.class);
		 
		out = call.execute(in);
		lista = (List<Tercero>) out.get("a_cursor");
		  
		return lista;

	}

	@Override
	public List<Tercero> obtTercero(Tercero tercero) {
		// TODO Auto-generated method stub
		return null;
	}

	 
}

