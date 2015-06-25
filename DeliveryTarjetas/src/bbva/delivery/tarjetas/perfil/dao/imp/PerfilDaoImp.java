package bbva.delivery.tarjetas.perfil.dao.imp;
 
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
import bbva.delivery.tarjetas.courier.dao.CourierDao;
import bbva.delivery.tarjetas.perfil.bean.Perfil;
import bbva.delivery.tarjetas.perfil.dao.PerfilDao;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
             
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
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> lstPerfiles(Perfil perfil) {
		List<Perfil> lista = null; 
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(getJdbcTemplate(),
				resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
				resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
				"sp_lst_perfil");
 
		 
		JdbcHelper.setOutParameter(call, "a_cursor", 		OracleTypes.CURSOR, Perfil.class);
		 
		out = call.execute(in);
		lista = (List<Perfil>) out.get("a_cursor");
		  
		return lista;
	}

	@Override
	public List<Perfil> obtPerfil(Perfil perfil) {
		// TODO Auto-generated method stub
		return null;
	}


}