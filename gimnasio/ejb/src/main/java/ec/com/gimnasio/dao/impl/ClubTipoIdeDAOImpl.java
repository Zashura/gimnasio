package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubTipoIdeDAO;
import ec.com.gimnasio.model.ClubTipIde;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubTipoIdeDAOImpl extends GenericDAOImpl<ClubTipIde, Long> implements ClubTipoIdeDAO {

	@Override
	public List<ClubTipIde> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubTipIde> criteria = cb.createQuery(ClubTipIde.class);
        Root<ClubTipIde> aplicacion = criteria.from(ClubTipIde.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("tiinEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("tiinDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubTipIde> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubTipIde> criteria = cb.createQuery(ClubTipIde.class);
        Root<ClubTipIde> aplicacion = criteria.from(ClubTipIde.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("tiinDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("tiinDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubTipIde findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubTipIde o ");
		sentencia.append("where o.tiinCodigo = :codigo and o.tiinEstado=:estado ");
		ClubTipIde aplicacion = (ClubTipIde) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
