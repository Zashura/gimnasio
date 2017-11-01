package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ec.com.control.acceso.resources.Utils;
import ec.com.control.acceso.security.EducacionUserSecurity;
import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.AplicacionService;
import ec.com.control.acceso.service.RecursoService;
import ec.com.control.acceso.service.RolAplicacionService;
import ec.com.control.acceso.service.UsuarioService;

@Named
@SessionScoped
public class SessionController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;

	private EducacionUserSecurity userSecurity;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private RecursoService recursoService;
	
	@Inject
	private RolAplicacionService rolAplicacionService;
	
	@Inject
	private AplicacionService aplicacionService;
		
	private String roles;
	
	private List<Recurso> menu;
	
	private String ipAddressUserInSession;
	private String hostNameUserInSession;
	
	@PostConstruct
    private void init() {
		roles = "";
		
        SecurityContext context = SecurityContextHolder.getContext();
        if(context.getAuthentication() != null) {
	        userSecurity = (EducacionUserSecurity) context.getAuthentication().getPrincipal();
			try {
				ipAddressUserInSession = getIpAddress();
				InetAddress inetAddress = InetAddress.getByName(ipAddressUserInSession);
				hostNameUserInSession = inetAddress.getHostName();
				
				AbstractAuthenticationToken auth = (AbstractAuthenticationToken)context.getAuthentication();
			    HashMap<String, Object> info = new HashMap<String, Object>();
			    info.put("ipAddressUserInSession", ipAddressUserInSession);
			    info.put("hostNameUserInSession", hostNameUserInSession);
			    auth.setDetails(info);
			    
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
			}
	        
			
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
//		Menu menu1 = new Menu("Opcion 1", "#");
//		Menu menu11 = new Menu("Opcion 1.1", "#");
//		Menu menu12 = new Menu("Opcion 1.2", "#");
//		Menu menu121 = new Menu("Opcion 1.2.1", "#");
//		Menu menu122 = new Menu("Opcion 1.2.2", "#");
//		menu12.agregarMenu(menu121);
//		menu12.agregarMenu(menu122);
//		Menu menu13 = new Menu("Opcion 1.3", "#");
//		menu1.agregarMenu(menu11);
//		menu1.agregarMenu(menu12);
//		menu1.agregarMenu(menu13);
//		Menu menu2 = new Menu("Opcion 2", "#");
//		Menu menu21 = new Menu("Opcion 2.1", "#");
//		Menu menu22 = new Menu("Opcion 2.2", "#");
//		menu2.agregarMenu(menu21);
//		menu2.agregarMenu(menu22);
//		Menu menu3 = new Menu("Opcion 3", "#");
//		Menu menu31 = new Menu("Opcion 3.1", "#");
//		Menu menu32 = new Menu("Opcion 3.2", "#");
//		Menu menu33 = new Menu("Opcion 3.3", "#");
//		menu3.agregarMenu(menu31);
//		menu3.agregarMenu(menu32);
//		menu3.agregarMenu(menu33);
//		List<Menu> lista = new ArrayList<Menu>();
//		lista.add(menu1);
//		lista.add(menu2);
//		lista.add(menu3);
//		return lista;
		try {
			Aplicacion aplicacion = aplicacionService.obtenerPorPrefijo(Utils.obtenerPropiedad("app.code"));
			Usuario usuario = usuarioService.buscarPorIdentificacion(userSecurity.getUsername());
			menu = recursoService.generarMenu(aplicacion, rolAplicacionService.obtenerPorAplicacion(aplicacion, usuario));
		} catch (RolAplicacionException e) {
			throw new FaceletException(e);
		}
		
	}
	
	private String getIpAddress(){
		String ipAddress = "0.0.0.0";
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
			    ipAddress = request.getRemoteAddr();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ipAddress;
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

	public String getIpAddressUserInSession() {
		return ipAddressUserInSession;
	}

	public void setIpAddressUserInSession(String ipAddressUserInSession) {
		this.ipAddressUserInSession = ipAddressUserInSession;
	}

	public String getHostNameUserInSession() {
		return hostNameUserInSession;
	}

	public void setHostNameUserInSession(String hostNameUserInSession) {
		this.hostNameUserInSession = hostNameUserInSession;
	}
	
}
