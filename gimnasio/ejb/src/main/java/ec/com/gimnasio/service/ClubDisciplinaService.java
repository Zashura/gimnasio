package ec.com.gimnasio.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubDisciplina;

@Local
public interface ClubDisciplinaService {
	
	public ClubDisciplina buscarPorId(Long id);
	
	public void crear(ClubDisciplina aplicacion)throws ClubPersistException;
	
	public ClubDisciplina actualizar(ClubDisciplina aplicacion)throws ClubUpdateException;
	
	public List<ClubDisciplina> obtenerActivas();
	
	public List<ClubDisciplina> buscarPorNombre(String nombre);
	
	public ClubDisciplina findByCodigo(long codigo);
	
	public List<ClubDisciplina> listBySede(long codigoSede);
}
