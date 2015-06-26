package bbva.delivery.tarjetas.perfil.dao;

import java.util.List;

import bbva.delivery.tarjetas.perfil.bean.Perfil;

public interface PerfilDao {
	
	void mntPerfil(Perfil perfil);
	
	List<Perfil> lstPerfiles(Perfil perfil);
	
	Perfil  obtPerfil(Perfil perfil); 
	 
}