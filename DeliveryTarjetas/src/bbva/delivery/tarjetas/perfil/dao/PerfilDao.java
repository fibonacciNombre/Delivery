package bbva.delivery.tarjetas.perfil.dao;

import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;

public interface PerfilDao {

	void registrarUsuarioweb(UsuarioWeb usuarioWeb);
	
	void obtEstadoUsuarioWeb(UsuarioWeb usuarioWeb);

	void mtnEstadoUsuarioWeb(UsuarioWeb usuarioWeb, String ind);

	void obtUsuarioweb(UsuarioWeb usuarioWeb);
	
	void obtIdeterceroUsuarioweb(UsuarioWeb usuarioWeb);
	
	void obtCodexternoUsuarioweb(UsuarioWeb usuarioWeb);
	
	void obtPersona(Persona persona);
	
	boolean validarContrsena(UsuarioWeb usuarioWeb);
	
}
