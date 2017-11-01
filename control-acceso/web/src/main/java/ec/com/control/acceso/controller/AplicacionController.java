package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.exception.AplicacionException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.service.AplicacionService;

@Named
@ViewScoped
public class AplicacionController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;
	
	@Inject
	private AplicacionService aplicacionService;
	
	private List<Aplicacion> listaAplicaciones;
	
    private Aplicacion nuevaAplicacion;
    
    private Aplicacion aplicacionSeleccionada;
    
    private String nombreBuscar;
    
    private boolean cambioNombre;
	
	@PostConstruct
	public void init() {
		nuevaAplicacion = new Aplicacion();
		listaAplicaciones = aplicacionService.obtenerActivas();
	}
	
	public void registrar() {
        try {
        	if((!aplicacionService.obtenerPorNombre(nuevaAplicacion.getNombre()).isEmpty())){
        		agregarMensajeError("Ya existe un registro con el nombre ingresado", "");
        		
        	}else{
        		aplicacionService.crear(nuevaAplicacion);
                agregarMensajeInformacion("Registro ingresado exitosamente", "");
                init();
        	}
            
        } catch (AplicacionException e) {
           agregarMensajeError("Error al guardar registro", e.getMessage());
        }
    }
	
	public void buscar() {
		listaAplicaciones = aplicacionService.obtenerPorNombre(nombreBuscar);
	}
	
	public void seleccionarAplicacion(Aplicacion aplicacion) {
		this.aplicacionSeleccionada = aplicacion;
	}
	
	public void actualizar() {
		try {
			if(cambioNombre == true){
				if (!(aplicacionService.obtenerPorNombre(aplicacionSeleccionada.getNombre()).isEmpty())) {
					agregarMensajeError("Ya existe un registro con el nombre ingresado", "");
				} else {
					aplicacionService.actualizar(aplicacionSeleccionada);
					agregarMensajeInformacion("Registro actualizado exitosamente","");
					init();
				}
			}else{
				aplicacionService.actualizar(aplicacionSeleccionada);
				agregarMensajeInformacion("Registro actualizado exitosamente","");
				init();
			}
		} catch (AplicacionException e) {
			agregarMensajeError("Error al actualizar registro", e.getMessage());
		}
	}
	
	public void eliminar() {
		try {
			aplicacionService.desactivar(aplicacionSeleccionada);
			if (aplicacionSeleccionada.getEstado().equals(Constantes.REGISTRO_INACTIVO)) {
				agregarMensajeInformacion("Registro desactivado exitosamente","");
				init();
			} else {
				agregarMensajeError("No se desactivó el registro ya que otros elementos dependen de él","");
			}
		} catch (AplicacionException e) {
			agregarMensajeError("Error al eliminar registro", e.getMessage());
		}
	}
	
	public void noCambioNombre(){
		cambioNombre = false;
	}
	public void cambioNombre(){
		cambioNombre = true;
	}

	public List<Aplicacion> getListaAplicaciones() {
		return listaAplicaciones;
	}

	public void setListaAplicacions(List<Aplicacion> listaAplicacions) {
		this.listaAplicaciones = listaAplicacions;
	}

	public Aplicacion getNuevaAplicacion() {
		return nuevaAplicacion;
	}

	public void setNuevaAplicacion(Aplicacion nuevaAplicacion) {
		this.nuevaAplicacion = nuevaAplicacion;
	}

	public String getNombreBuscar() {
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}

	public Aplicacion getAplicacionSeleccionada() {
		return aplicacionSeleccionada;
	}

	public void setAplicacionSeleccionada(Aplicacion aplicacionSeleccionada) {
		this.aplicacionSeleccionada = aplicacionSeleccionada;
	}

	public boolean isCambioNombre() {
		return cambioNombre;
	}

	public void setCambioNombre(boolean cambioNombre) {
		this.cambioNombre = cambioNombre;
	}

}
