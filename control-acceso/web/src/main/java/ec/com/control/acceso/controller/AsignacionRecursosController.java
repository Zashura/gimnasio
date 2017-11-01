package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.exception.RecursoException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.RolAplicacionRecurso;
import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.service.AplicacionService;
import ec.com.control.acceso.service.RecursoService;
import ec.com.control.acceso.service.RolAplicacionRecursoService;
import ec.com.control.acceso.service.RolAplicacionService;

@Named
@ViewScoped
public class AsignacionRecursosController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;
	
	@Inject
	private AplicacionService aplicacionService;
	
	@Inject
	private RecursoService recursoService;
	
	@Inject
	private RolAplicacionService rolAplicacionService;
	
	@Inject
	private RolAplicacionRecursoService rolAplicacionRecursoService;
	
	private List<Aplicacion> listaAplicaciones;
    
    private Aplicacion aplicacion;
    
    private RolAplicacion rol;
    
    private List<Recurso> raices;
    
    private List<RolAplicacionRecurso> listaActivos;
    
    private List<RolAplicacion> roles;
    
    private List<RolAplicacion> todosRoles;
    
	
	@PostConstruct
	public void init() {
		aplicacion = new Aplicacion();
		rol = new RolAplicacion();
		listaAplicaciones = aplicacionService.obtenerActivas();
		
		
	}
	
	public void seleccionarAplicacion(AjaxBehaviorEvent evt) {
		roles = rolAplicacionService.obtenerPorAplicacion(aplicacion, true);
		if(roles == null || roles.isEmpty()) {
			raices = new ArrayList<Recurso>();
			rol = new RolAplicacion();
		}
	}
	
	public void seleccionarRol(AjaxBehaviorEvent evt) {
		listaActivos = rolAplicacionRecursoService.obtenerActivoPorCodigos(rol); 
		raices = new ArrayList<Recurso>();
		List<Recurso> recursos = recursoService.obtenerPorAplicacion(aplicacion, true, true);
		for (Recurso recurso : recursos) {
			armarEstructura(recurso);
			//recurso.setAsignado(recursoService.estaAsignado(recurso, rol));
			for(RolAplicacionRecurso ra :listaActivos){
				if(ra.getEstado().equals("A")&&ra.getRecurso().getCodigo().equals(recurso.getCodigo())&&rol.getCodigo().equals(ra.getRolAplicacion().getCodigo())){
					recurso.setAsignado(true);
				}
				if(ra.getEstado().equals("I")&&ra.getRecurso().getCodigo().equals(recurso.getCodigo())&&rol.getCodigo().equals(ra.getRolAplicacion().getCodigo())){
					recurso.setAsignado(false);
				}
			}
		}
		raices = recursos;
	}
	
	public void seleccionarRecurso(Recurso item){
		if(item.getNivel().equals(1) && item.isAsignado()){
			for (Recurso recurso : raices) {
				if(recurso.getCodigo()==item.getCodigo()){
					for (Recurso hijo : recurso.getRecursos()){
						hijo.setAsignado(true);
					}
				}
			}
		}
		if(item.getNivel().equals(1) && !item.isAsignado()){
			for (Recurso recurso : raices) {
				if(recurso.getCodigo()==item.getCodigo()){
					for (Recurso hijo : recurso.getRecursos()){
						hijo.setAsignado(false);
					}
				}
			}
		}
		if(item.isAsignado()){
			agregarMensajeInformacion("Recurso asignado correctamente.", "");
		}else{
			agregarMensajeAdvertencia("Recurso desactivado correctamente.", "");
		}
		guardar();
	}
	
	public void armarEstructura(Recurso recurso) {
		List<Recurso> hijos = recursoService.buscarPorPadre(recurso, true);
		for (Recurso hijo : hijos) {
			armarEstructura(hijo);
		}
		recurso.setRecursos(hijos);
		//recurso.setAsignado(recursoService.estaAsignado(recurso, rol));
		for(RolAplicacionRecurso ra :listaActivos){
			if(ra.getEstado().equals("A")&&ra.getRecurso().getCodigo().equals(recurso.getCodigo())&&rol.getCodigo().equals(ra.getRolAplicacion().getCodigo())){
				recurso.setAsignado(true);
			}
			if(ra.getEstado().equals("I")&&ra.getRecurso().getCodigo().equals(recurso.getCodigo())&&rol.getCodigo().equals(ra.getRolAplicacion().getCodigo())){
				recurso.setAsignado(false);
			}
		}
	}
	
	
	public void guardar() {
		try {
			recursoService.asignar(raices, rol);
			seleccionarRol(null);
		} catch (RecursoException e) {
			agregarMensajeError(e);
		}
	}
	
	public void limpiar() {
		seleccionarRol(null);
	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public List<Aplicacion> getListaAplicaciones() {
		return listaAplicaciones;
	}

	public void setListaAplicaciones(List<Aplicacion> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
	}

	public List<Recurso> getRaices() {
		return raices;
	}

	public void setRaices(List<Recurso> raices) {
		this.raices = raices;
	}

	public RecursoService getRecursoService() {
		return recursoService;
	}

	public void setRecursoService(RecursoService recursoService) {
		this.recursoService = recursoService;
	}

	public RolAplicacion getRol() {
		return rol;
	}

	public void setRol(RolAplicacion rol) {
		this.rol = rol;
	}

	public List<RolAplicacion> getRoles() {
		return roles;
	}

	public void setRoles(List<RolAplicacion> roles) {
		this.roles = roles;
	}

	public List<RolAplicacion> getTodosRoles() {
		return todosRoles;
	}

	public void setTodosRoles(List<RolAplicacion> todosRoles) {
		this.todosRoles = todosRoles;
	}

	public List<RolAplicacionRecurso> getListaActivos() {
		return listaActivos;
	}

	public void setListaActivos(List<RolAplicacionRecurso> listaActivos) {
		this.listaActivos = listaActivos;
	}

}
