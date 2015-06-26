package bbva.delivery.tarjetas.perfil.service;

import java.util.List;

import bbva.delivery.tarjetas.perfil.bean.Perfil;

public interface PerfilService {
	
	void mntPerfil(Perfil perfil);
	
	List<Perfil> lstPerfiles(Perfil perfil);
	
	Perfil  obtPerfil(Perfil perfil); 
	 
}