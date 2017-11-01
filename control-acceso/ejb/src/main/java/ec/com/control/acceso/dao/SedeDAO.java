package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.Sede;

@Local
public interface SedeDAO  extends GenericDAO<Sede, Long> {
	
	public List<Sede> obtenerEstructura(boolean soloActivas);
	
	public List<Sede> obtenerPorPadre(Sede sede, boolean soloActivas);
	
	public List<Sede> obtenerSinPadre(boolean soloActivas);

	public List<Sede> listarTodosIn(List<Long> ids);
	public List<Sede> listarTodosNot(List<Long> ids);
}
