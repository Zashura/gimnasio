package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubTipIde;

@Local
public interface ClubTipoIdeService {
	
	public ClubTipIde buscarPorId(Long id);
	
	public void crear(ClubTipIde aplicacion)throws ClubPersistException;
	
	public ClubTipIde actualizar(ClubTipIde aplicacion)throws ClubUpdateException;
	
	public List<ClubTipIde> obtenerActivas();
	
	public List<ClubTipIde> buscarPorNombre(String nombre);
	
	public ClubTipIde findByCodigo(long codigo);
}
