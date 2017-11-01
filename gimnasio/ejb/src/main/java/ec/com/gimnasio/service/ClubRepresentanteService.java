package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubRepresentante;

@Local
public interface ClubRepresentanteService {
	
	public ClubRepresentante buscarPorId(Long id);
	
	public void crear(ClubRepresentante aplicacion)throws ClubPersistException;
	
	public ClubRepresentante actualizar(ClubRepresentante aplicacion)throws ClubUpdateException;
	
	public List<ClubRepresentante> obtenerActivas();
	
	public List<ClubRepresentante> buscarPorNombre(String nombre);
	
	public ClubRepresentante findByCodigo(long codigo);
}
