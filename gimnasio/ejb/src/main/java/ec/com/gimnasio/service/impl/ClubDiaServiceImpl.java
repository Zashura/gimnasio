package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubDiaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubDia;
import ec.com.gimnasio.service.ClubDiaService;

@Stateless
public class ClubDiaServiceImpl implements ClubDiaService {
	
	@Inject
	private ClubDiaDAO clubDiaDAO;
	
	@Override
	public ClubDia buscarPorId(Long id){
		return clubDiaDAO.findById(id);
	}
	
	@Override
	public void crear(ClubDia aplicacion){
		try {
			clubDiaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubDia actualizar(ClubDia aplicacion){
        	ClubDia app=new ClubDia();
			try {
				app = clubDiaDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubDia findByCodigo(long codigo){
		return clubDiaDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubDia> obtenerActivas() {
		return clubDiaDAO.obtenerActivas();
	}

	@Override
	public List<ClubDia> buscarPorNombre(String nombre) {
		return clubDiaDAO.buscarPorNombre(nombre);
	}
}
