package notes.service.test.schemaTimer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import notes.service.test.quartzDemo.CronUtils;
import notes.service.test.quartzDemo.MyJob;

/**
 * 
 * @author Administrator
 *
 */
public class QuartzUtils {
	private static Logger logger = LoggerFactory.getLogger(QuartzUtils.class);
	private static SchedulerFactory sfact=new StdSchedulerFactory();
	
	public static boolean addJob(Long key) {
//		 try {
//	            //通过SchedulerFactory来获取一个调度器 
//	            Scheduler sched = getScheduler();
//	            //引进作业程序  
//	            JobDetail jobDetail = getJobDetail(key);
//	            //触发器
//	            CronTrigger trigger = getTrigger(key, "");
//	            // 触发器时间设定
//	            sched.scheduleJob(jobDetail, trigger);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            throw new RuntimeException(e);
//	        }
		return true;
	}
	
    public static void modifyJobTime(Long triggerName,String time) {
        try {
            Scheduler sched = QuartzUtils.getScheduler();
            TriggerKey triggerKey = new TriggerKey(triggerName.toString());
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
//                CronTrigger ct = (CronTrigger) trigger;
                // 修改时间
                deleteJob(triggerName);
//                // 重启触发器
                addJob(triggerName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteJob(Long key) throws SchedulerException {
    	//1.check param
    	if(StringUtils.isEmpty(key)) {
    		logger.info("null input");
    		return false;
    	}
    	TriggerKey triggerKey = new TriggerKey(key.toString());
    	JobKey jobKey  = new JobKey(key.toString());
		Scheduler scheduler = QuartzUtils.getScheduler();
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		if(null == jobDetail) {
			logger.info("can not find job when key = " + key);
    		return false;
		}
		Trigger trigger = scheduler.getTrigger(triggerKey);
		if(null == trigger) {
			logger.info("can not find trigger when key = " + key);
    		return false;
		}
		//2.pause trigger
		scheduler.pauseTrigger(triggerKey);
		scheduler.unscheduleJob(triggerKey);
		//3.delete job
		scheduler.deleteJob(jobKey);
		return true;
	}
    
    public static boolean unScheduleJob(String string) throws SchedulerException {
		Scheduler scheduler = QuartzUtils.getScheduler();
		TriggerKey T = new TriggerKey(string);
		boolean success = scheduler.unscheduleJob(T);
		return success;
	}
	
//	public static boolean deleteJob(String string) throws SchedulerException {
//		Scheduler scheduler = QuartzUtils.getScheduler();
//		JobKey jobKey  = new JobKey(string);
////		scheduler.unscheduleJob(triggerKey);
//		boolean success = scheduler.deleteJob(jobKey);
//		return success;
//	}

	public static boolean pauseTrigger(String string) throws SchedulerException {
		Scheduler scheduler = QuartzUtils.getScheduler();
		TriggerKey T = new TriggerKey(string);
		scheduler.pauseTrigger(T); //停止
		return true;
	}

//	public static CronTrigger getTrigger(Long schemaId,String time) {
//		public static void main(String[] args) throws SchedulerException {
//			Scheduler scheduler = QuartzUtils.getScheduler();
//			
//			CronTrigger trigger = (CronTrigger) TriggerBuilder
//					.newTrigger()
//					.withIdentity("1","x")   //创建一个标识符
//					.startAt(new Timestamp(System.currentTimeMillis()))//什么时候开始触发
//					.withSchedule(CronScheduleBuilder.cronSchedule("10 31 * * * ?"))
//					.usingJobData("xuan", 2D)
//					.build();
//			
//			CronTrigger trigger2 = (CronTrigger) TriggerBuilder
//					.newTrigger()
//					.withIdentity("2","x")   //创建一个标识符
//					.startAt(new Timestamp(System.currentTimeMillis()))//什么时候开始触发
//					.withSchedule(CronScheduleBuilder.cronSchedule("00 32 * * * ?"))
//					.usingJobData("xuan", 3D)
//					.build();
//			Set<CronTrigger> triggers = new HashSet<CronTrigger>();
//			triggers.add(trigger);
//			triggers.add(trigger2);
//			JobDetail jobDetail=JobBuilder
//					.newJob(MyJob.class)
//					.withIdentity("1")
//	                .build();
//			scheduler.scheduleJob(jobDetail, triggers, true);
//			scheduler.start();
//			
////			GroupMatcher<TriggerKey> triggerKeyMather = GroupMatcher.groupEquals("x");
////			//1.暂停
////			Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(triggerKeyMather);
////			System.out.println("fff"+triggerKeys);
//////			scheduler.pauseTriggers(triggerKeyMather);
////			
////			GroupMatcher<JobKey> jobKeyMather = GroupMatcher.groupEquals("1");
////			Set<JobKey> jobKeys = scheduler.getJobKeys(jobKeyMather);
//			//3.删除job
////			if(null != jobKeys && !jobKeys.isEmpty()){
////				scheduler.deleteJobs(new ArrayList<JobKey>(jobKeys));
////			}
//			System.out.println(scheduler.getJobGroupNames());
//		}
		
		public static void main(String[] args) throws SchedulerException {
			Scheduler scheduler = QuartzUtils.getScheduler();
			Set<Long> cyclePhases = new HashSet<Long>();
			cyclePhases.add(1L);
			cyclePhases.add(2L);
			cyclePhases.add(3L);
			cyclePhases.add(4L);
			int count = 15;
			for(Long cyclePhase:cyclePhases){
				JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
						.withIdentity(cyclePhase.toString(),"1")
						.build(); 
				JobDataMap cyclePhaseMap = new JobDataMap();
				// get trigger
				Trigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(cyclePhase.toString(),"1")
						.withSchedule(CronScheduleBuilder.cronSchedule((count++)+" 15 * * * ?"))
						.startAt(new Date())
						.usingJobData(cyclePhaseMap)
						.build();
				try {
				scheduler.scheduleJob(jobDetail, trigger);
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			}
		}
		
//	}


	public static String getCron(Timestamp timestamp) {
		String cron = CronUtils.getCron(timestamp);
		return cron;
	}

	/**
	 * @author xuanweilun   
	 * @date 2019年11月12日 下午3:44:52 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 */
	public static JobDetail getJobDetail(Long schemaId) {
		JobDetail jobDetail=JobBuilder
				.newJob(MyJob.class)
				.withIdentity(schemaId.toString())
                .build();
		return jobDetail;
	}

	/**
	 * @author xuanweilun   
	 * @date 2019年11月12日 下午3:44:47 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 */
	public static Scheduler getScheduler() throws SchedulerException {
		Scheduler scheduler= sfact.getScheduler();
		if(!scheduler.isShutdown()) {
			scheduler.start();  
		}
		return scheduler;
	}
}
