 package ec.com.gimnasio.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.remote.UsuarioServiceRemote;
import ec.com.gimnasio.dto.InscripcionDTO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubDisciplina;
import ec.com.gimnasio.model.ClubHodiXDissedclub;
import ec.com.gimnasio.model.ClubHorDia;
import ec.com.gimnasio.model.ClubInsDisSedClub;
import ec.com.gimnasio.model.ClubInscripcion;
import ec.com.gimnasio.model.ClubInstitucion;
import ec.com.gimnasio.model.ClubPersona;
import ec.com.gimnasio.model.ClubRepresentante;
import ec.com.gimnasio.model.ClubSedIn;
import ec.com.gimnasio.model.ClubSede;
import ec.com.gimnasio.model.ClubSexo;
import ec.com.gimnasio.model.ClubTipIde;
import ec.com.gimnasio.resources.Constantes;
import ec.com.gimnasio.resources.ServiceLocator;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubDisXSedInsService;
import ec.com.gimnasio.service.ClubDisciplinaService;
import ec.com.gimnasio.service.ClubHodiXDisService;
import ec.com.gimnasio.service.ClubHoraDiaService;
import ec.com.gimnasio.service.ClubInsDisSedClubService;
import ec.com.gimnasio.service.ClubInscripcionService;
import ec.com.gimnasio.service.ClubInstitucionService;
import ec.com.gimnasio.service.ClubPersonaService;
import ec.com.gimnasio.service.ClubRepresentanteService;
import ec.com.gimnasio.service.ClubSedInsService;
import ec.com.gimnasio.service.ClubSedeService;
import ec.com.gimnasio.service.ClubSexoService;
import ec.com.gimnasio.service.ClubTipoIdeService;

@Named
@ViewScoped
public class InscripcionController extends BaseController implements Serializable {

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
	@Inject
	private ClubInstitucionService clubInstitucionService;
	@Inject
	private ClubSedeService clubSedeService;
	@Inject 
	private ClubDisciplinaService clubDisciplinaService;
	@Inject
	private ClubInscripcionService clubInscripcionService;
	@Inject
	private ClubSedInsService clubSedInsService;
	@Inject
	private ClubDisXSedInsService clubDisXSedInsService;
	@Inject
	private ClubInsDisSedClubService clubInsDisSedClubService;
	@Inject
	private ClubHodiXDisService clubHodiXDisService;
	@Inject
	private ClubHoraDiaService clubHoraDiaService;
	
	private ClubPersona persona=new ClubPersona();
	private ClubInstitucion clubInstitucion=new ClubInstitucion();
	private ClubSexo sexo=new ClubSexo(); 
	private ClubTipIde tipoIdentificacion=new ClubTipIde();
	private ClubRepresentante representante=new ClubRepresentante();
	private ClubSede sede=new ClubSede();
	private ClubDisciplina disciplina=new ClubDisciplina();
	private ClubHodiXDissedclub hodiDisciplina=new ClubHodiXDissedclub();
	private ClubHorDia horaDia=new ClubHorDia();
	private List<ClubPersona> listPersona=new ArrayList<ClubPersona>();
	private List<ClubSexo> listSexo=new ArrayList<ClubSexo>();
	private List<ClubTipIde> listTipoIdentificacion=new ArrayList<ClubTipIde>();
	private List<ClubSede> listSedes=new ArrayList<ClubSede>();
	private List<ClubDisciplina> listDisciplina=new ArrayList<ClubDisciplina>();
	private List<ClubInsDisSedClub> listInscripcion=new ArrayList<ClubInsDisSedClub>();
	private List<InscripcionDTO> listaInscripcion=new ArrayList<InscripcionDTO>();
	private List<ClubHodiXDissedclub> listHorario=new ArrayList<ClubHodiXDissedclub>();
	private List<ClubHodiXDissedclub> listInscripciones=new ArrayList<ClubHodiXDissedclub>();
	
	private boolean update;
	private String nombreBuscar="";
	
	@PostConstruct
	public void init(){
		UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		clubInstitucion=clubInstitucionService.findByCodigoCas(usuario.getSede().getCodigo());
		listSedes=clubSedeService.findByInstitucion(clubInstitucion.getCluCodigo());
		listSexo=clubSexoService.obtenerActivas();
		listTipoIdentificacion=clubTipoIdeService.obtenerActivas();
		update=sessionController.isUpdatePersona();
		if(update){
			persona=sessionController.getPersona();
			tipoIdentificacion=persona.getClubTipIde();
			representante=persona.getClubRepresentante();
			sexo=persona.getClubSexo();
		}else{
			persona=new ClubPersona();
			sexo=new ClubSexo();
			tipoIdentificacion=new ClubTipIde();
			representante=new ClubRepresentante();
		}

		listInscripcion=clubInsDisSedClubService.findPersonaInstitucion(persona.getPerCodigo(), clubInstitucion.getCluCodigo());
		listInscripciones=clubHodiXDisService.findByInstitucionPersona(clubInstitucion.getCluCodigo(), persona.getPerCodigo());
		
		for (ClubHodiXDissedclub itemDTO : listInscripciones) {
			InscripcionDTO item=new InscripcionDTO(itemDTO.getClubDisXSedIn().getClubSedIn().getClubSede().getSedCodigo(),itemDTO.getClubDisXSedIn().getClubSedIn().getClubSede().getSedDescripcion(),
					itemDTO.getClubDisXSedIn().getClubDisciplina().getDisCodigo(),itemDTO.getClubDisXSedIn().getClubDisciplina().getDisDescripcion(),
					itemDTO.getClubHorDia().getHodiCodigo(),itemDTO.getClubHorDia().getClubDia().getDiaDescripcion(),itemDTO.getClubDisXSedIn().getDisiCodigo());
			listaInscripcion.add(item);
		}
	}
	
	public void findDisciplina(){
		listDisciplina=clubDisciplinaService.listBySede(sede.getSedCodigo());
	}
	
	public void findHorarios(){
		listHorario=clubHodiXDisService.findBySedeDisciplina(sede.getSedCodigo(), disciplina.getDisCodigo());
	}
	
	public void cancel(){
		FacesContext contex = FacesContext.getCurrentInstance();
        try {
			contex.getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() +"/faces/paginas/persona/persona.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void save(){
		if(update){
			try {
				clubRepresentanteService.actualizar(representante);
				
				persona.setClubSexo(clubSexoService.findByCodigo(sexo.getSexCodigo()));
				persona.setClubTipIde(clubTipoIdeService.findByCodigo(tipoIdentificacion.getTiinCodigo()));
				persona.setClubSexo(clubSexoService.findByCodigo(sexo.getSexCodigo()));
				persona.setClubTipIde(clubTipoIdeService.findByCodigo(tipoIdentificacion.getTiinCodigo()));
				clubPersonaService.actualizar(persona);
				
				ClubInscripcion inscripcion=new ClubInscripcion();
				inscripcion=clubInscripcionService.findByPersona(persona.getPerCodigo());
				for (InscripcionDTO item : listaInscripcion) {
					ClubSedIn clubSedIn=new ClubSedIn();
					clubSedIn=clubSedInsService.findByCodigoInstucionSede(clubInstitucion.getCluCodigo(), item.getCodSede());
					
					ClubInsDisSedClub clubInsDisSedClub=new ClubInsDisSedClub();
					if(item.getDisiCodigo()==0l){
						clubInsDisSedClub=new ClubInsDisSedClub();
					}else{
						clubInsDisSedClub=clubInsDisSedClubService.findByInscripcionHorarioDisciplina(inscripcion.getInsCodigo(), item.getCodHorario(), item.getDisiCodigo());
					}
					clubInsDisSedClub.setHodiCodigo(item.getCodHorario());
					clubInsDisSedClub.setClubInscripcion(inscripcion);
					clubInsDisSedClub.setClubDisXSedIn(clubDisXSedInsService.findByCodigoSedeDisciplina(clubSedIn.getSeinCodigo(),item.getCodDisciplina()));
					clubInsDisSedClub.setIdsiEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
					clubInsDisSedClub.setIdsiFecCreacion(new Date());
					if(item.getDisiCodigo()==0l){
						try {
							clubInsDisSedClubService.crear(clubInsDisSedClub);
						} catch (ClubPersistException e) {
							e.printStackTrace();
						}
					}else{
						clubInsDisSedClubService.actualizar(clubInsDisSedClub);
					}
				}
				
				
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}else{
			try {
				ClubInscripcion inscripcion=new ClubInscripcion();
				
				representante.setRepEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				representante.setRepFechCreacion(new Date());
				clubRepresentanteService.crear(representante);
				
				persona.setPerEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				persona.setClubSexo(clubSexoService.findByCodigo(sexo.getSexCodigo()));
				persona.setClubTipIde(clubTipoIdeService.findByCodigo(tipoIdentificacion.getTiinCodigo()));
				persona.setClubRepresentante(representante);
				persona.setPerAutorepresentado("N");
				clubPersonaService.crear(persona);
				
				inscripcion.setClubPersona(persona);
				inscripcion.setInsEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				inscripcion.setInsFecCreacion(new Date());
				clubInscripcionService.crear(inscripcion);
				
				for (InscripcionDTO item : listaInscripcion) {
					ClubSedIn clubSedIn=new ClubSedIn();
					clubSedIn=clubSedInsService.findByCodigoInstucionSede(clubInstitucion.getCluCodigo(), item.getCodSede());
					ClubInsDisSedClub clubInsDisSedClub=new ClubInsDisSedClub();
					clubInsDisSedClub.setHodiCodigo(item.getCodHorario());
					clubInsDisSedClub.setClubInscripcion(inscripcion);
					clubInsDisSedClub.setClubDisXSedIn(clubDisXSedInsService.findByCodigoSedeDisciplina(clubSedIn.getSeinCodigo(),item.getCodDisciplina()));
					clubInsDisSedClub.setIdsiEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
					clubInsDisSedClub.setIdsiFecCreacion(new Date());
					clubInsDisSedClubService.crear(clubInsDisSedClub);
				}
				
				 agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		FacesContext contex = FacesContext.getCurrentInstance();
        try {
			contex.getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() +"/faces/paginas/persona/persona.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void addInscripcion(){
		listaInscripcion=new ArrayList<InscripcionDTO>();
	}
	
	public void addItemInscripcion(){
		horaDia=clubHoraDiaService.findByCodigo(horaDia.getHodiCodigo());
		InscripcionDTO item=new InscripcionDTO(sede.getSedCodigo(),clubSedeService.findByCodigo(sede.getSedCodigo()).getSedDescripcion(),
				disciplina.getDisCodigo(),clubDisciplinaService.buscarPorId(disciplina.getDisCodigo()).getDisDescripcion(),
				horaDia.getHodiCodigo(),horaDia.getClubDia().getDiaDescripcion()+" "+horaDia.getClubHorario().getHorHoraInicio()+"-"+horaDia.getClubHorario().getHorHoraFin(),0l);
		
		listaInscripcion.add(item);
		cancelInscripcion();
	}
	
	public void editItemInscripcion(InscripcionDTO item){
		sede.setSedCodigo(item.getCodSede());
		disciplina.setDisCodigo(item.getCodDisciplina());
	}
	
	public void deleteInscripcion(InscripcionDTO item){
		listaInscripcion.remove(item);
	}
	
	public void cancelInscripcion(){
		sede=new ClubSede();
		disciplina=new ClubDisciplina();
		horaDia=new ClubHorDia();
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

	public ClubInstitucion getClubInstitucion() {
		return clubInstitucion;
	}

	public void setClubInstitucion(ClubInstitucion clubInstitucion) {
		this.clubInstitucion = clubInstitucion;
	}

	public List<ClubSede> getListSedes() {
		return listSedes;
	}

	public void setListSedes(List<ClubSede> listSedes) {
		this.listSedes = listSedes;
	}

	public ClubSede getSede() {
		return sede;
	}

	public void setSede(ClubSede sede) {
		this.sede = sede;
	}

	public List<ClubDisciplina> getListDisciplina() {
		return listDisciplina;
	}

	public void setListDisciplina(List<ClubDisciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}

	public ClubDisciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(ClubDisciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<ClubInsDisSedClub> getListInscripcion() {
		return listInscripcion;
	}

	public void setListInscripcion(List<ClubInsDisSedClub> listInscripcion) {
		this.listInscripcion = listInscripcion;
	}

	public List<InscripcionDTO> getListaInscripcion() {
		return listaInscripcion;
	}

	public void setListaInscripcion(List<InscripcionDTO> listaInscripcion) {
		this.listaInscripcion = listaInscripcion;
	}

	public List<ClubHodiXDissedclub> getListHorario() {
		return listHorario;
	}

	public void setListHorario(List<ClubHodiXDissedclub> listHorario) {
		this.listHorario = listHorario;
	}

	public ClubHodiXDissedclub getHodiDisciplina() {
		return hodiDisciplina;
	}

	public void setHodiDisciplina(ClubHodiXDissedclub hodiDisciplina) {
		this.hodiDisciplina = hodiDisciplina;
	}

	public List<ClubHodiXDissedclub> getListInscripciones() {
		return listInscripciones;
	}

	public void setListInscripciones(List<ClubHodiXDissedclub> listInscripciones) {
		this.listInscripciones = listInscripciones;
	}

	public ClubHorDia getHoraDia() {
		return horaDia;
	}

	public void setHoraDia(ClubHorDia horaDia) {
		this.horaDia = horaDia;
	}
}
