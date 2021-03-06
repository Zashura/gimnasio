package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubHoraDiaDAO;
import ec.com.gimnasio.model.ClubHorDia;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubHoraDiaDAOImpl extends GenericDAOImpl<ClubHorDia, Long> implements ClubHoraDiaDAO {
	
	@Override
	public ClubHorDia findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubHorDia o ");
		sentencia.append("where o.hodiCodigo = :codigo and o.hodiEstado=:estado ");
		ClubHorDia aplicacion = (ClubHorDia) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		if(aplicacion.getClubHorario()!=null){
			aplicacion.getClubHorario().getHorCodigo();
			aplicacion.getClubHorario().getHorHoraFin();
			aplicacion.getClubHorario().getHorHoraInicio();
		}
		if(aplicacion.getClubDia()!=null){
			aplicacion.getClubDia().getDiaCodigo();
			aplicacion.getClubDia().getDiaDescripcion();
		}
		
		return aplicacion;
	}
	
	@Override
	public List<ClubHorDia> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubHorDia> criteria = cb.createQuery(ClubHorDia.class);
        Root<ClubHorDia> aplicacion = criteria.from(ClubHorDia.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("hodiEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("hodiCodigo")));
        List<ClubHorDia> aux= getEntityManager().createQuery(criteria).getResultList();
        for (ClubHorDia item : aux) {
			if(item.getClubHorario()!=null){
				item.getClubHorario().getHorCodigo();
				item.getClubHorario().getHorHoraFin();
				item.getClubHorario().getHorHoraInicio();
			}
			if(item.getClubDia()!=null){
				item.getClubDia().getDiaCodigo();
				item.getClubDia().getDiaDescripcion();
			}
		}
        return aux;
	}
	
	@Override
	public ClubHorDia findByDiaHora(long codDia,long codHora){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubHorDia o ");
		sentencia.append("where o.clubDia.diaCodigo = :codDia and o.clubHorario.horCodigo=:codHora and o.hodiEstado=:estado ");
		ClubHorDia aplicacion = (ClubHorDia) getEntityManager().createQuery(sentencia.toString()).setParameter("codHora" , codHora).setParameter("codDia" , codDia).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
