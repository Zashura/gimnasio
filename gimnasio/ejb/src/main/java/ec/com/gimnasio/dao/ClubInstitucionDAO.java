package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubInstitucion;

/**
*
* @author
* */
@Local
public interface ClubInstitucionDAO  extends GenericDAO<ClubInstitucion, Long> {
	
	public List<ClubInstitucion> obtenerActivas();
	
	public List<ClubInstitucion> buscarPorNombre(String nombre);
	
	public ClubInstitucion findByCodigo(long codigo);
	
	public ClubInstitucion findByCodigoCas(long codigo);

}
