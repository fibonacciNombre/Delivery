package bbva.delivery.tarjetas.comun.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.comun.bean.ArchivoBlob;
import bbva.delivery.tarjetas.comun.bean.Constante;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.dao.ComunDao;

@Repository("comunDao")
public class ComunDaoImp extends JdbcDaoBase implements ComunDao {

	public Constante obtenerConstante(String ideConstante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parametro obtenerParametro(Parametro parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parametro> listarParametro(Parametro parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void obtenerListaParametros(Parametro param) {
		// TODO Auto-generated method stub
		
	}

	public void mntArchivoblob(ArchivoBlob param) {
		// TODO Auto-generated method stub
		
	}
}
