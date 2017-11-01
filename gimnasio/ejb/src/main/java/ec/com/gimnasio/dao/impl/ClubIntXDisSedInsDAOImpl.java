package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;

import ec.com.gimnasio.dao.ClubIntXDisSedInsDAO;
import ec.com.gimnasio.model.ClubIntXDisSedIn;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubIntXDisSedInsDAOImpl extends GenericDAOImpl<ClubIntXDisSedIn, Long> implements ClubIntXDisSedInsDAO {
	
	@Override
	public ClubIntXDisSedIn findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubIntXDisSedIn o ");
		sentencia.append("where o.idsiCodigo = :codigo and o.idsiEstado=:estado ");
		ClubIntXDisSedIn aplicacion = (ClubIntXDisSedIn) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
