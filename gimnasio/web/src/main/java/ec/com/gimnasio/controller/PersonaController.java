 package ec.com.gimnasio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.remote.UsuarioServiceRemote;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.CluParXSedIn;
import ec.com.gimnasio.model.ClubPersona;
import ec.com.gimnasio.model.ClubRepresentante;
import ec.com.gimnasio.model.ClubSede;
import ec.com.gimnasio.model.ClubSexo;
import ec.com.gimnasio.model.ClubTipIde;
import ec.com.gimnasio.resources.Constantes;
import ec.com.gimnasio.resources.ServiceLocator;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubPersonaService;
import ec.com.gimnasio.service.ClubRepresentanteService;
import ec.com.gimnasio.service.ClubSexoService;
import ec.com.gimnasio.service.ClubTipoIdeService;

@Named
@ViewScoped
public class PersonaController extends BaseController implements Serializable {

	private static final long serialVersionUID = 4973597523116784584L;
	
	@Inject
	private SessionController sessionController;
	@Inject
	private ClubPersonaService clubPersonaService;
	@Inject
	private ClubSexoService clubSexoService;
	@Inject
	private ClubTipoIdeService clubTipoIdeService;
	@Inject
	private ClubRepresentanteService clubRepresentanteService;
	
	private ClubPersona persona=new ClubPersona();
	private ClubSexo sexo=new ClubSexo(); 
	private ClubTipIde tipoIdentificacion=new ClubTipIde();
	private ClubRepresentante representante=new ClubRepresentante();
	private List<ClubPersona> listPersona=new ArrayList<ClubPersona>();
	private List<ClubSexo> listSexo=new ArrayList<ClubSexo>();
	private List<ClubTipIde> listTipoIdentificacion=new ArrayList<ClubTipIde>();
	private boolean update;
	private String nombreBuscar="";
	
	@PostConstruct
	public void init(){
		UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		listPersona=clubPersonaService.obtenerActivas();
		listSexo=clubSexoService.obtenerActivas();
		listTipoIdentificacion=clubTipoIdeService.obtenerActivas();
		update=Boolean.FALSE;
	}
	
	public void findbyName(){
		listPersona=clubPersonaService.buscarPorNombre(nombreBuscar);
	}
	
	public void delete(ClubSede sede){
		
	}
	
	public void edit(CluParXSedIn item){
		update=Boolean.TRUE;
		
	}
	
	public void cancel(){
		update=Boolean.FALSE;
		persona=new ClubPersona();
	}
	
	public void save(){
		if(update){
			try {
				clubRepresentanteService.actualizar(representante);
				persona.setClubSexo(clubSexoService.findByCodigo(sexo.getSexCodigo()));
				persona.setClubTipIde(clubTipoIdeService.findByCodigo(tipoIdentificacion.getTiinCodigo()));
				clubPersonaService.actualizar(persona);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}else{
			try {
				representante.setRepEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				representante.setRepFechCreacion(new Date());
				clubRepresentanteService.crear(representante);
				
				persona.setPerEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				persona.setClubSexo(clubSexoService.findByCodigo(sexo.getSexCodigo()));
				persona.setClubTipIde(clubTipoIdeService.findByCodigo(tipoIdentificacion.getTiinCodigo()));
				persona.setClubRepresentante(representante);
				clubPersonaService.crear(persona);
				 agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		update=Boolean.FALSE;
		listPersona=clubPersonaService.obtenerActivas();		
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

	public ClubSexo getSexo() {
		return sexo;
	}

	public void setSexo(ClubSexo sexo) {
		this.sexo = sexo;
	}

	public List<ClubSexo> getListSexo() {
		return listSexo;
	}

	public void setListSexo(List<ClubSexo> listSexo) {
		this.listSexo = listSexo;
	}

	public ClubTipIde getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(ClubTipIde tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public List<ClubTipIde> getListTipoIdentificacion() {
		return listTipoIdentificacion;
	}

	public void setListTipoIdentificacion(List<ClubTipIde> listTipoIdentificacion) {
		this.listTipoIdentificacion = listTipoIdentificacion;
	}

	public ClubRepresentante getRepresentante() {
		return representante;
	}

	public void setRepresentante(ClubRepresentante representante) {
		this.representante = representante;
	}
}
