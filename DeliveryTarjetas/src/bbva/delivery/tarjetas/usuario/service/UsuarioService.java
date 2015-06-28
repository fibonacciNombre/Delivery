package bbva.delivery.tarjetas.usuario.service;

import java.util.List;

import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.Usuario;

public interface UsuarioService {

	void mntUsuario(Usuario usuario) throws Exception;
	
	List<Usuario> lstUsuarios(Usuario usuario);
	
	List<Usuario> lstUsuariosWS(Usuario usuario);
	
	boolean validarContrasena(Usuario usuario);
	
	void mntContrasena(Usuario usuario) throws Exception;
	 
	Usuario obtUsuario(Usuario usuario);
		
	Usuario autenticarUsuario(LoginWeb usuario) throws Exception;
	 
	public Usuario addUsuario(Usuario usuario) throws Exception ;
}
