package bbva.delivery.tarjetas.dao.imp;

import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.tarjetas.dao.DeliveryDao;

@Repository("portalWebDao")
public class DeliveryDaoImp extends JdbcDaoBase implements DeliveryDao {
	
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("dao ok");

	}	
}

