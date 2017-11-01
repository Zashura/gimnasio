package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubProvinciaDAO;
import ec.com.gimnasio.model.ClubProvincia;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubProvinciaDAOImpl extends GenericDAOImpl<ClubProvincia, Long> implements ClubProvinciaDAO {

	@Override
	public List<ClubProvincia> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubProvincia> criteria = cb.createQuery(ClubProvincia.class);
        Root<ClubProvincia> aplicacion = criteria.from(ClubProvincia.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("proEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("proDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubProvincia> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubProvincia> criteria = cb.createQuery(ClubProvincia.class);
        Root<ClubProvincia> aplicacion = criteria.from(ClubProvincia.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("proDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("proDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubProvincia buscarPorDpa(String dpa){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubProvincia o ");
			sentencia.append("where o.proDpa = :dpa and o.proEstado=:estado ");
			return (ClubProvincia) getEntityManager().createQuery(sentencia.toString()).setParameter("dpa" ,dpa).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch (NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ClubProvincia findByCodigo(long codigo){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubProvincia o ");
			sentencia.append("where o.codigo = :codigo and o.proEstado=:estado ");
			return (ClubProvincia) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
}
