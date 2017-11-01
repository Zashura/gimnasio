package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubHorarioDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubHorario;
import ec.com.gimnasio.service.ClubHorarioService;

@Stateless
public class ClubHorarioServiceImpl implements ClubHorarioService {
	
	@Inject
	private ClubHorarioDAO clubHorarioDAO;
	
	@Override
	public ClubHorario buscarPorId(Long id){
		return clubHorarioDAO.findById(id);
	}
	
	@Override
	public void crear(ClubHorario aplicacion){
		try {
			clubHorarioDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubHorario actualizar(ClubHorario aplicacion){
        	ClubHorario app=new ClubHorario();
			try {
				app = clubHorarioDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubHorario findByCodigo(long codigo){
		return clubHorarioDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubHorario> obtenerActivas() {
		return clubHorarioDAO.obtenerActivas();
	}
}
