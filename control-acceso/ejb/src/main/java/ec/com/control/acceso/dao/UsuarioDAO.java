package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.Usuario;

@Local
public interface UsuarioDAO  extends GenericDAO<Usuario, Long> {
	
	public List<Usuario> obtenerTodos(boolean soloActivos);
	
	public List<Usuario> buscarPorNombre(String nombre, boolean soloActivos);
	
	public List<Usuario> obtenerPorNombreYSedes(String nombre, List<Long> codigosSede, boolean soloActivos);
	
	public Usuario buscarPorIdentificacion(String identificacion);
	
	public Usuario buscarPorCorreo(String correo);

	public List<Usuario> buscarUsuarioNombre(String nombre, String apellido);
	
	public Usuario buscarUsuarioId(String identificacion);
	
	public List<Usuario> buscarPorCorreoElectronico(String correo);
	
}
