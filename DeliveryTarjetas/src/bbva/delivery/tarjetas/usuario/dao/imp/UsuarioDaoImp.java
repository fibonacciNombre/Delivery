package bbva.delivery.tarjetas.usuario.dao.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.usuario.bean.Usuario;
import bbva.delivery.tarjetas.usuario.dao.UsuarioDao;

@Repository("usuarioDao")
public class UsuarioDaoImp extends JdbcDaoBase implements UsuarioDao {

	private static UsuarioDaoImp instance;
	
	private static Logger logger 				= Logger.getLogger(UsuarioDaoImp.class.getName());
	
//	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public UsuarioDaoImp() {
		super();
	}
	
	public static UsuarioDaoImp getInstance() {
	    if (instance == null) {
		  instance = new UsuarioDaoImp();
	    }
	    return instance;
	}
	
	@Override
	public boolean validarContrasena(Usuario usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao validarContrasena");
		return false;
	}

	@Override
	public void mntUsuario(Usuario usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao mntUsuarioWeb");
	}

	@Override
	public List<Usuario> lstUsuarios(Usuario usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao lstUsuariosWeb");
		return null;
	}

	@Override
	public Usuario obtDetalleUsuario(Usuario usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao obtDetalleUsuarioWeb");
		return null;
	}

	@Override
	public void actContrasena(Usuario usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao actContrasena");
	}

	@Override
	public List<Usuario> lstUsuarios(Usuario usuarioWeb, Tercero tercero) {
		// TODO Auto-generated method stub
		return null;
	}
}

