package ec.com.control.acceso.service.remote;

import java.util.List;

import javax.ejb.Remote;

import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.UsuarioRolAplicacion;

@Remote
public interface UsuarioRolAplicacionServiceRemote {
	
	public Boolean verificacionAcceso(String cedula , String url);
	
	public List<UsuarioRolAplicacion> buscarPorUsuario(Usuario usuario, boolean soloActivos);
	
	public UsuarioRolAplicacion obtenerPorUsuarioYRol(Usuario usuario, RolAplicacion rol);
}
