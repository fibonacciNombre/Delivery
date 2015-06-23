package bbva.delivery.tarjetas.courier.dao.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.courier.dao.CourierDao;
import bbva.delivery.tarjetas.tercero.bean.Tercero;

@Repository("courierDao")
public class CourierDaoImp extends JdbcDaoBase implements CourierDao {

	private static CourierDaoImp instance;
	
	private static Logger logger 				= Logger.getLogger(CourierDaoImp.class.getName());
	
//	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public CourierDaoImp() {
		super();
	}
	
	public static CourierDaoImp getInstance() {
	    if (instance == null) {
		  instance = new CourierDaoImp();
	    }
	    return instance;
	  }

	@Override
	public void regCourier(Courier courier) {
		// TODO Auto-generated method stub
		logger.info("Dao regCourier");
	}

	@Override
	public void mntCourier(Courier courier) {
		// TODO Auto-generated method stub
		logger.info("Dao mntCourier");
	}

	@Override
	public List<Courier> lstCouriers(String estado) {
		// TODO Auto-generated method stub
		logger.info("Dao lstCouriers");
		return null;
	}

	@Override
	public Courier obtDetalleCourier(Courier courier) {
		// TODO Auto-generated method stub
		logger.info("Dao obtDetalleCourier");
		return null;
	}

	@Override
	public List<Tercero> lstTercerosxCourier(Courier courier) {
		// TODO Auto-generated method stub
		logger.info("Dao lstTercerosxCourier");
		return null;
	}
}