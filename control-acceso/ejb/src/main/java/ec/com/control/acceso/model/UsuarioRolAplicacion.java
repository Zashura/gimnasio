package ec.com.control.acceso.model;
// default package
// Generated Oct 29, 2013 4:10:51 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * UsuarioRolAplicacion generated by hbm2java
 */
@Entity
@Table(name = "CAS_USUARIO_ROL_APLICACION")
@SequenceGenerator(name = "SEQ_SEG_USUARIO_ROL_APLICACION_GEN", sequenceName = "SEQ_CAS_USUARIO_ROL_APLICACION", allocationSize = 1)
@Audited
public class UsuarioRolAplicacion implements java.io.Serializable {

	private static final long serialVersionUID = 7862034547880429559L;
	private Long codigo;
	private Usuario usuario;
	private RolAplicacion rolAplicacion;
	private Date fechaInicial;
	private Date fechaFinal;

	public UsuarioRolAplicacion() {
	}

	public UsuarioRolAplicacion(Long codigo, Usuario usuario,
			RolAplicacion rolAplicacion, Date fechaInicial) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.rolAplicacion = rolAplicacion;
		this.fechaInicial = fechaInicial;
	}

	public UsuarioRolAplicacion(Long codigo, Usuario usuario,
			RolAplicacion rolAplicacion, Date fechaInicial, Date fechaFinal) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.rolAplicacion = rolAplicacion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	@Id
	@GeneratedValue(generator = "SEQ_SEG_USUARIO_ROL_APLICACION_GEN", strategy = GenerationType.SEQUENCE)
	@Column(name = "CODIGO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_USUARIO", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_ROL_APLICACION", nullable = false)
	public RolAplicacion getRolAplicacion() {
		return this.rolAplicacion;
	}

	public void setRolAplicacion(RolAplicacion rolAplicacion) {
		this.rolAplicacion = rolAplicacion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_INICIAL", nullable = false, length = 23)
	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_FINAL", length = 23)
	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

}