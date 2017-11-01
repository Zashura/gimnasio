package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubSedeDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubSede;
import ec.com.gimnasio.service.ClubSedeService;

@Stateless
public class ClubSedeServiceImpl implements ClubSedeService {
		
	@Inject
	private ClubSedeDAO clubSedeDAO;
	
	@Override
	public ClubSede buscarPorId(Long id){
		return clubSedeDAO.findById(id);
	}
	
	@Override
	public void crear(ClubSede aplicacion){
		try {
			clubSedeDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubSede actualizar(ClubSede aplicacion){
        	ClubSede app=new ClubSede();
			try {
				app = clubSedeDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubSede findByCodigo(long codigo){
		return clubSedeDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubSede> obtenerActivas() {
		return clubSedeDAO.obtenerActivas();
	}

	@Override
	public List<ClubSede> buscarPorNombre(String nombre) {
		return clubSedeDAO.buscarPorNombre(nombre);
	}
	
	@Override
	public List<ClubSede> findByInstitucion(long codInstitucion){
		return clubSedeDAO.findByInstitucion(codInstitucion);
	}

	@Override
	public ClubSede findByNemonico(String nemonico) {
		return clubSedeDAO.findByNemonico(nemonico);
	}
}
