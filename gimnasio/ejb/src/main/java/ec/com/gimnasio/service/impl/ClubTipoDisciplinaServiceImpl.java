package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubTipoDisciplinaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubTipDi;
import ec.com.gimnasio.service.ClubTipoDisciplinaService;

@Stateless
public class ClubTipoDisciplinaServiceImpl implements ClubTipoDisciplinaService {
	
	@Inject
	private ClubTipoDisciplinaDAO clubTipoDisciplinaDAO;
	
	@Override
	public ClubTipDi buscarPorId(Long id){
		return clubTipoDisciplinaDAO.findById(id);
	}
	
	@Override
	public void crear(ClubTipDi aplicacion){
		try {
			clubTipoDisciplinaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubTipDi actualizar(ClubTipDi aplicacion){
        	ClubTipDi app=new ClubTipDi();
			try {
				app = clubTipoDisciplinaDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubTipDi findByCodigo(long codigo){
		return clubTipoDisciplinaDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubTipDi> obtenerActivas() {
		return clubTipoDisciplinaDAO.obtenerActivas();
	}

	@Override
	public List<ClubTipDi> buscarPorNombre(String nombre) {
		return clubTipoDisciplinaDAO.buscarPorNombre(nombre);
	}
}
