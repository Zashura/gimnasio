package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubDisciplinaDAO;
import ec.com.gimnasio.model.ClubDisciplina;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubDisciplinaDAOImpl extends GenericDAOImpl<ClubDisciplina, Long> implements ClubDisciplinaDAO {

	@Override
	public List<ClubDisciplina> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubDisciplina> criteria = cb.createQuery(ClubDisciplina.class);
        Root<ClubDisciplina> aplicacion = criteria.from(ClubDisciplina.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("disEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("disDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<ClubDisciplina> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubDisciplina> criteria = cb.createQuery(ClubDisciplina.class);
        Root<ClubDisciplina> aplicacion = criteria.from(ClubDisciplina.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("disDescripcion"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("disDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubDisciplina findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubDisciplina o ");
		sentencia.append("where o.disCodigo = :codigo and o.disEstado=:estado ");
		ClubDisciplina aplicacion = (ClubDisciplina) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubDisciplina> listBySede(long codigoSede){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubDisciplina o,ClubSedIn c, ClubDisXSedIn i ");
		sentencia.append("where i.clubDisciplina.disCodigo=o.disCodigo and i.clubSedIn.seinCodigo=c.seinCodigo"
				+ " and c.clubSede.sedCodigo = :codigo and i.disiEstado=:estado ");
		return getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigoSede).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
		 
	}
	
	
}
