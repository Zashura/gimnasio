package ec.com.control.acceso.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EducacionQueryException extends EducacionDAOException {
	
	private static final long serialVersionUID = -7298173463478234883L;
	
	public static final String MSG = "Error al realizar la consulta";

	public EducacionQueryException() {
		super(MSG);
	}

	public EducacionQueryException(Throwable ex) {
		super(MSG, ex);
	}
}
