package bbva.delivery.tarjetas.perfil.dao.imp;
 
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

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

	@Override
	public List<Perfil> lstPerfiles(Perfil perfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> obtPerfil(Perfil perfil) {
		// TODO Auto-generated method stub
		return null;
	}


}