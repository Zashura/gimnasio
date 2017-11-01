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
 * The persistent class for the club_tip_ide database table.
 * 
 */
@Entity
@Table(name="club_tip_ide")
@NamedQuery(name="ClubTipIde.findAll", query="SELECT c FROM ClubTipIde c")
public class ClubTipIde implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_TIP_IDE_TIINCODIGO_GENERATOR", sequenceName="SEQ_CLUB_TIP_IDE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_TIP_IDE_TIINCODIGO_GENERATOR")
	@Column(name="tiin_codigo")
	private long tiinCodigo;

	@Column(name="tiin_descripcion")
	private String tiinDescripcion;

	@Column(name="tiin_estado")
	private int tiinEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="tiin_fec_creacion")
	private Date tiinFecCreacion;

	@Column(name="tiin_nemonico")
	private String tiinNemonico;

	//bi-directional many-to-one association to ClubPersona
	@OneToMany(mappedBy="clubTipIde")
	private List<ClubPersona> clubPersonas;

	public ClubTipIde() {
	}

	public long getTiinCodigo() {
		return this.tiinCodigo;
	}

	public void setTiinCodigo(long tiinCodigo) {
		this.tiinCodigo = tiinCodigo;
	}

	public String getTiinDescripcion() {
		return this.tiinDescripcion;
	}

	public void setTiinDescripcion(String tiinDescripcion) {
		this.tiinDescripcion = tiinDescripcion;
	}

	public int getTiinEstado() {
		return this.tiinEstado;
	}

	public void setTiinEstado(int tiinEstado) {
		this.tiinEstado = tiinEstado;
	}

	public Date getTiinFecCreacion() {
		return this.tiinFecCreacion;
	}

	public void setTiinFecCreacion(Date tiinFecCreacion) {
		this.tiinFecCreacion = tiinFecCreacion;
	}

	public String getTiinNemonico() {
		return this.tiinNemonico;
	}

	public void setTiinNemonico(String tiinNemonico) {
		this.tiinNemonico = tiinNemonico;
	}

	public List<ClubPersona> getClubPersonas() {
		return this.clubPersonas;
	}

	public void setClubPersonas(List<ClubPersona> clubPersonas) {
		this.clubPersonas = clubPersonas;
	}

	public ClubPersona addClubPersona(ClubPersona clubPersona) {
		getClubPersonas().add(clubPersona);
		clubPersona.setClubTipIde(this);

		return clubPersona;
	}

	public ClubPersona removeClubPersona(ClubPersona clubPersona) {
		getClubPersonas().remove(clubPersona);
		clubPersona.setClubTipIde(null);

		return clubPersona;
	}

}