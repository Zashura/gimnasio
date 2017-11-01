package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubTipDi;

/**
*
* */
@Local
public interface ClubTipoDisciplinaDAO  extends GenericDAO<ClubTipDi, Long> {
	
	public List<ClubTipDi> obtenerActivas();
	
	public List<ClubTipDi> buscarPorNombre(String nombre);
	
	public ClubTipDi findByCodigo(long codigo);


}
