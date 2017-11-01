package ec.com.control.acceso.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import ec.com.control.acceso.dao.AplicacionDAO;
import ec.com.control.acceso.exception.AplicacionException;
import ec.com.control.acceso.exception.EducacionPersistException;
import ec.com.control.acceso.exception.EducacionUpdateException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.service.AplicacionService;
import ec.com.control.acceso.service.remote.AplicacionServiceRemote;

@Stateless
public class AplicacionServiceImpl implements AplicacionService, AplicacionServiceRemote {
	
	@Inject
    private Event<Aplicacion> aplicacionEventSrc;
	
	@Inject
	private AplicacionDAO aplicacionDAO;
	
	@Override
	public Aplicacion buscarPorId(Long id) throws AplicacionException {
		return aplicacionDAO.findById(id);
	}

	public void crear(Aplicacion aplicacion) throws AplicacionException {
        try {
        	//log.info("---> Creando nueva aplicacion: " + aplicacion.getNombre());
        	aplicacion.setEstado(Constantes.REGISTRO_ACTIVO);
        	aplicacionDAO.persist(aplicacion);
			aplicacionEventSrc.fire(aplicacion);
		} catch (EducacionPersistException e) {
			throw new AplicacionException(e);
		}
    }
	
	public Aplicacion actualizar(Aplicacion aplicacion) throws AplicacionException {
        try {
        	//log.info("---> Actualizando aplicacion: " + aplicacion.getNombre());
        	Aplicacion app = aplicacionDAO.update(aplicacion);
			aplicacionEventSrc.fire(aplicacion);
			return app;
		} catch (EducacionUpdateException e) {
			throw new AplicacionException(e);
		}
    }
	
	public Aplicacion desactivar(Aplicacion aplicacion)
			throws AplicacionException {
		try {
			//log.info("---> Desactivando aplicacion: " + aplicacion.getNombre());
			Aplicacion otherAplicacion = aplicacionDAO.findByCodigo(aplicacion.getCodigo());
			if ((otherAplicacion.getRecursos() == null || otherAplicacion.getRecursos().isEmpty())	
					&& (otherAplicacion.getRolAplicacions() == null || otherAplicacion.getRolAplicacions().isEmpty())) {
				aplicacion.setEstado(Constantes.REGISTRO_INACTIVO);
				aplicacionEventSrc.fire(aplicacion);
				Aplicacion app = aplicacionDAO.update(aplicacion);
				return app;
			} else {
				aplicacion.setEstado(Constantes.REGISTRO_ACTIVO);
				aplicacionEventSrc.fire(aplicacion);
				Aplicacion app = aplicacionDAO.update(aplicacion);
				return app;
			}

		} catch (EducacionUpdateException e) {
			throw new AplicacionException(e);
		}
	}
	
	public Aplicacion obtenerPorCodigo(long codigo){
		return aplicacionDAO.findByCodigo(codigo);
	}

	@Override
	public List<Aplicacion> obtenerTodas() {
		return aplicacionDAO.findAll();
	}

	@Override
	public List<Aplicacion> obtenerActivas() {
		return aplicacionDAO.obtenerActivas();
	}

	@Override
	public List<Aplicacion> obtenerPorNombre(String nombre) {
		return aplicacionDAO.buscarPorNombre(nombre);
	}
	
	@Override
	public Aplicacion obtenerPorPrefijo(String prefijo) {
		return aplicacionDAO.buscarPorPrefijo(prefijo);
	}

}
