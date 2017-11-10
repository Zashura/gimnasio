 package ec.com.gimnasio.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.remote.UsuarioServiceRemote;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInstitucion;
import ec.com.gimnasio.resources.Constantes;
import ec.com.gimnasio.resources.ServiceLocator;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubInstitucionService;

@Named
@ViewScoped
public class InstitucionController extends BaseController implements Serializable {

	private static final long serialVersionUID = 4973597523116784584L;
	
	@Inject
	private SessionController sessionController;
	@Inject
	private ClubInstitucionService clubInstitucionService;
	
	private ClubInstitucion clubInstitucion=new ClubInstitucion();
	private boolean update;
	private long codigoCas;
	
	@PostConstruct
	public void init(){
		UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		codigoCas=usuario.getSede().getCodigo();
		clubInstitucion=clubInstitucionService.findByCodigoCas(usuario.getSede().getCodigo());
		if(clubInstitucion==null){
			clubInstitucion=new ClubInstitucion();
			update=Boolean.FALSE;
		}else{
			update=Boolean.TRUE;
		}
	}
	
	public void save(){
		if(update){
			try {
				clubInstitucionService.actualizar(clubInstitucion);
				 agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
			
		}else{
			clubInstitucion.setCluCodCas(codigoCas);
			clubInstitucion.setCluFecCreacion(new Date());
			clubInstitucion.setCluEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
			try {
				clubInstitucionService.crear(clubInstitucion);
				 agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		init();
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		 UploadedFile uploadedFile = event.getFile();
		 String fileName = uploadedFile.getFileName();
		 String contentType = uploadedFile.getContentType();
		 byte[] contents = uploadedFile.getContents(); // Or getInputStream()
		 clubInstitucion.setCluLogotipo(contents);
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public ClubInstitucion getClubInstitucion() {
		return clubInstitucion;
	}

	public void setClubInstitucion(ClubInstitucion clubInstitucion) {
		this.clubInstitucion = clubInstitucion;
	}
	
	

}
