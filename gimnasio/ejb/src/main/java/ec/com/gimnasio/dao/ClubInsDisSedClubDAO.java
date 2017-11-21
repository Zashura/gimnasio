package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubInsDisSedClub;

/**
*
* */
@Local
public interface ClubInsDisSedClubDAO  extends GenericDAO<ClubInsDisSedClub, Long> {
	
	public ClubInsDisSedClub findByCodigo(long codigo);
	
	public List<ClubInsDisSedClub> findPersonaInstitucion(long codPersona,long codInstitucion);
	
	public ClubInsDisSedClub findByInscripcionHorarioDisciplina(long codInscripcion,long codHorario,long codDisciplina);


}
