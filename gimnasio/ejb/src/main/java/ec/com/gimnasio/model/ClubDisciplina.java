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
 * The persistent class for the club_disciplina database table.
 * 
 */
@Entity
@Table(name="club_disciplina")
@NamedQuery(name="ClubDisciplina.findAll", query="SELECT c FROM ClubDisciplina c")
public class ClubDisciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_DISCIPLINA_DISCODIGO_GENERATOR", sequenceName="SEQ_CLUB_DISCIPLINA", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_DISCIPLINA_DISCODIGO_GENERATOR")
	@Column(name="dis_codigo")
	private long disCodigo;

	@Column(name="dis_descripcion")
	private String disDescripcion;

	@Column(name="dis_estado")
	private int disEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="dis_fec_creacion")
	private Date disFecCreacion;

	//bi-directional many-to-one association to ClubCinDi
	@OneToMany(mappedBy="clubDisciplina")
	private List<ClubCinDi> clubCinDis;

	//bi-directional many-to-one association to ClubDisXSedIn
	@OneToMany(mappedBy="clubDisciplina")
	private List<ClubDisXSedIn> clubDisXSedIns;

	//bi-directional many-to-one association to ClubTipDi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tidi_codigo")
	private ClubTipDi clubTipDi;

	public ClubDisciplina() {
	}

	public long getDisCodigo() {
		return this.disCodigo;
	}

	public void setDisCodigo(long disCodigo) {
		this.disCodigo = disCodigo;
	}

	public String getDisDescripcion() {
		return this.disDescripcion;
	}

	public void setDisDescripcion(String disDescripcion) {
		this.disDescripcion = disDescripcion;
	}

	public int getDisEstado() {
		return this.disEstado;
	}

	public void setDisEstado(int disEstado) {
		this.disEstado = disEstado;
	}

	public Date getDisFecCreacion() {
		return this.disFecCreacion;
	}

	public void setDisFecCreacion(Date disFecCreacion) {
		this.disFecCreacion = disFecCreacion;
	}

	public List<ClubCinDi> getClubCinDis() {
		return this.clubCinDis;
	}

	public void setClubCinDis(List<ClubCinDi> clubCinDis) {
		this.clubCinDis = clubCinDis;
	}

	public ClubCinDi addClubCinDi(ClubCinDi clubCinDi) {
		getClubCinDis().add(clubCinDi);
		clubCinDi.setClubDisciplina(this);

		return clubCinDi;
	}

	public ClubCinDi removeClubCinDi(ClubCinDi clubCinDi) {
		getClubCinDis().remove(clubCinDi);
		clubCinDi.setClubDisciplina(null);

		return clubCinDi;
	}

	public List<ClubDisXSedIn> getClubDisXSedIns() {
		return this.clubDisXSedIns;
	}

	public void setClubDisXSedIns(List<ClubDisXSedIn> clubDisXSedIns) {
		this.clubDisXSedIns = clubDisXSedIns;
	}

	public ClubDisXSedIn addClubDisXSedIn(ClubDisXSedIn clubDisXSedIn) {
		getClubDisXSedIns().add(clubDisXSedIn);
		clubDisXSedIn.setClubDisciplina(this);

		return clubDisXSedIn;
	}

	public ClubDisXSedIn removeClubDisXSedIn(ClubDisXSedIn clubDisXSedIn) {
		getClubDisXSedIns().remove(clubDisXSedIn);
		clubDisXSedIn.setClubDisciplina(null);

		return clubDisXSedIn;
	}

	public ClubTipDi getClubTipDi() {
		return this.clubTipDi;
	}

	public void setClubTipDi(ClubTipDi clubTipDi) {
		this.clubTipDi = clubTipDi;
	}

}