package bbva.delivery.tarjetas.perfil.dao.imp;
 
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
import bbva.delivery.tarjetas.perfil.bean.Perfil;
import bbva.delivery.tarjetas.perfil.dao.PerfilDao;
import bbva.delivery.tarjetas.util.JdbcHelper;
             
@Repository("perfilDao")
public class PerfilDaoImp extends JdbcDaoBase implements PerfilDao {

	private static PerfilDaoImp instance;
	
	private static Logger logger 				= Logger.getLogger(PerfilDaoImp.class.getName());
	
	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public PerfilDaoImp() {
		super();
	}
	
	public static PerfilDaoImp getInstance() {
	    if (instance == null) {
		  instance = new PerfilDaoImp();
	    }
	    return instance;
	  }

	@Override
	public void mntPerfil(Perfil perfil) {
		logger.info("DAO PerfilDaoImp mntPerfil");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> lstPerfiles(Perfil perfil) {
		
		logger.info("DAO PerfilDaoImp lstPerfiles");
		
		List<Perfil> lista 			= null; 
		SimpleJdbcCall call			= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
					getJdbcTemplate(),
					resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
					resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
					"sp_lst_perfil");
 		
		JdbcHelper.setOutParameter(call, "a_cursor", 		OracleTypes.CURSOR, Perfil.class);
		 
		out = call.execute(in);
		
		lista = (List<Perfil>) out.get("a_cursor");
		  
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Perfil obtPerfil(Perfil perfil) {
		
		logger.info("DAO PerfilDaoImp obtPerfil");
		
		List<Perfil> lista 			= null;
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in	= new MapSqlParameterSource();
		Perfil perfilRpta 			= new Perfil();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
							getJdbcTemplate(),
							resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
							resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
							"sp_obt_perfil");
 		
		JdbcHelper.setInOutParameter(call, in, "a_idperfil", Types.VARCHAR, perfil.getIdperfil());
		JdbcHelper.setOutParameter(call, "a_cursor", 		OracleTypes.CURSOR, Perfil.class);
		 
		out = call.execute(in);
		lista = (List<Perfil>) out.get("a_cursor");
		 
		if(lista.size()>0)
			perfilRpta = lista.get(0);

		return perfilRpta;
	}
}