package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubHorario;

@Local
public interface ClubHorarioService {
	
	public ClubHorario buscarPorId(Long id);
	
	public void crear(ClubHorario aplicacion)throws ClubPersistException;
	
	public ClubHorario actualizar(ClubHorario aplicacion)throws ClubUpdateException;
	
	public List<ClubHorario> obtenerActivas();
	
	public ClubHorario findByCodigo(long codigo);
	
	public List<ClubHorario> findByDia(long codDia);
}
