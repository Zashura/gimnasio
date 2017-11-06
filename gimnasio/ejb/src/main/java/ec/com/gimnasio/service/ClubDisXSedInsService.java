package ec.com.gimnasio.service;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubDisXSedIn;

@Local
public interface ClubDisXSedInsService {
	
	public ClubDisXSedIn buscarPorId(Long id);
	
	public ClubDisXSedIn findByCodigoSedeDisciplina(long codSedIns,long codDis);
	
	public void crear(ClubDisXSedIn aplicacion)throws ClubPersistException;
	
	public ClubDisXSedIn actualizar(ClubDisXSedIn aplicacion)throws ClubUpdateException;
		
	public ClubDisXSedIn findByCodigo(long codigo);
}
