package ec.com.control.acceso.enumeration;

public enum EstadoCaducidadClave {

	OLVIDO_CONTRASENIA("Estado cuando olvida la contraseña", "O"),
	CAMBIO_CONTRASENIA("Estado cuando se cambia la contraseña sin ninguna petición", "C"),
	CADUCIDAD_VIGENCIA("Estado cuando se caduca la contraseña", "V"),
	INACTIVACION_USUARIO("Estado cuando se inactiva el usuario", "D"),
	CADUCIDAD_MASIVA("Estado cuando se caduca de forma masiva", "M");
	
	private String descripcion;
	private String valor;
	
	private EstadoCaducidadClave(String descripcion, String valor){
		this.descripcion = descripcion;
		this.valor = valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
