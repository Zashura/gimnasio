package ec.com.gimnasio.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubHodiXDisDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubHodiXDissedclub;
import ec.com.gimnasio.service.ClubHodiXDisService;

@Stateless
public class ClubHodiXDisServiceImpl implements ClubHodiXDisService {
	
	@Inject
	private ClubHodiXDisDAO clubHodiXDisDAO;
	
	@Override
	public ClubHodiXDissedclub buscarPorId(Long id){
		return clubHodiXDisDAO.findById(id);
	}
	
	@Override
	public void crear(ClubHodiXDissedclub aplicacion){
		try {
			clubHodiXDisDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubHodiXDissedclub actualizar(ClubHodiXDissedclub aplicacion){
		ClubHodiXDissedclub app=new ClubHodiXDissedclub();
			try {
				app = clubHodiXDisDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubHodiXDissedclub findByCodigo(long codigo){
		return clubHodiXDisDAO.findByCodigo(codigo);
	}
}
