package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubParroquia;

@Local
public interface ClubParroquiaService {
	
	public ClubParroquia buscarPorId(Long id);
	
	public void crear(ClubParroquia aplicacion) throws ClubPersistException;
	
	public ClubParroquia actualizar(ClubParroquia aplicacion) throws ClubUpdateException;
	
	public List<ClubParroquia> obtenerActivas();
	
	public List<ClubParroquia> buscarPorNombre(String nombre);
	
	public List<ClubParroquia> buscarPorCodCanton(long codCanton);
	
	public ClubParroquia buscarPorDpa(String dpa);
	
	public ClubParroquia findByCodigo(long codigo);
}
