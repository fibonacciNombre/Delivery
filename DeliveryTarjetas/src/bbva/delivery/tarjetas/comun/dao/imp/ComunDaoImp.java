package bbva.delivery.tarjetas.comun.dao.imp;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bbva.delivery.tarjetas.commons.ConstantsProperties;
import bbva.delivery.tarjetas.comun.bean.ArchivoBlob;
import bbva.delivery.tarjetas.comun.bean.Atributo;
import bbva.delivery.tarjetas.comun.bean.CboDepartamento;
import bbva.delivery.tarjetas.comun.bean.CboDistrito;
import bbva.delivery.tarjetas.comun.bean.CboPais;
import bbva.delivery.tarjetas.comun.bean.CboProvincia;
import bbva.delivery.tarjetas.comun.bean.Constante;
import bbva.delivery.tarjetas.comun.bean.ListaParametroCursor;
import bbva.delivery.tarjetas.comun.bean.Parametro;
import bbva.delivery.tarjetas.comun.bean.Valor;
import bbva.delivery.tarjetas.comun.dao.ComunDao;

import com.rimac.sas.utiles.comunes.JdbcHelper;

@Repository("comunDao")
public class ComunDaoImp extends JdbcDaoBase implements ComunDao {
	
	private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");

	@Override
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

	@Override
	public String obtenerEstadoPlan(BigDecimal ideplan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerSinMonedaPlan(BigDecimal ideplan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Valor> listarValoresxAtributoHijo(Atributo atributo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Valor> listarValoresxAtributo(Atributo atributo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mntArchivoblob(ArchivoBlob param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actArchivoblob(ArchivoBlob param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CboDepartamento> cboDepartamento(CboPais param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CboProvincia> cboProvincia(CboDepartamento param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CboDistrito> cboDistrito(CboProvincia param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
