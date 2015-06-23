package bbva.delivery.tarjetas.usuario.dao;

import java.util.List;

import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.usuario.bean.UsuarioWeb;

public interface UsuarioDao {
	
	void regUsuarioWeb(UsuarioWeb usuarioWeb);
	
	void mntUsuarioWeb(UsuarioWeb usuarioWeb);
	
	List<Courier> lstUsuariosWeb(String estado);
	
	UsuarioWeb obtDetalleUsuarioWeb(UsuarioWeb usuarioWeb);
		
	boolean validarContrasena(UsuarioWeb usuarioWeb);
	
	void actContrasena(UsuarioWeb usuarioWeb);
}
