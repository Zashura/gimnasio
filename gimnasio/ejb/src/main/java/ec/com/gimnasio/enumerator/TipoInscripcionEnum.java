package ec.com.gimnasio.enumerator;

public enum TipoInscripcionEnum {
	
	INDIVIDUAL("I", "Individual"),
	GRUPO_HERMANOS("G", "Grupo de hermanos");

	private String codigo;
	private String descripcion;

	private TipoInscripcionEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
