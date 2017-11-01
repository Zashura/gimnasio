package ec.com.gimnasio.enumerator;

public enum EstadoValAnioEnum {
	
	PRESUPUESTO(0, "Presupuestado"), 
	ANIO_1(1, "Anio_1"),
	ANIO_2(2, "Anio_2"),
	ANIO_3(3, "Anio_3"),
	EJECUTADO(4, "Ejecutado"), 
	SUSTITUTIVA_PRESUPUESTO(5, "Sustitutiva de presupuestado"), 
	SUSTITUTIVA_EJECUTADO(6, "Sustitutiva de ejecutado"), 
	ESTUDIANTES_ANTIGUOS(7, "Estudiantes antiguos"), 
	ESTUDIANTES_NUEVOS(8, "Estudiantes nuevos");
	
	private int codigo;
	private String descripcion;

	private EstadoValAnioEnum(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
