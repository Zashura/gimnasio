package ec.com.gimnasio.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubInscripcionDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInscripcion;
import ec.com.gimnasio.service.ClubInscripcionService;

@Stateless
public class ClubInscripcionServiceImpl implements ClubInscripcionService {
	
	@Inject
	private ClubInscripcionDAO clubInscripcionDAO;
	
	@Override
	public ClubInscripcion buscarPorId(Long id){
		return clubInscripcionDAO.findById(id);
	}
	
	@Override
	public void crear(ClubInscripcion aplicacion){
		try {
			clubInscripcionDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubInscripcion actualizar(ClubInscripcion aplicacion){
		ClubInscripcion app=new ClubInscripcion();
			try {
				app = clubInscripcionDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubInscripcion findByCodigo(long codigo){
		return clubInscripcionDAO.findByCodigo(codigo);
	}
	
	@Override
	public ClubInscripcion findByPersona(long codigo){
		return clubInscripcionDAO.findByPersona(codigo);
	}
}
