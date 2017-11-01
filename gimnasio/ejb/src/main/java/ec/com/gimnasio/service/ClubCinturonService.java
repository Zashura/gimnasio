package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCinturon;

@Local
public interface ClubCinturonService {
	
	public ClubCinturon buscarPorId(Long id);
	
	public void crear(ClubCinturon aplicacion)throws ClubPersistException;
	
	public ClubCinturon actualizar(ClubCinturon aplicacion)throws ClubUpdateException;
	
	public List<ClubCinturon> obtenerActivas();
	
	public List<ClubCinturon> buscarPorNombre(String nombre);
	
	public ClubCinturon findByCodigo(long codigo);
}
