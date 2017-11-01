package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubRepresentante;

/**
*
* */
@Local
public interface ClubRepresentanteDAO  extends GenericDAO<ClubRepresentante, Long> {
	
	public List<ClubRepresentante> obtenerActivas();
	
	public List<ClubRepresentante> buscarPorNombre(String nombre);
	
	public ClubRepresentante findByCodigo(long codigo);


}
