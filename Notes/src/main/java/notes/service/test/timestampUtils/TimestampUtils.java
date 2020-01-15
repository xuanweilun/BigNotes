package notes.service.test.timestampUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TimestampUtils {
	

	
	private static final String timeZone = "GMT+8:00";
	
	/**
     * 获取当月开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getMonthStartTime(Long timeStamp,int month) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, month); //偏移月
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    
    public static void main(String[] args) {
//    	 Calendar calendar = Calendar.getInstance();// 获取当前日期
//         calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
//         calendar.setTimeInMillis(1541984205000L);
//        System.out.println( calendar.get(Calendar.YEAR));;
//    	Integer a = new Integer(10);
//    	String aa = a.toString();
//    	System.out.println(aa);
    	SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	System.out.println(sim.format(new Date()));
    	
	}
 
    /**
     * 获取当月的结束时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getMonthEndTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }
    
    
//    public static void main(String[] args) {
//    	List<BeginAndEnd> bgs = getMonthsBeginTimeAndEndTimeBeteewn(1556585839000L, 1572319144000L);
//    	for(BeginAndEnd bg:bgs) {
//    		System.out.println(new Timestamp(bg.getBeginTimes()));
//    		System.out.println(new Timestamp(bg.getEndTimes()));
//    	}
//    }
    
   
    /**
     * @author xuanweilun   
     * @date 2019年10月29日 下午4:23:17 
     * @Title: getMonthsBeginTimeAndEndTimeBeteewn 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param begin
     * @param end
     * @return List<BeginAndEnd>
     * @throws
     */
    public static List<BeginAndEnd> getMonthsBeginTimeAndEndTimeBeteewn(Long begin,Long end) {
    	List<BeginAndEnd> bgs= new ArrayList<BeginAndEnd>();
    	long beginTimeInMillis = begin;
    	long endTimeInMillis = 0;
    	//第一月
    	beginTimeInMillis = getMonthStartTime(beginTimeInMillis,0);
    	endTimeInMillis = getMonthEndTime(beginTimeInMillis);
    	bgs.add(new TimestampUtils().new BeginAndEnd(beginTimeInMillis,endTimeInMillis));
    	//第二月到最后一个月
    	while(beginTimeInMillis < end) {
    		beginTimeInMillis = getMonthStartTime(beginTimeInMillis,1);
    		if(beginTimeInMillis > end) {
    			break;
    		}
    		endTimeInMillis = getMonthEndTime(beginTimeInMillis);
    		bgs.add(new TimestampUtils().new BeginAndEnd(beginTimeInMillis,endTimeInMillis));
    	}
    	return bgs;
    }
    
    /**
     * 
     * @author Administrator
     *
     */
    class BeginAndEnd {
    	private Long beginTimes;
    	private Long endTimes;
    	
		public BeginAndEnd(Long beginTimes, Long endTimes) {
			super();
			this.beginTimes = beginTimes;
			this.endTimes = endTimes;
		}
		public Long getBeginTimes() {
			return beginTimes;
		}
		public void setBeginTimes(Long beginTimes) {
			this.beginTimes = beginTimes;
		}
		public Long getEndTimes() {
			return endTimes;
		}
		public void setEndTimes(Long endTimes) {
			this.endTimes = endTimes;
		}
    	
    }
    
    
    
}
