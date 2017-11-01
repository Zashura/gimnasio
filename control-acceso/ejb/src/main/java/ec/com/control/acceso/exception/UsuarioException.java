package ec.com.control.acceso.exception;

public class UsuarioException extends Exception {

	private static final long serialVersionUID = -7280120866853321637L;

	public UsuarioException() {
		super();
	}

	public UsuarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioException(String message) {
		super(message);
	}

	public UsuarioException(Throwable cause) {
		super(cause);
	}
	
}
