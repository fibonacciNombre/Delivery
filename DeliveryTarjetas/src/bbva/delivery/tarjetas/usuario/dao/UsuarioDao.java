package bbva.delivery.tarjetas.usuario.dao;

import java.util.List;

import bbva.delivery.tarjetas.tercero.bean.Tercero;
import bbva.delivery.tarjetas.usuario.bean.Usuario;

public interface UsuarioDao {
	
	void mntUsuario(Usuario usuarioWeb);
	
	List<Usuario> lstUsuarios(Usuario usuario);
	 
	
	Usuario obtDetalleUsuario(Usuario usuarioWeb);
		
	boolean validarContrasena(Usuario usuarioWeb);
	
	void mntContrasena(Usuario usuarioWeb);
	
	public Usuario obtUsuario(Usuario usuario);

	public Usuario addUsuario(Usuario usuario);

}
