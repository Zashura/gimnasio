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
import ec.com.gimnasio.model.ClubCanton;
import ec.com.gimnasio.model.ClubCinDi;
import ec.com.gimnasio.model.ClubCinXGra;
import ec.com.gimnasio.model.ClubCinturon;
import ec.com.gimnasio.model.ClubDisXSedIn;
import ec.com.gimnasio.model.ClubDisciplina;
import ec.com.gimnasio.model.ClubGrado;
import ec.com.gimnasio.model.ClubInstitucion;
import ec.com.gimnasio.model.ClubParroquia;
import ec.com.gimnasio.model.ClubProvincia;
import ec.com.gimnasio.model.ClubSedIn;
import ec.com.gimnasio.model.ClubSede;
import ec.com.gimnasio.model.ClubTipDi;
import ec.com.gimnasio.resources.Constantes;
import ec.com.gimnasio.resources.ServiceLocator;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubCantonService;
import ec.com.gimnasio.service.ClubCinturonDisciplinaService;
import ec.com.gimnasio.service.ClubCinturonGradoService;
import ec.com.gimnasio.service.ClubCinturonService;
import ec.com.gimnasio.service.ClubDisXSedInsService;
import ec.com.gimnasio.service.ClubDisciplinaService;
import ec.com.gimnasio.service.ClubGradoService;
import ec.com.gimnasio.service.ClubInstitucionService;
import ec.com.gimnasio.service.ClubParXSedInService;
import ec.com.gimnasio.service.ClubParroquiaService;
import ec.com.gimnasio.service.ClubProvinciaService;
import ec.com.gimnasio.service.ClubSedInsService;
import ec.com.gimnasio.service.ClubSedeService;
import ec.com.gimnasio.service.ClubTipoDisciplinaService;

@Named
@ViewScoped
public class SedeController extends BaseController implements Serializable {

	private static final long serialVersionUID = 4973597523116784584L;
	
	@Inject
	private SessionController sessionController;
	@Inject
	private ClubInstitucionService clubInstitucionService;
	@Inject 
	private ClubProvinciaService clubProvinciaService;
	@Inject
	private ClubCantonService clubCantonService;
	@Inject
	private ClubParroquiaService clubParroquiaService;
	@Inject
	private ClubSedeService clubSedeService;
	@Inject
	private ClubParXSedInService clubParXSedInService;
	@Inject
	private ClubSedInsService clubSedInsService;
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
	@Inject
	private ClubDisXSedInsService clubDisXSedInsService;
	
	
	private ClubInstitucion clubInstitucion=new ClubInstitucion();
	private ClubSede clubSede=new ClubSede();
	private ClubProvincia clubProvincia=new ClubProvincia();
	private ClubCanton clubCanton=new ClubCanton();
	private ClubParroquia clubParroquia=new ClubParroquia();
	private CluParXSedIn cluParXSedIn=new CluParXSedIn();
	private ClubSedIn sedeIns=new ClubSedIn();
	private CluParXSedIn cluSedPar=new CluParXSedIn();
	private ClubDisciplina disciplina=new ClubDisciplina();
	private ClubCinturon cinturon= new ClubCinturon();
	private ClubGrado grado=new ClubGrado();
	private ClubCinDi cinturonDisciplina=new ClubCinDi();
	private ClubCinXGra cinturonGrado=new ClubCinXGra();
	private ClubTipDi tipoDisciplina=new ClubTipDi();
	private ClubDisXSedIn disciplinaSede=new ClubDisXSedIn();
	private ClubSedIn sedeInstitucion=new ClubSedIn();
	
	private List<ClubSede> listaSedes=new ArrayList<ClubSede>();
	private List<ClubProvincia> listaProvincia=new ArrayList<ClubProvincia>();
	private List<ClubCanton> listaCanton=new ArrayList<ClubCanton>();
	private List<ClubParroquia> listaParroquia=new ArrayList<ClubParroquia>();
	private List<CluParXSedIn> listaCluParXSedIn=new ArrayList<CluParXSedIn>();
	private List<ClubTipDi> listTipoDisciplina=new ArrayList<ClubTipDi>();
	private List<ClubDisciplina> listDisciplina=new ArrayList<ClubDisciplina>();
	private List<ClubCinturon> listCinturon=new ArrayList<ClubCinturon>();
	private List<ClubGrado> listGrado=new ArrayList<ClubGrado>();
	private List<ClubCinXGra> listCinturonGrado=new ArrayList<ClubCinXGra>();
	
	private boolean update;
	private boolean updateDisciplina;
	private boolean updateCinturonGrado;
	private long codigoCas;
	private String nombreBuscar="";
	
	@PostConstruct
	public void init(){
		UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		codigoCas=usuario.getSede().getCodigo();
		clubInstitucion=clubInstitucionService.findByCodigoCas(usuario.getSede().getCodigo());
		listaProvincia=clubProvinciaService.obtenerActivas();
		listaCluParXSedIn=clubParXSedInService.findByInstitucion(clubInstitucion.getCluCodigo());
		listTipoDisciplina=clubTipoDisciplinaService.obtenerActivas();
		listCinturon=clubCinturonService.obtenerActivas();
		listGrado=clubGradoService.obtenerActivas();
		update=Boolean.FALSE;
		updateDisciplina=Boolean.FALSE;
	}
	
	public void findbyName(){
		listaCluParXSedIn=clubParXSedInService.findBySedeNombre(nombreBuscar,clubInstitucion.getCluCodigo());
	}
	
	public void delete(ClubSede sede){
		
	}
	
	public void edit(CluParXSedIn item){
		update=Boolean.TRUE;
		clubSede=item.getClubSedIn().getClubSede();
		sedeIns=item.getClubSedIn();
		cluSedPar=item;
		clubParroquia=item.getClubParroquia();
		clubProvincia=item.getClubParroquia().getClubCanton().getClubProvincia();
		clubCanton=item.getClubParroquia().getClubCanton();
		findCanton();
		findParroquia();
	}
	
	public void addDisciplina(CluParXSedIn item){
		sedeInstitucion=item.getClubSedIn();
		listDisciplina=clubDisciplinaService.listBySede(sedeInstitucion.getClubSede().getSedCodigo());
	}
	
	public void cancel(){
		update=Boolean.FALSE;
		clubSede=new ClubSede();
	}
	
	public void findCanton(){
		listaCanton=clubCantonService.buscarPorDpaProvincia(clubProvincia.getProCodigo());
	}
	
	public void findParroquia(){
		listaParroquia=clubParroquiaService.buscarPorCodCanton(clubCanton.getCanCodigo());
	}
	
	public void save(){
		if(update){
			try {
				cluSedPar.setClubParroquia(clubParroquiaService.buscarPorId(clubParroquia.getParCodigo()));
				clubParXSedInService.actualizar(cluSedPar);
				
				clubSedeService.actualizar(clubSede);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
			
		}else{
			clubSede.setSedFecCreacion(new Date());
			clubSede.setSedEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
			try {
				clubSedeService.crear(clubSede);
				
				sedeIns.setClubInstitucion(clubInstitucion);
				sedeIns.setClubSede(clubSede);
				sedeIns.setSeinEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				sedeIns.setSeinFecCreacion(new Date());
				sedeIns.setSeinNemonico("A");
				clubSedInsService.crear(sedeIns);
				
				cluSedPar.setClubSedIn(sedeIns);
				cluSedPar.setClubParroquia(clubParroquiaService.buscarPorId(clubParroquia.getParCodigo()));
				cluSedPar.setPasiFecCreacion(new Date());
				cluSedPar.setPasiNemonico("A");
				cluSedPar.setPasiEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				clubParXSedInService.crear(cluSedPar);
				 agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		update=Boolean.FALSE;
		clubSede=new ClubSede();
		cluSedPar=new CluParXSedIn();
		listaCluParXSedIn=clubParXSedInService.findByInstitucion(clubInstitucion.getCluCodigo());
	}
	
	public void editDisciplina(ClubDisciplina dis){
		disciplina=dis;
		tipoDisciplina=dis.getClubTipDi();
		updateDisciplina=Boolean.TRUE;
	}
	
	public void saveDisciplina(){
		if(updateDisciplina){
			try {
				disciplina.setClubTipDi(clubTipoDisciplinaService.buscarPorId(tipoDisciplina.getTidiCodigo()));
				clubDisciplinaService.actualizar(disciplina);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}else{
			try {
				disciplina.setClubTipDi(clubTipoDisciplinaService.buscarPorId(tipoDisciplina.getTidiCodigo()));
				disciplina.setDisEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				disciplina.setDisFecCreacion(new Date());
				clubDisciplinaService.crear(disciplina);
							
				disciplinaSede.setClubDisciplina(disciplina);
				disciplinaSede.setClubSedIn(sedeInstitucion);
				disciplinaSede.setDisiEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				disciplinaSede.setDisFechCreacion(new Date());
				clubDisXSedInsService.crear(disciplinaSede);
				
				agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		listDisciplina=clubDisciplinaService.listBySede(sedeInstitucion.getClubSede().getSedCodigo());
		cancelDisciplina();
	}
	
	public void cancelDisciplina(){
		updateDisciplina=Boolean.FALSE;
		disciplina=new ClubDisciplina();
		tipoDisciplina=new ClubTipDi();
	}
	
	public void addCinturon(ClubDisciplina dis){
		updateCinturonGrado=Boolean.FALSE;
		disciplina=new ClubDisciplina();
		disciplina=dis;
		listCinturonGrado=clubCinturonGradoService.listByDisciplina(dis.getDisCodigo());
		grado=new ClubGrado();
		cinturon=new ClubCinturon();
		cinturonGrado=new ClubCinXGra();
		cinturonDisciplina=new ClubCinDi();
	}
	
	public void saveCinturonGrado(){
		if(updateCinturonGrado){
			try {
				cinturonGrado.setClubCinturon(clubCinturonService.findByCodigo(cinturon.getCinCodigo()));
				cinturonGrado.setClubGrado(clubGradoService.findByCodigo(grado.getGraCodigo()));
				clubCinturonGradoService.actualizar(cinturonGrado);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}else{
			try {
				cinturonGrado.setCigaEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				cinturonGrado.setCigaFecCreacion(new Date());
				cinturonGrado.setClubCinturon(clubCinturonService.findByCodigo(cinturon.getCinCodigo()));
				cinturonGrado.setClubGrado(clubGradoService.findByCodigo(grado.getGraCodigo()));
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
		listCinturonGrado=clubCinturonGradoService.listByDisciplina(disciplina.getDisCodigo());
		cancelCinturonGrado();
	}

	public void editCinturonGrado(ClubCinXGra cinGra){
		updateCinturonGrado=Boolean.TRUE;
		grado=cinGra.getClubGrado();
		cinturon=cinGra.getClubCinturon();
		cinturonGrado=cinGra;
	}
	
	public void cancelCinturonGrado(){
		grado=new ClubGrado();
		cinturon=new ClubCinturon();
		cinturonGrado=new ClubCinXGra();
		updateCinturonGrado=Boolean.FALSE;
	}
	
	public ClubInstitucion getClubInstitucion() {
		return clubInstitucion;
	}

	public void setClubInstitucion(ClubInstitucion clubInstitucion) {
		this.clubInstitucion = clubInstitucion;
	}

	public List<ClubSede> getListaSedes() {
		return listaSedes;
	}

	public void setListaSedes(List<ClubSede> listaSedes) {
		this.listaSedes = listaSedes;
	}

	public ClubSede getClubSede() {
		return clubSede;
	}

	public void setClubSede(ClubSede clubSede) {
		this.clubSede = clubSede;
	}

	public ClubProvincia getClubProvincia() {
		return clubProvincia;
	}

	public void setClubProvincia(ClubProvincia clubProvincia) {
		this.clubProvincia = clubProvincia;
	}

	public ClubCanton getClubCanton() {
		return clubCanton;
	}

	public void setClubCanton(ClubCanton clubCanton) {
		this.clubCanton = clubCanton;
	}

	public ClubParroquia getClubParroquia() {
		return clubParroquia;
	}

	public void setClubParroquia(ClubParroquia clubParroquia) {
		this.clubParroquia = clubParroquia;
	}

	public List<ClubCanton> getListaCanton() {
		return listaCanton;
	}

	public void setListaCanton(List<ClubCanton> listaCanton) {
		this.listaCanton = listaCanton;
	}

	public List<ClubParroquia> getListaParroquia() {
		return listaParroquia;
	}

	public void setListaParroquia(List<ClubParroquia> listaParroquia) {
		this.listaParroquia = listaParroquia;
	}

	public List<ClubProvincia> getListaProvincia() {
		return listaProvincia;
	}

	public void setListaProvincia(List<ClubProvincia> listaProvincia) {
		this.listaProvincia = listaProvincia;
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

	public List<ClubCinturon> getListCinturon() {
		return listCinturon;
	}

	public void setListCinturon(List<ClubCinturon> listCinturon) {
		this.listCinturon = listCinturon;
	}

	public List<ClubGrado> getListGrado() {
		return listGrado;
	}

	public void setListGrado(List<ClubGrado> listGrado) {
		this.listGrado = listGrado;
	}

	public List<ClubCinXGra> getListCinturonGrado() {
		return listCinturonGrado;
	}

	public void setListCinturonGrado(List<ClubCinXGra> listCinturonGrado) {
		this.listCinturonGrado = listCinturonGrado;
	}
	
	

}
