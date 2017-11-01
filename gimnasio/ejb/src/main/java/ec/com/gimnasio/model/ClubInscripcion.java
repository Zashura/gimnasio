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
 * The persistent class for the club_inscripcion database table.
 * 
 */
@Entity
@Table(name="club_inscripcion")
@NamedQuery(name="ClubInscripcion.findAll", query="SELECT c FROM ClubInscripcion c")
public class ClubInscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_INSCRIPCION_INSCODIGO_GENERATOR", sequenceName="SEQ_CLUB_INSCRIPCION",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_INSCRIPCION_INSCODIGO_GENERATOR")
	@Column(name="ins_codigo")
	private long insCodigo;

	@Column(name="ins_estado")
	private int insEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="ins_fec_creacion")
	private Date insFecCreacion;

	//bi-directional many-to-one association to ClubInsDisSedClub
	@OneToMany(mappedBy="clubInscripcion")
	private List<ClubInsDisSedClub> clubInsDisSedClubs;

	//bi-directional many-to-one association to ClubPersona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="per_codigo")
	private ClubPersona clubPersona;

	public ClubInscripcion() {
	}

	public long getInsCodigo() {
		return this.insCodigo;
	}

	public void setInsCodigo(long insCodigo) {
		this.insCodigo = insCodigo;
	}

	public int getInsEstado() {
		return this.insEstado;
	}

	public void setInsEstado(int insEstado) {
		this.insEstado = insEstado;
	}

	public Date getInsFecCreacion() {
		return this.insFecCreacion;
	}

	public void setInsFecCreacion(Date insFecCreacion) {
		this.insFecCreacion = insFecCreacion;
	}

	public List<ClubInsDisSedClub> getClubInsDisSedClubs() {
		return this.clubInsDisSedClubs;
	}

	public void setClubInsDisSedClubs(List<ClubInsDisSedClub> clubInsDisSedClubs) {
		this.clubInsDisSedClubs = clubInsDisSedClubs;
	}

	public ClubInsDisSedClub addClubInsDisSedClub(ClubInsDisSedClub clubInsDisSedClub) {
		getClubInsDisSedClubs().add(clubInsDisSedClub);
		clubInsDisSedClub.setClubInscripcion(this);

		return clubInsDisSedClub;
	}

	public ClubInsDisSedClub removeClubInsDisSedClub(ClubInsDisSedClub clubInsDisSedClub) {
		getClubInsDisSedClubs().remove(clubInsDisSedClub);
		clubInsDisSedClub.setClubInscripcion(null);

		return clubInsDisSedClub;
	}

	public ClubPersona getClubPersona() {
		return this.clubPersona;
	}

	public void setClubPersona(ClubPersona clubPersona) {
		this.clubPersona = clubPersona;
	}

}