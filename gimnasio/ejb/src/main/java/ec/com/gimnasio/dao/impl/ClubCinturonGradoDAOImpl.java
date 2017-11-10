package ec.com.gimnasio.dao.impl;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubCinXGra> listByDisciplina(long codDisciplina){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubCinXGra o,ClubGrado g,ClubCinturon c,ClubCinDi cd,ClubDisciplina d ");
		sentencia.append("where cd.clubDisciplina.disCodigo = d.disCodigo and cd.clubCinturon.cinCodigo=c.cinCodigo "
				+ " and o.clubCinturon.cinCodigo=c.cinCodigo and o.clubGrado.graCodigo=g.graCodigo "
				+ " and o.cigaEstado=:estado and d.disCodigo =:codDisciplina ");
		List<ClubCinXGra> auxList = getEntityManager().createQuery(sentencia.toString()).setParameter("codDisciplina" , codDisciplina).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
		for (ClubCinXGra club : auxList) {
			if(club.getClubCinturon()!=null){
				club.getClubCinturon().getCinCodigo();
				club.getClubCinturon().getCinDescripcion();
			}
			if(club.getClubGrado()!=null){
				club.getClubGrado().getGraCodigo();
				club.getClubGrado().getGraDescripcion();
			}
		}
		return auxList;
	}
	
	
}
