package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubGradoDAO;
import ec.com.gimnasio.model.ClubGrado;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubGradoDAOImpl extends GenericDAOImpl<ClubGrado, Long> implements ClubGradoDAO {

	@Override
	public List<ClubGrado> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubGrado> criteria = cb.createQuery(ClubGrado.class);
        Root<ClubGrado> aplicacion = criteria.from(ClubGrado.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("graEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("graDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubGrado> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubGrado> criteria = cb.createQuery(ClubGrado.class);
        Root<ClubGrado> aplicacion = criteria.from(ClubGrado.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("graDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("graDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubGrado findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubGrado o ");
		sentencia.append("where o.graCodigo = :codigo and o.graEstado=:estado ");
		ClubGrado aplicacion = (ClubGrado) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
