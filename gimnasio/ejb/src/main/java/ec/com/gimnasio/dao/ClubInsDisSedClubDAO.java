package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubInsDisSedClub;

/**
*
* */
@Local
public interface ClubInsDisSedClubDAO  extends GenericDAO<ClubInsDisSedClub, Long> {
	
	public ClubInsDisSedClub findByCodigo(long codigo);


}
