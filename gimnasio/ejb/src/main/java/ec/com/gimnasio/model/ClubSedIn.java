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
 * The persistent class for the club_sed_ins database table.
 * 
 */
@Entity
@Table(name="club_sed_ins")
@NamedQuery(name="ClubSedIn.findAll", query="SELECT c FROM ClubSedIn c")
public class ClubSedIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_SED_INS_SEINCODIGO_GENERATOR", sequenceName="SEQ_CLUB_SED_INS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_SED_INS_SEINCODIGO_GENERATOR")
	@Column(name="sein_codigo")
	private long seinCodigo;

	@Column(name="sein_estado")
	private int seinEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="sein_fec_creacion")
	private Date seinFecCreacion;

	@Column(name="sein_nemonico")
	private String seinNemonico;

	//bi-directional many-to-one association to CluParXSedIn
	@OneToMany(mappedBy="clubSedIn")
	private List<CluParXSedIn> cluParXSedIns;

	//bi-directional many-to-one association to ClubDisXSedIn
	@OneToMany(mappedBy="clubSedIn")
	private List<ClubDisXSedIn> clubDisXSedIns;

	//bi-directional many-to-one association to ClubInstitucion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clu_codigo")
	private ClubInstitucion clubInstitucion;

	//bi-directional many-to-one association to ClubSede
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sed_codigo")
	private ClubSede clubSede;

	//bi-directional many-to-one association to ClubTicoXSede
	@OneToMany(mappedBy="clubSedIn")
	private List<ClubTicoXSede> clubTicoXSedes;

	public ClubSedIn() {
	}

	public long getSeinCodigo() {
		return this.seinCodigo;
	}

	public void setSeinCodigo(long seinCodigo) {
		this.seinCodigo = seinCodigo;
	}

	public int getSeinEstado() {
		return this.seinEstado;
	}

	public void setSeinEstado(int seinEstado) {
		this.seinEstado = seinEstado;
	}

	public Date getSeinFecCreacion() {
		return this.seinFecCreacion;
	}

	public void setSeinFecCreacion(Date seinFecCreacion) {
		this.seinFecCreacion = seinFecCreacion;
	}

	public String getSeinNemonico() {
		return this.seinNemonico;
	}

	public void setSeinNemonico(String seinNemonico) {
		this.seinNemonico = seinNemonico;
	}

	public List<CluParXSedIn> getCluParXSedIns() {
		return this.cluParXSedIns;
	}

	public void setCluParXSedIns(List<CluParXSedIn> cluParXSedIns) {
		this.cluParXSedIns = cluParXSedIns;
	}

	public CluParXSedIn addCluParXSedIn(CluParXSedIn cluParXSedIn) {
		getCluParXSedIns().add(cluParXSedIn);
		cluParXSedIn.setClubSedIn(this);

		return cluParXSedIn;
	}

	public CluParXSedIn removeCluParXSedIn(CluParXSedIn cluParXSedIn) {
		getCluParXSedIns().remove(cluParXSedIn);
		cluParXSedIn.setClubSedIn(null);

		return cluParXSedIn;
	}

	public List<ClubDisXSedIn> getClubDisXSedIns() {
		return this.clubDisXSedIns;
	}

	public void setClubDisXSedIns(List<ClubDisXSedIn> clubDisXSedIns) {
		this.clubDisXSedIns = clubDisXSedIns;
	}

	public ClubDisXSedIn addClubDisXSedIn(ClubDisXSedIn clubDisXSedIn) {
		getClubDisXSedIns().add(clubDisXSedIn);
		clubDisXSedIn.setClubSedIn(this);

		return clubDisXSedIn;
	}

	public ClubDisXSedIn removeClubDisXSedIn(ClubDisXSedIn clubDisXSedIn) {
		getClubDisXSedIns().remove(clubDisXSedIn);
		clubDisXSedIn.setClubSedIn(null);

		return clubDisXSedIn;
	}

	public ClubInstitucion getClubInstitucion() {
		return this.clubInstitucion;
	}

	public void setClubInstitucion(ClubInstitucion clubInstitucion) {
		this.clubInstitucion = clubInstitucion;
	}

	public ClubSede getClubSede() {
		return this.clubSede;
	}

	public void setClubSede(ClubSede clubSede) {
		this.clubSede = clubSede;
	}

	public List<ClubTicoXSede> getClubTicoXSedes() {
		return this.clubTicoXSedes;
	}

	public void setClubTicoXSedes(List<ClubTicoXSede> clubTicoXSedes) {
		this.clubTicoXSedes = clubTicoXSedes;
	}

	public ClubTicoXSede addClubTicoXSede(ClubTicoXSede clubTicoXSede) {
		getClubTicoXSedes().add(clubTicoXSede);
		clubTicoXSede.setClubSedIn(this);

		return clubTicoXSede;
	}

	public ClubTicoXSede removeClubTicoXSede(ClubTicoXSede clubTicoXSede) {
		getClubTicoXSedes().remove(clubTicoXSede);
		clubTicoXSede.setClubSedIn(null);

		return clubTicoXSede;
	}

}