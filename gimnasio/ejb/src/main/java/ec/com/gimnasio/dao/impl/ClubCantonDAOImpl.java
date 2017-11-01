package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubCantonDAO;
import ec.com.gimnasio.model.ClubCanton;
import ec.com.gimnasio.resources.Constantes;

/**
*
* @author
*/
@Stateless
public class ClubCantonDAOImpl extends GenericDAOImpl<ClubCanton, Long> implements ClubCantonDAO {

	@Override
	public List<ClubCanton> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubCanton> criteria = cb.createQuery(ClubCanton.class);
        Root<ClubCanton> aplicacion = criteria.from(ClubCanton.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("canEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("canDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubCanton> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubCanton> criteria = cb.createQuery(ClubCanton.class);
        Root<ClubCanton> aplicacion = criteria.from(ClubCanton.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("canDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("canDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubCanton> buscarPorDpaProvincia(long codProvincia) {
		StringBuilder sentencia = new StringBuilder().append("select o from ClubCanton o ");
		sentencia.append("where o.clubProvincia.proCodigo= :codProvincia and o.canEstado=:estado ");
		return (List<ClubCanton>) getEntityManager().createQuery(sentencia.toString()).setParameter("codProvincia" , codProvincia).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
	}
	
	@Override
	public ClubCanton findByCodigo(long codigo){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubCanton o ");
			sentencia.append("where o.canCodigo = :codigo and o.canEstado=:estado");
			return (ClubCanton) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ClubCanton buscarPorDpa(String dpa){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubCanton o ");
			sentencia.append("where o.canDpa = :dpa and o.canEstado=:estado");
			return (ClubCanton) getEntityManager().createQuery(sentencia.toString()).setParameter("dpa" , dpa).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
