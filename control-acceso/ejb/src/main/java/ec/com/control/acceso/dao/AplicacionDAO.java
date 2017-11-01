package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.Aplicacion;

@Local
public interface AplicacionDAO  extends GenericDAO<Aplicacion, Long> {
	
	public List<Aplicacion> obtenerActivas();
	
	public List<Aplicacion> buscarPorNombre(String nombre);
	
	public Aplicacion buscarPorPrefijo(String prefijo);
	
	public Aplicacion findByCodigo(long codigo);

}
