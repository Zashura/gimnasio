package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubProvinciaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubProvincia;
import ec.com.gimnasio.service.ClubProvinciaService;

@Stateless
public class ClubProvinciaServiceImpl implements ClubProvinciaService {
		
	@Inject
	private ClubProvinciaDAO clubProvinciaDAO;

	@Override
	public void crear(ClubProvincia aplicacion){
        try {
        	clubProvinciaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubProvincia actualizar(ClubProvincia aplicacion){
        try {
        	ClubProvincia app = clubProvinciaDAO.update(aplicacion);
			return app;
		} catch (ClubUpdateException e) {
			e.printStackTrace();
			return null;
		}
    }

	@Override
	public List<ClubProvincia> obtenerTodas() {
		return clubProvinciaDAO.findAll();
	}

	@Override
	public List<ClubProvincia> obtenerActivas() {
		return clubProvinciaDAO.obtenerActivas();
	}

	@Override
	public List<ClubProvincia> buscarPorNombre(String nombre) {
		return clubProvinciaDAO.buscarPorNombre(nombre);
	}
	
	@Override
	public ClubProvincia buscarPorDpa(String dpa) {
		return clubProvinciaDAO.buscarPorDpa(dpa);
	}

	@Override
	public ClubProvincia findByCodigo(long codigo) {
		return clubProvinciaDAO.findByCodigo(codigo);
	}

}
