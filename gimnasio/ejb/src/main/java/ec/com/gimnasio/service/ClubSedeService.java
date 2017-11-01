package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubSede;

@Local
public interface ClubSedeService {
	
	public ClubSede buscarPorId(Long id);
	
	public void crear(ClubSede aplicacion)throws ClubPersistException;
	
	public ClubSede actualizar(ClubSede aplicacion)throws ClubUpdateException;
	
	public List<ClubSede> obtenerActivas();
	
	public List<ClubSede> buscarPorNombre(String nombre);
	
	public ClubSede findByNemonico(String nemonico);
	
	public ClubSede findByCodigo(long codigo);
	
	public List<ClubSede> findByInstitucion(long codInstitucion);
}
