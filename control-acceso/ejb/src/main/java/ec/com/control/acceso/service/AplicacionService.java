package ec.com.control.acceso.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.exception.AplicacionException;
import ec.com.control.acceso.model.Aplicacion;

@Local
public interface AplicacionService {
	
	public Aplicacion buscarPorId(Long id) throws AplicacionException;
	
	public void crear(Aplicacion aplicacion) throws AplicacionException;
	
	public Aplicacion actualizar(Aplicacion aplicacion) throws AplicacionException;
	
	public Aplicacion desactivar(Aplicacion aplicacion) throws AplicacionException;
	
	public List<Aplicacion> obtenerTodas();
	
	public List<Aplicacion> obtenerActivas();
	
	public List<Aplicacion> obtenerPorNombre(String nombre);
	
	public Aplicacion obtenerPorPrefijo(String prefijo);

	public Aplicacion obtenerPorCodigo(long codigo);
}
