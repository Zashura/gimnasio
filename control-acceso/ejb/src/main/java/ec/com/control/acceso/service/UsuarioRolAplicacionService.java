package ec.com.control.acceso.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.UsuarioRolAplicacion;

@Local
public interface UsuarioRolAplicacionService {
	
	public Boolean verificacionAcceso(String cedula , String url);
	
	public List<UsuarioRolAplicacion> buscarPorUsuario(Usuario usuario, boolean soloActivos);
	
	public UsuarioRolAplicacion obtenerPorUsuarioYRol(Usuario usuario, RolAplicacion rol);
}
