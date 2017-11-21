package ec.com.gimnasio.dao;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubInscripcion;

/**
*
* */
@Local
public interface ClubInscripcionDAO  extends GenericDAO<ClubInscripcion, Long> {
	
	public ClubInscripcion findByCodigo(long codigo);
	
	public ClubInscripcion findByPersona(long codigo);


}
