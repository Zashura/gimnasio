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
 * The persistent class for the club_canton database table.
 * 
 */
@Entity
@Table(name="club_canton")
@NamedQuery(name="ClubCanton.findAll", query="SELECT c FROM ClubCanton c")
public class ClubCanton implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_CANTON_CANCODIGO_GENERATOR", sequenceName="SEQ_CLUB_CANTON")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_CANTON_CANCODIGO_GENERATOR")
	@Column(name="can_codigo")
	private long canCodigo;

	@Column(name="can_descripcion")
	private String canDescripcion;

	@Column(name="can_dpa")
	private String canDpa;

	@Column(name="can_estado")
	private int canEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="can_fec_creacion")
	private Date canFecCreacion;

	//bi-directional many-to-one association to ClubProvincia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pro_codigo")
	private ClubProvincia clubProvincia;

	//bi-directional many-to-one association to ClubParroquia
	@OneToMany(mappedBy="clubCanton")
	private List<ClubParroquia> clubParroquias;

	public ClubCanton() {
	}

	public long getCanCodigo() {
		return this.canCodigo;
	}

	public void setCanCodigo(long canCodigo) {
		this.canCodigo = canCodigo;
	}

	public String getCanDescripcion() {
		return this.canDescripcion;
	}

	public void setCanDescripcion(String canDescripcion) {
		this.canDescripcion = canDescripcion;
	}

	public String getCanDpa() {
		return this.canDpa;
	}

	public void setCanDpa(String canDpa) {
		this.canDpa = canDpa;
	}

	public int getCanEstado() {
		return this.canEstado;
	}

	public void setCanEstado(int canEstado) {
		this.canEstado = canEstado;
	}

	public Date getCanFecCreacion() {
		return this.canFecCreacion;
	}

	public void setCanFecCreacion(Date canFecCreacion) {
		this.canFecCreacion = canFecCreacion;
	}

	public ClubProvincia getClubProvincia() {
		return this.clubProvincia;
	}

	public void setClubProvincia(ClubProvincia clubProvincia) {
		this.clubProvincia = clubProvincia;
	}

	public List<ClubParroquia> getClubParroquias() {
		return this.clubParroquias;
	}

	public void setClubParroquias(List<ClubParroquia> clubParroquias) {
		this.clubParroquias = clubParroquias;
	}

	public ClubParroquia addClubParroquia(ClubParroquia clubParroquia) {
		getClubParroquias().add(clubParroquia);
		clubParroquia.setClubCanton(this);

		return clubParroquia;
	}

	public ClubParroquia removeClubParroquia(ClubParroquia clubParroquia) {
		getClubParroquias().remove(clubParroquia);
		clubParroquia.setClubCanton(null);

		return clubParroquia;
	}

}