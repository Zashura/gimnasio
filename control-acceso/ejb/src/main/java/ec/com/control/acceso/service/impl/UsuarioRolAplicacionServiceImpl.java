package ec.com.control.acceso.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.control.acceso.dao.UsuarioRolAplicacionDAO;
import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.UsuarioRolAplicacion;
import ec.com.control.acceso.service.UsuarioRolAplicacionService;
import ec.com.control.acceso.service.remote.UsuarioRolAplicacionServiceRemote;

@Stateless
public class UsuarioRolAplicacionServiceImpl implements UsuarioRolAplicacionService, UsuarioRolAplicacionServiceRemote {
	
	@Inject
	private UsuarioRolAplicacionDAO usuarioRolAplicacionDAO;

	public List<UsuarioRolAplicacion> buscarPorUsuario(Usuario usuario, boolean soloActivos){
		return usuarioRolAplicacionDAO.buscarPorUsuario(usuario, soloActivos);
	}
	
	public UsuarioRolAplicacion obtenerPorUsuarioYRol(Usuario usuario, RolAplicacion rol){
		try {
			return usuarioRolAplicacionDAO.obtenerPorUsuarioYRol(usuario, rol);
		} catch (RolAplicacionException e) {
			return null;
		}
	}
	
	public Boolean verificacionAcceso(String cedula , String url){
		
		try {
			
			if( usuarioRolAplicacionDAO.verificacionAcceso(cedula, url) >=1){
				return true;
			}
		} catch (RolAplicacionException e) {
			return false;
		}
			
		return false;
	}
	
}
