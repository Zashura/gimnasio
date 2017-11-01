package ec.com.gimnasio.dao.impl;

import javax.ejb.Stateless;

import ec.com.gimnasio.dao.ClubCinturonGradoDAO;
import ec.com.gimnasio.model.ClubCinXGra;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubCinturonGradoDAOImpl extends GenericDAOImpl<ClubCinXGra, Long> implements ClubCinturonGradoDAO {
	
	@Override
	public ClubCinXGra findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubCinXGra o ");
		sentencia.append("where o.cigaCodigo = :codigo and o.cigaEstado=:estado ");
		ClubCinXGra aplicacion = (ClubCinXGra) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
