package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubParXSedInDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.CluParXSedIn;
import ec.com.gimnasio.service.ClubParXSedInService;

@Stateless
public class ClubParXSedInServiceImpl implements ClubParXSedInService {
	
	@Inject
	private ClubParXSedInDAO clubParXSedInDAO;
	
	@Override
	public CluParXSedIn findByCodigo(long id) {
		return clubParXSedInDAO.findByCodigo(id);
	}
	
	@Override
	public List<CluParXSedIn> findByInstitucion(long codInstitucion){
		return clubParXSedInDAO.findByInstitucion(codInstitucion);
	}
	
	public List<CluParXSedIn> findBySedeNombre(String nombreSede,long codInstitucion){
		return clubParXSedInDAO.findBySedeNombre(nombreSede,codInstitucion);
	}

	@Override	
	public void crear(CluParXSedIn aplicacion){
        try {
        	clubParXSedInDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public CluParXSedIn actualizar(CluParXSedIn aplicacion){
        try {
        	CluParXSedIn app = clubParXSedInDAO.update(aplicacion);
			return app;
		} catch (ClubUpdateException e) {
			e.printStackTrace();
			return null;
		}
    }
}
