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
import ec.com.gimnasio.model.ClubCinDi;
import ec.com.gimnasio.model.ClubCinXGra;
import ec.com.gimnasio.model.ClubCinturon;
import ec.com.gimnasio.model.ClubDisciplina;
import ec.com.gimnasio.model.ClubGrado;
import ec.com.gimnasio.model.ClubInstitucion;
import ec.com.gimnasio.model.ClubSede;
import ec.com.gimnasio.model.ClubTipDi;
import ec.com.gimnasio.resources.Constantes;
import ec.com.gimnasio.resources.ServiceLocator;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubCinturonDisciplinaService;
import ec.com.gimnasio.service.ClubCinturonGradoService;
import ec.com.gimnasio.service.ClubCinturonService;
import ec.com.gimnasio.service.ClubDisciplinaService;
import ec.com.gimnasio.service.ClubGradoService;
import ec.com.gimnasio.service.ClubInstitucionService;
import ec.com.gimnasio.service.ClubParXSedInService;
import ec.com.gimnasio.service.ClubTipoDisciplinaService;

@Named
@ViewScoped
public class DisciplinaController extends BaseController implements Serializable {

	private static final long serialVersionUID = 4973597523116784584L;
	
	@Inject
	private SessionController sessionController;
	@Inject
	private ClubInstitucionService clubInstitucionService;
	@Inject
	private ClubParXSedInService clubParXSedInService;
	@Inject
	private ClubDisciplinaService clubDisciplinaService;
	@Inject
	private ClubTipoDisciplinaService clubTipoDisciplinaService;
	@Inject
	private ClubCinturonService clubCinturonService;
	@Inject
	private ClubGradoService clubGradoService;
	@Inject
	private ClubCinturonGradoService clubCinturonGradoService;
	@Inject
	private ClubCinturonDisciplinaService clubCinturonDisciplinaService;
	
	
	private ClubInstitucion clubInstitucion=new ClubInstitucion();
	private CluParXSedIn cluParXSedIn=new CluParXSedIn();
	private ClubDisciplina disciplina=new ClubDisciplina();
	private ClubCinturon cinturon= new ClubCinturon();
	private ClubGrado grado=new ClubGrado();
	private ClubCinDi cinturonDisciplina=new ClubCinDi();
	private ClubCinXGra cinturonGrado=new ClubCinXGra();
	private ClubTipDi tipoDisciplina=new ClubTipDi();
	
	private List<CluParXSedIn> listaCluParXSedIn=new ArrayList<CluParXSedIn>();
	private List<ClubTipDi> listTipoDisciplina=new ArrayList<ClubTipDi>();
	private List<ClubDisciplina> listDisciplina=new ArrayList<ClubDisciplina>();
	private List<ClubGrado> listGrado=new ArrayList<ClubGrado>();
	
	private boolean update;
	private boolean updateDisciplina;
	private long codigoCas;
	private String nombreBuscar="";
	
	@PostConstruct
	public void init(){
		UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		codigoCas=usuario.getSede().getCodigo();
		clubInstitucion=clubInstitucionService.findByCodigoCas(usuario.getSede().getCodigo());
		listaCluParXSedIn=clubParXSedInService.findByInstitucion(clubInstitucion.getCluCodigo());
		listTipoDisciplina=clubTipoDisciplinaService.obtenerActivas();
		listDisciplina=clubDisciplinaService.obtenerActivas();
		update=Boolean.FALSE;
		updateDisciplina=Boolean.FALSE;
	}
	
	public void findbyName(){
		listaCluParXSedIn=clubParXSedInService.findBySedeNombre(nombreBuscar,clubInstitucion.getCluCodigo());
	}
	
	public void delete(ClubSede sede){
		
	}
	
	
	public void editDisciplina(ClubDisciplina dis){
		disciplina=dis;
		updateDisciplina=Boolean.TRUE;
	}
	
	public void saveDisciplina(){
		if(updateDisciplina){
			try {
				clubGradoService.actualizar(grado);
				clubDisciplinaService.actualizar(disciplina);
				clubCinturonService.actualizar(cinturon);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}else{
			try {
				grado.setGraEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				grado.setGraFecCreacion(new Date());
				clubGradoService.crear(grado);
				disciplina.setClubTipDi(clubTipoDisciplinaService.buscarPorId(tipoDisciplina.getTidiCodigo()));
				disciplina.setDisEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				disciplina.setDisFecCreacion(new Date());
				clubDisciplinaService.crear(disciplina);
				cinturon.setCinEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				cinturon.setCinFecCreacion(new Date());
				clubCinturonService.crear(cinturon);
				cinturonGrado.setCigaEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				cinturonGrado.setCigaFecCreacion(new Date());
				cinturonGrado.setClubCinturon(cinturon);
				cinturonGrado.setClubGrado(grado);
				clubCinturonGradoService.crear(cinturonGrado);
				cinturonDisciplina.setCidiEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				cinturonDisciplina.setCidiFecCreacion(new Date());
				cinturonDisciplina.setClubCinturon(cinturon);
				cinturonDisciplina.setClubDisciplina(disciplina);
				clubCinturonDisciplinaService.crear(cinturonDisciplina);
				agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		listDisciplina=clubDisciplinaService.obtenerActivas();
		cancelDisciplina();
	}
	
	public void cancelDisciplina(){
		updateDisciplina=Boolean.FALSE;
		disciplina=new ClubDisciplina();
		grado=new ClubGrado();
		cinturon=new ClubCinturon();
		cinturonGrado=new ClubCinXGra();
		cinturonDisciplina=new ClubCinDi();
	}

	public ClubInstitucion getClubInstitucion() {
		return clubInstitucion;
	}

	public void setClubInstitucion(ClubInstitucion clubInstitucion) {
		this.clubInstitucion = clubInstitucion;
	}

	public String getNombreBuscar() {
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}

	public CluParXSedIn getCluParXSedIn() {
		return cluParXSedIn;
	}

	public void setCluParXSedIn(CluParXSedIn cluParXSedIn) {
		this.cluParXSedIn = cluParXSedIn;
	}

	public List<CluParXSedIn> getListaCluParXSedIn() {
		return listaCluParXSedIn;
	}

	public void setListaCluParXSedIn(List<CluParXSedIn> listaCluParXSedIn) {
		this.listaCluParXSedIn = listaCluParXSedIn;
	}

	public ClubDisciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(ClubDisciplina disciplina) {
		this.disciplina = disciplina;
	}

	public ClubCinturon getCinturon() {
		return cinturon;
	}

	public void setCinturon(ClubCinturon cinturon) {
		this.cinturon = cinturon;
	}

	public ClubGrado getGrado() {
		return grado;
	}

	public void setGrado(ClubGrado grado) {
		this.grado = grado;
	}

	public ClubCinDi getCinturonDisciplina() {
		return cinturonDisciplina;
	}

	public void setCinturonDisciplina(ClubCinDi cinturonDisciplina) {
		this.cinturonDisciplina = cinturonDisciplina;
	}

	public ClubCinXGra getCinturonGrado() {
		return cinturonGrado;
	}

	public void setCinturonGrado(ClubCinXGra cinturonGrado) {
		this.cinturonGrado = cinturonGrado;
	}

	public ClubTipDi getTipoDisciplina() {
		return tipoDisciplina;
	}

	public void setTipoDisciplina(ClubTipDi tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}

	public List<ClubTipDi> getListTipoDisciplina() {
		return listTipoDisciplina;
	}

	public void setListTipoDisciplina(List<ClubTipDi> listTipoDisciplina) {
		this.listTipoDisciplina = listTipoDisciplina;
	}

	public List<ClubDisciplina> getListDisciplina() {
		return listDisciplina;
	}

	public void setListDisciplina(List<ClubDisciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}
}
