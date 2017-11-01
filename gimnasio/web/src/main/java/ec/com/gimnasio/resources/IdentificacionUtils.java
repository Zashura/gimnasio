package ec.com.gimnasio.resources;


public class IdentificacionUtils {
	
	public static final String RUC = "04";
	public static final String CEDULA = "05";
	public static final String CONSUMIDOR_FINAL = "07";
	public static final String DESCRIPCION_CONSUMIDOR_FINAL = "CONSUMIDOR FINAL";
	
	private static final int MODULO_DIEZ = 10;
	private static final int MODULO_ONCE = 11;
	
	/**
	 * Metodo que valida la identificacion de un consumidor final segun
	 * estandares del SRI del Ecuador
	 * 
	 * @param identificacion
	 * @return Verdadero o falso
	 */
	public static boolean validateConsumidorFinal(String identificacion) {
		return identificacion.length() == 13 && "9999999999999".equals(identificacion);
	}
	
	/**
	 * Metodo que valida la identificacion tipo cedula segun estandares del SRI
	 * del Ecuador
	 * 
	 * @param identificacion
	 * @return Verdadero o falso
	 */
	public static boolean validateCedula(String identificacion) {
		if (identificacion.length() == 10 && identificacion.matches("^[0-9]{10}$")) {
			int[] coeficiente = new int[] { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
			int[] valores = new int[10];
			for (int i = 0; i < 10; i++) {
				valores[i] = Character.digit(identificacion.charAt(i), 10);
			}
			
			if (valores[2] >= 6 || valores[2] < 0) {
				return Boolean.FALSE;
			}
			
			int provincia = Integer.parseInt(identificacion.substring(0, 2));
			if (provincia > 24 || provincia < 1) {
				return Boolean.FALSE;
			}
			
			return valores[9] == findComparador(MODULO_DIEZ, valores, coeficiente);
		} else {
			return Boolean.FALSE;
		}
	}
	
	/**
	 * Metodo que valida la identificacion tipo ruc segun estandares del SRI del
	 * Ecuador
	 * 
	 * @param identificacion
	 * @return Verdadero o falso
	 */
	public static boolean validateRuc(String identificacion) {
		if (identificacion.length() == 13 && identificacion.matches("^[0-9]{13}$")) {
			boolean casoUno = validateCedula(identificacion.substring(0, 10));
			if (casoUno) {
				casoUno = !"000".equals(identificacion.substring(10, identificacion.length()));
			}
			boolean casoDos = validateJuridicoOtro(identificacion);
			boolean casoTres = validatePublico(identificacion);
			
			return casoUno | casoDos | casoTres;
		} else {
			return Boolean.FALSE;
		}
	}
	
	/**
	 * Metodo que valida un ruc juridico y/o extranjeros sin cedula
	 * 
	 * @param identificacion
	 * @return Verdadero o falso
	 */
	private static boolean validateJuridicoOtro(String identificacion) {
		int[] coeficiente = new int[] { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		int[] valores = new int[10];
		for (int i = 0; i < 10; i++) {
			valores[i] = Character.digit(identificacion.charAt(i), 10);
		}
		
		if ("000".equals(identificacion.substring(10, identificacion.length()))) {
			return Boolean.FALSE;
		}
		
		if (valores[2] != 9) {
			return Boolean.FALSE;
		}
		
		int provincia = Integer.parseInt(identificacion.substring(0, 2));
		if (provincia > 24 || provincia < 1) {
			return Boolean.FALSE;
		}
		
		return valores[9] == findComparador(MODULO_ONCE, valores, coeficiente);
	}
	
	/**
	 * Metodo que valida un ruc publico
	 * 
	 * @param identificacion
	 * @return Verdadero o falso
	 */
	private static boolean validatePublico(String identificacion) {
		int[] coeficiente = new int[] { 3, 2, 7, 6, 5, 4, 3, 2 };
		int[] valores = new int[9];
		for (int i = 0; i < 9; i++) {
			valores[i] = Character.digit(identificacion.charAt(i), 10);
		}
		
		if ("0000".equals(identificacion.substring(9, identificacion.length()))) {
			return Boolean.FALSE;
		}
		
		if (valores[2] != 6) {
			return Boolean.FALSE;
		}
		
		int provincia = Integer.parseInt(identificacion.substring(0, 2));
		if (provincia > 24 || provincia < 1) {
			return Boolean.FALSE;
		}
		
		return valores[8] == findComparador(MODULO_ONCE, valores, coeficiente);
	}
	
	/**
	 * Metodo que obtiene el digito verificador para la identificacion segun el
	 * algoritmo indicado
	 * 
	 * @param modulo
	 * @param valores
	 * @param coeficiente
	 * @return int
	 */
	private static int findComparador(int modulo, int[] valores, int[] coeficiente) {
		int sumatoria = 0;
		for (int i = 0; i < coeficiente.length; i++) {
			int parcial = valores[i] * coeficiente[i];
			if (modulo == MODULO_DIEZ && parcial > modulo) {
				parcial = Integer.parseInt(String.valueOf(parcial).charAt(0) + "") + Integer.parseInt(String.valueOf(parcial).charAt(1) + "");
			}
			sumatoria += parcial;
		}
		return sumatoria % modulo == 0 ? 0 : modulo - (sumatoria % modulo);
	}

}
