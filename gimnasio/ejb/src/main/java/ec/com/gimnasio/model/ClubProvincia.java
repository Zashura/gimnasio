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
 * The persistent class for the club_provincia database table.
 * 
 */
@Entity
@Table(name="club_provincia")
@NamedQuery(name="ClubProvincia.findAll", query="SELECT c FROM ClubProvincia c")
public class ClubProvincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_PROVINCIA_PROCODIGO_GENERATOR", sequenceName="SEQ_CLUB_PROVINCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_PROVINCIA_PROCODIGO_GENERATOR")
	@Column(name="pro_codigo")
	private long proCodigo;

	@Column(name="pro_descripcion")
	private String proDescripcion;

	@Column(name="pro_dpa")
	private String proDpa;

	@Column(name="pro_estado")
	private int proEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="pro_fec_creacion")
	private Date proFecCreacion;

	//bi-directional many-to-one association to ClubCanton
	@OneToMany(mappedBy="clubProvincia")
	private List<ClubCanton> clubCantons;

	public ClubProvincia() {
	}

	public long getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(long proCodigo) {
		this.proCodigo = proCodigo;
	}

	public String getProDescripcion() {
		return this.proDescripcion;
	}

	public void setProDescripcion(String proDescripcion) {
		this.proDescripcion = proDescripcion;
	}

	public String getProDpa() {
		return this.proDpa;
	}

	public void setProDpa(String proDpa) {
		this.proDpa = proDpa;
	}

	public int getProEstado() {
		return this.proEstado;
	}

	public void setProEstado(int proEstado) {
		this.proEstado = proEstado;
	}

	public Date getProFecCreacion() {
		return this.proFecCreacion;
	}

	public void setProFecCreacion(Date proFecCreacion) {
		this.proFecCreacion = proFecCreacion;
	}

	public List<ClubCanton> getClubCantons() {
		return this.clubCantons;
	}

	public void setClubCantons(List<ClubCanton> clubCantons) {
		this.clubCantons = clubCantons;
	}

	public ClubCanton addClubCanton(ClubCanton clubCanton) {
		getClubCantons().add(clubCanton);
		clubCanton.setClubProvincia(this);

		return clubCanton;
	}

	public ClubCanton removeClubCanton(ClubCanton clubCanton) {
		getClubCantons().remove(clubCanton);
		clubCanton.setClubProvincia(null);

		return clubCanton;
	}

}