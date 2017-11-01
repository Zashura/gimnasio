package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCanton;

@Local
public interface ClubCantonService {
	
	public ClubCanton buscarPorId(Long id);
	
	public void crear(ClubCanton aplicacion) throws ClubPersistException;
	
	public ClubCanton actualizar(ClubCanton aplicacion) throws ClubUpdateException;
	
	public List<ClubCanton> obtenerTodas();
	
	public List<ClubCanton> obtenerActivas();
	
	public List<ClubCanton> obtenerPorNombre(String nombre);
	
	public ClubCanton buscarPorDpa(String dpa);

	public ClubCanton obtenerPorCodigo(long codigo);
	
	public List<ClubCanton> buscarPorDpaProvincia(long codProvincia);
}
