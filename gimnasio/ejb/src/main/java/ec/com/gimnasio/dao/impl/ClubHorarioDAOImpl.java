package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubHorarioDAO;
import ec.com.gimnasio.model.ClubHorario;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubHorarioDAOImpl extends GenericDAOImpl<ClubHorario, Long> implements ClubHorarioDAO {

	@Override
	public List<ClubHorario> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubHorario> criteria = cb.createQuery(ClubHorario.class);
        Root<ClubHorario> aplicacion = criteria.from(ClubHorario.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("horEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("diaDescripcion")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubHorario findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubHorario o ");
		sentencia.append("where o.horCodigo = :codigo and o.horEstado=:estado ");
		ClubHorario aplicacion = (ClubHorario) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
