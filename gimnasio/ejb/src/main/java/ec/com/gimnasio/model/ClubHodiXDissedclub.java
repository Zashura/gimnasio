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
 * The persistent class for the club_hodi_x_dissedclub database table.
 * 
 */
@Entity
@Table(name="club_hodi_x_dissedclub")
@NamedQuery(name="ClubHodiXDissedclub.findAll", query="SELECT c FROM ClubHodiXDissedclub c")
public class ClubHodiXDissedclub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_HODI_X_DISSEDCLUB_DIHOCODIGO_GENERATOR", sequenceName="SEQ_CLUB_HODI_X_DISSEDCLUB",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_HODI_X_DISSEDCLUB_DIHOCODIGO_GENERATOR")
	@Column(name="diho_codigo")
	private long dihoCodigo;

	@Column(name="diho_estado")
	private int dihoEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="diho_fec_creacion")
	private Date dihoFecCreacion;

	//bi-directional many-to-one association to ClubDisXSedIn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="disi_codigo")
	private ClubDisXSedIn clubDisXSedIn;

	//bi-directional many-to-one association to ClubHorDia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hodi_codigo")
	private ClubHorDia clubHorDia;

	//bi-directional many-to-one association to ClubIntXDisSedIn
	@OneToMany(mappedBy="clubHodiXDissedclub")
	private List<ClubIntXDisSedIn> clubIntXDisSedIns;

	public ClubHodiXDissedclub() {
	}

	public long getDihoCodigo() {
		return this.dihoCodigo;
	}

	public void setDihoCodigo(long dihoCodigo) {
		this.dihoCodigo = dihoCodigo;
	}

	public int getDihoEstado() {
		return this.dihoEstado;
	}

	public void setDihoEstado(int dihoEstado) {
		this.dihoEstado = dihoEstado;
	}

	public Date getDihoFecCreacion() {
		return this.dihoFecCreacion;
	}

	public void setDihoFecCreacion(Date dihoFecCreacion) {
		this.dihoFecCreacion = dihoFecCreacion;
	}

	public ClubDisXSedIn getClubDisXSedIn() {
		return this.clubDisXSedIn;
	}

	public void setClubDisXSedIn(ClubDisXSedIn clubDisXSedIn) {
		this.clubDisXSedIn = clubDisXSedIn;
	}

	public ClubHorDia getClubHorDia() {
		return this.clubHorDia;
	}

	public void setClubHorDia(ClubHorDia clubHorDia) {
		this.clubHorDia = clubHorDia;
	}

	public List<ClubIntXDisSedIn> getClubIntXDisSedIns() {
		return this.clubIntXDisSedIns;
	}

	public void setClubIntXDisSedIns(List<ClubIntXDisSedIn> clubIntXDisSedIns) {
		this.clubIntXDisSedIns = clubIntXDisSedIns;
	}

	public ClubIntXDisSedIn addClubIntXDisSedIn(ClubIntXDisSedIn clubIntXDisSedIn) {
		getClubIntXDisSedIns().add(clubIntXDisSedIn);
		clubIntXDisSedIn.setClubHodiXDissedclub(this);

		return clubIntXDisSedIn;
	}

	public ClubIntXDisSedIn removeClubIntXDisSedIn(ClubIntXDisSedIn clubIntXDisSedIn) {
		getClubIntXDisSedIns().remove(clubIntXDisSedIn);
		clubIntXDisSedIn.setClubHodiXDissedclub(null);

		return clubIntXDisSedIn;
	}

}