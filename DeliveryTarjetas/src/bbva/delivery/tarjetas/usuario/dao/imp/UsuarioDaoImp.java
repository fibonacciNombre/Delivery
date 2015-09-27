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
import bbva.delivery.tarjetas.usuario.bean.Oficina;
import bbva.delivery.tarjetas.usuario.bean.Subgerente;
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
	
	@Override
	public void mntUsuarioGerente(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp mntUsuarioGerente");
		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_mnt_usuariogerente");

		JdbcHelper.setInOutParameter(call, in, "a_idusuario", 	Types.INTEGER, usuario.getIdusuario());
		JdbcHelper.setInParameter(call, in, "a_idtercero", 		OracleTypes.INTEGER, usuario.getIdtercero());
		JdbcHelper.setInParameter(call, in, "a_idperfil",		OracleTypes.INTEGER, usuario.getIdperfil());
		JdbcHelper.setInParameter(call, in, "a_idpestado", 		OracleTypes.INTEGER, usuario.getIdpestado());
		JdbcHelper.setInParameter(call, in, "a_codusuario", 	OracleTypes.VARCHAR, usuario.getCodusuario());
		JdbcHelper.setInParameter(call, in, "a_historial", 		OracleTypes.VARCHAR, usuario.getHistorial());
		JdbcHelper.setInParameter(call, in, "a_comentario", 	OracleTypes.VARCHAR, usuario.getComentario());
		JdbcHelper.setInParameter(call, in, "a_indrnvcontrasena", 	OracleTypes.VARCHAR, usuario.getIndrnvcontrasena());
		JdbcHelper.setInParameter(call, in, "a_usuario", 		OracleTypes.VARCHAR, usuario.getUsumodificacion());
		JdbcHelper.setInParameter(call, in, "a_idoficina", 		OracleTypes.INTEGER, usuario.getIdoficina());

		out = call.execute(in);

		usuario.setIdusuario( (Integer)out.get("a_idusuario") ); 
	}
	@Override
	public void mntOficina(Oficina oficina) {
		
		logger.info("DAO UsuarioDaoImp mntOficina");
		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_mnt_oficina");

		JdbcHelper.setInOutParameter(call, in, "a_idoficina", 	Types.INTEGER, oficina.getIdoficina());
		JdbcHelper.setInParameter(call, in, "a_codoficina", 	OracleTypes.INTEGER, oficina.getCodoficina());
		JdbcHelper.setInParameter(call, in, "a_oficina",		OracleTypes.VARCHAR, oficina.getOficina());
		JdbcHelper.setInParameter(call, in, "a_categoria", 		OracleTypes.VARCHAR, oficina.getCategoria());
		JdbcHelper.setInParameter(call, in, "a_ubigeo", 		OracleTypes.VARCHAR, oficina.getUbigeo());
		JdbcHelper.setInParameter(call, in, "a_direccion", 		OracleTypes.VARCHAR, oficina.getDireccion());
		JdbcHelper.setInParameter(call, in, "a_horariolv", 		OracleTypes.VARCHAR, oficina.getHorariolv());
		JdbcHelper.setInParameter(call, in, "a_horarios", 		OracleTypes.VARCHAR, oficina.getHorarios());
		JdbcHelper.setInParameter(call, in, "a_horariodf", 		OracleTypes.VARCHAR, oficina.getHorariodf());
		JdbcHelper.setInParameter(call, in, "a_latitud", 		OracleTypes.DECIMAL, oficina.getLatitudofi());
		JdbcHelper.setInParameter(call, in, "a_longitud", 		OracleTypes.DECIMAL, oficina.getLongitudofi());
		JdbcHelper.setInParameter(call, in, "a_usuario", 		OracleTypes.VARCHAR, oficina.getUsumodificacion());

		out = call.execute(in);

		oficina.setIdoficina((Integer)out.get("a_idoficina")); 
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> lstUsuarios2(Usuario usuario) {
		
		logger.info("DAO UsuarioDaoImp lstUsuarios2");
		
		List<Usuario> lista = null;
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_lst_usuario2");

		JdbcHelper.setInParameter(call, in, "a_ubigeo", OracleTypes.VARCHAR, usuario.getUbigeo());
		JdbcHelper.setOutParameter(call,  "a_cursor", OracleTypes.CURSOR, Usuario.class);

		out = call.execute(in);
		lista = (List<Usuario>) out.get("a_cursor");

		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Oficina> lstOficinas(Oficina oficina) {
		
		logger.info("DAO UsuarioDaoImp lstOficinas");
		
		List<Oficina> lista = null;
		MapSqlParameterSource in = null;

		SimpleJdbcCall call = null;
		Map<String, Object> out = null;
		in = new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_lst_oficina");

		JdbcHelper.setInParameter(call, in, "a_codoficina", OracleTypes.INTEGER, oficina.getCodoficina());
		/** ini / mfarfanr / ajustes **/
//		JdbcHelper.setInParameter(call, in, "a_oficina", OracleTypes.VARCHAR, oficina.getOficina());
		if(oficina.getOficina()!=null)
			JdbcHelper.setInParameter(call, in, "a_oficina", OracleTypes.VARCHAR, oficina.getOficina().toUpperCase());
		else
			JdbcHelper.setInParameter(call, in, "a_oficina", OracleTypes.VARCHAR, oficina.getOficina());
		/** fin / mfarfanr / ajustes **/
		JdbcHelper.setInParameter(call, in, "a_ubigeo", OracleTypes.VARCHAR, oficina.getUbigeo());
		JdbcHelper.setOutParameter(call,  "a_cursor", OracleTypes.CURSOR, Oficina.class);

		out = call.execute(in);
		lista = (List<Oficina>) out.get("a_cursor");

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subgerente> lstSubgerentes(Subgerente subgerente) {
		
		logger.info("DAO UsuarioDaoImp lstSubgerentes");
		
		SimpleJdbcCall call 			= null;
		Map<String, Object> out 		= null;
		List<Subgerente> lista				= null;		
		MapSqlParameterSource in 		= new MapSqlParameterSource();
		
		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
							getJdbcTemplate(),
							resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
							resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
							"sp_lst_subgerente");

		JdbcHelper.setInParameter(call, in, "a_ubigeo", 		OracleTypes.VARCHAR, subgerente.getUbigeo());
		JdbcHelper.setInParameter(call, in, "a_idoficina", 		OracleTypes.INTEGER, subgerente.getIdoficina());		
		JdbcHelper.setOutParameter(call,  "a_cursor", 			OracleTypes.CURSOR, Subgerente.class);

		out = call.execute(in);
		lista = (List<Subgerente>) out.get("a_cursor");

		return lista;		
	}
	
	@Override
	public void mntSubgerente(Subgerente subgerente){
		logger.info("DAO UsuarioDaoImp mntSubgerente");
		
		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		MapSqlParameterSource in 	= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_mnt_subgerente");

		JdbcHelper.setInOutParameter(call, in, "a_idsubgerente", 	Types.INTEGER, subgerente.getIdsubgerente());
		JdbcHelper.setInParameter(call, in, "a_idoficina", 				OracleTypes.INTEGER, subgerente.getIdoficina());
		JdbcHelper.setInParameter(call, in, "a_nombre",					OracleTypes.VARCHAR, subgerente.getNombre());
		JdbcHelper.setInParameter(call, in, "a_apellidopaterno", 		OracleTypes.VARCHAR, subgerente.getApellidopaterno());
		JdbcHelper.setInParameter(call, in, "a_apellidomaterno", 		OracleTypes.VARCHAR, subgerente.getApellidomaterno());
		JdbcHelper.setInParameter(call, in, "a_correo", 				OracleTypes.VARCHAR, subgerente.getCorreo());
		JdbcHelper.setInParameter(call, in, "a_estado", 				OracleTypes.INTEGER, subgerente.getEstado());		

		out = call.execute(in);

		subgerente.setIdsubgerente((Integer)out.get("a_idoficina")); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public Oficina obtOficina(Oficina oficina) {
		
		logger.info("DAO UsuarioDaoImp obtOficina");
		
		List<Oficina> lstOficina 	= null;
		Oficina oficinaRs 			= null;
		MapSqlParameterSource in 	= null;

		SimpleJdbcCall call 		= null;
		Map<String, Object> out 	= null;
		in 							= new MapSqlParameterSource();

		call = JdbcHelper.initializeSimpleJdbcCallProcedure(
								getJdbcTemplate(),
								resources.getString(ConstantsProperties.OWNER_ESQUEMA_DELIVERY),
								resources.getString(ConstantsProperties.PQ_DEL_USUARIO),
								"sp_obt_oficina");

		JdbcHelper
				.setInOutParameter(call, in, "a_idoficina", Types.NUMERIC, oficina.getIdoficina());

		JdbcHelper.setOutParameter(call, "a_cursor", OracleTypes.CURSOR,
				Oficina.class);

		out = call.execute(in);

		lstOficina = (List<Oficina>) out.get("a_cursor");

		System.out.println("FIN: Ejecutando metodo obtOficina");

		if(lstOficina.size()>0)
			oficinaRs = lstOficina.get(0);
		else
			oficinaRs = new Oficina();
		
		return oficinaRs;
	}
}