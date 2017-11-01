package ec.com.gimnasio.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.model.ClubDisciplina;

/**
*
* */
@Local
public interface ClubDisciplinaDAO  extends GenericDAO<ClubDisciplina, Long> {
	
	public List<ClubDisciplina> obtenerActivas();
	
	public List<ClubDisciplina> buscarPorNombre(String nombre);
	
	public ClubDisciplina findByCodigo(long codigo);
	
	public List<ClubDisciplina> listBySede(long codigoSede);


}
