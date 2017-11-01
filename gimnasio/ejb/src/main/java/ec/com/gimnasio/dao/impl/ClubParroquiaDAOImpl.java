package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubParroquiaDAO;
import ec.com.gimnasio.model.ClubParroquia;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubParroquiaDAOImpl extends GenericDAOImpl<ClubParroquia, Long> implements ClubParroquiaDAO {

	@Override
	public List<ClubParroquia> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubParroquia> criteria = cb.createQuery(ClubParroquia.class);
        Root<ClubParroquia> aplicacion = criteria.from(ClubParroquia.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("parEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("parDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubParroquia> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubParroquia> criteria = cb.createQuery(ClubParroquia.class);
        Root<ClubParroquia> aplicacion = criteria.from(ClubParroquia.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("parDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("parDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubParroquia> buscarPorCodCanton(long codCanton) {
		StringBuilder sentencia = new StringBuilder().append("select o from ClubParroquia o ");
		sentencia.append("where o.clubCanton.canCodigo= :codCanton and o.parEstado=:estado ");
		return (List<ClubParroquia>) getEntityManager().createQuery(sentencia.toString()).setParameter("codCanton" , codCanton).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
	}
	
	@Override
	public ClubParroquia findByCodigo(long codigo){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubParroquia o ");
			sentencia.append("where o.parCodigo = :codigo and o.parEstado=:estado");
			return (ClubParroquia) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ClubParroquia buscarPorDpa(String dpa){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubParroquia o ");
			sentencia.append("where o.parDpa = :dpa and o.parEstado=:estado");
			return (ClubParroquia) getEntityManager().createQuery(sentencia.toString()).setParameter("dpa" , dpa).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
