package ec.com.control.acceso.service.remote;

import java.util.List;

import javax.ejb.Remote;

import ec.com.control.acceso.exception.SedeException;
import ec.com.control.acceso.model.Sede;

@Remote
public interface SedeServiceRemote {
	

	public void actualizar(Sede sede) throws SedeException;
	
	public List<Sede> listarTodas(List<Long> ids, boolean incluye) throws SedeException;
	
	public Sede obtenerPorID(long id);
}
