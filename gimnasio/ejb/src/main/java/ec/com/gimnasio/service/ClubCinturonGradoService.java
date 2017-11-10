package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCinXGra;

@Local
public interface ClubCinturonGradoService {
	
	public ClubCinXGra buscarPorId(Long id);
	
	public void crear(ClubCinXGra aplicacion)throws ClubPersistException;
	
	public ClubCinXGra actualizar(ClubCinXGra aplicacion)throws ClubUpdateException;
		
	public ClubCinXGra findByCodigo(long codigo);
	
	public List<ClubCinXGra> listByDisciplina(long codDisciplina);
}
