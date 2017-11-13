package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubHorDia;

@Local
public interface ClubHoraDiaService {
	
	public ClubHorDia buscarPorId(Long id);
	
	public void crear(ClubHorDia aplicacion)throws ClubPersistException;
	
	public ClubHorDia actualizar(ClubHorDia aplicacion)throws ClubUpdateException;
		
	public ClubHorDia findByCodigo(long codigo);
	
	public List<ClubHorDia> obtenerActivas();
	
	public ClubHorDia findByDiaHora(long codDia,long codHora);
}
