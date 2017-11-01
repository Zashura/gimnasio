package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubPersonaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubPersona;
import ec.com.gimnasio.service.ClubPersonaService;

@Stateless
public class ClubPersonaServiceImpl implements ClubPersonaService {
	
	@Inject
	private ClubPersonaDAO clubPersonaDAO;
	
	@Override
	public ClubPersona buscarPorId(Long id){
		return clubPersonaDAO.findById(id);
	}
	
	@Override
	public void crear(ClubPersona aplicacion){
		try {
			clubPersonaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubPersona actualizar(ClubPersona aplicacion){
        	ClubPersona app=new ClubPersona();
			try {
				app = clubPersonaDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubPersona findByCodigo(long codigo){
		return clubPersonaDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubPersona> obtenerActivas() {
		return clubPersonaDAO.obtenerActivas();
	}

	@Override
	public List<ClubPersona> buscarPorNombre(String nombre) {
		return clubPersonaDAO.buscarPorNombre(nombre);
	}
}
