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
 * The persistent class for the club_ins_dis_sed_club database table.
 * 
 */
@Entity
@Table(name="club_ins_dis_sed_club")
@NamedQuery(name="ClubInsDisSedClub.findAll", query="SELECT c FROM ClubInsDisSedClub c")
public class ClubInsDisSedClub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_INS_DIS_SED_CLUB_IDSICODIGO_GENERATOR", sequenceName="SEQ_CLUB_INS_DIS_SED_CLUB",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_INS_DIS_SED_CLUB_IDSICODIGO_GENERATOR")
	@Column(name="idsi_codigo")
	private long idsiCodigo;

	@Column(name="idsi_estado")
	private int idsiEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="idsi_fec_creacion")
	private Date idsiFecCreacion;

	//bi-directional many-to-one association to ClubDisXSedIn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="disi_codigo")
	private ClubDisXSedIn clubDisXSedIn;

	//bi-directional many-to-one association to ClubInscripcion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ins_codigo")
	private ClubInscripcion clubInscripcion;
	
	@Column(name="hodi_codigo")
	private long hodiCodigo;

	public ClubInsDisSedClub() {
	}

	public long getIdsiCodigo() {
		return this.idsiCodigo;
	}

	public void setIdsiCodigo(long idsiCodigo) {
		this.idsiCodigo = idsiCodigo;
	}

	public int getIdsiEstado() {
		return this.idsiEstado;
	}

	public void setIdsiEstado(int idsiEstado) {
		this.idsiEstado = idsiEstado;
	}

	public Date getIdsiFecCreacion() {
		return this.idsiFecCreacion;
	}

	public void setIdsiFecCreacion(Date idsiFecCreacion) {
		this.idsiFecCreacion = idsiFecCreacion;
	}

	public ClubDisXSedIn getClubDisXSedIn() {
		return this.clubDisXSedIn;
	}

	public void setClubDisXSedIn(ClubDisXSedIn clubDisXSedIn) {
		this.clubDisXSedIn = clubDisXSedIn;
	}

	public ClubInscripcion getClubInscripcion() {
		return this.clubInscripcion;
	}

	public void setClubInscripcion(ClubInscripcion clubInscripcion) {
		this.clubInscripcion = clubInscripcion;
	}

	public long getHodiCodigo() {
		return hodiCodigo;
	}

	public void setHodiCodigo(long hodiCodigo) {
		this.hodiCodigo = hodiCodigo;
	}

}