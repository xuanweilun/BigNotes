package notes.service.test.timerDemo;

import java.util.Calendar;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class TimerTest {

	public static void main(String[] args) {
		
		System.out.println(isLastHourOfMonth());
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2020年1月16日 上午11:28:09 
	 * @Description: 判断当前时间是否是当月的最后一天的最后一个小时
	 */
	public static final boolean isLastHourOfMonth() {
		Calendar calendar = Calendar.getInstance();
		//当前日期是最后一天的话。日期再加1等于1号
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			if(23 == calendar.get(Calendar.HOUR)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2020年1月16日 下午2:09:24 
	 * @Description: 判断当前时间是否是每个月的第一天第一个小时
	 */
	public static final boolean isFirstHourOfMonth() {
		Calendar calendar = Calendar.getInstance();
		if(1 == calendar.get(Calendar.DATE)) 
		{
			if(0 == calendar.get(Calendar.HOUR)) 
			{
				return true;
			}
		}
		return false;
	}
	
	
				//     秒  分  时  日  月  周    每月1号零点
	@Scheduled(cron = "0 0 0 1 * ?")
	public void sys() {
		System.out.println("xuanweilun haha .");
	}
}
