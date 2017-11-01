package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubHorario;

/**
*
* */
@Local
public interface ClubHorarioDAO  extends GenericDAO<ClubHorario, Long> {
	
	public List<ClubHorario> obtenerActivas();
	
	public ClubHorario findByCodigo(long codigo);


}
