package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubDia;

/**
*
* */
@Local
public interface ClubDiaDAO  extends GenericDAO<ClubDia, Long> {
	
	public List<ClubDia> obtenerActivas();
	
	public List<ClubDia> buscarPorNombre(String nombre);
	
	public ClubDia findByCodigo(long codigo);


}
