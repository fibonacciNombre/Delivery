package bbva.delivery.tarjetas.usuario.service;

import java.util.List;

import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.UsuarioWeb;

public interface UsuarioService {

	
	void regUsuarioWeb(UsuarioWeb usuarioWeb);
	
	void mntUsuarioWeb(UsuarioWeb usuarioWeb);
	
	List<Courier> lstUsuariosWeb(String estado);
	
	UsuarioWeb obtDetalleUsuarioWeb(UsuarioWeb usuarioWeb);
		
	boolean validarContrasena(UsuarioWeb usuarioWeb);
	
	UsuarioWeb autenticarUsuario(LoginWeb loginWeb);
	
	void actContrasena(UsuarioWeb usuarioWeb);
}
