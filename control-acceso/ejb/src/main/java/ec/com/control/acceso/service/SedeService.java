package ec.com.control.acceso.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.exception.SedeException;
import ec.com.control.acceso.model.Sede;

@Local
public interface SedeService {
	
	public List<Sede> obtenerEstructura(boolean soloActivas);
	
	public void crear(Sede sede) throws SedeException;
	
	public void mover(Sede padre, Sede sede) throws SedeException;
	
	public void desactivar(Sede sede) throws SedeException;
	
	public void activar(Sede sede) throws SedeException;
	
	public void actualizar(Sede sede) throws SedeException;
	
	public List<Sede> listarTodas(List<Long> ids, boolean incluye) throws SedeException;
	
	public Sede obtenerPorID(long id);
}
