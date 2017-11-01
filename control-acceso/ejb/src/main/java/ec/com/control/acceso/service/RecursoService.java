package ec.com.control.acceso.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.exception.RecursoException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.RolAplicacion;


@Local
public interface RecursoService {

	public List<Recurso> obtenerPorAplicacion(Aplicacion aplicacion, boolean soloActivos, boolean soloRaices);
	
	public List<Recurso> buscarPorPadre(Recurso padre, boolean soloActivos);

	public void crear(Recurso recurso) throws RecursoException;

	public void actualizar(Recurso recurso) throws RecursoException;
	
	public List<Recurso> obtenerAsignadosPorAplicacion(Aplicacion aplicacion, boolean soloRaices, RolAplicacion rol);
	
	public List<Recurso> buscarAsignadosPorPadre(Recurso padre, RolAplicacion rol);
	
	public void asignar(List<Recurso> recursos, RolAplicacion rol) throws RecursoException;
	
	public void asignar(Recurso recurso, RolAplicacion rol) throws RecursoException;
	
	public void eliminarAsignacion(Recurso recurso, RolAplicacion rol) throws RecursoException;
	
	public boolean estaAsignado(Recurso recurso, RolAplicacion rol);
	
	public List<Recurso> generarMenu(Aplicacion aplicacion, List<RolAplicacion> roles);

}
