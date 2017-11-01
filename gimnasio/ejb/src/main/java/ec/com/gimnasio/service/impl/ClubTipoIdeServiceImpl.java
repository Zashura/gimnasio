package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubTipoIdeDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubTipIde;
import ec.com.gimnasio.service.ClubTipoIdeService;

@Stateless
public class ClubTipoIdeServiceImpl implements ClubTipoIdeService {
	
	@Inject
	private ClubTipoIdeDAO clubTipoIdeDAO;
	
	@Override
	public ClubTipIde buscarPorId(Long id){
		return clubTipoIdeDAO.findById(id);
	}
	
	@Override
	public void crear(ClubTipIde aplicacion){
		try {
			clubTipoIdeDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubTipIde actualizar(ClubTipIde aplicacion){
        	ClubTipIde app=new ClubTipIde();
			try {
				app = clubTipoIdeDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubTipIde findByCodigo(long codigo){
		return clubTipoIdeDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubTipIde> obtenerActivas() {
		return clubTipoIdeDAO.obtenerActivas();
	}

	@Override
	public List<ClubTipIde> buscarPorNombre(String nombre) {
		return clubTipoIdeDAO.buscarPorNombre(nombre);
	}
}
