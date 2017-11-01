package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.exception.SedeException;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.service.SedeService;

@Named
@ViewScoped
public class SedeController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;

	@Inject
	private SedeService sedeService;
	
    private Sede nuevaSede;
    
    private Sede sedeSeleccionada;
    
    private List<Sede> estructura;
    	
	@PostConstruct
	public void init() {
		nuevaSede = new Sede();
		estructura = sedeService.obtenerEstructura(false);
	}
	
	public String registrar() {
		nuevaSede.setSede(sedeSeleccionada);
		try {
			sedeService.crear(nuevaSede);
			agregarMensajeInformacion("Registro ingresado correctamente", "");
			init();
		} catch (SedeException e) {
			agregarMensajeError("Ha existido un error en la creaci\u00f3n del registro", "");
		}
		return null;
	}
	
	public String actualizar() {
		try {
			sedeService.actualizar(sedeSeleccionada);
			agregarMensajeInformacion("Registro actualizado correctamente", "");
			init();
		} catch (SedeException e) {
			agregarMensajeError("Ha existido un error en la actualizaci\u00f3n del registro", "");
		}
		return null;
	}

	public void activar() {
		try {
			sedeService.activar(sedeSeleccionada);
			agregarMensajeInformacion("Registro activado correctamente", "");
			init();
		} catch (SedeException e) {
			agregarMensajeError("Ha existido un error en la activaci\u00f3n del registro", "");
		}
	}
	
	public void desactivar() {
		try {
			sedeService.desactivar(sedeSeleccionada);
			agregarMensajeInformacion("Registro desactivado correctamente", "");
			init();
		} catch (SedeException e) {
			agregarMensajeError("Ha existido un error en la desactivaci\u00f3n del registro", "");
		}
	}
	
	public void mover(Sede sede) {
		try {
			if(sede == null | verificarPosibilidad(sede, sedeSeleccionada)) {
				sedeService.mover(sede, sedeSeleccionada);
				init();
				agregarMensajeInformacion("Sede actualizada exitosamente", "");
			} else {
				agregarMensajeError("No se puede colocar la sede en la ubicaci\u00f3n seleccionada.", "");
			}
		} catch (SedeException e) {
			agregarMensajeError(e);
		}
	}
	
	public boolean verificarPosibilidad(Sede target, Sede item) {
		if(target.getSede() == null) {
			return true;
		} else {
			if(item.getCodigo().equals(target.getSede().getCodigo())) {
				return false;
			} else {
				return verificarPosibilidad(target.getSede(), item);
			}
		}
	}
	
	public Sede getNuevaSede() {
		return nuevaSede;
	}

	public void setNuevaSede(Sede nuevaSede) {
		this.nuevaSede = nuevaSede;
	}

	public List<Sede> getEstructura() {
		return estructura;
	}

	public void setEstructura(List<Sede> estructura) {
		this.estructura = estructura;
	}

	public Sede getSedeSeleccionada() {
		return sedeSeleccionada;
	}

	public void setSedeSeleccionada(Sede sedeSeleccionada) {
		this.sedeSeleccionada = sedeSeleccionada;
	}
	
}
