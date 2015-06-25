package bbva.delivery.tarjetas.usuario.service;

import java.util.List;

import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.Usuario;

public interface UsuarioService {

	
	void regUsuario(Usuario usuario);
	
	void mntUsuario(Usuario usuario) throws Exception;
	
	List<Usuario> lstUsuarios(Usuario usuario);
	
	List<Usuario> lstUsuarios(Usuario usuario, Tercero tercero);
	
	Usuario obtDetalleUsuario(Usuario usuario);
		
	boolean validarContrasena(Usuario usuario);
	
	Usuario autenticarUsuario(LoginWeb usuario);
	
	void actContrasena(Usuario usuario);
}
