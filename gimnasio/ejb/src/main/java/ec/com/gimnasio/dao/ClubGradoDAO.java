package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubGrado;

/**
*
* */
@Local
public interface ClubGradoDAO  extends GenericDAO<ClubGrado, Long> {
	
	public List<ClubGrado> obtenerActivas();
	
	public List<ClubGrado> buscarPorNombre(String nombre);
	
	public ClubGrado findByCodigo(long codigo);


}
