package bbva.delivery.tarjetas.dao.imp;

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

import bbva.delivery.tarjetas.bean.Archivo;
import bbva.delivery.tarjetas.bean.ArchivoPDF;
import bbva.delivery.tarjetas.bean.Delivery;
import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.dao.DeliveryDao;
import bbva.delivery.tarjetas.service.imp.DeliveryServiceImp;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.util.JdbcHelper;

@Repository("deliveryDao")
public class DeliveryDaoImp extends JdbcDaoBase implements DeliveryDao {
	
	private static Logger logger = Logger.getLogger(DeliveryServiceImp.class.getName());
	
	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	@Override
	public void mntDelivery(Delivery param) { 
		
		logger.info("DAO DeliveryDaoImp mntDelivery");
		
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
	      JdbcHelper.setInParameter(call, in, "a_horaentrega"     , 	Types.VARCHAR, param.getHoraentrega()); 
	      JdbcHelper.setInParameter(call, in, "a_lugarentrega"    , 	Types.VARCHAR, param.getLugarentrega()); 
	      JdbcHelper.setInParameter(call, in, "a_indverificacion" , 	Types.VARCHAR, param.getIndverificacion()); 
	      JdbcHelper.setInParameter(call, in, "a_direccioncli"    , 	Types.VARCHAR, param.getDireccioncli()); 
	      JdbcHelper.setInParameter(call, in, "a_distritocli"     , 	Types.VARCHAR, param.getDistritocli()); 
	      JdbcHelper.setInParameter(call, in, "a_latitudofi"      , 	Types.NUMERIC, param.getLatitudofi()); 
	      JdbcHelper.setInParameter(call, in, "a_longitudofi"     , 	Types.NUMERIC, param.getLongitudofi()); 
	      JdbcHelper.setInParameter(call, in, "a_correocli"       , 	Types.VARCHAR, param.getCorreocli()); 
	      JdbcHelper.setInParameter(call, in, "a_telmovilcli"     , 	Types.VARCHAR, param.getTelmovilcli()); 
	      JdbcHelper.setInParameter(call, in, "a_ordenentrega"    , 	Types.VARCHAR, param.getOrdenentrega());
	      JdbcHelper.setInParameter(call, in, "a_idtercero"    , 	Types.INTEGER, param.getIdtercero()); 
	      JdbcHelper.setInParameter(call, in, "a_idpestado"        , 	Types.INTEGER, param.getIdpestado()); 
	      JdbcHelper.setInParameter(call, in, "a_idarchivo"        , 	Types.INTEGER, param.getIdarchivo());
	      JdbcHelper.setInParameter(call, in, "a_idpestadocarga"        , 	Types.INTEGER, param.getIdpestadocarga()); 
	      JdbcHelper.setInParameter(call, in, "a_idpestadodelivery"        , 	Types.INTEGER, param.getIdpestadodelivery()); 
	      JdbcHelper.setInParameter(call, in, "a_historial"        , 	Types.VARCHAR, param.getHistorial()); 
	      JdbcHelper.setInParameter(call, in, "a_grupocarga"        , 	Types.INTEGER, param.getGrupocarga()); 
	      JdbcHelper.setInParameter(call, in, "a_usuario"        , 	Types.VARCHAR, param.getUsuario()); 
	          
		out = call.execute(in);
		
		iddelivery = JdbcHelper.getOutResult(out, "a_iddelivery", BigDecimal.class);
		param.setIddelivery(iddelivery); 
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> lstDelivery(Delivery delivery, Tercero tercero) {
		 
		logger.info("DAO DeliveryDaoImp lstDelivery");
		
		List<Delivery> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_COURIER),
				"sp_lst_delivery");

		JdbcHelper.setInParameter(call, in, "a_fecentrega", 	 	OracleTypes.DATE, 	 delivery.getFechaentregaarh());
		JdbcHelper.setInParameter(call, in, "a_idcourier", 		 	OracleTypes.INTEGER, delivery.getIdcourier());
		JdbcHelper.setInParameter(call, in, "a_nrodocumentocli", 	OracleTypes.VARCHAR, delivery.getNrodocumentocli());
		JdbcHelper.setInParameter(call, in, "a_idpestadodelivery", 	OracleTypes.INTEGER, delivery.getIdpestadodelivery());
		JdbcHelper.setInParameter(call, in, "a_tipodocumento",	 	OracleTypes.VARCHAR, tercero.getIdptipodocumento());
		JdbcHelper.setInParameter(call, in, "a_nrodocumento", 	 	OracleTypes.VARCHAR, tercero.getNrodocumento());
		JdbcHelper.setOutParameter(call, 	"a_cursor", 		 	OracleTypes.CURSOR,  Delivery.class);

		out = call.execute(in);
		lista = (List<Delivery>) out.get("a_cursor");
		 
		return lista;
	}
	
	@Override
	public BigDecimal crearGrupoCargaDelivery() { 
		
		logger.info("DAO DeliveryDaoImp crearGrupoCargaDelivery");
		
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
		
		logger.info("DAO DeliveryDaoImp valCourierDelivery");
		
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
	 	
	@Override
	public void mntArchivo(Archivo param) { 
		
		logger.info("DAO DeliveryDaoImp mntArchivo");
		
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		Integer idarchivo = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
				resources.getString(ConstantsProperties.PQ_DEL_COURIER), 
				"sp_mnt_archivo");
		
		  JdbcHelper.setInOutParameter(call, in, "a_idarchivo"      , 	Types.INTEGER, param.getIdarchivo());  	  
		  JdbcHelper.setInParameter(call, in, "a_idtipoarchivo" , 	Types.INTEGER, param.getIdtipoarchivo()); 	  
		  JdbcHelper.setInParameter(call, in, "a_fecentrega"      , 	Types.DATE, param.getFecentrega()); 
		  JdbcHelper.setInParameter(call, in, "a_idcourier"     , 	Types.INTEGER, param.getIdcourier());  
		  JdbcHelper.setInParameter(call, in, "a_usuario"     , 	Types.VARCHAR, param.getUsuario());  

		out = call.execute(in);
		
		idarchivo = JdbcHelper.getOutResult(out, "a_idarchivo", Integer.class);
		param.setIdarchivo(idarchivo); 
	}
	
	@SuppressWarnings("unchecked")
	public List<ArchivoPDF> getArchivoPDF( ArchivoPDF archivoPDF){
		
		logger.info("DAO DeliveryDaoImp getArchivoPDF");
		
		System.out.println("INI DAO: Ejecutando metodo getArchivoPDF");
		
		List<ArchivoPDF> rcer = null;
		MapSqlParameterSource in = null;
		
		SimpleJdbcCall call= null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY), 
								resources.getString(ConstantsProperties.PQ_DEL_SERVICIO), 
								"sp_obt_delivery_archivo_pdf");

		JdbcHelper.setInParameter(call, in, "a_iddelivery", OracleTypes.NUMERIC, archivoPDF.getCodentrega());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, ArchivoPDF.class);
		
		
		out = call.execute(in);
		
		rcer = (List<ArchivoPDF>) out.get("a_cursor");
		
		System.out.println("FIN DAO: Ejecutando metodo getArchivoPDF");
		logger.debug("FIN DAO: Ejecutando metodo getArchivoPDF");
		
		return rcer;
	}
}
