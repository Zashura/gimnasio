package ec.com.gimnasio.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubCinturonDisciplinaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCinDi;
import ec.com.gimnasio.service.ClubCinturonDisciplinaService;

@Stateless
public class ClubCinturonDisciplinaServiceImpl implements ClubCinturonDisciplinaService {
	
	@Inject
	private ClubCinturonDisciplinaDAO clubCinturonDisciplinaDAO;
	
	@Override
	public ClubCinDi buscarPorId(Long id){
		return clubCinturonDisciplinaDAO.findById(id);
	}
	
	@Override
	public void crear(ClubCinDi aplicacion){
		try {
			clubCinturonDisciplinaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubCinDi actualizar(ClubCinDi aplicacion){
		ClubCinDi app=new ClubCinDi();
			try {
				app = clubCinturonDisciplinaDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubCinDi findByCodigo(long codigo){
		return clubCinturonDisciplinaDAO.findByCodigo(codigo);
	}
}
