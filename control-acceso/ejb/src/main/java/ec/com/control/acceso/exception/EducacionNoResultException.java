package ec.com.control.acceso.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class EducacionNoResultException extends Exception {

	private static final long serialVersionUID = 5707623347782367027L;

	public EducacionNoResultException() {
		super();
	}

	public EducacionNoResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public EducacionNoResultException(String message) {
		super(message);
	}

	public EducacionNoResultException(Throwable cause) {
		super(cause);
	}
}
