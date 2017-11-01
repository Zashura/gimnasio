package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubSede;

/**
*
* */
@Local
public interface ClubSedeDAO  extends GenericDAO<ClubSede, Long> {
	
	public List<ClubSede> obtenerActivas();
	
	public List<ClubSede> buscarPorNombre(String nombre);
	
	public ClubSede findByNemonico(String nemonico);
	
	public ClubSede findByCodigo(long codigo);
	
	public List<ClubSede> findByInstitucion(long codInstitucion);

}
