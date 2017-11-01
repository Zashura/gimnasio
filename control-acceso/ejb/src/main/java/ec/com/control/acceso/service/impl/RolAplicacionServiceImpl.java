package ec.com.control.acceso.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.control.acceso.dao.RolAplicacionDAO;
import ec.com.control.acceso.dao.UsuarioRolAplicacionDAO;
import ec.com.control.acceso.exception.EducacionPersistException;
import ec.com.control.acceso.exception.EducacionUpdateException;
import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.UsuarioRolAplicacion;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.service.RolAplicacionService;
import ec.com.control.acceso.service.remote.RolAplicacionServiceRemote;

@Stateless
public class RolAplicacionServiceImpl implements RolAplicacionService, RolAplicacionServiceRemote {

	@Inject
	private RolAplicacionDAO rolAplicacionDAO;
	
	@Inject
	private UsuarioRolAplicacionDAO usuarioRolAplicacionDAO;
	
	@Override
	public void crear(RolAplicacion rol) throws RolAplicacionException {
		try {
			rol.setEstado(Constantes.REGISTRO_ACTIVO);
			rolAplicacionDAO.persist(rol);
		} catch (EducacionPersistException e) {
			throw new RolAplicacionException(e);
		}
	}

	@Override
	public RolAplicacion actualizar(RolAplicacion rol)	throws RolAplicacionException {
		try {
			if(rol.isEstadoActivo()) {
				rol.setEstado(Constantes.REGISTRO_INACTIVO);
			} else {
				rol.setEstado(Constantes.REGISTRO_ACTIVO);
			}
			return rolAplicacionDAO.update(rol);
		} catch (EducacionUpdateException e) {
			throw new RolAplicacionException(e);
		}
	}

	@Override
	public RolAplicacion desactivar(RolAplicacion rol) 	throws RolAplicacionException {
		//rol.setEstado(Constantes.REGISTRO_INACTIVO);
		return actualizar(rol);
	}

	@Override
	public List<RolAplicacion> obtenerTodas() {
		return rolAplicacionDAO.findAll();
	}

	@Override
	public List<RolAplicacion> obtenerActivos() {
		return rolAplicacionDAO.obtenerActivos();
	}

	@Override
	public List<RolAplicacion> obtenerPorAplicacion(Aplicacion aplicacion, boolean soloActivos) {
		return rolAplicacionDAO.buscarPorAplicacion(aplicacion, soloActivos);
	}

	@Override
	public RolAplicacion obtenerPorId(Long id) {
		return rolAplicacionDAO.findById(id);
	}
	
	public List<RolAplicacion> obtenerConAsignacionPorAplicacion(Aplicacion aplicacion, Usuario usuario) throws RolAplicacionException {
		try {
			List<RolAplicacion> roles = obtenerPorAplicacion(aplicacion, true);
			for (RolAplicacion rolAplicacion : roles) {
				rolAplicacion.setAsignado(usuarioRolAplicacionDAO.obtenerPorUsuarioYRol(usuario, rolAplicacion) != null);
			}
//			for (RolAplicacion rolAplicacion : roles) {
//				if(!rolAplicacion.isAsignado()) {
//					roles.remove(rolAplicacion);
//				}
//			}
			return roles;
		} catch (RolAplicacionException e) {
			throw new RolAplicacionException(e);
		}
		
	}
	
	public List<RolAplicacion> obtenerPorAplicacion(Aplicacion aplicacion, Usuario usuario) throws RolAplicacionException {
		try {
			List<RolAplicacion> roles = obtenerPorAplicacion(aplicacion, true);
			for (RolAplicacion rolAplicacion : roles) {
				rolAplicacion.setAsignado(usuarioRolAplicacionDAO.obtenerPorUsuarioYRol(usuario, rolAplicacion) != null);
			}
			
			List<RolAplicacion> definitivo = new ArrayList<RolAplicacion>(); 
			for (RolAplicacion rolAplicacion : roles) {
				if(rolAplicacion.isAsignado()) {
					definitivo.add(rolAplicacion);
				}
			}
			return definitivo;
		} catch (RolAplicacionException e) {
			throw new RolAplicacionException(e);
		}
		
	}

	@Override
	public void asignar(Usuario usuario, RolAplicacion rol) throws RolAplicacionException {
		UsuarioRolAplicacion asignacion = usuarioRolAplicacionDAO.obtenerPorUsuarioYRol(usuario, rol);
		try {
			if(asignacion == null) {
				asignacion = new UsuarioRolAplicacion();
				asignacion.setFechaInicial(new Date());
				asignacion.setRolAplicacion(rol);
				asignacion.setUsuario(usuario);
				usuarioRolAplicacionDAO.persist(asignacion);
			} else {
				asignacion.setFechaFinal(new Date());
				usuarioRolAplicacionDAO.update(asignacion);
			}
		} catch (EducacionPersistException e) {
			throw new RolAplicacionException(e);
		} catch (EducacionUpdateException e) {
			throw new RolAplicacionException(e);
		}
	}
	
	@Override
	public RolAplicacion obtenerRolPorNombre(String descripcion){
		return rolAplicacionDAO.obtenerRolPorNombre(descripcion);
	}

}
