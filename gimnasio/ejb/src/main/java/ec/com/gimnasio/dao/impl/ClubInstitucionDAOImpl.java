package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubInstitucionDAO;
import ec.com.gimnasio.model.ClubInstitucion;
import ec.com.gimnasio.resources.Constantes;

/**
*
* @author
*/
@Stateless
public class ClubInstitucionDAOImpl extends GenericDAOImpl<ClubInstitucion, Long> implements ClubInstitucionDAO {

	@Override
	public List<ClubInstitucion> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubInstitucion> criteria = cb.createQuery(ClubInstitucion.class);
        Root<ClubInstitucion> aplicacion = criteria.from(ClubInstitucion.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("cluEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("cluDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubInstitucion> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubInstitucion> criteria = cb.createQuery(ClubInstitucion.class);
        Root<ClubInstitucion> aplicacion = criteria.from(ClubInstitucion.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("cluDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("cluDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubInstitucion findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubInstitucion o ");
		sentencia.append("where o.cluCodigo = :codigo and cluEstado=:estado ");
		ClubInstitucion aplicacion = (ClubInstitucion) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	@Override
	public ClubInstitucion findByCodigoCas(long codigo){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubInstitucion o ");
			sentencia.append("where o.cluCodCas = :codigo and cluEstado=:estado ");
			ClubInstitucion aplicacion = (ClubInstitucion) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
		}catch (NoResultException e){
			return null;
		}
		
	}
	
}
