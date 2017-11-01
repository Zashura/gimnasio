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
 * The persistent class for the club_dis_x_sed_ins database table.
 * 
 */
@Entity
@Table(name="club_dis_x_sed_ins")
@NamedQuery(name="ClubDisXSedIn.findAll", query="SELECT c FROM ClubDisXSedIn c")
public class ClubDisXSedIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_DIS_X_SED_INS_DISICODIGO_GENERATOR", sequenceName="SEQ_CLUB_DIS_X_SED_INS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_DIS_X_SED_INS_DISICODIGO_GENERATOR")
	@Column(name="disi_codigo")
	private long disiCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name="dis_fech_creacion")
	private Date disFechCreacion;

	@Column(name="disi_estado")
	private int disiEstado;

	//bi-directional many-to-one association to ClubDisciplina
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dis_codigo")
	private ClubDisciplina clubDisciplina;

	//bi-directional many-to-one association to ClubSedIn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sein_codigo")
	private ClubSedIn clubSedIn;

	//bi-directional many-to-one association to ClubHodiXDissedclub
	@OneToMany(mappedBy="clubDisXSedIn")
	private List<ClubHodiXDissedclub> clubHodiXDissedclubs;

	//bi-directional many-to-one association to ClubInsDisSedClub
	@OneToMany(mappedBy="clubDisXSedIn")
	private List<ClubInsDisSedClub> clubInsDisSedClubs;

	public ClubDisXSedIn() {
	}

	public long getDisiCodigo() {
		return this.disiCodigo;
	}

	public void setDisiCodigo(long disiCodigo) {
		this.disiCodigo = disiCodigo;
	}

	public Date getDisFechCreacion() {
		return this.disFechCreacion;
	}

	public void setDisFechCreacion(Date disFechCreacion) {
		this.disFechCreacion = disFechCreacion;
	}

	public int getDisiEstado() {
		return this.disiEstado;
	}

	public void setDisiEstado(int disiEstado) {
		this.disiEstado = disiEstado;
	}

	public ClubDisciplina getClubDisciplina() {
		return this.clubDisciplina;
	}

	public void setClubDisciplina(ClubDisciplina clubDisciplina) {
		this.clubDisciplina = clubDisciplina;
	}

	public ClubSedIn getClubSedIn() {
		return this.clubSedIn;
	}

	public void setClubSedIn(ClubSedIn clubSedIn) {
		this.clubSedIn = clubSedIn;
	}

	public List<ClubHodiXDissedclub> getClubHodiXDissedclubs() {
		return this.clubHodiXDissedclubs;
	}

	public void setClubHodiXDissedclubs(List<ClubHodiXDissedclub> clubHodiXDissedclubs) {
		this.clubHodiXDissedclubs = clubHodiXDissedclubs;
	}

	public ClubHodiXDissedclub addClubHodiXDissedclub(ClubHodiXDissedclub clubHodiXDissedclub) {
		getClubHodiXDissedclubs().add(clubHodiXDissedclub);
		clubHodiXDissedclub.setClubDisXSedIn(this);

		return clubHodiXDissedclub;
	}

	public ClubHodiXDissedclub removeClubHodiXDissedclub(ClubHodiXDissedclub clubHodiXDissedclub) {
		getClubHodiXDissedclubs().remove(clubHodiXDissedclub);
		clubHodiXDissedclub.setClubDisXSedIn(null);

		return clubHodiXDissedclub;
	}

	public List<ClubInsDisSedClub> getClubInsDisSedClubs() {
		return this.clubInsDisSedClubs;
	}

	public void setClubInsDisSedClubs(List<ClubInsDisSedClub> clubInsDisSedClubs) {
		this.clubInsDisSedClubs = clubInsDisSedClubs;
	}

	public ClubInsDisSedClub addClubInsDisSedClub(ClubInsDisSedClub clubInsDisSedClub) {
		getClubInsDisSedClubs().add(clubInsDisSedClub);
		clubInsDisSedClub.setClubDisXSedIn(this);

		return clubInsDisSedClub;
	}

	public ClubInsDisSedClub removeClubInsDisSedClub(ClubInsDisSedClub clubInsDisSedClub) {
		getClubInsDisSedClubs().remove(clubInsDisSedClub);
		clubInsDisSedClub.setClubDisXSedIn(null);

		return clubInsDisSedClub;
	}

}