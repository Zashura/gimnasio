package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubInstructor;

/**
*
* */
@Local
public interface ClubInstructorDAO  extends GenericDAO<ClubInstructor, Long> {
	
	public List<ClubInstructor> obtenerActivas();
	
	public List<ClubInstructor> buscarPorNombre(String nombre);
	
	public ClubInstructor findByCodigo(long codigo);


}
