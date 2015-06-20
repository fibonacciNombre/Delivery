package bbva.delivery.tarjetas.tercero.dao.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.tercero.dao.TerceroDao;

@Repository("terceroDao")
public class TerceroDaoImp extends JdbcDaoBase implements TerceroDao {

	private static TerceroDaoImp instance;
	
	private static Logger logger 				= Logger.getLogger(TerceroDaoImp.class.getName());
	
//	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public TerceroDaoImp() {
		super();
	}
	
	public static TerceroDaoImp getInstance() {
	    if (instance == null) {
		  instance = new TerceroDaoImp();
	    }
	    return instance;
	  }

	@Override
	public void regTercero(Tercero tercero) {
		// TODO Auto-generated method stub
		logger.info("Dao regTercero");
	}

	@Override
	public void mntTercero(Tercero tercero) {
		// TODO Auto-generated method stub
		logger.info("Dao mntTercero");
	}

	@Override
	public List<Tercero> lstTerceros(String estado) {
		// TODO Auto-generated method stub
		logger.info("Dao lstTercero");
		return null;
	}

	@Override
	public Tercero obtDetalleTercero(Tercero tercero) {
		// TODO Auto-generated method stub
		logger.info("Dao obtDetalleTercero");
		return null;
	}
}

