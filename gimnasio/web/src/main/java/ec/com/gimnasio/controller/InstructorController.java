 package ec.com.gimnasio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInstructor;
import ec.com.gimnasio.resources.Constantes;
import ec.com.gimnasio.resources.IdentificacionUtils;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubInstructorService;

@Named
@ViewScoped
public class InstructorController extends BaseController implements Serializable {

	private static final long serialVersionUID = 4973597523116784584L;
	
	//@Inject
	//private SessionController sessionController;
	@Inject
	private ClubInstructorService clubInstructorService;
	
	private ClubInstructor instructor=new ClubInstructor();
	private List<ClubInstructor> listInstructor=new ArrayList<ClubInstructor>();
	private boolean update;
	private String nombreBuscar="";
	private boolean validateCedula;
	
	@PostConstruct
	public void init(){
		//UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		//Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		listInstructor=clubInstructorService.obtenerActivas();
		update=Boolean.FALSE;
		validateCedula=Boolean.TRUE;
	}
	
	public void findbyName(){
		listInstructor=clubInstructorService.buscarPorNombre(nombreBuscar);
	}
	
	public void delete(ClubInstructor item){
		try {
			item.setIntEstado(0);
			clubInstructorService.actualizar(item);
			agregarMensajeInformacion("Registro actualizado exitosamente", "");
		} catch (ClubUpdateException e) {
			agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
			e.printStackTrace();
		}
	}
	
	public void edit(ClubInstructor item){
		update=Boolean.TRUE;
		validateCedula=Boolean.TRUE;
		instructor=item;
	}
	
	public void cancel(){
		update=Boolean.FALSE;
		instructor=new ClubInstructor();
	}
	
	public void validarCedula(){
		validateCedula=IdentificacionUtils.validateCedula(instructor.getIntIdentificacion());
	}
	
	public void save(){
		validateCedula=Boolean.FALSE;
		if(IdentificacionUtils.validateCedula(instructor.getIntIdentificacion())){
			validateCedula=Boolean.TRUE;
		if(update){
			try {
				clubInstructorService.actualizar(instructor);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}else{
			
			try {
				instructor.setIntEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				instructor.setIntFecCreacion(new Date());
				clubInstructorService.crear(instructor);
				 agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		}
		cancel();
		update=Boolean.FALSE;
		listInstructor=clubInstructorService.obtenerActivas();
	}

	public String getNombreBuscar() {
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}

	public List<ClubInstructor> getListInstructor() {
		return listInstructor;
	}

	public void setListInstructor(List<ClubInstructor> listInstructor) {
		this.listInstructor = listInstructor;
	}

	public ClubInstructor getInstructor() {
		return instructor;
	}

	public void setInstructor(ClubInstructor instructor) {
		this.instructor = instructor;
	}

	public boolean isValidateCedula() {
		return validateCedula;
	}

	public void setValidateCedula(boolean validateCedula) {
		this.validateCedula = validateCedula;
	}
}
