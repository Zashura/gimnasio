package ec.com.gimnasio.exception;

import javax.ejb.ApplicationException;


/**
 *
 */
@ApplicationException(rollback = true)
public class ClubUpdateException extends ClubDAOException {

	private static final long serialVersionUID = 4950539500379930647L;
	
	public static final String MSG = "Error actualizando registro en la tabla %s.";

    public ClubUpdateException(Object entity) {
        super(ClubDAOException.format(MSG, entity));
    }

    public ClubUpdateException(Object entity, Throwable ex) {
        super(ClubDAOException.format(MSG, entity), ex);
    }
    
}
