package ec.com.control.acceso.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.control.acceso.dao.RolAplicacionDAO;
import ec.com.control.acceso.dao.RolAplicacionRecursoDAO;
import ec.com.control.acceso.dao.UsuarioRolAplicacionDAO;
import ec.com.control.acceso.exception.EducacionPersistException;
import ec.com.control.acceso.exception.EducacionUpdateException;
import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.RolAplicacionRecurso;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.UsuarioRolAplicacion;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.service.RolAplicacionRecursoService;
import ec.com.control.acceso.service.RolAplicacionService;
import ec.com.control.acceso.service.remote.RolAplicacionServiceRemote;



@Stateless
public class RolAplicacionRecursosServiceImpl implements RolAplicacionRecursoService {

	@Inject
	private RolAplicacionRecursoDAO rolAplicacionRecursoDAO;	

	@Override
	public List<RolAplicacionRecurso> obtenerTodas() {
		return rolAplicacionRecursoDAO.findAll();
	}
	
	@Override
	public List<RolAplicacionRecurso> obtenerActivoPorCodigos(RolAplicacion rol) {
		return rolAplicacionRecursoDAO.obtenerActivoPorCodigos(rol);
	}

	/*
	 * (non-Javadoc)
	 * @see ec.com.control.acceso.service.RolAplicacionRecursoService#contarRolAplicacionRecursoHijo(long, java.lang.String)
	 */
	public long contarRolAplicacionRecursoHijo(long codigoRecurso,String estado){
		return rolAplicacionRecursoDAO.contarRolAplicacionRecursoHijo(codigoRecurso, estado);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ec.com.control.acceso.service.RolAplicacionRecursoService#contarRolAplicacionRecursoPadre(long, java.lang.String)
	 */
	public long contarRolAplicacionRecursoPadre(long codigoRecurso,String estado){
		return rolAplicacionRecursoDAO.contarRolAplicacionRecursoPadre(codigoRecurso, estado);
	}
}
