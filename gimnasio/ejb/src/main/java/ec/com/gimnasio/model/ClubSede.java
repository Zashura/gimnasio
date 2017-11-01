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
 * The persistent class for the club_sede database table.
 * 
 */
@Entity
@Table(name="club_sede")
@NamedQuery(name="ClubSede.findAll", query="SELECT c FROM ClubSede c")
public class ClubSede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_SEDE_SEDCODIGO_GENERATOR", sequenceName="SEQ_CLUB_SEDE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_SEDE_SEDCODIGO_GENERATOR")
	@Column(name="sed_codigo")
	private long sedCodigo;

	@Column(name="sed_descripcion")
	private String sedDescripcion;

	@Column(name="sed_estado")
	private int sedEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="sed_fec_creacion")
	private Date sedFecCreacion;

	@Column(name="sed_nemonico")
	private String sedNemonico;

	//bi-directional many-to-one association to ClubSedIn
	@OneToMany(mappedBy="clubSede")
	private List<ClubSedIn> clubSedIns;

	public ClubSede() {
	}

	public long getSedCodigo() {
		return this.sedCodigo;
	}

	public void setSedCodigo(long sedCodigo) {
		this.sedCodigo = sedCodigo;
	}

	public String getSedDescripcion() {
		return this.sedDescripcion;
	}

	public void setSedDescripcion(String sedDescripcion) {
		this.sedDescripcion = sedDescripcion;
	}

	public int getSedEstado() {
		return this.sedEstado;
	}

	public void setSedEstado(int sedEstado) {
		this.sedEstado = sedEstado;
	}

	public Date getSedFecCreacion() {
		return this.sedFecCreacion;
	}

	public void setSedFecCreacion(Date sedFecCreacion) {
		this.sedFecCreacion = sedFecCreacion;
	}

	public String getSedNemonico() {
		return this.sedNemonico;
	}

	public void setSedNemonico(String sedNemonico) {
		this.sedNemonico = sedNemonico;
	}

	public List<ClubSedIn> getClubSedIns() {
		return this.clubSedIns;
	}

	public void setClubSedIns(List<ClubSedIn> clubSedIns) {
		this.clubSedIns = clubSedIns;
	}

	public ClubSedIn addClubSedIn(ClubSedIn clubSedIn) {
		getClubSedIns().add(clubSedIn);
		clubSedIn.setClubSede(this);

		return clubSedIn;
	}

	public ClubSedIn removeClubSedIn(ClubSedIn clubSedIn) {
		getClubSedIns().remove(clubSedIn);
		clubSedIn.setClubSede(null);

		return clubSedIn;
	}

}