package ec.com.gimnasio.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubCinturonGradoDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubCinXGra;
import ec.com.gimnasio.service.ClubCinturonGradoService;

@Stateless
public class ClubCinturonGradoServiceImpl implements ClubCinturonGradoService {
	
	@Inject
	private ClubCinturonGradoDAO clubCinturonGradoDAO;
	
	@Override
	public ClubCinXGra buscarPorId(Long id){
		return clubCinturonGradoDAO.findById(id);
	}
	
	@Override
	public void crear(ClubCinXGra aplicacion){
		try {
			clubCinturonGradoDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubCinXGra actualizar(ClubCinXGra aplicacion){
		ClubCinXGra app=new ClubCinXGra();
			try {
				app = clubCinturonGradoDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubCinXGra findByCodigo(long codigo){
		return clubCinturonGradoDAO.findByCodigo(codigo);
	}
}
