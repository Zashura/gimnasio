package ec.com.control.acceso.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ec.com.control.acceso.resources.MailUtils;
import ec.com.control.acceso.resources.Utils;
import ec.com.control.acceso.scope.ViewScoped;
import ec.com.control.acceso.exception.ClaveNoValidaException;
import ec.com.control.acceso.exception.UsuarioException;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.service.UsuarioService;

@Named
@ViewScoped
public class CambioClaveController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 2387513617225956555L;

	private Usuario usuario;

	private String claveAnterior;

	private String nuevaClave;

	private String confirmacionNuevaClave;

	private boolean mostrarPanelCambio;

	private String correoElectronico;

	@Resource(mappedName = "java:jboss/mail/MinEdu")
	private Session mailSession;

	@Inject
	private UsuarioService servicioUsuario;

	@PostConstruct
	public void inicializar() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		usuario = servicioUsuario.buscarPorIdentificacion(auth.getName());
		mostrarPanelCambio = true;
	}

	public String cambiarClave() {
		if (nuevaClave.equals(confirmacionNuevaClave)) {
			try {
				servicioUsuario
						.cambiarClave(usuario, claveAnterior, nuevaClave);
				agregarMensajeInformacion(
						"La clave ha sido cambiada correctamente",
						"La clave ha sido cambiada correctamente");
			} catch (UsuarioException ex) {
				agregarMensajeError(ex);
			} catch (ClaveNoValidaException ex) {
				agregarMensajeError(
						"La clave ingresada no coincide con la anterior",
						"La clave ingresada no coincide con la anterior");
			}
		} else {
			agregarMensajeError("Las claves ingresadas no coinciden",
					"Las claves ingresadas no coinciden");
		}
		return null;
	}

	public String solicitarCambioClave() {
		try {
			usuario = servicioUsuario.buscarPorCorreo(correoElectronico);
			if (usuario == null) {
				agregarMensajeError(
						"No existe usuario con el correo electr\u00f3nico ingresado",
						"No existe usuario con el correo electr\u00f3nico ingresado");
			} else {
//				usuario = servicioUsuario.solicitarRecordarClave(usuario);
//				cargarDatos(correoElectronico, "Cambio de Clave",
//						construirMensaje());
//				enviarMail();
				
				usuario = servicioUsuario.solicitarRecordarClave(usuario);
				MailUtils mailUtils = new MailUtils(correoElectronico, "Cambio de Clave", construirMensaje());
				mailUtils.start();
			}
			mostrarPanelCambio = false;
		} catch (UsuarioException e) {
			e.printStackTrace();
			agregarMensajeError(e);
			
		} catch (SecurityException e) {
			e.printStackTrace();
			agregarMensajeError(e);
		}
		return null;
	}

	public String construirMensaje() {
		String texto = Utils.obtenerPropiedad("parametro.texto.mail.clave");
		String url = Utils.obtenerPathCompleto(getHttpRequest());
		url += "/faces/externo/cambioClave.xhtml?id=" + usuario.getCodigo()
				+ "&token=" + usuario.getTokenCambioClave();

		//System.out.println(url);
		return texto.replace("{link}", url);
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

	private Properties properties;

	private String usuario1;

	private String clave;

	private String destinatario;

	private String asunto;

	private String texto;

	public void enviarMail() throws IOException {

		{

			setearPropiedades();

			try {

				MimeMessage m = new MimeMessage(mailSession);

				Address from = new InternetAddress(usuario1);

				Address[] to = new InternetAddress[] { new InternetAddress(
						destinatario) };

				m.setFrom(from);

				m.setRecipients(Message.RecipientType.TO, to);

				m.setSubject(asunto);

				m.setSentDate(new java.util.Date());

				m.setContent(texto, "text/html");

				Transport.send(m);

			}

			catch (javax.mail.MessagingException e)

			{

				e.printStackTrace();

			}

		}

	}

	public void cargarDatos(String destinatario, String asunto, String texto) {
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.texto = texto;
	}

	public void enviarMensaje(boolean setearPropiedades)
			throws MessagingException {

		setearPropiedades();
		Session session = Session.getDefaultInstance(properties);
		session.setDebug(true);

		// MimeMessage message = new MimeMessage(session);
		// Session session = Session.getInstance(properties, new
		// javax.mail.Authenticator() {
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new PasswordAuthentication(usuario, clave);
		// }
		// });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usuario1));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject(asunto);
			message.setContent(texto, "text/html");

			Transport t = session.getTransport("smtp");
			t.connect(usuario1, clave);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			// Transport.send(message);
		} catch (Exception ex) {
			System.out.println("******************------**********************"
					+ ex);
			ex.printStackTrace();
		}
	}

	public void setearPropiedades(String host, String port, String auth,
			String username, String password) {
		properties = new Properties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.setProperty("mail.smtp.auth", auth);
		properties.setProperty("mail.smtp.starttls.enable", "false");
		properties.setProperty("mail.smtp.port", port);
		properties.setProperty("mail.smtp.user", usuario1);
		// properties.setProperty("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// properties.setProperty("mail.smtp.socketFactory.port", port);
		// properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.ssl.trust", host);
		// properties.setProperty("javax.net.debug","ssl,session");

	}

	public void setearPropiedades() {
		String host = Utils.obtenerPropiedad("parametro.smtp.host");
		String port = Utils.obtenerPropiedad("parametro.smtp.port");
		String auth = Utils.obtenerPropiedad("parametro.smtp.auth");
		usuario1 = Utils.obtenerPropiedad("parametro.smtp.user");
		clave = Utils.obtenerPropiedad("parametro.smtp.password");
		setearPropiedades(host, port, auth, usuario1, clave);

	}

	public Session getMailSession() {
		return mailSession;
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}

	public UsuarioService getServicioUsuario() {
		return servicioUsuario;
	}

	public void setServicioUsuario(UsuarioService servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(String usuario1) {
		this.usuario1 = usuario1;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
