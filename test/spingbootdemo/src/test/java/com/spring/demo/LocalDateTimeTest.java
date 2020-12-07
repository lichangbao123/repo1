package com.spring.demo;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@SuppressWarnings("unused")
public class LocalDateTimeTest {
	/**
	 * 	其他修饰符 静态修饰符 发牛只类型 方法名 （参数类型 参数名）{方法体}
	 * 
	 * Date如果不格式化，打印出的日期可读性差 ，使用SimpleDateFormat对时间进行格式化，但SimpleDateFormat是线程不安全的 
	 * LocalDate可读性强，和SimpleDateFormat相比，DateTimeFormatter是线程安全的
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		System.out.println(UUID.randomUUID());
//		System.out.println(LocalDate.now());
//		System.out.println(LocalTime.now());
//		System.out.println(LocalDateTime.now());
//		System.out.println("---");
		/**
		 * LocalDate  获取当前年月日
		 */
		//获取当前年月日
		LocalDate localDate = LocalDate.now();
		//构造指定的年月日
		LocalDate localDate1 = LocalDate.of(2019, 9, 10);
//		System.out.println(localDate);
//		System.out.println(localDate1);
		
		//获取年、月、日、星期几
		int year = localDate.getYear();
		int year1 = localDate.get(ChronoField.YEAR);
		Month month = localDate.getMonth();
		int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
		int day = localDate.getDayOfMonth();
		int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
//		System.out.println("---");
//		System.out.println(year);
//		System.out.println(year1);
//		System.out.println(month);
//		System.out.println(month1);
//		System.out.println(day);
//		System.out.println(day1);
//		System.out.println(dayOfWeek);
//		System.out.println(dayOfWeek1);
		/**
		 * LocalTime  获取当前时分秒
		 */
		LocalTime localtime=LocalTime.now();
//		System.out.println(localtime);
		LocalTime localtime1=LocalTime.of(13, 14, 15);
//		System.out.println(localtime1);
		
//		System.out.println(localtime.get(ChronoField.HOUR_OF_DAY));
//		System.out.println(localtime.getHour());
//		System.out.println(localtime.getMinute());
//		System.out.println(localtime.get(ChronoField.MINUTE_OF_HOUR));//当前小时内第几分钟
//		System.out.println(localtime.get(ChronoField.MINUTE_OF_DAY));//当天第几分钟
//		System.out.println(localtime.getSecond());
//		System.out.println(localtime.get(ChronoField.SECOND_OF_MINUTE));//当前分钟内第几秒
//		System.out.println(localtime.get(ChronoField.SECOND_OF_DAY));//当天第几秒
		/**
		 * LocalDateTime =LocalDate+LocalTime 获取年月日时分秒
		 */
		LocalDateTime localDateTime=LocalDateTime.now();
//		System.out.println(localDateTime);
//		System.out.println(LocalDateTime.of(localDate, localtime));
//		System.out.println(localDateTime.of(2019, Month.OCTOBER, 12, 9, 0, 0));
//		System.out.println(localDate.atTime(localtime));
//		System.out.println(localtime.atDate(localDate));
//		System.out.println(localDateTime.toLocalDate());
//		System.out.println(localDateTime.toLocalTime());
		/**
		 * Instant
		 */
		Instant instant=Instant.now();
//		System.out.println(instant);
//		System.out.println(instant.getEpochSecond());//获取秒数
//		System.out.println(instant.toEpochMilli());//获取毫秒数
//		System.out.println(System.currentTimeMillis());//获取毫秒数
		/**
		 * 	增加、减少年月日 时分秒 周      修改年月日
		 */
//		System.out.println(localDate.plusYears(1));
//		System.out.println(localDate.plusMonths(1));
//		System.out.println(localDate.plus(1, ChronoUnit.MONTHS));
//		System.out.println(localDate.plusDays(1));
//		System.out.println(localDate.minusYears(1));
//		System.out.println(localDate.minusMonths(1));
//		System.out.println(localDate.minusDays(1));

//		System.out.println(localDateTime.plusYears(2));
//		System.out.println(localDateTime.plusMonths(2));
//		System.out.println(localDateTime.plusDays(2));
//		System.out.println(localDateTime.plusHours(2));
//		System.out.println(localDateTime.plusMinutes(2));
//		System.out.println(localDateTime.plusSeconds(2));
//		System.out.println(localDateTime.minusYears(2));
//		System.out.println(localDateTime.minusMonths(2));
//		System.out.println(localDateTime.minusHours(2));
//		System.out.println(localDateTime.minusMinutes(2));
//		System.out.println(localDateTime.minusSeconds(2));
//		System.out.println(localDateTime.minusWeeks(2));
		
//		System.out.println(localDate.withYear(2022));
//		System.out.println(localDate.with(ChronoField.MONTH_OF_YEAR, 1));
		/**
		 *	 格式化时间
		 */
		//LocalDate localDate = LocalDate.of(2019, 9, 10);
		String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
		String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		//自定义格式化
		DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String s3 = LocalDateTime.now().format(dateTimeFormatter);
//		System.out.println(localDate.format(DateTimeFormatter.ISO_DATE));
//		System.out.println(s1);
//		System.out.println(s2);
//		System.out.println(s3);
		
	}
}
