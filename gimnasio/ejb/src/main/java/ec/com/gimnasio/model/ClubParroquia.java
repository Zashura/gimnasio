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
 * The persistent class for the club_parroquia database table.
 * 
 */
@Entity
@Table(name="club_parroquia")
@NamedQuery(name="ClubParroquia.findAll", query="SELECT c FROM ClubParroquia c")
public class ClubParroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_PARROQUIA_PARCODIGO_GENERATOR", sequenceName="SEQ_CLUB_PARROQUIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_PARROQUIA_PARCODIGO_GENERATOR")
	@Column(name="par_codigo")
	private long parCodigo;

	@Column(name="par_descripcion")
	private String parDescripcion;

	@Column(name="par_dpa")
	private String parDpa;

	@Column(name="par_estado")
	private int parEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="par_fec_creacion")
	private Date parFecCreacion;

	//bi-directional many-to-one association to CluParXSedIn
	@OneToMany(mappedBy="clubParroquia")
	private List<CluParXSedIn> cluParXSedIns;

	//bi-directional many-to-one association to ClubCanton
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="can_codigo")
	private ClubCanton clubCanton;

	//bi-directional many-to-one association to ClubResidencia
	@OneToMany(mappedBy="clubParroquia")
	private List<ClubResidencia> clubResidencias;

	public ClubParroquia() {
	}

	public long getParCodigo() {
		return this.parCodigo;
	}

	public void setParCodigo(long parCodigo) {
		this.parCodigo = parCodigo;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public String getParDpa() {
		return this.parDpa;
	}

	public void setParDpa(String parDpa) {
		this.parDpa = parDpa;
	}

	public int getParEstado() {
		return this.parEstado;
	}

	public void setParEstado(int parEstado) {
		this.parEstado = parEstado;
	}

	public Date getParFecCreacion() {
		return this.parFecCreacion;
	}

	public void setParFecCreacion(Date parFecCreacion) {
		this.parFecCreacion = parFecCreacion;
	}

	public List<CluParXSedIn> getCluParXSedIns() {
		return this.cluParXSedIns;
	}

	public void setCluParXSedIns(List<CluParXSedIn> cluParXSedIns) {
		this.cluParXSedIns = cluParXSedIns;
	}

	public CluParXSedIn addCluParXSedIn(CluParXSedIn cluParXSedIn) {
		getCluParXSedIns().add(cluParXSedIn);
		cluParXSedIn.setClubParroquia(this);

		return cluParXSedIn;
	}

	public CluParXSedIn removeCluParXSedIn(CluParXSedIn cluParXSedIn) {
		getCluParXSedIns().remove(cluParXSedIn);
		cluParXSedIn.setClubParroquia(null);

		return cluParXSedIn;
	}

	public ClubCanton getClubCanton() {
		return this.clubCanton;
	}

	public void setClubCanton(ClubCanton clubCanton) {
		this.clubCanton = clubCanton;
	}

	public List<ClubResidencia> getClubResidencias() {
		return this.clubResidencias;
	}

	public void setClubResidencias(List<ClubResidencia> clubResidencias) {
		this.clubResidencias = clubResidencias;
	}

	public ClubResidencia addClubResidencia(ClubResidencia clubResidencia) {
		getClubResidencias().add(clubResidencia);
		clubResidencia.setClubParroquia(this);

		return clubResidencia;
	}

	public ClubResidencia removeClubResidencia(ClubResidencia clubResidencia) {
		getClubResidencias().remove(clubResidencia);
		clubResidencia.setClubParroquia(null);

		return clubResidencia;
	}

}