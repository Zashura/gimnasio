package ec.com.gimnasio.exception;

import javax.ejb.ApplicationException;


/**
 * 
 */
@ApplicationException(rollback = false)
public class ClubNonUniqueResultException extends Exception {

	private static final long serialVersionUID = 971044079612567355L;

	public ClubNonUniqueResultException() {
		super();
	}

	public ClubNonUniqueResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClubNonUniqueResultException(String message) {
		super(message);
	}

	public ClubNonUniqueResultException(Throwable cause) {
		super(cause);
	}
}
