package com.bplow.netconn.base.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;



public class DateUtil {
	private static int curYear;

	private static int curMonth;

	private static int curDay;

	private static String date;

	private static int[] dayOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };

	private static int[] dayOfMonthLeapYear = { 0, 31, 29, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	private static DateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");

	private static DateFormat fmtTime = new SimpleDateFormat("HH:mm:ss");

	private static DateFormat fmtDT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static final String FmtDate = "yyyy-MM-dd";
	
	public static final String FmtDayDate = "yyyyMMdd";

	public static final String FmtTime = "HH:mm:ss";

	public static final String FmtDateTime = "yyyy-MM-dd HH:mm:ss";
	
    /**
     * 返回当前时间的字符串格式
     * @param Date 
     * @return String yyyyMMddHHmmss
     */
    public static String datetoStr() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyyMMddHHmmss");
        String curDate = sdf.format(new Date());
        return curDate;
    }
    /**
     * 返回当前日期 yyyyMMdd
     * @return
     */
    public static String currDayStr(){
    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyyMMdd");
    	 String curDate = sdf.format(new Date());
         return curDate;
    }
    
    
    public static String datetoStr(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        String curDate = sdf.format(date);
        return curDate;
    }
    
    public static String datetoStr2(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
        		FmtDateTime);
        String curDate = sdf.format(date);
        return curDate;
    }
    
    
    /**
     * 返回格式化后的字符串
     * @param date yyyy-mm-dd
     * @return　String yyyymmdd
     */
    public static String dateToStrin(String date)
    {
        StringTokenizer tokenizer = new StringTokenizer(date, "-");
        String datelist = "";
        while(tokenizer.hasMoreElements())
        {
            String tokenlist = (String)tokenizer.nextElement();
            datelist = datelist + tokenlist;
        }
        return datelist;
    }
	/**
	 * 将字符串转换为日期，提供界面层到对象层转换
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param dateFmt
	 *            日期格式
	 * @return
	 */
	public static Date strToDate(String dateStr, String dateFormat)
			throws ParseException {
		if (dateStr == null||"".equals(dateStr.trim()))
			return null;
		DateFormat dt = null;
		if (dateFormat == null) {
			dt = getDateFormat(dateStr);
		} else {
			dt = new SimpleDateFormat(dateFormat);
		}
		return dt.parse(dateStr);
	}

	/**
	 * 将字符串转换为日期，提供界面层到对象层转换,根据传入的日期字符串，分析该字符串的格式
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return
	 */
	public static Date strToDate(String dateStr) throws ParseException {
		return strToDate(dateStr, null);
	}

	/**
	 * 将字符串转换为Calendar
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Calendar strToCalendar(String dateStr) throws ParseException {
		Date date = strToDate(dateStr);
		return dateToCalendar(date);
	}

	/**
	 * 将字符串转换为Calendar,根据传入的日期字符串，分析该字符串的格式
	 * 
	 * @param dateStr
	 * @param dateFmt
	 * @return
	 */
	public static Calendar strToCalendar(String dateStr, String dateFmt)
			throws ParseException {
		Date date = strToDate(dateStr, dateFmt);
		return dateToCalendar(date);
	}

	public static Timestamp strToTimestamp(String dateStr)
			throws ParseException {
		Calendar cal = strToCalendar(dateStr);
		return DateUtil.calToTimeStamp(cal);

	}

	/**
	 * 将calendar转换为timestamp
	 * 
	 * @param cal
	 * @return
	 */
	public static Timestamp calToTimeStamp(Calendar cal) {
		Timestamp tm = null;
		if (cal == null) {
			tm = null;
		} else {
			tm = new Timestamp(cal.getTime().getTime());
		}

		return tm;
		// return (cal == null) ? null : new
		// Timestamp(cal.getTime().getTime());
	}

	public static Timestamp dateToTimeStamp(java.util.Date date) {
		Calendar cal = dateToCalendar(date);
		return calToTimeStamp(cal);
	}

	/**
	 * 将timestamp转换为calendar
	 * 
	 * @param tim
	 * @return
	 */
	public static Calendar timeStampToCal(Timestamp tim) {
		if (tim == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tim);
		return calendar;
	}

	/**
	 * 时间戳转换为Date
	 * 
	 * @param tim
	 * @return
	 */
	public static Date timeStampToDate(Timestamp tim) {
		return timeStampToCal(tim).getTime();
	}

	/**
	 * 解析传入日期得到日期字符串,将来可能会根据不同的需要扩展该函数,适应不同的日期格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static DateFormat getDateFormat(String dateStr) {
		int pos1 = dateStr.indexOf("-");
		int pos2 = dateStr.indexOf(":");
		DateFormat dt = null;
		if (pos1 != -1 && pos2 != -1) {
			dt = fmtDT;
		} else if (pos1 != -1 && pos2 == -1) {
			dt = fmtDate;
		} else if (pos1 == -1 && pos2 != -1) {
			dt = fmtTime;
		} else {
			dt = fmtDate;
		}
		return dt;
	}

    /**
     * @param 李繁昌 2007-04-28
     * @return HH:mm:ss
     */
    public static DateFormat getFmtTime() 
    {
        return fmtTime;
    }
    
	/**
	 * 将日期型数据转换为日历型
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 日历型转换为日期型
	 * 
	 * @param cal
	 * @return
	 */
	public static Date calendarToDate(Calendar cal) {
		return cal.getTime();

	}

	/**
	 * 转换cal为字符串
	 * 
	 * @param cal
	 * @return
	 */

	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		// return cal.getTime().getYear()+1900;
		return cal.get(Calendar.YEAR);

	}

	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH)+1;
	}

	public static int getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DATE);
	}

	/**
	 * 获得当前年月的年月时间字符串
	 * 
	 * @return
	 */
	public static String getCurrentYearMonth() {
		int year = getCurrentYear();
		int month = getCurrentMonth();
		String yearMonth = "";
		if (month >= 10) {
			yearMonth = String.valueOf(year) + String.valueOf(month);
		} else {
			yearMonth = String.valueOf(year) + "0" + String.valueOf(month);
		}
		return yearMonth;
	}

	/**
	 * 获得上月的年月时间字符串
	 * 
	 * @return
	 */
	public static String getPreYearMonth() {
		int year = getCurrentYear();
		int month = getCurrentMonth() - 1;
		String yearMonth = "";
		if (month == 1) {
			year = year - 1;
			month = 12;
		}
		if (month >= 10) {
			yearMonth = String.valueOf(year) + String.valueOf(month);
		} else {
			yearMonth = String.valueOf(year) + "0" + String.valueOf(month);
		}
		return yearMonth;
	}

	/**
	 * 获得当前的周
	 * 
	 * @return
	 */
	public static int getCurrentWeek() {

		return getWeekOfYear(Calendar.getInstance());
	}

	/**
	 * 获得输入的日期是第几周
	 * 
	 * @param cal
	 * @return
	 */
	public static int getWeekOfYear(Calendar cal) {
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获得上一周的周数
	 * 
	 * @return
	 */
	public static int getPriorWeek() {
		// 注意：如果是本年的第一周，则其上一周是上一年的最后一周
		// TODO:还未处理年交换时的问题
		return getCurrentWeek() - 1;

	}

	/**
	 * 获得输入的周的起始日期
	 * 
	 * @param week
	 * @return
	 */
	public static Calendar getWeekStartDate(int year, int week) {
		// 获得当前日期
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, 1);

		return cal;
	}

	/**
	 * 获得一个周的最后一天的日期
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Calendar getWeekEndDate(int year, int week) {
		Calendar cal = getWeekStartDate(year, week);
		// 加上6天得到周末的日期
		cal.add(Calendar.DAY_OF_YEAR, 6);

		return cal;
	}

	/**
	 * 解析年月字符串得到年
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static int paraseYear(String yearMonth) {
		String year = yearMonth.substring(0, 4);

		return Integer.parseInt(year);

	}

	/**
	 * 解析年月字符串得到月
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static int paraseMonth(String yearMonth) {
		String month = yearMonth.substring(4);

		return Integer.parseInt(month);

	}

	/**
	 * 返回当前日期的字符串 格式:20060330
	 * 
	 * @return String YYYYMMDD
	 */
	public static String getCurDate() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		curDay = calendar.get(Calendar.DATE);
		String strDay = null;
		String strMonth = null;
		if (curDay < 10) {
			strDay = "0" + curDay;
		} else {
			strDay = "" + curDay;
		}
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + strMonth + strDay;
		// System.out.println(date);
		return date;
	}

	/**
	 * 返回当前日期的字符串 格式:2006-03-30
	 * 
	 * @return String YYYY-MM-DD
	 */
	public static String getCurDate2() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		curDay = calendar.get(Calendar.DATE);
		String strDay = null;
		String strMonth = null;
		if (curDay < 10) {
			strDay = "0" + curDay;
		} else {
			strDay = "" + curDay;
		}
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + "-" + strMonth + "-" + strDay;
		// System.out.println(date);
		return date;
	}
    
    /**
     * 返回参数日期的字符串 格式:2006-03-30
     * 
     * @return String YYYY-MM-DD
     */
    public static String formatDate(Date d) {
    	if(null == d){
    		return "";
    	}
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH) + 1;
        curDay = calendar.get(Calendar.DATE);
        String strDay = null;
        String strMonth = null;
        if (curDay < 10) {
            strDay = "0" + curDay;
        } else {
            strDay = "" + curDay;
        }
        if (curMonth < 10) {
            strMonth = "0" + curMonth;
        } else {
            strMonth = "" + curMonth;
        }
        date = "" + curYear + "-" + strMonth + "-" + strDay;
        // System.out.println(date);
        return date;
    }

	/**
	 * 返回当前日期前一天的日期字符串 格式:20060329
	 * 
	 * @return String YYYYMMDD
	 */
	public static String getCurLastDate() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		curDay = calendar.get(Calendar.DATE);
		if (curDay == 1) {// 如果是1号,取上一月最后一天
			if (curMonth == 1) {// 如果是1月,上一天为前一年12月31日
				curYear--;
				curMonth = 12;
				curDay = dayOfMonth[curMonth];
			} else if (curMonth == 3) {// 如果是3月,上一天为2月最后一天,需要判断闰年
				curMonth--;
				if (curYearIsLeapYear()) {// 如果为闰年
					curDay = dayOfMonthLeapYear[curMonth];
				} else {// 如果不是闰年
					curDay = dayOfMonth[curMonth];
				}
			}// else if(curMonth==3) end
			else {// 其他月份,月份减1,取得此月份最后一天
				curMonth--;
				curDay = dayOfMonth[curMonth];
			}
		} else {// 如果不是1号,日期-1
			curDay--;
		}
		String strDay = null;
		String strMonth = null;
		if (curDay < 10) {
			strDay = "0" + curDay;
		} else {
			strDay = "" + curDay;
		}
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + strMonth + strDay;
		// System.out.println(date);
		return date;
	}

	/**
	 * 返回当前日期前一天的日期字符串 格式:2006-03-29
	 * 
	 * @return String YYYY-MM-DD
	 */
	public static String getCurLastDate2() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		curDay = calendar.get(Calendar.DATE);
		if (curDay == 1) {// 如果是1号,取上一月最后一天
			if (curMonth == 1) {// 如果是1月,上一天为前一年12月31日
				curYear--;
				curMonth = 12;
				curDay = dayOfMonth[curMonth];
			} else if (curMonth == 3) {// 如果是3月,上一天为2月最后一天,需要判断闰年
				curMonth--;
				if (curYearIsLeapYear()) {// 如果为闰年
					curDay = dayOfMonthLeapYear[curMonth];
				} else {// 如果不是闰年
					curDay = dayOfMonth[curMonth];
				}
			}// else if(curMonth==3) end
			else {// 其他月份,月份减1,取得此月份最后一天
				curMonth--;
				curDay = dayOfMonth[curMonth];
			}
		} else {// 如果不是1号,日期-1
			curDay--;
		}
		String strDay = null;
		String strMonth = null;
		if (curDay < 10) {
			strDay = "0" + curDay;
		} else {
			strDay = "" + curDay;
		}
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + "-" + strMonth + "-" + strDay;
		// System.out.println(date);
		return date;
	}

	/**
	 * 返回当前月份前一月的日期字符串 格式:YYYYMM
	 * 
	 * @return String YYYYMM
	 */
	public static String getCurMonth() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		String strMonth = null;
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + strMonth;
		// System.out.println(date);
		return date;
	}

	/**
	 * 返回当前月份后一月的日期字符串 格式:YYYYMM
	 * 
	 * @return String YYYYMM
	 */
	public static String getNextMonth() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		if (curMonth == 12) {// 如果是12月,后一月为当一年12月
			curYear++;
			curMonth = 1;
		} else {// 其他月份,月份加1
			curMonth++;
		}
		String strMonth = null;
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + strMonth;
		// System.out.println(date);
		return date;
	}
	
	/**
	 * 返回当前月份前一月的日期字符串 格式:YYYYMM
	 * 
	 * @return String YYYYMM
	 */
	public static String getCurLastMonth() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		if (curMonth == 1) {// 如果是1月,上一月为前一年12月
			curYear--;
			curMonth = 12;
		} else {// 其他月份,月份减1,取得此月份最后一天
			curMonth--;
		}
		String strMonth = null;
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + strMonth;
		// System.out.println(date);
		return date;
	}

	/**
	 * 返回当年第一个月的日期字符串 格式:YYYYMM
	 * 
	 * @return String YYYYMM
	 */
	public static String getFirstMonthOfThisYear() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		date = "" + curYear + "01";
		// System.out.println(date);
		return date;
	}

	/**
	 * 根据传入的日期参数(YYYYMMDD)返回前一天的日期字符串
	 * 
	 * @param String
	 *            YYYYMMDD
	 * @return String YYYYMMDD
	 */
	public static String getLastDate(String date) {
		// int curYear;
		int curYear = Integer.valueOf(date.substring(0, 4)).intValue();
		int curMonth = Integer.valueOf(date.substring(4, 6)).intValue();
		int curDay = Integer.valueOf(date.substring(6, 8)).intValue();

		if (curDay == 1) {// 如果是1号,取上一月最后一天
			if (curMonth == 1) {// 如果是1月,上一天为前一年12月31日
				curYear--;
				curMonth = 12;
				curDay = dayOfMonth[curMonth];
			} else if (curMonth == 3) {// 如果是3月,上一天为2月最后一天,需要判断闰年
				curMonth--;
				if (isLeapYear(curYear)) {// 如果为闰年
					curDay = dayOfMonthLeapYear[curMonth];
				} else {// 如果不是闰年
					curDay = dayOfMonth[curMonth];
				}
			}// else if(curMonth==3) end
			else {// 其他月份,月份减1,取得此月份最后一天
				curMonth--;
				curDay = dayOfMonth[curMonth];
			}
		} else {// 如果不是1号,日期-1
			curDay--;
		}
		String strDay = null;
		String strMonth = null;
		if (curDay < 10) {
			strDay = "0" + curDay;
		} else {
			strDay = "" + curDay;
		}
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + strMonth + strDay;
		System.out.println(date);
		return date;
	}

	/**
	 * 根据传入的日期参数YYYY-MM-DD或YYYYMMDD返回前一天的日期字符串
	 * 
	 * @param String
	 *            YYYY-MM-DD or YYYYMMDD
	 * @return String YYYY-MM-DD
	 */
	public static String getLastDate2(String date) {
		if(date.length()==8){
			date = getLastDate(date);
		}else{
			date = getLastDate(getYYYYMMDD(date));
		}
		date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
				+ date.substring(6);
		return date;
	}
	
	/**
	 * 根据传入的日期字符串，返回前一周的日期字符串
	 * @param String YYYYWW
	 * @return String YYYYWW
	 */
	public static String getLastWeek(String date) {
		// int curYear;
		int year = Integer.valueOf(date.substring(0, 4)).intValue();
		int week = Integer.valueOf(date.substring(4, 6)).intValue();
		if(week == 1){//如果是第一周，则年数-1，	取得上年最后一周
			year--;
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(year,11,31);//上一年的12月31日
			week = cal.get(Calendar.WEEK_OF_YEAR); //得到上一年12月31日所在的周数
		}else{//如果不是第一周，则周数-1
			week--;
		}
		return "" + year + formatLessThanTen(week);
	}

	/**
	 * 根据传入的日期字符串，返回前一月的日期字符串
	 * @author 马亮
	 * @param String YYYYMM
	 * @return String YYYYMM
	 */
	public static String getLastMonth(String date) {
		// int curYear;
		int year = Integer.valueOf(date.substring(0, 4)).intValue();
		int month = Integer.valueOf(date.substring(4, 6)).intValue();
		if(month == 1){//如果是第一月，则为上年12月
			year--;
			month = 12;
		}else{//如果不是第一月，则月数-1
			month--;
		}
		return "" + year + formatLessThanTen(month);
	}
	
	/**
	 * 根据传入的日期字符串，返回前一季的日期字符串
	 * @author 马亮
	 * @param String YYYYQQ
	 * @return String YYYYQQ
	 */
	public static String getLastQtr(String date) {	
		// int curYear;
		int year = Integer.valueOf(date.substring(0, 4)).intValue();
		int qtr = Integer.valueOf(date.substring(4, 6)).intValue();
		if(qtr == 1){//如果是第一季，则为上年4季
			year--;
			qtr = 4;
		}else{//如果不是第一季，则季度数-1
			qtr--;
		}
		return "" + year + formatLessThanTen(qtr);
	}
	
	/**
	 * 根据传入的日期(Calendar)返回前一天的日期字符串
	 * @author 马亮
	 * @param Calendar
	 * @return String YYYY-MM-DD
	 */
	public static String getLastDate(Calendar cal) {
		// int curYear;
		cal.add(Calendar.DATE, -1);
		return ""+cal.get(Calendar.YEAR)+"-"+formatLessThanTen(cal.get(Calendar.MONTH)+1)+"-"+formatLessThanTen(cal.get(Calendar.DATE));
	}
	
	/**
	 * 根据传入的日期(Calendar)返回前一周的日期字符串
	 * @param Calendar
	 * @return String YYYYWW
	 */
	public static String getLastWeek(Calendar cal) {
		// int curYear;
		cal.add(Calendar.DATE, -7);
		return ""+cal.get(Calendar.YEAR)+formatLessThanTen(cal.get(Calendar.WEEK_OF_YEAR));
	}
	
	/**
	 * 根据传入的日期(Calendar)返回前一月的日期字符串
	 * @author 马亮
	 * @param Calendar
	 * @return String YYYYMM
	 */
	public static String getLastMonth(Calendar cal) {
		// int curYear;
		cal.add(Calendar.MONTH, -1);
		return ""+cal.get(Calendar.YEAR)+formatLessThanTen((cal.get(Calendar.MONTH)+1));
	}
    
    /**
     * 返回当前日期的前一月的日期字符串
     * @param Calendar
     * @return String YYYY-MM
     */
    public static String getLastMonth2() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return ""+calendar.get(Calendar.YEAR)+"-"+formatLessThanTen((calendar.get(Calendar.MONTH)+1));
    }
    
	/**
	 * 根据传入的日期(Calendar)返回前一季的日期字符串
	 * @param Calendar
	 * @return String YYYYQQ
	 */
	public static String getLastQtr(Calendar cal) {
		// int curYear;
		cal.add(Calendar.MONTH, -3);
		int mon = cal.get(Calendar.MONTH);
		int qtr = 0;
		qtr = mon/3 + 1;
		return ""+cal.get(Calendar.YEAR)+formatLessThanTen(qtr);
	}
	
	/**
	 * 根据传入的日期(Calendar)返回前一年的日期字符串
	 * @param Calendar
	 * @return String YYYY
	 */
	public static String getLastYear(Calendar cal) {
		// int curYear;
		cal.add(Calendar.YEAR, -1);
		return ""+cal.get(Calendar.YEAR);
	}
	
	/**
	 * 根据传入的日期返回当前月第一天的时间字符串
	 * @param cal
	 * @return YYYYMM01
	 */
	public static String getFirstDayOfMonth(Calendar cal){
		return cal.get(Calendar.YEAR)+(cal.get(Calendar.MONTH)+1)+"01";
	}
	
	/**
	 * 根据传入的日期返回当年第一个周、月、季的时间字符串
	 * @param cal
	 * @return YYYY01
	 */
	public static String getFirstWmqOfYear(Calendar cal){
		return cal.get(Calendar.YEAR)+"01";
	}
	
	/**
	 * 获得2003年初到上一周为止的周字符串列表
	 * @return List YYYYWW
	 */
    public static List getListOfWeek(){
		List result = new ArrayList();
		Calendar cur = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 2003);
		cal.set(Calendar.DATE, 1);
		while(cal.before(cur)){
			result.add("" + cal.get(Calendar.YEAR) + formatLessThanTen(cal.get(Calendar.WEEK_OF_YEAR)));
			cal.add(Calendar.WEEK_OF_YEAR, 1);
		}
		return result;
	}
	
	/**
	 * 获得2003年一月到上一月为止的月字符串列表
	 * @return List YYYYMM
	 */
	public static List getListOfMonth(){
		List result = new ArrayList();
		Calendar cur = Calendar.getInstance();
		result = getYearMonths(2003,01,cur.get(Calendar.YEAR),cur.get(Calendar.MONTH));
		return result;
	}
	
    public static List getListOfQtr(){
		List result = new ArrayList();
		Calendar cur = Calendar.getInstance();
		cur.add(Calendar.MONTH, -3);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 2003);
		cal.set(Calendar.MONTH, 0);
		while(cal.before(cur)){
			result.add("" + cal.get(Calendar.YEAR) + formatLessThanTen(cal.get(Calendar.MONTH)/3 + 1));
			cal.add(Calendar.MONTH, 3);
		}
		return result;
		
	}
	
	/**
	 * 将传入字符串日期转换为整形，返回
	 * 
	 * @param 年份的字符串数据
	 *            例如20060413或2006-04-13
	 * @return int值,年份的整形值
	 */
	public static int dateToInt(String date) {
		int intDate = 0;
		if (date.length() == 8 && date.indexOf("-") < 0) {
			intDate = Integer.valueOf(date).intValue();
		} else if (date.length() == 10 && date.indexOf("-") > 0) {
			intDate = Integer.valueOf(getYYYYMMDD(date)).intValue();
		} else if (date.length() == 6 || date.length() == 4) {
			intDate = Integer.valueOf(date).intValue();
		} 
		return intDate;
	}
	
	/**
	 * 将传入字符串日期转换为字符串，返回
	 * 
	 * @param 年份的字符串数据
	 *            例如20060413或2006-04-13
	 * @return int值,年份的整形值
	 */
	public static String dateToString(String date) {
		String stringDate = "";
		if (date.length() == 10 && date.indexOf("-") > 0) {
			stringDate = getYYYYMMDD(date);
		} else{
			stringDate = date;
		} 
		return stringDate;
	}

	/**
	 * 将传入的日期字符串YYYY-MM-DD转换为YYYYMMDD格式，返回
	 * 
	 * @param 年份的字符串
	 *            例如2006-04-18
	 * @return 年份的字符串数据 例如20060418
	 */
	public static String getYYYYMMDD(String date) {
		String YYYYMMDD = "";
		YYYYMMDD = date.substring(0, 4) + date.substring(5, 7)
				+ date.substring(8);
		return YYYYMMDD;
	}

	/**
	 * 根据传入的年份判断是否为闰年
	 * 
	 * @param 年份的整形数据
	 *            例如2006
	 * @return boolean值,闰年为true,否则为false
	 */
	public static boolean isLeapYear(int curYear) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		// curYear=gCalendar.get(Calendar.YEAR);
		if (gCalendar.isLeapYear(curYear))
			return true;
		else
			return false;
	}

	/**
	 * 判断当前年份是否为闰年
	 * 
	 * @return boolean值,闰年为true,否则为false
	 */
	public static boolean curYearIsLeapYear() {
		GregorianCalendar gCalendar = new GregorianCalendar();
		curYear = gCalendar.get(Calendar.YEAR);
		if (gCalendar.isLeapYear(curYear))
			return true;
		else
			return false;
	}

	/**
	 * 获得当前月的第一天,每月的第一日还是返回上月第一天的日期字符串
	 * 
	 * @return YYYY-MM-DD
	 */
	public static String getFirstDayOfThisMonth() {
		String today = getCurLastDate2();
		return today.substring(0, 8) + "01";
	}

	/**
	 * 获得指定年月范围内的年月字符串列表
	 * 
	 * @param byear
	 * @param bmonth
	 * @param endYear
	 * @param endMonth
	 * @return
	 */
    public static List getYearMonths(int startYear, int startMonth,
			int endYear, int endMonth) {
		List lst = new ArrayList();
		for (int i = startYear; i <= endYear; i++) {
			for (int j = 1; j <= 12; j++) {
				if (((i == startYear && j < startMonth) || (i == endYear && j > endMonth))) {
				} else {
					String monthStr = "";
					if (j < 10)
						monthStr = "0" + j;
					else
						monthStr = Integer.toString(j);
					String yearMonth = Integer.toString(i) + monthStr;
					lst.add(yearMonth);
					// System.out.print("\n" + yearMonth);
				}

			}
		}
		return lst;
	}

	/**
	 * 获得年月字符串列表
	 * 
	 * @param yearMonth
	 * @param timeSpan
	 *            为正表示从该月算去连续多少个月
	 * @return
	 */
	public static List getYearMonths(String yearMonth, int timeSpan) {
		List lst = new ArrayList();
		int end_year = DateUtil.paraseYear(yearMonth);
		int end_month = DateUtil.paraseMonth(yearMonth);
		int start_year = 0;
		int start_month = 0;
		// 说明没有跨年度
		if (end_month - timeSpan > 0) {
			start_year = end_year;
			start_month = end_month - timeSpan;
		} else {
			start_year = end_year - 1;
			start_month = 12 - (timeSpan - end_month);// mod 12
		}
		lst = getYearMonths(start_year, start_month, end_year, end_month);
		return lst;
	}

	/**
	 * 获得上一个月之前timeSpan个月的年月字符串列表
	 * 
	 * @return
	 */
	public static List getYearMonth(int timeSpan) {
		List lst = new ArrayList();
		String curDate = DateUtil.getCurLastMonth();
		lst = getYearMonths(curDate, timeSpan);
		return lst;

	}

	/***************************************************************************
	 * 返回当年截至前一个月的月份列表.
	 * 
	 * staff ++
	 * 
	 * @return
	 */
    public static ArrayList getListMonths() {

		ArrayList months = new ArrayList(12);
		Calendar datet = Calendar.getInstance();
		int tyear, tmonth;
		tyear = (datet.get(Calendar.MONTH) == 0 ? datet.get(Calendar.YEAR) - 1
				: datet.get(Calendar.YEAR));
		tmonth = (datet.get(Calendar.MONTH) == 0 ? 12 : datet
				.get(Calendar.MONTH));
		for (int i = 0; i < tmonth; i++) {
			months.add(i, String.valueOf(tyear)
					+ (i + 1 > 9 ? (String.valueOf(i + 1)) : ("0" + String
							.valueOf(i + 1))));
		}

		return months;
	}

	/**
	 * 根据传入的日期（Calendar），返回此日期距当年1月1日总共过去了多少天
	 * 
	 * @author 马亮 2006-07-05
	 * @param Calendar
	 */
	public static int getTotalDays(Calendar cal) {
		int totalDays = 0;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		if (isLeapYear(year)) {// 如果是闰年
			for (int i = 0; i < month; i++) {// 循环取得前几个月的天数和
				totalDays += dayOfMonthLeapYear[i];
			}
		} else {
			for (int i = 0; i < month; i++) {// 循环取得前几个月的天数和
				totalDays += dayOfMonth[i];
			}
		}
		totalDays += day;
		return totalDays;
	}

	/**
	 * 根据传入的开始日期（Calendar）和结束日期（Calendar），计算他们之间间隔了多少天,如果开始日期在结束日期之后则返回负值
	 * 
	 * @param Calendar
	 */
	public static int getBetweenDays(Calendar beginCal, Calendar endCal) {
		int betweenDays = 0;
		if (beginCal.before(endCal)) {// 如果开始日期在结束日期之前，则直接调用getBetweenDays1，否则将2个时间对调
			betweenDays = getBetweenDaysOrder(beginCal, endCal);
		} else {
			betweenDays = -getBetweenDaysOrder(endCal, beginCal);
		}
		return betweenDays;
	}

	/**
	 * 根据传入的开始日期（Calendar）和结束日期（Calendar），计算他们之间间隔了多少天，注意开始日期必须在结束日期之前或为同一天
	 * 
	 * @param Calendar
	 */
	public static int getBetweenDaysOrder(Calendar beginCal, Calendar endCal) {
		int betweenDays = 0;
		int begin_year = beginCal.get(Calendar.YEAR);
		int end_year = endCal.get(Calendar.YEAR);
		int leapYearCount = 0;// 闰年计数器
		for (int i = begin_year; i < end_year; i++) {// 在开始时间和结束时间中寻找闰年数
			if (isLeapYear(i)) {// 如果是闰年则闰年计数器加1
				leapYearCount++;
			}
		}
		int betweenYear = end_year - begin_year;// 开始时间和结束时间的间隔年数
		int begin_totalDays = getTotalDays(beginCal);// 获得开始日期距离当年年初的天数
		int end_totalDays = getTotalDays(endCal);// 获得结束日期距离当年年初的天数
		if (betweenYear == 0) {// 如果是同一年，直接用结束日期距年初的天数减去开始日期距年初的天数，便得到间隔天数
			betweenDays = end_totalDays - begin_totalDays;
		} else {// 如果不是同一年，则需要考虑期间经过了多少个闰年，得出计算公式如下
			betweenDays = end_totalDays - begin_totalDays + 366 * leapYearCount
					+ (betweenYear - leapYearCount) * 365;
		}
		return betweenDays;
	}
	
	/**
	 * int转字符串（在小于10的整形前加0）
	 * @param input
	 * @return
	 */
	public static String formatLessThanTen(int input){
		if(input<10)
			return "0" + input;
		else
			return String.valueOf(input);
	}
	
	public static String parseQtr(String date){
		int year = Integer.valueOf(date.substring(0, 4)).intValue();
		int month = Integer.valueOf(date.substring(4, 6)).intValue();
		int qtr = (month-1)/3 + 1;
		return "" + year + formatLessThanTen(qtr);
	}
	
	/*
	*计算传入日期所在星期的星期一的日期
	*@author 王明超
	*/
	public static String getMonday(String date) {
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		   Date d = null;
		   try {
		    d = format.parse(date);
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(d);
		   int days = cal.get(Calendar.DAY_OF_WEEK);
		   if (days == 1) {
		    days = 1;
		    String preMonday = getPreviousMonday(d, days);
		    System.out.println("preMonday" + preMonday);
		    return preMonday;
		   } else {
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		    return format2.format(cal.getTime());
		   }
		}

	//获得上周星期一的日期
	//@author 王明超
	public static String getPreviousMonday(Date date,int days) {
		int weeks=-1;
	    GregorianCalendar currentDate = new GregorianCalendar();
	    currentDate.setTime(date);
	    currentDate.add(GregorianCalendar.DATE, days+7*weeks);
	    Date monday = currentDate.getTime();
	    DateFormat df = DateFormat.getDateInstance();
	    String preMonday = df.format(monday);
	    return preMonday;
	}
	/**
	 * @author 王明超
	 * 判断当前日期是星期几
	 * @param pTime:"20110117"
	 * @return 1-7 对应 星期一到星期日
	 */
	
	public static int dayForWeek(String pTime){  
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
		Calendar c = Calendar.getInstance();  
		int dayForWeek = 0;  
		try {
			c.setTime(format.parse(pTime));
			if(c.get(Calendar.DAY_OF_WEEK) == 1){  
				dayForWeek = 7;  
			}else{  
				dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
			}  
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return dayForWeek;  
		} 
	
		/**  
		 *  @author 王明超
		 *  @param d:"20110117"
	     * 得到几天前的时间  
	     */  
	    public static String getDateBefore(String d, int day) {
	    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        Calendar now = Calendar.getInstance();   
	        try {
				now.setTime(format.parse(d));
				now.set(Calendar.DATE, now.get(Calendar.DATE) - day);   
			} catch (ParseException e) {
				e.printStackTrace();
			}   
	        return format.format(now.getTime());   
	    }   

	public static void main(String args[]) throws Exception {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 2004);
//		cal.set(Calendar.MONTH, 3);
//		cal.set(Calendar.DATE, 1);
//		Calendar cal1 = Calendar.getInstance();
//		cal1.set(Calendar.YEAR, 2007);
//		cal1.set(Calendar.MONTH, 0);
//		cal1.set(Calendar.DATE, 1);
//		System.out.println(DateUtil.getLastDate(cal));
		
		System.out.println( getDateBefore("20110120",2) );
		
		
//		List aa = new ArrayList();
//		aa = getListOfQtr();
//		for(int i=0;i<aa.size();i++){
//			System.out.println(aa.get(i));
//		}
//		Calendar aaa = Calendar.getInstance();
//		System.out.println(aaa.get(Calendar.WEEK_OF_YEAR));
	}
	
}
