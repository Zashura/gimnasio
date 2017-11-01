package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubIntXDisSedIn;

/**
*
* */
@Local
public interface ClubIntXDisSedInsDAO  extends GenericDAO<ClubIntXDisSedIn, Long> {
	
	public ClubIntXDisSedIn findByCodigo(long codigo);


}
