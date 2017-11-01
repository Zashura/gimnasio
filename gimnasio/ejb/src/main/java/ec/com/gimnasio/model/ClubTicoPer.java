package ec.com.gimnasio.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the club_tico_per database table.
 * 
 */
@Entity
@Table(name="club_tico_per")
@NamedQuery(name="ClubTicoPer.findAll", query="SELECT c FROM ClubTicoPer c")
public class ClubTicoPer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_TICO_PER_TCPECODIGO_GENERATOR", sequenceName="SEQ_CLUB_TICO_PER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_TICO_PER_TCPECODIGO_GENERATOR")
	@Column(name="tcpe_codigo")
	private long tcpeCodigo;

	@Column(name="tcpe_descripcion")
	private String tcpeDescripcion;

	@Column(name="tcpe_estado")
	private BigDecimal tcpeEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="tcpe_fec_creacion")
	private Date tcpeFecCreacion;

	//bi-directional many-to-one association to ClubPersona
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="per_codigo")
	private ClubPersona clubPersona;

	//bi-directional many-to-one association to ClubTipCon
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tico_codigo")
	private ClubTipCon clubTipCon;

	public ClubTicoPer() {
	}

	public long getTcpeCodigo() {
		return this.tcpeCodigo;
	}

	public void setTcpeCodigo(long tcpeCodigo) {
		this.tcpeCodigo = tcpeCodigo;
	}

	public String getTcpeDescripcion() {
		return this.tcpeDescripcion;
	}

	public void setTcpeDescripcion(String tcpeDescripcion) {
		this.tcpeDescripcion = tcpeDescripcion;
	}

	public BigDecimal getTcpeEstado() {
		return this.tcpeEstado;
	}

	public void setTcpeEstado(BigDecimal tcpeEstado) {
		this.tcpeEstado = tcpeEstado;
	}

	public Date getTcpeFecCreacion() {
		return this.tcpeFecCreacion;
	}

	public void setTcpeFecCreacion(Date tcpeFecCreacion) {
		this.tcpeFecCreacion = tcpeFecCreacion;
	}

	public ClubPersona getClubPersona() {
		return this.clubPersona;
	}

	public void setClubPersona(ClubPersona clubPersona) {
		this.clubPersona = clubPersona;
	}

	public ClubTipCon getClubTipCon() {
		return this.clubTipCon;
	}

	public void setClubTipCon(ClubTipCon clubTipCon) {
		this.clubTipCon = clubTipCon;
	}

}