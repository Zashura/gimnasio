package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;

@Local
public interface RolAplicacionDAO extends GenericDAO<RolAplicacion, Long> {
	
	public List<RolAplicacion> obtenerActivos();
	
	public List<RolAplicacion> buscarPorAplicacion(Aplicacion aplicacion, boolean soloActivos);
	
	public List<Usuario> obtenerUsuarios(RolAplicacion rol);
	
	public RolAplicacion obtenerRolPorNombre(String descripcion);
	
}
