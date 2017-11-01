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
 * The persistent class for the club_cinturon database table.
 * 
 */
@Entity
@Table(name="club_cinturon")
@NamedQuery(name="ClubCinturon.findAll", query="SELECT c FROM ClubCinturon c")
public class ClubCinturon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_CINTURON_CINCODIGO_GENERATOR", sequenceName="SEQ_CLUB_CINTURON",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_CINTURON_CINCODIGO_GENERATOR")
	@Column(name="cin_codigo")
	private long cinCodigo;

	@Column(name="cin_descripcion")
	private String cinDescripcion;

	@Column(name="cin_estado")
	private int cinEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="cin_fec_creacion")
	private Date cinFecCreacion;

	//bi-directional many-to-one association to ClubCinDi
	@OneToMany(mappedBy="clubCinturon")
	private List<ClubCinDi> clubCinDis;

	//bi-directional many-to-one association to ClubCinXGra
	@OneToMany(mappedBy="clubCinturon")
	private List<ClubCinXGra> clubCinXGras;

	public ClubCinturon() {
	}

	public long getCinCodigo() {
		return this.cinCodigo;
	}

	public void setCinCodigo(long cinCodigo) {
		this.cinCodigo = cinCodigo;
	}

	public String getCinDescripcion() {
		return this.cinDescripcion;
	}

	public void setCinDescripcion(String cinDescripcion) {
		this.cinDescripcion = cinDescripcion;
	}

	public int getCinEstado() {
		return this.cinEstado;
	}

	public void setCinEstado(int cinEstado) {
		this.cinEstado = cinEstado;
	}

	public Date getCinFecCreacion() {
		return this.cinFecCreacion;
	}

	public void setCinFecCreacion(Date cinFecCreacion) {
		this.cinFecCreacion = cinFecCreacion;
	}

	public List<ClubCinDi> getClubCinDis() {
		return this.clubCinDis;
	}

	public void setClubCinDis(List<ClubCinDi> clubCinDis) {
		this.clubCinDis = clubCinDis;
	}

	public ClubCinDi addClubCinDi(ClubCinDi clubCinDi) {
		getClubCinDis().add(clubCinDi);
		clubCinDi.setClubCinturon(this);

		return clubCinDi;
	}

	public ClubCinDi removeClubCinDi(ClubCinDi clubCinDi) {
		getClubCinDis().remove(clubCinDi);
		clubCinDi.setClubCinturon(null);

		return clubCinDi;
	}

	public List<ClubCinXGra> getClubCinXGras() {
		return this.clubCinXGras;
	}

	public void setClubCinXGras(List<ClubCinXGra> clubCinXGras) {
		this.clubCinXGras = clubCinXGras;
	}

	public ClubCinXGra addClubCinXGra(ClubCinXGra clubCinXGra) {
		getClubCinXGras().add(clubCinXGra);
		clubCinXGra.setClubCinturon(this);

		return clubCinXGra;
	}

	public ClubCinXGra removeClubCinXGra(ClubCinXGra clubCinXGra) {
		getClubCinXGras().remove(clubCinXGra);
		clubCinXGra.setClubCinturon(null);

		return clubCinXGra;
	}

}