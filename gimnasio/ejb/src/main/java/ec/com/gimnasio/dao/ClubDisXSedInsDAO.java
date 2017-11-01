package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubDisXSedIn;

/**
*
* */
@Local
public interface ClubDisXSedInsDAO  extends GenericDAO<ClubDisXSedIn, Long> {
	
	public ClubDisXSedIn findByCodigo(long codigo);


}
