package ec.com.control.acceso.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.AsignacionUsuarioSedeDAO;
import ec.com.control.acceso.model.AsignacionUsuarioSede;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.model.Usuario;

@Stateless
public class AsignacionUsuarioSedeDAOImpl extends GenericDAOImpl<AsignacionUsuarioSede, Long> implements AsignacionUsuarioSedeDAO {

	@Override
	public AsignacionUsuarioSede obtenerUltimaAsignacion(Usuario usuario) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AsignacionUsuarioSede> criteria = cb.createQuery(AsignacionUsuarioSede.class);
        Root<AsignacionUsuarioSede> asignacion = criteria.from(AsignacionUsuarioSede.class);
        criteria.select(asignacion)
        	.where(
        		cb.and(
        				cb.equal(asignacion.get("usuario"), usuario),
        				cb.or(
        						cb.greaterThanOrEqualTo(asignacion.<Date>get("fechaFinalizacion"), new Date()),
        						cb.isNull(asignacion.get("fechaFinalizacion"))
        		        )
        		)
        	).orderBy(cb.desc(asignacion.get("fechaAsignacion")));
        List<AsignacionUsuarioSede> asignaciones = getEntityManager().createQuery(criteria).getResultList();
        if(asignaciones == null || asignaciones.isEmpty()) {
        	return null;
        } else {
        	return asignaciones.get(0);
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> obtenerPorSede(Sede sede) {
		String sql = "select distinct a.usuario from AsignacionUsuarioSede a where a.sede = :sede and (a.fechaFinalizacion is null or a.fechaFinalizacion >= :fechaActual)";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("sede", sede);
		query.setParameter("fechaActual", new Date());
		return query.getResultList();
	}

}
