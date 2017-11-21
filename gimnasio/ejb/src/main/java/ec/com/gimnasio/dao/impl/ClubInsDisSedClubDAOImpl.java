package ec.com.gimnasio.dao.impl;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubInsDisSedClub> findPersonaInstitucion(long codPersona,long codInstitucion){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubInsDisSedClub o,ClubInscripcion i,ClubDisXSedIn cd,ClubSedIn si");
				sentencia.append(" where o.clubInscripcion.insCodigo=i.insCodigo and o.clubDisXSedIn.disiCodigo=cd.disiCodigo and cd.clubSedIn.seinCodigo=si.seinCodigo");
				sentencia.append( " and si.clubInstitucion.cluCodigo=:codInstitucion and i.clubPersona.perCodigo=:codPersona");
				sentencia.append(" and o.idsiEstado=:estado ");
				List<ClubInsDisSedClub>  aux= getEntityManager().createQuery(sentencia.toString()).setParameter("codPersona" , codPersona).setParameter("codInstitucion" , codInstitucion).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
		
		for (ClubInsDisSedClub item : aux) {
			if(item.getClubDisXSedIn().getClubSedIn().getClubSede()!=null){
				item.getClubDisXSedIn().getClubSedIn().getClubSede().getSedCodigo();
				item.getClubDisXSedIn().getClubSedIn().getClubSede().getSedDescripcion();
			}
			if(item.getClubDisXSedIn().getClubDisciplina()!=null){
				item.getClubDisXSedIn().getClubDisciplina().getDisCodigo();
				item.getClubDisXSedIn().getClubDisciplina().getDisDescripcion();
			}
		}		
		return aux;
	}
	
	@Override
	public ClubInsDisSedClub findByInscripcionHorarioDisciplina(long codInscripcion,long codHorario,long codDisciplina){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubInsDisSedClub o ");
		sentencia.append("where o.clubInscripcion.insCodigo = :codInscripcion and o.hodiCodigo=:codHorario and o.clubDisXSedIn.disiCodigo=:codDisciplina and o.idsiEstado=:estado ");
		ClubInsDisSedClub aplicacion = (ClubInsDisSedClub) getEntityManager().createQuery(sentencia.toString()).setParameter("codInscripcion" , codInscripcion)
				.setParameter("codHorario" , codHorario).setParameter("codDisciplina" , codDisciplina).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
