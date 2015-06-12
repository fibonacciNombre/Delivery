package bbva.delivery.tarjetas.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.tarjetas.dao.DeliveryDao;
import bbva.delivery.tarjetas.service.DeliveryService;

@Service("portalWebService")
@Transactional(propagation=Propagation.SUPPORTS)
public class DeliveryServiceImp implements DeliveryService {

	@Autowired
	private DeliveryDao portalWebDao;

	public void test() {
		// TODO Auto-generated method stub
		System.out.println("service ok");
		
		portalWebDao.test();
	}

}