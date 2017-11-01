package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubProvincia;

/**
*
* @author
* */
@Local
public interface ClubProvinciaDAO  extends GenericDAO<ClubProvincia, Long> {
	
	public List<ClubProvincia> obtenerActivas();
	
	public List<ClubProvincia> buscarPorNombre(String nombre);
	
	public ClubProvincia buscarPorDpa(String dpa);
	
	public ClubProvincia findByCodigo(long codigo);

}
