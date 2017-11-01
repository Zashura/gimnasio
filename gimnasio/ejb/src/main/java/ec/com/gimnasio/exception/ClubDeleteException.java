package ec.com.gimnasio.exception;

import javax.ejb.ApplicationException;


/**
 *
 */
@ApplicationException(rollback = true)
public class ClubDeleteException extends ClubDAOException {

	private static final long serialVersionUID = 8472064570621553837L;
	
	public static final String MSG = "Error eliminando registro en la tabla %s, entidad: %s";

    public ClubDeleteException(Object entity) {
        super(ClubDAOException.format(MSG, entity));
    }

    public ClubDeleteException(Object entity, Throwable ex) {
        super(ClubDAOException.format(MSG, entity), ex);
    }
    
}
