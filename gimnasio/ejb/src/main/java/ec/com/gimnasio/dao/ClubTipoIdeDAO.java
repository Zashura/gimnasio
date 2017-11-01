package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubTipIde;

/**
*
* */
@Local
public interface ClubTipoIdeDAO  extends GenericDAO<ClubTipIde, Long> {
	
	public List<ClubTipIde> obtenerActivas();
	
	public List<ClubTipIde> buscarPorNombre(String nombre);
	
	public ClubTipIde findByCodigo(long codigo);


}
