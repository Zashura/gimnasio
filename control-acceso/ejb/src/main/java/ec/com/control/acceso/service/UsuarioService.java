package ec.com.control.acceso.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.exception.ClaveNoValidaException;
import ec.com.control.acceso.exception.UsuarioException;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.model.Usuario;

@Local
public interface UsuarioService {
	
	public Usuario buscarPorId(Long id) throws UsuarioException;
	
	public void crear(Usuario aplicacion) throws UsuarioException;
	
	public Usuario actualizar(Usuario aplicacion) throws UsuarioException;
	
	public Usuario desactivar(Usuario aplicacion) throws UsuarioException;
	
	public Usuario activar(Usuario usuario) throws UsuarioException;
	
	public List<Usuario> obtenerTodos(boolean soloActivos);
	
	public List<Usuario> obtenerPorNombre(String nombre, boolean soloActivos);
	
	public List<Usuario> obtenerPorNombreYSedes(String nombre, List<Long> codigosSede, boolean soloActivos);
	
	public void cambiarClave(Usuario usuario, String claveAnterior, String nuevaClave) throws UsuarioException, ClaveNoValidaException;
	
	public void cambiarClaveExterno(Usuario usuario, String nuevaClave) throws UsuarioException, ClaveNoValidaException;
	
	public Usuario buscarPorIdentificacion(String identificacion);
	
	public Usuario buscarPorCorreo(String correo);
	
	public Usuario solicitarRecordarClave(Usuario usuario) throws UsuarioException;
	
	public void asignarSede(Usuario usuario, Sede sede) throws UsuarioException;
	
	public  List<Usuario> obtenerPorSede(Sede sede);
	
	public List<Usuario> buscarUsuarioNombre(String nombre, String apellido);
	
	public Usuario buscarUsuarioId(String identificacion);
	
	public List<Usuario> buscarUsuarioPorCorreoElectronicoVariosResultados(String correo);
	
	public boolean existeCorreoElectronicoUsuario(String correo);
	
}
