package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubGradoDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubGrado;
import ec.com.gimnasio.service.ClubGradoService;

@Stateless
public class ClubGradoServiceImpl implements ClubGradoService {
	
	@Inject
	private ClubGradoDAO clubGradoDAO;
	
	@Override
	public ClubGrado buscarPorId(Long id){
		return clubGradoDAO.findById(id);
	}
	
	@Override
	public void crear(ClubGrado aplicacion){
		try {
			clubGradoDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubGrado actualizar(ClubGrado aplicacion){
        	ClubGrado app=new ClubGrado();
			try {
				app = clubGradoDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubGrado findByCodigo(long codigo){
		return clubGradoDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubGrado> obtenerActivas() {
		return clubGradoDAO.obtenerActivas();
	}

	@Override
	public List<ClubGrado> buscarPorNombre(String nombre) {
		return clubGradoDAO.buscarPorNombre(nombre);
	}
}
