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
 * The persistent class for the club_instructor database table.
 * 
 */
@Entity
@Table(name="club_instructor")
@NamedQuery(name="ClubInstructor.findAll", query="SELECT c FROM ClubInstructor c")
public class ClubInstructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_INSTRUCTOR_INTCODIGO_GENERATOR", sequenceName="SEQ_CLUB_INSTRUCTOR",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_INSTRUCTOR_INTCODIGO_GENERATOR")
	@Column(name="int_codigo")
	private long intCodigo;

	@Column(name="int_celular")
	private String intCelular;

	@Column(name="int_convencional")
	private String intConvencional;

	@Column(name="int_direccion")
	private String intDireccion;

	@Column(name="int_estado")
	private int intEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="int_fec_creacion")
	private Date intFecCreacion;

	@Column(name="int_identificacion")
	private String intIdentificacion;

	@Column(name="int_nombres")
	private String intNombres;

	//bi-directional many-to-one association to ClubIntXDisSedIn
	@OneToMany(mappedBy="clubInstructor")
	private List<ClubIntXDisSedIn> clubIntXDisSedIns;

	public ClubInstructor() {
	}

	public long getIntCodigo() {
		return this.intCodigo;
	}

	public void setIntCodigo(long intCodigo) {
		this.intCodigo = intCodigo;
	}

	public String getIntCelular() {
		return this.intCelular;
	}

	public void setIntCelular(String intCelular) {
		this.intCelular = intCelular;
	}

	public String getIntConvencional() {
		return this.intConvencional;
	}

	public void setIntConvencional(String intConvencional) {
		this.intConvencional = intConvencional;
	}

	public String getIntDireccion() {
		return this.intDireccion;
	}

	public void setIntDireccion(String intDireccion) {
		this.intDireccion = intDireccion;
	}

	public int getIntEstado() {
		return this.intEstado;
	}

	public void setIntEstado(int intEstado) {
		this.intEstado = intEstado;
	}

	public Date getIntFecCreacion() {
		return this.intFecCreacion;
	}

	public void setIntFecCreacion(Date intFecCreacion) {
		this.intFecCreacion = intFecCreacion;
	}

	public String getIntIdentificacion() {
		return this.intIdentificacion;
	}

	public void setIntIdentificacion(String intIdentificacion) {
		this.intIdentificacion = intIdentificacion;
	}

	public String getIntNombres() {
		return this.intNombres;
	}

	public void setIntNombres(String intNombres) {
		this.intNombres = intNombres;
	}

	public List<ClubIntXDisSedIn> getClubIntXDisSedIns() {
		return this.clubIntXDisSedIns;
	}

	public void setClubIntXDisSedIns(List<ClubIntXDisSedIn> clubIntXDisSedIns) {
		this.clubIntXDisSedIns = clubIntXDisSedIns;
	}

	public ClubIntXDisSedIn addClubIntXDisSedIn(ClubIntXDisSedIn clubIntXDisSedIn) {
		getClubIntXDisSedIns().add(clubIntXDisSedIn);
		clubIntXDisSedIn.setClubInstructor(this);

		return clubIntXDisSedIn;
	}

	public ClubIntXDisSedIn removeClubIntXDisSedIn(ClubIntXDisSedIn clubIntXDisSedIn) {
		getClubIntXDisSedIns().remove(clubIntXDisSedIn);
		clubIntXDisSedIn.setClubInstructor(null);

		return clubIntXDisSedIn;
	}

}