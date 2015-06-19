package bbva.delivery.tarjetas.perfil.dao.imp;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;
import bbva.delivery.tarjetas.perfil.dao.PerfilDao;

@Repository("perfilDao")
public class PerfilDaoImp extends JdbcDaoBase implements PerfilDao {

	private static PerfilDaoImp instance;
	
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

	public void obtEstadoUsuarioWeb(UsuarioWeb usuarioWeb) {
		 
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out		= null;

		logger.info("Ejecutando metodo obtEstadoUsuarioWeb");
		System.out.println("Ejecutando metodo obtEstadoUsuarioWeb");    
		call = new SimpleJdbcCall(getJdbcTemplate()).
					withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
					withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
					withProcedureName("SP_OBT_STS_USRWEB").
					withoutProcedureColumnMetaDataAccess().
					declareParameters(new SqlParameter("a_idetercero", OracleTypes.VARCHAR),
									  new SqlOutParameter("a_stsbloq", OracleTypes.VARCHAR),
									  new SqlOutParameter("a_num_visitas", Types.INTEGER),
									  new SqlOutParameter("a_idpstsusuario", Types.VARCHAR),
									  new SqlOutParameter("a_num_intentos", Types.INTEGER));
		  
		  in = new MapSqlParameterSource().
					addValue("a_idetercero", usuarioWeb.getIdetercero());
		  		  
		  out = call.execute(in);      
	
		  usuarioWeb.setIndbloqueo((String) out.get("a_stsbloq"));
		  usuarioWeb.setNumvisitas((Integer) out.get("a_num_visitas"));
		  usuarioWeb.setIdpstsusuario((String) out.get("a_idpstsusuario"));
		  usuarioWeb.setNumintentos((Integer) out.get("a_num_intentos"));
		  
		  logger.info("Ejecutando metodo obtEstadoUsuarioWeb");
		  System.out.println("Ejecutando metodo obtEstadoUsuarioWeb");  
	  }
	
	
	public void mtnEstadoUsuarioWeb(UsuarioWeb terLogIn,String ind) {

		SimpleJdbcCall call 	= null;
		SqlParameterSource in 	= null;
		Map<String, Object> out = null;

		logger.info("Ejecutando metodo mtnEstadoUsuarioWeb");
		System.out.println("Ejecutando metodo mtnEstadoUsuarioWeb");  
		call = new SimpleJdbcCall(getJdbcTemplate()).
				    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
				    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
//				    withCatalogName("PQ_IAA_TERCERO_JLL").
					withProcedureName("SP_MNT_STS_USRWEB").
					declareParameters(new SqlParameter("a_idetercero", Types.VARCHAR),
									  new SqlParameter("a_idpestado", Types.VARCHAR),
									  new SqlParameter("a_ind", Types.VARCHAR),
									  new SqlParameter("a_usuario", Types.VARCHAR),
									  new SqlOutParameter("a_pswd_repos", OracleTypes.VARCHAR)
									  );
		  
		  in = new MapSqlParameterSource().
					addValue("a_idetercero", terLogIn.getIdetercero()).
					addValue("a_idpestado", terLogIn.getIdpstsusuario()==null?"":terLogIn.getIdpstsusuario()).
					addValue("a_ind", ind).
					addValue("a_usuario", terLogIn.getUsuario());
		  		  
		  out = call.execute(in);
		  terLogIn.setPassword((String) out.get("a_pswd_repos"));
		  logger.info("Finalizando ejecucion de metodo mtnEstadoUsuarioWeb");
		  System.out.println("Finalizando ejecucion de metodo mtnEstadoUsuarioWeb");
  }


	
	public void obtIdeterceroUsuarioweb(UsuarioWeb mntRegUser) {
		
		SimpleJdbcCall call = null;
		SqlParameterSource in = null;
		logger.info("Ejecutando metodo obtIdeterceroUsuarioweb");
		System.out.println("Ejecutando metodo obtIdeterceroUsuarioweb");
		call = new SimpleJdbcCall(getJdbcTemplate()).
			withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
			withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
			withFunctionName("fn_dev_idetercero").
			withoutProcedureColumnMetaDataAccess().
			declareParameters(
					new SqlOutParameter("return", Types.VARCHAR),
					new SqlParameter("a_numerodoc", Types.VARCHAR),
					new SqlParameter("a_idptipodocumento", Types.INTEGER)
			);
		
		  in = new MapSqlParameterSource().
					addValue("a_numerodoc", mntRegUser.getNumerodoc()).
					addValue("a_idptipodocumento", mntRegUser.getIdptipodocumento());
		  
		  mntRegUser.setIdetercero(Integer.parseInt(call.executeFunction(String.class, in)));
		  logger.info("Finalizando ejecucion de metodo obtIdeterceroUsuarioweb idetercero: "+mntRegUser.getIdetercero());
		  System.out.println("Finalizando ejecucion de metodo obtIdeterceroUsuarioweb");
	}
	
	public void obtCodexternoUsuarioweb(UsuarioWeb mntRegUser) {
		
		SimpleJdbcCall call = null;
		SqlParameterSource in = null;
		
		logger.info("Ejecutando metodo obtCodexternoUsuarioweb");
		System.out.println("Ejecutando metodo obtCodexternoUsuarioweb");
		call = new SimpleJdbcCall(getJdbcTemplate()).
			withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
			withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
			withFunctionName("fn_obt_codexterno").
			withoutProcedureColumnMetaDataAccess().
			declareParameters(
					new SqlOutParameter("return", Types.VARCHAR),
					new SqlParameter("a_idetercero", Types.INTEGER)
			);
		
		  in = new MapSqlParameterSource().
					addValue("a_idetercero", mntRegUser.getIdetercero());
		  
		  mntRegUser.setCodexterno(call.executeFunction(String.class, in));
		  logger.info("Finalizando ejecucion de metodo obtCodexternoUsuarioweb codexterno: "+mntRegUser.getCodexterno());
		  System.out.println("Finalizando ejecucion de metodo obtCodexternoUsuarioweb");
	}
	
	public void registrarUsuarioweb(UsuarioWeb usuarioWeb) {

		SimpleJdbcCall call = null;
		SqlParameterSource in = null;
		Map<String, Object> out = null;
		logger.info("Ejecutando metodo registrarUsuarioweb");
		System.out.println("Ejecutando metodo registrarUsuarioweb");
		call = new SimpleJdbcCall(getJdbcTemplate()).
						withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
						withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
						withProcedureName("SP_REG_USRWEB").
						withoutProcedureColumnMetaDataAccess().
						declareParameters(
								new SqlParameter("a_idetercero", Types.INTEGER),
								new SqlParameter("a_idepuntocontactodir", Types.INTEGER),
								new SqlParameter("a_idepuntocontactotelf", Types.INTEGER),
								new SqlParameter("a_idepuntocontactomail", Types.INTEGER),
								new SqlParameter("a_usuario", Types.VARCHAR),
								new SqlParameter("a_idptipodocumento", Types.VARCHAR),
								new SqlParameter("a_numerodoc", Types.VARCHAR),
								new SqlOutParameter("a_pswdtemp", OracleTypes.VARCHAR)
						);
			  
		in = new MapSqlParameterSource().
						addValue("a_idetercero", usuarioWeb.getIdetercero()).
						addValue("a_idepuntocontactodir", usuarioWeb.getIdepuntocontactodir()==null?null:Integer.parseInt(usuarioWeb.getIdepuntocontactodir())).
						addValue("a_idepuntocontactotelf", usuarioWeb.getIdepuntocontactotelf()==null?null:Integer.parseInt(usuarioWeb.getIdepuntocontactotelf())).
						addValue("a_idepuntocontactomail", usuarioWeb.getIdepuntocontactomail()==null?null:Integer.parseInt(usuarioWeb.getIdepuntocontactomail())).
						addValue("a_usuario", usuarioWeb.getUsuario()).
						addValue("a_idptipodocumento", usuarioWeb.getIdptipodocumento()).
						addValue("a_numerodoc", usuarioWeb.getNumerodoc());
			  		  
		out = call.execute(in);
		usuarioWeb.setPassword((String) out.get("a_pswdtemp"));
		logger.info("Finalizando ejecucion de metodo registrarUsuarioweb");
		System.out.println("Finalizando ejecucion de metodo registrarUsuarioweb");
	}
	
	@SuppressWarnings("unchecked")
	public void obtUsuarioweb(UsuarioWeb usuarioWeb) {
		SimpleJdbcCall call = null;
		  SqlParameterSource in = null;
		  Map<String, Object> out = null;
		  
		  logger.info("Ejecutando metodo obtUsuarioweb");
		  System.out.println("Ejecutando metodo obtUsuarioweb");
		  call = new SimpleJdbcCall(getJdbcTemplate()).
					withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
					withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
					withProcedureName("SP_OBT_DET_USRWEB").
					withoutProcedureColumnMetaDataAccess().
					declareParameters(
							new SqlParameter("a_idetercero",Types.INTEGER),
							new SqlOutParameter("a_cursor", OracleTypes.CURSOR,
							new BeanPropertyRowMapper<UsuarioWeb>(UsuarioWeb.class))
					);
					
		  in = new MapSqlParameterSource().
					addValue("a_idetercero", usuarioWeb.getIdetercero());
					  
		  out = call.execute(in);
		  
		  usuarioWeb.setCursor((List<UsuarioWeb>) out.get("a_cursor"));
		  logger.info("Finalizando ejecucion de metodo obtUsuarioweb");
		  System.out.println("Finalizando ejecucion de metodo obtUsuarioweb");
	}
	
	@SuppressWarnings("unchecked")
	public void obtPersona(Persona persona) {
	    
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out 	= null;
		
		logger.info("Ejecutando metodo obtPersona");
		System.out.println("Finalizando metodo obtPersona");
		call = new SimpleJdbcCall(getJdbcTemplate()).
			    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
			    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_PERSONA)).
			    withProcedureName("SP_OBT_PERSONA").
			    withoutProcedureColumnMetaDataAccess().
			    declareParameters(
						new SqlParameter("a_idetercero",Types.INTEGER),
						new SqlOutParameter("a_cursor", OracleTypes.CURSOR,
						new BeanPropertyRowMapper<Persona>(Persona.class))
				);
			
		in = new MapSqlParameterSource().
				addValue("a_idetercero", persona.getIdetercero());
	 
		out = call.execute(in);
    	
		persona.setCursor((List<Persona>) out.get("a_cursor"));
		logger.info("Finalizando ejecucion de metodo obtPersona");
		System.out.println("Finalizando ejecucion de metodo obtPersona");
	}

	@Override
	public boolean validarContrsena(UsuarioWeb usuarioWeb) {
		// TODO Auto-generated method stub
		return false;
	}
}

