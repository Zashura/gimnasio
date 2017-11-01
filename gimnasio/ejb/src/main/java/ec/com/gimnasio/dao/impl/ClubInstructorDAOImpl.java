package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubInstructorDAO;
import ec.com.gimnasio.model.ClubInstructor;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubInstructorDAOImpl extends GenericDAOImpl<ClubInstructor, Long> implements ClubInstructorDAO {

	@Override
	public List<ClubInstructor> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubInstructor> criteria = cb.createQuery(ClubInstructor.class);
        Root<ClubInstructor> aplicacion = criteria.from(ClubInstructor.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("intEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("intNombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubInstructor> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubInstructor> criteria = cb.createQuery(ClubInstructor.class);
        Root<ClubInstructor> aplicacion = criteria.from(ClubInstructor.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("intNombres"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("intNombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubInstructor findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubInstructor o ");
		sentencia.append("where o.intCodigo = :codigo and o.intEstado=:estado ");
		ClubInstructor aplicacion = (ClubInstructor) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
