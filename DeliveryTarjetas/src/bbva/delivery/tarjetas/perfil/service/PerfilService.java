package bbva.delivery.tarjetas.perfil.service;

import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.perfil.bean.LoginWeb;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.PuntoContacto;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;

public interface PerfilService {

	public void validarEstadoUsuarioweb(UsuarioWeb usuarioWeb);

	public void actUsuarioweb(UsuarioWeb usuarioWeb, String indEscenarioLogin);

	public void regUsuarioweb(UsuarioWeb usuarioWeb) throws Exception;

	public void regDireccionUsuarioweb(PuntoContacto puntoContacto) throws Exception;

	public void regMediocontactoUsuarioweb(MedioContacto medioContacto,
			String idpTipoMedioContacto);

	public void obtUsuarioweb(UsuarioWeb usuarioWeb);

	public void obtIdeterceroUsuarioweb(UsuarioWeb usuarioWeb);

	public void obtCodexternoUsuarioweb(UsuarioWeb usuarioWeb);

	public void obtPtocontactoUsuarioweb(PuntoContacto puntoContacto);

	public void obtMediocontactoUsuarioweb(MedioContacto medioContacto);
	
	public void obtMediocontactoSecUsuarioweb(MedioContacto medioContacto);
	
	public void obtDirCorrespUsuarioweb(PuntoContacto puntoContacto);
	
	public void obtPersona(Persona persona);
	
	public String regToken(Integer idetercero, String token, String fecinivig, String userapp);
	
	public Integer obtIdeterceroxToken(String token);
	
	public UsuarioWeb autenticarUsuario(LoginWeb loginWeb) throws Exception;
}
