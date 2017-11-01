package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubSexoDAO;
import ec.com.gimnasio.model.ClubSexo;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubSexoDAOImpl extends GenericDAOImpl<ClubSexo, Long> implements ClubSexoDAO {

	@Override
	public List<ClubSexo> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubSexo> criteria = cb.createQuery(ClubSexo.class);
        Root<ClubSexo> aplicacion = criteria.from(ClubSexo.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("sexEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("sexDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubSexo> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubSexo> criteria = cb.createQuery(ClubSexo.class);
        Root<ClubSexo> aplicacion = criteria.from(ClubSexo.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("sexDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("sexDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubSexo findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubSexo o ");
		sentencia.append("where o.sexCodigo = :codigo and o.sexEstado=:estado ");
		ClubSexo aplicacion = (ClubSexo) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
