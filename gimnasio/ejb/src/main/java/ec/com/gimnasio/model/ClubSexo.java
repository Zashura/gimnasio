package ec.com.gimnasio.model;

import java.io.Serializable;
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


/**
 * The persistent class for the club_sexo database table.
 * 
 */
@Entity
@Table(name="club_sexo")
@NamedQuery(name="ClubSexo.findAll", query="SELECT c FROM ClubSexo c")
public class ClubSexo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_SEXO_SEXCODIGO_GENERATOR", sequenceName="SEQ_CLUB_SEXO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_SEXO_SEXCODIGO_GENERATOR")
	@Column(name="sex_codigo")
	private long sexCodigo;

	@Column(name="sex_descripcion")
	private String sexDescripcion;

	@Column(name="sex_estado")
	private int sexEstado;

	@Column(name="sex_nemonico")
	private String sexNemonico;

	//bi-directional many-to-one association to ClubPersona
	@OneToMany(mappedBy="clubSexo")
	private List<ClubPersona> clubPersonas;

	public ClubSexo() {
	}

	public long getSexCodigo() {
		return this.sexCodigo;
	}

	public void setSexCodigo(long sexCodigo) {
		this.sexCodigo = sexCodigo;
	}

	public String getSexDescripcion() {
		return this.sexDescripcion;
	}

	public void setSexDescripcion(String sexDescripcion) {
		this.sexDescripcion = sexDescripcion;
	}

	public int getSexEstado() {
		return this.sexEstado;
	}

	public void setSexEstado(int sexEstado) {
		this.sexEstado = sexEstado;
	}

	public String getSexNemonico() {
		return this.sexNemonico;
	}

	public void setSexNemonico(String sexNemonico) {
		this.sexNemonico = sexNemonico;
	}

	public List<ClubPersona> getClubPersonas() {
		return this.clubPersonas;
	}

	public void setClubPersonas(List<ClubPersona> clubPersonas) {
		this.clubPersonas = clubPersonas;
	}

	public ClubPersona addClubPersona(ClubPersona clubPersona) {
		getClubPersonas().add(clubPersona);
		clubPersona.setClubSexo(this);

		return clubPersona;
	}

	public ClubPersona removeClubPersona(ClubPersona clubPersona) {
		getClubPersonas().remove(clubPersona);
		clubPersona.setClubSexo(null);

		return clubPersona;
	}

}