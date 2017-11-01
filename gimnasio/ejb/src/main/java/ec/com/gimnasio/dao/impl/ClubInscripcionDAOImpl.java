package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;

import ec.com.gimnasio.dao.ClubInscripcionDAO;
import ec.com.gimnasio.model.ClubInscripcion;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubInscripcionDAOImpl extends GenericDAOImpl<ClubInscripcion, Long> implements ClubInscripcionDAO {
	
	@Override
	public ClubInscripcion findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubInscripcion o ");
		sentencia.append("where o.insCodigo = :codigo and o.insEstado=:estado ");
		ClubInscripcion aplicacion = (ClubInscripcion) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
