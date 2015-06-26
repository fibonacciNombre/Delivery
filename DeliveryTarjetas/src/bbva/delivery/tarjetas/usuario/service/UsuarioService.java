package bbva.delivery.tarjetas.usuario.service;

import java.util.List;

import bbva.delivery.tarjetas.courier.bean.Courier;
import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.Usuario;

public interface UsuarioService {

	void mntUsuario(Usuario usuario) throws Exception;
	
	List<Usuario> lstUsuarios(Usuario usuario);
	 
	
	Usuario obtUsuario(Usuario usuario);
		
	boolean validarContrasena(Usuario usuario);
	
	Usuario autenticarUsuario(LoginWeb usuario) throws Exception;
	
	void mntContrasena(Usuario usuario);
	 
	public Usuario addUsuario(Usuario usuario) throws Exception ;
}
