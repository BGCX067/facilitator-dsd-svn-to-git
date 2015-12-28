package th.ac.kmutt.dsd.train.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static Timestamp getCurrentTimestamp(){
		return new Timestamp((new java.util.GregorianCalendar(Locale.US).getTime()).getTime());
	}	
	
	public static Date getDate(String date, String format, Locale locale) {

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);

			return dateFormat.parse(date);

		} catch (Exception e) {

		}

		return null;

	}

	public static String getDateString(Date date, String format, Locale locale) {

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);

			return dateFormat.format(date);

		} catch (Exception e) {

		}

		return null;

	}

	public static Date addDays(Date d0, Integer amount) {

		Calendar c0 = Calendar.getInstance();

		c0.setTime(d0);

		c0.add(Calendar.DATE, amount);

		return c0.getTime();

	}

	public static Date addSeconds(Date d0, Integer amount) {

		Calendar c0 = Calendar.getInstance();

		c0.setTime(d0);

		c0.add(Calendar.SECOND, amount);

		return c0.getTime();

	}

}
