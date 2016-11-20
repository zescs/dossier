package com.zescs.dossier.common.web.date;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.zescs.dossier.common.web.date.domain.DateEntity;
import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: DateUtils
 * @Description: TODO(日期工具类)
 * @author sjth:郑建平
 * @date 2014年5月9日 上午10:01:27
 */
public final class DateUtils {
	private static final String DATE = "yyyy-MM-dd";
	private static final String DATE_LOCAL = "yyyyMMdd";
	private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 
	 * @Title: checkPattern @Description: TODO(检查格式化字符串) @param @param
	 *         pattern @return String @throws
	 */
	private static String checkPattern(String pattern) {
		if (pattern == null || pattern.equals("") || pattern.trim().length() == 0) {
			return DatePattern.FORMAT_DAY;
		}
		return pattern;
	}

	/**
	 * 
	 * @Title: parseDate
	 * @Description: TODO(将字符串转换为时间)
	 * @param strDate
	 *            时间字符串
	 * @param pattern
	 *            时间格式串
	 * @return Date
	 * @throws 转换失败返回当前时间
	 */
	public static Date parseDate(String strDate, String pattern) {
		// 用户传入格式化字符串不正确的时候，自动修正格式化字符串
		pattern = checkPattern(pattern);
		try {
			String dateValue = strDate.trim();
			int length = dateValue.length();
			DateFormat format = new SimpleDateFormat(pattern);
			if (length <= 8) {
				format = new SimpleDateFormat(DATE_LOCAL, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			if (length <= 10) {
				format = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			if (length <= 19) {
				format = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			if (length <= 23) {
				format = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			return format.parse(strDate);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static Date parseLocaleDate(String strDate) {
		// 用户传入格式化字符串不正确的时候，自动修正格式化字符串
		try {
			String dateValue = strDate.trim();
			int length = dateValue.length();
			DateFormat format = new SimpleDateFormat(R.Pattern.DATE_FORMAT_DEFAULT);
			if (length <= 8) {
				format = new SimpleDateFormat(DATE_LOCAL, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			if (length <= 10) {
				format = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			if (length <= 19) {
				format = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			if (length <= 23) {
				format = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
				return format.parse(dateValue);
			}
			return format.parse(strDate);
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 
	 * @Title: parseDate
	 * @Description: TODO(将字符串转换为时间,默认时间格式化字符串:"yyyy-MM-dd")
	 * @param strDate
	 *            时间字符串
	 * @return Date
	 * @throws 转换失败返回当前时间
	 */
	public static Date parseDate(String strDate) {
		return parseDate(strDate, null);
	}

	/**
	 * 
	 * @Title: format
	 * @Description: TODO(将时间格式化为指定的格式)
	 * @param srcDate
	 *            操作的时间
	 * @param pattern
	 *            格式化字符串
	 * @return String
	 * @throws 转换失败返回null
	 */
	public static String format(Date srcDate, String pattern) {
		// 用户传入格式化字符串不正确的时候，自动修正格式化字符串
		pattern = checkPattern(pattern);
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.format(srcDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: format
	 * @Description: TODO(将时间格式化为指定的格式,默认格式化字符串:"yyyy-MM-dd")
	 * @param srcDate
	 *            操作的时间
	 * @return String
	 * @throws 转换失败返回null
	 */
	public static String format(Date srcDate) {
		return format(srcDate, null);
	}

	public static String format(String srcDate, String pattern) {
		Date date = DateUtils.parseDate(srcDate, pattern);
		return DateUtils.format(date, pattern);
	}

	public static String format(String srcDate, String patternStr, String patternDate) {
		Date date = DateUtils.parseDate(srcDate, patternDate);
		return DateUtils.format(date, patternStr);
	}

	/**
	 * 
	 * @Title: dayDifference
	 * @Description: TODO(计算两个时间相差的天数)
	 * @param operatednDate
	 * @param operationDate
	 * @return Integer
	 * @throws 异常返回null
	 */
	public static Integer dayDifference(Date operatednDate, Date operationDate) {
		try {
			// 计算时间差
			Long difference = DateUtils.getDifference(operatednDate, operationDate);
			// 想差的天数
			Integer day = new Long(difference / (1000 * 60 * 60 * 24)).intValue();
			return day;
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer monthDifference(Date operatednDate, Date operationDate) {
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(operationDate);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(operatednDate);
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
				- cal2.get(Calendar.MONTH);
		return c;
	}

	public static Integer yearDifference(Date operatednDate, Date operationDate) {
		Integer month = DateUtils.monthDifference(operatednDate, operationDate);
		Integer year = month / 12;
		return year;
	}

	private static Long getDifference(Date operatednDate, Date operationDate) {
		try {
			Calendar calendarOne = Calendar.getInstance();
			calendarOne.setTime(operatednDate);
			Calendar calendarTwo = Calendar.getInstance();
			calendarTwo.setTime(operationDate);
			// 获取两个时间的毫秒数
			Long timeOne = calendarOne.getTimeInMillis();
			Long timeTwo = calendarTwo.getTimeInMillis();
			// 计算时间差
			Long difference = timeTwo - timeOne;
			return difference;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: dayDifference
	 * @Description: TODO(计算两个时间相差的小时)
	 * @param operatednDate
	 * @param operationDate
	 * @return Integer
	 * @throws 异常返回null
	 */
	public static Integer hhDifference(Date operatednDate, Date operationDate) {
		try {
			Integer day = DateUtils.dayDifference(operatednDate, operationDate);
			Long diff = DateUtils.getDifference(operatednDate, operationDate);
			Integer hh = new Long(diff % DatePattern.DD / DatePattern.HH + day * 24).intValue();
			return hh;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: dayDifference
	 * @Description: TODO(计算两个时间相差的分钟)
	 * @param operatednDate
	 * @param operationDate
	 * @return Integer
	 * @throws 异常返回null
	 */
	public static Integer minDifference(Date operatednDate, Date operationDate) {
		try {
			Integer day = DateUtils.dayDifference(operatednDate, operationDate);
			Long diff = DateUtils.getDifference(operatednDate, operationDate);
			Integer mm = new Long(diff % DatePattern.DD % DatePattern.HH + day * 24 * 60).intValue();
			return mm;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: dayDifference
	 * @Description: TODO(计算两个时间相差的分钟)
	 * @param operatednDate
	 * @param operationDate
	 * @return Integer
	 * @throws 异常返回null
	 */
	public static Integer secDifference(Date operatednDate, Date operationDate) {
		try {
			Long diff = DateUtils.getDifference(operatednDate, operationDate);
			Integer ss = new Long(diff % DatePattern.DD % DatePattern.HH % DatePattern.MM / DatePattern.SS).intValue();
			return ss;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: dayDifference
	 * @Description: TODO(计算两个时间的时间差)
	 * @param operatednDateStr
	 * @param operationDateStr
	 * @return Integer 返回类型
	 * @throws 异常返回null
	 */
	public static Integer dayDifference(String operatednDateStr, String operationDateStr, String pattern) {
		// 用户传入格式化字符串不正确的时候，自动修正格式化字符串
		pattern = checkPattern(pattern);
		return dayDifference(DateUtils.parseDate(operatednDateStr, pattern),
				DateUtils.parseDate(operationDateStr, pattern));
	}

	/**
	 * 
	 * @Title: dayDifference
	 * @Description: TODO(计算两个时间的时间差)
	 * @param operatednDateStr
	 * @param operationDateStr
	 * @return Integer 返回类型
	 * @throws 异常返回null
	 */
	public static Integer dayDifference(String operatednDateStr, String operationDateStr) {
		// 用户传入格式化字符串不正确的时候，自动修正格式化字符串
		return dayDifference(DateUtils.parseDate(operatednDateStr), DateUtils.parseDate(operationDateStr));
	}

	/**
	 * 
	 * @Title: dayDifference @Description: TODO(计算两个时间的时间差) @param
	 *         operatednDateStr @param operationDate @return int 返回类型 @throws
	 */
	public static Integer dayDifference(String operatednDateStr, Date operationDate) {
		return dayDifference(DateUtils.parseDate(operatednDateStr), operationDate);
	}

	/**
	 * 
	 * @Title: dayDifference @Description: TODO(计算两个时间的时间差) @param
	 *         operatednDateStr @param operationDate @return int 返回类型 @throws
	 */
	public static Integer dayDifference(String operatednDateStr, Date operationDate, String pattern) {
		pattern = checkPattern(pattern);
		return dayDifference(DateUtils.parseDate(operatednDateStr, pattern), operationDate);
	}

	/**
	 * 
	 * @Title: dayDifference @Description: TODO(计算两个时间的时间差) @param
	 *         operatednDate @param string @return int @throws
	 */
	public static Integer dayDifference(Date operatednDate, String operationDateStr, String pattern) {
		pattern = checkPattern(pattern);
		return dayDifference(operatednDate, DateUtils.parseDate(operationDateStr, pattern));
	}

	/**
	 * 
	 * @Title: dayDifference @Description: TODO(计算两个时间的时间差) @param
	 *         operatednDate @param string @return int @throws
	 */
	public static Integer dayDifference(Date operatednDate, String operationDateStr) {
		return dayDifference(operatednDate, DateUtils.parseDate(operationDateStr));
	}

	/**
	 * 检测一个字符串是否能够转换时间
	 * 
	 * @param date
	 * @return
	 */
	public static Boolean isDate(String date) {
		DateFormat format = new SimpleDateFormat(DatePattern.FORMAT_DAY);
		try {
			format.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String format(String srcDate) {
		return DateUtils.format(srcDate, null);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getYear(Date date) {
		DateFormat format = new SimpleDateFormat(DatePattern.FORMAT_YY);
		String tempDate = format.format(date);
		return Integer.parseInt(tempDate);
	}

	public static Integer getYear(String date) {
		try {
			DateFormat format = new SimpleDateFormat(DatePattern.FORMAT_YY);
			Date temp_date = format.parse(date);
			String tempDate = format.format(temp_date);
			return Integer.parseInt(tempDate);
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer get(String date, int field) {
		try {
			DateFormat format = new SimpleDateFormat(DatePattern.FORMAT_DEFAULT);
			Date temp_date = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp_date);
			return cal.get(field);
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer get(String date, int field, String parttern) {
		try {
			DateFormat format = new SimpleDateFormat(parttern);
			Date temp_date = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp_date);
			return cal.get(field);
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer get(Date date, int field) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(field);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取一年中的第一个月的第一天和最后一个月的最后一天
	 */
	public static DateEntity geDateEntityByYear(Integer year) {
		Date firstDate = geDateEntityByYeaMonth(year, 1).getFirstDate();
		Date lastDate = geDateEntityByYeaMonth(year, 12).getLastDate();
		return new DateEntity(firstDate, lastDate);
	}

	/**
	 * 获取一个月的第一天和最后一个月的最后一天
	 */
	public static DateEntity geDateEntityByYeaMonth(Integer year, Integer month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		return new DateEntity(firstDate, lastDate);
	}

	/**
	 * 获取一个月的第一天和最后一个月的最后一天
	 */
	public static DateEntity geDateEntityLessMonth(Integer year, Integer month) {
		Date firstDate = geDateEntityByYeaMonth(year, 1).getFirstDate();
		Date lastDate = geDateEntityByYeaMonth(year, month).getLastDate();
		return new DateEntity(firstDate, lastDate);
	}
}
