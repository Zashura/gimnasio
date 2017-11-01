package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubTipDi;

@Local
public interface ClubTipoDisciplinaService {
	
	public ClubTipDi buscarPorId(Long id);
	
	public void crear(ClubTipDi aplicacion)throws ClubPersistException;
	
	public ClubTipDi actualizar(ClubTipDi aplicacion)throws ClubUpdateException;
	
	public List<ClubTipDi> obtenerActivas();
	
	public List<ClubTipDi> buscarPorNombre(String nombre);
	
	public ClubTipDi findByCodigo(long codigo);
}
