package ec.com.gimnasio.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubIntXDisSedInsDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubIntXDisSedIn;
import ec.com.gimnasio.service.ClubIntXDisSedInsService;

@Stateless
public class ClubIntXDisSedInsServiceImpl implements ClubIntXDisSedInsService {
	
	@Inject
	private ClubIntXDisSedInsDAO clubIntXDisSedInsDAO;
	
	@Override
	public ClubIntXDisSedIn buscarPorId(Long id){
		return clubIntXDisSedInsDAO.findById(id);
	}
	
	@Override
	public void crear(ClubIntXDisSedIn aplicacion){
		try {
			clubIntXDisSedInsDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubIntXDisSedIn actualizar(ClubIntXDisSedIn aplicacion){
		ClubIntXDisSedIn app=new ClubIntXDisSedIn();
			try {
				app = clubIntXDisSedInsDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubIntXDisSedIn findByCodigo(long codigo){
		return clubIntXDisSedInsDAO.findByCodigo(codigo);
	}
}
