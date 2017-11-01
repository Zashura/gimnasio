package ec.com.gimnasio.exception;

import javax.ejb.ApplicationException;


/**
 */
@ApplicationException(rollback = true)
public class ClubPersistException extends ClubDAOException {

	private static final long serialVersionUID = -8715590066147130737L;
	
	public static final String MSG = "Error insertando registro en la tabla %s, entidad %s.";

	public ClubPersistException(Object entity) {
		super(ClubDAOException.format(MSG, entity));
	}

	public ClubPersistException(Object entity, Throwable ex) {
		super(ClubDAOException.format(MSG, entity), ex);
		
	}
}
