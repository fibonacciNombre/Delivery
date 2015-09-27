package bbva.delivery.tarjetas.usuario.dao;

import java.util.List;

import bbva.delivery.tarjetas.usuario.bean.Oficina;
import bbva.delivery.tarjetas.usuario.bean.Subgerente;
import bbva.delivery.tarjetas.usuario.bean.Usuario;

public interface UsuarioDao {
	
	void mntUsuario(Usuario usuario);
	
	void mntUsuarioGerente(Usuario usuario);
	
	void mntOficina(Oficina oficina);
	
	List<Oficina> lstOficinas(Oficina oficina);
	
	List<Usuario> lstUsuarios(Usuario usuario);
	
	List<Usuario> lstUsuarios2(Usuario usuario);
	
	List<Usuario> lstUsuariosWS(Usuario usuario);
		
	boolean validarContrasena(Usuario usuario);
	
	void mntContrasena(Usuario usuario);
	
	public Usuario obtUsuario(Usuario usuario);
	
	public Usuario addUsuario(Usuario usuario);
	
	List<Subgerente> lstSubgerentes(Subgerente subgerente);
	
	void mntSubgerente(Subgerente subgerente);
	
	Oficina obtOficina(Oficina oficina);
}
