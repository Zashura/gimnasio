package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubRepresentanteDAO;
import ec.com.gimnasio.model.ClubRepresentante;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubRepresentanteDAOImpl extends GenericDAOImpl<ClubRepresentante, Long> implements ClubRepresentanteDAO {

	@Override
	public List<ClubRepresentante> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubRepresentante> criteria = cb.createQuery(ClubRepresentante.class);
        Root<ClubRepresentante> aplicacion = criteria.from(ClubRepresentante.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("repEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("repNombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubRepresentante> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubRepresentante> criteria = cb.createQuery(ClubRepresentante.class);
        Root<ClubRepresentante> aplicacion = criteria.from(ClubRepresentante.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("repNombres"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("repNombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubRepresentante findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubRepresentante o ");
		sentencia.append("where o.repCodigo = :codigo and o.repEstado=:estado ");
		ClubRepresentante aplicacion = (ClubRepresentante) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
