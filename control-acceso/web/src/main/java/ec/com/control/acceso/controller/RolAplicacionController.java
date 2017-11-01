package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.service.AplicacionService;
import ec.com.control.acceso.service.RolAplicacionService;

@Named
@ViewScoped
public class RolAplicacionController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private transient Logger log;
	
	@Inject
	private RolAplicacionService rolAplicacionService;
	
	@Inject
	private AplicacionService aplicacionService;
	
	private List<RolAplicacion> listaRoles;
	
	private List<Aplicacion> listaAplicaciones;
	
    private RolAplicacion nuevoRol;
    
    private RolAplicacion rolSeleccionado;
    
    private Aplicacion aplicacion;
	
	@PostConstruct
	public void init() {
		aplicacion = new Aplicacion();
		nuevoRol = new RolAplicacion();
		nuevoRol.setAplicacion(new Aplicacion());
		listaAplicaciones = aplicacionService.obtenerActivas();
		rolSeleccionado=new RolAplicacion();
	}
	
	public void registrar() {
        try {
        	rolAplicacionService.crear(nuevoRol);
            agregarMensajeInformacion("Registro ingresado exitosamente", "");
            init();
        } catch (RolAplicacionException e) {
           agregarMensajeError("Error al guardar registro", e.getMessage());
        }
    }
	
	public void buscar() {
		log.info("Va a buscar roles");
		listaRoles = rolAplicacionService.obtenerPorAplicacion(aplicacion, false);
	}
	public void seleccionarRol(RolAplicacion rol) {
		this.rolSeleccionado = rol;
	}
	
	public void actualizar() {
        try {
        	log.info("Esta ingresando al actualizar");
        	rolAplicacionService.actualizar(rolSeleccionado);
            agregarMensajeInformacion("Registro actualizado exitosamente", "");
            buscar();
        } catch (RolAplicacionException e) {
           agregarMensajeError("Error al actualizar registro", e.getMessage());
        }
    }
	
	public void eliminar() {
        try {
        	log.info("Esta ingresando a eliminar");
        	rolAplicacionService.desactivar(rolSeleccionado);
            agregarMensajeInformacion("Registro desactivado exitosamente", "");
        } catch (RolAplicacionException e) {
           agregarMensajeError("Error al eliminar registro", e.getMessage());
        }
    }

	public List<RolAplicacion> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolAplicacion> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public RolAplicacion getNuevoRol() {
		return nuevoRol;
	}

	public void setNuevoRol(RolAplicacion nuevoRol) {
		this.nuevoRol = nuevoRol;
	}

	public RolAplicacion getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(RolAplicacion rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
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

}
