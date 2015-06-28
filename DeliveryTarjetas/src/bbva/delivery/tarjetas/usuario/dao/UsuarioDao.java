package bbva.delivery.tarjetas.usuario.dao;

import java.util.List;

import bbva.delivery.tarjetas.usuario.bean.Usuario;

public interface UsuarioDao {
	
	void mntUsuario(Usuario usuario);
	
	List<Usuario> lstUsuarios(Usuario usuario);
		
	boolean validarContrasena(Usuario usuario);
	
	void mntContrasena(Usuario usuario);
	
	public Usuario obtUsuario(Usuario usuario);
	
	public Usuario addUsuario(Usuario usuario);

}
