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
 * The persistent class for the club_grado database table.
 * 
 */
@Entity
@Table(name="club_grado")
@NamedQuery(name="ClubGrado.findAll", query="SELECT c FROM ClubGrado c")
public class ClubGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_GRADO_GRACODIGO_GENERATOR", sequenceName="SEQ_CLUB_GRADO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_GRADO_GRACODIGO_GENERATOR")
	@Column(name="gra_codigo")
	private long graCodigo;

	@Column(name="gra_descripcion")
	private String graDescripcion;

	@Column(name="gra_estado")
	private int graEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="gra_fec_creacion")
	private Date graFecCreacion;

	//bi-directional many-to-one association to ClubCinXGra
	@OneToMany(mappedBy="clubGrado")
	private List<ClubCinXGra> clubCinXGras;

	public ClubGrado() {
	}

	public long getGraCodigo() {
		return this.graCodigo;
	}

	public void setGraCodigo(long graCodigo) {
		this.graCodigo = graCodigo;
	}

	public String getGraDescripcion() {
		return this.graDescripcion;
	}

	public void setGraDescripcion(String graDescripcion) {
		this.graDescripcion = graDescripcion;
	}

	public int getGraEstado() {
		return this.graEstado;
	}

	public void setGraEstado(int graEstado) {
		this.graEstado = graEstado;
	}

	public Date getGraFecCreacion() {
		return this.graFecCreacion;
	}

	public void setGraFecCreacion(Date graFecCreacion) {
		this.graFecCreacion = graFecCreacion;
	}

	public List<ClubCinXGra> getClubCinXGras() {
		return this.clubCinXGras;
	}

	public void setClubCinXGras(List<ClubCinXGra> clubCinXGras) {
		this.clubCinXGras = clubCinXGras;
	}

	public ClubCinXGra addClubCinXGra(ClubCinXGra clubCinXGra) {
		getClubCinXGras().add(clubCinXGra);
		clubCinXGra.setClubGrado(this);

		return clubCinXGra;
	}

	public ClubCinXGra removeClubCinXGra(ClubCinXGra clubCinXGra) {
		getClubCinXGras().remove(clubCinXGra);
		clubCinXGra.setClubGrado(null);

		return clubCinXGra;
	}

}