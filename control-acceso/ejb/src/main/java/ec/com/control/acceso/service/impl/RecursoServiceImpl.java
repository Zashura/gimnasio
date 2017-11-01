package ec.com.control.acceso.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.control.acceso.dao.RecursoDAO;
import ec.com.control.acceso.dao.RolAplicacionRecursoDAO;
import ec.com.control.acceso.exception.EducacionPersistException;
import ec.com.control.acceso.exception.EducacionUpdateException;
import ec.com.control.acceso.exception.RecursoException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.RolAplicacionRecurso;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.service.RecursoService;
import ec.com.control.acceso.service.remote.RecursoServiceRemote;

@Stateless
public class RecursoServiceImpl implements RecursoService, RecursoServiceRemote {

	@Inject
	private RecursoDAO recursoDAO;
	
	@Inject
	private RolAplicacionRecursoDAO rolAplicacionRecursoDAO;
	
	@Override
	public List<Recurso> obtenerPorAplicacion(Aplicacion aplicacion, boolean soloActivos, boolean soloRaices) {
		return recursoDAO.obtenerPorAplicacion(aplicacion, soloActivos, soloRaices);
	}

	@Override
	public List<Recurso> buscarPorPadre(Recurso padre, boolean soloActivos) {
		return recursoDAO.buscarPorPadre(padre, soloActivos);
	}

	@Override
	public void crear(Recurso recurso) throws RecursoException {
		try {
			recurso.setEstado(Constantes.REGISTRO_ACTIVO);
			recurso.setNivel(obtenerNivel(recurso));
			recursoDAO.persist(recurso);
		} catch (EducacionPersistException e) {
			throw new RecursoException(e);
		}
	}

	@Override
	public void actualizar(Recurso recurso) throws RecursoException {
		try {
			recursoDAO.update(recurso);
		} catch (EducacionUpdateException e) {
			throw new RecursoException(e);
		}
	}
	
	public int obtenerNivel(Recurso recurso) {
		if (recurso.getPadre() == null) {
			return 1;
		} else {
			return obtenerNivel(recurso.getPadre()) + 1;
		}
	}

	@Override
	public List<Recurso> obtenerAsignadosPorAplicacion(Aplicacion aplicacion, boolean soloRaices, RolAplicacion rol) {
		List<Recurso> recursos = recursoDAO.obtenerPorAplicacion(aplicacion, true, true);
		for (Recurso recurso : recursos) {
			recurso.setAsignado(estaAsignado(recurso, rol));
		}
		return recursos;
	}

	@Override
	public List<Recurso> buscarAsignadosPorPadre(Recurso padre, RolAplicacion rol) {
		List<Recurso> recursos = recursoDAO.buscarPorPadre(padre, true);
		for (Recurso recurso : recursos) {
			recurso.setAsignado(estaAsignado(recurso, rol));
		}
		return recursos;
	}
	
	public boolean estaAsignado(Recurso recurso, RolAplicacion rol) {
		try {
			RolAplicacionRecurso asignacion = rolAplicacionRecursoDAO.obtenerPorRolYRecurso(rol, recurso);
			return asignacion != null;
		} catch (RecursoException e) {
			return false;
		}
	}

	@Override
	public void asignar(List<Recurso> recursos, RolAplicacion rol) throws RecursoException {
		for (Recurso recurso : recursos) {
			if(recurso.isAsignado()) {
				asignar(recurso, rol);
			} else {
				eliminarAsignacion(recurso, rol);
			}
		}
	}

	@Override
	public void asignar(Recurso recurso, RolAplicacion rol) throws RecursoException {
		try {
			RolAplicacionRecurso asignacion = rolAplicacionRecursoDAO.obtenerPorRolYRecurso(rol, recurso);
			if(asignacion == null) {
				RolAplicacionRecurso nuevaAsignacion= new RolAplicacionRecurso();
				nuevaAsignacion.setRecurso(recurso);
				nuevaAsignacion.setRolAplicacion(rol);
				nuevaAsignacion.setEstado(Constantes.REGISTRO_ACTIVO);
				rolAplicacionRecursoDAO.persist(nuevaAsignacion);
			} else {
				asignacion.setEstado(Constantes.REGISTRO_ACTIVO);
				rolAplicacionRecursoDAO.update(asignacion);
			}
			asignar(recurso.getRecursos(), rol);
		} catch(RecursoException e) {
			throw e;
		} catch(EducacionPersistException e) {
			throw new RecursoException(e);
		} catch(EducacionUpdateException e) {
			throw new RecursoException(e);
		}
	}
	
	@Override
	public void eliminarAsignacion(Recurso recurso, RolAplicacion rol) throws RecursoException {
		try {
			RolAplicacionRecurso asignacion = rolAplicacionRecursoDAO.obtenerPorRolYRecurso(rol, recurso);
			if(asignacion != null) {
				asignacion.setEstado(Constantes.REGISTRO_INACTIVO);
				rolAplicacionRecursoDAO.update(asignacion);
			}
			asignar(recurso.getRecursos(), rol);
		} catch(RecursoException e) { 
			throw e;
		} catch(EducacionUpdateException e) {
			throw new RecursoException(e);
		}
	}
	
	public List<Recurso> generarMenu(Aplicacion aplicacion, List<RolAplicacion> roles) {
	    List<Recurso> recursos = obtenerPorAplicacion(aplicacion, true, true);
		for (Recurso recurso : recursos) {
			armarEstructura(recurso, roles);
			recurso.setAsignado(rolAplicacionRecursoDAO.estaAsignado(roles, recurso));
		}
		for (Recurso recurso : recursos) {
			limpiarNoAsignados(recurso);
		}
		return recursos;
	}
	
	private void armarEstructura(Recurso recurso, List<RolAplicacion> roles) {
		List<Recurso> hijos = recursoDAO.buscarPorPadre(recurso, true);
		for (Recurso hijo : hijos) {
			armarEstructura(hijo, roles);
		}
		recurso.setRecursos(hijos);
		recurso.setAsignado(rolAplicacionRecursoDAO.estaAsignado(roles, recurso));
	}
	
	private void limpiarNoAsignados(Recurso recurso) {
		if(recurso.getRecursos() == null || recurso.getRecursos().isEmpty()) {
			return;
		}
		List<Recurso> definitiva = new ArrayList<Recurso>();
		for (Recurso hijo : recurso.getRecursos()) {
			if(hijo.isAsignado() || tieneHijosAsignados(hijo)) {
				definitiva.add(hijo);
			}
		}
		for (Recurso hijo : definitiva) {
			limpiarNoAsignados(hijo);
		}
		recurso.setRecursos(definitiva);
	}
	
	private boolean tieneHijosAsignados(Recurso recurso) {
		if(recurso.getRecursos() == null || recurso.getRecursos().isEmpty()) {
			return false;
		} else {
			for (Recurso hijo : recurso.getRecursos()) {
				if(hijo.isAsignado()) {
					return true;
				}
			}
			for (Recurso hijo : recurso.getRecursos()) {
				return tieneHijosAsignados(hijo);
			}
			return false;
		}
	}
	
}
