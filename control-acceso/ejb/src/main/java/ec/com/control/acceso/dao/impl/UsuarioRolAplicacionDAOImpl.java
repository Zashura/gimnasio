package ec.com.control.acceso.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.control.acceso.dao.UsuarioRolAplicacionDAO;
import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.UsuarioRolAplicacion;

@Stateless
public class UsuarioRolAplicacionDAOImpl extends GenericDAOImpl<UsuarioRolAplicacion, Long> implements UsuarioRolAplicacionDAO {

	@Override
	public List<UsuarioRolAplicacion> buscarPorUsuario(Usuario usuario, boolean soloActivos) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UsuarioRolAplicacion> criteria = cb.createQuery(UsuarioRolAplicacion.class);
        Root<UsuarioRolAplicacion> usuarioRolAplicacion = criteria.from(UsuarioRolAplicacion.class);
        if(soloActivos) {
        	criteria.select(usuarioRolAplicacion)
	        	.where(
	        		cb.and(
	        			cb.or(cb.greaterThanOrEqualTo(usuarioRolAplicacion.<Date>get("fechaFinal"), new Date()),
	        				  cb.isNull(usuarioRolAplicacion.get("fechaFinal"))),
	        			cb.lessThanOrEqualTo(usuarioRolAplicacion.<Date>get("fechaInicial"), new Date()),
	        			cb.equal(usuarioRolAplicacion.get("usuario"), usuario)
	        		)
	        	);
        } else {
        	criteria.select(usuarioRolAplicacion)
	        	.where(
	        		cb.equal(usuarioRolAplicacion.get("usuario"), usuario)
	        	);
        }
        return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public UsuarioRolAplicacion obtenerPorUsuarioYRol(Usuario usuario, RolAplicacion rol) throws RolAplicacionException {
		UsuarioRolAplicacion auxUsuario=null;
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UsuarioRolAplicacion> criteria = cb.createQuery(UsuarioRolAplicacion.class);
        Root<UsuarioRolAplicacion> usuarioRolAplicacion = criteria.from(UsuarioRolAplicacion.class);
       
    	criteria.select(usuarioRolAplicacion)
        	.where(
        		cb.and(
    				cb.or(cb.greaterThanOrEqualTo(usuarioRolAplicacion.<Date>get("fechaFinal"), new Date()),
        				  cb.isNull(usuarioRolAplicacion.get("fechaFinal"))),
        			cb.lessThanOrEqualTo(usuarioRolAplicacion.<Date>get("fechaInicial"), new Date()),
        			cb.equal(usuarioRolAplicacion.get("rolAplicacion"), rol),
        			cb.equal(usuarioRolAplicacion.get("usuario"), usuario)
        		)
        	);
        try {
        	auxUsuario= getEntityManager().createQuery(criteria).getSingleResult();
        	if(auxUsuario!=null){
        		auxUsuario.getRolAplicacion().getCodigo();
        		auxUsuario.getRolAplicacion().getNombre();
        		auxUsuario.getUsuario().getCodigo();
        		auxUsuario.getUsuario().getIdentificacion();
        	}
        	return auxUsuario;
		} catch (NonUniqueResultException e) {
			throw new RolAplicacionException(e);
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Long verificacionAcceso(String cedula , String url) throws RolAplicacionException {
		
		try{
		String aux = (String) emSeguridades.createNativeQuery("SELECT US.IDENTIFICACION  FROM SEG_USUARIO US, SEG_ROL_APLICACION R, SEG_USUARIO_ROL_APLICACION RA, SEG_APLICACION AP, "+ 
					" SEG_ROL_APLICACION_RECURSO RR, "+
					" SEG_RECURSO RC "+
			" WHERE AP.CODIGO = R.COD_APLICACION "+
  			" AND R.CODIGO = RA.COD_ROL_APLICACION "+
  			" AND US.CODIGO = RA.COD_USUARIO "+
  			" AND RA.FECHA_FINAL IS NULL "+
  			" AND US.IDENTIFICACION = :cedula "+
				" AND RR.CODIGO_ROL_APLICACION = R.CODIGO "+
				" AND RR.CODIGO_RECURSO= RC.CODIGO "+
				" AND RC.NIVEL= 2  AND ROWNUM = 1"+
        " AND RC.URL like :url and RC.ESTADO = 'A' "
        + " and RR.ESTADO = 'A' "
        + " and R.ESTADO = 'A' ")
        .setParameter("cedula", cedula).setParameter("url", "%"+url).getSingleResult();
	
		return 1L;
		}catch(NoResultException e){
			return 0L;
		}
		
		
	}

}
