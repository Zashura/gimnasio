package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ec.com.control.acceso.exception.RolAplicacionException;
import ec.com.control.acceso.exception.UsuarioException;
import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.model.RolAplicacion;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.service.AplicacionService;
import ec.com.control.acceso.service.RolAplicacionService;
import ec.com.control.acceso.service.SedeService;
import ec.com.control.acceso.service.UsuarioService;

@Named
@ViewScoped
public class UsuarioController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private transient Logger log;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private AplicacionService aplicacionService;
	
	@Inject
	private RolAplicacionService rolAplicacionService;
	
	@Inject
	private SedeService sedeService;
	
	private List<Aplicacion> listaAplicaciones;
    
    private Aplicacion aplicacion;
	
	private List<Usuario> listaUsuarios;
	
    private Usuario nuevoUsuario;
    
    private Usuario usuarioSeleccionado;
    
    private String nombreBuscar;
    private String apellidoBuscar;
    private String identificacionBuscar;
    
    //*********************************************
    private String idBucar;
    
    //*********************************************
    
    private RolAplicacion rolSeleccionado;
    
    private List<RolAplicacion> roles;
    
    private List<Long> codigosSedes;
    
    private List<Sede> estructura;
    
    private int opcionBusqueda;
    
    private boolean correoExiste;
    
    
    
	@PostConstruct
	public void init() {
		nuevoUsuario = new Usuario();
		aplicacion = new Aplicacion();
		//listaUsuarios = usuarioService.obtenerTodos(false);	
		codigosSedes = new ArrayList<Long>();
		opcionBusqueda=0;
	}
	
	public void registrar() {
		if(!correoExiste){
			try {
	        	usuarioService.crear(nuevoUsuario);
	            agregarMensajeInformacion("Registro ingresado exitosamente", "");
	            init();
	        } catch (UsuarioException e) {
	           agregarMensajeError("Error al guardar registro", e.getMessage());
	        }
		}
    }
	
	public void buscar() {
		listaUsuarios=new ArrayList<Usuario>();				
		if(codigosSedes.isEmpty()) {
			//listaUsuarios = usuarioService.obtenerPorNombre(nombreBuscar, false);
			if((nombreBuscar!=null&&!nombreBuscar.equals(""))||(apellidoBuscar!=null&&!apellidoBuscar.equals(""))){				
				listaUsuarios=usuarioService.buscarUsuarioNombre(nombreBuscar, apellidoBuscar);
			}
			
		} else {
			if(nombreBuscar!=null&&!nombreBuscar.equals("")){	
				listaUsuarios = usuarioService.obtenerPorNombreYSedes(nombreBuscar, codigosSedes, false);
			}
		}
	}
	
	//**************************************************************************************************
	public void buscarPorId(){
		listaUsuarios=new ArrayList<Usuario>();	
		if(identificacionBuscar!=null&&!identificacionBuscar.equals("")){
			if(codigosSedes.isEmpty()){
				//listaUsuarios.clear();			
					usuarioSeleccionado= usuarioService.buscarUsuarioId(identificacionBuscar);
					if(usuarioSeleccionado!=null){
						listaUsuarios.add(usuarioSeleccionado);
					}
					/*else{
						buscar();					
					}*/									
			}
		}		
	}
	//**************************************************************************************************
	
	
	public void nuevo() {
		nuevoUsuario = new Usuario();
		correoExiste = false;
	}
	
	public void seleccionarUsuario(Usuario usuario) {
		correoExiste = false;
		this.usuarioSeleccionado = usuario;
	}
	
	public void actualizar() {
		if(!correoExiste){
			try {
	        	log.info("Esta ingresando al actualizar");
	        	usuarioService.actualizar(usuarioSeleccionado);
	            agregarMensajeInformacion("Registro actualizado exitosamente", "");
	            init();
	        } catch (UsuarioException e) {
	           agregarMensajeError("Error al actualizar registro", e.getMessage());
	        }
		}
    }
	
	public void editarUsuario() {
	}
	
	public void eliminar() {
        try {
        	log.info("Esta ingresando a eliminar");
        	usuarioService.desactivar(usuarioSeleccionado);
            agregarMensajeInformacion("Registro desactivado exitosamente", "");
            init();
        } catch (UsuarioException e) {
           agregarMensajeError("Error al eliminar registro", e.getMessage());
        }
    }
	
	public void activar() {
        try {
        	log.info("Esta ingresando a activar");
        	usuarioService.activar(usuarioSeleccionado);
            agregarMensajeInformacion("Registro activado exitosamente", "");
            init();
        } catch (UsuarioException e) {
           agregarMensajeError("Error al eliminar registro", e.getMessage());
        }
    }
	
	public void seleccionarAplicacion(AjaxBehaviorEvent evt) throws RolAplicacionException {
		roles = rolAplicacionService.obtenerConAsignacionPorAplicacion(aplicacion, usuarioSeleccionado);
	}
	
	public void inicializarRoles() {
		aplicacion =  new Aplicacion();
		roles =  new ArrayList<RolAplicacion>();
	}
	
	public void asignarRol(RolAplicacion rol) {
		try {
			
			rolAplicacionService.asignar(usuarioSeleccionado, rol);
		} catch (RolAplicacionException e) {
			agregarMensajeError(e);
		}
	}

	public void seleccionarSede(Sede sede) {
		try {
			usuarioService.asignarSede(usuarioSeleccionado, sede);
			agregarMensajeInformacion("El usuario ha sido asignado correctamente a la sede", "");
			init();
		} catch (UsuarioException e) {
			agregarMensajeError(e.getMessage(), "");
		}
	}
	
	public void seleccionarSedeBusqueda(Long codigoSede) {
		if(codigosSedes.contains(codigoSede)) {
			codigosSedes.remove(codigoSede);
		} else {
			codigosSedes.add(codigoSede);
		}
	}
	
	public void listarSedes(){
		estructura = sedeService.obtenerEstructura(false);
	}
	
	public void listarAplicaciones(){
		listaAplicaciones = aplicacionService.obtenerActivas();
	}
	
	public void limpiarVariablesBusqueda(){
		nombreBuscar="";
	    apellidoBuscar="";
	    identificacionBuscar="";
	    listaUsuarios=new ArrayList<Usuario>();
	}
	
	public void validarCorreoElectronico(Usuario usuario, boolean esEdicion){
		correoExiste = false;
		if(usuario.getCorreoElectronico() != null){
			if(esEdicion){
				List<Usuario> usuarios = usuarioService.buscarUsuarioPorCorreoElectronicoVariosResultados(usuario.getCorreoElectronico());
				for (Usuario usuarioLista : usuarios) {
					if(!usuarioLista.getCodigo().equals(usuario.getCodigo())){
						correoExiste = true;
						break;
					}
				}
			}else {
				correoExiste = usuarioService.existeCorreoElectronicoUsuario(usuario.getCorreoElectronico());
			}
		}
	}
	
	
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getNuevoUsuario() {
		return nuevoUsuario;
	}

	public void setNuevoUsuario(Usuario nuevoUsuario) {
		this.nuevoUsuario = nuevoUsuario;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public String getNombreBuscar() {
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}

	public List<Aplicacion> getListaAplicaciones() {
		return listaAplicaciones;
	}

	public void setListaAplicaciones(List<Aplicacion> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public List<RolAplicacion> getRoles() {
		return roles;
	}

	public void setRoles(List<RolAplicacion> roles) {
		this.roles = roles;
	}

	public RolAplicacion getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(RolAplicacion rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}

	public String getIdBucar() {
		return idBucar;
	}

	public void setIdBucar(String idBucar) {
		this.idBucar = idBucar;
	}

	public String getApellidoBuscar() {
		return apellidoBuscar;
	}

	public void setApellidoBuscar(String apellidoBuscar) {
		this.apellidoBuscar = apellidoBuscar;
	}

	public String getIdentificacionBuscar() {
		return identificacionBuscar;
	}

	public void setIdentificacionBuscar(String identificacionBuscar) {
		this.identificacionBuscar = identificacionBuscar;
	}

	public List<Sede> getEstructura() {
		return estructura;
	}

	public void setEstructura(List<Sede> estructura) {
		this.estructura = estructura;
	}

	public int getOpcionBusqueda() {
		return opcionBusqueda;
	}

	public void setOpcionBusqueda(int opcionBusqueda) {
		this.opcionBusqueda = opcionBusqueda;
	}

	public boolean isCorreoExiste() {
		return correoExiste;
	}

	public void setCorreoExiste(boolean correoExiste) {
		this.correoExiste = correoExiste;
	}

	
	
}
