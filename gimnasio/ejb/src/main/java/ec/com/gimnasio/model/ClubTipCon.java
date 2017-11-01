package ec.com.gimnasio.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the club_tip_con database table.
 * 
 */
@Entity
@Table(name="club_tip_con")
@NamedQuery(name="ClubTipCon.findAll", query="SELECT c FROM ClubTipCon c")
public class ClubTipCon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_TIP_CON_TICOCODIGO_GENERATOR", sequenceName="SEQ_CLUB_TIP_CON")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_TIP_CON_TICOCODIGO_GENERATOR")
	@Column(name="tico_codigo")
	private long ticoCodigo;

	@Column(name="tico_descripcion")
	private String ticoDescripcion;

	@Column(name="tico_estado")
	private BigDecimal ticoEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="tico_fec_creacion")
	private Date ticoFecCreacion;

	@Column(name="tico_nemonico")
	private String ticoNemonico;

	//bi-directional many-to-one association to ClubTicoPer
	@OneToMany(mappedBy="clubTipCon")
	private List<ClubTicoPer> clubTicoPers;

	//bi-directional many-to-one association to ClubTicoXSede
	@OneToMany(mappedBy="clubTipCon")
	private List<ClubTicoXSede> clubTicoXSedes;

	public ClubTipCon() {
	}

	public long getTicoCodigo() {
		return this.ticoCodigo;
	}

	public void setTicoCodigo(long ticoCodigo) {
		this.ticoCodigo = ticoCodigo;
	}

	public String getTicoDescripcion() {
		return this.ticoDescripcion;
	}

	public void setTicoDescripcion(String ticoDescripcion) {
		this.ticoDescripcion = ticoDescripcion;
	}

	public BigDecimal getTicoEstado() {
		return this.ticoEstado;
	}

	public void setTicoEstado(BigDecimal ticoEstado) {
		this.ticoEstado = ticoEstado;
	}

	public Date getTicoFecCreacion() {
		return this.ticoFecCreacion;
	}

	public void setTicoFecCreacion(Date ticoFecCreacion) {
		this.ticoFecCreacion = ticoFecCreacion;
	}

	public String getTicoNemonico() {
		return this.ticoNemonico;
	}

	public void setTicoNemonico(String ticoNemonico) {
		this.ticoNemonico = ticoNemonico;
	}

	public List<ClubTicoPer> getClubTicoPers() {
		return this.clubTicoPers;
	}

	public void setClubTicoPers(List<ClubTicoPer> clubTicoPers) {
		this.clubTicoPers = clubTicoPers;
	}

	public ClubTicoPer addClubTicoPer(ClubTicoPer clubTicoPer) {
		getClubTicoPers().add(clubTicoPer);
		clubTicoPer.setClubTipCon(this);

		return clubTicoPer;
	}

	public ClubTicoPer removeClubTicoPer(ClubTicoPer clubTicoPer) {
		getClubTicoPers().remove(clubTicoPer);
		clubTicoPer.setClubTipCon(null);

		return clubTicoPer;
	}

	public List<ClubTicoXSede> getClubTicoXSedes() {
		return this.clubTicoXSedes;
	}

	public void setClubTicoXSedes(List<ClubTicoXSede> clubTicoXSedes) {
		this.clubTicoXSedes = clubTicoXSedes;
	}

	public ClubTicoXSede addClubTicoXSede(ClubTicoXSede clubTicoXSede) {
		getClubTicoXSedes().add(clubTicoXSede);
		clubTicoXSede.setClubTipCon(this);

		return clubTicoXSede;
	}

	public ClubTicoXSede removeClubTicoXSede(ClubTicoXSede clubTicoXSede) {
		getClubTicoXSedes().remove(clubTicoXSede);
		clubTicoXSede.setClubTipCon(null);

		return clubTicoXSede;
	}

}