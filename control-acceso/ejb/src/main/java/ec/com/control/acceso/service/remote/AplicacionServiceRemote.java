package ec.com.control.acceso.service.remote;

import java.util.List;

import javax.ejb.Remote;

import ec.com.control.acceso.exception.AplicacionException;
import ec.com.control.acceso.model.Aplicacion;

@Remote
public interface AplicacionServiceRemote {
	
	public Aplicacion buscarPorId(Long id) throws AplicacionException;
	
	public void crear(Aplicacion aplicacion) throws AplicacionException;
	
	public Aplicacion actualizar(Aplicacion aplicacion) throws AplicacionException;
	
	public Aplicacion desactivar(Aplicacion aplicacion) throws AplicacionException;
	
	public List<Aplicacion> obtenerTodas();
	
	public List<Aplicacion> obtenerActivas();
	
	public List<Aplicacion> obtenerPorNombre(String nombre);
	
	public Aplicacion obtenerPorPrefijo(String prefijo);

}
