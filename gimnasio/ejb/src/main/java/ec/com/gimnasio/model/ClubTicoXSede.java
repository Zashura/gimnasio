package ec.com.gimnasio.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the club_tico_x_sede database table.
 * 
 */
@Entity
@Table(name="club_tico_x_sede")
@NamedQuery(name="ClubTicoXSede.findAll", query="SELECT c FROM ClubTicoXSede c")
public class ClubTicoXSede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_TICO_X_SEDE_TICOSECODIGO_GENERATOR", sequenceName="SEQ_CLUB_TICO_X_SEDE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_TICO_X_SEDE_TICOSECODIGO_GENERATOR")
	@Column(name="ticose_codigo")
	private long ticoseCodigo;

	@Column(name="ticose_descripcion")
	private String ticoseDescripcion;

	@Column(name="ticose_estado")
	private BigDecimal ticoseEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="ticose_fec_creacion")
	private Date ticoseFecCreacion;

	//bi-directional many-to-one association to ClubSedIn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sein_codigo")
	private ClubSedIn clubSedIn;

	//bi-directional many-to-one association to ClubTipCon
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tico_codigo")
	private ClubTipCon clubTipCon;

	public ClubTicoXSede() {
	}

	public long getTicoseCodigo() {
		return this.ticoseCodigo;
	}

	public void setTicoseCodigo(long ticoseCodigo) {
		this.ticoseCodigo = ticoseCodigo;
	}

	public String getTicoseDescripcion() {
		return this.ticoseDescripcion;
	}

	public void setTicoseDescripcion(String ticoseDescripcion) {
		this.ticoseDescripcion = ticoseDescripcion;
	}

	public BigDecimal getTicoseEstado() {
		return this.ticoseEstado;
	}

	public void setTicoseEstado(BigDecimal ticoseEstado) {
		this.ticoseEstado = ticoseEstado;
	}

	public Date getTicoseFecCreacion() {
		return this.ticoseFecCreacion;
	}

	public void setTicoseFecCreacion(Date ticoseFecCreacion) {
		this.ticoseFecCreacion = ticoseFecCreacion;
	}

	public ClubSedIn getClubSedIn() {
		return this.clubSedIn;
	}

	public void setClubSedIn(ClubSedIn clubSedIn) {
		this.clubSedIn = clubSedIn;
	}

	public ClubTipCon getClubTipCon() {
		return this.clubTipCon;
	}

	public void setClubTipCon(ClubTipCon clubTipCon) {
		this.clubTipCon = clubTipCon;
	}

}