package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubPersonaDAO;
import ec.com.gimnasio.model.ClubPersona;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubPersonaDAOImpl extends GenericDAOImpl<ClubPersona, Long> implements ClubPersonaDAO {

	@Override
	public List<ClubPersona> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubPersona> criteria = cb.createQuery(ClubPersona.class);
        Root<ClubPersona> aplicacion = criteria.from(ClubPersona.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("perEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("perNombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubPersona> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubPersona> criteria = cb.createQuery(ClubPersona.class);
        Root<ClubPersona> aplicacion = criteria.from(ClubPersona.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("perNombres"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("perNombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubPersona findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubPersona o ");
		sentencia.append("where o.perCodigo = :codigo and o.perEstado=:estado ");
		ClubPersona aplicacion = (ClubPersona) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
