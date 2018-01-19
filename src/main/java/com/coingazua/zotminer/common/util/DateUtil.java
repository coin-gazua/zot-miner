package com.coingazua.zotminer.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	private final static ZoneId defaultZoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofHours(9));

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private final static DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

	public static Date getToday() {
		return Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
	}

	public static Date getToday(int delta) {
		return Date.from(LocalDate.now().plusDays(delta).atStartOfDay(defaultZoneId).toInstant());
	}

	public static Date getTodayDateTime(int delta) {
		return Date.from(LocalDateTime.now().plusDays(delta).atZone(defaultZoneId).toInstant());
	}

	public static Date getTodayDateTime() {
		return getTodayDateTime(0);
	}

	public static String getString(Date date) {
		return simpleDateFormat.format(date);
	}

	public static String getString(LocalDate localDate) {
		return localDate.format(defaultDateTimeFormatter);
	}

	public static Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(defaultZoneId).toInstant());
	}

	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(defaultZoneId).toInstant());
	}

	public static LocalDate toLocalDate(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), defaultZoneId).toLocalDate();
	}

	public static String getDateTimeStringByParttern(String pattern){
		return new SimpleDateFormat(pattern).format(Date.from(LocalDateTime.now().atZone(defaultZoneId).toInstant()));
	}
}
