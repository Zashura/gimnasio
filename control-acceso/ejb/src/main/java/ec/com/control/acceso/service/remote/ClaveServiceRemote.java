package ec.com.control.acceso.service.remote;

import java.util.List;

import javax.ejb.Remote;

import ec.com.control.acceso.model.ClaveUsuario;
import ec.com.control.acceso.model.Usuario;

@Remote
public interface ClaveServiceRemote {
	
	public List<ClaveUsuario> obtenerClaves(Usuario usuario);
	
	public boolean primerIngreso(String usuarioLogueado);
	
	public boolean contraseniaUsada(String claveComprobar);
	
	public boolean entreUltimasContrasenias(String claveComprobar, Usuario usuario);
	
	public void inactivarClaveUsuario(Usuario usuario, String tipoEstadoFinClave);

	public void inactivarClaveUsuario(ClaveUsuario claveActiva, String tipoEstadoFinClave);
	
	public void inactivarPorCaducidadTiempoVigencia(Usuario usuario);
	
	public void inactivarPorCaducidadMasiva(Usuario usuario);
	
	public boolean cambiarClaveObligatoriamente(Usuario usuario);
	
	public boolean determinarInactivarPorCaducidadTiempoVigencia(Usuario usuario);
	
	public boolean determinarInactivarPorCaducidadMasiva(Usuario usuario);
	
	public void actualizarEstadoClaveInactivada(Usuario usuario, int opcionEstadoFinClave);
}
