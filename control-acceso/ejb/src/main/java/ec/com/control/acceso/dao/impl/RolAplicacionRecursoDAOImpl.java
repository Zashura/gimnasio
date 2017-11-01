package ec.com.control.acceso.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.RolAplicacionRecursoDAO;
import ec.com.control.acceso.exception.RecursoException;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.RolAplicacionRecurso;

@Stateless
public class RolAplicacionRecursoDAOImpl extends GenericDAOImpl<RolAplicacionRecurso, Long> implements RolAplicacionRecursoDAO {

	@Override
	public RolAplicacionRecurso obtenerPorRolYRecurso(RolAplicacion rol, Recurso recurso) throws RecursoException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RolAplicacionRecurso> criteria = cb.createQuery(RolAplicacionRecurso.class);
        Root<RolAplicacionRecurso> asignacion = criteria.from(RolAplicacionRecurso.class);
        criteria.select(asignacion)
        	.where(
        		cb.and(
        			cb.equal(asignacion.get("recurso"), recurso),
        			cb.equal(asignacion.get("rolAplicacion"), rol)
        		)
        	);
        try {
        	return getEntityManager().createQuery(criteria).getSingleResult();
        } catch(NonUniqueResultException e) {
        	throw new RecursoException(e);
        } catch(NoResultException e) {
        	return null;
        }
	}

	@Override
	public boolean estaAsignado(List<RolAplicacion> roles, Recurso recurso) {
//		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<RolAplicacionRecurso> criteria = cb.createQuery(RolAplicacionRecurso.class);
//        Root<RolAplicacionRecurso> asignacion = criteria.from(RolAplicacionRecurso.class);
//        criteria.select(asignacion)
//        	.where(
//        		cb.and(
//        			cb.equal(asignacion.get("recurso"), recurso),
//        			cb.isTrue(asignacion.get("rolAplicacion").in(roles)))
//        	);
//		List<RolAplicacionRecurso> lista = getEntityManager().createQuery(criteria).getResultList();
        String sql = "select a from RolAplicacionRecurso a where recurso = :recurso and rolAplicacion in (:roles) and estado = 'A'";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("recurso", recurso);
        query.setParameter("roles", roles);
        List<RolAplicacionRecurso> lista = query.getResultList();
       // System.out.println(recurso.getNombre() + ": " + lista.size());
        return !(lista == null || lista.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RolAplicacionRecurso>  obtenerActivoPorCodigos(RolAplicacion rol) {

        String sql = "select ra from RolAplicacionRecurso ra "
        			+"where rolAplicacion.codigo = :codigo ";
        Query query = getEntityManager().createQuery(sql);
         query.setParameter("codigo", rol.getCodigo());
       
        return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see ec.com.control.acceso.dao.RolAplicacionRecursoDAO#contarRolAplicacionRecursoHijo(long, java.lang.String)
	 */
	public long contarRolAplicacionRecursoHijo(long codigoRecurso,
			String estado) {
		long numeroRolAplicacionRecurso = 0L;

		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append("select count(ra) ");
			sentencia.append("	from RolAplicacionRecurso ra ");
			sentencia.append("where ra.recurso.codigo = :codigoRecurso ");
			sentencia.append("and ra.estado = :estado ");

			numeroRolAplicacionRecurso = (Long) getEntityManager().createQuery(sentencia.toString())
					.setParameter("estado", estado)
					.setParameter("codigoRecurso", codigoRecurso)
					.getSingleResult();
		} catch (NoResultException e) {
			numeroRolAplicacionRecurso = 0L;
		}
		return numeroRolAplicacionRecurso;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ec.com.control.acceso.dao.RolAplicacionRecursoDAO#contarRolAplicacionRecursoPadre(long, java.lang.String)
	 */
	public long contarRolAplicacionRecursoPadre(long codigoRecurso,
			String estado) {
		long numeroRolAplicacionRecurso = 0L;

		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append("select count(ra) ");
			sentencia.append("	from RolAplicacionRecurso ra, Recurso rPadre, Recurso rHijo ");
			sentencia.append("where rPadre.codigo = :codigoRecurso ");
			sentencia.append("and rPadre.codigo = rHijo.padre.codigo ");
			sentencia.append("and rHijo.codigo = ra.recurso.codigo ");
			sentencia.append("and rPadre.estado = :estado ");
			sentencia.append("and rHijo.estado = :estado ");
			sentencia.append("and ra.estado = :estado ");

			numeroRolAplicacionRecurso = (Long) getEntityManager().createQuery(sentencia.toString())
					.setParameter("estado", estado)
					.setParameter("codigoRecurso", codigoRecurso)
					.getSingleResult();
		} catch (NoResultException e) {
			numeroRolAplicacionRecurso = 0L;
		}
		return numeroRolAplicacionRecurso;
	}
	
}
