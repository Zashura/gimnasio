package ec.com.gimnasio.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubDisXSedInsDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubDisXSedIn;
import ec.com.gimnasio.service.ClubDisXSedInsService;

@Stateless
public class ClubDisXSedInServiceImpl implements ClubDisXSedInsService {
	
	@Inject
	private ClubDisXSedInsDAO clubDisXSedInsDAO;
	
	@Override
	public ClubDisXSedIn buscarPorId(Long id){
		return clubDisXSedInsDAO.findById(id);
	}
	
	@Override
	public void crear(ClubDisXSedIn aplicacion){
		try {
			clubDisXSedInsDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubDisXSedIn actualizar(ClubDisXSedIn aplicacion){
		ClubDisXSedIn app=new ClubDisXSedIn();
			try {
				app = clubDisXSedInsDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubDisXSedIn findByCodigo(long codigo){
		return clubDisXSedInsDAO.findByCodigo(codigo);
	}
}
