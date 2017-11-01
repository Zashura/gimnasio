package ec.com.control.acceso.model;
// default package
// Generated Nov 20, 2013 4:33:26 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;


@Entity
@Table(name = "CAS_PARAMETRO")
@SequenceGenerator(name = "SEQ_SEG_PARAMETRO_GEN", sequenceName = "SEQ_CAS_PARAMETRO", allocationSize = 1)
@Audited
public class Parametro implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String descripcion;
	private Integer valor;
	private EnumParametro enumeration;

	public Parametro() {
	}
	
	public enum EnumParametro {
		CAD_DIAS, CAD_MASIVA, NUM_CLAVES, RECORDATORIO, CAD_TOKEN
	}

	@Id
	@GeneratedValue(generator = "SEQ_SEG_PARAMETRO_GEN", strategy = GenerationType.SEQUENCE)
	@Column(name = "CODIGO", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "VALOR")
	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ENUMERATION", length = 12)
	public EnumParametro getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(EnumParametro enumeration) {
		this.enumeration = enumeration;
	}

}
