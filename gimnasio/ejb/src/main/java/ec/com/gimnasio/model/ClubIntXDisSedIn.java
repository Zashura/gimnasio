package ec.com.gimnasio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the club_int_x_dis_sed_ins database table.
 * 
 */
@Entity
@Table(name="club_int_x_dis_sed_ins")
@NamedQuery(name="ClubIntXDisSedIn.findAll", query="SELECT c FROM ClubIntXDisSedIn c")
public class ClubIntXDisSedIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_INT_X_DIS_SED_INS_IDSICODIGO_GENERATOR", sequenceName="SEQ_CLUB_INT_X_DIS_SED_INS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_INT_X_DIS_SED_INS_IDSICODIGO_GENERATOR")
	@Column(name="idsi_codigo")
	private long idsiCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name="idis_fecha_creacion")
	private Date idisFechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="idis_fecha_fin")
	private Date idisFechaFin;

	@Column(name="idsi_estado")
	private int idsiEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="iidis_fecha_inicio")
	private Date iidisFechaInicio;

	//bi-directional many-to-one association to ClubHodiXDissedclub
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diho_codigo")
	private ClubHodiXDissedclub clubHodiXDissedclub;

	//bi-directional many-to-one association to ClubInstructor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="int_codigo")
	private ClubInstructor clubInstructor;

	public ClubIntXDisSedIn() {
	}

	public long getIdsiCodigo() {
		return this.idsiCodigo;
	}

	public void setIdsiCodigo(long idsiCodigo) {
		this.idsiCodigo = idsiCodigo;
	}

	public Date getIdisFechaCreacion() {
		return this.idisFechaCreacion;
	}

	public void setIdisFechaCreacion(Date idisFechaCreacion) {
		this.idisFechaCreacion = idisFechaCreacion;
	}

	public Date getIdisFechaFin() {
		return this.idisFechaFin;
	}

	public void setIdisFechaFin(Date idisFechaFin) {
		this.idisFechaFin = idisFechaFin;
	}

	public int getIdsiEstado() {
		return this.idsiEstado;
	}

	public void setIdsiEstado(int idsiEstado) {
		this.idsiEstado = idsiEstado;
	}

	public Date getIidisFechaInicio() {
		return this.iidisFechaInicio;
	}

	public void setIidisFechaInicio(Date iidisFechaInicio) {
		this.iidisFechaInicio = iidisFechaInicio;
	}

	public ClubHodiXDissedclub getClubHodiXDissedclub() {
		return this.clubHodiXDissedclub;
	}

	public void setClubHodiXDissedclub(ClubHodiXDissedclub clubHodiXDissedclub) {
		this.clubHodiXDissedclub = clubHodiXDissedclub;
	}

	public ClubInstructor getClubInstructor() {
		return this.clubInstructor;
	}

	public void setClubInstructor(ClubInstructor clubInstructor) {
		this.clubInstructor = clubInstructor;
	}

}