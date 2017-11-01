package ec.com.control.acceso.service.remote;

import java.util.List;

import javax.ejb.Remote;

import ec.com.control.acceso.exception.ClaveNoValidaException;
import ec.com.control.acceso.exception.UsuarioException;
import ec.com.control.acceso.model.Usuario;

@Remote
public interface UsuarioServiceRemote {
	
	public Usuario buscarPorId(Long id) throws UsuarioException;
	
	public void crear(Usuario aplicacion) throws UsuarioException;
	
	public Usuario actualizar(Usuario aplicacion) throws UsuarioException;
	
	public Usuario desactivar(Usuario aplicacion) throws UsuarioException;
	
	public Usuario activar(Usuario usuario) throws UsuarioException;
	
	public List<Usuario> obtenerTodos(boolean soloActivos);
	
	public List<Usuario> obtenerPorNombre(String nombre, boolean soloActivos);
	
	public void cambiarClave(Usuario usuario, String claveAnterior, String nuevaClave) throws UsuarioException, ClaveNoValidaException;
	
	public Usuario buscarPorIdentificacion(String identificacion);
	
}
