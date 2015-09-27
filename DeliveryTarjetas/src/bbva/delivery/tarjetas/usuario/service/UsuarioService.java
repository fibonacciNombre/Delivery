package bbva.delivery.tarjetas.usuario.service;

import java.util.List;

import bbva.delivery.tarjetas.usuario.bean.LoginWeb;
import bbva.delivery.tarjetas.usuario.bean.Oficina;
import bbva.delivery.tarjetas.usuario.bean.Subgerente;
import bbva.delivery.tarjetas.usuario.bean.Usuario;

public interface UsuarioService {

	void mntUsuario(Usuario usuario) throws Exception;
	
	void mntUsuarioGerente(Usuario usuario) throws Exception;
	
	void mntOficina(Oficina oficina) throws Exception;
	
	void mntSubgerente(Subgerente subgerente) throws Exception;
	
	List<Usuario> lstUsuarios(Usuario usuario);
	
	List<Usuario> lstUsuarios2(Usuario usuario);
	
	List<Oficina> lstOficinas(Oficina oficina);
	
	List<Usuario> lstUsuariosWS(Usuario usuario);
	
	List<Subgerente> lstSubgerentes(Subgerente subgerente);
	
	boolean validarContrasena(Usuario usuario);
	
	void mntContrasena(Usuario usuario) throws Exception;
	 
	Usuario obtUsuario(Usuario usuario);
		
	Usuario autenticarUsuario(LoginWeb usuario) throws Exception;
	 
	Usuario addUsuario(Usuario usuario) throws Exception ;
	
	Oficina obtOficina(Oficina oficina);
}
