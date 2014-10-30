package com.lerx.sys.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import com.lerx.sys.util.vo.YearBELong;
import com.opensymphony.xwork2.ActionSupport;

public class TimeUtil {
	
	//返回某一年的首尾时间戳
	public static YearBELong cov(int year){
		 Calendar c = Calendar.getInstance(); 
		 c.set(Calendar.YEAR, year);
		 c.set(Calendar.MONTH, 0); 
        c.set(Calendar.DATE, 1); 
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        YearBELong ybel=new YearBELong();
        ybel.setB(c.getTimeInMillis());
        c.set(Calendar.MONTH, 11); 
        c.set(Calendar.DATE, 31);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        ybel.setE(c.getTimeInMillis());
        return ybel;
	}
	
	//将long变成字符串
	public static String coverLongToStr(Long t){
		if (t==0L){
			return "";
		}
//		System.out.println("aaa");
		Timestamp tt=new java.sql.Timestamp(t);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(tt);
	}
	
	//同上
	public String covLongToStr(Long t){
		if (t==0L){
			return "";
		}
//		System.out.println("aaa");
		Timestamp tt=new java.sql.Timestamp(t);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(tt);
	}
	
	public static Timestamp getCurTime() {
		return new Timestamp(new java.util.Date().getTime());
	}

	public static Date getCurDate() {
		return new Date(new java.util.Date().getTime());
	}

	public static Date getYesterday() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);

		return new Date(c.getTime().getTime());
	}

	public static String getDate(Timestamp ts) {
		if (ts == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(ts);

		return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月"
				+ c.get(Calendar.DAY_OF_MONTH) + "日";
	}

	public static String getDate(Date date) {
		if (date == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月"
				+ c.get(Calendar.DAY_OF_MONTH) + "日";
	}

	public static String getTimestamp(Timestamp ts) {
		if (ts == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(ts);

		return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月"
				+ c.get(Calendar.DAY_OF_MONTH) + "日" + " "
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
				+ ":" + c.get(Calendar.SECOND);
	}

	public static String getSQLTimestamp(Timestamp ts) {
		if (ts == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(ts);

		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DAY_OF_MONTH) + "-" + " "
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
				+ ":" + c.get(Calendar.SECOND);
	}

	public static String getCurTimeString() {
		java.util.Date date = new java.util.Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return "" + c.get(Calendar.YEAR) + (c.get(Calendar.MONTH) + 1)
				+ c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR_OF_DAY)
				+ c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);
	}

	public static Timestamp createTimestamp(int year, int month, int day,
			int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day, hour, minute, second);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Date createDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day);
		return new Date(c.getTimeInMillis());
	}

	public static Timestamp testCreateTimestamp(String str, String format,
			int datePostion) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(datePostion);
		// df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		df.setLenient(false);
		try {
			df.parse(str.toString(), pos);
			Timestamp a = Timestamp.valueOf(str);
			return a;
		} catch (Exception e) {
			return null;
		}
	}

	public static String createCurrTimestampStr(String format) {
		DateFormat f = new SimpleDateFormat(format);
		return f.format(new Timestamp(new java.util.Date().getTime()));
		// retrun new Timestamp(new java.util.Date().getTime());
	}

	public static boolean testCreateDate(String format, int datePostion,
			int year, int month, int day) {
		try {
			DateFormat dataFormat = new SimpleDateFormat(format);
			ParsePosition pos = new ParsePosition(datePostion);
			dataFormat.setLenient(false);
			dataFormat.parse(year + "-" + month + "-" + day, pos);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getJavaScriptTimeString(Timestamp ts) {
		if (ts == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(ts);

		return "new Date(" + c.get(Calendar.YEAR) + "," + c.get(Calendar.MONTH)
				+ "," + c.get(Calendar.DAY_OF_MONTH) + ","
				+ c.get(Calendar.HOUR_OF_DAY) + "," + c.get(Calendar.MINUTE)
				+ "," + c.get(Calendar.SECOND) + ")";
	}

	public static String getTimestampVar(Timestamp ts, int arg) {
		if (ts == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(ts);
		switch (arg) {
		case 1:
			return c.get(Calendar.MONTH) + 1 + "";
		case 2:
			return c.get(Calendar.YEAR) + "";
		default:
			return c.get(Calendar.DAY_OF_MONTH) + "";
		}

	}

	public static String getDateVar(Date date, int arg) {
		if (date == null)
			return "";

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch (arg) {
		case 1:
			return c.get(Calendar.MONTH) + 1 + "";
		case 2:
			return c.get(Calendar.YEAR) + "";
		default:
			return c.get(Calendar.DAY_OF_MONTH) + "";
		}

	}

	public static String getWeekDay(Date date) {
		java.text.SimpleDateFormat FormatterWeekDay = new SimpleDateFormat("E");
		String weekDay = FormatterWeekDay.format(date).trim();
		if (weekDay.equals("Sun")) {
			weekDay = "星期日";
		} else if (weekDay.equals("Mon")) {
			weekDay = "星期一";
		} else if (weekDay.equals("Tue")) {
			weekDay = "星期二";
		} else if (weekDay.equals("Wed")) {
			weekDay = "星期三";
		} else if (weekDay.equals("Thu")) {
			weekDay = "星期四";
		} else if (weekDay.equals("Fri")) {
			weekDay = "星期五";
		} else if (weekDay.equals("Sat")) {
			weekDay = "星期六";
		}
		return weekDay;
	}

	public static String timeSpan(ActionSupport as, long timeNum) {
		long nowTimeNum = System.currentTimeMillis();
		long ltmp;
		long timeSpan = nowTimeNum - timeNum;
		ltmp = Long.parseLong("31536000000");
		int yearsSpan = (int) (timeSpan / ltmp);
		ltmp = Long.parseLong("2592000000");
		int monthsSpan = (int) (timeSpan / ltmp);
		ltmp = Long.parseLong("86400000");
		int daysSpan = (int) (timeSpan / ltmp);
		ltmp = Long.parseLong("3600000");
		int hoursSpan = (int) (timeSpan / ltmp);
		ltmp = Long.parseLong("60000");
		int minutesSpan = (int) (timeSpan / ltmp);
		ltmp = Long.parseLong("1000");
		int secondsSpan = (int) (timeSpan / ltmp);
		if (yearsSpan > 0) {
			return yearsSpan + as.getText("lerx.bbsThemeDoForYearsAgoTxt");
		} else if (monthsSpan > 0) {
			return monthsSpan + as.getText("lerx.bbsThemeDoForMonthsAgoTxt");
		} else if (daysSpan > 0) {
			return daysSpan + as.getText("lerx.bbsThemeDoForDaysAgoTxt");
		} else if (hoursSpan > 0) {
			return hoursSpan + as.getText("lerx.bbsThemeDoForHoursAgoTxt");
		} else if (minutesSpan > 0) {
			return minutesSpan + as.getText("lerx.bbsThemeDoForMinutesAgoTxt");
		} else {
			return secondsSpan + as.getText("lerx.bbsThemeDoForSecondsAgoTxt");
		}
	}

	public static Timestamp stringToTimestamp(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		try {
			Date date = (Date) sdf.parse(dateStr);
			date.getTime();
			cal.setTime(date);
			return new Timestamp(cal.getTimeInMillis());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		// cal.setTime(new Date());
		// return new Timestamp(cal.getTimeInMillis());
	}

	// 判断两个日期是否在同一周
	public boolean isSameWeek(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (subYear == 0) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}

		} else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}

		} else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}

		}
		return false;
	}

	// 按日期取该日期所在周的第一天
	public static java.util.Date firstDayAtWeek(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()); // Monday
		return cal.getTime();
	}

	// 按日期取该日期所在周的某一天
	public static java.util.Date dayAtWeek(java.util.Date date, int dn) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, dn); // Monday
		return cal.getTime();
	}

	// 取年、月 、周的整数及前偏移
	public static int timeNum(String key, int offset) {
		int timeKey;
		if (key == null) {
			return 0;
		}
		if (key.trim().equalsIgnoreCase("curYear")) {
			java.text.SimpleDateFormat formatterYear = new java.text.SimpleDateFormat(
					"yyyy");
			String curYear = formatterYear.format(new Timestamp(System
					.currentTimeMillis()));
			timeKey = Integer.valueOf(curYear) - offset;

		} else if (key.trim().equalsIgnoreCase("curMonth")) {
			String month;
			java.text.SimpleDateFormat formatterMonth = new java.text.SimpleDateFormat(
					"yyyyMM");
			if (offset > 0) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MONDAY, cal.get(Calendar.MONDAY) - offset);
				month = formatterMonth.format(cal.getTime());
				timeKey = Integer.valueOf(month);
			} else {
				month = formatterMonth.format(new Timestamp(System
						.currentTimeMillis()));
				timeKey = Integer.valueOf(month);
			}

		} else if (key.trim().equalsIgnoreCase("curWeek")) {
			String week;
			java.text.SimpleDateFormat formatterWeek = new java.text.SimpleDateFormat(
					"yyyyMMdd");
			if (offset > 0) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, (0 - offset) * 7);
				java.util.Date weekStamp = TimeUtil.firstDayAtWeek(new Date(cal
						.getTimeInMillis()));
				week = formatterWeek.format(new java.sql.Timestamp(weekStamp
						.getTime()));
				timeKey = Integer.valueOf(week);
			} else {
				java.util.Date weekStamp = TimeUtil.firstDayAtWeek(new Date(
						System.currentTimeMillis()));
				week = formatterWeek.format(new java.sql.Timestamp(weekStamp
						.getTime()));
				timeKey = Integer.valueOf(week);
			}

		} else {
			timeKey = 0;
		}
		return timeKey;
	}

	public static int weekTimeNum(int offset, int dn) {
		int timeKey;
		String week;
		java.text.SimpleDateFormat formatterWeek = new java.text.SimpleDateFormat(
				"yyyyMMdd");
		if (offset > 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, (0 - offset) * 7);
			java.util.Date weekStamp = TimeUtil.dayAtWeek(
					new Date(cal.getTimeInMillis()), dn);
			week = formatterWeek.format(new java.sql.Timestamp(weekStamp
					.getTime()));
			timeKey = Integer.valueOf(week);
		} else {
			java.util.Date weekStamp = TimeUtil.firstDayAtWeek(new Date(System
					.currentTimeMillis()));
			week = formatterWeek.format(new java.sql.Timestamp(weekStamp
					.getTime()));
			timeKey = Integer.valueOf(week);
		}
		return timeKey;
	}

	// 时间转换成日期后相减，得天数
	public static int daysSubByTimestamp(Timestamp ta, Timestamp tb) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyyMMdd");
		int da = Integer.valueOf(formatter.format(ta));
		int db = Integer.valueOf(formatter.format(tb));
		return db - da;
	}

	public static int covTimeToDateInt(Timestamp t) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyyMMdd");
		int d = Integer.valueOf(formatter.format(t));
		return d;
	}

	// 时间计算
	public static Timestamp cal(Timestamp t, int pos, int value) {
//		System.out.println("计算前:"+t.toString()+"  pos:"+pos + "  vakye:"+value);
		Calendar cal = Calendar.getInstance();
		cal.setTime(t);
		switch (pos) {
		
		case 1:		//天
			cal.add(Calendar.YEAR, value);
			break;
		case 2:		//月
			cal.add(Calendar.MONTH, value);
			break;
		case 3:		//日
			cal.add(Calendar.DATE, value);
			break;
		case 4:		//小时
			cal.add(Calendar.HOUR, value);
			break;
		case 5:		//分
			cal.add(Calendar.MINUTE, value);
			break;
		case 6:		//秒
			cal.add(Calendar.SECOND, value);
			break;
		default:
			break;
		}
		t.setTime(cal.getTimeInMillis());
//		System.out.println("计算后:"+t.toString());
		return t;
	}

}
