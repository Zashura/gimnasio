package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubCantonDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCanton;
import ec.com.gimnasio.service.ClubCantonService;

@Stateless
public class ClubCantonServiceImpl implements ClubCantonService {
	
	@Inject
	private ClubCantonDAO aplicacionDAO;
	
	@Override
	public ClubCanton buscarPorId(Long id) {
		return aplicacionDAO.findById(id);
	}

	@Override	
	public void crear(ClubCanton aplicacion){
        try {
        	aplicacionDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubCanton actualizar(ClubCanton aplicacion){
        try {
        	ClubCanton app = aplicacionDAO.update(aplicacion);
			return app;
		} catch (ClubUpdateException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	@Override
	public ClubCanton obtenerPorCodigo(long codigo){
		return aplicacionDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubCanton> obtenerTodas() {
		return aplicacionDAO.findAll();
	}

	@Override
	public List<ClubCanton> obtenerActivas() {
		return aplicacionDAO.obtenerActivas();
	}

	@Override
	public List<ClubCanton> obtenerPorNombre(String nombre) {
		return aplicacionDAO.buscarPorNombre(nombre);
	}
	
	@Override
	public ClubCanton buscarPorDpa(String dpa) {
		return aplicacionDAO.buscarPorDpa(dpa);
	}

	@Override
	public List<ClubCanton> buscarPorDpaProvincia(long codProvincia) {
		return aplicacionDAO.buscarPorDpaProvincia(codProvincia);
	}

}
