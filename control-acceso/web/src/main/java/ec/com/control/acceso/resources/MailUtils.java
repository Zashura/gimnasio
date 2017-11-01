package ec.com.control.acceso.resources;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils extends Thread {
	
	private static final Logger logger = Logger.getLogger(MailUtils.class.getName());
	
	private Properties properties;
	
	private String usuario;
	
	private String clave;
	
	private String destinatario;
	
	private String asunto;
	
	private String texto;
	
	public void run() {
		try {
			enviarMensaje(true);
		} catch (MessagingException e) {
			logger.severe(" ---> No se ha podido enviar el correo al usuario: " + destinatario + ", Error: " + e.getMessage());
		}
	}
	
	public MailUtils(String destinatario, String asunto, String texto) {
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.texto = texto;
	}

	public void enviarMensaje(boolean setearPropiedades) throws MessagingException {
		if (setearPropiedades) {
			setearPropiedades();
		}
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, clave);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usuario));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(asunto);
			message.setContent(texto, "text/html");
			 Transport.send(message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setearPropiedades(String host, String port, String auth, String username, String password) {
		properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "false");
        properties.put("mail.smtp.ehlo", "false");
        properties.put("mail.debug", "true");
       // properties.put("mail.smtp.socketFactory.port", port);
        //properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	}
	
	public void setearPropiedades() {
		String host = Utils.obtenerPropiedad("parametro.smtp.host");
		String port = Utils.obtenerPropiedad("parametro.smtp.port");
		String auth = Utils.obtenerPropiedad("parametro.smtp.auth");
		usuario = Utils.obtenerPropiedad("parametro.smtp.user");
		clave = Utils.obtenerPropiedad("parametro.smtp.password");
		setearPropiedades(host, port, auth, usuario, clave);
	}
	
	public static void main(String[] args) {
		byte[] bytes = "rené ñaupari".getBytes();
		String cadena = new String(bytes);
		cadena = cadena.replaceAll("á", "a");
		cadena = cadena.replaceAll("é", "e");
		cadena = cadena.replaceAll("í", "i");
		cadena = cadena.replaceAll("ó", "o");
		cadena = cadena.replaceAll("ú", "u");
		cadena = cadena.replaceAll("ñ", "n");
		System.out.println(cadena);
		((String)(null)).toString();
	}

}
