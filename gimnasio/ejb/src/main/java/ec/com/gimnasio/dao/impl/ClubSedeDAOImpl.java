package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubSedeDAO;
import ec.com.gimnasio.model.ClubSede;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubSedeDAOImpl extends GenericDAOImpl<ClubSede, Long> implements ClubSedeDAO {

	@Override
	public List<ClubSede> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubSede> criteria = cb.createQuery(ClubSede.class);
        Root<ClubSede> aplicacion = criteria.from(ClubSede.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("sedEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("sedDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubSede> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubSede> criteria = cb.createQuery(ClubSede.class);
        Root<ClubSede> aplicacion = criteria.from(ClubSede.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("sedDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("sedDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubSede findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubSede o ");
		sentencia.append("where o.sedCodigo = :codigo and o.sedEstado=:estado ");
		ClubSede aplicacion = (ClubSede) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubSede> findByInstitucion(long codInstitucion){
			StringBuilder sentencia = new StringBuilder().append("select o from ClubSede o, ClubSedIn ci, ClubInstitucion i ");
			sentencia.append("where ci.clubSede.sedCodigo=o.sedCodigo and ci.clubInstitucion.cluCodigo=i.cluCodigo ");
			sentencia.append("and i.cluCodigo = :codigo and o.sedEstado=:estado ");
			return getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codInstitucion).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
	}

	@Override
	public ClubSede findByNemonico(String nemonico) {
		StringBuilder sentencia = new StringBuilder().append("select o from ClubSede o ");
		sentencia.append("where o.sedNemonico = :nemonico and o.sedEstado=:estado ");
		ClubSede aplicacion = (ClubSede) getEntityManager().createQuery(sentencia.toString()).setParameter("nemonico" , nemonico).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
}
