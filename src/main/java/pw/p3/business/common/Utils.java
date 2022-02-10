package pw.p3.business.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class with some utils for date manipulation
 * @author Mario Berrios Carmona
 * @author Francisco Camargo Lucena
 * @author Francisco Llamas Nuflo
 *
 */

public class Utils {
	/**
	 * Parses a given date (string format) to a date object.
	 * @throws NullPointerException - if text is null.
	 * @param fecha string to parse into Date, format: yyyy-MM-dd HH:mm
	 * @return Date representation of the given string, null if the given string format was wrong
	 */
	public static Date stringToDate(String fecha) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		try {
			date = format.parse(fecha);
		} catch (ParseException e) {
			System.out.println("Error al intentar formatear la fecha.");
			System.out.println("El formato de fecha es yyyy-MM-dd");
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	 *  Parses a given date (string format) to a date object.
	 * @param fecha, string to parse
	 * @param formato, format to apply
	 * @return Date representation of the given string, null if the given string format was wrong
	 */
	public static Date stringToDate(String fecha, String formato) {

		SimpleDateFormat format = new SimpleDateFormat(formato);
		Date date = new Date();
		try {
			date = format.parse(fecha);
		} catch (ParseException e) {
			System.out.println("Error al intentar formatear la fecha.");
			System.out.println("El formato de fecha es yyyy-MM-dd");
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	 * Converts a date object into a string
	 * @param date, date to convert
	 * @return string with format: yyyy-MM-dd HH:mm
	 */
	public static String dateToString(Date date) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String fecha = format.format(date);
		return fecha;
	}
	
	/**
	 * Converts a date object into a string
	 * @param date, date to convert
	 * @return string with format: yyyy-MM-dd HH:mm
	 */
	public static String dateToString(Date date, String formato) {
		
		SimpleDateFormat format = new SimpleDateFormat(formato);
		String fecha = format.format(date);
		return fecha;
	}
	
	/**
	 * Converts a date object into a string
	 * @param date, string to parse
	 * @param formato, format to apply
	 * @return Date representation of the given string, null if the given string format was wrong
	 */
    public static String millisToString(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(millis);
        String fecha = format.format(date);
        return fecha;
    }
}
