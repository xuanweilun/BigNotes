package notes.service.test.quartzDemo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * 
 * @author xwl
 *
 */
public class CronUtils {
		private static final SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ?");
		private static final String lDateOfMonthCron = "59 59 23 L * ?";
	    /***
	     *  功能描述：日期转换cron表达式
	     * @param time
	     * @return
	     */
	    public static String formatDateByPattern(Timestamp time) {
	        String formatTimeStr = null;
	        if (Objects.nonNull(time)) {
	            formatTimeStr = sdf.format(time);
	        }
	        return formatTimeStr;
	    }
	    
	    public static void main(String[] args) {
	    	System.out.println(getCron(new Timestamp(1508114402000L)));
	    }
	    

	    /***
	     * convert Date to cron, eg "0 07 10 15 1 ? 2016"
	     * @param date  : 时间点
	     * @return
	     */
	    public static String getCron(Timestamp date) {
	        return formatDateByPattern(date);
	    }
	    
	    /**
	     * @author xuanweilun   
	     * @date 2019年11月13日 上午10:28:50 
	     * @Title: getLastDayOfMonth 
	     * @Description: 每月最后一天
	     * @return String
	     * @throws
	     */
	    public static String getLastDayOfMonth() {
	    	return CronUtils.lDateOfMonthCron;
	    }
	    
}
