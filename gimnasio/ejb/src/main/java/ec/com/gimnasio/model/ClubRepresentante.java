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
 * The persistent class for the club_representante database table.
 * 
 */
@Entity
@Table(name="club_representante")
@NamedQuery(name="ClubRepresentante.findAll", query="SELECT c FROM ClubRepresentante c")
public class ClubRepresentante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_REPRESENTANTE_REPCODIGO_GENERATOR", sequenceName="SEQ_CLUB_REPRESENTANTE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_REPRESENTANTE_REPCODIGO_GENERATOR")
	@Column(name="rep_codigo")
	private long repCodigo;

	@Column(name="rep_celular")
	private String repCelular;

	@Column(name="rep_convencional")
	private String repConvencional;

	@Column(name="rep_direccion")
	private String repDireccion;

	@Column(name="rep_estado")
	private int repEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="rep_fech_creacion")
	private Date repFechCreacion;

	@Column(name="rep_identificacion")
	private String repIdentificacion;

	@Column(name="rep_nombres")
	private String repNombres;

	//bi-directional many-to-one association to ClubPersona
	@OneToMany(mappedBy="clubRepresentante")
	private List<ClubPersona> clubPersonas;

	public ClubRepresentante() {
	}

	public long getRepCodigo() {
		return this.repCodigo;
	}

	public void setRepCodigo(long repCodigo) {
		this.repCodigo = repCodigo;
	}

	public String getRepCelular() {
		return this.repCelular;
	}

	public void setRepCelular(String repCelular) {
		this.repCelular = repCelular;
	}

	public String getRepConvencional() {
		return this.repConvencional;
	}

	public void setRepConvencional(String repConvencional) {
		this.repConvencional = repConvencional;
	}

	public String getRepDireccion() {
		return this.repDireccion;
	}

	public void setRepDireccion(String repDireccion) {
		this.repDireccion = repDireccion;
	}

	public int getRepEstado() {
		return this.repEstado;
	}

	public void setRepEstado(int repEstado) {
		this.repEstado = repEstado;
	}

	public Date getRepFechCreacion() {
		return this.repFechCreacion;
	}

	public void setRepFechCreacion(Date repFechCreacion) {
		this.repFechCreacion = repFechCreacion;
	}

	public String getRepIdentificacion() {
		return this.repIdentificacion;
	}

	public void setRepIdentificacion(String repIdentificacion) {
		this.repIdentificacion = repIdentificacion;
	}

	public String getRepNombres() {
		return this.repNombres;
	}

	public void setRepNombres(String repNombres) {
		this.repNombres = repNombres;
	}

	public List<ClubPersona> getClubPersonas() {
		return this.clubPersonas;
	}

	public void setClubPersonas(List<ClubPersona> clubPersonas) {
		this.clubPersonas = clubPersonas;
	}

	public ClubPersona addClubPersona(ClubPersona clubPersona) {
		getClubPersonas().add(clubPersona);
		clubPersona.setClubRepresentante(this);

		return clubPersona;
	}

	public ClubPersona removeClubPersona(ClubPersona clubPersona) {
		getClubPersonas().remove(clubPersona);
		clubPersona.setClubRepresentante(null);

		return clubPersona;
	}

}