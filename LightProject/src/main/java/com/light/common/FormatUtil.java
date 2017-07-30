package com.light.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 格式转换工具
 * @author TobyHan
 * Date: 2016年10月24日 下午7:48:29
 */
public class FormatUtil {

	/**
	 * 时间戳 转为 日期字符串
	 * @author TobyHan
	 * Date : 2016年10月24日 下午7:57:48
	 * return String
	 */
	public static String timeStamp2Date(Integer timeStamp,String format){
		if(timeStamp == null || timeStamp == 0) return null;
		if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		Long temp = (long)timeStamp*1000;
	//	System.out.println(temp);
        return sdf.format(new Date(temp));
	}
	/**
	 * return String
	 * 获取当前时间戳
	 * @author TobyHan
	 * Date : 2016年10月24日 下午7:58:19
	 */
	public static String timeStampStr(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }
	/**
	 * return Integer
	 * 获取当前时间戳
	 * @author TobyHan
	 * Date : 2016年10月25日 下午4:02:31
	 */
	public static Integer timeStampInt(){
		long time = System.currentTimeMillis(); 
		return (int) (time/1000);
	}
	/**
	 * 日期转化为时间戳
	 * @author TobyHan
	 * Date : 2016年10月25日 上午8:51:24
	 * return Integer
	 */
	public static Integer date2TimeStamp(String time,String format){
		if(time == "") return null;
		if(format == null || format.isEmpty()) format = "yyyy-MM-dd";
		//Date或者String转化为时间戳  
	    SimpleDateFormat sdf =  new SimpleDateFormat(format);  
	    //  String time="1970-01-06 11:45:55";  
	    Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	    
	    return (int) (date.getTime()/1000);
	}
	
	/**
	 * return double
	 * 将数字缩小两位
	 * @author TobyHan
	 * Date : 2016年10月25日 上午9:00:18
	 */
	public static double drop2DigitToDouble(Integer num){
		return Double.valueOf(num/100+"."+num%100);
	}
	/**
	 * return String
	 * 将数字缩小两位
	 * @author TobyHan
	 * Date : 2016年10月25日 上午9:00:18
	 */
	public static String drop2DigitToString(Integer num){
		if(num != null && num != 0)
		return num/100+"."+num%100;
		return "0";
	}
	/**
	 * 数字时间区间
	 * @Title: timeStampInterval 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午2:45:23
	 */
	public static boolean integerInterval(Integer startTime, Integer endTime){
		if(startTime != null && endTime != null)
			return startTime < endTime ? true : false;
		return false;
	}
	
	public static int getDateNoByStr(String dateNo) { 
		String[] realDate = dateNo.split(":");
		
	    Calendar calendar = Calendar.getInstance();// 获取当前日期  
	    
	    /* 
         * 当设置的单位对应的值超过了该单位允许的最大值时， 
         * Calendar会自动进位 
         * 这里小时会向天进位 
         * 设置星期也会影响日期 
         *  
         */ 
	    //1表示周日 ，2表示周一  
	   // calendar.add(Calendar.MONTH, Integer.valueOf(realDate[1])); 
        calendar.set(Calendar.YEAR, Integer.valueOf(realDate[0]));  
        // 月是从0开始的，0为1月 
        calendar.set(Calendar.MONTH,Integer.valueOf(realDate[1])-1);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天  
	    calendar.set(Calendar.HOUR_OF_DAY, 0);  
	    calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND, 0);
	    Integer finalTime = (int)(calendar.getTimeInMillis()/1000);
	    return finalTime;  
	} 
	//本月第一天时间戳
	public static int getCurDateNo() { 
	    Calendar calendar = Calendar.getInstance();// 获取当前日期  
	    calendar.add(Calendar.MONTH, 0);  
	    calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天  
	    calendar.set(Calendar.HOUR_OF_DAY, 0);  
	    calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND, 0); 
	    
	    Integer finalTime = (int)(calendar.getTimeInMillis()/1000);

	    return finalTime; 
	} 
	public static int getCurMonthMaxTime() {
		Calendar calendar = Calendar.getInstance();  
		// 设置时间,当前时间不用设置  
		// 设置日期为本月最大日期  
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
	    calendar.set(Calendar.HOUR_OF_DAY, 23);  
	    calendar.set(Calendar.MINUTE, 59); 
	    calendar.set(Calendar.SECOND, 59); 
	    Integer finalTime = (int)(calendar.getTimeInMillis()/1000);
	    return finalTime; 

	}
	/**
	 * 根据时间戳，获取当月dateNo
	 * @Title: getDateNoByTimestamp 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午4:38:02
	 */
	public static int getDateNoByTimestamp(Integer timeStamp){
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		Long temp = (long)timeStamp*1000;
		String[] strArr = sdf.format(temp).split("-");
		return getDateNoByStr(strArr[0]+":"+strArr[1]);
	}
	
	public static int getPassOrFurtureDateNo(int num){
		Calendar calendar = Calendar.getInstance();// 获取当前日期  
	    calendar.add(Calendar.MONTH, num);  
	    calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天  
	    calendar.set(Calendar.HOUR_OF_DAY, 0);  
	    calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND, 0); 
	    
	    Integer finalTime = (int)(calendar.getTimeInMillis()/1000);

	    return finalTime; 
	}
	public static void main(String args[]){
		//String timeStamp = timeStamp();
		//System.out.println(timeStamp);

		//System.out.println(timeStamp2Date(Integer.valueOf(timeStamp),null));
		
		//date2TimeStamp("2016-10-25",null);
		
		//System.out.println(drop2DigitToString(22));
		//System.out.println(drop2DigitToDouble(322));
		//int temp = (int) (System.currentTimeMillis()/1000);
		//String date = timeStamp2Date(1458022228,null);
		//System.out.println(date.substring(8, 10));
		//getCurDateNo();
	/*	//System.out.println(getCurMonthMaxTime());
		//String json = "[\n" +
				"    {\n" +
				"        \"floor\": \"地上一层\",\n" +
				"        \"stall\": \"A1,A2,A3\"\n" +
				"    },\n" +
				"		 {\n" +
				"        \"floor\": \"地下二层\",\n" +
				"        \"stall\": \"B1,B2,B3\"\n" +
				"    }\n" +
				"]";*/
	//	List<FloorInfoCommitBean>  bean = JSONObject.parseArray(json,FloorInfoCommitBean.class);
	//	System.out.println(bean.get(1).getStall());
		//System.out.println(getDateNoByTimestamp(1484064000));
		   // System.out.println(getPassOrFurtureDateNo(1));  
			List<Integer> timeList = new ArrayList<>();
			for(int i=0; i<4; i++){
				timeList.add(FormatUtil.getPassOrFurtureDateNo(-i));
			}
			for(Integer element : timeList)
				System.out.println(element);


	}
}
