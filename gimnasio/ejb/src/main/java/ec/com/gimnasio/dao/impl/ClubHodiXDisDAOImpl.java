package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;

import ec.com.gimnasio.dao.ClubHodiXDisDAO;
import ec.com.gimnasio.model.ClubHodiXDissedclub;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubHodiXDisDAOImpl extends GenericDAOImpl<ClubHodiXDissedclub, Long> implements ClubHodiXDisDAO {
	
	@Override
	public ClubHodiXDissedclub findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubHodiXDissedclub o ");
		sentencia.append("where o.dihoCodigo = :codigo and o.dihoEstado=:estado ");
		ClubHodiXDissedclub aplicacion = (ClubHodiXDissedclub) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
