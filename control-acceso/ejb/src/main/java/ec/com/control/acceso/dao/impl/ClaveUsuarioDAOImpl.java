package ec.com.control.acceso.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.ClaveUsuarioDAO;
import ec.com.control.acceso.model.ClaveUsuario;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.resources.Constantes;

@Stateless
public class ClaveUsuarioDAOImpl extends GenericDAOImpl<ClaveUsuario, Long> implements ClaveUsuarioDAO {

	@Override
	public ClaveUsuario obtenerActiva(Usuario usuario) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClaveUsuario> criteria = cb.createQuery(ClaveUsuario.class);
        Root<ClaveUsuario> repoUsuario = criteria.from(ClaveUsuario.class);
    	criteria.select(repoUsuario)
        	.where(
        			cb.and(
        					cb.equal(repoUsuario.get("estado"), Constantes.REGISTRO_ACTIVO)),
        					cb.equal(repoUsuario.get("usuario"), usuario));
    	try {
    		return getEntityManager().createQuery(criteria).getSingleResult();
    	} catch(NoResultException e) {
    		return null;
    	}
	}
	
	@Override
	public List<ClaveUsuario> obtenerClaves(Usuario usuario) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClaveUsuario> criteria = cb.createQuery(ClaveUsuario.class);
        Root<ClaveUsuario> repoUsuario = criteria.from(ClaveUsuario.class);
    	criteria.select(repoUsuario)
        	.where(	cb.equal(repoUsuario.get("usuario"), usuario));
    	try {
    		return getEntityManager().createQuery(criteria).getResultList();
    	} catch(NoResultException e) {
    		return null;
    	}
	}
	
	public long totalClavesUsuario(String identificacionUsuario){
		long total = 0;
		try {
			String sql = "select count(c) from ClaveUsuario c, Usuario u where u.identificacion = :identificacionUsuario and c.usuario = u";
	        Query query = getEntityManager().createQuery(sql);
	        query.setParameter("identificacionUsuario", identificacionUsuario);
	        total = (Long) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return total;
	}
	
	public long totalClavesUsuarioCoincidentes(String clave, boolean soloActivos, boolean soloInactivos){
		long total = 0;
		try {
			String sql = "select count(c) from ClaveUsuario c where c.clave = :clave ";
			if(soloActivos){
				sql = sql + "and c.estado = '"+Constantes.REGISTRO_ACTIVO+"'";
			}
			if(soloInactivos){
				sql = sql + "and c.estado = '"+Constantes.REGISTRO_INACTIVO+"'";
			}
			
	        Query query = getEntityManager().createQuery(sql);
	        query.setParameter("clave", clave);
	        total = (Long) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return total;
	}
	
	public long totalClavesUsuario(Usuario usuario, boolean soloActivos, boolean soloInactivos){
		long total = 0;
		try {
			String sql = "select count(c) from ClaveUsuario c where c.usuario = :usuario ";
			if(soloActivos){
				sql = sql + "and c.estado = '"+Constantes.REGISTRO_ACTIVO+"'";
			}
			if(soloInactivos){
				sql = sql + "and c.estado = '"+Constantes.REGISTRO_INACTIVO+"'";
			}
			
	        Query query = getEntityManager().createQuery(sql);
	        query.setParameter("usuario", usuario);
	        total = (Long) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return total;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> obtenerUltimasClaves(Long codigoUsuario, int cantidadClavesDevolver){
		String sql = "SELECT SCU.CLAVE FROM SEG_CLAVE_USUARIO SCU "
				+ "WHERE "
				+ "SCU.COD_USUARIO = :codigoUsuario "
				+ "AND ROWNUM <= :cantidadClavesDevolver "
				+ "ORDER BY SCU.CODIGO DESC";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter("codigoUsuario", codigoUsuario)
        .setParameter("cantidadClavesDevolver", cantidadClavesDevolver);
        return query.getResultList();
	}
	
	public ClaveUsuario obtenerUltimaInactiva(Usuario usuario){
		try {
			String sql = "select c from ClaveUsuario c where c.usuario = :usuario and c.estado =:estadoInactivo order by c.codigo DESC";
	        Query query = getEntityManager().createQuery(sql);
	        query.setParameter("usuario", usuario)
	        .setParameter("estadoInactivo", Constantes.REGISTRO_INACTIVO)
	        .setMaxResults(1);
	        return (ClaveUsuario) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
