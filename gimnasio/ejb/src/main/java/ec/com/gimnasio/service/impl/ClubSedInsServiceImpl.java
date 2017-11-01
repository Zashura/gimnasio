package ec.com.gimnasio.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubSedInsDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubSedIn;
import ec.com.gimnasio.service.ClubSedInsService;

@Stateless
public class ClubSedInsServiceImpl implements ClubSedInsService {
	
	@Inject
	private ClubSedInsDAO clubSedInsDAO;
	
	@Override
	public ClubSedIn findByCodigo(long id) {
		return clubSedInsDAO.findByCodigo(id);
	}

	@Override	
	public void crear(ClubSedIn aplicacion){
        try {
        	clubSedInsDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubSedIn actualizar(ClubSedIn aplicacion){
        try {
        	ClubSedIn app = clubSedInsDAO.update(aplicacion);
			return app;
		} catch (ClubUpdateException e) {
			e.printStackTrace();
			return null;
		}
    }
}
