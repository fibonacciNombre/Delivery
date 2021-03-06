package bbva.delivery.tarjetas.usuario.dao.imp;

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

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.usuario.bean.Usuario;
import bbva.delivery.tarjetas.usuario.dao.UsuarioDao;
import bbva.delivery.tarjetas.util.JdbcHelper;

@Repository("usuarioDao")
public class UsuarioDaoImp extends JdbcDaoBase implements UsuarioDao {

	private static UsuarioDaoImp instance;

	private static Logger logger = Logger.getLogger(UsuarioDaoImp.class
			.getName());

	private static final ResourceBundle resources = ResourceBundle
			.getBundle("configuracion");

	public UsuarioDaoImp() {
		super();
	}

	public static UsuarioDaoImp getInstance() {
		if (instance == null) {
			instance = new UsuarioDaoImp();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validarContrasena(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp validarContrasena");
		
		List<Usuario> usr = null;
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
							getJdbcTemplate(),
							resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
							resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
							"sp_val_contrasena");

		JdbcHelper.setInOutParameter(call, in, "a_codusuario", Types.VARCHAR, usuario.getCodusuario());		
		JdbcHelper.setInOutParameter(call, in, "a_contrasena", Types.VARCHAR, usuario.getContrasena());
		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR, Usuario.class);

		out = call.execute(in);

		usr = (List<Usuario>) out.get("a_cursor");

		System.out.println("FIN: Ejecutando metodo obtUsuario");

		return usr.size()>0;
	}

	@Override
	public void mntUsuario(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp mntUsuario");
		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_mnt_usuario");

		JdbcHelper.setInOutParameter(call, in, "a_idusuario", 	Types.INTEGER, usuario.getIdusuario());
		JdbcHelper.setInParameter(call, in, "a_idtercero", 		OracleTypes.INTEGER, usuario.getIdtercero());
		JdbcHelper.setInParameter(call, in, "a_idperfil",		OracleTypes.INTEGER, usuario.getIdperfil());
		JdbcHelper.setInParameter(call, in, "a_idpestado", 		OracleTypes.INTEGER, usuario.getIdpestado());
		JdbcHelper.setInParameter(call, in, "a_codusuario", 	OracleTypes.VARCHAR, usuario.getCodusuario());
		JdbcHelper.setInParameter(call, in, "a_historial", 		OracleTypes.VARCHAR, usuario.getHistorial());
		JdbcHelper.setInParameter(call, in, "a_comentario", 	OracleTypes.VARCHAR, usuario.getComentario());
		JdbcHelper.setInParameter(call, in, "a_indrnvcontrasena", 	OracleTypes.VARCHAR, usuario.getIndrnvcontrasena());
		JdbcHelper.setInParameter(call, in, "a_usuario", 		OracleTypes.VARCHAR, usuario.getUsumodificacion());

		out = call.execute(in);

		usuario.setIdusuario( (Integer)out.get("a_idusuario") ); 
	}

	@SuppressWarnings("unchecked")
	public Usuario obtUsuario(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp obtUsuario");
		
		List<Usuario> usr = null;
		Usuario usrRpta = null;
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_obt_usuario");

		JdbcHelper
				.setInOutParameter(call, in, "a_idusuario", Types.NUMERIC, usuario.getIdusuario());
		JdbcHelper
				.setInOutParameter(call, in, "a_codusuario", Types.VARCHAR, usuario.getCodusuario());

		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR,
				Usuario.class);

		out = call.execute(in);

		usr = (List<Usuario>) out.get("a_cursor");

		System.out.println("FIN: Ejecutando metodo obtUsuario");

		if(usr.size()>0)
			usrRpta = usr.get(0);
		else
			usrRpta = new Usuario();
		
		return usrRpta;
	}

	public Usuario addUsuario(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp addUsuario");
		
		Usuario usr = new Usuario();
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO), 
								"sp_mnt_usuario");

		JdbcHelper.setInOutParameter(call, in, "a_idusuario", Types.NUMERIC, usuario.getIdusuario());
		JdbcHelper.setInParameter(call, in, "a_contrasena", OracleTypes.VARCHAR, usuario.getContrasena());
		JdbcHelper.setInParameter(call, in, "a_idtercero", OracleTypes.NUMERIC, usuario.getIdtercero());
		JdbcHelper.setInParameter(call, in, "a_idperfil", OracleTypes.NUMERIC, usuario.getIdperfil());
		JdbcHelper.setInParameter(call, in, "a_idpestado", OracleTypes.NUMERIC, usuario.getIdpestado());
		JdbcHelper.setInParameter(call, in, "a_codusuario", OracleTypes.VARCHAR, usuario.getCodusuario());
		JdbcHelper.setInParameter(call, in, "a_historial", OracleTypes.VARCHAR, usuario.getHistorial());
		JdbcHelper.setInParameter(call, in, "a_comentario", OracleTypes.VARCHAR, usuario.getComentario());
		JdbcHelper.setInParameter(call, in, "a_usuario", OracleTypes.VARCHAR, usuario.getUsucreacion());

		out = call.execute(in);

		usr.setIdusuario(Integer.parseInt(((BigDecimal) out.get("a_idusuario"))
				.toString()));
		System.out.println("FIN: Ejecutando metodo addUsuario");
		return usr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> lstUsuarios(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp lstUsuarios");
		
		List<Usuario> lista = null;
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_lst_usuario");

		JdbcHelper.setInParameter(call, in, "a_idperfil", OracleTypes.INTEGER, usuario.getIdperfil());
		JdbcHelper.setInParameter(call, in, "a_codusuario", OracleTypes.VARCHAR, usuario.getCodusuario());
		JdbcHelper.setInParameter(call, in, "a_idpestado", OracleTypes.INTEGER, usuario.getIdpestado());
		JdbcHelper.setInParameter(call, in, "a_nrodocumento", OracleTypes.VARCHAR, usuario.getNrodocumento());
		JdbcHelper.setInParameter(call, in, "a_noidperfil", OracleTypes.INTEGER, usuario.getNoidperfil());
		JdbcHelper.setOutParameter(call,  "a_cursor", OracleTypes.CURSOR, Usuario.class);

		out = call.execute(in);
		lista = (List<Usuario>) out.get("a_cursor");

		return lista;
	}

	@Override
	public void mntContrasena(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp mntContrasena");
		
		MapSqlParameterSource in = null;
		SimpleJdbcCall call 	= null;
		
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_mnt_contrasena");

		JdbcHelper.setInOutParameter(call, in, "a_codusuario", Types.VARCHAR, usuario.getCodusuario());
		JdbcHelper.setInOutParameter(call, in, "a_indrnvcontrasena", Types.VARCHAR, usuario.getIndrnvcontrasena());
		JdbcHelper.setInOutParameter(call, in, "a_contrasena", Types.VARCHAR, usuario.getContrasena());

		call.execute(in);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> lstUsuariosWS(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp lstUsuariosWS");
		
		SimpleJdbcCall call 			= null;
		Map<String, Object> out 		= null;
		List<Usuario> lista				= null;		
		MapSqlParameterSource in 		= new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
							getJdbcTemplate(),
							resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
							resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
							"sp_lst_usuario_maestro");

		JdbcHelper.setInParameter(call, in, "a_idperfil", 		OracleTypes.INTEGER, usuario.getIdperfil());
		JdbcHelper.setInParameter(call, in, "a_codusuario", 	OracleTypes.VARCHAR, usuario.getCodusuario());
		JdbcHelper.setInParameter(call, in, "a_idpestado", 		OracleTypes.INTEGER, usuario.getIdpestado());
		JdbcHelper.setOutParameter(call,  "a_cursor", 			OracleTypes.CURSOR, Usuario.class);

		out = call.execute(in);
		lista = (List<Usuario>) out.get("a_cursor");

		return lista;		
	}
}