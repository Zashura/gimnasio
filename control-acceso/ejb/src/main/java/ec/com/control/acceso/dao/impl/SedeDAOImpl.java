package ec.com.control.acceso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.SedeDAO;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.resources.Constantes;

@Stateless
public class SedeDAOImpl extends GenericDAOImpl<Sede, Long> implements SedeDAO {

	@Override
	public List<Sede> obtenerEstructura(boolean soloActivas) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sede> criteria = cb.createQuery(Sede.class);
        Root<Sede> sede = criteria.from(Sede.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (soloActivas) {
        	predicates.add(cb.equal(sede.get("estado"), Constantes.REGISTRO_ACTIVO));
        }
        predicates.add(cb.isNull(sede.get("sede")));
        criteria.select(sede)
        	.where(
        		cb.and(
        				predicates.toArray(new Predicate[predicates.size()])
        		)
        	).orderBy(cb.asc(sede.get("nemonico")));
        List<Sede> raices = getEntityManager().createQuery(criteria).getResultList();
        for (Sede raiz : raices) {
			armarEstructura(raiz, soloActivas);
		}
        return raices;
	}
	
	public List<Sede> obtenerPorPadre(Sede padre, boolean soloActivas) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sede> criteria = cb.createQuery(Sede.class);
        Root<Sede> sede = criteria.from(Sede.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (soloActivas) {
        	predicates.add(cb.equal(sede.get("estado"), Constantes.REGISTRO_ACTIVO));
        }
        predicates.add(cb.equal(sede.get("sede"), padre));
        criteria.select(sede)
        	.where(
        		cb.and(
        				predicates.toArray(new Predicate[predicates.size()])
        		)
        	).orderBy(cb.asc(sede.get("nemonico")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	public List<Sede> obtenerSinPadre(boolean soloActivas) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sede> criteria = cb.createQuery(Sede.class);
        Root<Sede> sede = criteria.from(Sede.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (soloActivas) {
        	predicates.add(cb.equal(sede.get("estado"), Constantes.REGISTRO_ACTIVO));
        }
        predicates.add(cb.isNull(sede.get("sede")));
        criteria.select(sede)
        	.where(
        		cb.and(
        				predicates.toArray(new Predicate[predicates.size()])
        		)
        	).orderBy(cb.asc(sede.get("nemonico")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	private Sede armarEstructura(Sede sede, boolean soloActivas) {
		List<Sede> hijas = obtenerPorPadre(sede, soloActivas);
		if(hijas == null || hijas.isEmpty()) {
			
		} else {
			for (Sede hija : hijas) {
				armarEstructura(hija, soloActivas);
			}
		}
		sede.setSedes(hijas);
		return sede;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sede> listarTodosIn(List<Long> ids) {
		List<Sede> sedes = null;
		try {
			sedes = emSeguridades.createQuery("select o from Sede o where o.codigo in :ids ").setParameter("ids", ids).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sedes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sede> listarTodosNot(List<Long> ids) {
		List<Sede> sedes = null;
		try {
			sedes = emSeguridades.createQuery("select o from Sede o where o.codigo not in :ids ").setParameter("ids", ids).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sedes;
	}


	
}
