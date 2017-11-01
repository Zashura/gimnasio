package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubProvincia;

@Local
public interface ClubProvinciaService {
	
	public void crear(ClubProvincia aplicacion) throws ClubPersistException;
	
	public ClubProvincia actualizar(ClubProvincia aplicacion) throws ClubUpdateException;
	
	public List<ClubProvincia> obtenerActivas();
	
	public List<ClubProvincia> buscarPorNombre(String nombre);
	
	public ClubProvincia buscarPorDpa(String dpa);
	
	public ClubProvincia findByCodigo(long codigo);
	
	public List<ClubProvincia> obtenerTodas();
}
