package ec.com.gimnasio.service;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubSedIn;

@Local
public interface ClubSedInsService {
	
	public ClubSedIn findByCodigo(long codigo);
	
	public ClubSedIn findByCodigoInstucionSede(long codInst,long codSede);
	
	public void crear(ClubSedIn aplicacion) throws ClubPersistException;
	
	public ClubSedIn actualizar(ClubSedIn aplicacion) throws ClubUpdateException;
	
}
