package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInstructor;

@Local
public interface ClubInstructorService {
	
	public ClubInstructor buscarPorId(Long id);
	
	public void crear(ClubInstructor aplicacion)throws ClubPersistException;
	
	public ClubInstructor actualizar(ClubInstructor aplicacion)throws ClubUpdateException;
	
	public List<ClubInstructor> obtenerActivas();
	
	public List<ClubInstructor> buscarPorNombre(String nombre);
	
	public ClubInstructor findByCodigo(long codigo);
}
