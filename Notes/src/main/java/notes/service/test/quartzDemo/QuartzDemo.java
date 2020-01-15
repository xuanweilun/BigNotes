package notes.service.test.quartzDemo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo {
	
	

	/**
	 * second minute hour date month week year
	 * 字段   允许值   允许的特殊字符   
		秒    0-59    , - * /   
		分    0-59    , - * /   
		小时    0-23    , - * /   
		日期    1-31    , - * ? / L W C   
		月份    1-12 或者 JAN-DEC    , - * /   
		星期    1-7 或者 SUN-SAT    , - * ? / L C #   
		年（可选）    留空, 1970-2099    , - * /   
		*    表示所有值；   
		?    表示未说明的值，即不关心它为何值；   
		-    表示一个指定的范围；   
		,    表示附加一个可能值；   
		/    符号前表示开始时间，符号后表示每次递增的值；   
		L("last")    ("last") "L" 用在day-of-month字段意思是 "这个月最后一天"；用在 day-of-week字段, 它简单意思是 "7" or "SAT"。 如果在day-of-week字段里和数字联合使用，它的意思就是 "这个月的最后一个星期几" – 例如： "6L" means "这个月的最后一个星期五". 当我们用“L”时，不指明一个列表值或者范围是很重要的，不然的话，我们会得到一些意想不到的结果。   
		W("weekday")    只能用在day-of-month字段。用来描叙最接近指定天的工作日（周一到周五）。例如：在day-of-month字段用“15W”指“最接近这个月第15天的工作日”，即如果这个月第15天是周六，那么触发器将会在这个月第14天即周五触发；如果这个月第15天是周日，那么触发器将会在这个月第16天即周一触发；如果这个月第15天是周二，那么就在触发器这天触发。注意一点：这个用法只会在当前月计算值，不会越过当前月。“W”字符仅能在day-of-month指明一天，不能是一个范围或列表。也可以用“LW”来指定这个月的最后一个工作日。    
		#    只能用在day-of-week字段。用来指定这个月的第几个周几。例：在day-of-week字段用"6#3"指这个月第3个周五（6指周五，3指第3个）。如果指定的日期不存在，触发器就不会触发。    
		C    指和calendar联系后计算过的值。例：在day-of-month 字段用“5C”指在这个月第5天或之后包括calendar的第一天；在day-of-week字段用“1C”指在这周日或之后包括calendar的第一天  
	 * @author xuanweilun   
	 * @date 2019年11月11日 下午6:13:52 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param args
	 * @throws SchedulerException void
	 * @throws
	 */
	
	
	void save(Long schemaId) throws SchedulerException {
		//1.get job
		JobDetail jobDetail = this.getJobDetail(schemaId);
		//2.get time
		String time = this.getTime(new Timestamp(System.currentTimeMillis()));
		//3.get trigger
		CronTrigger trigger = this.getTrigger(schemaId,time);
		//3.get schedule
		Scheduler scheduler = this.getScheduler();
		boolean a;
		
		a = this.pauseTrigger("");
		JobKey jobKey  = new JobKey("");
		a = scheduler.deleteJob(jobKey);
		a = this.deleteJob("");
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	
	public static void main(String[] args) {
//		SimpleDateFormat sdf2 = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
//		String format = sdf2.format(new Date());
//		System.out.println(format);
//		CronTrigger trigger = (CronTrigger) TriggerBuilder
//                .newTrigger()
//                .withIdentity("1")   //创建一个标识符
//                .withSchedule(CronScheduleBuilder.cronSchedule(format))
//                .build();
//		
//		JobDetail jobDetail=JobBuilder
//				.newJob(MyJob.class)
//				.withIdentity("fuck")
//                .build();
//		
//		Scheduler scheduler;
//		try {
//			scheduler = getScheduler();
//			scheduler.scheduleJob(jobDetail, trigger);
//			
//		} catch (SchedulerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(new Timestamp(1554048609000L));
		
		String url = "${mainServer}/schemas/{schemaId}/criterias/{criteriaId}/disableInternalComment";
		url = url.replace("{schemaId}", new Long(1).toString()).replace("{criteriaId}", new Long(2).toString());
		System.out.println(url);
	}
	
	
	
	
	private boolean deleteJob(String string) throws SchedulerException {
		Scheduler scheduler = this.getScheduler();
		JobKey jobKey  = new JobKey(string);
		boolean success = scheduler.deleteJob(jobKey);
		return success;
	}

	private boolean pauseTrigger(String string) throws SchedulerException {
		Scheduler scheduler = this.getScheduler();
		TriggerKey T = new TriggerKey(string);
		scheduler.pauseTrigger(T); //停止
		return true;
	}

	private CronTrigger getTrigger(Long schemaId,String time) {
		CronTrigger trigger = (CronTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity(schemaId.toString())   //创建一个标识符
//                .startAt(new Date())//什么时候开始触发
                .withSchedule(CronScheduleBuilder.cronSchedule(time))
                .build();
		return trigger;
	}

	private String getTime(Timestamp timestamp) {
		String time = "*/5 * * * * ?";//    每月15日上午10:15触发  
		return time;
	}

	/**
	 * @author xuanweilun   
	 * @date 2019年11月12日 下午3:44:52 
	 * @Title: getJobDetail 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param schemaId
	 * @return JobDetail
	 * @throws
	 */
	private JobDetail getJobDetail(Long schemaId) {
		JobDetail jobDetail=JobBuilder
				.newJob(MyJob.class)
				.withIdentity(schemaId.toString())
                .build();
		return jobDetail;
	}

	/**
	 * @author xuanweilun   
	 * @date 2019年11月12日 下午3:44:47 
	 * @Title: getScheduler 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 * @throws SchedulerException Scheduler
	 * @throws
	 */
	public static Scheduler getScheduler() throws SchedulerException {
		SchedulerFactory sfact=new StdSchedulerFactory();
		Scheduler scheduler= sfact.getScheduler();
		if(!scheduler.isShutdown()) {
			scheduler.start();  
		}
		return scheduler;
	}
	
}
