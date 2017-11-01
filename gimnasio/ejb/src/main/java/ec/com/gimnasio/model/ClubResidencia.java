package ec.com.gimnasio.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the club_residencia database table.
 * 
 */
@Entity
@Table(name="club_residencia")
@NamedQuery(name="ClubResidencia.findAll", query="SELECT c FROM ClubResidencia c")
public class ClubResidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_RESIDENCIA_RESCODIGO_GENERATOR", sequenceName="SEQ_CLUB_RESIDENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_RESIDENCIA_RESCODIGO_GENERATOR")
	@Column(name="res_codigo")
	private long resCodigo;

	@Column(name="res_calle_1")
	private String resCalle1;

	@Column(name="res_calle_2")
	private String resCalle2;

	@Column(name="res_estado")
	private BigDecimal resEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="res_fec_creacion")
	private Date resFecCreacion;

	@Column(name="res_numero")
	private String resNumero;

	//bi-directional many-to-one association to ClubParroquia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="par_codigo")
	private ClubParroquia clubParroquia;

	//bi-directional many-to-one association to ClubPersona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="per_codigo")
	private ClubPersona clubPersona;

	public ClubResidencia() {
	}

	public long getResCodigo() {
		return this.resCodigo;
	}

	public void setResCodigo(long resCodigo) {
		this.resCodigo = resCodigo;
	}

	public String getResCalle1() {
		return this.resCalle1;
	}

	public void setResCalle1(String resCalle1) {
		this.resCalle1 = resCalle1;
	}

	public String getResCalle2() {
		return this.resCalle2;
	}

	public void setResCalle2(String resCalle2) {
		this.resCalle2 = resCalle2;
	}

	public BigDecimal getResEstado() {
		return this.resEstado;
	}

	public void setResEstado(BigDecimal resEstado) {
		this.resEstado = resEstado;
	}

	public Date getResFecCreacion() {
		return this.resFecCreacion;
	}

	public void setResFecCreacion(Date resFecCreacion) {
		this.resFecCreacion = resFecCreacion;
	}

	public String getResNumero() {
		return this.resNumero;
	}

	public void setResNumero(String resNumero) {
		this.resNumero = resNumero;
	}

	public ClubParroquia getClubParroquia() {
		return this.clubParroquia;
	}

	public void setClubParroquia(ClubParroquia clubParroquia) {
		this.clubParroquia = clubParroquia;
	}

	public ClubPersona getClubPersona() {
		return this.clubPersona;
	}

	public void setClubPersona(ClubPersona clubPersona) {
		this.clubPersona = clubPersona;
	}

}