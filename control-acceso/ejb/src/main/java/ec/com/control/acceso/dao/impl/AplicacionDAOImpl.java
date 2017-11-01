package ec.com.control.acceso.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.AplicacionDAO;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.resources.Constantes;

@Stateless
public class AplicacionDAOImpl extends GenericDAOImpl<Aplicacion, Long> implements AplicacionDAO {

	@Override
	public List<Aplicacion> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Aplicacion> criteria = cb.createQuery(Aplicacion.class);
        Root<Aplicacion> aplicacion = criteria.from(Aplicacion.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("estado"), Constantes.REGISTRO_ACTIVO))
        	.orderBy(cb.asc(aplicacion.get("nombre")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<Aplicacion> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Aplicacion> criteria = cb.createQuery(Aplicacion.class);
        Root<Aplicacion> aplicacion = criteria.from(Aplicacion.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("nombre"),"%"+ nombre+"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("nombre")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public Aplicacion buscarPorPrefijo(String prefijo) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Aplicacion> criteria = cb.createQuery(Aplicacion.class);
        Root<Aplicacion> aplicacion = criteria.from(Aplicacion.class);
        criteria.select(aplicacion).where(
        		cb.and(
        			cb.equal(aplicacion.<String>get("prefijo"), prefijo),
        			cb.equal(aplicacion.get("estado"), Constantes.REGISTRO_ACTIVO)
        		))
        	.orderBy(cb.asc(aplicacion.get("nombre")));
        try {
        	return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Aplicacion findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from Aplicacion o ");
		sentencia.append("where o.codigo = :codigo ");
		Aplicacion aplicacion = (Aplicacion) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).getSingleResult();
		for (Recurso item : aplicacion.getRecursos()) {
			item.getCodigo();
			item.getEstado();
		}
		for (RolAplicacion item : aplicacion.getRolAplicacions()) {
			item.getCodigo();
			item.getEstado();
		}
		
		return aplicacion;
	}
	
}
