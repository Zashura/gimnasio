package ec.com.control.acceso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.RecursoDAO;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.resources.Constantes;

@Stateless
public class RecursoDAOImpl extends GenericDAOImpl<Recurso, Long> implements RecursoDAO {

	@Override
	public List<Recurso> obtenerPorAplicacion(Aplicacion aplicacion, boolean soloActivos, boolean soloRaices) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Recurso> criteria = cb.createQuery(Recurso.class);
        Root<Recurso> recurso = criteria.from(Recurso.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(recurso.get("aplicacion"), aplicacion));
        if (soloActivos) {
        	predicates.add(cb.equal(recurso.get("estado"), Constantes.REGISTRO_ACTIVO));
        }
        if (soloRaices) {
        	predicates.add(cb.isNull(recurso.get("padre")));
        }
        criteria.select(recurso)
        	.where(
        		cb.and(
        				predicates.toArray(new Predicate[predicates.size()])
        		)
        	).orderBy(cb.asc(recurso.get("orden")));
        
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<Recurso> buscarPorPadre(Recurso padre, boolean soloActivos) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Recurso> criteria = cb.createQuery(Recurso.class);
        Root<Recurso> recurso = criteria.from(Recurso.class);
        if(soloActivos) {
	        criteria.select(recurso)
	        	.where(
	        		cb.and(
	        			cb.equal(recurso.get("estado"), Constantes.REGISTRO_ACTIVO),
	        			cb.equal(recurso.get("padre"), padre)
	        		)
	        	).orderBy(cb.asc(recurso.get("orden")));
        } else {
        	criteria.select(recurso)
        	.where(cb.equal(recurso.get("padre"), padre)).orderBy(cb.asc(recurso.get("orden")));
        }
        return getEntityManager().createQuery(criteria).getResultList();
	}

}
