package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubParroquiaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubParroquia;
import ec.com.gimnasio.service.ClubParroquiaService;

@Stateless
public class ClubParroquiaServiceImpl implements ClubParroquiaService {
	
	@Inject
	private ClubParroquiaDAO clubParroquiaDAO;
	
	@Override
	public ClubParroquia buscarPorId(Long id) {
		return clubParroquiaDAO.findById(id);
	}

	@Override	
	public void crear(ClubParroquia aplicacion){
        try {
        	clubParroquiaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubParroquia actualizar(ClubParroquia aplicacion){
        try {
        	ClubParroquia app = clubParroquiaDAO.update(aplicacion);
			return app;
		} catch (ClubUpdateException e) {
			e.printStackTrace();
			return null;
		}
    }

	@Override
	public List<ClubParroquia> obtenerActivas() {
		return clubParroquiaDAO.obtenerActivas();
	}

	@Override
	public List<ClubParroquia> buscarPorNombre(String nombre) {
		return clubParroquiaDAO.buscarPorNombre(nombre);
	}
	
	@Override
	public ClubParroquia buscarPorDpa(String dpa) {
		return clubParroquiaDAO.buscarPorDpa(dpa);
	}

	@Override
	public List<ClubParroquia> buscarPorCodCanton(long codProvincia) {
		return clubParroquiaDAO.buscarPorCodCanton(codProvincia);
	}

	@Override
	public ClubParroquia findByCodigo(long codigo) {
		return clubParroquiaDAO.findById(codigo);
	}

}
