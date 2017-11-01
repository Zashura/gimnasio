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
 * The persistent class for the club_tip_dis database table.
 * 
 */
@Entity
@Table(name="club_tip_dis")
@NamedQuery(name="ClubTipDi.findAll", query="SELECT c FROM ClubTipDi c")
public class ClubTipDi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_TIP_DIS_TIDICODIGO_GENERATOR", sequenceName="SEQ_CLUB_TIP_DIS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_TIP_DIS_TIDICODIGO_GENERATOR")
	@Column(name="tidi_codigo")
	private long tidiCodigo;

	@Column(name="tidi_descripcion")
	private String tidiDescripcion;

	@Column(name="tidi_estado")
	private int tidiEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="tidi_fec_creacion")
	private Date tidiFecCreacion;

	@Column(name="tidi_nemonico")
	private String tidiNemonico;

	//bi-directional many-to-one association to ClubDisciplina
	@OneToMany(mappedBy="clubTipDi")
	private List<ClubDisciplina> clubDisciplinas;

	public ClubTipDi() {
	}

	public long getTidiCodigo() {
		return this.tidiCodigo;
	}

	public void setTidiCodigo(long tidiCodigo) {
		this.tidiCodigo = tidiCodigo;
	}

	public String getTidiDescripcion() {
		return this.tidiDescripcion;
	}

	public void setTidiDescripcion(String tidiDescripcion) {
		this.tidiDescripcion = tidiDescripcion;
	}

	public int getTidiEstado() {
		return this.tidiEstado;
	}

	public void setTidiEstado(int tidiEstado) {
		this.tidiEstado = tidiEstado;
	}

	public Date getTidiFecCreacion() {
		return this.tidiFecCreacion;
	}

	public void setTidiFecCreacion(Date tidiFecCreacion) {
		this.tidiFecCreacion = tidiFecCreacion;
	}

	public String getTidiNemonico() {
		return this.tidiNemonico;
	}

	public void setTidiNemonico(String tidiNemonico) {
		this.tidiNemonico = tidiNemonico;
	}

	public List<ClubDisciplina> getClubDisciplinas() {
		return this.clubDisciplinas;
	}

	public void setClubDisciplinas(List<ClubDisciplina> clubDisciplinas) {
		this.clubDisciplinas = clubDisciplinas;
	}

	public ClubDisciplina addClubDisciplina(ClubDisciplina clubDisciplina) {
		getClubDisciplinas().add(clubDisciplina);
		clubDisciplina.setClubTipDi(this);

		return clubDisciplina;
	}

	public ClubDisciplina removeClubDisciplina(ClubDisciplina clubDisciplina) {
		getClubDisciplinas().remove(clubDisciplina);
		clubDisciplina.setClubTipDi(null);

		return clubDisciplina;
	}

}