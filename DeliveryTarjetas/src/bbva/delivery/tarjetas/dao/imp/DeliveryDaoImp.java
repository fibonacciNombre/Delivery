package bbva.delivery.tarjetas.dao.imp;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.rimac.sas.utiles.comunes.JdbcHelper;

import bbva.delivery.tarjetas.bean.Delivery; 
import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.dao.DeliveryDao;

@Repository("portalWebDao")
public class DeliveryDaoImp extends JdbcDaoBase implements DeliveryDao {
	
	private static final ResourceBundle resources = ResourceBundle
			.getBundle("configuracion");
	
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("dao ok");

	}	
	
	@Override
	public void mntDelivery(Delivery param) { 
		
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		BigDecimal iddelivery = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_mnt_delivery");
		
		  JdbcHelper.setInOutParameter(call, in, "a_iddelivery"      , 	Types.NUMERIC, param.getIddelivery());  
	      JdbcHelper.setInParameter(call, in, "a_tipodocumento" , 	Types.VARCHAR, param.getTipodocumento());  	  
	      JdbcHelper.setInParameter(call, in, "a_nrodocumentocli" , 	Types.VARCHAR, param.getNrodocumentocli()); 	  
	      JdbcHelper.setInParameter(call, in, "a_nombrescli"      , 	Types.VARCHAR, param.getNombrescli()); 
	      JdbcHelper.setInParameter(call, in, "a_tipotarjeta"     , 	Types.VARCHAR, param.getTipotarjeta());  
	      JdbcHelper.setInParameter(call, in, "a_pridigtarjeta"   , 	Types.VARCHAR, param.getPridigtarjeta()); 
	      JdbcHelper.setInParameter(call, in, "a_ultdigtarjeta"   , 	Types.VARCHAR, param.getUltdigtarjeta()); 
	      JdbcHelper.setInParameter(call, in, "a_nrocontrato"     , 	Types.VARCHAR, param.getNrocontrato()); 
	      JdbcHelper.setInParameter(call, in, "a_mtoasoctarjeta"  , 	Types.VARCHAR, param.getMtoasoctarjeta()); 
	      JdbcHelper.setInParameter(call, in, "a_fecentrega"      , 	Types.DATE, param.getFecentrega()); 
	      JdbcHelper.setInParameter(call, in, "a_horaentrega"     , 	Types.DATE, param.getHoraentrega()); 
	      JdbcHelper.setInParameter(call, in, "a_lugarentrega"    , 	Types.VARCHAR, param.getLugarentrega()); 
	      JdbcHelper.setInParameter(call, in, "a_indverificacion" , 	Types.VARCHAR, param.getIndverificacion()); 
	      JdbcHelper.setInParameter(call, in, "a_direccioncli"    , 	Types.VARCHAR, param.getDireccioncli()); 
	      JdbcHelper.setInParameter(call, in, "a_distritocli"     , 	Types.VARCHAR, param.getDistritocli()); 
	      JdbcHelper.setInParameter(call, in, "a_latitudofi"      , 	Types.NUMERIC, param.getLatitudofi()); 
	      JdbcHelper.setInParameter(call, in, "a_longitudofi"     , 	Types.NUMERIC, param.getLongitudofi()); 
	      JdbcHelper.setInParameter(call, in, "a_correocli"       , 	Types.VARCHAR, param.getCorreocli()); 
	      JdbcHelper.setInParameter(call, in, "a_telmovilcli"     , 	Types.VARCHAR, param.getTelmovilcli()); 
	      JdbcHelper.setInParameter(call, in, "a_ordenentrega"    , 	Types.VARCHAR, param.getOrdenentrega()); 
	      JdbcHelper.setInParameter(call, in, "a_idcourier"       , 	Types.NUMERIC, param.getIdcourier()); 
	      JdbcHelper.setInParameter(call, in, "a_idtercero"       , 	Types.NUMERIC, param.getIdtercero()); 
	      JdbcHelper.setInParameter(call, in, "a_idpestado"        , 	Types.INTEGER, param.getIdpestado()); 
	      JdbcHelper.setInParameter(call, in, "a_idarchivo"       , 	Types.INTEGER, param.getIdarchivo()); 
	      JdbcHelper.setInParameter(call, in, "a_idpestadocarga"     , 	Types.INTEGER, param.getIdpestadocarga()); 
	      JdbcHelper.setInParameter(call, in, "a_historial"    , 	Types.VARCHAR, param.getHistorial()); 
	      JdbcHelper.setInParameter(call, in, "a_grupocarga"      , 	Types.NUMERIC, param.getGrupocarga()); 
		
		out = call.execute(in);
		
		iddelivery = JdbcHelper.getOutResult(out, "a_iddelivery", BigDecimal.class);
		param.setIddelivery(iddelivery); 

	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> lstDelivery(Delivery param) {
		  
		List<Delivery> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_COURIER),
				"sp_lst_delivery");
 
		
		JdbcHelper.setInParameter(call, in, "a_nrodocumentocli", OracleTypes.VARCHAR, param.getNrodocumentocli());
		JdbcHelper.setInParameter(call, in, "a_nombrescli", OracleTypes.VARCHAR, param.getNombrescli());
		JdbcHelper.setInParameter(call, in, "a_nrodocumentocou", OracleTypes.VARCHAR, param.getNrodocumentocli());
		JdbcHelper.setInParameter(call, in, "a_nombrecou", OracleTypes.VARCHAR, param.getNombrescli());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Delivery.class);
		

		out = call.execute(in);
		lista = (List<Delivery>) out.get("a_cursor");
		 
		 
		return lista;
	}
	
	@Override
	public BigDecimal crearGrupoCargaDelivery() { 
		
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		BigDecimal grupocarga = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_crear_grupocarga");
		
		JdbcHelper.setInOutParameter(call, in, "a_grupocarga", 	Types.NUMERIC, grupocarga); 
	 
		
		out = call.execute(in);
		
		grupocarga = JdbcHelper.getOutResult(out, "a_grupocarga", BigDecimal.class);
		return grupocarga;

	}
	
	
	
	@Override
	public Integer valCourierDelivery(String dnicourier) {
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		Integer valor = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_val_courier_delivery");
		
		JdbcHelper.setInParameter(call, in, "a_nrodoccou_xls", 	Types.VARCHAR, dnicourier); 
		JdbcHelper.setInOutParameter(call, in, "a_valor", 	Types.INTEGER, dnicourier); 
		 
		
		out = call.execute(in);
		
		valor = JdbcHelper.getOutResult(out, "a_nrodoccou_xls", Integer.class);
		return valor;

	} 
	 

	
	
}

