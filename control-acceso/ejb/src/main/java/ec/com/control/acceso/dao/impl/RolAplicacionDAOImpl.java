package ec.com.control.acceso.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.RolAplicacionDAO;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.resources.Constantes;

@Stateless
public class RolAplicacionDAOImpl extends GenericDAOImpl<RolAplicacion, Long> implements RolAplicacionDAO {

	@Override
	public List<RolAplicacion> obtenerActivos() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RolAplicacion> criteria = cb.createQuery(RolAplicacion.class);
        Root<RolAplicacion> rolAplicacion = criteria.from(RolAplicacion.class);
        criteria.select(rolAplicacion)
        	.where(cb.equal(rolAplicacion.get("estado"), Constantes.REGISTRO_ACTIVO))
        	.orderBy(cb.asc(rolAplicacion.get("nombre")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<RolAplicacion> buscarPorAplicacion(Aplicacion aplicacion, boolean soloActivos) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RolAplicacion> criteria = cb.createQuery(RolAplicacion.class);
        Root<RolAplicacion> rolAplicacion = criteria.from(RolAplicacion.class);
        if(soloActivos) {
        	criteria.select(rolAplicacion)
	        	.where(
	        		cb.and(
	        			cb.equal(rolAplicacion.get("estado"), Constantes.REGISTRO_ACTIVO),
	        			cb.equal(rolAplicacion.get("aplicacion"), aplicacion)
	        		)
	        	).orderBy(cb.asc(rolAplicacion.get("nombre")));
        } else {
        	criteria.select(rolAplicacion)
	        	.where(
	        		cb.equal(rolAplicacion.get("aplicacion"), aplicacion)
	        	).orderBy(cb.asc(rolAplicacion.get("nombre")));
        }
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<Usuario> obtenerUsuarios(RolAplicacion rol) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public RolAplicacion obtenerRolPorNombre(String descripcion) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RolAplicacion> criteria = cb.createQuery(RolAplicacion.class);
        Root<RolAplicacion> rolAplicacion = criteria.from(RolAplicacion.class);
        criteria.select(rolAplicacion)
        	.where(cb.equal(rolAplicacion.get("nombre"),descripcion ));
        return getEntityManager().createQuery(criteria).getSingleResult();
	}

}
