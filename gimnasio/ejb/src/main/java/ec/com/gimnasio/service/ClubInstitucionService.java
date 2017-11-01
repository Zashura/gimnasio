package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInstitucion;

@Local
public interface ClubInstitucionService {
	
	public ClubInstitucion buscarPorId(Long id);
	
	public void crear(ClubInstitucion aplicacion)throws ClubPersistException;
	
	public ClubInstitucion actualizar(ClubInstitucion aplicacion)throws ClubUpdateException;
	
	public List<ClubInstitucion> obtenerTodas();
	
	public List<ClubInstitucion> obtenerActivas();
	
	public List<ClubInstitucion> obtenerPorNombre(String nombre);

	public ClubInstitucion obtenerPorCodigo(long codigo);
	
	public ClubInstitucion findByCodigoCas(long codigo);
}
