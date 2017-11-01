package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubHodiXDissedclub;

/**
*
* */
@Local
public interface ClubHodiXDisDAO  extends GenericDAO<ClubHodiXDissedclub, Long> {
	
	public ClubHodiXDissedclub findByCodigo(long codigo);


}
