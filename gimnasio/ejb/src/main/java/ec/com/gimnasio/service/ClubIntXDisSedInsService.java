package ec.com.gimnasio.service;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubIntXDisSedIn;

@Local
public interface ClubIntXDisSedInsService {
	
	public ClubIntXDisSedIn buscarPorId(Long id);
	
	public void crear(ClubIntXDisSedIn aplicacion)throws ClubPersistException;
	
	public ClubIntXDisSedIn actualizar(ClubIntXDisSedIn aplicacion)throws ClubUpdateException;
		
	public ClubIntXDisSedIn findByCodigo(long codigo);
}
