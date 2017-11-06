package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import ec.com.gimnasio.dao.ClubSedInsDAO;
import ec.com.gimnasio.model.ClubSedIn;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubSedInsDAOImpl extends GenericDAOImpl<ClubSedIn, Long> implements ClubSedInsDAO {

	@Override
	public ClubSedIn findByCodigo(long codigo){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubSedIn o ");
			sentencia.append("where o.seinCodigo = :codigo and o.seinEstado=:estado");
			return (ClubSedIn) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ClubSedIn findByCodigoInstucionSede(long codInst,long codSede){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from ClubSedIn o ");
			sentencia.append("where o.clubInstitucion.cluCodigo=:codInst and o.clubSede.sedCodigo=:codSede  and o.seinEstado=:estado");
			return (ClubSedIn) getEntityManager().createQuery(sentencia.toString()).setParameter("codInst" , codInst).setParameter("codSede" , codSede).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
