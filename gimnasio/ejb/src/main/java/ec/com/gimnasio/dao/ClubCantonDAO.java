package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubCanton;

/**
*
* @author
* */
@Local
public interface ClubCantonDAO  extends GenericDAO<ClubCanton, Long> {
	
	public List<ClubCanton> obtenerActivas();
	
	public List<ClubCanton> buscarPorNombre(String nombre);
	
	public List<ClubCanton> buscarPorDpaProvincia(long codProvincia);
	
	public ClubCanton buscarPorDpa(String dpa);
	
	public ClubCanton findByCodigo(long codigo);

}
