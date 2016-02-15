package com.yz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
/**
 * 日期、时间工具类
 * @author JLJ&LQ
 *
 */
public class DateTimeKit {
	/**
	 * 得到当前日期与时间,精确到[时:分:秒]
	 * @return
	 */
	public static String getLocalTime() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**
	 * 得到当前日期与时间,精确到[天]
	 * @return
	 */
	public static String getLocalDate() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**
	 * 得到当前日期与时间,精确到[时]
	 * @return
	 */
	public static String getLocalhour() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**
	 * 得到当前日期与时间,精确到[时:分]
	 * @return
	 */
	public static String getLocalHM() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**
	 * 根据日期获得随即字符串
	 */
	public static String getDateRandom() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString=sdf.format(date);
		Random random=new Random();
		int rand=random.nextInt(1000);
		dateString=dateString+rand;
		return dateString;
	}
	
	/**
	 * 某个日期转换成字符串
	 * @return
	 */
	public static String getDateString(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**
	 * 某个日期转换成字符串
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**
	 * 某个日期到分钟转换成字符串
	 * @return
	 */
	public static String getDateMinuteString(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws java.text.ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
     
    /**  
     * 计算两个日期之间相差的分钟  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int minutesBetweenDate(Date smdate,Date bdate) throws java.text.ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*60);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
    
    /**  
     * 计算两个日期之间相差的分钟  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int minutesBetweenStr(String smdate,String bdate) throws java.text.ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date smdateD=sdf.parse(smdate);  
        Date bdateD=sdf.parse(bdate);  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdateD);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdateD);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*60);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }   
    
	/** 
	*字符串的日期格式的计算 
	 * @throws java.text.ParseException 
	*/  
    public static int daysBetween(String smdate,String bdate) throws java.text.ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
    
    /** 
     *获得翻几天之前的日期字符串 
	 * @throws java.text.ParseException 
	*/  
    public static String dateBeforethis(String thisdateStr,int days) throws java.text.ParseException{  
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
    	Date thisdate = sdf.parse(thisdateStr);   //当前时间
    	Date beforedate = new Date();
    	Calendar calendar = Calendar.getInstance(); //得到日历
    	calendar.setTime(thisdate);//把当前时间赋给日历
    	calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为前一天
    	beforedate = calendar.getTime();   //得到前一天的时间
    	String beforedateStr = sdf.format(beforedate);    //格式化前一天
    	

//    	System.out.println("原日期是：" + thisdateStr);
//    	System.out.println("前日期是：" + beforedateStr); 
    	return beforedateStr;
    	
    } 
    
    /** 
     *获得翻几小时之前的日期字符串 
	 * @throws java.text.ParseException 
	*/  
    public static String hourBeforethis(String thisdateStr,int hours) throws java.text.ParseException{  
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
    	Date thisdate = sdf.parse(thisdateStr);   //当前时间
    	Date beforedate = new Date();
    	Calendar calendar = Calendar.getInstance(); //得到日历
    	calendar.setTime(thisdate);//把当前时间赋给日历
    	calendar.add(Calendar.HOUR_OF_DAY, -hours);  //设置为前一天
    	beforedate = calendar.getTime();   //得到前一天的时间
    	String beforedateStr = sdf.format(beforedate);    //格式化前一天
    	return beforedateStr;
    	
    } 
    
    /** 
     *获得翻几小时之前的日期字符串 
	 * @throws java.text.ParseException 
	*/  
    public static String MinuteBeforethis(String thisdateStr,int minutes) throws java.text.ParseException{  
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); //设置时间格式
    	Date thisdate = sdf.parse(thisdateStr);   //当前时间
    	Date beforedate = new Date();
    	Calendar calendar = Calendar.getInstance(); //得到日历
    	calendar.setTime(thisdate);//把当前时间赋给日历
    	calendar.add(Calendar.MINUTE, -minutes);  //设置为前一天
    	beforedate = calendar.getTime();   //得到前一天的时间
    	String beforedateStr = sdf.format(beforedate);    //格式化前一天
    	return beforedateStr;
    	
    } 
    
    public static void main(String[] args) throws java.text.ParseException {
//    	System.out.println(DateTimeKit.daysBetween("2012-09-08","2012-09-09")); 
//    	System.out.println(dateBeforethis("2015-07-05",-5));
    	System.out.println(MinuteBeforethis("2015-07-05 14:41",-1000));
    	
    	System.out.println(minutesBetweenStr("2012-09-09 10:00","2012-09-09 11:00"));
    	
	}
}
