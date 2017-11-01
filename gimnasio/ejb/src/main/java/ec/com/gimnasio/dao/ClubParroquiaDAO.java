package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubParroquia;

/**
*
* @author
* */
@Local
public interface ClubParroquiaDAO  extends GenericDAO<ClubParroquia, Long> {
	
	public List<ClubParroquia> obtenerActivas();
	
	public List<ClubParroquia> buscarPorNombre(String nombre);
	
	public List<ClubParroquia> buscarPorCodCanton(long codCanton);
	
	public ClubParroquia buscarPorDpa(String dpa);
	
	public ClubParroquia findByCodigo(long codigo);

}
