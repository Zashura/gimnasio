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
 * The persistent class for the club_cin_x_gra database table.
 * 
 */
@Entity
@Table(name="club_cin_x_gra")
@NamedQuery(name="ClubCinXGra.findAll", query="SELECT c FROM ClubCinXGra c")
public class ClubCinXGra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_CIN_X_GRA_CIGACODIGO_GENERATOR", sequenceName="SEQ_CLUB_CIN_X_GRA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_CIN_X_GRA_CIGACODIGO_GENERATOR")
	@Column(name="ciga_codigo")
	private long cigaCodigo;

	@Column(name="ciga_estado")
	private int cigaEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="ciga_fec_creacion")
	private Date cigaFecCreacion;

	//bi-directional many-to-one association to ClubCinturon
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cin_codigo")
	private ClubCinturon clubCinturon;

	//bi-directional many-to-one association to ClubGrado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gra_codigo")
	private ClubGrado clubGrado;

	public ClubCinXGra() {
	}

	public long getCigaCodigo() {
		return this.cigaCodigo;
	}

	public void setCigaCodigo(long cigaCodigo) {
		this.cigaCodigo = cigaCodigo;
	}

	public int getCigaEstado() {
		return this.cigaEstado;
	}

	public void setCigaEstado(int cigaEstado) {
		this.cigaEstado = cigaEstado;
	}

	public Date getCigaFecCreacion() {
		return this.cigaFecCreacion;
	}

	public void setCigaFecCreacion(Date cigaFecCreacion) {
		this.cigaFecCreacion = cigaFecCreacion;
	}

	public ClubCinturon getClubCinturon() {
		return this.clubCinturon;
	}

	public void setClubCinturon(ClubCinturon clubCinturon) {
		this.clubCinturon = clubCinturon;
	}

	public ClubGrado getClubGrado() {
		return this.clubGrado;
	}

	public void setClubGrado(ClubGrado clubGrado) {
		this.clubGrado = clubGrado;
	}

}