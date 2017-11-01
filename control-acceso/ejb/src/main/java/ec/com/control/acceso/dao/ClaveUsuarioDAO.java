package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.ClaveUsuario;
import ec.com.control.acceso.model.Usuario;

@Local
public interface ClaveUsuarioDAO  extends GenericDAO<ClaveUsuario, Long> {
	
	public ClaveUsuario obtenerActiva(Usuario usuario);

	public List<ClaveUsuario> obtenerClaves(Usuario usuario);
	
	public long totalClavesUsuario(String identificacionUsuario);
	
	public long totalClavesUsuarioCoincidentes(String clave, boolean soloActivos, boolean soloInactivos);
	
	public long totalClavesUsuario(Usuario usuario, boolean soloActivos, boolean soloInactivos);
	
	public List<String> obtenerUltimasClaves(Long codigoUsuario, int cantidadClavesDevolver);
	
	public ClaveUsuario obtenerUltimaInactiva(Usuario usuario);

}
