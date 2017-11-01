package ec.com.control.acceso.exception;

public class ClaveNoValidaException extends Exception {

	private static final long serialVersionUID = -7280120866853321637L;

	public ClaveNoValidaException() {
		super();
	}

	public ClaveNoValidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClaveNoValidaException(String message) {
		super(message);
	}

	public ClaveNoValidaException(Throwable cause) {
		super(cause);
	}
	
}
