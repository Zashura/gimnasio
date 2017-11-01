package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubDisciplinaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubDisciplina;
import ec.com.gimnasio.service.ClubDisciplinaService;

@Stateless
public class ClubDisciplinaServiceImpl implements ClubDisciplinaService {
	
	@Inject
	private ClubDisciplinaDAO clubDisciplinaDAO;
	
	@Override
	public ClubDisciplina buscarPorId(Long id){
		return clubDisciplinaDAO.findById(id);
	}
	
	@Override
	public void crear(ClubDisciplina aplicacion){
		try {
			clubDisciplinaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubDisciplina actualizar(ClubDisciplina aplicacion){
        	ClubDisciplina app=new ClubDisciplina();
			try {
				app = clubDisciplinaDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubDisciplina findByCodigo(long codigo){
		return clubDisciplinaDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubDisciplina> obtenerActivas() {
		return clubDisciplinaDAO.obtenerActivas();
	}

	@Override
	public List<ClubDisciplina> buscarPorNombre(String nombre) {
		return clubDisciplinaDAO.buscarPorNombre(nombre);
	}
	@Override
	public List<ClubDisciplina> listBySede(long codigoSede){
		return clubDisciplinaDAO.listBySede(codigoSede);
	}
}
