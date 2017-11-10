 package ec.com.gimnasio.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.remote.UsuarioServiceRemote;
import ec.com.gimnasio.model.CluParXSedIn;
import ec.com.gimnasio.model.ClubPersona;
import ec.com.gimnasio.model.ClubSede;
import ec.com.gimnasio.resources.ServiceLocator;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubPersonaService;

@Named
@ViewScoped
public class PersonaController extends BaseController implements Serializable {

	private static final long serialVersionUID = 4973597523116784584L;
	
	@Inject
	private SessionController sessionController;
	@Inject
	private ClubPersonaService clubPersonaService;

	private ClubPersona persona=new ClubPersona();
	private List<ClubPersona> listPersona=new ArrayList<ClubPersona>();
	
	private String nombreBuscar="";
	
	@PostConstruct
	public void init(){
		UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		listPersona=clubPersonaService.obtenerActivas();
	}
	
	public void findbyName(){
		listPersona=clubPersonaService.buscarPorNombre(nombreBuscar);
	}
	
	public void delete(ClubPersona sede){
		
	}
	
	public void edit(ClubPersona item){
		sessionController.setPersona(item);
		sessionController.setUpdatePersona(Boolean.TRUE);
		FacesContext contex = FacesContext.getCurrentInstance();
        try {
			contex.getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() +"/faces/paginas/persona/inscripcion.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void inscripcion(){
		sessionController.setUpdatePersona(Boolean.FALSE);
		 FacesContext contex = FacesContext.getCurrentInstance();
         try {
			contex.getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() +"/faces/paginas/persona/inscripcion.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ClubPersona getPersona() {
		return persona;
	}

	public void setPersona(ClubPersona persona) {
		this.persona = persona;
	}

	public List<ClubPersona> getListPersona() {
		return listPersona;
	}

	public void setListPersona(List<ClubPersona> listPersona) {
		this.listPersona = listPersona;
	}

	public String getNombreBuscar() {
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}
}
