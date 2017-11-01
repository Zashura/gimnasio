package ec.com.control.acceso.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static Properties properties;

	public static String obtenerPropiedad(String key) {
		if (properties == null) {
			InputStream inputStream = new Utils().getClass().getClassLoader().getResourceAsStream("application.properties");
			try {
				properties = new Properties();
				properties.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				return null;
			}
		}
		return properties.getProperty(key);
	}
	
	public static String obtenerPathCompleto(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		return url.substring(0, url.indexOf(contextPath)) + contextPath;
	}
	
}
