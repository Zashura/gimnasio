package ec.com.gimnasio.service;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCinDi;

@Local
public interface ClubCinturonDisciplinaService {
	
	public ClubCinDi buscarPorId(Long id);
	
	public void crear(ClubCinDi aplicacion)throws ClubPersistException;
	
	public ClubCinDi actualizar(ClubCinDi aplicacion)throws ClubUpdateException;
		
	public ClubCinDi findByCodigo(long codigo);
}
