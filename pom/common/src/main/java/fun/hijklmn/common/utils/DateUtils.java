package fun.hijklmn.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private final static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat();
		}
	};

	public static String curDateStr(String regex) {
		SimpleDateFormat format = local.get();
		format.applyPattern(regex);
		return format.format(new Date());
	}

	public static String date2Str(Date date, String regex) {
		SimpleDateFormat format = local.get();
		format.applyPattern(regex);
		return format.format(date);
	}

	public static Date str2Date(String str, String regex) throws ParseException {
		SimpleDateFormat format = local.get();
		format.applyPattern(regex);
		return format.parse(str);
	}

}
