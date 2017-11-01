package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubPersona;

/**
*
* */
@Local
public interface ClubPersonaDAO  extends GenericDAO<ClubPersona, Long> {
	
	public List<ClubPersona> obtenerActivas();
	
	public List<ClubPersona> buscarPorNombre(String nombre);
	
	public ClubPersona findByCodigo(long codigo);


}
