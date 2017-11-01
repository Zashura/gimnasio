package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;

import ec.com.control.acceso.exception.RecursoException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.Recurso;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.service.AplicacionService;
import ec.com.control.acceso.service.RecursoService;
import ec.com.control.acceso.service.RolAplicacionRecursoService;

@Named
@ViewScoped
public class RecursosController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;
	
	@Inject
	private AplicacionService aplicacionService;
	
	@Inject
	private RecursoService recursoService;
	
	@Inject
	private RolAplicacionRecursoService rolAplicacionRecursoService;
	
	
	private List<Aplicacion> listaAplicaciones;
    
    private Aplicacion aplicacion;
    
    private List<Recurso> raices;
    
    private Recurso nuevoRecurso;
    
    private Recurso recursoSeleccionado;
    
    private boolean estado;
    
    private boolean existeRolAplicacionRecurso;
	
	@PostConstruct
	public void init() {
		aplicacion = new Aplicacion();
		listaAplicaciones = aplicacionService.obtenerActivas();
		nuevoRecurso =  new Recurso();
		existeRolAplicacionRecurso=false;
	}
	
	public void seleccionarAplicacion(AjaxBehaviorEvent evt) {
		existeRolAplicacionRecurso=false;
		recursoSeleccionado = null;
		List<Recurso> recursos = recursoService.obtenerPorAplicacion(aplicacion, false, true);
		for (Recurso recurso : recursos) {
			armarEstructura(recurso);
		}
		raices = recursos;
	}
	
	public void armarEstructura(Recurso recurso) {
		List<Recurso> hijos = recursoService.buscarPorPadre(recurso, false);
		
		for (Recurso hijo : hijos) {
			armarEstructura(hijo);
			
		}
		recurso.setRecursos(hijos);
	}
	
	public void registrar() {		
		try {
			nuevoRecurso.setAplicacion(aplicacion);
			nuevoRecurso.setPadre(recursoSeleccionado);
			recursoService.crear(nuevoRecurso);
			seleccionarAplicacion(null);
			nuevoRecurso =  new Recurso();
			agregarMensajeInformacion("Registro ingresado exitosamente", "");
		} catch(RecursoException e) {
			agregarMensajeError(e);
		}
	}
	
	public void actualizar() {
		try {
			recursoService.actualizar(recursoSeleccionado);
			seleccionarAplicacion(null);
			agregarMensajeInformacion("Registro actualizado exitosamente", "");
		} catch(RecursoException e) {
			agregarMensajeError(e);
		}
	}
	
	public void seleccionarRecurso(TreeSelectionChangeEvent evt) {
		List<Object> selection = new ArrayList<Object>(evt.getNewSelection());
        Object currentSelectionKey = selection.get(0);
        UITree tree = (UITree) evt.getSource();
        Object storedKey = tree.getRowKey();
        tree.setRowKey(currentSelectionKey);
        recursoSeleccionado = (Recurso) tree.getRowData();
        tree.setRowKey(storedKey);
	}
	
	public void moverRecurso(Recurso recurso) {		
		try {
			if(recurso == null | verificarPosibilidad(recurso, recursoSeleccionado)) {
				recursoSeleccionado.setPadre(recurso);
				recursoService.actualizar(recursoSeleccionado);
				seleccionarAplicacion(null);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} else {
				agregarMensajeError("No se puede colocar el recurso en ubicaci\u00f3n seleccionada.", "");
			}
		} catch (RecursoException e) {
			agregarMensajeError(e);
		}
	}
	
	public boolean verificarPosibilidad(Recurso target, Recurso item) {
		if(target.getPadre() == null) {
			return true;
		} else {
			if(item.getCodigo().equals(target.getPadre().getCodigo())) {
				return false;
			} else {
				return verificarPosibilidad(target.getPadre(), item);
			}
		}
	}
	
	public void cancelar() {
		recursoSeleccionado = null;
	}
	
	public void finalizar() {
		recursoSeleccionado = null;
		seleccionarAplicacion(null);
		
	}
	
	public void activarDesactivar(Recurso item, boolean activar){
		existeRolAplicacionRecurso=false;
		long numeroRegistros=0;
		try{
			if(item.getNivel().equals(2)){	
				if(!activar){					
					numeroRegistros = rolAplicacionRecursoService.contarRolAplicacionRecursoHijo(item.getCodigo(), Constantes.REGISTRO_ACTIVO);
				}				
				if(numeroRegistros==0){
					item.setEstado(activar? Constantes.REGISTRO_ACTIVO : Constantes.REGISTRO_INACTIVO);
					recursoService.actualizar(item);
				}else{
					existeRolAplicacionRecurso=true;
				}
					
			}else if(item.getNivel().equals(1)){
				if(!activar){
					numeroRegistros = rolAplicacionRecursoService.contarRolAplicacionRecursoPadre(item.getCodigo(), Constantes.REGISTRO_ACTIVO);
				}							
				if(numeroRegistros==0){
					List<Recurso> listaHijos=new ArrayList<Recurso>();
					listaHijos=recursoService.buscarPorPadre(item, false);										
					if(!listaHijos.isEmpty()){
						for (Recurso recursoHijo : listaHijos) {												
							recursoHijo.setEstado(activar? Constantes.REGISTRO_ACTIVO : Constantes.REGISTRO_INACTIVO);
							recursoService.actualizar(recursoHijo);						
						}
					}
					/*if(!raices.isEmpty()){
						for (Recurso recurso : raices) {
							if(recurso.getCodigo()==item.getCodigo()){
								for (Recurso hijo : recurso.getRecursos()){
									hijo.setEstado(activar? Constantes.REGISTRO_ACTIVO : Constantes.REGISTRO_INACTIVO);
									recursoService.actualizar(hijo);
								}
							}
						}
					}*/				
					item.setEstado(activar? Constantes.REGISTRO_ACTIVO : Constantes.REGISTRO_INACTIVO);
					recursoService.actualizar(item);
					
					List<Recurso> recursos = recursoService.obtenerPorAplicacion(aplicacion, false, true);
					for (Recurso recurso : recursos) {
						armarEstructura(recurso);
					}
					raices = recursos;
				}else {
					existeRolAplicacionRecurso=true;
				}				
			}
						
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	public void limpiarMensaje(){		
		existeRolAplicacionRecurso=false;
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

	public Recurso getRecursoSeleccionado() {
		return recursoSeleccionado;
	}

	public void setRecursoSeleccionado(Recurso recursoSeleccionado) {
		this.recursoSeleccionado = recursoSeleccionado;
	}

	public Recurso getNuevoRecurso() {
		return nuevoRecurso;
	}

	public void setNuevoRecurso(Recurso nuevoRecurso) {
		this.nuevoRecurso = nuevoRecurso;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean getExisteRolAplicacionRecurso() {
		return existeRolAplicacionRecurso;
	}

	public void setExisteRolAplicacionRecurso(boolean existeRolAplicacionRecurso) {
		this.existeRolAplicacionRecurso = existeRolAplicacionRecurso;
	}
	
}
