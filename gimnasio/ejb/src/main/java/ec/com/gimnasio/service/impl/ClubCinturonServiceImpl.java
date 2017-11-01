package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubCinturonDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCinturon;
import ec.com.gimnasio.service.ClubCinturonService;

@Stateless
public class ClubCinturonServiceImpl implements ClubCinturonService {
	
	@Inject
	private ClubCinturonDAO clubCinturonDAO;
	
	@Override
	public ClubCinturon buscarPorId(Long id){
		return clubCinturonDAO.findById(id);
	}
	
	@Override
	public void crear(ClubCinturon aplicacion){
		try {
			clubCinturonDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubCinturon actualizar(ClubCinturon aplicacion){
        	ClubCinturon app=new ClubCinturon();
			try {
				app = clubCinturonDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubCinturon findByCodigo(long codigo){
		return clubCinturonDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubCinturon> obtenerActivas() {
		return clubCinturonDAO.obtenerActivas();
	}

	@Override
	public List<ClubCinturon> buscarPorNombre(String nombre) {
		return clubCinturonDAO.buscarPorNombre(nombre);
	}
}
