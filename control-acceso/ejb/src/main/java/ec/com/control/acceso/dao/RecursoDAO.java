package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;

@Local
public interface RecursoDAO  extends GenericDAO<Recurso, Long> {
	
	public List<Recurso> obtenerPorAplicacion(Aplicacion aplicacion, boolean soloActivos, boolean soloRaices);
	
	public List<Recurso> buscarPorPadre(Recurso recurso, boolean soloActivos);

}
