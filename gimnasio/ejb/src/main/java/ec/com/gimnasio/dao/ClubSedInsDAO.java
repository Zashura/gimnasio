package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubSedIn;

/**
*
* */
@Local
public interface ClubSedInsDAO  extends GenericDAO<ClubSedIn, Long> {
		
	public ClubSedIn findByCodigo(long codigo);

}
