package ec.com.control.acceso.service;

import java.util.List;

import javax.ejb.Local;

import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.RolAplicacionRecurso;
import ec.com.control.acceso.model.Usuario;

@Local
public interface RolAplicacionRecursoService {

	public List<RolAplicacionRecurso> obtenerTodas();
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
