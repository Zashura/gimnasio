package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubSexoDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubSexo;
import ec.com.gimnasio.service.ClubSexoService;

@Stateless
public class ClubSexoServiceImpl implements ClubSexoService {
	
	@Inject
	private ClubSexoDAO clubSexoDAO;
	
	@Override
	public ClubSexo buscarPorId(Long id){
		return clubSexoDAO.findById(id);
	}
	
	@Override
	public void crear(ClubSexo aplicacion){
		try {
			clubSexoDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubSexo actualizar(ClubSexo aplicacion){
        	ClubSexo app=new ClubSexo();
			try {
				app = clubSexoDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubSexo findByCodigo(long codigo){
		return clubSexoDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubSexo> obtenerActivas() {
		return clubSexoDAO.obtenerActivas();
	}

	@Override
	public List<ClubSexo> buscarPorNombre(String nombre) {
		return clubSexoDAO.buscarPorNombre(nombre);
	}
}
