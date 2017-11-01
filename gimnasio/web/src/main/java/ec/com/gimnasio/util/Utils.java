package ec.com.gimnasio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ec.com.gimnasio.resources.Constantes;

public class Utils {
	
	/**
	 * Este metodo permite verificar si una cedula de identidad es verdadera
	 * retorna verdadero si es valida, caso contrario retorna falso.
	 * 
	 * @param cedula Cedula de Identidad ecuatoriana de 10 digitos.
	 * @return Si es verdadera true, si es falsa false
	 */
    public static boolean isValidCedula(String cedula) {
		// verifica que tenga 10 dígitos y que contenga solo valores numéricos
		if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
			return false;
		}

		// verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
		int prov = Integer.parseInt(cedula.substring(0, 2));
		if (!((prov > 0) && (prov <= Constantes.NUMERO_PROVINCIAS_ECUADOR))) {
			return false;
		}

		// verifica que el último dígito de la cédula sea válido
		int[] d = new int[10];

		// Asignamos el string a un array
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(cedula.charAt(i) + "");
		}

		int imp = 0;
		int par = 0;

		// sumamos los duplos de posición impar
		for (int i = 0; i < d.length; i += 2) {
			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
			imp += d[i];
		}

		// sumamos los digitos de posición par
		for (int i = 1; i < (d.length - 1); i += 2) {
			par += d[i];
		}

		// Sumamos los dos resultados
		int suma = imp + par;

		// Restamos de la decena superior
		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) + "0") - suma;
		// Si es diez el décimo dígito es cero
		d10 = (d10 == 10) ? 0 : d10;

		// si el décimo dígito calculado es igual al digitado la cédula es
		// correcta
		return d10 == d[9];
    }
	
	/**
	 * Método que valida la cédula en casos especiales
	 * 
	 * @param cedula
	 * @return Verdadero o falso cuando se cumple la condición
	 */
	public static boolean isValidSpecialCaseCedula(String cedula) {
		return cedula.length() == 10 && ("304".equals(cedula.substring(0, 3)) || "305".equals(cedula.substring(0, 3))); 
	}
    
	/**
	 * Metodo que 
	 * @param fechaNacimientoWeb
	 * @return
	 * @throws ParseException
	 */
	public static Integer calculateAge(Date fechaNacimientoWeb) throws ParseException {
		if (fechaNacimientoWeb != null) {
			// Se crea un objeto con la fecha actual
			Calendar fechaActual = Calendar.getInstance();
			// Se asigna la fecha recibida a la fecha de nacimiento.
			Calendar fechaNacimiento = Calendar.getInstance();
			fechaNacimiento.setTime(fechaNacimientoWeb);
			// Se restan la fecha actual y la fecha de nacimiento
			Integer anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
			Integer mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
			Integer dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
			// Se ajusta el año dependiendo el mes y el día
			if (mes < 0 || (mes == 0 && dia < 0)) {
				anio--;
			}
			return anio; // Regresa la edad en base a la fecha de nacimiento
		} else {
			return 0;
		}
	}
	
	/**
	 * Método que pone la fecha en letras
	 * 
	 * @param fecha
	 * @return fecha en letras
	 */
	public static String fechaEnLetrasComprobante(Date fecha){
		String fechaFinal = "";
		if (fecha != null) {
			SimpleDateFormat formateador = new SimpleDateFormat(
					"dd 'de' MMMMM 'de' yyyy'.'", new Locale("es", "ES"));
			fechaFinal = formateador.format(fecha);
		}
		return fechaFinal;
	}

}
