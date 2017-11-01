package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.AsignacionUsuarioSede;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.model.Usuario;

@Local
public interface AsignacionUsuarioSedeDAO  extends GenericDAO<AsignacionUsuarioSede, Long> {
	
	public AsignacionUsuarioSede obtenerUltimaAsignacion(Usuario usuario);
	
	public List<Usuario> obtenerPorSede(Sede sede);

}
