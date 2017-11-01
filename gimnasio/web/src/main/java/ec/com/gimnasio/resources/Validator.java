package ec.com.gimnasio.resources;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Validator {

	//public static final int NUMERO_DE_PROVINCIAS = 24;//22;   
	  
    /**
     * Este método permite verificar si una cédula de identidad es verdadera
     * retorna true si es válida, caso contrario retorna false.
     * @param cedula Cédula de Identidad Ecuatoriana de 10 digitos.
     * @return Si es verdadera true, si es falsa false
     */
    public static boolean esCedulaValida(String cedula) {
        //verifica que tenga 10 dígitos y que contenga solo valores numéricos
        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }

        //verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= Constantes.NUMERO_PROVINCIAS_ECUADOR))) {
            return false;
        }

        //verifica que el último dígito de la cédula sea válido
        int[] d = new int[10];

        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        //sumamos los duplos de posición impar
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        //sumamos los digitos de posición par
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }

        //Sumamos los dos resultados
        int suma = imp + par;
        
        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) +
                "0") - suma;
        
        //Si es diez el décimo dígito es cero        
        d10 = (d10 == 10) ? 0 : d10;

        //si el décimo dígito calculado es igual al digitado la cédula es correcta
        return d10 == d[9];
    }  
    
    public static Integer calcularEdad(Date fechaNacimientoWeb) throws ParseException{
		if(fechaNacimientoWeb!=null){
		
			 Calendar fechaNacimiento = Calendar.getInstance();
			 //Se crea un objeto con la fecha actual
			  Calendar fechaActual = Calendar.getInstance();
			 //Se asigna la fecha recibida a la fecha de nacimiento.
			  fechaNacimiento.setTime(fechaNacimientoWeb);
			  //Se restan la fecha actual y la fecha de nacimiento
			   Integer anio = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
			    Integer mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
			   Integer dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
			      //Se ajusta el año dependiendo el mes y el día
			   if(mes<0 || (mes==0 && dia<0)){
				   anio--;
		     }
		  //Regresa la edad en base a la fecha de nacimiento
		     return anio;
		}else {
			return 0;
		}
	}
    
    public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		/* CREAMOS LOS OBJETOS GREGORIAN CALENDAR PARA EFECTUAR LA RESTA */
		GregorianCalendar date1 = new GregorianCalendar();
		date1.setTime(fechaMenor); // dateIni es el objeto Date
		GregorianCalendar date2 = new GregorianCalendar();
		date2.setTime(fechaMayor); // dateFin es el objeto Date
		int rango = 0;
		/* COMPROBAMOS SI ESTAMOS EN EL MISMO AÑO */
		if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
			rango = date2.get(Calendar.DAY_OF_YEAR)-date1.get(Calendar.DAY_OF_YEAR);
		} else {
			/*
			 * SI ESTAMOS EN DISTINTO AÑO COMPROBAMOS QUE EL AÑO DEL DATEINI NO
			 * SEA BISIESTO SI ES BISIESTO SON 366 DIAS EL AÑO SINO SON 365
			 */
			int diasAnio = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366	: 365;
			/* CALCULAMOS EL RANGO DE AÑOS */
			int rangoAnios = date2.get(Calendar.YEAR)-date1.get(Calendar.YEAR);
			/* CALCULAMOS EL RANGO DE DIAS QUE HAY */
			rango = (rangoAnios*diasAnio)+(date2.get(Calendar.DAY_OF_YEAR)-date1.get(Calendar.DAY_OF_YEAR));
		}
		return rango;
	}
    

}
