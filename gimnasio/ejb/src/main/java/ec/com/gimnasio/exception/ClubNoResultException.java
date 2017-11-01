package ec.com.gimnasio.exception;

import javax.ejb.ApplicationException;


/**
 * 
 */
@ApplicationException(rollback = false)
public class ClubNoResultException extends Exception {

	private static final long serialVersionUID = 5707623347782367027L;

	public ClubNoResultException() {
		super();
	}

	public ClubNoResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClubNoResultException(String message) {
		super(message);
	}

	public ClubNoResultException(Throwable cause) {
		super(cause);
	}
}
