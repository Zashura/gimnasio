package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubGrado;

@Local
public interface ClubGradoService {
	
	public ClubGrado buscarPorId(Long id);
	
	public void crear(ClubGrado aplicacion)throws ClubPersistException;
	
	public ClubGrado actualizar(ClubGrado aplicacion)throws ClubUpdateException;
	
	public List<ClubGrado> obtenerActivas();
	
	public List<ClubGrado> buscarPorNombre(String nombre);
	
	public ClubGrado findByCodigo(long codigo);
}
