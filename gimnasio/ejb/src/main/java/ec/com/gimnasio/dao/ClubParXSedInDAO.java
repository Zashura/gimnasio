package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.CluParXSedIn;

/**
*
* */
@Local
public interface ClubParXSedInDAO  extends GenericDAO<CluParXSedIn, Long> {
		
	public CluParXSedIn findByCodigo(long codigo);
	
	public List<CluParXSedIn> findByInstitucion(long codInstitucion);
	
	public List<CluParXSedIn> findBySedeNombre(String nombre,long codInstitucion);
	
}
