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
 * The persistent class for the club_dia database table.
 * 
 */
@Entity
@Table(name="club_dia")
@NamedQuery(name="ClubDia.findAll", query="SELECT c FROM ClubDia c")
public class ClubDia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLUB_DIA_DIACODIGO_GENERATOR", sequenceName="SEQ_CLUB_DIA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLUB_DIA_DIACODIGO_GENERATOR")
	@Column(name="dia_codigo")
	private long diaCodigo;

	@Column(name="dia_descripcion")
	private String diaDescripcion;

	@Column(name="dia_estado")
	private int diaEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="dia_fec_creacion")
	private Date diaFecCreacion;

	@Column(name="dia_nemonico")
	private String diaNemonico;

	//bi-directional many-to-one association to ClubHorDia
	@OneToMany(mappedBy="clubDia")
	private List<ClubHorDia> clubHorDias;

	public ClubDia() {
	}

	public long getDiaCodigo() {
		return this.diaCodigo;
	}

	public void setDiaCodigo(long diaCodigo) {
		this.diaCodigo = diaCodigo;
	}

	public String getDiaDescripcion() {
		return this.diaDescripcion;
	}

	public void setDiaDescripcion(String diaDescripcion) {
		this.diaDescripcion = diaDescripcion;
	}

	public int getDiaEstado() {
		return this.diaEstado;
	}

	public void setDiaEstado(int diaEstado) {
		this.diaEstado = diaEstado;
	}

	public Date getDiaFecCreacion() {
		return this.diaFecCreacion;
	}

	public void setDiaFecCreacion(Date diaFecCreacion) {
		this.diaFecCreacion = diaFecCreacion;
	}

	public String getDiaNemonico() {
		return this.diaNemonico;
	}

	public void setDiaNemonico(String diaNemonico) {
		this.diaNemonico = diaNemonico;
	}

	public List<ClubHorDia> getClubHorDias() {
		return this.clubHorDias;
	}

	public void setClubHorDias(List<ClubHorDia> clubHorDias) {
		this.clubHorDias = clubHorDias;
	}

	public ClubHorDia addClubHorDia(ClubHorDia clubHorDia) {
		getClubHorDias().add(clubHorDia);
		clubHorDia.setClubDia(this);

		return clubHorDia;
	}

	public ClubHorDia removeClubHorDia(ClubHorDia clubHorDia) {
		getClubHorDias().remove(clubHorDia);
		clubHorDia.setClubDia(null);

		return clubHorDia;
	}

}