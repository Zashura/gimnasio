package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubInstructorDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInstructor;
import ec.com.gimnasio.service.ClubInstructorService;

@Stateless
public class ClubInstructorServiceImpl implements ClubInstructorService {
	
	@Inject
	private ClubInstructorDAO clubInstructorDAO;
	
	@Override
	public ClubInstructor buscarPorId(Long id){
		return clubInstructorDAO.findById(id);
	}
	
	@Override
	public void crear(ClubInstructor aplicacion){
		try {
			clubInstructorDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubInstructor actualizar(ClubInstructor aplicacion){
        	ClubInstructor app=new ClubInstructor();
			try {
				app = clubInstructorDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubInstructor findByCodigo(long codigo){
		return clubInstructorDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubInstructor> obtenerActivas() {
		return clubInstructorDAO.obtenerActivas();
	}

	@Override
	public List<ClubInstructor> buscarPorNombre(String nombre) {
		return clubInstructorDAO.buscarPorNombre(nombre);
	}
}
