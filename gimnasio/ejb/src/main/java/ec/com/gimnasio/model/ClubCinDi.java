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
 * The persistent class for the club_cin_dis database table.
 * 
 */
@Entity
@Table(name="club_cin_dis")
@NamedQuery(name="ClubCinDi.findAll", query="SELECT c FROM ClubCinDi c")
public class ClubCinDi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_CIN_DIS_CIDICODIGO_GENERATOR", sequenceName="SEQ_CLUB_CIN_DIS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_CIN_DIS_CIDICODIGO_GENERATOR")
	@Column(name="cidi_codigo")
	private long cidiCodigo;

	@Column(name="cidi_estado")
	private int cidiEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="cidi_fec_creacion")
	private Date cidiFecCreacion;

	//bi-directional many-to-one association to ClubCinturon
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cin_codigo")
	private ClubCinturon clubCinturon;

	//bi-directional many-to-one association to ClubDisciplina
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dis_codigo")
	private ClubDisciplina clubDisciplina;

	public ClubCinDi() {
	}

	public long getCidiCodigo() {
		return this.cidiCodigo;
	}

	public void setCidiCodigo(long cidiCodigo) {
		this.cidiCodigo = cidiCodigo;
	}

	public int getCidiEstado() {
		return this.cidiEstado;
	}

	public void setCidiEstado(int cidiEstado) {
		this.cidiEstado = cidiEstado;
	}

	public Date getCidiFecCreacion() {
		return this.cidiFecCreacion;
	}

	public void setCidiFecCreacion(Date cidiFecCreacion) {
		this.cidiFecCreacion = cidiFecCreacion;
	}

	public ClubCinturon getClubCinturon() {
		return this.clubCinturon;
	}

	public void setClubCinturon(ClubCinturon clubCinturon) {
		this.clubCinturon = clubCinturon;
	}

	public ClubDisciplina getClubDisciplina() {
		return this.clubDisciplina;
	}

	public void setClubDisciplina(ClubDisciplina clubDisciplina) {
		this.clubDisciplina = clubDisciplina;
	}

}