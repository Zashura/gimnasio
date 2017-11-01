package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubCinDi;

/**
*
* */
@Local
public interface ClubCinturonDisciplinaDAO  extends GenericDAO<ClubCinDi, Long> {
	
	public ClubCinDi findByCodigo(long codigo);


}
