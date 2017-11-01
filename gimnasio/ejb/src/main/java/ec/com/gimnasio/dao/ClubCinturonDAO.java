package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubCinturon;

/**
*
* */
@Local
public interface ClubCinturonDAO  extends GenericDAO<ClubCinturon, Long> {
	
	public List<ClubCinturon> obtenerActivas();
	
	public List<ClubCinturon> buscarPorNombre(String nombre);
	
	public ClubCinturon findByCodigo(long codigo);


}
