package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;

import ec.com.gimnasio.dao.ClubInsDisSedClubDAO;
import ec.com.gimnasio.model.ClubInsDisSedClub;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubInsDisSedClubDAOImpl extends GenericDAOImpl<ClubInsDisSedClub, Long> implements ClubInsDisSedClubDAO {
	
	@Override
	public ClubInsDisSedClub findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubInsDisSedClub o ");
		sentencia.append("where o.idsiCodigo = :codigo and o.idsiEstado=:estado ");
		ClubInsDisSedClub aplicacion = (ClubInsDisSedClub) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
