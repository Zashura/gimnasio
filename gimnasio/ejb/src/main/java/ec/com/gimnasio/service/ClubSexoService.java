package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubSexo;

@Local
public interface ClubSexoService {
	
	public ClubSexo buscarPorId(Long id);
	
	public void crear(ClubSexo aplicacion)throws ClubPersistException;
	
	public ClubSexo actualizar(ClubSexo aplicacion)throws ClubUpdateException;
	
	public List<ClubSexo> obtenerActivas();
	
	public List<ClubSexo> buscarPorNombre(String nombre);
	
	public ClubSexo findByCodigo(long codigo);
}
