package bbva.delivery.tarjetas.perfil.dao.imp;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.commons.Constants;
import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.PuntoContacto;
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
	
	public void regPuntocontactoUsuarioweb(PuntoContacto puntoContacto) throws Exception{
		  
		SimpleJdbcCall call = null;
		  SqlParameterSource in = null;
		  Map<String, Object> out = null;
		  
		  
		  logger.info("Ejecutando metodo regPuntocontactoUsuarioweb usoptocontacto: "+puntoContacto.getCadidpusopuntocontacto());
		  System.out.println("Ejecutando metodo regPuntocontactoUsuarioweb");
		  
		  call = new SimpleJdbcCall(getJdbcTemplate()).
				    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
				    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_PUNTOCONTACTO)).
					withProcedureName("SP_MNT_DIRECCION").
					withoutProcedureColumnMetaDataAccess().
					declareParameters(
							new SqlInOutParameter("a_idepuntocontacto", Types.INTEGER),
							new SqlParameter("a_idedistrito", Types.INTEGER),
							new SqlParameter("a_idptipovia", Types.VARCHAR),
							new SqlParameter("a_nomvia", Types.VARCHAR),
							new SqlParameter("a_numcasa", Types.VARCHAR),
							new SqlParameter("a_idptipointerior", Types.INTEGER),
							new SqlParameter("a_interior", Types.VARCHAR),
							new SqlParameter("a_manzana", Types.VARCHAR),
							new SqlParameter("a_lote", Types.VARCHAR),
							new SqlParameter("a_kilometro", Types.VARCHAR),
							new SqlParameter("a_idtipoagrupacion", Types.INTEGER),
							new SqlParameter("a_dscagrupgeo", Types.VARCHAR),
							new SqlParameter("a_idpsubdivision", Types.INTEGER),
							new SqlParameter("a_dscsubdivision", Types.VARCHAR),
							new SqlParameter("a_referencia", Types.VARCHAR),
							new SqlParameter("a_usucreacion", Types.VARCHAR),
							new SqlParameter("a_idetercero", Types.VARCHAR),
							new SqlParameter("a_idevia", Types.INTEGER),
							new SqlParameter("a_cadidpusoptocontac", Types.VARCHAR),
							new SqlParameter("a_idelugar", Types.INTEGER),
							new SqlParameter("a_stspuntocontacto", Types.VARCHAR),
							new SqlParameter("a_indprincipal", Types.VARCHAR),
							new SqlParameter("a_coordenadax", Types.VARCHAR),
							new SqlParameter("a_coordenaday", Types.VARCHAR)
					);
		  
		  in = new MapSqlParameterSource().
					addValue("a_idepuntocontacto", puntoContacto.getIdepuntocontacto()/*.equals("")?null:mntRegUser.getIdepuntocontacto()*/).
					addValue("a_idedistrito", puntoContacto.getIdedistrito()).
					addValue("a_idptipovia", puntoContacto.getIdptipovia()).
					addValue("a_nomvia", puntoContacto.getDireccioncompleta()).
					addValue("a_numcasa", null).
					addValue("a_idptipointerior", null).
					addValue("a_interior", null).
					addValue("a_manzana", null).
					addValue("a_lote", null).
					addValue("a_kilometro", null).
					addValue("a_idtipoagrupacion", null).
					addValue("a_dscagrupgeo", null).
					addValue("a_idpsubdivision", null).
					addValue("a_dscsubdivision", null).
					addValue("a_referencia", null).
					addValue("a_usucreacion", "KCEVALLOSM").
					addValue("a_idetercero", puntoContacto.getIdetercero()).
					addValue("a_idevia", null).
					addValue("a_cadidpusoptocontac", puntoContacto.getCadidpusopuntocontacto()).
					addValue("a_idelugar", null).
					addValue("a_stspuntocontacto", Constants.ESTADO_USUARIO_ACT).
					addValue("a_indprincipal", Constants.IND_DIR_PRINCIPAL_NO).
					addValue("a_coordenadax", null).
					addValue("a_coordenaday", null);
		  
		  out = call.execute(in);
		  puntoContacto.setIdepuntocontacto(Integer.parseInt(out.get("a_idepuntocontacto").toString()));
		  logger.info("Finalizando ejecucion de metodo regPuntocontactoUsuarioweb: "+puntoContacto.getIdepuntocontacto());
		  System.out.println("Finalizando ejecucion de metodo regPuntocontactoUsuarioweb");
		  
	}
	
	public void regMediocontactoUsuarioweb(MedioContacto medioContacto) {
		  SimpleJdbcCall call 		= null;
		  SqlParameterSource in 	= null;
		  Map<String, Object> out 	= null;
		  
		  String val_medcont_telf 	= "";
		  String val_medcont_mail 	= "";
		  
		  if(medioContacto.getIdptipmediocontac().equals(Constants.TMC_TELEF))
			  val_medcont_telf = medioContacto.getNumlocal();
		  
		  if(medioContacto.getIdptipmediocontac().equals(Constants.TMC_EMAIL))
				  val_medcont_mail = medioContacto.getEmail();
		  
		  logger.info("Ejecutando metodo regMediocontactoUsuarioweb usoptocontacto: "+medioContacto.getCadidpusopuntocontacto());
		  System.out.println("Ejecutando metodo regMediocontactoUsuarioweb");
		  
		  call = new SimpleJdbcCall(getJdbcTemplate()).
				    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
				    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_PUNTOCONTACTO)).
					withProcedureName("SP_MNT_MEDIOCONTACTO_ORG").
					withoutProcedureColumnMetaDataAccess().
					declareParameters(
							new SqlInOutParameter("a_idepuntocontacto", Types.INTEGER),
							new SqlParameter("a_codigotelefonopais", Types.VARCHAR),
							new SqlParameter("a_codigoarea", Types.VARCHAR),
							new SqlParameter("a_idptipotelefono", Types.INTEGER),
							new SqlParameter("a_numlocal", Types.VARCHAR),
							new SqlParameter("a_anexo", Types.VARCHAR),
							new SqlParameter("a_indmovil", Types.VARCHAR),
							new SqlParameter("a_usucreacion", Types.VARCHAR),
							new SqlParameter("a_idptipdireelec", Types.VARCHAR),
							new SqlParameter("a_email", Types.VARCHAR),
							new SqlParameter("a_idptipmediocontac", Types.VARCHAR),
							new SqlParameter("a_idetercero", Types.VARCHAR),
							new SqlParameter("a_cadidpusoptocontac", Types.VARCHAR),
							new SqlParameter("a_direccionweb", Types.VARCHAR),
							new SqlParameter("a_stspuntocontacto", Types.VARCHAR),
							new SqlParameter("a_idelugar", Types.INTEGER)
					);
			  
		  in = new MapSqlParameterSource().
					addValue("a_idepuntocontacto", medioContacto.getIdepuntocontacto()).
					addValue("a_codigotelefonopais", null).
					addValue("a_codigoarea", null).
					addValue("a_idptipotelefono", null).
					addValue("a_numlocal", val_medcont_telf).
					addValue("a_anexo", null).
					addValue("a_indmovil", null).
					addValue("a_usucreacion", medioContacto.getUsucreacion()).
					addValue("a_idptipdireelec", null).
					addValue("a_email", val_medcont_mail).
					addValue("a_idptipmediocontac", medioContacto.getIdptipmediocontac()).
					addValue("a_idetercero", medioContacto.getIdetercero()).
					addValue("a_cadidpusoptocontac", medioContacto.getCadidpusopuntocontacto()).
					addValue("a_direccionweb", null).
					addValue("a_stspuntocontacto", Constants.ESTADO_USUARIO_ACT).
					addValue("a_idelugar", null);
			  		  
		  out = call.execute(in);
			  
		  medioContacto.setIdepuntocontacto(Integer.parseInt(out.get("a_idepuntocontacto").toString()));
		  logger.info("Finalizando ejecucion de metodo regMediocontactoUsuarioweb: "+medioContacto.getIdepuntocontacto());
		  System.out.println("Finalizando ejecucion de metodo regMediocontactoUsuarioweb");
		  
	}
	
	@SuppressWarnings("unchecked")	
	public void obtPtocontactoUsuarioweb(PuntoContacto puntoContacto) {
	
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out 	= null;
		  
		logger.info("Ejecutando metodo obtPtocontactoUsuarioweb");
		System.out.println("Ejecutando metodo obtPtocontactoUsuarioweb");
		call = new SimpleJdbcCall(getJdbcTemplate()).
				    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
				    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
					withProcedureName("SP_OBT_PTOCONTACTO_USRWEB").
					withoutProcedureColumnMetaDataAccess().
					declareParameters(
							new SqlParameter("a_idepuntocontacto",Types.INTEGER),
							new SqlOutParameter("a_cursor", OracleTypes.CURSOR,
							new BeanPropertyRowMapper<PuntoContacto>(PuntoContacto.class))
					);
		 
		in = new MapSqlParameterSource().
					addValue("a_idepuntocontacto", puntoContacto.getIdepuntocontacto());
		 
		out = call.execute(in);
		 
		puntoContacto.setCursor((List<PuntoContacto>) out.get("a_cursor"));
		logger.info("Finalizando ejecucion de metodo obtPtocontactoUsuarioweb");
		System.out.println("Finalizando ejecucion de metodo obtPtocontactoUsuarioweb");
	}
	
	@SuppressWarnings("unchecked")
	public void obtMediocontactoUsuarioweb(MedioContacto medioContacto) {
	
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out 	= null;
		  
		logger.info("Ejecutando metodo obtMediocontactoUsuarioweb");
		System.out.println("Ejecutando metodo obtMediocontactoUsuarioweb");
		call = new SimpleJdbcCall(getJdbcTemplate()).
				    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
				    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_PUNTOCONTACTO)).
				    withProcedureName("SP_OBT_MEDIOCONTACTO").
				    withoutProcedureColumnMetaDataAccess().
				    declareParameters(
							new SqlParameter("a_idepuntocontacto",Types.INTEGER),
							new SqlOutParameter("a_cursor", OracleTypes.CURSOR,
							new BeanPropertyRowMapper<MedioContacto>(MedioContacto.class))
					);
		 
		in = new MapSqlParameterSource().
					addValue("a_idepuntocontacto", medioContacto.getIdepuntocontacto());
		 
		out = call.execute(in);
		 
		medioContacto.setCursor((List<MedioContacto>) out.get("a_cursor"));
		logger.info("Finalizando ejecucion de metodo obtMediocontactoUsuarioweb");
		System.out.println("Finalizando ejecucion de metodo obtMediocontactoUsuarioweb");
	}
	
	@SuppressWarnings("unchecked")
	public void obtMediocontactoSecUsuarioweb(MedioContacto medioContacto) {
	
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out 	= null;
		  
		logger.info("Ejecutando metodo obtMediocontactoSecUsuarioweb");
		System.out.println("Ejecutando metodo obtMediocontactoSecUsuarioweb");
		call = new SimpleJdbcCall(getJdbcTemplate()).
				    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
				    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
				    withProcedureName("sp_obt_mediocontacto_cliente").
				    withoutProcedureColumnMetaDataAccess().
				    declareParameters(
							new SqlParameter("a_idetercero",Types.INTEGER),
							new SqlParameter("a_ideusomediocontacto",Types.INTEGER),
							new SqlParameter("a_tipomediocontacto",Types.VARCHAR),
							new SqlOutParameter("a_cursor", OracleTypes.CURSOR,
							new BeanPropertyRowMapper<MedioContacto>(MedioContacto.class))
					);
		 
		in = new MapSqlParameterSource().
					addValue("a_idetercero", medioContacto.getIdetercero()).
					addValue("a_ideusomediocontacto", medioContacto.getCadidpusopuntocontacto()).
					addValue("a_tipomediocontacto", medioContacto.getIdptipmediocontac());
		 
		out = call.execute(in);
		 
		medioContacto.setCursor((List<MedioContacto>) out.get("a_cursor"));
		logger.info("Finalizando ejecucion de metodo obtMediocontactoSecUsuarioweb");
		System.out.println("Finalizando ejecucion de metodo obtMediocontactoSecUsuarioweb");
	}
	
	@SuppressWarnings("unchecked")
	public void obtDirCorrespUsuarioweb(PuntoContacto puntoContacto) {
	
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out 	= null;
		  
		
		call = new SimpleJdbcCall(getJdbcTemplate()).
				    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
				    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
				    withProcedureName("sp_obt_dir_corresp_cliente").
				    withoutProcedureColumnMetaDataAccess().
				    declareParameters(
							new SqlParameter("a_idetercero",Types.INTEGER),
							new SqlParameter("a_ideusomediocontacto",Types.INTEGER),
							new SqlOutParameter("a_cursor", OracleTypes.CURSOR,
							new BeanPropertyRowMapper<PuntoContacto>(PuntoContacto.class))
					);
		 
		in = new MapSqlParameterSource().
					addValue("a_idetercero", puntoContacto.getIdetercero()).
					addValue("a_ideusomediocontacto", puntoContacto.getCadidpusopuntocontacto());
		 
		out = call.execute(in);
		 
		puntoContacto.setCursor((List<PuntoContacto>) out.get("a_cursor"));
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
	
	public String regToken(Integer idetercero, String token, String fecinivig, String userapp) {
	    
		SimpleJdbcCall call 		= null;
		SqlParameterSource in 		= null;
		Map<String, Object> out 	= null;
		String result				= "";
		
		
		call = new SimpleJdbcCall(getJdbcTemplate()).
			    withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
			    withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
			    withProcedureName("sp_mnt_token_portal").
			    withoutProcedureColumnMetaDataAccess().
			    declareParameters(
						new SqlParameter("a_idetercero",Types.INTEGER),
						new SqlParameter("a_token", OracleTypes.VARCHAR),
						new SqlParameter("a_fecinivig",Types.VARCHAR),
						new SqlParameter("a_appuser",Types.VARCHAR),
						new SqlOutParameter("a_result",Types.VARCHAR)
				);
			
		in = new MapSqlParameterSource().
				addValue("a_idetercero", idetercero).
				addValue("a_token", token).
				addValue("a_fecinivig", fecinivig==null?"":fecinivig).
				addValue("a_appuser", userapp==null?"":userapp);
	 
		out = call.execute(in);
		
		result = (String) out.get("a_result");
		
		return result;	
	}
	
	@SuppressWarnings("unused")
	public Integer obtIdeterceroxToken(String token) {
		
		SimpleJdbcCall call = null;
		SqlParameterSource in = null;
		Integer idetercero = null;
		
		call = new SimpleJdbcCall(getJdbcTemplate()).
			withSchemaName(resources.getString(ConstantsProperties.OWNER_ESQUEMA_TERCERO)).
			withCatalogName(resources.getString(ConstantsProperties.PQ_TERCERO_TERCERO)).
			withFunctionName("fn_obt_ideterceroxtoken").
			withoutProcedureColumnMetaDataAccess().
			declareParameters(
					new SqlOutParameter("return", Types.INTEGER),
					new SqlParameter("a_token", OracleTypes.VARCHAR)
			);
		
		  in = new MapSqlParameterSource().
					addValue("a_token", token);
		  
		  return idetercero = call.executeFunction(Integer.class, in);
		  //mntRegUser.setIdetercero(Integer.parseInt(call.executeFunction(String.class, in)));
	}
}

