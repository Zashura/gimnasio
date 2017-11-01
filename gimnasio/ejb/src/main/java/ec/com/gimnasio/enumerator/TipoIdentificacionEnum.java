package ec.com.gimnasio.enumerator;

public enum TipoIdentificacionEnum {
	
	CEDULA("C", "Cédula"),
	PASAPORTE("P", "Pasaporte"),
	CARNET_REFUGIADO("R", "Carné de Refugiado"),
	SIN_DOCUMENTOS("S", "Sin documentos");

	private String codigo;
	private String descripcion;

	private TipoIdentificacionEnum(String codigo, String descripcion) {
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
