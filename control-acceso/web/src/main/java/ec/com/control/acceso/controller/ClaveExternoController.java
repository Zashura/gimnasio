package ec.com.control.acceso.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.resources.Utils;
import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.exception.ClaveNoValidaException;
import ec.com.control.acceso.exception.UsuarioException;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.UsuarioService;

@Named
@ViewScoped
public class ClaveExternoController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 2387513617225956555L;

	private Usuario usuario;

	private String claveAnterior;

	private String nuevaClave;

	private String confirmacionNuevaClave;

	private boolean mostrarPanelCambio;
	
	private String correoElectronico;
	
	private String mensaje;
	
	private String estilo;

	@Inject
	private UsuarioService servicioUsuario;

	@PostConstruct
	public void inicializar() {
		verificarToken();
	}
	
	public void verificarToken() {
		try {
			estilo = "rf-msgs-err";
			mostrarPanelCambio = false;
			int diasVigencia = Integer.valueOf(Utils.obtenerPropiedad("parametro.dias.vigencia.token").trim());
			String token = super.getHttpRequestParameter("token");
			String id = super.getHttpRequestParameter("id");
			if (id == null || id.isEmpty() || token == null || token.isEmpty()) {
				mensaje = "Lo sentimos, el link ingresado no es correcto.";
				mostrarPanelCambio = false;
			} else {
				usuario = servicioUsuario.buscarPorId(Long.valueOf(id));
				validarToken(token, diasVigencia);
			}
		} catch (NumberFormatException e) {
			agregarMensajeError(e);
		} catch (UsuarioException e) {
			agregarMensajeError(e);
		}
	}
	
	public void validarToken(String token, int diasVigencia) {
		if(usuario.getFechaSolicitudClave() == null) {
			mensaje = "Lo sentimos, el link ingresado no es correcto.";
			return;
		}
		if(existeVigencia(diasVigencia)) {
			if (token.equals(usuario.getTokenCambioClave())) {
				mostrarPanelCambio = true;
			} else {
				mensaje = "Lo sentimos, el link ingresado no es correcto.";
			}
		} else {
			mensaje = "Lo sentimos, el link ingresado ha expirado.";
		}
	}
	
	public String cambiarClaveExterno() {
		if (nuevaClave.equals(confirmacionNuevaClave)) {
			try {
				servicioUsuario.cambiarClaveExterno(usuario, nuevaClave);
				mensaje = "La clave ha sido cambiada correctamente";
				estilo = "rf-msgs-ok";
				mostrarPanelCambio = false;
			} catch (UsuarioException ex) {
				agregarMensajeError(ex);
			} catch (ClaveNoValidaException ex) {
				agregarMensajeError("La clave ingresada no coincide con la anterior", "La clave ingresada no coincide con la anterior");
			}
		} else {
			agregarMensajeError("Las claves ingresadas no coinciden", "Las claves ingresadas no coinciden");
		}
		return null;
	}
	
	public boolean existeVigencia(int diasVigencia) {
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(usuario.getFechaSolicitudClave());
		calendar.add(Calendar.DAY_OF_MONTH, diasVigencia);
		return (fechaActual.compareTo(calendar.getTime()) <= 0);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getClaveAnterior() {
		return claveAnterior;
	}

	public void setClaveAnterior(String claveAnterior) {
		this.claveAnterior = claveAnterior;
	}

	public String getNuevaClave() {
		return nuevaClave;
	}

	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}

	public String getConfirmacionNuevaClave() {
		return confirmacionNuevaClave;
	}

	public void setConfirmacionNuevaClave(String confirmacionNuevaClave) {
		this.confirmacionNuevaClave = confirmacionNuevaClave;
	}

	public boolean isMostrarPanelCambio() {
		return mostrarPanelCambio;
	}

	public void setMostrarPanelCambio(boolean mostrarPanelCambio) {
		this.mostrarPanelCambio = mostrarPanelCambio;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

}
