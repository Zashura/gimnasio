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
 * The persistent class for the club_institucion database table.
 * 
 */
@Entity
@Table(name="club_institucion")
@NamedQuery(name="ClubInstitucion.findAll", query="SELECT c FROM ClubInstitucion c")
public class ClubInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_INSTITUCION_CLUCODIGO_GENERATOR", sequenceName="SEQ_CLUB_INSTITUCION",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_INSTITUCION_CLUCODIGO_GENERATOR")
	@Column(name="clu_codigo")
	private long cluCodigo;

	@Column(name="clu_descripcion")
	private String cluDescripcion;

	@Column(name="clu_eslogan")
	private String cluEslogan;

	@Column(name="clu_estado")
	private int cluEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="clu_fec_creacion")
	private Date cluFecCreacion;

	@Column(name="clu_logotipo")
	private byte[] cluLogotipo;

	@Column(name="clu_pag_web")
	private String cluPagWeb;

	@Column(name="clu_ruc")
	private String cluRuc;
	
	@Column(name="clu_cod_cas")
	private long cluCodCas;

	//bi-directional many-to-one association to ClubSedIn
	@OneToMany(mappedBy="clubInstitucion")
	private List<ClubSedIn> clubSedIns;

	public ClubInstitucion() {
	}

	public long getCluCodigo() {
		return this.cluCodigo;
	}

	public void setCluCodigo(long cluCodigo) {
		this.cluCodigo = cluCodigo;
	}

	public String getCluDescripcion() {
		return this.cluDescripcion;
	}

	public void setCluDescripcion(String cluDescripcion) {
		this.cluDescripcion = cluDescripcion;
	}

	public String getCluEslogan() {
		return this.cluEslogan;
	}

	public void setCluEslogan(String cluEslogan) {
		this.cluEslogan = cluEslogan;
	}

	public int getCluEstado() {
		return this.cluEstado;
	}

	public void setCluEstado(int cluEstado) {
		this.cluEstado = cluEstado;
	}

	public Date getCluFecCreacion() {
		return this.cluFecCreacion;
	}

	public void setCluFecCreacion(Date cluFecCreacion) {
		this.cluFecCreacion = cluFecCreacion;
	}

	public byte[] getCluLogotipo() {
		return this.cluLogotipo;
	}

	public void setCluLogotipo(byte[] cluLogotipo) {
		this.cluLogotipo = cluLogotipo;
	}

	public String getCluPagWeb() {
		return this.cluPagWeb;
	}

	public void setCluPagWeb(String cluPagWeb) {
		this.cluPagWeb = cluPagWeb;
	}

	public String getCluRuc() {
		return this.cluRuc;
	}

	public void setCluRuc(String cluRuc) {
		this.cluRuc = cluRuc;
	}

	public List<ClubSedIn> getClubSedIns() {
		return this.clubSedIns;
	}

	public void setClubSedIns(List<ClubSedIn> clubSedIns) {
		this.clubSedIns = clubSedIns;
	}

	public ClubSedIn addClubSedIn(ClubSedIn clubSedIn) {
		getClubSedIns().add(clubSedIn);
		clubSedIn.setClubInstitucion(this);

		return clubSedIn;
	}

	public ClubSedIn removeClubSedIn(ClubSedIn clubSedIn) {
		getClubSedIns().remove(clubSedIn);
		clubSedIn.setClubInstitucion(null);

		return clubSedIn;
	}

	public long getCluCodCas() {
		return cluCodCas;
	}

	public void setCluCodCas(long cluCodCas) {
		this.cluCodCas = cluCodCas;
	}

}