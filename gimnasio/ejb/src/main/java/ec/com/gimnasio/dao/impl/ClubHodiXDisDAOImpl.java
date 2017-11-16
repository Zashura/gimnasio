package ec.com.gimnasio.dao.impl;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubHodiXDissedclub> findBySedeDisciplina(long codSede, long codDisciplina){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubHodiXDissedclub o ,ClubDisXSedIn ds, ClubSedIn cs ");
		sentencia.append(" where o.clubDisXSedIn.disiCodigo=ds.disiCodigo and ds.clubSedIn.seinCodigo=cs.seinCodigo"
				+ " and ds.clubDisciplina.disCodigo=:codDisciplina and cs.clubSede.sedCodigo=:codSede and o.dihoEstado=:estado ");
		List<ClubHodiXDissedclub>  aux= getEntityManager().createQuery(sentencia.toString()).setParameter("codDisciplina" , codDisciplina).setParameter("codSede" , codSede).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
		for (ClubHodiXDissedclub club : aux) {
			if(club.getClubHorDia()!=null){
				club.getClubHorDia().getHodiCodigo();
				club.getClubHorDia().getClubDia().getDiaCodigo();
				club.getClubHorDia().getClubDia().getDiaDescripcion();
				club.getClubHorDia().getClubHorario().getHorCodigo();
				club.getClubHorDia().getClubHorario().getHorHoraInicio();
				club.getClubHorDia().getClubHorario().getHorHoraFin();
			}
			if(club.getClubIntXDisSedIns().size()>0){
				club.getClubIntXDisSedIns().get(0).getIdsiCodigo();
				club.getClubIntXDisSedIns().get(0).getClubInstructor().getIntCodigo();
				club.getClubIntXDisSedIns().get(0).getClubInstructor().getIntNombres();
				club.getClubIntXDisSedIns().get(0).getClubInstructor().getIntIdentificacion();
			}
		}
		return aux;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubHodiXDissedclub> findByInstitucionPersona(long codInstitucion, long codPersona){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubHodiXDissedclub o ,ClubDisXSedIn ds, ClubSedIn cs,ClubInsDisSedClub ci,ClubInscripcion i ");
		sentencia.append(" where o.clubDisXSedIn.disiCodigo=ds.disiCodigo and ds.clubSedIn.seinCodigo=cs.seinCodigo and ci.clubDisXSedIn.disiCodigo=ds.disiCodigo"
				+ " and ci.clubInscripcion.insCodigo=i.insCodigo and i.clubPersona.perCodigo=:codPersona and cs.clubInstitucion.cluCodigo=:codInstitucion and o.dihoEstado=:estado ");
		List<ClubHodiXDissedclub>  aux= getEntityManager().createQuery(sentencia.toString()).setParameter("codInstitucion" , codInstitucion).setParameter("codPersona" , codPersona).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
		for (ClubHodiXDissedclub club : aux) {
			if(club.getClubHorDia()!=null){
				club.getClubHorDia().getHodiCodigo();
				club.getClubHorDia().getClubDia().getDiaCodigo();
				club.getClubHorDia().getClubDia().getDiaDescripcion();
				club.getClubHorDia().getClubHorario().getHorCodigo();
				club.getClubHorDia().getClubHorario().getHorHoraInicio();
				club.getClubHorDia().getClubHorario().getHorHoraFin();
			}
			if(club.getClubIntXDisSedIns().size()>0){
				club.getClubIntXDisSedIns().get(0).getIdsiCodigo();
				club.getClubIntXDisSedIns().get(0).getClubInstructor().getIntCodigo();
				club.getClubIntXDisSedIns().get(0).getClubInstructor().getIntNombres();
				club.getClubIntXDisSedIns().get(0).getClubInstructor().getIntIdentificacion();
			}
			if(club.getClubDisXSedIn()!=null){
				club.getClubDisXSedIn().getDisiCodigo();
				club.getClubDisXSedIn().getClubSedIn().getSeinCodigo();
				club.getClubDisXSedIn().getClubSedIn().getClubSede().getSedCodigo();
				club.getClubDisXSedIn().getClubSedIn().getClubSede().getSedDescripcion();
				club.getClubDisXSedIn().getClubDisciplina().getDisCodigo();
				club.getClubDisXSedIn().getClubDisciplina().getDisDescripcion();
			}
		}
		return aux;
	}
	
	
}
