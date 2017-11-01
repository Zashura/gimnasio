package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubCinXGra;

/**
*
* */
@Local
public interface ClubCinturonGradoDAO  extends GenericDAO<ClubCinXGra, Long> {
	
	public ClubCinXGra findByCodigo(long codigo);


}
