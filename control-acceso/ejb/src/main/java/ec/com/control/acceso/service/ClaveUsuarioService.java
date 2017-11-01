package ec.com.control.acceso.service;

import javax.ejb.Local;

import ec.com.control.acceso.model.Usuario;

@Local
public interface ClaveUsuarioService {
	
	public boolean cambiarClaveObligatoriamente(Usuario usuario);
}
