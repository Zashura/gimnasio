package ec.com.gimnasio.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.facelets.FaceletException;
import javax.inject.Named;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ec.com.gimnasio.resources.ServiceLocator;
import ec.com.gimnasio.resources.Utils;
import ec.com.gimnasio.security.EducacionUserSecurity;
import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.remote.AplicacionServiceRemote;
import ec.com.control.acceso.service.remote.RecursoServiceRemote;
import ec.com.control.acceso.service.remote.RolAplicacionServiceRemote;
import ec.com.control.acceso.service.remote.UsuarioServiceRemote;

@Named
@SessionScoped
public class SessionController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;

	private EducacionUserSecurity userSecurity;
	
	private String roles;
	
	private List<Recurso> menu;
	
	@PostConstruct
    private void init() {
		roles = "";
        SecurityContext context = SecurityContextHolder.getContext();
        if(context.getAuthentication() != null) {
	        userSecurity = (EducacionUserSecurity) context.getAuthentication().getPrincipal();
	        if(userSecurity.getAuthorities() == null || userSecurity.getAuthorities().isEmpty()) {
	        	roles = "Invitado";
	        } else {
		        for (GrantedAuthority authority : userSecurity.getAuthorities()) {
					roles += authority.getAuthority() + ", ";
				}
		        roles = roles.substring(0, roles.length() - 2);
	        }
	        obtenerMenu();
        }
    }
	
	private void obtenerMenu() {
		try {
			AplicacionServiceRemote servicioAplicacion = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, AplicacionServiceRemote.class, true);
			UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
			RolAplicacionServiceRemote servicioRol = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, RolAplicacionServiceRemote.class, true);
			RecursoServiceRemote servicioRecurso = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, RecursoServiceRemote.class, true);
			Aplicacion aplicacion =  servicioAplicacion.obtenerPorPrefijo(Utils.obtenerPropiedad("app.code"));
			Usuario usuario = servicioUsuario.buscarPorIdentificacion(userSecurity.getUsername());
			List<RolAplicacion> roles = servicioRol.obtenerPorAplicacion(aplicacion, usuario);
			menu = servicioRecurso.generarMenu(aplicacion, roles);
		} catch (RolAplicacionException e) {
			throw new FaceletException(e);
		}
	}

	public EducacionUserSecurity getUserSecurity() {
		return userSecurity;
	}

	public void setUserSecurity(EducacionUserSecurity userSecurity) {
		this.userSecurity = userSecurity;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<Recurso> getMenu() {
		return menu;
	}

	public void setMenu(List<Recurso> menu) {
		this.menu = menu;
	}
}
