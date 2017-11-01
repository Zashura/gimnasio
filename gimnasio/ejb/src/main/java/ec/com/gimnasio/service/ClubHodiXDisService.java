package ec.com.gimnasio.service;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubHodiXDissedclub;

@Local
public interface ClubHodiXDisService {
	
	public ClubHodiXDissedclub buscarPorId(Long id);
	
	public void crear(ClubHodiXDissedclub aplicacion)throws ClubPersistException;
	
	public ClubHodiXDissedclub actualizar(ClubHodiXDissedclub aplicacion)throws ClubUpdateException;
		
	public ClubHodiXDissedclub findByCodigo(long codigo);
}
