package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubRepresentanteDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubRepresentante;
import ec.com.gimnasio.service.ClubRepresentanteService;

@Stateless
public class ClubRepresentanteServiceImpl implements ClubRepresentanteService {
	
	@Inject
	private ClubRepresentanteDAO ClubRepresentanteDAO;
	
	@Override
	public ClubRepresentante buscarPorId(Long id){
		return ClubRepresentanteDAO.findById(id);
	}
	
	@Override
	public void crear(ClubRepresentante aplicacion){
		try {
			ClubRepresentanteDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubRepresentante actualizar(ClubRepresentante aplicacion){
        	ClubRepresentante app=new ClubRepresentante();
			try {
				app = ClubRepresentanteDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubRepresentante findByCodigo(long codigo){
		return ClubRepresentanteDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubRepresentante> obtenerActivas() {
		return ClubRepresentanteDAO.obtenerActivas();
	}

	@Override
	public List<ClubRepresentante> buscarPorNombre(String nombre) {
		return ClubRepresentanteDAO.buscarPorNombre(nombre);
	}
}
