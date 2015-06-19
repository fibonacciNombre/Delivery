package bbva.delivery.tarjetas.perfil.service;

import bbva.delivery.tarjetas.bean.LoginWeb;
import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;

public interface PerfilService {

	void regUsuarioweb(UsuarioWeb usuarioWeb) throws Exception;

	void actUsuarioweb(UsuarioWeb usuarioWeb, String indEscenarioLogin);

	void obtPersona(Persona persona);
	
	void obtUsuarioweb(UsuarioWeb usuarioWeb);
	
	void obtIdeterceroUsuarioweb(UsuarioWeb usuarioWeb);

	void obtCodexternoUsuarioweb(UsuarioWeb usuarioWeb);
	
	void validarEstadoUsuarioweb(UsuarioWeb usuarioWeb);
	
	UsuarioWeb autenticarUsuario(LoginWeb loginWeb) throws Exception;
	
	boolean validarContrasena(UsuarioWeb usuarioWeb);

}
