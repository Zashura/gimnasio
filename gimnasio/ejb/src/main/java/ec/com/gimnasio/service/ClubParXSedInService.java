package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.CluParXSedIn;

@Local
public interface ClubParXSedInService {
	
	public CluParXSedIn findByCodigo(long codigo);
	
	public List<CluParXSedIn> findByInstitucion(long codInstitucion);
	
	public List<CluParXSedIn> findBySedeNombre(String nombreSede,long codInstitucion);
	
	public void crear(CluParXSedIn aplicacion) throws ClubPersistException;
	
	public CluParXSedIn actualizar(CluParXSedIn aplicacion) throws ClubUpdateException;
	
}
