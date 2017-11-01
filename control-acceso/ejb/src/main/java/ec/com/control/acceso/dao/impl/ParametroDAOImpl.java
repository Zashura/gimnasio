package ec.com.control.acceso.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.com.control.acceso.dao.ParametroDAO;
import ec.com.control.acceso.model.Parametro;
import ec.com.control.acceso.model.Parametro.EnumParametro;


@Stateless
public class ParametroDAOImpl extends GenericDAOImpl<Parametro, Long> implements ParametroDAO {

	public Parametro obtenerPorEnumeracion(EnumParametro enumeration){
		Parametro paramBuscado = null;
		try {
			String sql = "select p from Parametro p where p.enumeration = :enumeration";
	        Query query = getEntityManager().createQuery(sql);
	        query.setParameter("enumeration", enumeration);
	        paramBuscado = (Parametro) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return paramBuscado;
	}

}
