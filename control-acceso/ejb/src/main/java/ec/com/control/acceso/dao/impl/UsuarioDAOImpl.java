package ec.com.control.acceso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.UsuarioDAO;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.resources.Constantes;

@Stateless
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Long> implements UsuarioDAO {

	@Override
	public List<Usuario> obtenerTodos(boolean soloActivos) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        if (soloActivos) {
        	criteria.select(usuario)
	        	.where(cb.equal(usuario.get("estado"), Constantes.REGISTRO_ACTIVO))
	        	.orderBy(cb.asc(usuario.get("apellidos")), cb.asc(usuario.get("nombres")));
        } else {
        	criteria.select(usuario);
        }
        return getEntityManager().createQuery(criteria).getResultList();      
	}

	
	//***********************************************************************************************************************
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuarioNombre(
			String nombre, String apellido) {
			String sql = "select su from Usuario su where su.estado=:registroActivo ";
			boolean hayNombre = false;
			boolean hayApellido = false;
			String condicion = "and";

			if (nombre != null && !nombre.equals("")) {
			condicion = "and";
			sql = sql + " " + condicion + " su.nombres like :nombre";
			hayNombre = true;
			}

			if (apellido != null && !apellido.equals("")) {
			condicion = "and";
			sql = sql + " " + condicion + " su.apellidos like :apellido";
			hayApellido = true;
			}

			sql = sql + " and 1=1 " + " order by su.apellidos asc";
			Query query = getEntityManager().createQuery(sql);
			query.setParameter("registroActivo", Constantes.REGISTRO_ACTIVO);
			if (hayNombre == true) {
			query.setParameter("nombre", '%' + nombre + '%');
			}
			if (hayApellido == true) {
			query.setParameter("apellido", '%' + apellido + '%');
			}

			return query.getResultList();
			}
	
	//***********************************************************************************************************************
	
	@Override
	public List<Usuario> buscarPorNombre(String nombre, boolean soloActivos) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (soloActivos) {
        	predicates.add(cb.equal(usuario.get("estado"), Constantes.REGISTRO_ACTIVO));
        }
        criteria.select(usuario)
	    	.where(
	    			
	    		cb.and(predicates.toArray(new Predicate[predicates.size()])), 
	    		cb.or(
	    			cb.like(usuario.<String>get("nombres"), nombre), 
	    			cb.like(usuario.<String>get("apellidos"), nombre))
	    	).orderBy(cb.asc(usuario.get("apellidos")), cb.asc(usuario.get("nombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	//******************************************************************************************************
	
	public Usuario buscarUsuarioId(String identificacion){
		String sql="";	
		Usuario salida=new Usuario();
		boolean hayId = false;
			if (identificacion != null && !identificacion.equals("")) {
				 try {
				sql = "select su from Usuario su where su.identificacion=:identificacion ";
				hayId = true;
				Query query = getEntityManager().createQuery(sql);
				if (hayId == true) {
				query.setParameter("identificacion", identificacion);
				}
				return (Usuario)query.getSingleResult();
				 } catch (NoResultException e) {
						return null;
				}
			}			
			return null;
	}
	
	
	//******************************************************************************************************
	
	public Usuario buscarPorIdentificacion(String identificacion) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(usuario.get("estado"), Constantes.REGISTRO_ACTIVO));
        predicates.add(cb.equal(usuario.get("identificacion"), identificacion));
        criteria.select(usuario)
	    	.where(
	    		cb.and(predicates.toArray(new Predicate[predicates.size()])) 
	    	);
        try {
        	return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerPorNombreYSedes(String nombre, List<Long> codigosSede, boolean soloActivos) {
		String sql = "select u from Usuario u where (u.nombres like :nombre or u.apellidos like :nombre) and u.sede.codigo in (:sedes) ";
		if (soloActivos) {
			sql += "and u.estado = '" + Constantes.REGISTRO_ACTIVO + "'";
		}
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("nombre", nombre);
		query.setParameter("sedes", codigosSede);
        return query.getResultList();
	}
	
	public Usuario buscarPorCorreo(String correo) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(usuario.get("estado"), Constantes.REGISTRO_ACTIVO));
        predicates.add(cb.equal(usuario.get("correoElectronico"), correo));
        criteria.select(usuario)
	    	.where(
	    		cb.and(predicates.toArray(new Predicate[predicates.size()])) 
	    	);
        try {
        	return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Usuario> buscarPorCorreoElectronico(String correo){
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(usuario.get("correoElectronico"), correo));
        criteria.select(usuario)
	    	.where(
	    		cb.and(predicates.toArray(new Predicate[predicates.size()])) 
	    	);
        return getEntityManager().createQuery(criteria).getResultList();
	}
}
