package ec.com.gimnasio.enumerator;

public enum SexoEnum {
	
	FEMENIMO("F", "FEMENINO", "Femenino"),
	MASCULINO("M", "MASCULINO", "Masculino");

	private String codigo;
	private String nombre;
	private String descripcion;

	private SexoEnum(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
