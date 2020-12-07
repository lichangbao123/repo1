package com.spring.demo.utils;

import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 时间格式转换
 *
 *2019年5月6日
 *
 */
public class DateUtils {
	private final static String YYYY = "yyyy";
	private final static String YYYY_MM_DD = "yyyy-MM-dd";
	private final static String HH_MM_SS = "HH:mm:ss";
	private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String MM = "MM";
	public final static String DD = "dd";
	/**
	 * 构造函数
	 */
	public DateUtils() {
	}

	/**
	 * 日期格式化－将Date类型的日期格式化为String型
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			return getFormatter(pattern).format(date);
		}
	}
	/**
	 * 默认把日期格式化成yyyy-mm-dd格式
	 */
	public static String format(Date date) {
		if (date == null) {
			return "";
		} else {
			return getFormatter(YYYY_MM_DD).format(date);
		}
	}
	/**
	 * 把字符串日期默认转换为yyyy-mm-dd格式的Date对象
	 */
	public static Date format(String strDate) {
		Date d = null;
		if (StringUtils.isEmpty(strDate)) {
			try {
				d = getFormatter(YYYY_MM_DD).parse(strDate);
			} catch (ParseException pex) {
				return d;
			}
		}
		return d;
	}
	/**
	 * 把字符串日期转换为f指定格式的Data对象
	 */
	public static Date format(String strDate, String f) {
		Date d = null;
		if (StringUtils.isEmpty(strDate)) {
			try {
				d = getFormatter(f).parse(strDate);
			} catch (ParseException pex) {
				return d;
			}
		}
		return d;
	}
	/**
	 * 日期解析－将String类型的日期解析为Date型
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		try {
			return getFormatter(pattern).parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException("Method parse in Class DateUtils  err: parse strDate fail.", pe.getErrorOffset());
		}
	}
	/**
	 * 获取当前日期
	 */
	public static synchronized Date getCurrDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	/**
	 * 获得当前日期
	 */
	public static String getCurrDateStr_() {
		return format(getCurrDate(), YYYY_MM_DD);
	}
	/**
	 * 获取当前时间
	 */
	public static String getCurrTimeStr() {
		return format( getCurrDate(), HH_MM_SS);
	}
	/**
	 * 获取当前完整时间,样式: yyyy－MM－dd hh:mm:ss
	 */
	public static String getCurrDateTimeStr() {
		return format(getCurrDate(), YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 获取当前年分 样式：yyyy
	 */
	public static String getYear() {
		return format(getCurrDate(), YYYY);
	}
	/**
	 * 获取当前日期号 样式：MM
	 * @return 
	 */
	public static String getMonth() {
		return format(getCurrDate(), MM);
	}
	/**
	 * 获取当前日期号 样式：dd
	 * @return 
	 */
	public static String getDay() {
		return format(getCurrDate(), DD);
	}
	
	
	/**
	 * 按给定日期样式判断给定字符串是否为合法日期数据
	 */
	public static boolean isDate(String strDate, String pattern) {
		try {
			parse(strDate, pattern);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}
	/**
	 * 判断给定字符串是否为特定格式年份（格式：yyyy）数据
	 */
	public static boolean isYYYY(String strDate) {
		try {
			parse(strDate, YYYY);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}
	/**
	 * 获取一个简单的日期格式化对象
	 */
	private static SimpleDateFormat getFormatter(String parttern) {
		return new SimpleDateFormat(parttern);
	}
	
	
	public static String getCurrDateS() {
		StringBuffer now_ = new StringBuffer();
		now_.append(getYear());
		now_.append(getMonth());
		now_.append(getDay());
		return now_.toString();
	}
}
