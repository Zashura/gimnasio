package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubDia;

@Local
public interface ClubDiaService {
	
	public ClubDia buscarPorId(Long id);
	
	public void crear(ClubDia aplicacion)throws ClubPersistException;
	
	public ClubDia actualizar(ClubDia aplicacion)throws ClubUpdateException;
	
	public List<ClubDia> obtenerActivas();
	
	public List<ClubDia> buscarPorNombre(String nombre);
	
	public ClubDia findByCodigo(long codigo);
}
