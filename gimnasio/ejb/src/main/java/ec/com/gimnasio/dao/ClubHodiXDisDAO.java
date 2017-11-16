package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubHodiXDissedclub;

/**
*
* */
@Local
public interface ClubHodiXDisDAO  extends GenericDAO<ClubHodiXDissedclub, Long> {
	
	public ClubHodiXDissedclub findByCodigo(long codigo);
	
	public List<ClubHodiXDissedclub> findBySedeDisciplina(long codSede, long codDisciplina);
	
	public List<ClubHodiXDissedclub> findByInstitucionPersona(long codInstitucion, long codPersona);

}
