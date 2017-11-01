package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubDiaDAO;
import ec.com.gimnasio.model.ClubDia;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubDiaDAOImpl extends GenericDAOImpl<ClubDia, Long> implements ClubDiaDAO {

	@Override
	public List<ClubDia> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubDia> criteria = cb.createQuery(ClubDia.class);
        Root<ClubDia> aplicacion = criteria.from(ClubDia.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("diaEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("diaCodigo")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubDia> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubDia> criteria = cb.createQuery(ClubDia.class);
        Root<ClubDia> aplicacion = criteria.from(ClubDia.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("diaDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("diaDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubDia findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubDia o ");
		sentencia.append("where o.diaCodigo = :codigo and o.diaEstado=:estado ");
		ClubDia aplicacion = (ClubDia) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
