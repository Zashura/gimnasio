package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;

import ec.com.gimnasio.dao.ClubCinturonDisciplinaDAO;
import ec.com.gimnasio.model.ClubCinDi;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubCinturonDisciplinaDAOImpl extends GenericDAOImpl<ClubCinDi, Long> implements ClubCinturonDisciplinaDAO {
	
	@Override
	public ClubCinDi findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubCinDi o ");
		sentencia.append("where o.cidiCodigo = :codigo and o.cidiEstado=:estado ");
		ClubCinDi aplicacion = (ClubCinDi) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
