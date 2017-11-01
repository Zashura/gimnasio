package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.UsuarioRolAplicacion;

@Local
public interface UsuarioRolAplicacionDAO extends GenericDAO<UsuarioRolAplicacion, Long> {
	
	public List<UsuarioRolAplicacion> buscarPorUsuario(Usuario usuario, boolean soloActivos);
	
	public UsuarioRolAplicacion obtenerPorUsuarioYRol(Usuario usuario, RolAplicacion rol) throws RolAplicacionException;

	public Long verificacionAcceso(String cedula , String url) throws RolAplicacionException;
	
}
