package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubPersona;

@Local
public interface ClubPersonaService {
	
	public ClubPersona buscarPorId(Long id);
	
	public void crear(ClubPersona aplicacion)throws ClubPersistException;
	
	public ClubPersona actualizar(ClubPersona aplicacion)throws ClubUpdateException;
	
	public List<ClubPersona> obtenerActivas();
	
	public List<ClubPersona> buscarPorNombre(String nombre);
	
	public ClubPersona findByCodigo(long codigo);
}
