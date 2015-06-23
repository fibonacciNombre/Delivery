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


import bbva.delivery.tarjetas.bean.CargaEntregaTarjeta;
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
	public void cargarEntregaTarjeta(CargaEntregaTarjeta param) { 
		
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		BigDecimal idecarga = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_COMUNES), 
				resources.getString(ConstantsProperties.PACKAGE_CARGA_TARJETAS), 
				"sp_carga_entregatarjeta");
		
		JdbcHelper.setInOutParameter(call, in, "a_idecarga", 	Types.NUMERIC, param.getIdecarga()); 
		JdbcHelper.setInParameter(call, in, "a_numerotarjeta", 	Types.VARCHAR, param.getNumerotarjeta());
		JdbcHelper.setInParameter(call, in, "a_ultimosdigitos", Types.VARCHAR, param.getUltimosdigitos());
		JdbcHelper.setInParameter(call, in, "a_dnicliente", 	Types.VARCHAR, param.getDnicliente());
		JdbcHelper.setInParameter(call, in, "a_nomcliente", 	Types.VARCHAR, param.getNomcliente());
		JdbcHelper.setInParameter(call, in, "a_loccliente", 	Types.VARCHAR, param.getLoccliente());
		JdbcHelper.setInParameter(call, in, "a_dircliente", 	Types.VARCHAR, param.getDircliente());
		JdbcHelper.setInParameter(call, in, "a_distcliente", 	Types.VARCHAR, param.getDistcliente());
		JdbcHelper.setInParameter(call, in, "a_tipoprod", 		Types.VARCHAR, param.getTipoprod());
		JdbcHelper.setInParameter(call, in, "a_subtipoprod", 	Types.VARCHAR, param.getSubtipoprod()); 
		JdbcHelper.setInParameter(call, in, "a_montocred", 		Types.NUMERIC, param.getMontocred()); 
		JdbcHelper.setInParameter(call, in, "a_ubientrega", 	Types.VARCHAR, param.getUbientrega());
		JdbcHelper.setInParameter(call, in, "a_latoficina", 	Types.NUMERIC, param.getLatoficina());
		JdbcHelper.setInParameter(call, in, "a_lngoficina", 	Types.NUMERIC, param.getLngoficina());
		JdbcHelper.setInParameter(call, in, "a_latdomicilio", 	Types.NUMERIC, param.getLatdomicilio());
		JdbcHelper.setInParameter(call, in, "a_lngdomicilio", 	Types.NUMERIC, param.getLngdomicilio());
		JdbcHelper.setInParameter(call, in, "a_fecentregaprog", Types.VARCHAR, param.getFecentregaprog());
		JdbcHelper.setInParameter(call, in, "a_horentregaprog", Types.VARCHAR, param.getHorentregaprog());
		JdbcHelper.setInParameter(call, in, "a_direntrega", 	Types.VARCHAR, param.getDirentrega());
		JdbcHelper.setInParameter(call, in, "a_distentrega", 	Types.VARCHAR, param.getDistentrega());
		JdbcHelper.setInParameter(call, in, "a_proventrega", 	Types.VARCHAR, param.getProventrega());
		
		JdbcHelper.setInParameter(call, in, "a_depentrega", 	Types.VARCHAR, param.getDepentrega());
		JdbcHelper.setInParameter(call, in, "a_courierasoc", 	Types.VARCHAR, param.getCourierasoc());
		JdbcHelper.setInParameter(call, in, "a_dnicourier", 	Types.VARCHAR, param.getDnicourier()); 
		JdbcHelper.setInParameter(call, in, "a_grupocarga", 	Types.VARCHAR, param.getGrupocarga()); 
		
		out = call.execute(in);
		
		idecarga = JdbcHelper.getOutResult(out, "a_idecarga", BigDecimal.class);
		param.setIdecarga(idecarga); 

	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<CargaEntregaTarjeta> lstCargarEntregaTarjeta(CargaEntregaTarjeta param) {
		 
		
		List<CargaEntregaTarjeta> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_COMUNES),
				resources.getString(ConstantsProperties.PACKAGE_CARGA_TARJETAS),
				"sp_lst_carga_entregatarjeta");
 
		JdbcHelper.setInParameter(call, in, "a_dnicliente", OracleTypes.VARCHAR, param.getDnicliente());
		JdbcHelper.setInParameter(call, in, "a_nomcliente", OracleTypes.VARCHAR, param.getNomcliente());
		JdbcHelper.setInParameter(call, in, "a_dnicourier", OracleTypes.VARCHAR, param.getDnicourier());
		JdbcHelper.setInParameter(call, in, "a_courierasoc", OracleTypes.VARCHAR, param.getCourierasoc());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, CargaEntregaTarjeta.class);
		

		out = call.execute(in);
		lista = (List<CargaEntregaTarjeta>) out.get("a_cursor");
		 
		 
		return lista;
	}
	
	@Override
	public BigDecimal crearGrupoCarga() { 
		
		SimpleJdbcCall call = null;
		MapSqlParameterSource in = null;
		Map<String, Object> out = null; 
		in = new MapSqlParameterSource(); 
		BigDecimal grupocarga = null;
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(), 
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_COMUNES), 
				resources.getString(ConstantsProperties.PACKAGE_CARGA_TARJETAS), 
				"sp_crear_grupocarga");
		
		JdbcHelper.setInOutParameter(call, in, "a_grupocarga", 	Types.NUMERIC, grupocarga); 
	 
		
		out = call.execute(in);
		
		grupocarga = JdbcHelper.getOutResult(out, "a_grupocarga", BigDecimal.class);
		return grupocarga;

	}
}

