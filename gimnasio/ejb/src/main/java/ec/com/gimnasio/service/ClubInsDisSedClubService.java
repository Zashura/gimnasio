package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInsDisSedClub;

@Local
public interface ClubInsDisSedClubService {
	
	public ClubInsDisSedClub buscarPorId(Long id);
	
	public void crear(ClubInsDisSedClub aplicacion)throws ClubPersistException;
	
	public ClubInsDisSedClub actualizar(ClubInsDisSedClub aplicacion)throws ClubUpdateException;
		
	public ClubInsDisSedClub findByCodigo(long codigo);
	
	public List<ClubInsDisSedClub> findPersonaInstitucion(long codPersona,long codInstitucion);
}
