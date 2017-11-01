package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubInstitucionDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInstitucion;
import ec.com.gimnasio.service.ClubInstitucionService;

@Stateless
public class ClubInstitucionServiceImpl implements ClubInstitucionService {
		
	@Inject
	private ClubInstitucionDAO clubInstitucionDAO;
	
	@Override
	public ClubInstitucion buscarPorId(Long id){
		return clubInstitucionDAO.findById(id);
	}

	public void crear(ClubInstitucion aplicacion){
		try {
			clubInstitucionDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	public ClubInstitucion actualizar(ClubInstitucion aplicacion){
        	ClubInstitucion app=new ClubInstitucion();
			try {
				app = clubInstitucionDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	public ClubInstitucion obtenerPorCodigo(long codigo){
		return clubInstitucionDAO.findByCodigo(codigo);
	}

	@Override
	public List<ClubInstitucion> obtenerTodas() {
		return clubInstitucionDAO.findAll();
	}

	@Override
	public List<ClubInstitucion> obtenerActivas() {
		return clubInstitucionDAO.obtenerActivas();
	}

	@Override
	public List<ClubInstitucion> obtenerPorNombre(String nombre) {
		return clubInstitucionDAO.buscarPorNombre(nombre);
	}
	
	@Override
	public ClubInstitucion findByCodigoCas(long codigo){
		return clubInstitucionDAO.findByCodigoCas(codigo);
	}

}
