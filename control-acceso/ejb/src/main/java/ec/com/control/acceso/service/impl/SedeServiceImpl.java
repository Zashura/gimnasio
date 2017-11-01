package ec.com.control.acceso.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.control.acceso.dao.SedeDAO;
import ec.com.control.acceso.exception.EducacionPersistException;
import ec.com.control.acceso.exception.EducacionUpdateException;
import ec.com.control.acceso.exception.SedeException;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.service.SedeService;
import ec.com.control.acceso.service.remote.SedeServiceRemote;

@Stateless
public class SedeServiceImpl implements SedeService, SedeServiceRemote {
	
	@Inject
	private SedeDAO sedeDAO;
	
	@Override
	public List<Sede> obtenerEstructura(boolean soloActivas) {
		return sedeDAO.obtenerEstructura(soloActivas);
	}

	@Override
	public void crear(Sede sede) throws SedeException {
		// sede.setNemonico(obtenerNemonico(sede));
		sede.setEstado(Constantes.REGISTRO_ACTIVO);
		try {
			sedeDAO.persist(sede);
		} catch (EducacionPersistException e) {
			throw new SedeException(e);
		}
	}
	
	@Override
	public void mover(Sede padre, Sede sede) throws SedeException {
		sede.setSede(padre);
		//sede.setNemonico(obtenerNemonico(sede));
		try {
			sedeDAO.update(sede);
		} catch (EducacionUpdateException e) {
			throw new SedeException(e);
		}
	}
	
	@Override
	public void desactivar(Sede sede) throws SedeException {
		try {
			sede.setEstado(Constantes.REGISTRO_INACTIVO);
			sedeDAO.update(sede);
		} catch (EducacionUpdateException e) {
			throw new SedeException(e);
		}
	}
	
	@Override
	public void activar(Sede sede) throws SedeException {
		try {
			sede.setEstado(Constantes.REGISTRO_ACTIVO);
			sedeDAO.update(sede);
		} catch (EducacionUpdateException e) {
			throw new SedeException(e);
		}
	}
	
	@Override
	public void actualizar(Sede sede) throws SedeException {
		try {
			sedeDAO.update(sede);
		} catch (EducacionUpdateException e) {
			throw new SedeException(e);
		}
	}
	
	private String obtenerNemonico(Sede sede) {
		String nemonicoPadre = "";
		List<Sede> sedes = new ArrayList<Sede>();
		if(sede.getSede() != null) {
			nemonicoPadre = sede.getSede().getNemonico();
			sedes =sedeDAO.obtenerPorPadre(sede.getSede(),false);
			
		} else {
			sedes =sedeDAO.obtenerSinPadre(false);
		}
		int cantidad = 1;
		if(sedes != null) {
			cantidad += sedes.size();
		}
		return nemonicoPadre + cantidad;
	}

	@Override
	public List<Sede> listarTodas(List<Long> ids, boolean incluye)
			throws SedeException {
		if (ids == null || ids.isEmpty()) {
			return sedeDAO.findAll();
		} else if (incluye) {
			return sedeDAO.listarTodosIn(ids);
		} else {
			return sedeDAO.listarTodosNot(ids);
		}
	}
	
	@Override
	public Sede obtenerPorID(long id){
		return sedeDAO.findById(id);
	}
}
