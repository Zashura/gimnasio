package ec.com.control.acceso.dao;

import javax.ejb.Local;

import ec.com.control.acceso.model.Parametro;
import ec.com.control.acceso.model.Parametro.EnumParametro;


@Local
public interface ParametroDAO  extends GenericDAO<Parametro, Long> {

	public Parametro obtenerPorEnumeracion(EnumParametro enumeration);

}
