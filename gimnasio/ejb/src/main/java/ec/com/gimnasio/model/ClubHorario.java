package ec.com.gimnasio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the club_horario database table.
 * 
 */
@Entity
@Table(name="club_horario")
@NamedQuery(name="ClubHorario.findAll", query="SELECT c FROM ClubHorario c")
public class ClubHorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_HORARIO_HORCODIGO_GENERATOR", sequenceName="SEQ_CLUB_HORARIO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_HORARIO_HORCODIGO_GENERATOR")
	@Column(name="hor_codigo")
	private long horCodigo;

	@Column(name="hor_estado")
	private int horEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="hor_fec_creacion")
	private Date horFecCreacion;

	@Temporal(TemporalType.TIME)
	@Column(name="hor_hora_fin")
	private Date horHoraFin;

	@Temporal(TemporalType.TIME)
	@Column(name="hor_hora_inicio")
	private Date horHoraInicio;

	//bi-directional many-to-one association to ClubHorDia
	@OneToMany(mappedBy="clubHorario")
	private List<ClubHorDia> clubHorDias;

	public ClubHorario() {
	}

	public long getHorCodigo() {
		return this.horCodigo;
	}

	public void setHorCodigo(long horCodigo) {
		this.horCodigo = horCodigo;
	}

	public int getHorEstado() {
		return this.horEstado;
	}

	public void setHorEstado(int horEstado) {
		this.horEstado = horEstado;
	}

	public Date getHorFecCreacion() {
		return this.horFecCreacion;
	}

	public void setHorFecCreacion(Date horFecCreacion) {
		this.horFecCreacion = horFecCreacion;
	}

	public Date getHorHoraFin() {
		return this.horHoraFin;
	}

	public void setHorHoraFin(Date horHoraFin) {
		this.horHoraFin = horHoraFin;
	}

	public Date getHorHoraInicio() {
		return this.horHoraInicio;
	}

	public void setHorHoraInicio(Date horHoraInicio) {
		this.horHoraInicio = horHoraInicio;
	}

	public List<ClubHorDia> getClubHorDias() {
		return this.clubHorDias;
	}

	public void setClubHorDias(List<ClubHorDia> clubHorDias) {
		this.clubHorDias = clubHorDias;
	}

	public ClubHorDia addClubHorDia(ClubHorDia clubHorDia) {
		getClubHorDias().add(clubHorDia);
		clubHorDia.setClubHorario(this);

		return clubHorDia;
	}

	public ClubHorDia removeClubHorDia(ClubHorDia clubHorDia) {
		getClubHorDias().remove(clubHorDia);
		clubHorDia.setClubHorario(null);

		return clubHorDia;
	}

}