package ec.com.control.acceso.service.remote;

import java.util.List;

import javax.ejb.Remote;

import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;

@Remote
public interface RolAplicacionServiceRemote {

	public void crear(RolAplicacion rol) throws RolAplicacionException;
	
	public RolAplicacion actualizar(RolAplicacion rol) throws RolAplicacionException;
	
	public RolAplicacion desactivar(RolAplicacion rol) throws RolAplicacionException;
	
	public List<RolAplicacion> obtenerTodas();
	
	public List<RolAplicacion> obtenerActivos();
	
	public List<RolAplicacion> obtenerPorAplicacion(Aplicacion aplicacion, boolean soloActivos);
	
	public RolAplicacion obtenerPorId(Long id);
	
	public List<RolAplicacion> obtenerConAsignacionPorAplicacion(Aplicacion aplicacion, Usuario usuario) throws RolAplicacionException;
	
	public List<RolAplicacion> obtenerPorAplicacion(Aplicacion aplicacion, Usuario usuario) throws RolAplicacionException;
	
	public void asignar(Usuario usuario, RolAplicacion rol) throws RolAplicacionException;
	
	public RolAplicacion obtenerRolPorNombre(String descripcion);
	
}
