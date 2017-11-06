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
import ec.com.gimnasio.model.ClubDia;
import ec.com.gimnasio.model.ClubHorDia;
import ec.com.gimnasio.model.ClubHorario;
import ec.com.gimnasio.resources.Constantes;
import ec.com.gimnasio.scope.ViewScoped;
import ec.com.gimnasio.service.ClubDiaService;
import ec.com.gimnasio.service.ClubHoraDiaService;
import ec.com.gimnasio.service.ClubHorarioService;

@Named
@ViewScoped
public class HorarioController extends BaseController implements Serializable {

	private static final long serialVersionUID = 4973597523116784584L;
	
	//@Inject
	//private SessionController sessionController;
	@Inject
	private ClubDiaService clubDiaService;
	@Inject
	private ClubHorarioService clubHorarioService;
	@Inject
	private ClubHoraDiaService clubHoraDiaService;
	
	private ClubHorario horario=new ClubHorario();
	private ClubDia dia=new ClubDia();
	private ClubHorDia horarioDia=new ClubHorDia();
	private List<ClubDia> listDias=new ArrayList<ClubDia>();
	private List<ClubHorDia> listHorarioDia=new ArrayList<ClubHorDia>();
	private boolean update;
	private String nombreBuscar="";
	
	@PostConstruct
	public void init(){
		//UsuarioServiceRemote servicioUsuario = ServiceLocator.buscarInstancia(ec.com.gimnasio.resources.Constantes.URL_SEGURIDADES, UsuarioServiceRemote.class, true); 
		//Usuario usuario = servicioUsuario.buscarPorIdentificacion(sessionController.getUserSecurity().getUsername());
		listDias=clubDiaService.obtenerActivas();
		listHorarioDia=clubHoraDiaService.obtenerActivas();
		update=Boolean.FALSE;
	}
	
	public void findbyName(){
		
	}
	
	public void delete(ClubHorDia item){
		
	}
	
	public void edit(ClubHorDia item){
		update=Boolean.TRUE;
		dia=item.getClubDia();
		horario=item.getClubHorario();
		horarioDia=item;
	}
	
	public void cancel(){
		update=Boolean.FALSE;
		dia=new ClubDia();
		horario=new ClubHorario();
		horarioDia=new ClubHorDia();
	}
	
	public void save(){
		if(update){
			try {
				clubHorarioService.actualizar(horario);
				horarioDia.setHodiFecInicio(horario.getHorHoraInicio());
				horarioDia.setHodiFecFin(horario.getHorHoraFin());
				horarioDia.setClubHorario(horario);
				horarioDia.setClubDia(clubDiaService.findByCodigo(dia.getDiaCodigo()));
				clubHoraDiaService.actualizar(horarioDia);
				agregarMensajeInformacion("Registro actualizado exitosamente", "");
			} catch (ClubUpdateException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}else{
			
			try {
				horario.setHorEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				horario.setHorFecCreacion(new Date());
				clubHorarioService.crear(horario);
				
				horarioDia.setHodiEstado(Constantes.REGISTRO_ACTIVO_NUMERO);
				horarioDia.setHodiFecCreacion(new Date());
				horarioDia.setHodiFecInicio(horario.getHorHoraInicio());
				horarioDia.setHodiFecFin(horario.getHorHoraFin());
				horarioDia.setClubHorario(horario);
				horarioDia.setClubDia(clubDiaService.findByCodigo(dia.getDiaCodigo()));
				clubHoraDiaService.crear(horarioDia);
				 agregarMensajeInformacion("Registro ingresado exitosamente", "");
			} catch (ClubPersistException e) {
				agregarMensajeError(Constantes.LABEL_ERROR,Constantes.ERROR_CREACION);
				e.printStackTrace();
			}
		}
		cancel();
		listHorarioDia=clubHoraDiaService.obtenerActivas();
	}

	public String getNombreBuscar() {
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}

	public ClubHorario getHorario() {
		return horario;
	}

	public void setHorario(ClubHorario horario) {
		this.horario = horario;
	}

	public ClubDia getDia() {
		return dia;
	}

	public void setDia(ClubDia dia) {
		this.dia = dia;
	}

	public List<ClubDia> getListDias() {
		return listDias;
	}

	public void setListDias(List<ClubDia> listDias) {
		this.listDias = listDias;
	}

	public List<ClubHorDia> getListHorarioDia() {
		return listHorarioDia;
	}

	public void setListHorarioDia(List<ClubHorDia> listHorarioDia) {
		this.listHorarioDia = listHorarioDia;
	}

	public ClubHorDia getHorarioDia() {
		return horarioDia;
	}

	public void setHorarioDia(ClubHorDia horarioDia) {
		this.horarioDia = horarioDia;
	}
}
