package ec.com.gimnasio.exception;

import javax.ejb.ApplicationException;


/**
 * 
 */
@ApplicationException(rollback = true)
public class ClubQueryException extends ClubDAOException {
	
	private static final long serialVersionUID = -7298173463478234883L;
	
	public static final String MSG = "Error al realizar la consulta";

	public ClubQueryException() {
		super(MSG);
	}

	public ClubQueryException(Throwable ex) {
		super(MSG, ex);
	}
}
