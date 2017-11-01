package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;

import ec.com.gimnasio.dao.ClubDisXSedInsDAO;
import ec.com.gimnasio.model.ClubDisXSedIn;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubDisXSedInsDAOImpl extends GenericDAOImpl<ClubDisXSedIn, Long> implements ClubDisXSedInsDAO {
	
	@Override
	public ClubDisXSedIn findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubDisXSedIn o ");
		sentencia.append("where o.disiCodigo = :codigo and o.disiEstado=:estado ");
		ClubDisXSedIn aplicacion = (ClubDisXSedIn) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
