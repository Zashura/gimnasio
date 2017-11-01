package ec.com.control.acceso.exception;

public class AplicacionException extends Exception {

	private static final long serialVersionUID = -7280120866853321637L;

	public AplicacionException() {
		super();
	}

	public AplicacionException(String message, Throwable cause) {
		super(message, cause);
	}

	public AplicacionException(String message) {
		super(message);
	}

	public AplicacionException(Throwable cause) {
		super(cause);
	}
	
}
