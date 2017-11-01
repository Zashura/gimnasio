package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubTipoDisciplinaDAO;
import ec.com.gimnasio.model.ClubTipDi;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubTipoDisciplinaDAOImpl extends GenericDAOImpl<ClubTipDi, Long> implements ClubTipoDisciplinaDAO {

	@Override
	public List<ClubTipDi> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubTipDi> criteria = cb.createQuery(ClubTipDi.class);
        Root<ClubTipDi> aplicacion = criteria.from(ClubTipDi.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("tidiEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("tidiDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubTipDi> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubTipDi> criteria = cb.createQuery(ClubTipDi.class);
        Root<ClubTipDi> aplicacion = criteria.from(ClubTipDi.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("tidiDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("tidiDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubTipDi findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubTipDi o ");
		sentencia.append("where o.tidiCodigos = :codigo and o.tidiEstado=:estado ");
		ClubTipDi aplicacion = (ClubTipDi) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
