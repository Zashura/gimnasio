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
 * The persistent class for the club_persona database table.
 * 
 */
@Entity
@Table(name="club_persona")
@NamedQuery(name="ClubPersona.findAll", query="SELECT c FROM ClubPersona c")
public class ClubPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_PERSONA_PERCODIGO_GENERATOR", sequenceName="SEQ_CLUB_PERSONA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_PERSONA_PERCODIGO_GENERATOR")
	@Column(name="per_codigo")
	private long perCodigo;

	@Column(name="per_autorepresentado")
	private String perAutorepresentado;

	@Column(name="per_estado")
	private int perEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fec_nacimiento")
	private Date perFecNacimiento;

	@Column(name="per_foto")
	private String perFoto;

	@Column(name="per_identificacion")
	private String perIdentificacion;

	@Column(name="per_nombres_")
	private String perNombres;

	//bi-directional many-to-one association to ClubInscripcion
	@OneToMany(mappedBy="clubPersona")
	private List<ClubInscripcion> clubInscripcions;

	//bi-directional many-to-one association to ClubRepresentante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rep_codigo")
	private ClubRepresentante clubRepresentante;

	//bi-directional many-to-one association to ClubSexo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sex_codigo")
	private ClubSexo clubSexo;

	//bi-directional many-to-one association to ClubTipIde
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tiin_codigo")
	private ClubTipIde clubTipIde;

	//bi-directional many-to-one association to ClubResidencia
	@OneToMany(mappedBy="clubPersona")
	private List<ClubResidencia> clubResidencias;

	//bi-directional many-to-one association to ClubTicoPer
	@OneToMany(mappedBy="clubPersona")
	private List<ClubTicoPer> clubTicoPers;

	public ClubPersona() {
	}

	public long getPerCodigo() {
		return this.perCodigo;
	}

	public void setPerCodigo(long perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerAutorepresentado() {
		return this.perAutorepresentado;
	}

	public void setPerAutorepresentado(String perAutorepresentado) {
		this.perAutorepresentado = perAutorepresentado;
	}

	public int getPerEstado() {
		return this.perEstado;
	}

	public void setPerEstado(int perEstado) {
		this.perEstado = perEstado;
	}

	public Date getPerFecNacimiento() {
		return this.perFecNacimiento;
	}

	public void setPerFecNacimiento(Date perFecNacimiento) {
		this.perFecNacimiento = perFecNacimiento;
	}

	public String getPerFoto() {
		return this.perFoto;
	}

	public void setPerFoto(String perFoto) {
		this.perFoto = perFoto;
	}

	public String getPerIdentificacion() {
		return this.perIdentificacion;
	}

	public void setPerIdentificacion(String perIdentificacion) {
		this.perIdentificacion = perIdentificacion;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public List<ClubInscripcion> getClubInscripcions() {
		return this.clubInscripcions;
	}

	public void setClubInscripcions(List<ClubInscripcion> clubInscripcions) {
		this.clubInscripcions = clubInscripcions;
	}

	public ClubInscripcion addClubInscripcion(ClubInscripcion clubInscripcion) {
		getClubInscripcions().add(clubInscripcion);
		clubInscripcion.setClubPersona(this);

		return clubInscripcion;
	}

	public ClubInscripcion removeClubInscripcion(ClubInscripcion clubInscripcion) {
		getClubInscripcions().remove(clubInscripcion);
		clubInscripcion.setClubPersona(null);

		return clubInscripcion;
	}

	public ClubRepresentante getClubRepresentante() {
		return this.clubRepresentante;
	}

	public void setClubRepresentante(ClubRepresentante clubRepresentante) {
		this.clubRepresentante = clubRepresentante;
	}

	public ClubSexo getClubSexo() {
		return this.clubSexo;
	}

	public void setClubSexo(ClubSexo clubSexo) {
		this.clubSexo = clubSexo;
	}

	public ClubTipIde getClubTipIde() {
		return this.clubTipIde;
	}

	public void setClubTipIde(ClubTipIde clubTipIde) {
		this.clubTipIde = clubTipIde;
	}

	public List<ClubResidencia> getClubResidencias() {
		return this.clubResidencias;
	}

	public void setClubResidencias(List<ClubResidencia> clubResidencias) {
		this.clubResidencias = clubResidencias;
	}

	public ClubResidencia addClubResidencia(ClubResidencia clubResidencia) {
		getClubResidencias().add(clubResidencia);
		clubResidencia.setClubPersona(this);

		return clubResidencia;
	}

	public ClubResidencia removeClubResidencia(ClubResidencia clubResidencia) {
		getClubResidencias().remove(clubResidencia);
		clubResidencia.setClubPersona(null);

		return clubResidencia;
	}

	public List<ClubTicoPer> getClubTicoPers() {
		return this.clubTicoPers;
	}

	public void setClubTicoPers(List<ClubTicoPer> clubTicoPers) {
		this.clubTicoPers = clubTicoPers;
	}

	public ClubTicoPer addClubTicoPer(ClubTicoPer clubTicoPer) {
		getClubTicoPers().add(clubTicoPer);
		clubTicoPer.setClubPersona(this);

		return clubTicoPer;
	}

	public ClubTicoPer removeClubTicoPer(ClubTicoPer clubTicoPer) {
		getClubTicoPers().remove(clubTicoPer);
		clubTicoPer.setClubPersona(null);

		return clubTicoPer;
	}

}