package ec.com.gimnasio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the club_hor_dia database table.
 * 
 */
@Entity
@Table(name="club_hor_dia")
@NamedQuery(name="ClubHorDia.findAll", query="SELECT c FROM ClubHorDia c")
public class ClubHorDia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_HOR_DIA_HODICODIGO_GENERATOR", sequenceName="SEQ_CLUB_HOR_DIA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_HOR_DIA_HODICODIGO_GENERATOR")
	@Column(name="hodi_codigo")
	private long hodiCodigo;

	@Column(name="hodi_estado")
	private int hodiEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="hodi_fec_creacion")
	private Date hodiFecCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="hodi_fec_fin")
	private Date hodiFecFin;

	@Temporal(TemporalType.DATE)
	@Column(name="hodi_fec_inicio")
	private Date hodiFecInicio;

	//bi-directional many-to-one association to ClubHodiXDissedclub
	@OneToMany(mappedBy="clubHorDia")
	private List<ClubHodiXDissedclub> clubHodiXDissedclubs;

	//bi-directional many-to-one association to ClubDia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dia_codigo")
	private ClubDia clubDia;

	//bi-directional many-to-one association to ClubHorario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hor_codigo")
	private ClubHorario clubHorario;

	public ClubHorDia() {
	}

	public long getHodiCodigo() {
		return this.hodiCodigo;
	}

	public void setHodiCodigo(long hodiCodigo) {
		this.hodiCodigo = hodiCodigo;
	}

	public int getHodiEstado() {
		return this.hodiEstado;
	}

	public void setHodiEstado(int hodiEstado) {
		this.hodiEstado = hodiEstado;
	}

	public Date getHodiFecCreacion() {
		return this.hodiFecCreacion;
	}

	public void setHodiFecCreacion(Date hodiFecCreacion) {
		this.hodiFecCreacion = hodiFecCreacion;
	}

	public Date getHodiFecFin() {
		return this.hodiFecFin;
	}

	public void setHodiFecFin(Date hodiFecFin) {
		this.hodiFecFin = hodiFecFin;
	}

	public Date getHodiFecInicio() {
		return this.hodiFecInicio;
	}

	public void setHodiFecInicio(Date hodiFecInicio) {
		this.hodiFecInicio = hodiFecInicio;
	}

	public List<ClubHodiXDissedclub> getClubHodiXDissedclubs() {
		return this.clubHodiXDissedclubs;
	}

	public void setClubHodiXDissedclubs(List<ClubHodiXDissedclub> clubHodiXDissedclubs) {
		this.clubHodiXDissedclubs = clubHodiXDissedclubs;
	}

	public ClubHodiXDissedclub addClubHodiXDissedclub(ClubHodiXDissedclub clubHodiXDissedclub) {
		getClubHodiXDissedclubs().add(clubHodiXDissedclub);
		clubHodiXDissedclub.setClubHorDia(this);

		return clubHodiXDissedclub;
	}

	public ClubHodiXDissedclub removeClubHodiXDissedclub(ClubHodiXDissedclub clubHodiXDissedclub) {
		getClubHodiXDissedclubs().remove(clubHodiXDissedclub);
		clubHodiXDissedclub.setClubHorDia(null);

		return clubHodiXDissedclub;
	}

	public ClubDia getClubDia() {
		return this.clubDia;
	}

	public void setClubDia(ClubDia clubDia) {
		this.clubDia = clubDia;
	}

	public ClubHorario getClubHorario() {
		return this.clubHorario;
	}

	public void setClubHorario(ClubHorario clubHorario) {
		this.clubHorario = clubHorario;
	}

}