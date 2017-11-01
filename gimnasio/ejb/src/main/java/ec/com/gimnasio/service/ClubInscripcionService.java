package ec.com.gimnasio.service;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInscripcion;

@Local
public interface ClubInscripcionService {
	
	public ClubInscripcion buscarPorId(Long id);
	
	public void crear(ClubInscripcion aplicacion)throws ClubPersistException;
	
	public ClubInscripcion actualizar(ClubInscripcion aplicacion)throws ClubUpdateException;
		
	public ClubInscripcion findByCodigo(long codigo);
}
