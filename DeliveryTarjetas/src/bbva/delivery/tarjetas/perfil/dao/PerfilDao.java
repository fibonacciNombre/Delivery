package bbva.delivery.tarjetas.perfil.dao;

import bbva.delivery.tarjetas.comun.bean.Persona;
import bbva.delivery.tarjetas.perfil.bean.MedioContacto;
import bbva.delivery.tarjetas.perfil.bean.PuntoContacto;
import bbva.delivery.tarjetas.perfil.bean.UsuarioWeb;

public interface PerfilDao {

	void obtEstadoUsuarioWeb(UsuarioWeb usuarioWeb);

	void mtnEstadoUsuarioWeb(UsuarioWeb usuarioWeb, String ind);

	void registrarUsuarioweb(UsuarioWeb usuarioWeb);

	void obtIdeterceroUsuarioweb(UsuarioWeb usuarioWeb);
	
	void obtCodexternoUsuarioweb(UsuarioWeb usuarioWeb);

	void obtUsuarioweb(UsuarioWeb usuarioWeb);
	
	void regPuntocontactoUsuarioweb(PuntoContacto puntoContacto) throws Exception;

	void regMediocontactoUsuarioweb(MedioContacto medioContacto);
	
	void obtPtocontactoUsuarioweb(PuntoContacto puntoContacto);
	
	void obtMediocontactoUsuarioweb(MedioContacto medioContacto);
	
	void obtMediocontactoSecUsuarioweb(MedioContacto medioContacto);
	
	void obtDirCorrespUsuarioweb(PuntoContacto puntoContacto);
	
	public void obtPersona(Persona persona);
	
	public String regToken(Integer idetercero, String token, String fecinivig, String userapp);
	
	public Integer obtIdeterceroxToken(String token);
}
