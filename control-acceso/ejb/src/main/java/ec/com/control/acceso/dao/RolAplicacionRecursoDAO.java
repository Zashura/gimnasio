package ec.com.control.acceso.dao;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.exception.RecursoException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.RolAplicacionRecurso;

@Local
public interface RolAplicacionRecursoDAO  extends GenericDAO<RolAplicacionRecurso, Long> {
	
	public RolAplicacionRecurso obtenerPorRolYRecurso(RolAplicacion rol, Recurso recurso) throws RecursoException ;
	
	public boolean estaAsignado(List<RolAplicacion> roles, Recurso recurso);
	
	public List<RolAplicacionRecurso> obtenerActivoPorCodigos(RolAplicacion rol);
	
	/**
	 * Método para contar el número de registros RolAplicacionRecurso que poseen un recurso hijo
	 * @param codigoRecurso
	 * @param estado
	 * @return  valor de tipo long
	 */
	public long contarRolAplicacionRecursoHijo(long codigoRecurso,String estado);
	
	/**
	 * Método para contar el número de registros RolAplicacionRecurso que poseen los hijos de un recurso padre
	 * @param codigoRecurso
	 * @param estado
	 * @return valor de tipo long
	 */
	public long contarRolAplicacionRecursoPadre(long codigoRecurso,String estado);
	
}
