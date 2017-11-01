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
 * The persistent class for the clu_par_x_sed_ins database table.
 * 
 */
@Entity
@Table(name="clu_par_x_sed_ins")
@NamedQuery(name="CluParXSedIn.findAll", query="SELECT c FROM CluParXSedIn c")
public class CluParXSedIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLU_PAR_X_SED_INS_PASICODIGO_GENERATOR", sequenceName="SEQ_CLU_PAR_X_SED_INS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLU_PAR_X_SED_INS_PASICODIGO_GENERATOR")
	@Column(name="pasi_codigo")
	private long pasiCodigo;

	@Column(name="pasi_estado")
	private int pasiEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="pasi_fec_creacion")
	private Date pasiFecCreacion;

	@Column(name="pasi_nemonico")
	private String pasiNemonico;

	//bi-directional many-to-one association to ClubParroquia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="par_codigo")
	private ClubParroquia clubParroquia;

	//bi-directional many-to-one association to ClubSedIn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sein_codigo")
	private ClubSedIn clubSedIn;

	public CluParXSedIn() {
	}

	public long getPasiCodigo() {
		return this.pasiCodigo;
	}

	public void setPasiCodigo(long pasiCodigo) {
		this.pasiCodigo = pasiCodigo;
	}

	public int getPasiEstado() {
		return this.pasiEstado;
	}

	public void setPasiEstado(int pasiEstado) {
		this.pasiEstado = pasiEstado;
	}

	public Date getPasiFecCreacion() {
		return this.pasiFecCreacion;
	}

	public void setPasiFecCreacion(Date pasiFecCreacion) {
		this.pasiFecCreacion = pasiFecCreacion;
	}

	public String getPasiNemonico() {
		return this.pasiNemonico;
	}

	public void setPasiNemonico(String pasiNemonico) {
		this.pasiNemonico = pasiNemonico;
	}

	public ClubParroquia getClubParroquia() {
		return this.clubParroquia;
	}

	public void setClubParroquia(ClubParroquia clubParroquia) {
		this.clubParroquia = clubParroquia;
	}

	public ClubSedIn getClubSedIn() {
		return this.clubSedIn;
	}

	public void setClubSedIn(ClubSedIn clubSedIn) {
		this.clubSedIn = clubSedIn;
	}

}