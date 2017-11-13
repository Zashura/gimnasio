package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubHorDia;

/**
*
* */
@Local
public interface ClubHoraDiaDAO  extends GenericDAO<ClubHorDia, Long> {
	
	public ClubHorDia findByCodigo(long codigo);
	
	public List<ClubHorDia> obtenerActivas();
	
	public ClubHorDia findByDiaHora(long codDia,long codHora);


}
