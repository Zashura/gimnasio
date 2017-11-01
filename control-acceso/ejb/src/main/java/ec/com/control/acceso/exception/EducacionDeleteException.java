package ec.com.control.acceso.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EducacionDeleteException extends EducacionDAOException {

	private static final long serialVersionUID = 8472064570621553837L;
	
	public static final String MSG = "Error eliminando registro en la tabla %s, entidad: %s";

    public EducacionDeleteException(Object entity) {
        super(EducacionDAOException.format(MSG, entity));
    }

    public EducacionDeleteException(Object entity, Throwable ex) {
        super(EducacionDAOException.format(MSG, entity), ex);
    }
    
}
