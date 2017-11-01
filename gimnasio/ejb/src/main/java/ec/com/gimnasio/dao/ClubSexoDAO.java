package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubSexo;

/**
*
* */
@Local
public interface ClubSexoDAO  extends GenericDAO<ClubSexo, Long> {
	
	public List<ClubSexo> obtenerActivas();
	
	public List<ClubSexo> buscarPorNombre(String nombre);
	
	public ClubSexo findByCodigo(long codigo);


}
