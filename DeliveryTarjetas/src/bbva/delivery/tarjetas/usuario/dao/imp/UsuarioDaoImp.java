package bbva.delivery.tarjetas.usuario.dao.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.usuario.bean.UsuarioWeb;
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
	public boolean validarContrasena(UsuarioWeb usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao validarContrasena");
		return false;
	}

	@Override
	public void regUsuarioWeb(UsuarioWeb usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao regUsuarioWeb");
	}

	@Override
	public void mntUsuarioWeb(UsuarioWeb usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao mntUsuarioWeb");
	}

	@Override
	public List<Courier> lstUsuariosWeb(String estado) {
		// TODO Auto-generated method stub
		logger.info("Dao lstUsuariosWeb");
		return null;
	}

	@Override
	public UsuarioWeb obtDetalleUsuarioWeb(UsuarioWeb usuarioWeb) {
		// TODO Auto-generated method stub
		logger.info("Dao obtDetalleUsuarioWeb");
		return null;
	}
}

