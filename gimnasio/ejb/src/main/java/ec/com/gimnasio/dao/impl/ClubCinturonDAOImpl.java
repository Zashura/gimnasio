package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubCinturonDAO;
import ec.com.gimnasio.model.ClubCinturon;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubCinturonDAOImpl extends GenericDAOImpl<ClubCinturon, Long> implements ClubCinturonDAO {

	@Override
	public List<ClubCinturon> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubCinturon> criteria = cb.createQuery(ClubCinturon.class);
        Root<ClubCinturon> aplicacion = criteria.from(ClubCinturon.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("cinEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("cinDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubCinturon> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubCinturon> criteria = cb.createQuery(ClubCinturon.class);
        Root<ClubCinturon> aplicacion = criteria.from(ClubCinturon.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("cinDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("cinDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubCinturon findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubCinturon o ");
		sentencia.append("where o.cinCodigo = :codigo and o.cinEstado=:estado ");
		ClubCinturon aplicacion = (ClubCinturon) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
